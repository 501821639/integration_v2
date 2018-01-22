package com.core.user.controller;

import com.core.user.service.UserRoleService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GSN on 2017/4/26.
 * 用户分配角色相关操作
 */
@Controller
@RequestMapping("/userrole")
public class UserRoleController {

    @Resource
    private UserRoleService userRoleService;

    //用户分配角色
    @RequestMapping("/save.shtml")
    @RequiresPermissions("userrole:save")
    public void addUserRole(HttpServletResponse response, String userId, String arrayRoleId) throws IOException {
        response.getWriter().print(userRoleService.requiredAddUserRole(userId, arrayRoleId));
    }

    //根据用户查询所拥有的角色
    @RequestMapping("/findrole.shtml")
    @RequiresPermissions("userrole:findrole")
    public void queryUserRole(HttpServletResponse response, String userId) throws IOException {
        response.getWriter().print(userRoleService.queryUserRole(userId));
    }


}
