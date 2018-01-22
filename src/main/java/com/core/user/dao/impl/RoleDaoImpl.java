package com.core.user.dao.impl;

import com.core.user.bean.Role;
import com.core.user.dao.RoleDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
@Repository
public class RoleDaoImpl implements RoleDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addRole(Role role) {
        hibernateTemplate.save(role);
    }

    @Override
    public void deleteRole(Role role) {
        hibernateTemplate.delete(role);
    }

    @Override
    public void deleteRoles(List<?> list) {
        hibernateTemplate.deleteAll(list);
    }

    @Override
    public void updateRole(Role role) {
        hibernateTemplate.update(role);
    }

    @Override
    public Role getRole(String id) {
        return hibernateTemplate.get(Role.class, id);
    }

    @Override
    public List<?> queryRole(String hql) {
        return hibernateTemplate.find(hql);
    }
}
