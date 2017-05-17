package com.htsoft.oa.util;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang.StringUtils;

/**
 * 流水号生成帮助类
 * 
 * @author zxh
 * 
 */
public class SerialNumberUtil {
	/**
	 * 当前日历值
	 */
	public static Calendar calendar = Calendar.getInstance();

	/**
	 * 获取当前日历值
	 */
	public static Calendar getCalendar() {
		return calendar;
	}

	/**
	 * 设置当前日历值
	 */
	public static void setCalendar(Calendar calendar) {
		SerialNumberUtil.calendar = calendar;
	}

	/**
	 * <pre>
	 * 格式符	输出                    说明
	 * =================================
	 * {yyyy}	2012		4位数字表示的当前年数
	 * {yy}	12		2位数字表示的当前年数
	 * {MM}	01		当前的月份，两位数字，不足位补“0”
	 * {mm}	1 		当前的月份，不补“0”
	 * {DD}	10 		月份中的天数，两位数字，不足位补“0” 
	 * {dd}	1 		月份中的天数，不补“0”
	 * {HH}	15		24小时制表示的当前小时数，不足位补“0”
	 * {hh}	15		24小时制表示的当前小时数，不补“0”
	 * {GG}	03		12小时制表示的当前小时数，不足位补“0”
	 * {gg}	3 		12小时制表示的当前小时数，不补“0”
	 * {MI}	5 		不足位补“0”的分钟数
	 * {mi}	05 		不补“0”的分钟数
	 * {SS}	01 		不足位补“0”的秒数
	 * {ss}	01		不补“0”的秒数
	 * {SSS}	001		不足位补“0”的毫秒数
	 * {SSSS}	0001		不足位补“0”的毫秒数（4位）
	 * {ss}	1 		不补“0”的毫秒数
	 * {FF}	January		当前月份的完整拼写
	 * {ff}	Jan		当前月份的缩写，前三个字母
	 * {WW}	Wednesday		当前星期的完整拼写 
	 * {ww}	Wed		当前星期的缩写，前三个字母
	 * {YW}	01		一年之中的周数，两位数字（00～53）
	 * {WD}	3 		 一周之中的天数（0～6）（周日为0, 周六为6）
	 * {AA}	PM		大写的“AM”和“PM”
	 * {aa}	pm		小写的“am”和“pm”
	 * {YD}	300		当前年中的天数。一年中第一天的值为 1。
	 * <font style="color:red;">{NO}</font>	00001		流水号 
	 * <font style="color:red;">{RN}</font>	00345		随机号
	 * </pre>
	 */
	public static Map<String, Integer> rule = new HashMap<String, Integer>();

	/**
	 * 随机的数
	 * 
	 * <pre>
	 * 格式符	输出                    说明
	 * =================================
	 * <font style="color:red;">{RN}</font>	00345		随机号
	 * <font style="color:red;">{RA}</font>	00345		随机号
	 *  <font style="color:red;">{RAU}</font>	00345		随机号
	 * </pre>
	 */
	public static Map<String, Integer> random = new HashMap<String, Integer>();

	static {
		rule.put("{yyyy}", 1);
		rule.put("{yy}", 2);
		rule.put("{MM}", 3);
		rule.put("{mm}", 4);
		rule.put("{DD}", 5);
		rule.put("{dd}", 6);
		rule.put("{HH}", 7);
		rule.put("{hh}", 8);
		rule.put("{GG}", 9);
		rule.put("{gg}", 10);
		rule.put("{MI}", 11);
		rule.put("{mi}", 12);
		rule.put("{SS}", 13);
		rule.put("{ss}", 14);
		rule.put("{SSS}", 15);
		rule.put("{sss}", 16);
		rule.put("{FF}", 17);
		rule.put("{ff}", 18);
		rule.put("{WW}", 19);
		rule.put("{ww}", 20);
		rule.put("{YW}", 21);
		rule.put("{WD}", 22);
		rule.put("{AA}", 23);
		rule.put("{aa}", 24);
		rule.put("{YD}", 25);// 当前年中的天数。一年中第一天的值为 1。
		rule.put("{SSSS}", 26);// 4位的毫秒
		random.put("{RN}", 101);// 随机数字
		random.put("{RA}", 102);// 随机字母
		random.put("{RAU}", 103);// 随机大写字母
		random.put("{RAL}", 104);// 随机小写字母
		random.put("{RAN}", 105);// 随机字母和数字
		random.put("{RANU}", 106);// 随机大写字母和数字
		random.put("{RANL}", 107);// 随机小写字母和数字
	}

