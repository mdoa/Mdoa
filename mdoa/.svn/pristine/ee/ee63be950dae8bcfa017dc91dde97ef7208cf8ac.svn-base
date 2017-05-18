package com.mdoa.user.controller;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.constant.FileConstant;
import com.mdoa.user.model.UserInfo;
import com.mdoa.user.service.UserInviteInfoService;
import com.mdoa.util.FileUtil;


@RestController
@RequestMapping("/userInviteInfo")
public class UserInviteInfoController extends BaseController{
	
	@Autowired
	private UserInviteInfoService userInviteInfoService;
	
	/**
	 * 查询基本信息
	 */
	@RequestMapping("/selectUserInfo.do")
	public String selectUserInfo(UserInfo userInfo){
		try{
			Gson gson = new Gson();
			return gson.toJson(userInviteInfoService.selectUserInfo(userInfo));
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 修改基本信息
	 */
	@RequestMapping("/updateUserInfo.do")
	public String updateUserInfo(UserInfo userInfo, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			userInfo.setUpdateUserId(user.getUserId());
			userInfo.setUpdateUserName(user.getUserName());
			userInfo.setCreateUserId(user.getUserId());
			userInfo.setCreateUserName(user.getUserName());
			userInviteInfoService.updateUserInfo(userInfo);
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
	 * 根据前端传入的信息，添加用户基本信息，然后返回用户Id
	 * @param userInfo
	 * @return
	 */
	@RequestMapping("/saveUserInfo.do")
	public String saveUserInfo(UserInfo userInfo, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			userInfo.setCreateUserId(user.getUserId());
			userInfo.setCreateUserName(user.getUserName());
			String string = userInviteInfoService.saveUserInfo(userInfo);
			return string;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 录用员工
	 */
	@RequestMapping("/hireUser.do")
	public String hireUser(UserInfo userInfo, HttpServletRequest request) {
		try{
			userInviteInfoService.hireUser(userInfo, request);
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
	 * 导出人员数据到Excel
	 * 
	 * @param userInfo
	 * @param jsonString
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/writeToExcel.do")
	public String writeToExcel(UserInfo userInfo,String jsonString,HttpServletRequest request,HttpServletResponse response){
		try {
			String filePath = FileConstant.FILE_PATH + FileConstant.SHORT_TIME_EXCEL + "/" +new Date().getTime() + ".xls";
			userInviteInfoService.writeToExcel(userInfo, jsonString, filePath);
			FileUtil.download(request, response, filePath, new Date().getTime()+".xls");
			return Constant.SUCCESS_CODE;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
}
