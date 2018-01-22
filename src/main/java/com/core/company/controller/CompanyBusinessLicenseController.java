package com.core.company.controller;

import com.core.company.bean.CompanyBusinessLicense;
import com.core.company.service.CompanyBusinessLicenseService;
import com.core.user.dto.UserAllMessage;
import com.utils.file.Management;
import com.utils.upload.SpringMvcUpload;
import com.utils.upload.UploadType;
import com.utils.upload.UploadUrl;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Controller
@RequestMapping("/companyBusinessLicense")
public class CompanyBusinessLicenseController {

    @Resource
    private CompanyBusinessLicenseService companyBusinessLicenseService;

    @RequestMapping("/save.shtml")
    @RequiresPermissions("company:update")
    public void saveCBL(@RequestParam("file") CommonsMultipartFile cmFile, HttpServletRequest request, HttpServletResponse response, String companyId) throws IOException {

        PrintWriter out = response.getWriter();
        UserAllMessage user = (UserAllMessage) SecurityUtils.getSubject().getPrincipal();

        String uploadFileName = cmFile.getOriginalFilename();

        if (!new UploadType().verification("image", uploadFileName)) {
            out.print("不支持的文件类型");
        } else if (cmFile.getSize() > 1024 * 1024 * 2) {
            out.print("大小限制2MB");
        } else {

            String companyPortrait = new UploadUrl().getValue("COMPANY_CBL") + user.getId() + "/";
            String path = request.getSession().getServletContext().getRealPath("/") + companyPortrait;

            try {

                String fileName = new SpringMvcUpload(cmFile).run(path, 0);

                CompanyBusinessLicense companyBusinessLicense = new CompanyBusinessLicense();
                companyBusinessLicense.setCompanyId(companyId);
                companyBusinessLicense.setQuondamName(uploadFileName);
                companyBusinessLicense.setUrl(companyPortrait + fileName);

                String result = companyBusinessLicenseService.requiredAddCompanyBusinessLicense(companyBusinessLicense, user);

                if (result.equals("success")) {
                    out.print("success");
                } else {
                    new Management().deleteFile(path + fileName);
                    out.print(result);
                }

            } catch (Exception e) {
                e.printStackTrace();
                out.print("上传失败");
            }

        }

    }

    @RequestMapping("/delete.shtml")
    @RequiresPermissions("company:update")
    public void deleteCBL(HttpServletResponse response, String id) throws IOException {
        UserAllMessage user = (UserAllMessage) SecurityUtils.getSubject().getPrincipal();
        response.getWriter().print(companyBusinessLicenseService.requiredDeleteCompanyBusinessLicense(id, user));
    }

    @RequestMapping("/find.shtml")
    @RequiresPermissions("company:update")
    public void findCBL(HttpServletResponse response, String companyId) throws IOException {
        UserAllMessage user = (UserAllMessage) SecurityUtils.getSubject().getPrincipal();
        response.getWriter().print(companyBusinessLicenseService.queryCompanyBusinessLicense(companyId, user));
    }

}
