package com.mdoa.personnel.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.google.gson.Gson;
import com.mdoa.constant.Constant;
import com.mdoa.personnel.bo.KpiApplyForm;
import com.mdoa.personnel.model.PersonKpi;
import com.mdoa.personnel.model.PersonKpiGroup;
import com.mdoa.personnel.service.PersonKpiGroupService;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;



@RestController
@RequestMapping("personnel")
public class PersonKpiGroupController {

	@Autowired
	private PersonKpiGroupService kpiGroupService;
	
	/**
	 * 给员工分配kpi组
	 * @param userInfo
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updateUserKpiGroup.do")
	public String updateUserKpiGroup(UserInfo userInfo, HttpServletRequest request){
		try{
			kpiGroupService.updateUserKpiGroup(userInfo, request);
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
	 * 获取前台传来的json，进行解析，并循环执行updateKpi
	 * @param json
	 * @return
	 */
	public String updateKpiByGroupId(String json,String kpiGroupId){
		try {
			JSONObject jsonObj = new JSONObject(json);
			JSONArray personList;
			personList = jsonObj.getJSONArray("id");
			for (int i = 0; i < personList.length(); i++)
	        {
				JSONObject jsonItem = personList.getJSONObject(i);
				PersonKpi kpi = new PersonKpi();
	        }
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return null;
        
	}
	
	/**
	 * 新建打分记录的时候回显的kpi标准和kpi组的信息
	 * @param userId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectKpiGroupByUserId.do")
	public String selectKpiGroupByUserId(String userId,String kpiGroupId){
		try{
			List<KpiApplyForm> list = kpiGroupService.selectKpiGroupByUserId(userId, kpiGroupId);
			Gson gson = new Gson();
			return gson.toJson(list); 
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
	
	
	/**
	 * 根据创建的时间先后去查询kpi组的信息
	 * @param model
	 * @param personKpiGroup
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectKpiGroupByTime.do")
	public String selectKpiGroupByTime(PersonKpiGroup personKpiGroup){
		
		try{
			 PageModel<PersonKpiGroup> pageModel = kpiGroupService.selectKpiGroupByTime(personKpiGroup);
			Gson gson = new Gson();
			return gson.toJson(pageModel); 
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 查看打分详细情况的时候显示的信息
	 * @param kpiApplyForm
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectKpiRecordByGroup.do")
	public String selectKpiRecordByGroup(KpiApplyForm kpiApplyForm){
		
		try{
			 PageModel<KpiApplyForm> pageModel = kpiGroupService.selectKpiRecordByGroup(kpiApplyForm);
			Gson gson = new Gson();
			return gson.toJson(pageModel); 
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
	/**
	 * 保存kpiGroup信息
	 * @param personKpiGroup
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("addKpiGroup.do")
	public String addKpiGroup(KpiApplyForm kpiApplyForm, HttpServletRequest request){
		
		try{
			kpiGroupService.addKpiGroup(kpiApplyForm, request);
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
	 * 修改kpiGroup
	 * @param personKpiGroup
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updateKpiGroup.do")
	public String updateKpiGroup(PersonKpiGroup personKpiGroup, HttpServletRequest request){
		try{
			kpiGroupService.updateKpiGroup(personKpiGroup, request);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 根据id去删除kpi组的信息
	 * @param kpiGroupId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("deleteKpiGroup.do")
	public String deleteKpiGroup(KpiApplyForm kpiApplyForm, HttpServletRequest request){
		
		try{
			kpiGroupService.deleteKpiGroup(kpiApplyForm, request);
			return Constant.SUCCESS_CODE;
		}catch (RuntimeException e) {
			return Constant.DATA_ERROR_CODE;
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
	
	/**
	 * 根据时间查询kpi信息
	 * @param model
	 * @param personKpi
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectKpiByTime.do")
	public String selectKpiByTime(KpiApplyForm applyForm, HttpServletRequest request){
		
		try{
			PageModel<KpiApplyForm> pageModel = kpiGroupService.selectKpiByTime(applyForm, request);
			Gson gson = new Gson();
			return gson.toJson(pageModel);
		}catch (Exception e) {
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
	/**
	 * 保存kpi标准信息
	 * @param personKpi
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("insertKpi.do")
	public String insertKpi(PersonKpi personKpi, HttpServletRequest request){
		
		try{
			kpiGroupService.insertKpi(personKpi, request);
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
	 * 修改kpi标准 根据kpi标准的id
	 * @param personKpi
	 * @param request
	 * @param model
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updateKpi.do")
	public String updateKpi(PersonKpi personKpi,HttpServletRequest request,Model model){
		
		try{
			kpiGroupService.updateKpi(personKpi, request);
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
	 * 删除kpi标准
	 * @param kpiId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("deleteKpi.do")
	public String deleteKpi(String kpiId, HttpServletRequest request){
		try{
			kpiGroupService.deleteKpi(kpiId, request);
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
