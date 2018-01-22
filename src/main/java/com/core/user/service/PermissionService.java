package com.core.user.service;

import com.core.user.bean.Permission;

/**
 * Created by GSN on 2017/4/28.
 * 暂无描述
 */
public interface PermissionService {

    /**
     * 添加权限
     *
     * @param permission 权限实体类
     * @return 添加结果
     */
    String requiredAddPermission(Permission permission);

    /**
     * 删除权限
     *
     * @param permissionId 权限id
     * @return 删除结果
     */
    String requiredDeletePermission(String permissionId);

    /**
     * 修改权限
     *
     * @param permission 权限实体类
     * @return 修改结果
     */
    String requiredUpdatePermission(Permission permission);

    /**
     * 查询所有权限
     *
     * @return json格式权限列表
     */
    String findPermission();

}
