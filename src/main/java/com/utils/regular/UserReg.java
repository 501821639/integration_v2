package com.utils.regular;


import java.util.regex.Pattern;

/**
 * Created by GSN on 2017/4/5.
 * 用户信息正则
 */
public class UserReg {

    /**
     * 用户账号验证
     *
     * @param regVal 要验证的字符串
     * @return true：验证通过, false：验证失败
     */
    public boolean userName(String regVal) {
        return this.userRegFz(regVal);
    }

    /**
     * 用户密码验证
     *
     * @param regVal 要验证的字符串
     * @return true：验证通过, false：验证失败
     */
    public boolean password(String regVal) {
        return userRegFz1(regVal);
    }

    /**
     * 手机号码验证
     *
     * @param regVal 要验证的字符串
     * @return true：验证通过, false：验证失败
     */
    public boolean phone(String regVal) {
        return new NumberReg().number(regVal, 11);
    }

    /**
     * 固定电话验证
     *
     * @param regVal 要验证的字符串
     * @return true：验证通过, false：验证失败
     */
    public boolean gdPhone(String regVal) {
        return Pattern.compile("^[0-9]{3,4}-([0-9]{7,8})$").matcher(regVal).matches();
    }

    /**
     * 邮箱验证
     *
     * @param regVal 要验证的字符串
     * @return true：验证通过, false：验证失败
     */
    public boolean mail(String regVal) {
        return Pattern.compile("^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$").matcher(regVal).matches();
    }

    /**
     * 身份证号码验证
     *
     * @param regVal 要验证的字符串
     * @return true：验证通过, false：验证失败
     */
    public boolean idCard(String regVal) {
        return Pattern.compile("^\\d{15}|^\\d{17}([0-9]|X|x)$").matcher(regVal).matches();
    }

    //有字母、数字和下划线且不能以下划线开头和结尾
    private boolean userRegFz(String regVal) {
        return Pattern.compile("^(?!_)(?!.*?_$)[a-zA-Z0-9_]{8,16}+$").matcher(regVal).matches();
    }

    //大小写字母、数字
    private boolean userRegFz1(String regVal) {
        return Pattern.compile("^[0-9a-zA-Z]{8,16}$").matcher(regVal).matches();
    }


}
