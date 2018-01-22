package com.core.company.assist;

import com.core.common.dao.RegionDao;
import com.core.company.bean.Company;
import com.core.company.dao.CompanyDao;
import com.core.company.dao.CompanyNatureDao;
import com.core.company.dao.CompanyTypeDao;
import com.core.company.dto.CompanyBriefnessMessage;
import com.core.company.dto.CompanySuperMessage;
import com.core.user.dto.UserAllMessage;
import com.utils.RandomGenerate;
import com.utils.regular.OtherReg;
import com.utils.regular.UserReg;

import java.util.List;

/**
 * Created by GSN on 2017/5/19.
 * 辅助Company类
 */
public class AssistCompany {

    private CompanyDao companyDao;
    private CompanyTypeDao companyTypeDao;
    private CompanyNatureDao companyNatureDao;
    private RegionDao regionDao;

    public AssistCompany(CompanyDao companyDao) {
        this.companyDao = companyDao;
    }

    public AssistCompany(CompanyDao companyDao, CompanyTypeDao companyTypeDao, CompanyNatureDao companyNatureDao, RegionDao regionDao) {
        this.companyDao = companyDao;
        this.companyTypeDao = companyTypeDao;
        this.companyNatureDao = companyNatureDao;
        this.regionDao = regionDao;
    }

    /**
     * 获取指定单位的所有上级单位
     *
     * @param company 要获取该单位的上级单位
     * @return 上级单位链表
     */
    public CompanySuperMessage getSuperCompany(Company company) {

        Company superCompany = companyDao.getCompany(company.getpId());

        if (superCompany != null) {

            CompanySuperMessage companySuperMessage = this.getSuperCompany(superCompany);

            CompanySuperMessage companySuperMessage1 = new CompanySuperMessage();

            companySuperMessage1.setId(superCompany.getId());
            companySuperMessage1.setName(superCompany.getName());
            companySuperMessage1.setCheck(superCompany.getCheck());
            companySuperMessage1.setCompanySuperMessage(companySuperMessage);


            return companySuperMessage1;

        }

        return null;


    }

    /**
     * 修改单位信息
     *
     * @param company        修改的单位信息
     * @param userAllMessage 当前操作的用户
     * @return null：数据合法
     */
    public String updateCompany(Company company, UserAllMessage userAllMessage) {

        //验证用户是否有权限修改该单位信息
        boolean b = false;
        for (CompanyBriefnessMessage cbm : userAllMessage.getCompanyBriefnessMessageList()) {
            if (cbm.getId().equals(company.getId())) {
                b = true;
                break;
            }
        }
        if (!b) {
            return "系统检测该账号无权限修改这个单位的信息";
        }

        String result = this.verifyAttribute(company);

        if (result != null) {
            return result;
        }

        if (!company.getpId().equals("0")) {
            result = this.verifySuperCompany(company.getpId());
            if (result != null) {
                return result;
            }
        }

        if (!this.verifyName(company.getName(), company.getId())) {
            return "单位名称已被使用";
        }

        if (!this.verifyType(company.getTypeId())) {
            return "单位类型ID错误";
        }

        if (!this.verifyNature(company.getNatureId())) {
            return "单位性质ID错误";
        }

        if (!this.verifyRegion(company.getRegionId())) {
            return "单位地区ID错误";
        }

        if (!this.verifyCode(company.getCode(), company.getId())) {
            return "组织机构代码、社会信用代码已被使用";
        }


        return null;
    }

    /**
     * 单位注册验证
     *
     * @param company 单位数据
     * @return null：数据合法
     */
    public String saveCompany(Company company) {

        String result = this.verifyAttribute(company);

        if (result != null) {
            return result;
        }

        if (!company.getpId().equals("0")) {
            result = this.verifySuperCompany(company.getpId());
            if (result != null) {
                return result;
            }
        }

        result = this.createKey();

        if (result == null) {
            return "创建单位密匙失败！";
        } else {
            company.setKey(result);
        }

        if (!this.verifyName(company.getName())) {
            return "单位名称已被使用";
        }

        if (!this.verifyType(company.getTypeId())) {
            return "单位类型ID错误";
        }

        if (!this.verifyNature(company.getNatureId())) {
            return "单位性质ID错误";
        }

        if (!this.verifyRegion(company.getRegionId())) {
            return "单位地区ID错误";
        }

        if (!this.verifyCode(company.getCode())) {
            return "组织机构代码、社会信用代码已被使用";
        }

        return null;
    }

