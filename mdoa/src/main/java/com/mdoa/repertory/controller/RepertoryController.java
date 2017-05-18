package com.mdoa.repertory.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.repertory.bo.GoodsForm;
import com.mdoa.repertory.model.RepertoryGoodsPosition;
import com.mdoa.repertory.model.RepertoryType;
import com.mdoa.repertory.service.RepertoryService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
/**
 * 仓库的controller层
 * @author Administrator
 *
 */
@RestController
@RequestMapping("/repertory")
public class RepertoryController extends BaseController{
	@Autowired
	private RepertoryService repertoryService;
	
	/**
	 * 根据时间查询仓位仓库和物品的信息以及模糊查询
	 * @param goodsForm
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectRepertoryPosition.do")
	public String selectRepertoryPosition(GoodsForm goodsForm){
		try{
			PageModel<GoodsForm> pageModel = repertoryService.selectRepertoryPosition(goodsForm);
			Gson gson = new Gson();
		    return gson.toJson(pageModel);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}	
	}
	
	/**
	 *  添加仓库
	 * @param repertoryType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insertRepertoryType.do")
	public String insertRepertoryType(RepertoryType repertoryType, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			repertoryType.setCreateUserId(user.getCreateUserId());
			repertoryType.setCreateUserName(user.getCreateUserName());
			repertoryService.insertRepertoryType(repertoryType);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 添加仓位
	 * @param goodsPosition
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insertRepertoryPosition.do")
	public String insertRepertoryPosition(GoodsForm goodsForm, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			goodsForm.setCreateUserId(user.getCreateUserId());
			goodsForm.setCreateUserName(user.getCreateUserName());
			repertoryService.insertRepertoryPosition(goodsForm);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 删除仓库
	 * @param repertoryId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteRepertory.do")
	public String deleteRepertory(String repertoryId){
		try{
			repertoryService.deleteRepertory(repertoryId);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 删除仓位
	 * @param goodsPositionId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteRepertoryPosition.do")
	public String deleteRepertoryPosition(String goodsPositionId, HttpServletRequest request){
		try{
			repertoryService.deleteRepertoryPosition(goodsPositionId);
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
	 * 修改仓库信息
	 * @param repertoryType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateRepertory.do")
	public String updateRepertory(RepertoryType repertoryType, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			repertoryType.setUpdateUserId(user.getUpdateUserId());
			repertoryType.setUpdateUserName(user.getUpdateUserName());
			repertoryService.updateRepertory(repertoryType);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 修改仓位信息
	 * @param goodsPosition
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateRepertoryPosition.do")
	public String updateRepertoryPosition(GoodsForm goodsForm, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			goodsForm.setUpdateUserId(user.getUpdateUserId());
			goodsForm.setUpdateUserName(user.getUpdateUserName());
			repertoryService.updateRepertoryPosition(goodsForm);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 查询仓位信息 用于下拉框
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectGoodsPosition.do")
	public String selectGoodsPosition(RepertoryGoodsPosition goodsPosition){
		try{
			List<RepertoryGoodsPosition> list = repertoryService.selectGoodsPosition(goodsPosition);
			Gson gson = new Gson();
		    return gson.toJson(list);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}	
	}
	/**
	 * 查询仓库信息 用于下拉框
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectRepertoryType.do")
	public String selectRepertoryType(){
		try{
			List<RepertoryType> list = repertoryService.selectRepertoryType();
			Gson gson = new Gson();
		    return gson.toJson(list);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}	
	}
}
