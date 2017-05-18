package com.mdoa.personnel.controller;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.google.gson.Gson;
import com.mdoa.constant.Constant;
import com.mdoa.personnel.bo.KpiApplyForm;
import com.mdoa.personnel.bo.KpiRecordForm;
import com.mdoa.personnel.model.PersonKpiGroupRecord;
import com.mdoa.personnel.model.PersonKpiRecord;
import com.mdoa.personnel.service.PersonKpiRecordService;
import com.mdoa.util.PageModel;

@Controller
@RequestMapping("personnel")
public class PersonKpiRecordController {

	@Autowired
	public PersonKpiRecordService kpiRecordService;
	
	/**
	 * 
	 * @param kpiRecordForm
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectKpiRecordByTime.do")
	public String selectKpiRecordByTime(KpiApplyForm kpiApplyForm){
		try{	
			PageModel<KpiApplyForm> pageModel = kpiRecordService.selectKpiRecordByTime(kpiApplyForm);
			Gson gson = new Gson();
		    return gson.toJson(pageModel);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}	
	}	
	
	/**
	 * 给员工进行打分的操作
	 * @param personKpiRecord
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("insertKpiRecord.do")
	public String insertKpiRecord(String json, HttpServletRequest request){
		try{
			JSONArray jsonArray = new JSONArray(json);
			System.out.println(jsonArray.length());
			for(int i = 0;i <jsonArray.length(); i++){
				String reason = jsonArray.getJSONObject(i).getString("reason");
				String kpiId = jsonArray.getJSONObject(i).getString("kpiId");
				Integer userScore = (int) jsonArray.getJSONObject(i).getLong("userScore");
				Integer standardScore = (int) jsonArray.getJSONObject(i).getLong("standardScore");
				String groupRecordId = jsonArray.getJSONObject(0).getString("groupRecordId");	
				PersonKpiRecord kpiRecord = new PersonKpiRecord();
				kpiRecord.setKpiGroupRecordId(groupRecordId);
				kpiRecord.setKpiId(kpiId);
				kpiRecord.setReason(reason);
				kpiRecord.setUserScore(userScore);
				kpiRecord.setStandardScore(standardScore);
				kpiRecordService.insertKpiRecord(kpiRecord, request);
			}
			String groupRecordId =jsonArray.getJSONObject(0).getString("groupRecordId");
			Integer scoreStandard = (int) jsonArray.getJSONObject(0).getLong("scoreStandard");
			Integer scoreUser = (int) jsonArray.getJSONObject(0).getLong("scoreUser");
			PersonKpiGroupRecord kpiGroupRecord = new PersonKpiGroupRecord();
			kpiGroupRecord.setGroupRecordId(groupRecordId);
			kpiGroupRecord.setScoreStandard(scoreStandard);
			kpiGroupRecord.setScoreUser(scoreUser);
			kpiRecordService.updateKpiGroupRecord(kpiGroupRecord, request);
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
	 * 创建员工打分记录组
	 * @param personKpiGroupRecord
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("insertKpiRecordGroup.do")
	public String insertKpiRecordGroup(PersonKpiGroupRecord personKpiGroupRecord, HttpServletRequest request){
		try{
			kpiRecordService.insertKpiRecordGroup(personKpiGroupRecord, request);
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
	 * 查询组记录下的分数记录
	 * @param kpiRecordForm
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectKpiRecordByGroupId.do")
	public String selectKpiRecordByGroupId(KpiRecordForm kpiRecordForm){
		try{
		    PageModel<PersonKpiRecord> pageModel = kpiRecordService.selectKpiRecordByGroupId(kpiRecordForm);
		    Gson gson = new Gson();
		    return gson.toJson(pageModel);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	/**
	 * 根据userId去查询kpi打分记录
	 * @param kpiRecordForm
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("selectKpiGroupRecordByUserId")
	public String selectKpiGroupRecordByUserId(KpiRecordForm kpiRecordForm){
		try{
		    PageModel<PersonKpiRecord> pageModel = kpiRecordService.selectKpiGroupRecordByUserId(kpiRecordForm);
		    Gson gson = new Gson();
		    return gson.toJson(pageModel);
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	/**
	 * 修改kpi员工打分表
	 * @param personKpiRecord
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updateKpiRecord.do")
	public String updateKpiRecord(PersonKpiRecord personKpiRecord, HttpServletRequest request){
		try{
			kpiRecordService.updateKpiRecord(personKpiRecord, request);
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
	 * 修改员工打分记录组
	 * @param personKpiGroupRecord
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("updateKpiGroupRecord.do")
	public String updateKpiGroupRecord(PersonKpiGroupRecord personKpiGroupRecord, HttpServletRequest request){
		
		try{
			kpiRecordService.updateKpiGroupRecord(personKpiGroupRecord, request);
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
	 * 删除员工打分表
	 * @param recordId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("deleteKpiRecord.do")
	public String deleteKpiRecord(String recordId, HttpServletRequest request){
		try{
			kpiRecordService.deleteKpiGroupRecord(recordId, request);
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
	 * 删除员工打分记录组
	 * @param recordId
	 * @return
	 */
	@ResponseBody 
	@RequestMapping("deleteKpiGroupRecord.do")
	public String deleteKpiGroupRecord(String groupRecordId, HttpServletRequest request){
		
		try{
			kpiRecordService.deleteKpiGroupRecord(groupRecordId, request);
			return Constant.SUCCESS_CODE;
		}catch(RuntimeException e){
			e.printStackTrace();
			return Constant.DATA_ERROR_CODE;
		}catch(Exception e){
			e.printStackTrace();
			return Constant.SERVER_ERROR_CODE;
		}
	}
	
	
}