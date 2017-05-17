package com.htsoft.core.util;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.collection.PersistentBag;
import org.hibernate.proxy.map.MapProxy;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.htsoft.core.Constants;
import com.htsoft.core.json.SqlTimestampConverter;
import com.htsoft.core.model.DynaModel;
import com.htsoft.oa.util.FlowUtil;

import flexjson.JSONSerializer;
import flexjson.transformer.DateTransformer;

public class JsonUtil {
	private static Log logger = LogFactory.getLog(JsonUtil.class);

	/**
	 * 取得json的格式化器，用JSONSerializer可以解决延迟加载的问题
	 * 
	 * @param dateFields
	 *            　日期字段
	 * @return
	 */
	public static JSONSerializer getJSONSerializer(String... dateFields) {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"),
				dateFields);
		return serializer;
	}

	public static JSONSerializer getJSONSerializer() {
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude("*.class");
		serializer.transform(new DateTransformer("yyyy-MM-dd HH:mm:ss"),
				Date.class);
		return serializer;
	}

	/**
	 * List to json string
	 * 
	 * @param List
	 * @return
	 */
	public static String listEntity2Json(List<Map<String, Object>> list,
			String entityName) {

		StringBuffer sb = new StringBuffer("[");
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				Map m = list.get(i);
				String entStr = mapEntity2Json(m, entityName);
				sb.append(entStr).append(",");
			}
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");

		return sb.toString();
	}

	/**
	 * Map to json string
	 * 
	 * @param map
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String mapEntity2Json(Map<String, Object> mapData,
			String entityName) {

		StringBuffer sb = new StringBuffer("{");
		Gson gson = new GsonBuilder().serializeNulls().create();
		DynaModel dynaModel = FlowUtil.DynaModelMap.get(entityName);
		Iterator<?> entryIt = mapData.entrySet().iterator();
		int i = 0;
		while (entryIt.hasNext()) {
			Map.Entry entry = (Map.Entry) entryIt.next();
			String key = (String) entry.getKey();
			Object val = entry.getValue();

			if (key.equals(entityName))
				continue;
			if (val instanceof MapProxy)
				continue;
			if (val instanceof Map)
				continue;

			if (val instanceof PersistentBag) {
				int j = 0;
				// 找到其对应的key
				String subEntityName = key.substring(0, key.length() - 1);
				sb.append(key).append(":[");
				Iterator<?> bagIt = ((PersistentBag) val).iterator();
				while (bagIt.hasNext()) {
					if (j++ > 0)
						sb.append(",");
					Map<String, Object> map = (Map<String, Object>) bagIt
							.next();
					sb.append(mapEntity2Json(map, subEntityName));
				}
				sb.append("],");
			} else if (val instanceof Date) {
				String formatStyle = dynaModel.getFormat((String) key);
				if (formatStyle == null)
					formatStyle = Constants.DATE_FORMAT_FULL;

				String result = DateFormatUtil.format((Date) val, formatStyle);
				sb.append(key).append(":").append(gson.toJson(result))
						.append(",");

			} else {
				sb.append(key).append(":").append(gson.toJson(val)).append(",");
			}
			i++;
		}
		if (i > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * gson 格式化数据列表
	 * 
	 * @param datas
	 *            数据列表
	 * @param gson
	 *            Gson对象
	 * @param type
	 *            元数据类型
	 * @param fields
	 *            延迟加载的字段
	 * @return
	 */
	public static String jsonSerilize(List datas, Gson gson, Type type,
			String... fields) {
		// 触发get方法以使得外键实体能获取以方便不需要的外键映射
		if (fields != null) {
			for (Object obj : datas) {
				for (String field : fields) {
					String methodName = "get"
							+ FunctionsUtil.makeFirstLetterUpperCase(field);
					try {
						Method getMethod = obj.getClass().getMethod(methodName,
								null);
						getMethod.invoke(obj, null);
					} catch (Exception ex) {
						logger.error(ex.getMessage());
					}
				}
			}
		}
		return gson.toJson(datas, type);
	}

	/**
	 * 取得通用的Gson格式化，如日期格式 yyyy-MM-dd HH:mm:ss
	 * 
	 * @return
	 */
	public static Gson getGson() {
		GsonBuilder builder = new GsonBuilder().serializeNulls().setDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		builder.registerTypeAdapter(Timestamp.class,
				new SqlTimestampConverter());
		Gson gson = builder.create();
		return gson;
	}

}
