package com.core.user.service;

import com.core.user.bean.Role;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
public interface RoleService {

    /**
     * 添加角色
     *
     * @param role 角色实体类
     * @return 添加结果
     */
    String requiredAddRole(Role role);

    /**
     * 删除角色
     * 敏感操作 后期使用中应关闭删除权限
     *
     * @param id 角色id
     * @return 删除结果
     */
    String requiredDeleteRole(String id);

    /**
     * 修改角色
     *
     * @param role 角色实体类
     * @return 修改结果
     */
    String requiredUpdateRole(Role role);

    /**
     * 查询所有角色
     *
     * @return json格式角色列表
     */
    String findRole();

}
