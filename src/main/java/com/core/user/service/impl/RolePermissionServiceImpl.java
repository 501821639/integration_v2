package com.core.user.service.impl;

import com.core.user.bean.Permission;
import com.core.user.bean.Role;
import com.core.user.bean.RolePermission;
import com.core.user.dao.PermissionDao;
import com.core.user.dao.RoleDao;
import com.core.user.dao.RolePermissionDao;
import com.core.user.service.RolePermissionService;
import com.utils.json.FormatJson;
import com.utils.json.FormatObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by GSN on 2017/4/28.
 * 暂无描述
 */

@Service
public class RolePermissionServiceImpl implements RolePermissionService {

    @Resource
    private RoleDao roleDao;

    @Resource
    private PermissionDao permissionDao;

    @Resource
    private RolePermissionDao rolePermissionDao;

    @Override
    public String requiredAddRolePermission(String roleId, String arrayRoleId) {

        //验证角色是否存在
        Role role = roleDao.getRole(roleId);

        if (role == null) {
            return "角色ID" + roleId + "不存在";
        }

        List<Map<String, String>> list = new FormatObject().stringConversion(arrayRoleId, new String[]{"permissionId"});

        List<RolePermission> rolePermissions = new ArrayList<>();//要保存的数据

        for (Map<String, String> map : list) {

            String permissionId = map.get("permissionId");

            if (permissionId != null) {

                Permission permission = permissionDao.getPermission(permissionId);

                if (permission == null) {
                    return "权限ID" + permissionId + "不存在";
                } else {

                    RolePermission rolePermission = new RolePermission();
                    rolePermission.setRoleId(roleId);
                    rolePermission.setPermissionId(permissionId);

                    rolePermissions.add(rolePermission);
                }

            }

        }

        //删除数据库已分配的权限
        String hql = "from RolePermission rp where rp.roleId='" + roleId + "'";
        rolePermissionDao.deleteRolePermissions(rolePermissionDao.queryRolePermission(hql));

        //保存分配的权限
        for (RolePermission rolePermission : rolePermissions) {
            rolePermissionDao.addRolePermission(rolePermission);
        }

        return "success";
    }

    @Override
    public String queryRolePermission(String roleId) {

        String hql = "from RolePermission rp where rp.roleId='" + roleId + "'";

        List<?> rolePermissions = rolePermissionDao.queryRolePermission(hql);

        List<Permission> permissions = new ArrayList<>();

        for (Object o : rolePermissions) {
            RolePermission rolePermission = (RolePermission) o;
            Permission permission = permissionDao.getPermission(rolePermission.getPermissionId());
            if (permission != null) {
                permissions.add(permission);
            }
        }

        return new FormatJson().listConversion(permissions);

    }
}
