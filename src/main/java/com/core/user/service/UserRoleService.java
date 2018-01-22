package com.core.user.service;


/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
public interface UserRoleService {

    /**
     * 给用户添加角色
     * 先清除用户所有角色再添加新的角色
     *
     * @param userId      用户id
     * @param arrayRoleId 角色数组
     * @return 信息
     */
    String requiredAddUserRole(String userId, String arrayRoleId);

    /**
     * 根据用户id查询所拥有的角色
     *
     * @param userId 用户id
     * @return 角色json
     */
    String queryUserRole(String userId);

}
