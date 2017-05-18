package com.mdoa.personnel.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.gson.Gson;
import com.mdoa.base.model.BaseModel;
import com.mdoa.constant.Constant;
import com.mdoa.personnel.bo.AwardPunishForm;
import com.mdoa.personnel.model.PersonAwardPunish;
import com.mdoa.personnel.model.PersonAwardPunishType;
import com.mdoa.personnel.service.PersonAwordPunishTypeService;
import com.mdoa.util.PageModel;

@Controller
@RequestMapping("personnel")
public class PersonAwardPunishTypeController {
	
	@Autowired
	private PersonAwordPunishTypeService awordPunishTypeService;
	

	
	/**
	 * 根据创建时间查询奖惩类别
	 * @param model
	 * @param awardPunishType
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectAwardPunishTypeByTime.do")
	public String selectAwardPunishTypeByTime(PersonAwardPunishType personAwardPunishType){
		
		try{
			 PageModel<PersonAwardPunishType> pageModel = awordPunishTypeService.selectAwardPunishTypeByTime(personAwardPunishType);
			Gson gson = new Gson();
			return gson.toJson(pageModel);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 修改奖惩类别
	 * @param awardPunishType
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updateAwardPunishType.do")
	public String updateAwardPunishType(PersonAwardPunishType awardPunishType, HttpServletRequest request){
		try{
			awordPunishTypeService.updateAwardPunishType(awardPunishType, request);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
	/**
	 * 插入奖惩类别
	 * @param awardPunishType
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("insertAwardPunishType.do")
	public String insertAwardPunishType(PersonAwardPunishType awardPunishType, HttpServletRequest request){
		try{
			awordPunishTypeService.insertAwardPunishType(awardPunishType, request);
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
	 * 删除奖惩类别
	 * @param awordPunishId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("deleteAwardPunishType.do")
	public String deleteAwardPunishType(String awardPunishTypeId, HttpServletRequest request){
		
		try{
			awordPunishTypeService.deleteAwardPunishType(awardPunishTypeId, request);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
//=========================================================================
	/**
	 * 条件查询奖惩信息
	 * @param personAwardPunish
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectAwardPunishByTime.do")
	public String selectAwardPunishByTime(AwardPunishForm awardPunishForm){
		try{
			PageModel<AwardPunishForm> pageModel = awordPunishTypeService.selectAwardPunishByTime(awardPunishForm);
			Gson gson = new Gson();
			return gson.toJson(pageModel);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 普通修改奖惩信息
	 * @param personAwardPunish
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updateAwardPunish.do")
	public String updateAwardPunish(PersonAwardPunish personAwardPunish, HttpServletRequest request){
		
		try{
			awordPunishTypeService.updateAwardPunish(personAwardPunish, request);
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
	 * 更改奖惩信息为已执行
	 * @param personAwardPunish
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updateAwardPunishPerform.do")
	public String updateAwardPunishPerform(PersonAwardPunish personAwardPunish, HttpServletRequest request){
		
		try{
			awordPunishTypeService.updateAwardPunishPerform(personAwardPunish, request);
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
	 * 删除奖惩信息
	 * @param personAwardPunish
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("deleteAwardPunish.do")
	public String deleteAwardPunish(String awardPunishId, HttpServletRequest request){
		
		try{
			awordPunishTypeService.deleteAwardPunish(awardPunishId, request);
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
	 * 插入奖惩信息
	 * @param personAwardPunish
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("insertAwardPunish.do")
	public String insertAwardPunish(PersonAwardPunish personAwardPunish, HttpServletRequest request){
		
		try{
			awordPunishTypeService.insertAwardPunish(personAwardPunish, request);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
}
