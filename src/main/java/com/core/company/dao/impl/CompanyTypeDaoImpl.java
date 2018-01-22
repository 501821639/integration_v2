package com.core.company.dao.impl;

import com.core.company.bean.CompanyType;
import com.core.company.dao.CompanyTypeDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Repository
public class CompanyTypeDaoImpl implements CompanyTypeDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addCompanyType(CompanyType companyType) {
        hibernateTemplate.save(companyType);
    }

    @Override
    public void deleteCompanyType(CompanyType companyType) {
        hibernateTemplate.delete(companyType);
    }

    @Override
    public void deleteCompanyTypes(List<CompanyType> list) {
        hibernateTemplate.deleteAll(list);
    }

    @Override
    public void updateCompanyType(CompanyType companyType) {
        hibernateTemplate.update(companyType);
    }

    @Override
    public CompanyType getCompanyType(String id) {
        return hibernateTemplate.get(CompanyType.class, id);
    }

    @Override
    public List<?> queryCompanyType(String hql) {
        return hibernateTemplate.find(hql);
    }
}
