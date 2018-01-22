package com.core.user.bean;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by GSN on 2017/3/31.
 * 用户
 */
@Entity
@Table(name = "user")
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "uuid_user_id")
    @GenericGenerator(name = "uuid_user_id", strategy = "uuid")
    @Column(name = "id", nullable = false, unique = true)
    private String id;

    /**
     * 头像
     */
    @Column(name = "portrait_")
    private String portrait;

    /**
     * 账号
     */
    @Column(name = "user_name", nullable = false, unique = true)
    private String username;

    /**
     * 密码
     * md5加密
     * 盐值 = 账号
     */
    @Column(name = "password_", nullable = false)
    private String password;

    /**
     * 锁定状态
     * 0：锁定
     * 1:正常使用
     */
    @Column(name = "locked_")
    private int locked;

    /**
     * 账号被锁定原因
     */
    @Column(name = "locked_remark", length = 100)
    private String lockedRemark;

    /**
     * 邮箱
     */
    @Column(name = "mail_", length = 80)
    private String mail;

    /**
     * 姓名
     */
    @Column(name = "card_", length = 80)
    private String card;

    /**
     * 身份证号码
     */
    @Column(name = "id_card", length = 40)
    private String idCard;

    /**
     * 注册时间
     */
    @Column(name = "reg_time", nullable = false)
    private Date regTime;

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

    public String getLockedRemark() {
        return lockedRemark;
    }

    public void setLockedRemark(String lockedRemark) {
        this.lockedRemark = lockedRemark;
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

}
