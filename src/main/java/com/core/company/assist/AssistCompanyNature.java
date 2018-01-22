package com.core.company.assist;

import com.core.company.bean.CompanyNature;

/**
 * Created by GSN on 2017/5/17.
 * 辅助CompanyNature类
 */
public class AssistCompanyNature {

    public String verificationCompanyNature(CompanyNature companyNature) {

        if (companyNature == null) {
            return "CompanyNature null";
        }

        if (companyNature.getName() == null) {
            return "请输入单位性质名称";
        }

        companyNature.setName(companyNature.getName().trim());

        if (companyNature.getName().equals("")) {
            return "请输入单位性质名称";
        }

        if (companyNature.getName().length() > 40) {
            return "单位性质长度限制40汉字、字符";
        }

        return null;

    }

}
