package com.core.company.dao.impl;

import com.core.company.bean.CompanyNature;
import com.core.company.dao.CompanyNatureDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Repository
public class CompanyNatureDaoImpl implements CompanyNatureDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addCompanyNature(CompanyNature companyNature) {
        hibernateTemplate.save(companyNature);
    }

    @Override
    public void deleteCompanyNature(CompanyNature companyNature) {
        hibernateTemplate.delete(companyNature);
    }

    @Override
    public void deleteCompanyNatures(List<CompanyNature> list) {
        hibernateTemplate.deleteAll(list);
    }

    @Override
    public void updateCompanyNature(CompanyNature companyNature) {
        hibernateTemplate.update(companyNature);
    }

    @Override
    public CompanyNature getCompanyNature(String id) {
        return hibernateTemplate.get(CompanyNature.class, id);
    }

    @Override
    public List<?> queryCompanyNature(String hql) {
        return hibernateTemplate.find(hql);
    }
}
