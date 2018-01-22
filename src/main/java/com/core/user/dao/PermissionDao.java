package com.core.user.dao;

import com.core.user.bean.Permission;

import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
public interface PermissionDao {

    void addPermission(Permission permission);

    void deletePermission(Permission permission);

    void deletePermissions(List<?> list);

    void updatePermission(Permission permission);

    Permission getPermission(String id);

    List<?> queryPermission(String hql);

}
