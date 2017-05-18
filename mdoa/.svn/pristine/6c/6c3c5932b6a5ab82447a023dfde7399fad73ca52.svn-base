package com.mdoa.dictionary.controller;



import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.dictionary.model.Dictionary;
import com.mdoa.dictionary.model.DictionaryModelConstant;
import com.mdoa.dictionary.service.DictionaryService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;

import junit.framework.Test;

@RestController
@RequestMapping("/dictionary")
public class DictionaryController extends BaseController{
	
	@Autowired
	private DictionaryService dictionaryService;
	
	
	/**
	 * 查询信息(数据类型)
	 */
	@RequestMapping("/queryDictionaryType.do")
	public String queryDictionaryType(Dictionary dictionary){
		try {
			PageModel<Dictionary> list = dictionaryService.queryDictionaryType(dictionary);
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 查询信息(option)
	 */
	@RequestMapping("/queryDictionary.do")
	public String queryDictionary(Dictionary dictionary){
		try {
			PageModel<Dictionary> list = dictionaryService.queryDictionary(dictionary);
			Gson gson = new Gson();
			String jsonString = gson.toJson(list);
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
		
	}
	
	/**
	 * 删除信息
	 */
	@RequestMapping("/deleteDictionary.do")
	public String deleteDictionary(Dictionary dictionary, HttpServletRequest request){
		try {
			UserInfo userInfo = getUser(request);
			dictionary.setUpdateUserId(userInfo.getUserId());
			dictionary.setUpdateUserName(userInfo.getUserName());
			dictionaryService.deleteDictionary(dictionary);
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
	 * 修改信息
	 */
	@RequestMapping("/updateDictionary.do")
	public String updateDictionary(Dictionary dictionary, HttpServletRequest request){
		try {
			UserInfo userInfo = getUser(request);
			dictionary.setUpdateUserId(userInfo.getUserId());
			dictionary.setUpdateUserName(userInfo.getUserName());
			dictionaryService.updateDictionary(dictionary, request);
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
	 * 添加信息
	 */
	@RequestMapping("/addDictionary.do")
	public String addDictionary(Dictionary dictionary, HttpServletRequest request){
		try {
			UserInfo userInfo = getUser(request);
			dictionary.setCreateUserId(userInfo.getUserId());
			dictionary.setCreateUserName(userInfo.getUserName());
			dictionaryService.addDictionary(dictionary);
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
	 * 修改数据项顺序
	 */
	@RequestMapping("/updateDictionaryOrder.do")
	public String addDictionary(String jsonString, HttpServletRequest request){
		try {
			dictionaryService.updateDictionaryOrder(jsonString, request);
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
