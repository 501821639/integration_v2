package com.utils.date;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GSN on 2017/4/6.
 * Date 格式换 String 类型
 */
public class FormatString {

    /**
     * Date格式化String yyyy-MM-dd
     *
     * @param date 要格式化的日期
     * @return String
     */
    public String FromatYmd(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(new FormatType().getYmd());
        return format.format(date);
    }

    /**
     * Date格式化String yyyy-MM-dd HH:mm:ss
     *
     * @param date 要格式化的日期
     * @return String
     */
    public String FromatYmdhms(Date date) {
        SimpleDateFormat format = new SimpleDateFormat(new FormatType().getYmdhms());
        return format.format(date);
    }

    /**
     * Date格式化String
     *
     * @param date       要格式化的日期
     * @param formatType 自定义格式
     * @return String
     */
    public String formatCustom(Date date, String formatType) {
        SimpleDateFormat format = new SimpleDateFormat(formatType);
        return format.format(date);
    }

}
