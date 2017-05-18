package com.mdoa.base.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdoa.base.dao.BaseDao;
import com.mdoa.user.model.UserInfo;

@Service
@Transactional
public class BaseService {
	
	@Autowired
	private BaseDao baseDao;
	
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
	
	protected String getuuid(){
		return baseDao.getuuid();
	}
}
