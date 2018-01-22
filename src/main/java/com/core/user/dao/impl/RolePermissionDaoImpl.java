package com.core.user.dao.impl;

import com.core.user.bean.RolePermission;
import com.core.user.dao.RolePermissionDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
@Repository
public class RolePermissionDaoImpl implements RolePermissionDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addRolePermission(RolePermission rolePermission) {
        hibernateTemplate.save(rolePermission);
    }

    @Override
    public void deleteRolePermission(RolePermission rolePermission) {
        hibernateTemplate.delete(rolePermission);
    }

    @Override
    public void deleteRolePermissions(List<?> list) {
        hibernateTemplate.deleteAll(list);
    }

    @Override
    public void updateRolePermission(RolePermission rolePermission) {
        hibernateTemplate.update(rolePermission);
    }

    @Override
    public RolePermission getRolePermission(String id) {
        return hibernateTemplate.get(RolePermission.class, id);
    }

    @Override
    public List<?> queryRolePermission(String hql) {
        return hibernateTemplate.find(hql);
    }
}
