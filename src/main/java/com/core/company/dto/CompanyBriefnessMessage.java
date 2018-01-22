package com.core.company.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by GSN on 2017/5/18.
 * 封装单位部分数据
 * 用于存放shiro认证中
 */
public class CompanyBriefnessMessage implements Serializable {

    private String id;

    //单位名称
    private String name;

    private String superName;

    //单位类型名称
    private String typeName;

    //单位性质名称
    private String natureName;

    //省市直辖区名称
    private String regionName;

    //审核状态
    private int check;

    //注册时间
    private Date regTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSuperName() {
        return superName;
    }

    public void setSuperName(String superName) {
        this.superName = superName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getNatureName() {
        return natureName;
    }

    public void setNatureName(String natureName) {
        this.natureName = natureName;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getCheck() {
        return check;
    }

    public void setCheck(int check) {
        this.check = check;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }
}
