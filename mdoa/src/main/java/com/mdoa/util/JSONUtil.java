package com.mdoa.util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;


public class JSONUtil{
	
	/**
	 * json字符串转List的
	 * @param json
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> jsonToList(String jsonString){
        Gson gson = new Gson();
        List<T> list = gson.fromJson(jsonString, new TypeToken<List<T>>(){}.getType());
        return list;
    }
	
	public static <T> List<T> jsonToList(String jsonString, Class<T[]> clazz){
        Gson gson = new Gson();
        T[] array = gson.fromJson(jsonString, clazz);
        return Arrays.asList(array);
    }
	
	public static List<ExcelModel> jsonToExcelModelList(String jsonString){
		
		//创建Gson对象
	    Gson gson = new Gson();
	    //创建JsonParser
	    JsonParser parser = new JsonParser();
	    //通过JsonParser可以把json字符串解析成一个JsonElement对象
	    JsonElement jsonElement = parser.parse(jsonString);

	    //把JsonElement对象转换成JsonObject
	    JsonObject jsonObject = null;
	    if(jsonElement.isJsonObject()){
	        jsonObject = jsonElement.getAsJsonObject();
	    }

	    //把JsonElement转换成JsonArray
	    JsonArray jsonArray = null;
	    if(jsonElement.isJsonArray()){
	        jsonArray = jsonElement.getAsJsonArray();
	    }

	    //遍历JsonArray对象
	    Iterator<JsonElement> iterator = jsonArray.iterator();
	    List<ExcelModel> excelModels = new ArrayList<>();
	    while(iterator.hasNext()){
	        JsonElement element = iterator.next();
	        //JsonElement转换为ExcelModel对象
	        ExcelModel excelModel= gson.fromJson(element, ExcelModel.class);
	        excelModels.add(excelModel);
	    }
	    return excelModels;
	}
}
