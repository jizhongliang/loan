/**
 * Copyright (c) 2017 All Rights Reserved.
 */
package com.hwc.framework.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * @author jzl
 *
 */
public class DateUtil {

    public static final String sdf = "yyyy-MM-dd";
    public static final String night = "23:59:59";
    public static final String patten = "yyyy-MM-dd HH:mm:ss";

    /**
     * 想要获取的日期与传入日期的差值 比如想要获取传入日期前四天的日期 day=-4即可
     * @param date
     * @param day
     * @return
     */
    public static Date getSomeDay(Date date, int day){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, day);
        return calendar.getTime();
    }

    /**
     * 获取某天的午夜时间
     * @param date
     * @param day
     * @return
     */
    public static Date getSomeDayNight(Date date, int day) {
        Date someDay = getSomeDay(date, day);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(sdf);
        String str = simpleDateFormat.format(someDay).concat(" ").concat(night);
        try {
            return new SimpleDateFormat(patten).parse(str);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 比较时间大小
     * date1 > date2  1
     * date1 < date2 -1
     * date1 = date2 0
     * @param date1
     * @param date2
     * @return
     */
    public static int compareDate(Date date1, Date date2) {
        return date1.getTime() > date2.getTime() ? 1 : date1.getTime() < date2.getTime() ? -1 : 0;
    }

    public static void main(String[] args) throws ParseException {
        //System.out.print(getSomeDayNight(new Date(), 0));
		String str1 = "2018-01-10 00:00:00";
		String str2 = "2018-01-09 23:59:59";
		Date date1 = new SimpleDateFormat(patten).parse(str1);
		Date date2 = new SimpleDateFormat(patten).parse(str2);
		System.out.print(daysBetween(date2, date1));
    }


    public static Date valueOf(String str) {
        return valueOf(str, "yyyy-MM-dd");
    }

    public static Date valueOf(String str, String dateFormatStr) {
        SimpleDateFormat formatter = new SimpleDateFormat(dateFormatStr);
        ParsePosition pos = new ParsePosition(0);
        Date strtoDate = formatter.parse(str, pos);
        return strtoDate;
    }
    public static String getDate(Date date){
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy年MM月dd日");  
		return sdf.format(date);  
    }
    public static String getAllDate(Date date){
    	SimpleDateFormat sdf=new SimpleDateFormat(patten);  
		return sdf.format(date);  
    }
    /**
     * 计算日期相隔天数
     * @param now
     * @param returnDate
     * @return
     */
    public static int daysBetween(Date now, Date returnDate) {
    	  Calendar cNow = Calendar.getInstance();
    	  Calendar cReturnDate = Calendar.getInstance();
    	  cNow.setTime(now);
    	  cReturnDate.setTime(returnDate);
    	  setTimeToMidnight(cNow);
    	  setTimeToMidnight(cReturnDate);
    	  long todayMs = cNow.getTimeInMillis();
    	  long returnMs = cReturnDate.getTimeInMillis();
    	  long intervalMs = todayMs - returnMs;
    	  return millisecondsToDays(intervalMs);
    	}
    	private static int millisecondsToDays(long intervalMs) {
    	  return (int) (intervalMs / (1000 * 86400));
    	}
    	private static void setTimeToMidnight(Calendar calendar) {
    	  calendar.set(Calendar.HOUR_OF_DAY, 0);
    	  calendar.set(Calendar.MINUTE, 0);
    	  calendar.set(Calendar.SECOND, 0);
    	}

	public static Date parseDate2hms(String s) throws ParseException {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(s);
	}
    	/**
    	 * 获取指定时间天的开始时间
    	 * 
    	 * @param date
    	 * @return
    	 */
    	public static Date getDayStartTime(Date date) {
    		Calendar cal = Calendar.getInstance();
    		cal.setTimeInMillis(date.getTime());
    		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
    				cal.get(Calendar.DATE), 0, 0, 0);
    		return cal.getTime();
    	}

    	/**
    	 * 获取指定时间天的结束时间
    	 * 
    	 * @param date
    	 * @return
    	 */
    	public static Date getDayEndTime(Date date) {
    		Calendar cal = Calendar.getInstance();
    		cal.setTimeInMillis(date.getTime());
    		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH),
    				cal.get(Calendar.DATE), 23, 59, 59);
    		return cal.getTime();
    	}

}
