package com.htsoft.oa.core.mail;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;

/**
 * 辅助类为发件人信息验证类
 * 
 * @author zxh
 * @date 2012-11-13 下午2:48:25
 */
public class MyAuthenticator extends Authenticator {
	private String username;
	private String password;

	/**
	 * 
	 * @author zxh
	 * @date 2012-11-13 下午2:48:25
	 * @param username
	 * @param password
	 */
	public MyAuthenticator(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	protected PasswordAuthentication getPasswordAuthentication() {
		return new PasswordAuthentication(username, password);
	}
}