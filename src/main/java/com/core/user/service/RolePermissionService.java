package com.core.user.service;

/**
 * Created by GSN on 2017/4/28.
 * 暂无描述
 */
public interface RolePermissionService {

    /**
     * 为角色分配权限
     *
     * @param roleId      角色id
     * @param arrayRoleId 权限id字符串
     * @return 分配结果
     */
    String requiredAddRolePermission(String roleId, String arrayRoleId);

    /**
     * 根据角色id查询所拥有的权限
     *
     * @param roleId 角色id
     * @return 权限json
     */
    String queryRolePermission(String roleId);

}
