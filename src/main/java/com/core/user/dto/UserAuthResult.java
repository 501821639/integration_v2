package com.core.user.dto;

import java.util.Date;

/**
 * Created by GSN on 2017/5/19.
 * 生成用户注册验证码封装数据
 */
public class UserAuthResult {

    /**
     * success : 成功
     * error : 错误
     */
    private String code;

    /**
     * 原因
     */
    private String result;

    /**
     * 生成的验证码
     */
    private String auth;

    /**
     * 验证码生成时间
     */
    private Date date;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getAuth() {
        return auth;
    }

    public void setAuth(String auth) {
        this.auth = auth;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