	public static String idNumber = "{NO}";

	/**
	 * 转换值 // {yyyy}{MM}{dd}等格式转换
	 * 
	 * @param calendar
	 *            当前日历
	 * @param value
	 *            转换的值
	 * @return
	 */
	private static String converValue(int key) {
		switch (key) {
		case 1:// 4位年
			return toString(calendar.get(Calendar.YEAR));
		case 2:// 2位年
			return right2(calendar.get(Calendar.YEAR));
		case 3:// 当前的月份，两位数字，不足位补“0”
			return leftPad2(calendar.get(Calendar.MONTH) + 1);
		case 4:// 当前的月份，不补“0”
			return toString(calendar.get(Calendar.MONTH) + 1);
		case 5:// 月份中的天数，两位数字，不足位补“0”
			return leftPad2(calendar.get(Calendar.DAY_OF_MONTH));
		case 6:// 月份中的天数，不补“0”
			return toString(calendar.get(Calendar.DAY_OF_MONTH));
		case 7:// 24小时制表示的当前小时数，不足位补“0”
			return leftPad2(calendar.get(Calendar.HOUR_OF_DAY));
		case 8:// 24小时制表示的当前小时数，不补“0”
			return toString(calendar.get(Calendar.HOUR_OF_DAY));
		case 9:// 12小时制表示的当前小时数，不足位补“0”
			return leftPad2(calendar.get(Calendar.HOUR));
		case 10:// 12小时制表示的当前小时数，不补“0”
			return toString(calendar.get(Calendar.HOUR));
		case 11:// 不足位补“0”的分钟数
			return leftPad2(calendar.get(Calendar.MINUTE));
		case 12:// 不补“0”的分钟数
			return toString(calendar.get(Calendar.MINUTE));
		case 13:// 不足位补“0”的秒数
			return leftPad2(calendar.get(Calendar.SECOND));
		case 14:// 不补“0”的秒数
			return toString(calendar.get(Calendar.SECOND));
		case 15:// 不足位补“0”的毫秒数
			return leftPad(calendar.get(Calendar.MILLISECOND), 3);
		case 16:// 不补“0”的毫秒数
			return toString(calendar.get(Calendar.MILLISECOND));
		case 17:// 当前月份的完整拼写
			return toDisplayNameLong(calendar, Calendar.MONTH);
		case 18:// 当前月份的缩写，前三个字母
			return toDisplayNameShort(calendar, Calendar.MONTH);
		case 19:// 当前星期的完整拼写
			return toDisplayNameLong(calendar, Calendar.DAY_OF_WEEK);
		case 20:// 当前星期的完整拼写-缩写
			return toDisplayNameShort(calendar, Calendar.DAY_OF_WEEK);
		case 21:// 一年之中的周数，两位数字（00～53）
			return leftPad2(Calendar.WEEK_OF_YEAR);
		case 22:// 一周之中的天数（0～6）（周日为0, 周六为6）
			return leftPad2(calendar.get(Calendar.DAY_OF_WEEK) - 1);
		case 23:// 大写的“AM”和“PM”
			return toDisplayNameLong(calendar, Calendar.AM_PM).toUpperCase();
		case 24:// 小写的“am”和“pm”
			return toDisplayNameLong(calendar, Calendar.AM_PM).toLowerCase();
		case 25:// 当前年中的天数。一年中第一天的值为 1。
			return leftPad(calendar.get(Calendar.DAY_OF_YEAR), 3);
		case 26:// 增加4位的毫秒
			return leftPad(calendar.get(Calendar.MILLISECOND), 4);
		default:
			return "";
		}

	}

