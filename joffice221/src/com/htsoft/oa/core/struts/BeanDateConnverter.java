package com.htsoft.oa.core.struts;

import org.apache.commons.beanutils.Converter;
import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.htsoft.core.Constants;

/**
 * 
 * <B>
 * <P>
 * EST-BPM -- http://www.Mendersoft.com
 * </P>
 * </B> <B>
 * <P>
 * Copyright (C) 2008-2010 GuangZhou HongTian Software Company (杭州梦德软件有限公司)
 * </P>
 * </B> <B>
 * <P>
 * description:
 * </P>
 * </B>
 * <P>
 * </P>
 * <P>
 * product:joffice
 * </P>
 * <P>
 * 用于进行Bean的日期属性类型转化
 * </P>
 * 
 * @see com.htsoft.core.struts.BeanDateConnverter <P>
 *      </P>
 * @author
 * @version V1
 * @create: 2010-12-28上午10:57:52
 */
public class BeanDateConnverter implements Converter {
	private static final Log logger = LogFactory
			.getLog(BeanDateConnverter.class);

	public BeanDateConnverter() {
	}

	@SuppressWarnings("rawtypes")
	@Override
	public Object convert(Class arg0, Object value) {
		logger.debug("conver " + value + " to date object");
		String dateStr = value.toString();
		dateStr = dateStr.replace("T", " ");
		try {
			return DateUtils.parseDate(dateStr, Constants.ACCEPT_DATE_FORMATS);
		} catch (Exception ex) {
			logger.debug("parse date error:" + ex.getMessage());
		}
		return null;
	}
}