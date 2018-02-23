package com.hwc.framework.modules.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtils extends org.apache.commons.lang.time.DateUtils {
	// hh是12小时制,HH是24小时制
	public static String SECOND_FORMAT = "yyyy-MM-dd HH:mm:ss";
	public static String MINUTE_FORMAT = "yyyy-MM-dd HH:mm";
	public static String DAY_FORMAT = "yyyy-MM-dd";
	public static int minute = 1000 * 60;
	public static int hour = minute * 60;
	public static int day = hour * 24;
	public static int week = day * 7;
	public static int month = day * 30;

	/**
	 * 计算两个日期之间相差的天数
	 *
	 * @param beforeDate
	 *            较小的时间
	 * @param afterDate
	 *            较大的时间
	 * @return 相差天数
	 * @throws ParseException
	 */
	public static int daysBetween(Date beforeDate, Date afterDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		beforeDate = sdf.parse(sdf.format(beforeDate));
		afterDate = sdf.parse(sdf.format(afterDate));
		Calendar cal = Calendar.getInstance();
		cal.setTime(beforeDate);
		long time1 = cal.getTimeInMillis();
		cal.setTime(afterDate);
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);

		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 前后两个时间相差的小时数
	 *
	 * @param beforeDate
	 * @param afterDate
	 * @return
	 * @throws ParseException
	 */
	public static int hoursBetween(Date beforeDate, Date afterDate) throws ParseException {

		long time1 = beforeDate.getTime();
		long time2 = afterDate.getTime();
		long between_days = (time2 - time1) / (1000 * 3600);

		return (int) between_days;
	}

	public static void main(String[] args) throws ParseException {
		Date d = org.apache.commons.lang.time.DateUtils.addHours(new Date(), -1);
		System.out.println(hoursBetween(d, new Date()));
	}

	/**
	 * 字符串的日期格式的计算
	 */
	public static int daysBetween(String beforeDate, String afterDate) throws ParseException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(sdf.parse(beforeDate));
		long time1 = cal.getTimeInMillis();
		cal.setTime(sdf.parse(afterDate));
		long time2 = cal.getTimeInMillis();
		long between_days = (time2 - time1) / (1000 * 3600 * 24);
		return Integer.parseInt(String.valueOf(between_days));
	}

	/**
	 * 将时间格式化成format形式
	 *
	 * @param format
	 * @param date
	 * @return
	 */
	public static String format(String format, Date date) {
		if (date == null) {
			return "";
		}
		SimpleDateFormat dateFormat = new SimpleDateFormat(format);
		return dateFormat.format(date);
	}

	/**
	 * 将时间格式化成yyyy-MM-dd HH:mm:ss
	 *
	 * @param date
	 * @return
	 */
	public static String formatSecond(Date date) {
		return format(SECOND_FORMAT, date);
	}

	/**
	 * 将时间格式化成yyyy-MM-dd
	 *
	 * @param date
	 * @return
	 */
	public static String formatDay(Date date) {
		return format(DAY_FORMAT, date);
	}

	/**
	 * 将时间格式化成yyyy-MM-dd HH:mm
	 *
	 * @param date
	 * @return
	 */
	public static String formatMin(Date date) {
		return format(MINUTE_FORMAT, date);
	}

	public static String getDateDiff(long timeStamp) {
		long now = new Date().getTime();
		long diff = now - timeStamp;

		Long monthC;
		Long weekC;
		Long dayC;
		Long hourC;
		Long minC;
		String result = "刚刚";

		if ((monthC = diff / month) >= 1) {
			result = monthC + "个月前";
		} else if ((weekC = diff / week) >= 1) {
			result = weekC + "星期前";
		} else if ((dayC = diff / day) >= 1) {
			result = dayC + "天前";
		} else if ((hourC = diff / hour) >= 1) {
			result = hourC + "小时前";
		} else if ((minC = diff / minute) >= 1) {
			result = minC + "分钟前";
		}
		return result;
	}

	public static Date addDayOnly(Date date, int amount) {
		date = org.apache.commons.lang.time.DateUtils.setHours(date, 0);
		date = org.apache.commons.lang.time.DateUtils.setMinutes(date, 0);
		date = org.apache.commons.lang.time.DateUtils.setSeconds(date, 0);
		date = org.apache.commons.lang.time.DateUtils.setMilliseconds(date, 0);
		date = org.apache.commons.lang.time.DateUtils.addDays(date, amount);
		return date;
	}
}
