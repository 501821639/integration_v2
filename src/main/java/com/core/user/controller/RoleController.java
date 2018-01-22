package com.core.user.controller;

import com.core.user.bean.Role;
import com.core.user.service.RoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GSN on 2017/4/26.
 * 暂无描述
 */
@Controller
@RequestMapping("/role")
public class RoleController {

    @Resource
    private RoleService roleService;

    @RequestMapping("/save.shtml")
    @RequiresPermissions("role:save")
    public void saveRole(HttpServletResponse response, Role role) throws IOException {
        response.getWriter().print(roleService.requiredAddRole(role));
    }

    @RequestMapping("/delete.shtml")
    @RequiresPermissions("role:delete")
    public void deleteRole(HttpServletResponse response, String id) throws IOException {
        response.getWriter().print(roleService.requiredDeleteRole(id));
    }

    @RequestMapping("/update.shtml")
    @RequiresPermissions("role:update")
    public void updateRole(HttpServletResponse response, Role role) throws IOException {
        response.getWriter().print(roleService.requiredUpdateRole(role));
    }

    @RequestMapping("/find.shtml")
    @RequiresPermissions("role:find")
    public void findRole(HttpServletResponse response) throws IOException {
        response.getWriter().print(roleService.findRole());
    }

}
