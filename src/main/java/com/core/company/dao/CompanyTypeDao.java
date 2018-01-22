package com.core.company.dao;

import com.core.company.bean.CompanyType;

import java.util.List;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */
public interface CompanyTypeDao {

    void addCompanyType(CompanyType companyType);

    void deleteCompanyType(CompanyType companyType);

    void deleteCompanyTypes(List<CompanyType> list);

    void updateCompanyType(CompanyType companyType);

    CompanyType getCompanyType(String id);

    List<?> queryCompanyType(String hql);

}
