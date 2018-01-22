package com.core.company.service.impl;

import com.core.common.dao.RegionDao;
import com.core.company.assist.AssistCompany;
import com.core.company.bean.Company;
import com.core.company.dao.CompanyDao;
import com.core.company.dao.CompanyNatureDao;
import com.core.company.dao.CompanyTypeDao;
import com.core.company.dto.CompanyBriefnessMessage;
import com.core.company.dto.CompanySuperMessage;
import com.core.company.dto.CompanyUpdateMessage;
import com.core.company.service.CompanyService;
import com.core.user.bean.Role;
import com.core.user.bean.User;
import com.core.user.bean.UserCompany;
import com.core.user.bean.UserRole;
import com.core.user.dao.RoleDao;
import com.core.user.dao.UserCompanyDao;
import com.core.user.dao.UserDao;
import com.core.user.dao.UserRoleDao;
import com.core.user.dto.UserAllMessage;
import com.core.user.dto.UserAuthResult;
import com.utils.RandomGenerate;
import com.utils.date.FormatType;
import com.utils.json.FormatJson;
import com.utils.regular.UserReg;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Service
public class CompanyServiceImpl implements CompanyService {

    @Resource
    private CompanyDao companyDao;

    @Resource
    private CompanyTypeDao companyTypeDao;

    @Resource
    private CompanyNatureDao companyNatureDao;

    @Resource
    private RegionDao regionDao;

    @Resource
    private UserDao userDao;

    @Resource
    private UserCompanyDao userCompanyDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private RoleDao roleDao;

    @Override
    public UserAuthResult createCompanyAuth(String key, String val) {

        UserAuthResult userAuthResult = new UserAuthResult();

        //账号是否合法
        if (key == null || !new UserReg().phone(key)) {
            userAuthResult.setCode("error");
            userAuthResult.setResult("用户账号为手机号码,请输入正确的手机号码");
            return userAuthResult;
        }

        // 用户是否注册
        List<?> list = userDao.queryUser("from User u where u.username='" + key + "'");
        if (list.isEmpty()) {
            userAuthResult.setCode("error");
            userAuthResult.setResult(key + " 未注册");
            return userAuthResult;
        }

        Date d = new Date();

        //上次验证码生成未失效
        if (val != null) {

            //上次验证时间
            long time = Long.parseLong(val.split(",")[1]) + 60000;

            if (d.getTime() < time) {
                userAuthResult.setCode("error");
                userAuthResult.setResult("单位注册：验证码请求间隔限制1分钟");
                return userAuthResult;
            }

        }

        userAuthResult.setCode("success");
        userAuthResult.setAuth(new RandomGenerate().generateN(6));
        userAuthResult.setDate(d);
        userAuthResult.setResult("验证码已发送至" + key + ",10分钟有效");

        System.out.println(key + "：" + userAuthResult.getAuth());

        return userAuthResult;
    }

    @Override
    public String requiredRegisterCompany(Company company, String username, String userAuth, String sessionAuth) {

        //验证单位信息是否符合规则
        String result = new AssistCompany(companyDao, companyTypeDao, companyNatureDao, regionDao).saveCompany(company);

        if (result != null) {
            return result;
        }

        //验证用户是否为手机号码
        if (username == null || !new UserReg().phone(username)) {
            return "用户账号为手机号码,请输入正确的手机号码";
        }

        //验证用户是否存在
        List<?> listUser = userDao.queryUser("from User u where u.username='" + username + "'");
        if (listUser.isEmpty()) {
            return username + "未注册";
        }

        User user = (User) listUser.get(0);

        //验证用户账号是否锁定
        if (user.getLocked() == 0) {
            return username + "账号已被系统锁定(原因:" + user.getLockedRemark() + ")";
        }

        //验证用户是否已经拥有上级单位
        //用户可以管理多个上级单位

        //验证单位管理员角色是否存在
        Role role = roleDao.getRole("2c90869b5bb20e95015bb23343b40004");
        if (role == null) {
            return "系统错误，无法为该账号添加单位管理员角色";
        }

        //验证码
        if (sessionAuth == null) {
            return "请先获取验证码";
        }
        if (userAuth == null || userAuth.trim().equals("")) {
            return "请输入验证码";
        }
        if (!userAuth.equals(sessionAuth.split(",")[0])) {
            return "验证码错误";
        }

        //设置单位等待审核状态
        company.setCheck(0);

        //设置单位注册时间
        company.setRegTime(new Date());

        //保存单位信息
        String companyId = companyDao.addCompany(company);

        //将用户与单位关联
        userCompanyDao.addUserCompany(new UserCompany(user.getId(), companyId));

        //删除当前用户所有角色
        roleDao.deleteRoles(roleDao.queryRole("from UserRole ur where ur.userId='" + user.getId() + "'"));

        //赋予单位管理员角色
        userRoleDao.addUserRole(new UserRole(user.getId(), role.getId()));

        return "success";
    }

