package com.core.user.dao;

import com.core.user.bean.RolePermission;

import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
public interface RolePermissionDao {

    void addRolePermission(RolePermission rolePermission);

    void deleteRolePermission(RolePermission rolePermission);

    void deleteRolePermissions(List<?> list);

    void updateRolePermission(RolePermission rolePermission);

    RolePermission getRolePermission(String id);

    List<?> queryRolePermission(String hql);

}
