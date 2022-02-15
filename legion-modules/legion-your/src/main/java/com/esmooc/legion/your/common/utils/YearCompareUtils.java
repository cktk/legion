package com.esmooc.legion.your.common.utils;

import java.util.Calendar;
import java.util.Date;

/**
 * @Description:
 * @Author: zqj
 * @Date: 2021/12/1
 * @Version: 1.0
 */
public class YearCompareUtils {

    /**
     * 判断两个日期间是否超过的年数
     *
     * @param time1
     * @param time2
     * @param numYear
     * @return
     */
    public static Boolean DateCompare(Date time1, Date time2, int numYear) {
        Date time3 = add(time1, Calendar.YEAR, numYear);
        if (time3.getTime() < time2.getTime()) {
            return true;
        }
        return false;
    }

    /**
     * 时间加减
     *
     * @param date
     * @param calendarField ：Calendar.YEAR/ Calendar.MONTH /Calendar.DAY
     * @param amount
     * @return
     */
    public static Date add(final Date date, final int calendarField, final int amount) {
        if (date == null) {
            return null;
        }
        final Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

}
