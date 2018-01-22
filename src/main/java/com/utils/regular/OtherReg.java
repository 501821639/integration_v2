package com.utils.regular;

import java.util.regex.Pattern;

/**
 * Created by GSN on 2017/4/5.
 * 其他正则验证
 */
public class OtherReg {

    /**
     * 验证字符串长度在多少长度之间
     *
     * @param regVal      要验证的字符串
     * @param beginLength 开始长度
     * @param endLength   结束长度
     * @return true：验证通过, false：验证失败
     */
    public boolean among(String regVal, int beginLength, int endLength) {

        int regValLength = regVal.length();

        if (regValLength > endLength || regValLength < beginLength) {
            return false;
        }

        return true;

    }

    /**
     * 验证是否为数字、字母、忽略大小写
     *
     * @param regVal 眼验证的字符串
     * @return true：验证通过, false：验证失败
     */
    public boolean alphanumeric(String regVal) {
        return Pattern.compile("[a-z0-9A-Z]+").matcher(regVal).matches();
    }

}
