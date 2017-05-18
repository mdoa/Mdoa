package com.mdoa.framework.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.base.service.BaseService;
import com.mdoa.constant.Constant;
import com.mdoa.framework.bo.LeadForm;
import com.mdoa.framework.model.Lead;
import com.mdoa.framework.service.LeadService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;

@RestController
@RequestMapping("lead")
public class LeadController extends BaseService{
	
	@Autowired
	private LeadService leadService;
	
	/**
	 * 获取员工上级
	 * @param userId 用户的Id
	 * @return 上级信息
	 */
	@RequestMapping("getLeader.do")
	public String getLeader(LeadForm form){
		try{
			UserInfo user = leadService.getLeader(form);
			Gson gson = new Gson();
			return gson.toJson(user);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 获取员工的下级 
	 * @param userId 用户的Id
	 * @return 下级列表
	 */
	@RequestMapping("getLower.do")
	public String getLower(LeadForm form){
		try{
			PageModel<UserInfo> users = leadService.getLower(form);
			Gson gson = new Gson();
			return gson.toJson(users);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 为员工添加上级
	 * @param form userId，leadId
	 * @return 操作是否成功
	 */
	@RequestMapping("addLeader.do")
	public String addLeader(LeadForm form){
		try{
			leadService.addLeader(form);
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
	 * 为员工添加下级
	 * @param form userId    lowerIds ","逗号分隔
	 * @return
	 */
	@RequestMapping("addLower.do")
	public String addLower(LeadForm form, HttpServletRequest request){
		try{
			leadService.addLower(form, request);
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
	 * 删除员工上级
	 * @param form userId leaderId
	 * @return 
	 */
	@ResponseBody
	@RequestMapping("deleteLeader.do")
	public String deleteLeader(LeadForm form, HttpServletRequest request){
		try{
			UserInfo userInfo = getUser(request);
			form.setUpdateUserId(userInfo.getUserId());
			form.setUpdateUserName(userInfo.getUserName());
			leadService.deleteLeader(form);
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
	 * 删除员工下级
	 * @param form userId    lowers “,”逗号分隔
	 * @return
	 */
	@RequestMapping("deleteLower.do")
	public String deleteLower(LeadForm form, HttpServletRequest request){
		try{
			UserInfo userInfo = getUser(request);
			form.setUpdateUserId(userInfo.getUserId());
			form.setUpdateUserName(userInfo.getUserName());
			leadService.deleteLower(form);
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
	 * 获取员工上下级关系列表
	 * @param form 上级姓名leadName 用户姓名 userName
	 * @return 
	 */
	@RequestMapping("getLeadLowerList.do")
	public String getLeadLowerList(LeadForm form){
		try{
			PageModel<Lead> page = leadService.getLeadLowerList(form);
			Gson gson = new Gson();
			return gson.toJson(page);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
}
