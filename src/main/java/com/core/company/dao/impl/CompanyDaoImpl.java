package com.core.company.dao.impl;

import com.core.company.bean.Company;
import com.core.company.dao.CompanyDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Repository
public class CompanyDaoImpl implements CompanyDao{

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public String addCompany(Company company) {
        return (String) hibernateTemplate.save(company);
    }

    @Override
    public void updateCompany(Company company) {
        hibernateTemplate.update(company);
    }

    @Override
    public Company getCompany(String id) {
        return hibernateTemplate.get(Company.class,id);
    }

    @Override
    public List<?> queryCompany(String hql) {
        return hibernateTemplate.find(hql);
    }
}
