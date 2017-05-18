package com.mdoa.personnel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.personnel.bo.LeaveApplyForm;
import com.mdoa.personnel.bo.LeaveExamineForm;
import com.mdoa.personnel.bo.LeaveStreamForm;
import com.mdoa.personnel.model.LeaveApply;
import com.mdoa.personnel.model.LeaveStream;
import com.mdoa.personnel.service.LeaveService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;

@RestController
@RequestMapping("/leave")
public class LeaveController extends BaseController {

	@Autowired
	private LeaveService leaveService;

	/**
	 * 请假申请
	 * 
	 * @param leaveApplyForm
	 *            表单信息包括:startTime overTime examineUserId examineUserName
	 * @return
	 */
	@RequestMapping("/applyForLeave.do")
	public String applyForLeave(LeaveApplyForm leaveApplyForm, HttpServletRequest request) {
		try {
			 UserInfo userInfo= getUser(request);
			 leaveApplyForm.setUserId(userInfo.getUserId());
			 leaveApplyForm.setUserName(userInfo.getUserName());			 
			leaveService.applyForWelfare(leaveApplyForm);
			return Constant.SUCCESS_CODE;
		} catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}

	/**
	 * 查询请假申请信息
	 * 
	 * @param leaveApplyForm
	 * @return
	 */
	@RequestMapping("/findApplyByCondition.do")
	public String findApplyByCondition(LeaveApplyForm leaveApplyForm) {
		try {
			PageModel<LeaveApply> pageInfo = leaveService.findApplyByCondition(leaveApplyForm);
			Gson gson = new Gson();
			return gson.toJson(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 通过审核人id查询请假申请信息
	 * @param examineUserId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@RequestMapping("/findApplyByExamineUserId.do")
	public String findApplyByExamineUserId(String examineUserId, int pageNum, int pageSize) {
		try {
			PageModel<LeaveApply> pageInfo = leaveService.findApplyByExamineUserId(examineUserId,pageNum,pageSize);
			Gson gson = new Gson();
			return gson.toJson(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 查询待我审核流程
	 * @return
	 */
	@RequestMapping("findMyExamineStream.do")
	public String findMyExamineStream(HttpServletRequest request){
		try {
			LeaveApplyForm leaveApplyForm = new LeaveApplyForm();
			 UserInfo userInfo= getUser(request);
			leaveApplyForm.setExamineUserId(userInfo.getUserId());
			leaveApplyForm.setStreamType("1");
			PageModel<LeaveStreamForm> pageInfo = leaveService.findStreamByCondition(leaveApplyForm);
			Gson gson = new Gson();
			return gson.toJson(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 查询待我审核流程
	 * @return
	 */
	@RequestMapping("findMyExamineStream1.do")
	public String findMyExamineStream1(LeaveApplyForm leaveApplyForm,HttpServletRequest request){
		try {
			 UserInfo userInfo= getUser(request);
			leaveApplyForm.setExamineUserId(userInfo.getUserId());
			leaveApplyForm.setStreamType("1");
			PageModel<LeaveStreamForm> pageInfo = leaveService.findStreamByCondition(leaveApplyForm);
			Gson gson = new Gson();
			return gson.toJson(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 条件查询请假审核流信息
	 * @param leaveApplyForm
	 * @return
	 */
	@RequestMapping("/findStreamByCondition.do")
	public String findStreamByCondition(LeaveApplyForm leaveApplyForm){	
		try {
			PageModel<LeaveStreamForm> pageInfo = leaveService.findStreamByCondition(leaveApplyForm);
			Gson gson = new Gson();
			return gson.toJson(pageInfo);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 通过请假申请但仍需审核的方法
	 * @return
	 */
	@RequestMapping("/passLeaveApply.do")
	public String passLeaveApply(LeaveExamineForm leaveExamineForm){
		try{
			leaveService.passLeaveApply(leaveExamineForm);
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
	 * 通过请假申请且无需再审核的方法
	 * @return
	 */
	@RequestMapping("/finallyPassLeaveApply.do")
	public String finallyPassLeaveApply(LeaveExamineForm leaveExamineForm){
		try{
			leaveService.finallyPassLeaveApply(leaveExamineForm);
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
	 * 驳回请假申请的方法
	 * @param leaveExamineForm
	 * @return
	 */
	@RequestMapping("/rejectLeaveApply.do")
	public String rejectLeaveApply(LeaveExamineForm leaveExamineForm, HttpServletRequest request){
		try{
			UserInfo userInfo = getUser(request);
			leaveExamineForm.setUserId(userInfo.getUserId());
			leaveExamineForm.setUserName(userInfo.getUserName());
			leaveService.rejectLeaveApply(leaveExamineForm);
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
	 * 备案人驳回且需再审核的方法
	 * @param leaveExamineForm
	 */
	@RequestMapping("/rejectRecordApply.do")
	public String rejectRecordApply(LeaveExamineForm leaveExamineForm, HttpServletRequest request){
		try{
			UserInfo userInfo = getUser(request);
			leaveExamineForm.setUserId(userInfo.getUserId());
			leaveExamineForm.setUserName(userInfo.getUserName());			
			//因为备案人认为需再审核时，实际是对备案流程的驳回，故更改状态
			leaveExamineForm.setExamineStatus("4");
			leaveService.passLeaveApply(leaveExamineForm);
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
	 * 备案人通过请假申请并备案
	 * @param leaveExamineForm
	 * @return
	 */
	@RequestMapping("/recordLeaveApply.do")
	public String recordLeaveApply(LeaveExamineForm leaveExamineForm, HttpServletRequest request){
		try{
			UserInfo userInfo = getUser(request);
			leaveExamineForm.setUserId(userInfo.getUserId());
			leaveExamineForm.setUserName(userInfo.getUserName());
			leaveService.recordLeaveApply(leaveExamineForm);
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
	 * 申请人撤回请假申请的方法
	 * @param leaveExamineForm
	 * @return
	 */
	@RequestMapping("/withdrawLeaveApply.do")
	public String withdrawLeaveApply(LeaveExamineForm leaveExamineForm, HttpServletRequest request){
		try{
			UserInfo userInfo = getUser(request);
			leaveExamineForm.setUserId(userInfo.getUserId());
			leaveExamineForm.setUserName(userInfo.getUserName());
			leaveService.withdrawLeaveApply(leaveExamineForm);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
}
