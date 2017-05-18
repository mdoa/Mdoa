package com.mdoa.system.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.system.model.SystemLog;
import com.mdoa.system.service.SystemService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;

@RestController
@RequestMapping("/systemLog")
public class SystemLogController extends BaseController{

	@Autowired
	private SystemService systemService;
	
	
	/**
	 * 添加日志
	 * @param systemLog
	 * @return
	 */
	@RequestMapping("/addSystemLog.do")
	public String addSystemLog(SystemLog systemLog,HttpServletRequest request){
		try {
			System.out.println("tab="+systemLog.getOperaTab());
			UserInfo userInfo = getUser(request);
			systemLog.setOperaUserId(userInfo.getUserId());
			systemLog.setOperaUserName(userInfo.getUserName());
			systemService.addSystemLog(systemLog);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 查询日志
	 */
	@RequestMapping("/querySystemLog.do")
	public String querySystemLog(SystemLog systemLog,HttpServletRequest request){
		try {
			PageModel<SystemLog> pageModel = systemService.querySystemLog(systemLog);
			Gson gson = new Gson();
			String jsonString = gson.toJson(pageModel);
			return jsonString;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 删除日志
	 */
	@RequestMapping("/deleteSystemLog.do")
	public String deleteSystemLog(SystemLog systemLog,HttpServletRequest request){
		try {
			UserInfo userInfo = getUser(request);
			systemLog.setUpdateUserId(userInfo.getUserId());
			systemLog.setUpdateUserName(userInfo.getUserName());
			systemService.deleteSystemLog(systemLog);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
}
