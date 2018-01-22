package com.core.company.dao;

import com.core.company.bean.CompanyBusinessLicense;

import java.util.List;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */
public interface CompanyBusinessLicenseDao {

    void addCompanyBusinessLicense(CompanyBusinessLicense companyBusinessLicense);

    void deleteCompanyBusinessLicense(CompanyBusinessLicense companyBusinessLicense);

    void deleteCompanyBusinessLicenses(List<CompanyBusinessLicense> list);

    void updateCompanyBusinessLicense(CompanyBusinessLicense companyBusinessLicense);

    CompanyBusinessLicense getCompanyBusinessLicense(String id);

    List<?> queryCompanyBusinessLicense(String hql);

}
