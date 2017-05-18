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
import com.mdoa.repertory.model.RepertoryGoods;
import com.mdoa.repertory.service.GoodsService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
/**
 * 物品的controller层
 * @author Administrator
 */
@RestController
@RequestMapping("/repertory")
public class GoodsController extends BaseController{
	@Autowired
	private GoodsService goodsService;
	
	/**
	 * 添加物品信息
	 * @param repertoryGoods
	 * @return
	 */
	@ResponseBody
	@RequestMapping("insertGoods.do")
	public String insertGoods(RepertoryGoods repertoryGoods, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			repertoryGoods.setCreateUserId(user.getCreateUserId());
			repertoryGoods.setCreateUserName(user.getCreateUserName());
			goodsService.insertGoods(repertoryGoods);
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
	 * 修改物品信息
	 * @param repertoryGoods
	 * @return
	 */
	@ResponseBody
	@RequestMapping("updateGoods.do")
	public String updateGoods(GoodsForm goodsForm, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			goodsForm.setUpdateUserId(user.getUpdateUserId());
			goodsForm.setUpdateUserName(user.getUpdateUserName());
			goodsService.updateGoods(goodsForm);
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
	 * 删除物品
	 * @param goodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("deleteGoods.do")
	public String deleteGoods(GoodsForm goodsForm, HttpServletRequest request){
		try{
			UserInfo user = getUser(request);
			goodsForm.setUpdateUserId(user.getUpdateUserId());
			goodsForm.setUpdateUserName(user.getUpdateUserName());
			goodsService.deleteGoods(goodsForm);
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
	 * 用户在点击物品的时候，在右侧会显示物品的出入库和仓库的信息
	 * @param goodsId
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectRecordAndRepertoryById.do")
	public String selectRecordAndRepertoryById(String goodsId){	
		try{
			List<GoodsForm> list = goodsService.selectRecordAndRepertoryById(goodsId);
			Gson gson = new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}

	/**
	 * 根据时间去查询物品以及查询条件
	 * @param repertoryGoods
	 * @return
	 */
	@ResponseBody
	@RequestMapping("selectGoodsByTime.do")
	public String selectGoodsByTime(GoodsForm goodsForm){
		try{
			PageModel<RepertoryGoods> pageModel = goodsService.selectGoodsByTime(goodsForm);
			Gson gson = new Gson();
			return gson.toJson(pageModel);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 根据时间去查询物品以及查询条件
	 * @param repertoryGoods
	 * @return
	 */
	@ResponseBody
	@RequestMapping("findSum.do")
	public String findSum(GoodsForm goodsForm){
		try{
			RepertoryGoods repertoryGoods = goodsService.findSum(goodsForm);
			Gson gson = new Gson();
			return gson.toJson(repertoryGoods);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}

}
