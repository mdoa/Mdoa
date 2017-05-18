package com.mdoa.repertory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.repertory.bo.GoodsForm;
import com.mdoa.repertory.model.RepertoryDepartment;
import com.mdoa.repertory.model.RepertoryInRecord;
import com.mdoa.repertory.model.RepertoryOutRecord;
import com.mdoa.repertory.service.RepertoryInOutRecordService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.JSONUtil;
import com.mdoa.util.PageModel;

/**
 * 入库出库的controller
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/repertory")
public class RepertoryInOutRecordController extends BaseController{
	
	@Autowired
	private RepertoryInOutRecordService inOutRecordService;

	/**
	 * 批量入库操作
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("batchGoodsIn.do")
	public String batchGoodsIn(String json,HttpServletRequest request){
		
		try{
			JSONObject jsonObject = new JSONObject(json);
			RepertoryInRecord inRecord = new RepertoryInRecord();
			inRecord.setPutUserName(jsonObject.getString("putUserName"));
			inRecord.setRepertoryId(jsonObject.getString("repertoryId"));
			inRecord.setRepertoryName(jsonObject.getString("repertoryName"));
			inRecord.setStrInTime(jsonObject.getString("inTime"));
			inRecord.setProvider(jsonObject.getString("provider"));
			String string = jsonObject.getString("list");
			List<GoodsForm> list = JSONUtil.<GoodsForm>jsonToList(string, GoodsForm[].class);			
			for(int i= 0;i<list.size();i++){
				GoodsForm goodsForm = list.get(i);
				goodsForm.setPutUserName(inRecord.getPutUserName());
				goodsForm.setRepertoryId(inRecord.getRepertoryId());
				goodsForm.setInTimeStr(inRecord.getStrInTime());
				goodsForm.setProvider(inRecord.getProvider());
				goodsForm.setRepertoryName(inRecord.getRepertoryName());
				if(goodsForm.getGoodsPositionId() == null){
					inOutRecordService.newGoodsPositionPut(goodsForm,request);					
				}else{
					inOutRecordService.putInStorageRecord(goodsForm, request);
				}
			}
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 批量出库操作
	 * @param json
	 * @param request
	 * @return
	 */
	@ResponseBody
	@RequestMapping("batchGoodsOut.do")
	public String batchGoodsOut(String json,HttpServletRequest request){
		try{
			JSONObject jsonObject = new JSONObject(json);
			RepertoryOutRecord outRecord = new RepertoryOutRecord();
			outRecord.setGetUserName(jsonObject.getString("getUserName"));
			outRecord.setRepertoryId(jsonObject.getString("repertoryId"));
			outRecord.setRepertoryName(jsonObject.getString("repertoryName"));
			outRecord.setGetDepartmentId(jsonObject.getString("getDepartmentId"));
			outRecord.setGetDepartmentName(jsonObject.getString("getDepartmentName"));
			outRecord.setStrOutTime(jsonObject.getString("outTime"));
			outRecord.setUseType(jsonObject.getString("useType"));
			String string = jsonObject.getString("list");
			List<GoodsForm> list = JSONUtil.<GoodsForm>jsonToList(string, GoodsForm[].class);
			for(int i = 0;i<list.size();i++){
				GoodsForm goodsForm = list.get(i);
				goodsForm.setGetUserName(outRecord.getGetUserName());
				goodsForm.setRepertoryId(outRecord.getRepertoryId());
				goodsForm.setRepertoryName(outRecord.getRepertoryName());
				goodsForm.setGetDepartmentId(outRecord.getGetDepartmentId());
				goodsForm.setGetDepartmentName(outRecord.getGetDepartmentName());
				goodsForm.setOutTimeStr(outRecord.getStrOutTime());
				goodsForm.setUseType(outRecord.getUseType());
				inOutRecordService.putOutStorageRecord(goodsForm,request);
			}
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 新仓位入库操作
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("newGoodsPositionPut.do")
	public String newGoodsPositionPut(GoodsForm goodsForm, HttpServletRequest request){
		try{
			Double double1 = inOutRecordService.newGoodsPositionPut(goodsForm, request);
			Gson gson = new Gson();
			return gson.toJson(double1);	
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
	/**
	 * 进行入库操作
	 * @param inRecord
	 * @return
	 */
	@ResponseBody
	@RequestMapping("putInStorageRecord.do")
	public String putInStorageRecord(GoodsForm goodsForm, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			goodsForm.setCreateUserId(user.getCreateUserId());
			goodsForm.setCreateUserName(user.getCreateUserName());
			Double double1 = inOutRecordService.putInStorageRecord(goodsForm, request);
			Gson gson = new Gson();
			return gson.toJson(double1);	
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 进行出库操作
	 * @param outRecord
	 * @return
	 */
	@ResponseBody
	@RequestMapping("putOutStorageRecord.do")
	public String putOutStorageRecord(GoodsForm goodsForm, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			goodsForm.setCreateUserId(user.getCreateUserId());
			goodsForm.setCreateUserName(user.getCreateUserName());
			inOutRecordService.putOutStorageRecord(goodsForm, request);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
		
	}
	/**
	 * 进行移库操作
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("moveGoodsRepertoryByOutIn.do")
	public String moveGoodsRepertoryByOutIn(GoodsForm goodsForm, HttpServletRequest request){
		try{
			inOutRecordService.moveGoodsRepertoryByOutIn(goodsForm, request);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 修改入库记录
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("editGoodsInRecord.do")
	public String editGoodsInRecord(GoodsForm goodsForm, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			goodsForm.setUpdateUserId(user.getUpdateUserId());
			goodsForm.setUpdateUserName(user.getUpdateUserName());
			Double newMovingAverPrice = inOutRecordService.editGoodsInRecord(goodsForm,request);
			Gson gson = new Gson();
			return gson.toJson(newMovingAverPrice);	
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 修改出库记录
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("editGoodsOutRecord.do")
	public String editGoodsOutRecord(GoodsForm goodsForm, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			goodsForm.setUpdateUserId(user.getUpdateUserId());
			goodsForm.setUpdateUserName(user.getUpdateUserName());
			inOutRecordService.editGoodsOutRecord(goodsForm, request);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 查询所有物品的入库流水列表 以及各种查询
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectGoodsInRecord.do")
	public String selectGoodsInRecord(GoodsForm goodsForm){
		try{
			PageModel<GoodsForm> pageModel = inOutRecordService.selectGoodsInRecord(goodsForm);
			Gson gson = new Gson();
			return gson.toJson(pageModel);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 查询所有物品的出库明细列表 以及各种查询
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectGoodsOutRecord.do")
	public String selectGoodsOutRecord(GoodsForm goodsForm){
		try{
			PageModel<GoodsForm> pageModel = inOutRecordService.selectGoodsOutRecord(goodsForm);
			Gson gson = new Gson();
			return gson.toJson(pageModel);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 查询全部的出库入库记录  以及各种条件查询
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectAllInOutRecord.do")
	public String selectAllInOutRecord(GoodsForm goodsForm){
		try{
			PageModel<GoodsForm> pageModel = inOutRecordService.selectAllInOutRecord(goodsForm);
			Gson gson = new Gson();
			return gson.toJson(pageModel);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 查询出入库记录汇总
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findSumInOutRecord.do")
	public String findSumInOutRecord(GoodsForm goodsForm){
		try{
			GoodsForm form = inOutRecordService.findSumInOutRecord(goodsForm);
			Gson gson = new Gson();
			return gson.toJson(form);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 查询出库记录汇总
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findSumOutRecord.do")
	public String findSumOutRecord(GoodsForm goodsForm){
		try{
			GoodsForm form = inOutRecordService.findSumOutRecord(goodsForm);
			Gson gson = new Gson();
			return gson.toJson(form);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 查询入库记录汇总
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findSumInRecord.do")
	public String findSumInRecord(GoodsForm goodsForm){
		try{
			GoodsForm form = inOutRecordService.findSumInRecord(goodsForm);
			Gson gson = new Gson();
			return gson.toJson(form);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 批量删除入库记录 
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteGoodsInRecord.do")
	public String deleteGoodsInRecord(String json){	
		try{
			JSONArray jsonArray = new JSONArray(json);  
			for(int i = 0;i <jsonArray.length(); i++){
				String inRecordId = (String) jsonArray.getJSONObject(i).get("inRecordId");
				inOutRecordService.deleteGoodsInRecord(inRecordId);	
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
	 *  批量删除出库记录 
	 * @param json
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteGoodsOutRecord.do")
	public String deleteGoodsOutRecord(String json){
		try{
			JSONArray jsonArray = new JSONArray(json);
			for(int i = 0;i <jsonArray.length(); i++){
				String outRecordId = (String) jsonArray.getJSONObject(i).get("outRecordId");
				inOutRecordService.deleteGoodsOutRecord(outRecordId);	
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
	/** 删除一条入库记录
	 * @param inRecordId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteInRecord.do")
	public String deleteInRecord(String inRecordId){
		try{
			inOutRecordService.deleteGoodsInRecord(inRecordId);
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
	 * 删除一条出库记录
	 * @param outRecordId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteOutRecord.do")
	public String deleteOutRecord(String outRecordId){
		try{
			inOutRecordService.deleteGoodsOutRecord(outRecordId);
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
	 * 新建领用部门的信息
	 * @param department
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insertGetDepartment.do")
	public String insertGetDepartment(RepertoryDepartment department, HttpServletRequest request){
		try {
			UserInfo user = getUser(request);
			department.setCreateUserId(user.getCreateUserId());
			department.setCreateUserName(user.getCreateUserName());
			inOutRecordService.insertGetDepartment(department);
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
	 * 删除一个领用部门
	 * @param departmentId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteGetDepartment.do")
	public String deleteGetDepartment(String departmentId){
		try{
			inOutRecordService.deleteGetDepartment(departmentId);
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
	 * 修改领用部门的信息
	 * @param department
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateGetDepartment.do")
	public String updateGetDepartment(RepertoryDepartment department, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			department.setUpdateUserId(user.getUpdateUserId());
			department.setUpdateUserName(user.getUpdateUserName());
			inOutRecordService.updateGetDepartment(department);
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
	 * 查询所有部门信息 
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectGetDepartment.do")
	public String selectGetDepartment(){
		try{
			List<RepertoryDepartment> list = inOutRecordService.selectGetDepartment();
			Gson gson = new Gson();
			return gson.toJson(list);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
			
	}
}