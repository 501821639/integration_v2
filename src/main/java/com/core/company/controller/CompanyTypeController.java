package com.core.company.controller;

import com.core.company.bean.CompanyType;
import com.core.company.service.CompanyTypeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Controller
@RequestMapping("/companyType")
public class CompanyTypeController {

    @Resource
    private CompanyTypeService companyTypeService;

    @RequestMapping("/save.shtml")
    @RequiresPermissions("companytype:save")
    public void CompanyTypeSave(HttpServletResponse response, CompanyType companyType) throws IOException {
        response.getWriter().print(companyTypeService.requiredAddCompanyType(companyType));
    }

    @RequestMapping("/delete.shtml")
    @RequiresPermissions("companytype:delete")
    public void CompanyTypeDelete(HttpServletResponse response, String id) throws IOException {
        response.getWriter().print(companyTypeService.requiredDeleteCompanyType(id));
    }

    @RequestMapping("/update.shtml")
    @RequiresPermissions("companytype:update")
    public void CompanyTypeUpdate(HttpServletResponse response, CompanyType companyType) throws IOException {
        response.getWriter().print(companyTypeService.requiredUpdateCompanyType(companyType));
    }

    @RequestMapping("/find.shtml")
    public void CompanyTypeFind(HttpServletResponse response) throws IOException {
        response.getWriter().print(companyTypeService.queryCompanyType());
    }

}
