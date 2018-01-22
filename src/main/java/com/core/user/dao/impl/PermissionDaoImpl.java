package com.core.user.dao.impl;

import com.core.user.bean.Permission;
import com.core.user.dao.PermissionDao;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
@Repository
public class PermissionDaoImpl implements PermissionDao {

    @Resource
    private HibernateTemplate hibernateTemplate;

    @Override
    public void addPermission(Permission permission) {
        hibernateTemplate.save(permission);
    }

    @Override
    public void deletePermission(Permission permission) {
        hibernateTemplate.delete(permission);
    }

    @Override
    public void deletePermissions(List<?> list) {
        hibernateTemplate.deleteAll(list);
    }

    @Override
    public void updatePermission(Permission permission) {
        hibernateTemplate.update(permission);
    }

    @Override
    public Permission getPermission(String id) {
        return hibernateTemplate.get(Permission.class, id);
    }

    @Override
    public List<?> queryPermission(String hql) {
        return hibernateTemplate.find(hql);
    }
}