    @Override
    public String requiredUpdateComoany(CompanyUpdateMessage companyUpdateMessage, UserAllMessage userAllMessage) {

        if (companyUpdateMessage == null) {
            return "Company null";
        }

        if (companyUpdateMessage.getId() == null) {
            return "Company ID error";
        }

        //验证单位id是否存在
        Company companyDB = companyDao.getCompany(companyUpdateMessage.getId());

        if (companyDB == null) {
            return "单位不存在(ID错误)";
        }

        //验证单位信息是否符合规则
        String result = new AssistCompany(companyDao, companyTypeDao, companyNatureDao, regionDao).updateCompany(companyUpdateMessage, userAllMessage);

        if (result != null) {
            return result;
        }

        companyDB.setpId(companyUpdateMessage.getpId());
        companyDB.setName(companyUpdateMessage.getName());
        companyDB.setTypeId(companyUpdateMessage.getTypeId());
        companyDB.setNatureId(companyUpdateMessage.getNatureId());
        companyDB.setRegionId(companyUpdateMessage.getRegionId());
        companyDB.setCode(companyUpdateMessage.getCode());
        companyDB.setPhone(companyUpdateMessage.getPhone());
        companyDB.setCheck(0);
        companyDB.setCheckRemark(null);

        companyDao.updateCompany(companyDB);

        return "success";
    }

    @Override
    public String querySuperCompany(String name) {

        if (name == null) {
            return new FormatJson().listConversion(new ArrayList<>());
        }

        name = name.replace(" ", "");

        if (name.equals("")) {
            return new FormatJson().listConversion(new ArrayList<>());
        }

        List<CompanySuperMessage> companyList = new ArrayList<>();
        List<?> list = companyDao.queryCompany("from Company c where c.name like '%" + name + "%'");

        for (Object o : list) {

            Company company = (Company) o;

            CompanySuperMessage companySuperMessage = new CompanySuperMessage();

            companySuperMessage.setId(company.getId());
            companySuperMessage.setName(company.getName());
            companySuperMessage.setCheck(company.getCheck());
            companySuperMessage.setCompanySuperMessage(new AssistCompany(companyDao).getSuperCompany(company));

            companyList.add(companySuperMessage);
        }

        return new FormatJson().listConversion(companyList);
    }

    @Override
    public String queryUserCompany(UserAllMessage userAllMessage) {

        List<CompanyUpdateMessage> companyUpdateMessageList = new ArrayList<>();

        for (CompanyBriefnessMessage cbm : userAllMessage.getCompanyBriefnessMessageList()) {

            Company company = companyDao.getCompany(cbm.getId());

            if (company != null) {

                CompanyUpdateMessage companyUpdateMessage = new CompanyUpdateMessage();

                companyUpdateMessage.setId(company.getId());
                companyUpdateMessage.setpId(company.getpId());
                companyUpdateMessage.setName(company.getName());
                companyUpdateMessage.setTypeId(company.getTypeId());
                companyUpdateMessage.setNatureId(company.getNatureId());
                companyUpdateMessage.setRegionId(company.getRegionId());
                companyUpdateMessage.setCode(company.getCode());
                companyUpdateMessage.setPhone(company.getPhone());
                companyUpdateMessage.setRegTime(company.getRegTime());
                companyUpdateMessage.setCheck(company.getCheck());
                companyUpdateMessage.setCheckRemark(company.getCheckRemark());

                if (!company.getpId().equals("0")) {

                    Company superCompany = companyDao.getCompany(company.getpId());

                    if (superCompany != null) {
                        companyUpdateMessage.setSuperCompanyName(superCompany.getName());
                    }

                }

                companyUpdateMessageList.add(companyUpdateMessage);

            }

        }

        return new FormatJson(new FormatType().getYmdhms()).listConversion(companyUpdateMessageList);
    }
}
