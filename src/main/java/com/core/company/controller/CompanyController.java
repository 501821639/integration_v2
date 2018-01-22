package com.core.company.controller;

import com.core.company.bean.Company;
import com.core.company.dto.CompanyUpdateMessage;
import com.core.company.service.CompanyService;
import com.core.user.dto.UserAllMessage;
import com.core.user.dto.UserAuthResult;
import com.utils.json.FormatJson;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Controller
@RequestMapping("/company")
public class CompanyController {

    @Resource
    private CompanyService companyService;

    //生成单位注册验证码
    @RequestMapping("/auth.shtml")
    public void createUserAuth(HttpServletResponse response, HttpSession session, String username) throws IOException {
        UserAuthResult userAuthResult = companyService.createCompanyAuth(username, (String) session.getAttribute(username));
        if (userAuthResult.getCode().equals("success")) {
            session.setMaxInactiveInterval(600000);
            session.setAttribute(username, userAuthResult.getAuth() + "," + userAuthResult.getDate().getTime());
            userAuthResult.setAuth(null);
        }
        response.getWriter().print(new FormatJson().objectConversion(userAuthResult));
    }

    @RequestMapping("/register.shtml")
    public void registerCompany(HttpServletResponse response, HttpSession session, Company company, String username, String userAuth) throws IOException {
        response.getWriter().print(companyService.requiredRegisterCompany(company, username, userAuth, (String) session.getAttribute(username)));
    }

    @RequestMapping("/update.shtml")
    @RequiresPermissions("company:update")
    public void updateCompany(HttpServletResponse response, CompanyUpdateMessage companyUpdateMessage) throws IOException {
        UserAllMessage user = (UserAllMessage) SecurityUtils.getSubject().getPrincipal();
        response.getWriter().print(companyService.requiredUpdateComoany(companyUpdateMessage, user));
    }

    @RequestMapping("/findSuper.shtml")
    public void findSuperCompany(HttpServletResponse response, String name) throws IOException {
        response.getWriter().print(companyService.querySuperCompany(name));
    }

    @RequestMapping("findUC.shtml")
    @RequiresPermissions("company:update")
    public void findUserCompany(HttpServletResponse response) throws IOException {
        UserAllMessage user = (UserAllMessage) SecurityUtils.getSubject().getPrincipal();
        response.getWriter().print(companyService.queryUserCompany(user));
    }

}
