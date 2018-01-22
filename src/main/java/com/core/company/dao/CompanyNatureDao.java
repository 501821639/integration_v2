package com.core.company.dao;

import com.core.company.bean.CompanyNature;

import java.util.List;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */
public interface CompanyNatureDao {

    void addCompanyNature(CompanyNature companyNature);

    void deleteCompanyNature(CompanyNature companyNature);

    void deleteCompanyNatures(List<CompanyNature> list);

    void updateCompanyNature(CompanyNature companyNature);

    CompanyNature getCompanyNature(String id);

    List<?> queryCompanyNature(String hql);

}
