package com.mdoa.user.controller;

import java.text.SimpleDateFormat;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.user.model.UserInfo;
import com.mdoa.user.service.UserService;
import com.mdoa.util.Md5Util;


@Controller
@RequestMapping("/user")
public class UserController extends BaseController{
	
	@Autowired
	private UserService userService;
	
	@ResponseBody
	@RequestMapping("/login.do")
	public String login(UserInfo userInfo, String reamberMe, HttpServletRequest request, HttpServletResponse response){
		try{
			userService.login(userInfo, request);
			if(reamberMe.equals("1")){
				Cookie userAccountCookie = new Cookie("userAccount",userInfo.getUserAccount());
				Cookie passwordCookie = new Cookie("password",Md5Util.getMd5Str(userInfo.getPassword()));
				response.addCookie(userAccountCookie);
				response.addCookie(passwordCookie);
			}
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
	/**
	 * 修改密码
	 * @param newPassword
	 */
	@ResponseBody
	@RequestMapping("/updatePassword.do")
	public String updatePassword(String oldPassword,String newPassword, HttpServletRequest request){
		try{
			//Subject subject = SecurityUtils.getSubject();
			UserInfo userInfo = getUser(request);
			String nowPassword=userInfo.getPassword();
			userService.updatePassword(oldPassword,newPassword,nowPassword,userInfo.getUserId(), request);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
	/**
	 * 获取用户的权限接口
	 */
	@ResponseBody
	@RequestMapping("getPermissions.do")
	public String getPermissions(HttpServletRequest request){
		try{
			UserInfo userInfo = getUser(request);
			Set<String> set = userInfo.getPermissions();
			Gson gson = new Gson();
			return gson.toJson(set);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 获取用户的权限接口
	 */
	@ResponseBody
	@RequestMapping("getUserInfo.do")
	public String getUserInfo(HttpServletRequest request){
		try{
			UserInfo userInfo = getUser(request);
			Gson gson = new Gson();
			return gson.toJson(userInfo);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 退出登录
	 */
	@ResponseBody
	@RequestMapping("/outLogin.do")
	public String outLogin(HttpServletRequest request){
		try {
			request.setAttribute("userInfo", null);
			return Constant.SUCCESS_CODE;
		} catch (Exception e) {
			return Constant.SERVER_ERROR_CODE;
		}
		
	}
}
