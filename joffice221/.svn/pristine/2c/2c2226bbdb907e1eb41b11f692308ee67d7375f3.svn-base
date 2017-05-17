package com.htsoft.core.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.jsp.PageContext;

import org.springframework.security.ui.rememberme.TokenBasedRememberMeServices;

/**
 * Cookie的常用操作
 * 
 * @author zxh
 * @version
 */

public class CookieUtil {
	/**
	 * 往客户端写入Cookie 说明: maxAge:(单位秒) 0:删除Cookie -1:页面关闭时删除cookie
	 * 
	 * @param name
	 *            String
	 * @param value
	 *            String
	 * @param maxAge
	 *            int
	 * @param context
	 *            PageContext
	 */
	public static void addCookie(String name, String value, int maxAge,
			PageContext context) {
		HttpServletResponse response = (HttpServletResponse) context
				.getResponse();
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 往客户端写入Cookie 说明: maxAge:(单位秒) 0:删除Cookie -1:页面关闭时删除cookie
	 * 
	 * @param name
	 *            String
	 * @param value
	 *            String
	 * @param maxAge
	 *            int
	 * @param response
	 *            HttpServletResponse
	 */
	public static void addCookie(String name, String value, int maxAge,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(maxAge);
		response.addCookie(cookie);
	}

	/**
	 * 写入cookie 当页面关闭是删除cookie。
	 * 
	 * @param name
	 * @param value
	 * @param context
	 */
	public static void addCookie(String name, String value, PageContext context) {
		HttpServletResponse response = (HttpServletResponse) context
				.getResponse();
		Cookie cookie = new Cookie(name, value);
		cookie.setMaxAge(24 * 60 * 60);
		response.addCookie(cookie);
	}

	/**
	 * 删除cookie
	 * 
	 * @param name
	 * @param context
	 */
	public static void delCookie(String name, PageContext context) {
		HttpServletResponse response = (HttpServletResponse) context
				.getResponse();
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/**
	 * 删除cookie
	 * 
	 * @param name
	 * @param response
	 */
	public static void delCookie(String name, HttpServletResponse response) {
		Cookie cookie = new Cookie(name, "");
		cookie.setMaxAge(0);
		response.addCookie(cookie);
	}

	/**
	 * 根据Cookie名取得Cookie的值. 如果cookie 为空 则返回 null;
	 * 
	 * @param name
	 *            String
	 * @param context
	 *            PageContext
	 * @return String
	 */
	public static String getValueByName(String name, PageContext context) {
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		Cookie cookies[] = request.getCookies();
		Cookie sCookie = null;
		String svalue = null;
		String sname = null;

		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length; i++) {
			sCookie = cookies[i];
			sname = sCookie.getName();
			if (sname.equals(name)) {
				svalue = sCookie.getValue();
				break;
			}
		}
		return svalue;
	}

	/**
	 * 根据cookie名称取得值
	 * 
	 * @param name
	 * @param request
	 * @return
	 */
	public static String getValueByName(String name, HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		Cookie sCookie = null;
		String svalue = null;
		String sname = null;

		if (cookies == null)
			return null;
		for (int i = 0; i < cookies.length; i++) {
			sCookie = cookies[i];
			sname = sCookie.getName();
			if (sname.equals(name)) {
				svalue = sCookie.getValue();
				break;
			}
		}
		return svalue;
	}

	/**
	 * 根据Cookie名判断Cookie是否存在.
	 * 
	 * @param name
	 *            String
	 * @param context
	 *            PageContext
	 * @return String
	 */
	public static boolean isExistByName(String name, PageContext context) {
		HttpServletRequest request = (HttpServletRequest) context.getRequest();
		Cookie cookies[] = request.getCookies();
		Cookie sCookie = null;
		String sname = null;
		boolean isExist = false;
		if (cookies == null)
			return false;
		for (int i = 0; i < cookies.length; i++) {
			sCookie = cookies[i];
			sname = sCookie.getName();
			if (sname.equals(name)) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}

	/**
	 * 根据Cookie名判断Cookie是否存在.
	 * 
	 * @param name
	 *            String
	 * @param request
	 *            HttpServletRequest
	 * @return String
	 */
	public static boolean isExistByName(String name, HttpServletRequest request) {
		Cookie cookies[] = request.getCookies();
		Cookie sCookie = null;
		String sname = null;
		boolean isExist = false;
		if (cookies == null)
			return false;
		for (int i = 0; i < cookies.length; i++) {
			sCookie = cookies[i];
			sname = sCookie.getName();
			if (sname.equals(name)) {
				isExist = true;
				break;
			}
		}
		return isExist;
	}
	
	/**
	 * 向客户端写入"SPRING_SECURITY_REMEMBER_ME_COOKIE"
	 * @param expiryTime 过期时间
	 * @param tokenValueBase64 令牌的base64编码
	 * @param request
	 * @param response
	 */
	public static void addCookie(long expiryTime,
			String tokenValueBase64, HttpServletRequest request,
			HttpServletResponse response) {
		Cookie cookie = new Cookie(
				TokenBasedRememberMeServices.SPRING_SECURITY_REMEMBER_ME_COOKIE_KEY,
				tokenValueBase64);
		cookie.setMaxAge((int) expiryTime); // 5 years
		cookie.setPath(org.springframework.util.StringUtils.hasLength(request
				.getContextPath()) ? request.getContextPath() : "/");
		response.addCookie(cookie);
	}

}
