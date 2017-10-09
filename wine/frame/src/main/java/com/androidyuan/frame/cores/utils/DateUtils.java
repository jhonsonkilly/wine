package com.androidyuan.frame.cores.utils;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by weizongwei on 15-12-2.
 */
public class DateUtils {

    public static String getCurrentYear() {

        Calendar cal = Calendar.getInstance();
        String year = cal.get(Calendar.YEAR) + "";

        return year;
    }

    public static String getCurrentMonth() {

        Calendar cal = Calendar.getInstance();
        String month = cal.get(Calendar.MONTH) + 1 + "";
        if ((cal.get(Calendar.MONTH) + 1) < 10) {
            month = "0" + month;
        }
        return month;
    }

    public static String getCurrentDay() {

        Calendar cal = Calendar.getInstance();

        String day = cal.get(Calendar.DAY_OF_MONTH) + "";
        if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "0" + day;
        }
        return day;
    }


    public static String getYear(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String year = cal.get(Calendar.YEAR) + "";
        return year;
    }


    public static String getMonth(Date date) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        String month = cal.get(Calendar.MONTH) + 1 + "";
        if ((cal.get(Calendar.MONTH) + 1) < 10) {
            month = "0" + month;
        }
        return month;
    }

    public static String getDay(Date dt) {

        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        String day = cal.get(Calendar.DAY_OF_MONTH) + "";
        if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "0" + day;
        }
        return day;
    }

    //返回未来几天的日期
    public static String getFeatureDate_noyear(int d) {

        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, d);//把日期往后增加一天.整数往后推,负数往前移动

        String month = cal.get(Calendar.MONTH) + 1 + "";
        if ((cal.get(Calendar.MONTH) + 1) < 10) {
            month = "0" + month;
        }

        String day = cal.get(Calendar.DAY_OF_MONTH) + "";
        String day4 = cal.get(Calendar.DAY_OF_WEEK) + "";
        String day2 = cal.get(Calendar.DAY_OF_WEEK_IN_MONTH) + "";
        String day3 = cal.get(Calendar.DAY_OF_YEAR) + "";
        if (cal.get(Calendar.DAY_OF_MONTH) < 10) {
            day = "0" + day;
        }

        return month + "-" + day;
    }


    /**
     * @param date
     * @return "yyyy-MM-dd" 格式的String
     */
    public static final String date2String(Date date) {

        if (date == null)
            return "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    /**
     * @return "yyyy年MM月dd日" 格式的String
     */
    public static final String date2YMDStr(Date dt) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
        return format.format(dt);
    }

    /**
     * @return "yyyy年MM月dd日" 格式的String
     */
    public static final String date2YMDStr2(Date dt) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");
        return format.format(dt);
    }


    /**
     * 获得当前的年月日的格式的String
     *
     * @return "yyyy年MM月dd日" 格式的String
     */
    public static final String getNowYMD() {

        return date2YMDStr(new Date());
    }


    public static final String dateTime2String(Date date) {

        if (date == null)
            return "";


        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd  hh:mm:ss");
        return format.format(date);
    }


    /**
     * 获取当前日期是星期几<br>
     *
     * @param dt
     * @return 当前日期是星期几
     */
    public static String getWeekOfDate(Date dt) {

        String[] weekDays = {"星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六"};
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0)
            w = 0;
        return weekDays[w];
    }

    /**
     * @author Chris
     * @time 16/8/24 下午10:25
     * @function 返回现在的时间
     */
    public static String getTimeFromMillisecond(Long millisecond) {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        Date date = new Date(millisecond);
        String timeStr = simpleDateFormat.format(date);
        return timeStr;
    }

    //second to XX:XX:XX
    public static String secToTime(int time) {

        String timeStr = null;
        int hour = 0;
        int minute = 0;
        int second = 0;
        if (time <= 0)
            return "00:00";
        else {
            minute = time / 60;
            if (minute < 60) {
                second = time % 60;
                timeStr = unitFormat(minute) + ":" + unitFormat(second);
            }
            else {
                hour = minute / 60;
                if (hour > 99)
                    return "99:59:59";
                minute = minute % 60;
                second = time - hour * 3600 - minute * 60;
                timeStr = unitFormat(hour) + ":" + unitFormat(minute) + ":" + unitFormat(second);
            }
        }
        return timeStr;
    }

    public static String unitFormat(int i) {

        String retStr = null;
        if (i >= 0 && i < 10)
            retStr = "0" + Integer.toString(i);
        else
            retStr = "" + i;
        return retStr;
    }


}
