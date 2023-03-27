package com.szxs.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 日期工具类
 */
public class DateUtil {
    /**
     * 获取两个日期之间的日期
     * @param start 开始日期
     * @param end 结束日期
     * @return 日期字符串格式的集合
     */
    public static List<Date> getBetweenDates(Date start, Date end) {
        List<Date> result = new ArrayList<Date>();
        Calendar tempStart = Calendar.getInstance();
        tempStart.setTime(start);

        Calendar tempEnd = Calendar.getInstance();
        tempEnd.setTime(end);
        while (tempStart.before(tempEnd) || tempStart.equals(tempEnd)) {
            result.add(tempStart.getTime());
            tempStart.add(Calendar.DAY_OF_YEAR, 1);
        }
        return result;
    }

    public static int getBookingDays(Date inDate,Date outDate) {
        GregorianCalendar gc = (GregorianCalendar) Calendar.getInstance();
        gc.setTime(inDate);
        int in = gc.get(6);
        gc.setTime(outDate);
        int out = gc.get(6);
        if(out > in){
            return out - in;
        }
        return -1;
    }

    /**
     * 根据日期字符串返回日期
     * @param source
     * @param format
     * @return
     * @throws ParseException
     */
    public static final Date parse(String source,String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        return df.parse(source);
    }

    /**
     * 根据日期获取格式化的日期字符串
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static final String format(Date date,String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(date);
    }

    /**
     * 根据日期获取格式化的日期字符串，指定默认格式为：yyyy-MM-dd HH:mm:ss
     * @param date
     * @return
     * @throws ParseException
     */
    public static final String format(Date date) throws ParseException {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 返回当前日期
     * @param format
     * @return
     */
    public static final String getCurrentDate(String format) {
        DateFormat df = new SimpleDateFormat(format);
        return df.format(new Date());
    }

    /***
     * 新增天数
     * @param sourceDate
     * @param days
     * @return
     */
    public static Date addDay(Date sourceDate,Integer days){
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(sourceDate);
        calendar.add(calendar.DATE,days);
        return calendar.getTime();
    }

    /***
     * 新增小时
     * @param sourceDate
     * @param hours
     * @return
     */
    public static Date addHours(Date sourceDate,Integer hours){
        Calendar calendar   =   new GregorianCalendar();
        calendar.setTime(sourceDate);
        calendar.add(Calendar.HOUR_OF_DAY,hours);
        return calendar.getTime();
    }

    /**
     * 根据年月获取当月最后一天
     * @param yearmonth yyyy-MM
     * @return yyyy-MM-dd
     * @throws ParseException
     */
    public static String getLastDayOfMonth(String yearmonth) {
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM");
            Date dd = format.parse(yearmonth);
            Calendar cal = Calendar.getInstance();
            cal.setTime(dd);
            int cc=cal.getActualMaximum(Calendar.DAY_OF_MONTH);
            String result = yearmonth+"-"+cc;
            return result;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
