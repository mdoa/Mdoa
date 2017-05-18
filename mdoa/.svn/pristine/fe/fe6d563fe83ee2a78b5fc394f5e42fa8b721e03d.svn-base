package com.mdoa.repertory.controller;


import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.support.StringMultipartFileEditor;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.constant.TreeModelConstant;
import com.mdoa.repertory.model.DragModel;
import com.mdoa.repertory.model.RepertoryGoodsType;
import com.mdoa.repertory.model.TreeModel;
import com.mdoa.repertory.service.TreeService;
import com.mdoa.user.model.UserInfo;

import junit.framework.Test;

@Controller
@RequestMapping("/treeController")
public class TreeController extends BaseController{

	
	@Autowired
	private TreeService treeService;
	//表示根目录的pid
	private static final String ROOT = "0";
	
	/**
	 * 测试state
	 */
	@ResponseBody
	@RequestMapping("updateTreeState.do")
	public String updateTreeState(RepertoryGoodsType repertoryGoodsType){
		try {
			treeService.updateTreeState(repertoryGoodsType,TreeModelConstant.treeList);
			return Constant.SUCCESS_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
		
	}
	/**
	 * 展示树结构
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/queryTree.do")
	public String deptTree(){
		 // 默认从根节点开始 
        if(TreeModelConstant.treeList == null){
        	List<TreeModel> list = new ArrayList<>();
        	TreeModel treeModel=new TreeModel();
        	treeModel.setId(ROOT);
        	treeModel.setThisId(ROOT);
        	treeModel.setText("总仓库");
        	list.add(treeModel);
        	list.get(0).setChildren(treeService.selectTree(treeModel.getThisId()));
        	TreeModelConstant.treeList = list;
        }
        Gson gson = new Gson();
        String jsonString = gson.toJson(TreeModelConstant.treeList);
        return jsonString; 
	}
	/**
	 * 在当前父ID下添加一个物品类
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/addRepertoryType.do")
	public String addRepertoryType(RepertoryGoodsType repertoryGoodsType, HttpServletRequest request){
		try {
			UserInfo userInfo=getUser(request);
			repertoryGoodsType.setCreateUserId(userInfo.getUserId());
			repertoryGoodsType.setCreateUserName(userInfo.getUserName());
			//treeService.addRepertoryType(repertoryGoodsType);
			treeService.allTreeMethod(repertoryGoodsType);
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
	 * 根据当前父ID删除下面的所有物品类
	 * @param repertoryGoodsType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/removeRepertoryType.do")
	public String removeRepertoryType(RepertoryGoodsType repertoryGoodsType, HttpServletRequest request){
		try {
			UserInfo userInfo=getUser(request);
			repertoryGoodsType.setUpdateUserId(userInfo.getUserId());
			repertoryGoodsType.setUpdateUserName(userInfo.getUserName());
			//treeService.removeRepertoryType(repertoryGoodsType);
			treeService.allTreeMethod(repertoryGoodsType);
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
	 * 根据前台传来的ID和Name,完成修改
	 * @param repertoryGoodsType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/editRepertoryType.do")
	public String editRepertoryType(RepertoryGoodsType repertoryGoodsType, HttpServletRequest request){
		try {
			UserInfo userInfo=getUser(request);
			repertoryGoodsType.setUpdateUserId(userInfo.getUserId());
			repertoryGoodsType.setUpdateUserName(userInfo.getUserName());
			//treeService.editRepertoryType(repertoryGoodsType);
			treeService.allTreeMethod(repertoryGoodsType);
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
	 * (拖动)
	 * @param repertoryGoodsType
	 * @return
	 */
	@ResponseBody
	@RequestMapping("/dragRepertoryType.do")
	public String dragRepertoryType(RepertoryGoodsType dragModel, HttpServletRequest request){
		try {
			UserInfo userInfo=getUser(request);
			dragModel.setUpdateUserId(userInfo.getUserId());
			dragModel.setUpdateUserName(userInfo.getUserName());
			//treeService.dragRepertoryType(dragModel);
			treeService.allTreeMethod(dragModel);
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
