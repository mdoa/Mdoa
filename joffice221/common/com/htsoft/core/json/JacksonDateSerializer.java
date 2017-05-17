package com.htsoft.core.json;

import java.io.IOException;
import java.util.Date;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.htsoft.core.Constants;
import com.htsoft.core.util.DateFormatUtil;

/**
 * 
 * java日期对象经过Jackson库转换成JSON日期格式化（yyyy-MM-dd）自定义类
 * 
 * <pre>
 *  例子：
 * 	&#064;JsonSerialize(using = JacksonDateSerializer.class)
 * 	protected java.util.Date time;
 * </pre>
 * 
 * @author zxh
 * @date 2012-7-25 下午11:36:33
 */
public class JacksonDateSerializer extends JsonSerializer<Date> {
	
	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		jgen.writeString(DateFormatUtil.format(value,
				Constants.DATE_FORMAT_YMD));
	}
}