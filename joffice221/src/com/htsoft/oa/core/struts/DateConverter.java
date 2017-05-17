package com.htsoft.oa.core.struts;

import java.util.Date;
import java.util.Map;

import org.apache.commons.lang.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.util.StrutsTypeConverter;

import com.htsoft.core.Constants;
import com.htsoft.core.util.DateFormatUtil;

/**
 * 自定义类型转换器之日期转换
 * 
 * @author csx
 * 
 */
public class DateConverter extends StrutsTypeConverter {

	private static final Log logger = LogFactory.getLog(DateConverter.class);

	/**
	 * 
	 */
	public DateConverter() {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.util.StrutsTypeConverter#convertFromString(java.util
	 * .Map, java.lang.String[], java.lang.Class)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public Object convertFromString(Map context, String[] values, Class toClass) {
		if (logger.isDebugEnabled()) {
			logger.debug("converFromString....");
			if (values != null) {
				logger.debug("convert from  :" + values[0]);
			}
		}
		if (values[0] == null || values[0].trim().equals(""))
			return null;
		String dateStr = values[0];
		dateStr = dateStr.replace("T", " ");
		try {
			return DateUtils.parseDate(dateStr, Constants.ACCEPT_DATE_FORMATS);
		} catch (Exception ex) {
			logger.debug("parse date error:" + ex.getMessage());
		}

		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.apache.struts2.util.StrutsTypeConverter#convertToString(java.util
	 * .Map, java.lang.Object)
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public String convertToString(Map context, Object o) {
		if (o instanceof Date) {
			try {
				return DateFormatUtil.format((Date) o,
						Constants.DATE_FORMAT_FULL);
			} catch (RuntimeException e) {
				return "";
			}
		}
		return "";
	}

}
