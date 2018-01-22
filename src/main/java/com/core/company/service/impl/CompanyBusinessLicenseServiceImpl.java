package com.core.company.service.impl;

import com.core.company.assist.AssistCompanyBusinessLicense;
import com.core.company.bean.CompanyBusinessLicense;
import com.core.company.dao.CompanyBusinessLicenseDao;
import com.core.company.service.CompanyBusinessLicenseService;
import com.core.user.dto.UserAllMessage;
import com.utils.json.FormatJson;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by GSN on 2017/5/16.
 * 暂无描述
 */

@Service
public class CompanyBusinessLicenseServiceImpl implements CompanyBusinessLicenseService {

    @Resource
    private CompanyBusinessLicenseDao companyBusinessLicenseDao;


    @Override
    public String requiredAddCompanyBusinessLicense(CompanyBusinessLicense companyBusinessLicense, UserAllMessage userAllMessage) {

        AssistCompanyBusinessLicense acbl = new AssistCompanyBusinessLicense();

        if (!acbl.cblPermission(companyBusinessLicense.getCompanyId(), userAllMessage.getCompanyBriefnessMessageList())) {
            return "无权限操作该单位";
        }

        String result = acbl.verificationCbl(companyBusinessLicense);

        if (result != null) {
            return result;
        }

        companyBusinessLicenseDao.addCompanyBusinessLicense(companyBusinessLicense);

        return "success";
    }

    @Override
    public String requiredDeleteCompanyBusinessLicense(String id, UserAllMessage userAllMessage) {

        CompanyBusinessLicense companyBusinessLicense = companyBusinessLicenseDao.getCompanyBusinessLicense(id);

        if (companyBusinessLicense == null) {
            return "文件ID错误(要删除的文件不存在)";
        }

        if (!new AssistCompanyBusinessLicense().cblPermission(companyBusinessLicense.getCompanyId(), userAllMessage.getCompanyBriefnessMessageList())) {
            return "无权限操作该单位";
        }

        companyBusinessLicenseDao.deleteCompanyBusinessLicense(companyBusinessLicense);

        return "success";
    }

    @Override
    public String queryCompanyBusinessLicense(String companyId, UserAllMessage userAllMessage) {

        if (!new AssistCompanyBusinessLicense().cblPermission(companyId, userAllMessage.getCompanyBriefnessMessageList())) {
            return new FormatJson().listConversion(new ArrayList<>());
        }

        String hql = "from CompanyBusinessLicense cbl where cbl.companyId='" + companyId + "'";

        return new FormatJson().listConversion(companyBusinessLicenseDao.queryCompanyBusinessLicense(hql));
    }
}
