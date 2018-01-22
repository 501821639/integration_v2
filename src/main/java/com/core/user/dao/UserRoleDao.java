package com.core.user.dao;

import com.core.user.bean.UserRole;

import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
public interface UserRoleDao {

    void addUserRole(UserRole userRole);

    void deleteUserRole(UserRole userRole);

    void deleteUserRoles(List<?> list);

    void updateUserRole(UserRole userRole);

    UserRole getUserRole(String id);

    List<?> queryUserRole(String hql);

}