	/**
	 * 随机字母和数字
	 * 
	 * @param key
	 * @param length
	 * @return
	 */
	private static String converRandom(Integer key, int length) {
		switch (key) {
		case 101:// 随机数字
			return randomNumeric(length);
		case 102:// /随机字母
			return randomAlphabetic(length);
		case 103:// 随机大写字母
			return randomAlphabetic(length).toUpperCase();
		case 104:// 随机小写字母
			return randomAlphabetic(length).toLowerCase();
		case 105:// /随机字母和数字
			return randomAlphaNumeric(length);
		case 106:// 随机大写字母和数字
			return randomAlphaNumeric(length).toUpperCase();
		case 107:// 随机小写字母和数字
			return randomAlphaNumeric(length).toLowerCase();
		default:
			return "";
		}
	}

	/**
	 * 生成完整的流水号
	 * 
	 * @param value
	 *            规则值{yyyy}{MM}
	 * @param curValue
	 *            当前值
	 * @param length
	 *            流水号或随机号长度
	 * @return
	 */
	public static String genNumber(String value, int curValue, int length) {
		return replaceRandom(replaceNo(replaceNumber(value), curValue, length),
				length);
	}

	/**
	 * 生成不含流水号({NO})的随机数流水号
	 * 
	 * @param value
	 * @param length
	 * @return
	 */
	public static String genNumberByRandom(String value, int length) {
		return replaceRandom(replaceNumber(value), length);
	}

	/**
	 * 生成不含随机数（{RA}等）的流水号
	 * 
	 * @param value
	 * @param curValue
	 * @param length
	 * @return
	 */
	public static String genNumberByNO(String value, int curValue, int length) {
		return replaceNo(replaceNumber(value), curValue, length);
	}

	/**
	 * 获得替换后的流水号（不含流水号，随机数）
	 * 
	 * @param value
	 *            替换的值
	 * @return
	 */
	public static String replaceNumber(String value) {
		StringBuffer result = new StringBuffer();
		Pattern regex = Pattern.compile("\\{(.*?)\\}");
		Matcher matcher = regex.matcher(value);
		while (matcher.find()) {
			Integer key = rule.get(matcher.group(0));
			if (key != null) {
				matcher.appendReplacement(result, converValue(key));
			}
		}
		matcher.appendTail(result);
		return result.toString();
	}

	/**
	 * 只替换流水号（{NO}）
	 * 
	 * @param value
	 *            替换的值
	 * @param curValue
	 *            当前流水号
	 * @param length
	 *            流水号的长度
	 * @return
	 */
	public static String replaceNo(String value, Integer curValue, int length) {
		return value.replace(idNumber, leftPad(curValue, length));
	}

	/**
	 * 只替换随机数（{RN},{RA},{RAN}等）
	 * 
	 * @param value
	 *            替换的值
	 * @param length
	 *            替换随机数的长度
	 * @return
	 */
	public static String replaceRandom(String value, int length) {
		StringBuffer result = new StringBuffer();
		Pattern regex = Pattern.compile("\\{(.*?)\\}");
		Matcher matcher = regex.matcher(value);
		while (matcher.find()) {
			Integer key = random.get(matcher.group(0));
			if (key != null) {
				matcher.appendReplacement(result, converRandom(key, length));
			}
		}
		matcher.appendTail(result);
		return result.toString();
	}

	/**
	 * 数值转换字符串
	 * 
	 * @param value
	 *            字符串
	 * @return
	 */
	private static String toString(int value) {
		return String.valueOf(value);
	}

	/**
	 * 填补满2位
	 * 
	 * @param value
	 *            补位的值
	 * @return
	 */
	private static String leftPad2(int value) {
		return leftPad(value, 2);
	}

