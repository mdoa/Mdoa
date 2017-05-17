package com.htsoft.oa.vo;

import org.jsoup.nodes.Element;

/**
 * 字段元素与字段名键值对
 * @author zxh
 *
 */
public class Pair {
	/**
	 * 解析HTML的元素
	 */
	private Element el;
	/**
	 * 字段名称
	 */
	private String fieldName = "";

	public Pair(Element el, String fieldName) {
		this.el = el;
		this.fieldName = fieldName;
	}

	public Element getEl() {
		return el;
	}

	public void setEl(Element el) {
		this.el = el;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

}