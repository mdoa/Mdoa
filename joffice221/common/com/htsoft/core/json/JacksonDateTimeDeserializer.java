package com.htsoft.core.json;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.htsoft.core.Constants;
import com.htsoft.core.util.DateFormatUtil;

/**
 * json由日期字符串转换为java日期对象时做的转换<br>(yyyy-MM-dd HH:mm:ss)
 * 
 * <pre>
 *  例子：
 * 	&#064;JsonDeserialize(using = JacksonDateTimeDeserializer.class)
 * protected java.util.Date time;
 * </pre>
 * 
 * @author zxh
 * @date 2012-7-25 下午11:36:33
 */
public class JacksonDateTimeDeserializer extends JsonDeserializer<Date> {
	private static final Logger logger = LoggerFactory
			.getLogger(JacksonDateTimeDeserializer.class);

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		try {
			return DateFormatUtil.parse(parser.getText(),
					Constants.DATE_FORMAT_FULL);
		} catch (ParseException e) {
			logger.error("ParseException: ", e);
		}
		return null;
	}
}