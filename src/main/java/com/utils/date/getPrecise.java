package com.utils.date;

import java.util.Calendar;
import java.util.Date;

/**
 * Created by GSN on 2017/4/6.
 * 获取日期年月日时分秒
 */
public class getPrecise {

    /**
     * 年
     *
     * @param date
     * @return int
     */
    public int getYear(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.YEAR);
    }

    /**
     * 月
     *
     * @param date
     * @return int
     */
    public int getMonth(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.MONTH) + 1;
    }

    /**
     * 日
     *
     * @param date
     * @return int
     */
    public int getDay(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.DAY_OF_MONTH);
    }

    /**
     * 时
     *
     * @param date
     * @return
     */
    public int getHour(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 分
     *
     * @param date
     * @return
     */
    public int getMinute(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.MINUTE);
    }

    /**
     * 秒
     *
     * @param date
     * @return
     */
    public int getSce(Date date) {
        Calendar now = Calendar.getInstance();
        now.setTime(date);
        return now.get(Calendar.SECOND);
    }


}