	/**
	 * 最后2位
	 * 
	 * @param value
	 * @return
	 */
	private static String right2(int value) {
		return right(value, 2);
	}

	/**
	 * <pre>
	 * 根据当前的值 不够显示长度  左补足位数
	 * </pre>
	 * 
	 * @param value
	 *            当前的数值。
	 * @param size
	 *            填补长度
	 * @return
	 */
	private static String leftPad(int value, int size) {
		return StringUtils.leftPad(toString(value), size, "0");

	}

	/**
	 * 截取右边的位数
	 * 
	 * @param value
	 * @param len
	 * @return
	 */
	private static String right(int value, int len) {
		return StringUtils.right(toString(value), len);
	}

	/**
	 * 产生长名称
	 * 
	 * @param calendar
	 * @param field
	 * @return
	 */
	private static String toDisplayNameLong(Calendar cal, int field) {
		return cal.getDisplayName(field, Calendar.LONG, Locale.ENGLISH);
	}

	/**
	 * 产生短名称
	 * 
	 * @param calendar
	 * @param field
	 * @return
	 */
	private static String toDisplayNameShort(Calendar cal, int field) {
		return cal.getDisplayName(field, Calendar.SHORT, Locale.ENGLISH);
	}

	/**
	 * 取得随机的数字
	 * 
	 * @param count
	 * @return (String)随机的数字
	 */
	public static String randomNumeric(int count) {
		return RandomStringUtils.randomNumeric(count);
	}

	/**
	 * 随机的字母
	 * 
	 * @param count
	 *            长度
	 * @return (String)随机的字母
	 */
	public static String randomAlphabetic(int count) {
		return RandomStringUtils.randomAlphabetic(count);
	}

	/**
	 * 随机的字母和数字
	 * 
	 * @param count
	 *            长度
	 * @return (String)随机的字母和数字
	 */
	public static String randomAlphaNumeric(int count) {
		return RandomStringUtils.randomAlphanumeric(count);
	}

	/**
	 * 获取随机数（）
	 * 
	 * @param digit
	 *            长度
	 * @return (Long) 随机数
	 */
	public static long randomNumber(int digit) {
		return Math.round(Math.random() * pow10(digit));
	}

	/**
	 * 位数*10倍数
	 * 
	 * @param digit
	 * @return
	 */
	private static long pow10(int digit) {
		long result = 1L;
		while (digit-- > 0) {
			result *= 10L;
		}
		return result;
	}

	// 测试的方法
	public static void main(String[] args) {
		// int year = cal.get(Calendar.YEAR);
		// int month = cal.get(Calendar.MONTH);
		// int date =cal.get(Calendar.DATE)
		// int hour =cal.get(Calendar.HOUR)//HOUR 用于 12 小时制时钟 (0 - 11)。
		// int hour =cal.get(Calendar.HOUR_OF_DAY)//HOUR_OF_DAY 用于 24 小时制时钟
		// int MINUTE = cal.get(Calendar.MINUTE) 分
		// Calendar.SECOND 秒
		// Calendar.MILLISECOND
		// DAY_OF_WEEK
		// cal.getDisplayName(Calendar.AM_PM, Calendar.LONG, Locale.ENGLISH)
		// /Calendar calendar = Calendar.getInstance();
		// System.out.println(toDisplayNameLong(calendar, Calendar.AM_PM));

		// System.out.println(calendar.get(Calendar.ERA));
		// for (int i = 0; i < 1000; i++) {
		// System.out.println(random(10));
		// }
		// String str = "{yyyy}{yy}TT{SS}";
		// str = replaceNumber(str);
		//
		// System.out.println(str);

		// for (String string : matchList) {
		// System.out.println(string);
		// }
		for (int i = 0; i < 1000; i++) {
			System.out.println(RandomStringUtils.randomAlphabetic(5));
		}

	}

}
