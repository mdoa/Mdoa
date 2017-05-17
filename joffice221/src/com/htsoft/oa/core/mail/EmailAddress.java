package com.htsoft.oa.core.mail;

import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;

/**
 * 邮箱地址类
 * 
 * @author zxh
 * 
 */
public class EmailAddress {
	/** 地址 */
	protected String address;
	/** 名称 */
	protected String name;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public EmailAddress() {

	}

	public EmailAddress(String address, String name) {
		this.address = address;
		this.name = name;
	}

	public InternetAddress toInternetAddress() throws Exception {
		if (name != null && !name.trim().equals("")) {
			return new InternetAddress(address, MimeUtility.encodeWord(name,
					"utf-8", "Q"));
		}
		return new InternetAddress(address);
	}
}
