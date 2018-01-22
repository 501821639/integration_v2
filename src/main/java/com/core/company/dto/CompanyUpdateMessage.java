package com.core.company.dto;

import com.core.company.bean.Company;

import java.util.Date;

/**
 * Created by GSN on 2017/5/25.
 * 封装部分单位信息
 * 可修改的单位属性
 */
public class CompanyUpdateMessage extends Company {


    private String superCompanyName;//上级单位名称


    public String getSuperCompanyName() {
        return superCompanyName;
    }

    public void setSuperCompanyName(String superCompanyName) {
        this.superCompanyName = superCompanyName;
    }
}
