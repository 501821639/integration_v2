package com.utils.regular;

import java.util.regex.Pattern;

/**
 * Created by GSN on 2017/4/5.
 * 数字验证
 */
public class NumberReg {

    /**
     * 数字验证
     *
     * @param regVal 要验证的字符串
     * @return true：验证通过, false：验证失败
     */
    public boolean number(String regVal) {
        return this.numberFz(regVal);
    }

    /**
     * 数字验证
     *
     * @param regVal 要验证的字符串
     * @param length 长度验证
     * @return true：验证通过, false：验证失败
     */
    public boolean number(String regVal, int length) {

        if (this.numberFz(regVal) && regVal.length() == length) {
            return true;
        }
        return false;

    }

    /**
     * 数字验证
     *
     * @param regVal      要验证的字符串
     * @param beginLength 开始长度
     * @param endLength   结束长度
     * @return true：验证通过, false：验证失败
     */
    public boolean number(String regVal, int beginLength, int endLength) {

        if (this.numberFz(regVal) && new OtherReg().among(regVal, beginLength, endLength)) {
            return true;
        }
        return false;

    }

    private boolean numberFz(String regVal) {
        return Pattern.compile("[0-9]*").matcher(regVal).matches();
    }

}
