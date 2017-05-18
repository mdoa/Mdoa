package com.mdoa.base.controller;

import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mdoa.user.model.UserInfo;

public class BaseController {
	/**
	 * 获取当前登录的员工的信息
	 * @return
	 */
	protected UserInfo getUser(HttpServletRequest request){
		return (UserInfo)request.getSession().getAttribute("userInfo");
	}
	
	/**
	 * 获取session中的一个name中的数据
	 * @return
	 */
	protected Object getSession(HttpServletRequest request, String name){
		return request.getSession().getAttribute("key");
	}
	
	/**
	 * 获取在sesssion中更新一个name的数据
	 * @return
	 */
	protected void putSession(HttpServletRequest request, String name, Object value){
		request.getSession().setAttribute(name, value);
	}
	
	protected Gson getGson(){
		return new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
	}
}
