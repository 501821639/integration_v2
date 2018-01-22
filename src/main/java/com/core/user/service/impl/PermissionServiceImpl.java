package com.core.user.service.impl;

import com.core.user.assist.AssistPermission;
import com.core.user.bean.Permission;
import com.core.user.dao.PermissionDao;
import com.core.user.dao.RolePermissionDao;
import com.core.user.service.PermissionService;
import com.utils.json.FormatJson;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by GSN on 2017/4/28.
 * 暂无描述
 */

@Service
public class PermissionServiceImpl implements PermissionService {

    @Resource
    private PermissionDao permissionDao;

    @Resource
    private RolePermissionDao rolePermissionDao;

    @Override
    public String requiredAddPermission(Permission permission) {

        String result = new AssistPermission().verificationPermission(permission);

        if (result != null) {
            return result;
        }

        String hql = "from Permission p where p.name='" + permission.getName() + "'";

        if (!permissionDao.queryPermission(hql).isEmpty()) {
            return "权限名称已重复";
        }


        if (!permission.getpId().equals("0")) {
            if (permissionDao.getPermission(permission.getpId()) == null) {
                return "数据库中无上级权限ID";
            }
        }

        permissionDao.addPermission(permission);

        return "success";
    }

    @Override
    public String requiredDeletePermission(String permissionId) {

        //数据库中是否拥有这个权限数据
        Permission permission = permissionDao.getPermission(permissionId);

        if (permission == null) {
            return "要删除的权限不存在";
        }

        String hql = "from Permission p where p.pId='" + permission.getId() + "'";

        List<?> permissions = permissionDao.queryPermission(hql);

        if (!permissions.isEmpty()) {
            return "请删除下级权限才能删除该权限";
        }

        hql = "from RolePermission rp where rp.permissionId='" + permissionId + "'";

        //查询所有角色正在使用的该权限
        List<?> rolePermissions = rolePermissionDao.queryRolePermission(hql);

        //删除所有角色正在使用的该权限
        rolePermissionDao.deleteRolePermissions(rolePermissions);

        //删除权限
        permissionDao.deletePermission(permission);


        return "success";
    }

    @Override
    public String requiredUpdatePermission(Permission permission) {

        Permission dbPermission = permissionDao.getPermission(permission.getId());

        if (dbPermission == null) {
            return "数据库没有要修改的权限ID";
        }

        String result = new AssistPermission().verificationPermission(permission);

        if (result != null) {
            return result;
        }

        String hql = "from Permission p where p.name='" + permission.getName() + "'";
        List<?> permissions = permissionDao.queryPermission(hql);

        if (!permissions.isEmpty()) {
            if (!((Permission) permissions.get(0)).getId().equals(permission.getId())) {
                return "权限名称已重复";
            }
        }

        if (!permission.getpId().equals("0")) {
            if (permissionDao.getPermission(permission.getpId()) == null) {
                return "数据库中无上级权限ID";
            }
        }

        dbPermission.setName(permission.getName());
        dbPermission.setpId(permission.getpId());
        dbPermission.setOrder(permission.getOrder());
        dbPermission.setType(permission.getType());
        dbPermission.setControl(permission.getControl());
        dbPermission.setIcon(permission.getIcon());

        permissionDao.updatePermission(dbPermission);

        return "success";
    }

    @Override
    public String findPermission() {

        List<?> list = permissionDao.queryPermission("from Permission");

        List<Permission> permissions = new ArrayList<>();

        for (Object o : list) {
            Permission permission = (Permission) o;
            permissions.add(permission);
        }

        //权限排序
        permissions.sort(new Comparator<Permission>() {
            @Override
            public int compare(Permission p1, Permission p2) {
                return p1.getOrder() - p2.getOrder();
            }
        });

        return new FormatJson().listConversion(permissions);
    }
}
