package com.core.user.controller;

import com.core.user.bean.Permission;
import com.core.user.service.PermissionService;
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
@RequestMapping("/permission")
public class PermissionController {

    @Resource
    private PermissionService permissionService;

    @RequestMapping("/save.shtml")
    @RequiresPermissions("permission:save")
    public void savePermission(HttpServletResponse response, Permission permission) throws IOException {
        response.getWriter().print(permissionService.requiredAddPermission(permission));
    }

    @RequestMapping("/delete.shtml")
    @RequiresPermissions("permission:delete")
    public void deletePermission(HttpServletResponse response, String permissionId) throws IOException {
        response.getWriter().print(permissionService.requiredDeletePermission(permissionId));
    }

    @RequestMapping("/update.shtml")
    @RequiresPermissions("permission:update")
    public void updatePermission(HttpServletResponse response, Permission permission) throws IOException {
        response.getWriter().print(permissionService.requiredUpdatePermission(permission));
    }

    @RequestMapping("/find.shtml")
    @RequiresPermissions("permission:find")
    public void savePermission(HttpServletResponse response) throws IOException {
        response.getWriter().print(permissionService.findPermission());
    }

}
