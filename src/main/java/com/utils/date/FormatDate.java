package com.utils.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by GSN on 2017/4/5.
 * String格式化Date类型
 */
public class FormatDate {

    /**
     * String格式化Date yyyy-MM-dd
     *
     * @param strDate 要格式化的字符串
     * @return Date
     */
    public Date fromatYmd(String strDate) {

        try {
            return new SimpleDateFormat(new FormatType().getYmd()).parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式化失败");
        }

    }

    /**
     * String格式化Date yyyy-MM-dd HH:mm:ss
     *
     * @param strDate 要格式化的字符串
     * @return Date
     */
    public Date fromatYmdhms(String strDate) {

        try {
            return new SimpleDateFormat(new FormatType().getYmdhms()).parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式化失败");
        }

    }

    /**
     * String格式化Date
     *
     * @param strDate    要格式化的字符串
     * @param formatType 自定义格式化
     * @return Date
     */
    public Date formatCustom(String strDate, String formatType) {

        try {
            return new SimpleDateFormat(formatType).parse(strDate);
        } catch (ParseException e) {
            throw new RuntimeException("日期格式化失败");
        }

    }

}
