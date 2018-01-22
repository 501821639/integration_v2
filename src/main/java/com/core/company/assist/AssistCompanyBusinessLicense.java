package com.core.company.assist;


import com.core.company.bean.CompanyBusinessLicense;
import com.core.company.dto.CompanyBriefnessMessage;

import java.util.List;

/**
 * Created by GSN on 2017/5/18.
 * 辅助CompanyBusinessLicense类
 */
public class AssistCompanyBusinessLicense {

    /**
     * 验证用户是否拥有操作该单位的权限
     *
     * @param companyId                   单位id
     * @param companyBriefnessMessageList 用户所属单位
     * @return true:拥有操作权限
     */
    public boolean cblPermission(String companyId, List<CompanyBriefnessMessage> companyBriefnessMessageList) {

        if (companyId == null) {
            return false;
        }

        if (companyBriefnessMessageList == null || companyBriefnessMessageList.isEmpty()) {
            return false;
        }

        for (CompanyBriefnessMessage companyBriefnessMessage : companyBriefnessMessageList) {
            if (companyId.equals(companyBriefnessMessage.getId())) {
                return true;
            }
        }

        return false;
    }


    /**
     * 验证数据是否符合规则
     *
     * @param companyBusinessLicense 实体类
     * @return null:符合规则
     */
    public String verificationCbl(CompanyBusinessLicense companyBusinessLicense) {

        if (companyBusinessLicense.getCompanyId() == null || companyBusinessLicense.getCompanyId().trim().equals("")) {
            return "单位ID错误";
        }

        if (companyBusinessLicense.getQuondamName() == null || companyBusinessLicense.getQuondamName().trim().equals("")) {
            return "系统检测不到上传文件名称";
        }

        if (companyBusinessLicense.getUrl() == null || companyBusinessLicense.getUrl().trim().equals("")) {
            return "系统检测不到上传地址";
        }

        return null;
    }


}
