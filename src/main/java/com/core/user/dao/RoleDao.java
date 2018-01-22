package com.core.user.dao;

import com.core.user.bean.Role;

import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
public interface RoleDao {

    void addRole(Role role);

    void deleteRole(Role role);

    void deleteRoles(List<?> list);

    void updateRole(Role role);

    Role getRole(String id);

    List<?> queryRole(String hql);

}
