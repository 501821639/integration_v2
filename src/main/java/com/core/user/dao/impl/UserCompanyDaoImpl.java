package com.core.user.dao.impl;

import com.core.user.bean.UserCompany;
import com.core.user.dao.UserCompanyDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/5/11.
 * 暂无描述
 */

@Repository
public class UserCompanyDaoImpl implements UserCompanyDao {

    @Resource
    private HibernateTemplate hibernateTemplate;


    @Override
    public void addUserCompany(UserCompany userCompany) {
        hibernateTemplate.save(userCompany);
    }

    @Override
    public void deleteUserCompany(UserCompany userCompany) {
        hibernateTemplate.delete(userCompany);
    }

    @Override
    public void deleteUserCompanys(List<UserCompany> list) {
        hibernateTemplate.deleteAll(list);
    }

    @Override
    public void updateUserCompany(UserCompany userCompany) {
        hibernateTemplate.update(userCompany);
    }

    @Override
    public UserCompany getUserCompany(String id) {
        return hibernateTemplate.get(UserCompany.class, id);
    }

    @Override
    public List<?> queryUserCompany(String hql) {
        return hibernateTemplate.find(hql);
    }
}
