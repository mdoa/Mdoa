package com.mdoa.framework.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.constant.Constant;
import com.mdoa.constant.FrameWorkConstant;
import com.mdoa.framework.model.Power;
import com.mdoa.framework.service.PowerService;

@RestController
@RequestMapping("power")
public class PowerController {
	
	@Autowired
	private PowerService powerService;
	
	/**
	 * 权限结构的接口
	 * @return
	 */
	@RequestMapping("getFrameWork.do")
	public String getFrameWork(){
		try{
			if(FrameWorkConstant.frameworkPowers == null){
				FrameWorkConstant.frameworkPowers = powerService.injectFrameworkPower("0000");
			}
			Gson gson = new Gson();
			return gson.toJson(FrameWorkConstant.frameworkPowers);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 通过用户的Id，获取用户的权限信息
	 * @param userId
	 * @return 
	 */
	@RequestMapping("getPowerByUser.do")
	public String getUserPower(String userId){
		try{
			List<Power> powers = powerService.getPowerByUser(userId);
			Gson gson = new Gson();
			return gson.toJson(powers);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 添加权限
	 */
	@RequestMapping("/addUserPower.do")
	public String addUserPower(Power power){
		try {
			powerService.addUserPower(power);
			return Constant.SUCCESS_CODE;
		} catch (RuntimeException e) {
			return Constant.DATA_ERROR_CODE;
		} catch (Exception e) {
			return Constant.SERVER_ERROR_CODE;
		}
		
	}
}
