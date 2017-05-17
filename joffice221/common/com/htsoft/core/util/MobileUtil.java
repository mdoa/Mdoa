package com.htsoft.core.util;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MobileUtil {
	/**
	 * 转换字段
	 * @param key
	 * @param label
	 * @return
	 */
	public static StringBuffer convertCol(String key,String label,String value,boolean isArea){
		StringBuffer sb = new StringBuffer();
		sb.append("{").append("\"key\":\"").append(key).append("\",");
		if(isArea){
			sb.append("\"xtype\":\"").append("fckeditor").append("\",");
		}
		sb.append("\"label\":\"").append(label).append("\",");
		sb.append("\"value\":\"").append(value).append("\"}");
		return sb;
	}
	
	/**
	 * 附件转换
	 * @param attach
	 * @return
	 */
	public static String convertAttach(String attach){
		String att = "";
		JSONArray jarr = JSONArray.fromObject(attach);
		for(Object o:jarr.toArray()){
			JSONObject j = JSONObject.fromObject(o);
			att += j.get("name")+",";
		}
		if(att.length()>0){att=att.substring(0,att.length()-1);}
		return att;
	}
}
