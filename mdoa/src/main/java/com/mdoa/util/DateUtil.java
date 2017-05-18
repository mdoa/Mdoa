package com.mdoa.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateUtil {
	
	/**
	 * 以yyyy-MM-dd HH:mm:ss 的形式将时间转换为字符串
	 * @param date 需要转换为字符串的时间
	 * @return 返回一个时间字符串
	 */
	public static String dateToStr(Date date){
		if(date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(date);
	}
	
	/**
	 * 以 自定义格式 的形式将时间转换为字符串
	 * @param date 需要转换为字符串的时间
	 * @return 返回一个时间字符串
	 */
	public static String dateToStr(Date date,String format){
		if(date == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	
	/**
	 * 将一个yyyy-MM-dd HH:mm:ss形式的字符串转换为时间
	 * @param str yyyy-MM-dd HH:mm:ss 格式的时间字符串
	 * @return 格式化后的时间
	 */
	public static Date strToDate(String str){
		if(str == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 以 自定义格式 的形式将字符串转换为时间
	 * @param date 需要转换为时间的字符串
	 * @return 返回一个时间
	 */
	public static Date strToDate(String str,String format){
		if(str == null)
			return null;
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(str);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *  "MM-dd"日期字符串 取月份
	 * @param dateString
	 * @return
	 */
	public static int getMonth(String dateString){
		return Integer.parseInt(dateString.substring(0, 2));
	}
	
	/**
	 *  "MM-dd"日期字符串 取日
	 * @param dateString
	 * @return
	 */
	public static int getDay(String dateString){
		return Integer.parseInt(dateString.substring(3, 5));
	}
	
	/**
	 * 判断两个日期是否跨年
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	public static boolean isSpanYear(String startDate,String endDate){
		if(StringUtil.isEmpty(startDate) || StringUtil.isEmpty(endDate)){
			return false;
		}
		int startMonth = getMonth(startDate);
		int startDay = getDay(startDate);
		int endMonth = getMonth(endDate);
		int endDay = getDay(endDate);
		if( startMonth > endMonth){
			return true;
		}else if(startMonth < endMonth){
			return false;
		}else if(startDay < endDay){
			return false;
		}else if(startMonth == endMonth && startDay == endDay){
			return false;
		}else{
			return true;
		}
	}
	
}
