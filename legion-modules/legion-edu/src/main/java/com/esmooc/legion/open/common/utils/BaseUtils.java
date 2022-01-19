package com.esmooc.legion.open.common.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName BaseUtils
 * @Author Administrator
 * @Date 2020-12-23 9:48
 **/
public class BaseUtils {
    /**
     * 生成32位uuid
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String getTimeDefault() {
        SimpleDateFormat sdf = new SimpleDateFormat("MMdd");
        return sdf.format(new Date());
    }

    /**
     * 获取当前系统时间
     *
     * @return 年-月-日 小时：分钟：秒
     */
    public static Date getDateNowSecond() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String s = df.format(new Date());
        Date parse = new Date();
        try {
            parse = df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    /**
     * 获取当前系统时间
     *
     * @return 年-月-日 小时：分钟：秒
     */
    public static String getChineseDateNowSecond() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
    }

    /**
     * 获取当前系统时间
     *
     * @return 年-月-日
     */
    public static String getChineseDateNowYear() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return df.format(new Date());
    }

    /**
     * 获取当前系统时间
     *
     * @return
     */
    public static Date getDateNowYear() {
        //设置日期格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String s = df.format(new Date());
        Date parse = new Date();
        try {
            parse = df.parse(s);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return parse;
    }

    // 获取系统时间 -》 年
    public static String getThisYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return sdf.format(date);
    }

    // 获取系统时间 -》 明年   2021
    public static String getNextYear() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        Date date = new Date();
        return String.valueOf(Integer.valueOf(sdf.format(date)) + 1);
    }

    /**
     * 返回数据示例： 2020年8月7日 12:37
     *
     * @return
     */
    public static String getFormattingChineseNowDate() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm");
        Date date = new Date();
        return sdf.format(date);
    }

    /**
     * 获取时间差方法
     *
     * @param maxTime 最大时间
     * @param minTime 最小时间
     * @return
     */
    public static String getTime(long diff) {
        String CountTime = "";
        if (diff != 0) {
            long days = diff / (1000 * 60 * 60 * 24);
            if (days != 0) {
                CountTime += days + "天";
            }
            long hours = (diff - days * (1000 * 60 * 60 * 24)) / (1000 * 60 * 60);
            if (hours != 0) {
                CountTime += hours + "小时";
            }
            long minutes = (diff - days * (1000 * 60 * 60 * 24) - hours * (1000 * 60 * 60)) / (1000 * 60);
            if (minutes != 0) {
                CountTime += minutes + "分";
            }
            long s = (diff / 1000 - days * 24 * 60 * 60 - hours * 60 * 60 - minutes * 60);
            if (s != 0) {
                CountTime += s + "秒";
            }
        }
        if (CountTime == "") {
            CountTime = "0秒";
            return CountTime;
        } else {
            return CountTime;
        }
    }

    /**
     * Date类型转Calendar类型
     *
     * @param date
     * @return
     */
    public static Calendar dataToCalendar(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        return calendar;
    }

    /**
     * 获取证书编号
     *
     * @param id     证书编号id  ->  生成的数字
     * @param length 生成证书编号的id的长度
     * @return
     */
    public static String getCertificate(String id, Integer length) {

        // 判断证书编号的长度
        int trueLength = length - id.length();
        for (int i = 0; i < trueLength; i++) {
            id = "0" + id;
        }
        return id;
    }

}
