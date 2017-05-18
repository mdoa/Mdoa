package com.mdoa.personnel.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.constant.Constant;
import com.mdoa.personnel.bo.InviteApplyForm;
import com.mdoa.personnel.bo.InviteEngageUserForm;
import com.mdoa.personnel.model.InviteRecord;
import com.mdoa.personnel.service.InviteService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;

@RestController
@RequestMapping("/personnel")
public class InviteController {
	@Autowired
	private InviteService inviteService;
	
	
	/**
	 * 聘用人员
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/engageUser")
	public String engageUser(InviteEngageUserForm inviteEngageUserForm){
		try {
			inviteService.engageUser(inviteEngageUserForm);
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
	 * 发起招聘申请
	 * @param Invite实体类存放招聘计划的数据
	 * @return Invite实体类存放招聘计划的数据
	 */
	@ResponseBody
	@RequestMapping("/startInviteApply.do")
	public String startInviteApply(InviteApplyForm inviteApplyForm, HttpServletRequest request){
		try {
			inviteService.startInviteApply(inviteApplyForm, request);
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
	 * 按照条件进行查询招聘计划:(计划名和申请人)
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryInviteApply.do")
	public String queryInviteApply(InviteApplyForm inviteApplyForm){
		try {
			PageModel<InviteApplyForm> list=inviteService.queryInviteApply(inviteApplyForm);
			Gson gson=new Gson();
			String jsonString=gson.toJson(list);
			return jsonString;
		}  catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 查询我参与的审批流程
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryInviteApplyByMy.do")
	public String queryInviteApplyByMy(InviteApplyForm inviteApplyForm, HttpServletRequest request){
		try {
			PageModel<InviteApplyForm> list=inviteService.queryInviteApplyByMy(inviteApplyForm, request);
			Gson gson=new Gson();
			String jsonString=gson.toJson(list);
			return jsonString;
		}  catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 查询待备案流程
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryInviteRecordApply.do")
	public String queryInviteRecordApply(InviteApplyForm inviteApplyForm){
		try {
			System.out.println(inviteApplyForm.getExamineStatus());
			PageModel<InviteApplyForm> list=inviteService.queryInviteRecordApply(inviteApplyForm);
			Gson gson=new Gson();
			String jsonString=gson.toJson(list);
			return jsonString;
		}  catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 按照条件进行查询招聘计划流:(审批人和流类型)
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryStreamInviteApply.do")
	public String queryStreamInviteApply(InviteApplyForm inviteApplyForm){
		try {
			PageModel<InviteApplyForm> list=inviteService.queryStreamInviteApply(inviteApplyForm);
			Gson gson=new Gson();
			String jsonString=gson.toJson(list);
			return jsonString;
		}  catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 撤回计划
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/backInviteApply.do")
	public String backInviteApply(InviteApplyForm inviteApplyForm, HttpServletRequest request){
		try {
			inviteService.backInviteApply(inviteApplyForm, request);
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
	 * 删除计划(已近废除)
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteInviteApply.do")
	public String deleteInviteApply(InviteApplyForm inviteApplyForm){
		try {
			inviteService.deleteInviteApply(inviteApplyForm);
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
	 * 审批人进行审批(通过)，把审批内容进行update，并指定下一个审批人Id，把此人作为审批人添加审批流
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/examineUpdateAddStream.do")
	public String examineUpdateAddStream(InviteApplyForm inviteApplyForm){
		try {
			inviteService.examineUpdateAddStream(inviteApplyForm);
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
	 * 被驳回的情况
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/rejectInviteStream.do")
	public String rejectInviteStream(InviteApplyForm inviteApplyForm, HttpServletRequest request){
		try {
			inviteService.rejectInviteStream(inviteApplyForm, request);
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
	 * 进入备案流程
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/recordAddInviteStream.do")
	public String recordAddInviteStream(InviteApplyForm inviteApplyForm){
		try {
			inviteService.recordAddInviteStream(inviteApplyForm);
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
	 * 人事进行备案审批(通过)
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/recordUpdateInviteApply.do")
	public String recordUpdateInviteApply(InviteApplyForm inviteApplyForm, HttpServletRequest request){
		try {
			inviteService.recordUpdateInviteApply(inviteApplyForm, request);
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
	 * 人事驳回并提交给下一个人
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/rejectUpdateAddInviteApply.do")
	public String rejectUpdateAddInviteApply(InviteApplyForm inviteApplyForm, HttpServletRequest request){
		try {
			inviteService.rejectUpdateAddInviteApply(inviteApplyForm, request);
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
	 * 添加招聘记录信息
	 * @param inviteRecord
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addInviteRecord.do")
	public String addInviteRecord(InviteRecord inviteRecord){
		try {
			inviteService.addInviteRecord(inviteRecord);
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
	 * 删除招聘记录信息
	 * @param inviteRecord
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteInviteRecord.do")
	public String deleteInviteRecord(InviteRecord inviteRecord){
		try {
			inviteService.deleteInviteRecord(inviteRecord);
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
	 * 批量删除招聘记录信息
	 * @param inviteRecord
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteInviteRecordAll.do")
	public String deleteInviteRecordAll(InviteRecord inviteRecord){
		try {
			inviteService.deleteInviteRecordAll(inviteRecord);
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
	 * 修改招聘记录信息
	 * @param inviteRecord
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateInviteRecord.do")
	public String updateInviteRecord(InviteRecord inviteRecord){
		try {
			inviteService.updateInviteRecord(inviteRecord);
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
	 * 查询招聘记录信息(姓名,性别,年龄)
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryAllInviteRecord.do")
	public String queryAllInviteRecord(InviteRecord inviteRecord){
		try{
			PageModel<InviteRecord> pageInfo=inviteService.queryAllInviteRecord(inviteRecord);
			Gson gson=new Gson();
			String jsonString=gson.toJson(pageInfo);
			return jsonString;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 判断是否已经完成招聘
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/judgeInviteFinish.do")
	public String judgeInviteFinish(InviteApplyForm inviteApplyForm){
		try {
			//PageInfo<InviteApplyForm> pageInfo=inviteService.queryInviteApply(inviteApplyForm);
			inviteService.judgeInviteFinish(inviteApplyForm);
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
	 * 判断是否有修改招聘计划的权限（停止招聘与招聘中，招聘完成：3,4,5）
	 * @param inviteApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/chageInviteApply.do")
	public String chageInviteApply(InviteApplyForm inviteApplyForm){
		try {
			//PageInfo<InviteApplyForm> pageInfo=inviteService.queryInviteApply(inviteApplyForm);
			inviteService.chageInviteApply(inviteApplyForm);
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
