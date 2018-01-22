package com.core.company.service.impl;

import com.core.company.assist.AssistCompanyNature;
import com.core.company.bean.CompanyNature;
import com.core.company.dao.CompanyDao;
import com.core.company.dao.CompanyNatureDao;
import com.core.company.service.CompanyNatureService;
import com.utils.json.FormatJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Service
public class CompanyNatureServiceImpl implements CompanyNatureService {

    @Resource
    private CompanyNatureDao companyNatureDao;

    @Resource
    private CompanyDao companyDao;

    @Override
    public String requiredAddCompanyNature(CompanyNature companyNature) {

        String result = new AssistCompanyNature().verificationCompanyNature(companyNature);

        if (result != null) {
            return result;
        }

        companyNatureDao.addCompanyNature(companyNature);

        return "success";
    }

    @Override
    public String requiredDeleteCompanyNature(String id) {

        CompanyNature companyNature = companyNatureDao.getCompanyNature(id);

        if (companyNature == null) {
            return "CompanyNature null";
        }

        //单位是否使用
        String hql = "from Company c where c.natureId='" + companyNature.getId() + "'";
        if (!companyDao.queryCompany(hql).isEmpty()) {
            return "单位正使用无法删除";
        }

        companyNatureDao.deleteCompanyNature(companyNature);

        return "success";
    }

    @Override
    public String requiredUpdateCompanyNature(CompanyNature companyNature) {

        String result = new AssistCompanyNature().verificationCompanyNature(companyNature);

        if (result != null) {
            return result;
        }

        CompanyNature companyNatureDb = companyNatureDao.getCompanyNature(companyNature.getId());

        if (companyNatureDb == null) {
            return "CompanyNature null";
        }

        companyNatureDb.setName(companyNature.getName());

        companyNatureDao.updateCompanyNature(companyNatureDb);

        return "success";
    }

    @Override
    public String queryCompanyNature() {
        return new FormatJson().listConversion(companyNatureDao.queryCompanyNature("from CompanyNature"));
    }
}