    /**
     * 验证单位属性是否合法
     *
     * @param company 单位信息
     * @return null：数据合法
     */
    private String verifyAttribute(Company company) {

        if (company == null) {
            return "请填写单位信息";
        }

        if (company.getpId() == null || company.getpId().trim().equals("")) {
            company.setpId("0");
        }

        if (company.getName() == null) {
            return "请填写单位名称";
        }

        company.setName(company.getName().replace(" ", ""));

        if (company.getName().equals("")) {
            return "请输入单位名称";
        }

        if (company.getName().length() > 50) {
            return "单位名称长度限制50汉字、字符";
        }

        if (company.getTypeId() == null) {
            return "请选择单位类型";
        }

        company.setTypeId(company.getTypeId().replace(" ", ""));

        if (company.getTypeId().equals("") || company.getTypeId().equals("0")) {
            return "请选择单位类型";
        }

        if (company.getNatureId() == null || company.getNatureId().equals("0")) {
            return "请选择单位性质";
        }

        company.setNatureId(company.getNatureId().replace(" ", ""));

        if (company.getNatureId().equals("")) {
            return "请选择单位性质";
        }

        if (company.getRegionId() == null || company.getRegionId().equals("0")) {
            return "请选择地区";
        }

        company.setRegionId(company.getRegionId().replace(" ", ""));

        if (company.getRegionId().equals("")) {
            return "请选择地区";
        }

        if (company.getCode() == null) {
            return "请填写组织机构代码、社会信用代码";
        }

        company.setCode(company.getCode().replace(" ", ""));

        if (!new OtherReg().alphanumeric(company.getCode())) {
            return "组织机构代码、社会信用代码格式错误";
        }

        if (company.getPhone() == null) {
            return "请填写座机号码";
        }

        company.setPhone(company.getPhone().replace(" ", ""));

        if (!new UserReg().gdPhone(company.getPhone())) {
            return "座机号码格式错误(010-1111111)";
        }

        return null;
    }

    /**
     * 验证上级单位
     *
     * @param companyId 上级单位id
     * @return null：上级单位可以使用
     */
    private String verifySuperCompany(String companyId) {

        Company company = companyDao.getCompany(companyId);

        if (company == null) {
            return "上级单位不存在";
        }

        if (company.getCheck() == 0) {
            return "单位正在审核";
        }

        if (company.getCheck() == 2) {
            return "单位审核未通过";
        }

        return null;
    }

    /**
     * 生成单位密匙
     *
     * @return null：生成失败
     */
    private String createKey() {

        String key = null;
        RandomGenerate randomGenerate = new RandomGenerate();

        for (int i = 0; i < 100; i++) {
            String createKey = randomGenerate.generateNd(9);
            String hql = "from Company c where c.key='" + createKey + "'";
            if (companyDao.queryCompany(hql).isEmpty()) {
                key = createKey;
                break;
            }
        }

        return key;

    }

    /**
     * 验证单位名称是否可用
     *
     * @param companyName 单位名称
     * @param companyIds  去重参数里面的相同id
     * @return false：重复单位
     */
    private boolean verifyName(String companyName, String... companyIds) {
        return this.repetition(companyDao.queryCompany("from Company c where c.name='" + companyName + "'"), companyIds);
    }

    /**
     * 验证单位类型
     *
     * @param typeId 单位类型id
     * @return false：id错误
     */
    private boolean verifyType(String typeId) {
        return companyTypeDao.getCompanyType(typeId) != null;
    }

    /**
     * 验证单位性质
     *
     * @param natureId 单位性质id
     * @return false：id错误
     */
    private boolean verifyNature(String natureId) {
        return companyNatureDao.getCompanyNature(natureId) != null;
    }

    /**
     * 验证省市区
     *
     * @param regionId 省市区id
     * @return false：id错误
     */
    private boolean verifyRegion(String regionId) {
        return regionDao.getRegion(regionId) != null;
    }

    /**
     * 验证组织机构代码、社会信用代码是否重复
     *
     * @param code       代码证号码
     * @param companyIds 过滤掉的id
     * @return false：重复
     */
    private boolean verifyCode(String code, String... companyIds) {
        return this.repetition(companyDao.queryCompany("from Company c where c.code='" + code + "'"), companyIds);
    }

    private boolean repetition(List<?> companyList, String... companyIds) {

        int companyListSize = companyList.size();

        for (int i = 0; i < companyListSize; i++) {

            Company company = (Company) companyList.get(i);
            boolean b = false;

            if (!company.getpId().equals("0") || company.getCheck() == 2) {
                b = true;
            } else {
                for (String companyId : companyIds) {
                    if (company.getId().equals(companyId)) {
                        b = true;
                        break;
                    }
                }
            }

            if (b) {
                companyList.remove(i);
                i--;
                companyListSize = companyList.size();
            }

        }

        return companyList.isEmpty();

    }

}
