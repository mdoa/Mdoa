package com.mdoa.personnel.controller;



import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.constant.FileConstant;
import com.mdoa.personnel.bo.BirthForm;
import com.mdoa.personnel.bo.WelfareApplyExamineForm;
import com.mdoa.personnel.bo.WelfareApplyForm;
import com.mdoa.personnel.bo.WelfareRecordForm;
import com.mdoa.personnel.bo.WelfareStreamForm;
import com.mdoa.personnel.model.Welfare;
import com.mdoa.personnel.service.WelfareService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.FileUtil;
import com.mdoa.util.PageModel;




@RestController
@RequestMapping("/welfare")
public class WelfareController extends BaseController{
	@Autowired
	private WelfareService welfareService;
	
	/**
	 * 修改福利
	 */
	@RequestMapping("/updateWelfareo.do")
	public String updateWelfareo(WelfareApplyForm welfareApplyForm, HttpServletRequest request){
		try{
			UserInfo userInfo=getUser(request);
			welfareApplyForm.setUpdateUserId(userInfo.getUserId());
			welfareApplyForm.setUpdateUserName(userInfo.getUserName());
			welfareService.updateWelfareo(welfareApplyForm);
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
	 * 查询所有福利名称
	 */
	@RequestMapping("/queryWelfareNameAll.do")
	public String queryWelfareNameAll(WelfareApplyForm welfareApplyForm, HttpServletRequest request){
		try{
			List<WelfareApplyForm> list = welfareService.queryWelfareNameAll(welfareApplyForm);
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
			return jsonString;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 删除福利记录
	 */
	@RequestMapping("deleteRecordForGet.do")
	public String deleteRecordForGet(WelfareApplyForm welfareApplyForm, HttpServletRequest request){
		try{
			UserInfo userInfo=getUser(request);
			welfareApplyForm.setUpdateUserId(userInfo.getUserId());
			welfareApplyForm.setUpdateUserName(userInfo.getUserName());
			welfareService.deleteRecordForGet(welfareApplyForm);
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
	 * 发起福利流程
	 */
	@RequestMapping("/insertWelfareStream.do")
	public String insertWelfareStream(WelfareApplyForm welfareApplyForm, HttpServletRequest request){
		try{
			UserInfo userInfo=getUser(request);
			welfareApplyForm.setCreateUserId(userInfo.getUserId());
			welfareApplyForm.setCreateUserName(userInfo.getUserName());
			welfareService.insertWelfareStream(welfareApplyForm);
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
	 * 发起福利申请
	 */
	@RequestMapping("/addWelfareApply.do")
	public String addWelfareApply(WelfareApplyForm welfareApplyForm, HttpServletRequest request){
		try{
			UserInfo userInfo=getUser(request);
			welfareApplyForm.setCreateUserId(userInfo.getUserId());
			welfareApplyForm.setCreateUserName(userInfo.getUserName());
			welfareService.addWelfareApply(welfareApplyForm);
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
	 * 查询人员信息
	 */
	@RequestMapping("queryPersonMessage.do")
	public String queryPersonMessage(WelfareApplyForm welfareApplyForm){
		try {
			List<WelfareApplyForm> list = welfareService.queryPersonMessage(welfareApplyForm);
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
	
	
	
	/**
	 * 通过生日时间段查询员工的方法
	 * @param startDate 查询起始日期
	 * @param endDate	查询结束日期
	 * @param request	
	 * @return 员工信息集合
	 */
	@RequestMapping("/findUserInfoByBirth.do")
	public String findUserInfoByBirth(BirthForm birthForm){
		try {
			PageModel<UserInfo> list=welfareService.findUserInfoByBirth(birthForm);
			Gson gson=new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}	
	}
	
	
	
	/**
	 * 撤回福利申请的方法
	 * @param welfareApplyForm
	 * @return
	 */
	@RequestMapping("/withdrawWelfareApply.do")
	public String withdrawWelfareApply(WelfareApplyExamineForm welfareApplyExamineForm, HttpServletRequest request){
		try{
			UserInfo userInfo=getUser(request);
			welfareApplyExamineForm.setUpdateUserId(userInfo.getUserId());
			welfareApplyExamineForm.setUpdateUserName(userInfo.getUserName());
			welfareService.withdrawWelfareApply(welfareApplyExamineForm);
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
	 * 驳回福利申请的方法
	 * @param welfareApplyExamineForm
	 * @return
	 */
	@RequestMapping("rejectWelfareApply.do")
	public String rejectWelfareApply(WelfareApplyExamineForm welfareApplyExamineForm, HttpServletRequest request){
		try{
			UserInfo userInfo=getUser(request);
			welfareApplyExamineForm.setUpdateUserId(userInfo.getUserId());
			welfareApplyExamineForm.setUpdateUserName(userInfo.getUserName());
			welfareApplyExamineForm.setExamineUserId(userInfo.getUserId());
			welfareApplyExamineForm.setExamineUserName(userInfo.getUserName());
			welfareService.rejectWelfareApply(welfareApplyExamineForm);
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
	 * 通过福利申请但仍需审核的方法
	 * @param welfareApplyExamineForm
	 * @return
	 */
	@RequestMapping("passWelfareApply.do")
	public String passWelfareApply(WelfareApplyExamineForm welfareApplyExamineForm, HttpServletRequest request){
		try{
			UserInfo userInfo=getUser(request);
			welfareApplyExamineForm.setUpdateUserId(userInfo.getUserId());
			welfareApplyExamineForm.setUpdateUserName(userInfo.getUserName());
			welfareApplyExamineForm.setExamineUserId(userInfo.getUserId());
			welfareApplyExamineForm.setExamineUserName(userInfo.getUserName());
			welfareService.passWelfareApply(welfareApplyExamineForm);
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
	 * 通过福利申请不需再审核的方法
	 * @param examineForm
	 * @return
	 */
	@RequestMapping("finallyPassWelfareApply.do")
	public String finallyPassWelfareApply(WelfareApplyExamineForm welfareApplyExamineForm, HttpServletRequest request){
		try{
			UserInfo userInfo=getUser(request);
			welfareApplyExamineForm.setUpdateUserId(userInfo.getUserId());
			welfareApplyExamineForm.setUpdateUserName(userInfo.getUserName());
			welfareApplyExamineForm.setExamineUserId(userInfo.getUserId());
			welfareApplyExamineForm.setExamineUserName(userInfo.getUserName());
			welfareService.finallyPassWelfareApply(welfareApplyExamineForm);
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
	 * 备案人驳回备案请求且需再审核的方法
	 * @param welfareApplyExamineForm
	 * @return
	 */
	@RequestMapping("/rejectRecordApply.do")
	public String rejectRecordApply(WelfareApplyExamineForm welfareApplyExamineForm, HttpServletRequest request){
		try{
			UserInfo userInfo=getUser(request);
			welfareApplyExamineForm.setUpdateUserId(userInfo.getUserId());
			welfareApplyExamineForm.setUpdateUserName(userInfo.getUserName());
			welfareApplyExamineForm.setExamineUserId(userInfo.getUserId());
			welfareApplyExamineForm.setExamineUserName(userInfo.getUserName());
			welfareService.rejectRecordApply(welfareApplyExamineForm);
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
	 * 备案人通过福利申请并备案的方法
	 * @param welfareApplyExamineForm
	 * @return
	 */
	@RequestMapping("/recordWelfareApply.do")
	public String recordWelfareApply(WelfareApplyExamineForm welfareApplyExamineForm, HttpServletRequest request){
		try{
			System.out.println("111"+welfareApplyExamineForm.getExamineStatus());
			UserInfo userInfo = getUser(request);
			welfareApplyExamineForm.setUpdateUserId(userInfo.getUserId());
			welfareApplyExamineForm.setUpdateUserName(userInfo.getUserName());
			welfareApplyExamineForm.setExamineUserId(userInfo.getUserId());
			welfareApplyExamineForm.setExamineUserName(userInfo.getUserName());
			welfareApplyExamineForm.setRecordUserId(userInfo.getUserId());
			welfareApplyExamineForm.setRecordUserName(userInfo.getUserName());
			welfareService.recordWelfareApply(welfareApplyExamineForm);
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
	 * 查询我的待审核流程
	 * @param welfareApplyExamineForm
	 * @return
	 */
	@RequestMapping("/findMyExamineStream.do")
	public String findMyExamineStream(WelfareApplyForm welfareApplyForm, HttpServletRequest request){
		try {
			UserInfo userInfo=getUser(request);
			welfareApplyForm.setExamineUserId(userInfo.getUserId());
			PageModel<WelfareStreamForm> list=welfareService.findStreamByCondition(welfareApplyForm);
			Gson gson=new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 条件查询福利流程的方法
	 * @param welfareApplyForm
	 * @return
	 */
	@RequestMapping("/findStreamByCondition")
	public String findStreamByCondition(WelfareApplyForm welfareApplyForm){
		try {
			PageModel<WelfareStreamForm> list=welfareService.findStreamByCondition(welfareApplyForm);
			Gson gson=new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 通过条件查询福利记录的方法
	 * @param welfareRecordForm
	 * @return
	 */
	@RequestMapping("/findRecordByCondition.do")
	public String findRecordByCondition(WelfareRecordForm welfareRecordForm){
		try {
			PageModel<WelfareRecordForm> list=welfareService.findRecordByCondition(welfareRecordForm);
			Gson gson=new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 条件查询福利的方法
	 * @param welfareApplyForm
	 * @return
	 */
	@RequestMapping("/findWelfareByCondition.do")
	public String findWelfareByCondition(WelfareApplyForm welfareApplyForm){
		try {
			PageModel<Welfare> list=welfareService.findWelfareByCondition(welfareApplyForm);
			Gson gson=new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 在员工领取福利后，福利记录表的后续更新操作
	 * @param welfareRecordForm 福利id:welfareId 领取人id：getUserId 
	 * @return
	 */
	@RequestMapping("/updateRecordForGet.do")
	public String updateRecordForGet(WelfareRecordForm welfareRecordForm){
		try {
			welfareService.updateRecordForGet(welfareRecordForm);
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
	 * 导出福利记录信息到Excel
	 * 
	 * @param userInfo
	 * @param jsonString
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/writeToExcel.do")
	public String writeToExcel(WelfareRecordForm welfareRecordForm,String jsonString,HttpServletRequest request,HttpServletResponse response){
		try {
			String filePath = FileConstant.FILE_PATH + FileConstant.SHORT_TIME_EXCEL + "/" +new Date().getTime() + ".xls";
			welfareService.writeToExcel(welfareRecordForm, jsonString, filePath);
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
