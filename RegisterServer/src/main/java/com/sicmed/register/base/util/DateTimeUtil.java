package com.sicmed.register.base.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @Author: ykbian
 * @Date: 2018/9/18 13:57
 * @Todo:
 */

public class DateTimeUtil {

    /**
     * 获取系统当前时间
     *
     * @return String
     *
     */
    public static String getCurrentTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

        return df.format(System.currentTimeMillis());
    }

    /**
     * 获取系统当前时间
     *
     * @return String
     *
     */
    public static String getDateStr() {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式

        return df.format(System.currentTimeMillis());
    }





    /**
     * 获取今天0点的 时间
     * @return
    //	 */
    public static Date getDayBeginDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        return date;
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 13:53
     *@Description: 获取今日24时时间,用于查询的截止时间
     *@param:
     */
    public static Date getDayEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 50);
        Date date = calendar.getTime();
        return date;
    }

    /**
     *@Author:      ykbian
     *@date_time:   2018/9/18 13:53
     *@Description: 就诊的结束时间
     *@param:
     */
    public static Date getCureEndDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.set(Calendar.HOUR_OF_DAY, 23);
        calendar.set(Calendar.MINUTE, 59);
        calendar.set(Calendar.SECOND, 0);
        Date date = calendar.getTime();
        return date;
    }

    /**
     * 判断是否是第二天
     * @return
     */
    public static boolean getDayBeginDate(Date date) {

        Date date3 = DateTimeUtil.getDayBeginDate();
        String str1 = getDateStr2(date3);
        String str2 = getDateStr2(date);
        if(str1.equals(str2)){
            return false;
        }
        return true;
    }
    public static String getDateStr2(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式

        return df.format(date);
    }
    /**
     *
     * @return String
     *
     */
    public static String getDateStr(Date date) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

        return df.format(date);
    }
    /**
     * 获取系统当前时间
     *
     * @return Date
     *
     */
    public static Date getTime() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

        try {
            return df.parse(df.format(System.currentTimeMillis()));
        } catch (ParseException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }

    public static Date getTime(String str) {
        SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");// 设置日期格式

        try {
            return df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDate(String str) {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

        try {
            return df.parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Date getDate1(String str) {
        String tempStr = str.replace("/", "-");
        if (tempStr.length()<10) {
            tempStr = tempStr+" 00:00:00";
        }
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式

        try {
            return df.parse(tempStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
