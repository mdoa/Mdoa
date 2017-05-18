package com.mdoa.user.controller;


import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.constant.FileConstant;
import com.mdoa.personnel.model.PersonEducation;
import com.mdoa.personnel.model.PersonTrain;
import com.mdoa.personnel.model.PersonWork;
import com.mdoa.user.model.UserInfo;
import com.mdoa.user.model.UserInfoOther;
import com.mdoa.user.model.UserTransfer;
import com.mdoa.user.service.UserInfoService;
import com.mdoa.util.FileUtil;

import sun.misc.BASE64Decoder;


@RestController
@RequestMapping("/userInfo")
public class UserInfoController extends BaseController{
	
	@Autowired
	private UserInfoService userInfoService;
	
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
			userInfoService.writeToExcel(userInfo, jsonString, filePath);
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
	
	/**
	 * 根据前端传入的信息，添加用户基本信息，然后返回用户Id
	 * @param userInfo
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/saveUserInfo.do")
	public String saveUserInfo(UserInfo userInfo, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			userInfo.setCreateUserId(user.getUserId());
			userInfo.setCreateUserName(user.getUserName());
			String string = userInfoService.saveUserInfo(userInfo);
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
	 * 根据前端传输来的用户Id和工作信息，将员工工作记录情况保存在数据库中
	 */
	@RequestMapping("/saveWork.do")
	public String saveWork(PersonWork personWork, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			personWork.setCreateUserId(user.getUserId());
			personWork.setCreateUserName(user.getUserName());
			return userInfoService.saveWork(personWork);
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 添加培训记录
	 */
	@RequestMapping("/saveTrain.do")
	public String saveTrain(PersonTrain personTrain, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			personTrain.setCreateUserId(user.getUserId());
			personTrain.setCreateUserName(user.getUserName());
			return userInfoService.saveTrain(personTrain);
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据前端传输来的用户Id和工作信息，将员工工作记录情况保存在数据库中
	 */
	@RequestMapping("/saveEdu.do")
	public String saveEdu(PersonEducation personEducation, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			personEducation.setCreateUserId(user.getUserId());
			personEducation.setCreateUserName(user.getUserName());
			return userInfoService.saveEdu(personEducation);
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
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
			userInfoService.updateUserInfo(userInfo);
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
	 * 修改工作信息
	 */
	@RequestMapping("/updateWork.do")
	public String updateWork(PersonWork personWork, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			personWork.setUpdateUserId(user.getUserId());
			personWork.setUpdateUserName(user.getUserName());
			userInfoService.updateWork(personWork);
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
	 * 修改教育信息
	 */
	@RequestMapping("/updateEdu.do")
	public String updateEdu(PersonEducation personEducation, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			personEducation.setUpdateUserId(user.getUserId());
			personEducation.setUpdateUserName(user.getUserName());
			userInfoService.updateEdu(personEducation);
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
	 * 查询基本信息
	 */
	@RequestMapping("/checkedIdCard.do")
	public String selectUserInfoById(UserInfo userInfo){
		try{
			Gson gson = new Gson();
			return gson.toJson(userInfoService.selectUserInfoById(userInfo));
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 查询基本信息
	 */
	@RequestMapping("/selectUserInfo.do")
	public String selectUserInfo(UserInfo userInfo){
		try{
			Gson gson = new Gson();
			return gson.toJson(userInfoService.selectUserInfo(userInfo));
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 查询工作信息
	 */
	@RequestMapping("/selectWork.do")
	public String selectWork(PersonWork personWork){
		try{
			Gson gson = new Gson();
			return gson.toJson(userInfoService.selectWork(personWork));
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 查询教育信息
	 */
	@RequestMapping("/selectEdu.do")
	public String selectEdu(PersonEducation personEducation, HttpServletRequest request){
		try{
			Gson gson = new Gson();
			return gson.toJson(userInfoService.selectEdu(personEducation));
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 重置密码
	 */
	@RequestMapping("/resetPassword.do")
	public String resetPassword(UserInfo userInfo, HttpServletRequest request){
		try{
			System.out.println(11111);
			UserInfo user = getUser(request);
			userInfo.setUpdateUserId(user.getUserId());
			userInfo.setUpdateUserName(user.getUserName());
			userInfoService.resetPassword(userInfo);
			return Constant.SUCCESS_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 角色赋予
	 */
	@RequestMapping("/roleGive.do")
	public String roleGive(UserInfo userInfo, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			userInfo.setCreateUserId(user.getUserId());
			userInfo.setUpdateUserId(user.getUserId());
			userInfo.setCreateUserName(user.getUserName());
			userInfo.setUpdateUserName(user.getUserName());
			userInfoService.roleGive(userInfo);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 员工调动
	 */
	@RequestMapping("/userTransfer.do")
	public String userTransfer(UserTransfer userTransfer, HttpServletRequest request){
		try{
			userInfoService.userTransfer(userTransfer, request);
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
	 * 员工批量调动
	 */
	@RequestMapping("/userTransfers.do")
	public String userTransfers(UserTransfer userTransfer, HttpServletRequest request){
		try{
			userInfoService.userTransfers(userTransfer, request);
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
	 * 查询员工调动记录
	 */
	@RequestMapping("/selectUserTransfer.do")
	public String selectUserTransfer(UserTransfer userTransfer, HttpServletRequest request){
		try{
			Gson gson = new Gson();
			return gson.toJson(userInfoService.selectUserTransfer(userTransfer, request));
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 修改调动记录表
	 */
	@RequestMapping("/updateUserTransfer.do")
	public String updateUserTransfer(UserTransfer userTransfer, HttpServletRequest request){
		try{
			userInfoService.updateUserTransfer(userTransfer, request);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 删除人员调动记录
	 * @param tUserTransfer
	 * @return
	 */
	@RequestMapping("/deleteUserTransfer.do")
	public String deleteUserTransfer(UserTransfer userTransfer, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			userTransfer.setUpdateUserId(user.getUserId());
			userTransfer.setUpdateUserName(user.getUserName());
			userInfoService.deleteUserTransfer(userTransfer, request);
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
	 * 添加人员的其他信息
	 */
	@RequestMapping("/addOtherInfo.do")
	public String addOtherInfo(UserInfoOther userInfoOther){
		try{
			String id = userInfoService.addOtherInfo(userInfoOther);
			return id;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 修改人员的其他信息
	 */
	@RequestMapping("/updateOtherInfo.do")
	public String updateOtherInfo(UserInfoOther userInfoOther){
		try{
			userInfoService.updateOtherInfo(userInfoOther);
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
	 * 查询人员的其他信息
	 */
	@RequestMapping("/selectOtherInfo.do")
	public String selectOtherInfo(UserInfoOther userInfoOther){
		try{
			List<UserInfoOther> list = userInfoService.selectOtherInfo(userInfoOther);
			Gson gson = new Gson();
			return gson.toJson(list);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
}
