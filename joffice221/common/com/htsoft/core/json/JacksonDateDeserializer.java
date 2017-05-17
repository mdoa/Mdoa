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
 * json由日期字符串转换为java日期对象时做的转换(yyyy-MM-dd)
 * 
 * <pre>
 *  例子：
 * 	&#064;JsonDeserialize(using = JacksonDateDeserializer.class)
 * 	protected java.util.Date time;
 * </pre>
 * 
 * @author zxh
 * @date 2012-7-25 下午11:36:33
 */
public class JacksonDateDeserializer extends JsonDeserializer<Date> {
	private static final Logger logger = LoggerFactory
			.getLogger(JacksonDateDeserializer.class);

	@Override
	public Date deserialize(JsonParser parser, DeserializationContext arg1)
			throws IOException, JsonProcessingException {
		try {
			return DateFormatUtil.parse(parser.getText(),
					Constants.DATE_FORMAT_YMD);
		} catch (ParseException e) {
			logger.error("ParseException: ", e);
		}
		return null;
	}

}