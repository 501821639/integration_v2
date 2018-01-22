package com.core.user.dto;

import com.core.company.dto.CompanyBriefnessMessage;
import com.core.user.bean.Permission;
import com.core.user.bean.Role;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by GSN on 2017/4/26.
 * 封装用户其他数据信息放入shiro管理
 */
public class UserAllMessage implements Serializable {

    private String id;

    //头像url
    private String portrait;

    //账号
    private String username;

    //密码
    private String password;

    //锁定状态
    private int locked;

    //邮箱
    private String mail;

    //姓名
    private String card;

    //身份证号码
    private String idCard;

    //注册时间
    private Date regTime;

    //角色
    private List<Role> roleList;

    //权限（菜单）
    private List<Permission> permissionList;

    //单位
    private List<CompanyBriefnessMessage> companyBriefnessMessageList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPortrait() {
        return portrait;
    }

    public void setPortrait(String portrait) {
        this.portrait = portrait;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getLocked() {
        return locked;
    }

    public void setLocked(int locked) {
        this.locked = locked;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getCard() {
        return card;
    }

    public void setCard(String card) {
        this.card = card;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    public Date getRegTime() {
        return regTime;
    }

    public void setRegTime(Date regTime) {
        this.regTime = regTime;
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }

    public List<CompanyBriefnessMessage> getCompanyBriefnessMessageList() {
        return companyBriefnessMessageList;
    }

    public void setCompanyBriefnessMessageList(List<CompanyBriefnessMessage> companyBriefnessMessageList) {
        this.companyBriefnessMessageList = companyBriefnessMessageList;
    }

}
