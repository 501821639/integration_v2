package com.core.company.service.impl;

import com.core.company.assist.AssistCompanyType;
import com.core.company.bean.CompanyType;
import com.core.company.dao.CompanyDao;
import com.core.company.dao.CompanyTypeDao;
import com.core.company.service.CompanyTypeService;
import com.utils.json.FormatJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Service
public class CompanyTypeServiceImpl implements CompanyTypeService {

    @Resource
    private CompanyTypeDao companyTypeDao;

    @Resource
    private CompanyDao companyDao;

    @Override
    public String requiredAddCompanyType(CompanyType companyType) {

        String result = new AssistCompanyType().verificationCompanyType(companyType);

        if (result != null) {
            return result;
        }

        companyTypeDao.addCompanyType(companyType);

        return "success";
    }

    @Override
    public String requiredDeleteCompanyType(String id) {

        CompanyType companyType = companyTypeDao.getCompanyType(id);

        if (companyType == null) {
            return "CompanyType null";
        }

        //单位是否使用
        String hql = "from Company c where c.typeId='" + companyType.getId() + "'";
        if (!companyDao.queryCompany(hql).isEmpty()) {
            return "单位正使用无法删除";
        }

        companyTypeDao.deleteCompanyType(companyType);

        return "success";
    }

    @Override
    public String requiredUpdateCompanyType(CompanyType companyType) {

        String result = new AssistCompanyType().verificationCompanyType(companyType);

        if (result != null) {
            return result;
        }

        CompanyType companyTypeDb = companyTypeDao.getCompanyType(companyType.getId());

        if (companyTypeDb == null) {
            return "CompanyType null";
        }

        companyTypeDb.setName(companyType.getName());

        companyTypeDao.updateCompanyType(companyTypeDb);

        return "success";
    }

    @Override
    public String queryCompanyType() {
        return new FormatJson().listConversion(companyTypeDao.queryCompanyType("from CompanyType"));
    }
}
