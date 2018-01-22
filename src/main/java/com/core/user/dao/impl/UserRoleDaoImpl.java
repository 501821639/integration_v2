package com.core.user.dao.impl;

import com.core.user.bean.UserRole;
import com.core.user.dao.UserRoleDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
@Repository
public class UserRoleDaoImpl implements UserRoleDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addUserRole(UserRole userRole) {
        hibernateTemplate.save(userRole);
    }

    @Override
    public void deleteUserRole(UserRole userRole) {
        hibernateTemplate.delete(userRole);
    }

    @Override
    public void deleteUserRoles(List<?> list) {
        hibernateTemplate.deleteAll(list);
    }

    @Override
    public void updateUserRole(UserRole userRole) {
        hibernateTemplate.update(userRole);
    }

    @Override
    public UserRole getUserRole(String id) {
        return hibernateTemplate.get(UserRole.class, id);
    }

    @Override
    public List<?> queryUserRole(String hql) {
        return hibernateTemplate.find(hql);
    }
}
