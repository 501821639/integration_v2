package com.core.user.service.impl;

import com.core.user.bean.Role;
import com.core.user.bean.User;
import com.core.user.bean.UserRole;
import com.core.user.dao.RoleDao;
import com.core.user.dao.UserDao;
import com.core.user.dao.UserRoleDao;
import com.core.user.service.UserRoleService;
import com.utils.json.FormatJson;
import com.utils.json.FormatObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
@Service
public class UserRoleServiceImpl implements UserRoleService {

    @Resource
    private UserDao userDao;

    @Resource
    private UserRoleDao userRoleDao;

    @Resource
    private RoleDao roleDao;

    @Override
    public String requiredAddUserRole(String userId, String arrayRoleId) {

        //验证用户是否存在
        User user = userDao.getUser(userId);

        if (user == null) {
            return "用户ID" + userId + "不存在";
        }

        List<Map<String, String>> list = new FormatObject().stringConversion(arrayRoleId, new String[]{"roleId"});

        List<UserRole> userRoles = new ArrayList<>();//要保存的数据

        for (Map<String, String> map : list) {

            String roleId = map.get("roleId");

            if (roleId != null) {

                Role role = roleDao.getRole(roleId);

                if (role == null) {
                    return "角色ID" + roleId + "不存在";
                } else {

                    UserRole userRole = new UserRole();
                    userRole.setUserId(userId);
                    userRole.setRoleId(roleId);

                    userRoles.add(userRole);
                }
            }

        }

        //删除数据库中已经分配的角色
        userRoleDao.deleteUserRoles(userRoleDao.queryUserRole("from UserRole ur where ur.userId='" + userId + "'"));

        //保存分配的角色
        for (UserRole userRole : userRoles) {
            userRoleDao.addUserRole(userRole);
        }


        return "success";
    }

    @Override
    public String queryUserRole(String userId) {

        String hql = "from UserRole ur where ur.userId='" + userId + "'";

        List<?> userRoles = userRoleDao.queryUserRole(hql);

        List<Role> roles = new ArrayList<>();

        for (Object o : userRoles) {
            UserRole userRole = (UserRole) o;
            Role role = roleDao.getRole(userRole.getRoleId());
            if (role != null) {
                roles.add(role);
            }
        }

        return new FormatJson().listConversion(roles);
    }
}
