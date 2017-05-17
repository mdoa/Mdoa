package com.htsoft.oa.core.util;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.htsoft.core.Constants;

/**
 * 时间处理类
 * @author zxh
 *
 */
public class DateUtil {
	private static final Log logger = LogFactory.getLog(DateUtil.class);

	/**
	 * 设置当前时间为当天的最初时间（即00时00分00秒）
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar setStartDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		return cal;
	}

	/**
	 * 设置当前时间为当天的结束的时间（即23时59分59秒）
	 * 
	 * @param cal
	 * @return
	 */
	public static Calendar setEndDay(Calendar cal) {
		cal.set(Calendar.HOUR_OF_DAY, 23);
		cal.set(Calendar.MINUTE, 59);
		cal.set(Calendar.SECOND, 59);
		return cal;
	}

	/**
	 * 把源日历的年月日设置到目标日历对象中
	 * 
	 * @param destCal
	 * @param sourceCal
	 */
	public static void copyYearMonthDay(Calendar destCal, Calendar sourceCal) {
		destCal.set(Calendar.YEAR, sourceCal.get(Calendar.YEAR));
		destCal.set(Calendar.MONTH, sourceCal.get(Calendar.MONTH));
		destCal.set(Calendar.DAY_OF_MONTH, sourceCal.get(Calendar.DAY_OF_MONTH));
	}

	/**
	 * 格式化日期为 MM/dd/yyyy hh:mm:ss a 上午转换成AM 下午转换成PM
	 * 
	 * @param date
	 * @return
	 */
	public static String formatEnDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy hh:mm:ss a");

		return sdf.format(date).replaceAll("上午", "AM").replaceAll("下午", "PM");
	}

	/**
	 * 将字符串格式的日期转换成日期
	 * 
	 * @param dateString
	 *            yyyy-MM-dd HH:mm:ss和yyyy-MM-dd格式
	 * @return
	 */
	public static Date parseDate(String dateString) {
		Date date = null;
		try {
			date = DateUtils.parseDate(dateString, new String[] {
					Constants.DATE_FORMAT_FULL, Constants.DATE_FORMAT_YMD });
		} catch (Exception ex) {
			logger.error("Pase the Date(" + dateString + ") occur errors:"
					+ ex.getMessage());
		}
		return date;
	}

	/**
	 * 获取现在时间
	 * 
	 * @return返回短时间格式 yyyy-MM-dd
	 */
	public static Date strToDate() {

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		formatter.setLenient(false);
		String strDate = formatter.format(new Date());
		Date strtodate = null;
		try {
			strtodate = formatter.parse(strDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return strtodate;

	}

	/**
	 * 取得下一天。
	 * 
	 * @param date
	 * @param days
	 * @return
	 */
	public static Date getNextDays(Date date, int days) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DATE, days);
		return cal.getTime();
	}

	/**
	 * @return 当前时间(时间格式)
	 */
	public static Date getCurrentTime() {
		GregorianCalendar todaydate = new GregorianCalendar();
		return todaydate.getTime();
	}

	/**
	 * @see 取得当前日期（格式为：yyyy-MM-dd）
	 * @return String
	 */
	public static String getDate() {
		return DateFormatUtil.formatDate(new Date());
	}

	/**
	 * @see 取得当前时间（格式为：yyy-MM-dd HH:mm:ss）
	 * @return String
	 */
	public static String getDateTime() {
		return DateFormatUtil.formatTime(new Date());
	}

	/**
	 * @see 按指定格式取得当前时间
	 * @return style 指定格式
	 */
	public static String getTimeFormat(String style) {
		return DateFormatUtil.format(new Date(), style);
	}

	public static Calendar parseDateTime(String baseDate) {
		Calendar cal = null;
		cal = new GregorianCalendar();
		int yy = Integer.parseInt(baseDate.substring(0, 4));
		int mm = Integer.parseInt(baseDate.substring(5, 7)) - 1;
		int dd = Integer.parseInt(baseDate.substring(8, 10));
		int hh = 0;
		int mi = 0;
		int ss = 0;
		if (baseDate.length() > 12) {
			hh = Integer.parseInt(baseDate.substring(11, 13));
			mi = Integer.parseInt(baseDate.substring(14, 16));
			ss = Integer.parseInt(baseDate.substring(17, 19));
		}
		cal.set(yy, mm, dd, hh, mi, ss);
		return cal;
	}

	public static int getDay(String strDate) {
		Calendar cal = parseDateTime(strDate);
		return cal.get(Calendar.DATE);
	}

	public static int getMonth(String strDate) {
		Calendar cal = parseDateTime(strDate);
		return cal.get(Calendar.MONTH) + 1;
	}

	public static int getWeekDay(String strDate) {
		Calendar cal = parseDateTime(strDate);
		return cal.get(Calendar.DAY_OF_WEEK);
	}

	/**
	 * 取得星期几
	 * 
	 * @param strDate
	 * @return
	 */
	public static String getWeekDayName(String strDate) {
		String mName[] = { "日", "一", "二", "三", "四", "五", "六" };
		int iWeek = getWeekDay(strDate);
		iWeek = iWeek - 1;
		return "星期" + mName[iWeek];
	}

	/**
	 * 取得指定时间字符串的年
	 * 
	 * @param strDate
	 * @return
	 */
	public static int getYear(String strDate) {
		Calendar cal = parseDateTime(strDate);
		return cal.get(Calendar.YEAR) + 1900;
	}

	/**
	 * 取得指定时间的年
	 * 
	 * @param date
	 * @return
	 */
	public static int getYear(Date date) {
		Calendar cal = new GregorianCalendar();
		cal.setTime(date);
		return cal.get(Calendar.YEAR) + 1900;
	}


	/**
	 * 获取时间间隔字符串
	 * 
	 * @param dateA
	 * @param dateB
	 * @param resolution
	 *            初始解析精度,比如resolution=1,表示只有间隔够一个月才会显示1月...，否则显示0月
	 * @return 时间间隔字符串
	 */
	public static final String dateDifference(long dateA, long dateB,
			long resolution) {
		StringBuffer sb = new StringBuffer();
		long difference = Math.abs(dateB - dateA);
		if (resolution > 0L) {
			resolution--;
			long months = difference / 0x9fa52400L;
			if (months > 0L) {
				difference %= 0x9fa52400L;
				sb.append(months + " 月, ");
			}
		}
		if (resolution <= 0L && sb.length() == 0)
			return "0 月";
		resolution--;
		long days = difference / 0x5265c00L;
		if (days > 0L) {
			difference %= 0x5265c00L;
			sb.append(days + " 天, ");
		}
		if (resolution <= 0L && sb.length() == 0)
			return "0 天";
		resolution--;
		long hours = difference / 0x36ee80L;
		if (hours > 0L) {
			difference %= 0x36ee80L;
			sb.append(hours + " 小时, ");
		}
		if (resolution <= 0L && sb.length() == 0)
			return "0 小时";
		resolution--;
		long minutes = difference / 60000L;
		if (minutes > 0L) {
			difference %= 60000L;
			sb.append(minutes + " 分钟, ");
		}
		if (resolution <= 0L && sb.length() == 0)
			return "0 分钟";
		resolution--;
		long seconds = difference / 1000L;
		if (seconds > 0L) {
			difference %= 1000L;
			sb.append(seconds + " 秒, ");
		}
		if (resolution <= 0L && sb.length() == 0)
			return "0 秒";
		if (sb.length() > 2)
			return sb.substring(0, sb.length() - 2);
		else
			return "";
	}
	
	public static void main(String[] args) {
		
	}

}
