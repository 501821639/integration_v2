package com.core.company.controller;

import com.core.company.bean.CompanyNature;
import com.core.company.service.CompanyNatureService;
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
@RequestMapping("/companyNature")
public class CompanyNatureController {

    @Resource
    private CompanyNatureService companyNatureService;

    @RequestMapping("/save.shtml")
    @RequiresPermissions("companynature:save")
    public void CompanyNatureSave(HttpServletResponse response, CompanyNature companyNature) throws IOException {
        response.getWriter().print(companyNatureService.requiredAddCompanyNature(companyNature));
    }

    @RequestMapping("/delete.shtml")
    @RequiresPermissions("companynature:delete")
    public void CompanyNatureDelete(HttpServletResponse response, String id) throws IOException {
        response.getWriter().print(companyNatureService.requiredDeleteCompanyNature(id));
    }

    @RequestMapping("/update.shtml")
    @RequiresPermissions("companynature:update")
    public void CompanyNatureUpdate(HttpServletResponse response, CompanyNature companyNature) throws IOException {
        response.getWriter().print(companyNatureService.requiredUpdateCompanyNature(companyNature));
    }

    @RequestMapping("/find.shtml")
    public void CompanyNatureFind(HttpServletResponse response) throws IOException {
        response.getWriter().print(companyNatureService.queryCompanyNature());
    }

}
