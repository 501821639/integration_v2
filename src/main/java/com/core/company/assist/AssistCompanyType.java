package com.core.company.assist;

import com.core.company.bean.CompanyType;

/**
 * Created by GSN on 2017/5/17.
 * 辅助CompanyType类
 */
public class AssistCompanyType {

    public String verificationCompanyType(CompanyType companyType) {

        if (companyType == null) {
            return "CompanyType null";
        }

        if (companyType.getName() == null) {
            return "请输入单位类型名称";
        }

        companyType.setName(companyType.getName().trim());

        if (companyType.getName().equals("")) {
            return "请输入单位类型名称";
        }

        if (companyType.getName().length() > 40) {
            return "单位类型名称长度限制40汉字、字符";
        }

        return null;

    }

}
