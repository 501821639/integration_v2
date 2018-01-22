package com.core.user.controller;

import com.core.user.service.RolePermissionService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GSN on 2017/4/28.
 * 暂无描述
 */

@Controller
@RequestMapping("/rolepermission")
public class RolePermissionController {

    @Resource
    private RolePermissionService rolePermissionService;

    //角色分配权限
    @RequestMapping("/save.shtml")
    @RequiresPermissions("rolepermission:save")
    public void addRolePermission(HttpServletResponse response, String roleId, String arrayRoleId) throws IOException {
        response.getWriter().print(rolePermissionService.requiredAddRolePermission(roleId, arrayRoleId));
    }

    //根据角色查询所拥有的权限
    @RequestMapping("/findrole.shtml")
    @RequiresPermissions("rolepermission:findpermission")
    public void queryRolePermission(HttpServletResponse response, String roleId) throws IOException {
        response.getWriter().print(rolePermissionService.queryRolePermission(roleId));
    }

}
