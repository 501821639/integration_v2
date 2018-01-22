package com.utils.date;

/**
 * Created by GSN on 2017/4/5.
 * 格式化类型
 */
public class FormatType {

    private String ymd = "yyyy-MM-dd";
    private String ymdhms = "yyyy-MM-dd HH:mm:ss";


    /**
     * @return "yyyy-MM-dd
     */
    public String getYmd() {
        return ymd;
    }

    /**
     * @return "yyyy-MM-dd HH:mm:ss
     */
    public String getYmdhms() {
        return ymdhms;
    }
}
