package com.core.company.dao;

import com.core.company.bean.Company;

import java.util.List;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */
public interface CompanyDao {

    String addCompany(Company company);

    void updateCompany(Company company);

    Company getCompany(String id);

    List<?> queryCompany(String hql);

}
