package com.mdoa.personnel.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.mdoa.base.controller.BaseController;
import com.mdoa.constant.Constant;
import com.mdoa.personnel.bo.InsuranceForm;
import com.mdoa.personnel.bo.InsuranceTypeForm;
import com.mdoa.personnel.model.InsuranceType;
import com.mdoa.personnel.service.InsuranceService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;

@RestController
@RequestMapping("/insurance")
public class InsuranceController extends BaseController {

	@Autowired
	private InsuranceService insuranceService;
	
	/**
	 * 条件查询社保信息的方法 待测
	 * @param insuranceForm
	 * @return
	 */
	@RequestMapping("/findInsuranceByCondition.do")
	public String findInsuranceByCondition(InsuranceForm insuranceForm){
		try {
			PageModel<InsuranceForm> list = insuranceService.findInsuranceByCondition(insuranceForm);
			Gson gson = new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 通过社保类型id查询员工社保信息的方法
	 * @param
	 * @return
	 */
	@RequestMapping("/findInsuranceByTypeId.do")
	public String findInsuranceByTypeId(InsuranceForm insuranceForm) {
		try {
			PageModel<InsuranceForm> list = insuranceService.findInsuranceByTypeId(insuranceForm);
			Gson gson = new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}

	/**
	 * 通过员工id查询社保信息的方法
	 * @param
	 * @return
	 */
	@RequestMapping("/findInsuranceByUserId.do")
	public String findInsuranceByUserId(InsuranceForm insuranceForm) {
		try {
			PageModel<InsuranceForm> list = insuranceService.findInsuranceByUserId(insuranceForm);
			Gson gson = new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 通过社保大类型来查询社保类型信息的方法
	 * @param insuranceTypeForm
	 * @return
	 */
	@RequestMapping("/findTypeBySuperType.do")
	public String findTypeBySuperType(InsuranceTypeForm insuranceTypeForm){
		try {
			PageModel<InsuranceTypeForm> list = insuranceService.findTypeBySuperType(insuranceTypeForm);
			Gson gson = new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 条件查询社保类型
	 * @param insuranceTypeForm
	 * @return
	 */
	@RequestMapping("/findTypeByCondition.do")
	public String findTypeByCondition(InsuranceTypeForm insuranceTypeForm){
		try {
			PageModel<InsuranceType> list = insuranceService.findTypeByCondition(insuranceTypeForm);
			Gson gson = new Gson();
			return gson.toJson(list);
		} catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}

	/**
	 * 插入社保信息的方法
	 * @param
	 *          
	 */
	@RequestMapping("/insertInsurance.do")
	public String insertInsurance(InsuranceForm insuranceForm, HttpServletRequest request){
		try{
			UserInfo userInfo= getUser(request);
			insuranceForm.setCreateUserId(userInfo.getUserId());
			insuranceService.insertInsurance(insuranceForm);
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
	 * 插入社保类型的方法
	 * @param insuranceTypeForm
	 * @return
	 */
	@RequestMapping("/insertInsuranceType.do")
	public String insertInsuranceType(InsuranceTypeForm insuranceTypeForm, HttpServletRequest request){
		try {
			UserInfo userInfo= getUser(request);
			insuranceTypeForm.setCreateUserId(userInfo.getUserId());
			insuranceService.insertInsuranceType(insuranceTypeForm);
			return Constant.SUCCESS_CODE;
		} catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
}
