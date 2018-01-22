package com.core.company.dao.impl;

import com.core.company.bean.CompanyBusinessLicense;
import com.core.company.dao.CompanyBusinessLicenseDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Repository
public class CompanyBusinessLicenseDaoImpl implements CompanyBusinessLicenseDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addCompanyBusinessLicense(CompanyBusinessLicense companyBusinessLicense) {
        hibernateTemplate.save(companyBusinessLicense);
    }

    @Override
    public void deleteCompanyBusinessLicense(CompanyBusinessLicense companyBusinessLicense) {
        hibernateTemplate.delete(companyBusinessLicense);
    }

    @Override
    public void deleteCompanyBusinessLicenses(List<CompanyBusinessLicense> list) {
        hibernateTemplate.deleteAll(list);
    }

    @Override
    public void updateCompanyBusinessLicense(CompanyBusinessLicense companyBusinessLicense) {
        hibernateTemplate.update(companyBusinessLicense);
    }

    @Override
    public CompanyBusinessLicense getCompanyBusinessLicense(String id) {
        return hibernateTemplate.get(CompanyBusinessLicense.class, id);
    }

    @Override
    public List<?> queryCompanyBusinessLicense(String hql) {
        return hibernateTemplate.find(hql);
    }
}
