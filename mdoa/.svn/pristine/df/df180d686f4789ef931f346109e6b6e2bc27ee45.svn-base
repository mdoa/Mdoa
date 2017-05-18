package com.mdoa.personnel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.personnel.bo.TrainApplyForm;
import com.mdoa.personnel.model.Train;
import com.mdoa.personnel.model.TrainUserIdAndNameByPosition;
import com.mdoa.personnel.service.TrainService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;

@Controller
@RequestMapping("/train")
public class TrainController extends BaseController{

	@Autowired
	private TrainService trainService;
	
	
	
	/**
	 * 发起培训申请，添加培训计划和培训计划流以及添加培训对象
	 * @param train
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/startTrainApply.do")
	public String startTrainApply(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try {
			trainService.startTrainApply(trainApplyForm, request);
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
	 * 查询培训信息0
	 */
	@ResponseBody
	@RequestMapping("/queryTrainApplyO.do")
	public String queryTrainApplyO(TrainApplyForm trainApplyForm){
		try{
			PageModel<TrainApplyForm> pageInfo=trainService.queryTrainApplyO(trainApplyForm);
			Gson gson=new Gson();
			String jsonString = gson.toJson(pageInfo);
			return jsonString;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
		
	}
	
	/**
	 * 查询培训的详细信息
	 * 其中包含培训对象列表，培训信息
	 * 待优化
	 */
	@ResponseBody
	@RequestMapping("queryTrainMessage.do")
	public String queryTrainMessage(String trainId){
		try{
			TrainApplyForm trainApplyForm = new TrainApplyForm();
			trainApplyForm.setTrainId(trainId);
			TrainApplyForm train = trainService.queryTrainMessage(trainApplyForm);
			Gson gson = new Gson();
			return gson.toJson(train); 
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 查询培训流程表(审批人，审批ID，培训流程ID)
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryTrainApplyStream.do")
	public String queryTrainApplyStream(TrainApplyForm trainApplyForm){
		try{
			List<TrainApplyForm> list=trainService.queryTrainApplyStream(trainApplyForm);
			Gson gson=new Gson();
			return gson.toJson(list);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 驳回培训申请的情况
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/rejectTrainApply.do")
	public String rejectTrainApply(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try {
			trainService.rejectTrainApply(trainApplyForm, request);
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
	 * 通过审批并给下一级
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/passAndNextTrainApply.do")
	public String passAndNextTrainApply(TrainApplyForm trainApplyForm){
		try {
			trainService.passAndNextTrainApply(trainApplyForm);
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
	 * 进入备案流程
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/joinTrainRecordStream.do")
	public String joinTrainRecordStream(TrainApplyForm trainApplyForm){
		try {
			trainService.joinTrainRecordStream(trainApplyForm);
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
	 * 人事进行备案
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/hrRecordTrain.do")
	public String hrRecordTrain(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try {
			trainService.hrRecordTrain(trainApplyForm, request);
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
	 * 人事驳回并给下一级进行审批
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/rejectAndNextTrainApply.do")
	public String rejectAndNextTrainApply(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try {
			trainService.rejectAndNextTrainApply(trainApplyForm, request);
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
	 * 撤回培训申请
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/backTrainApply.do")
	public String backTrainApply(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try {
			UserInfo user = getUser(request);
			trainApplyForm.setUserId(user.getUserId());
			trainApplyForm.setUserName(user.getUserName());
			trainService.backTrainApply(trainApplyForm);
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
	 * 在培训结束后,写培训记录
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/wirteTrainRecord.do")
	public String wirteTrainRecord(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try {
			trainService.wirteTrainRecord(trainApplyForm, request);
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
	 * 查询培训人员信息
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryTrainPersonMessage.do")
	public String queryTrainPersonMessage(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try{
			PageModel<TrainApplyForm> list=trainService.queryTrainPersonMessage(trainApplyForm, request);
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
			return jsonString;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 修改培训人员信息
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/updateTrainRecord.do")
	public String updateTrainRecord(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try {
			trainService.updateTrainRecord(trainApplyForm, request);
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
	 * 删除培训人员信息
	 * @param trainApplyForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/deleteTrainRecord.do")
	public String deleteTrainRecord(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try {
			trainService.deleteTrainRecord(trainApplyForm, request);
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
	 * 查询我参与的培训审核流程信息
	 */
	@ResponseBody
	@RequestMapping("queryMyTrainStream.do")
	public String queryMyTrainStream(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			trainApplyForm.setUserId(user.getUserId());
			PageModel<TrainApplyForm> page = trainService.queryMyTrainStream(trainApplyForm);
			Gson gson = new Gson();
			return gson.toJson(page);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 查询需要进行备案的培训流程
	 */
	@ResponseBody
	@RequestMapping("queryTrainRecordStream.do")
	public String queryTrainRecordStream(TrainApplyForm trainApplyForm, HttpServletRequest request){
		try{
			PageModel<TrainApplyForm> page = trainService.queryTrainRecordStream(trainApplyForm, request);
			Gson gson = new Gson();
			return gson.toJson(page);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据记录Id来修改员工的培训记录
	 */
	@ResponseBody
	@RequestMapping("updateTrainJoinFlag.do")
	public String updateTrainJoinFlag(String joinFlag, String trainDocId, HttpServletRequest request){
		try{
			trainService.updateTrainJoinFlag(joinFlag, trainDocId, request);
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
