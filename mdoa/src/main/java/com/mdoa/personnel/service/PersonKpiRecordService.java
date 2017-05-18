package com.mdoa.personnel.service;

import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.mdoa.base.service.BaseService;
import com.mdoa.personnel.bo.KpiApplyForm;
import com.mdoa.personnel.bo.KpiRecordForm;
import com.mdoa.personnel.dao.PersonKpiRecordDao;
import com.mdoa.personnel.model.PersonKpiGroupRecord;
import com.mdoa.personnel.model.PersonKpiRecord;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class PersonKpiRecordService extends BaseService{

	@Autowired
	public PersonKpiRecordDao kpiRecordDao;
	
	/**
	 * 根据时间去查询员工打分表
	 * @param kpiRecordForm
	 * @return
	 */
	public PageModel<KpiApplyForm> selectKpiRecordByTime(KpiApplyForm kpiApplyForm){
		if (!StringUtil.isEmpty(kpiApplyForm.getKpiGroupName())) {
			kpiApplyForm.setKpiGroupName("%"+kpiApplyForm.getKpiGroupName()+"%");
		}
		if(!StringUtil.isEmpty(kpiApplyForm.getMarkMonth())){
			kpiApplyForm.setMarkMonth("%"+kpiApplyForm.getMarkMonth()+"%");
		}	
		if (!StringUtil.isEmpty(kpiApplyForm.getMarkUserName())) {
			kpiApplyForm.setMarkUserName("%"+kpiApplyForm.getMarkUserName()+"%");
		}
		if (!StringUtil.isEmpty(kpiApplyForm.getUserName())) {
			kpiApplyForm.setUserName("%"+kpiApplyForm.getUserName()+"%");
		}
		PageHelper.startPage(kpiApplyForm.getPage(), kpiApplyForm.getRows());
		List<KpiApplyForm> list = kpiRecordDao.selectKpiRecordByTime(kpiApplyForm);
		PageModel<KpiApplyForm> pageModel = new PageModel((Page)list);
		return pageModel;
	}	
	/**
	 * 新建员工打分记录表
	 * @param personKpiRecord
	 */
	public void insertKpiRecord(PersonKpiRecord personKpiRecord, HttpServletRequest request){
		UserInfo user = getUser(request);
		personKpiRecord.setCreateUserId(user.getCreateUserId());
		personKpiRecord.setCreateUserName(user.getCreateUserName());
		if(!kpiRecordDao.insertKpiRecord(personKpiRecord)){
			throw new RuntimeException();
		}	
	}
	
	/**
	 * 创建员工打分记录组
	 * @param personKpiGroupRecord
	 */
	public void insertKpiRecordGroup(PersonKpiGroupRecord personKpiGroupRecord, HttpServletRequest request){
		UserInfo user = getUser(request);
		personKpiGroupRecord.setCreateUserId(user.getCreateUserId());
		personKpiGroupRecord.setCreateUserName(user.getCreateUserName());
		if(!kpiRecordDao.insertKpiRecordGroup(personKpiGroupRecord)){
			throw new RuntimeException();
		}	
	}
	
	
	/**
	 * 查询组记录下的分数记录
	 * @param kpiRecordForm
	 * @return
	 */
	public PageModel<PersonKpiRecord> selectKpiRecordByGroupId(KpiRecordForm kpiRecordForm){
		
		PageHelper.startPage(kpiRecordForm.getPage(), kpiRecordForm.getRows());
		List<PersonKpiRecord> list = kpiRecordDao.selectKpiRecordByGroupId(kpiRecordForm);
		PageModel<PersonKpiRecord> pageModel = new PageModel((Page)list);
		return pageModel;
	}
	
	/**
	 * 根据userId去查询kpi打分记录
	 * @param kpiRecordForm
	 * @return
	 */
	public PageModel<PersonKpiRecord> selectKpiGroupRecordByUserId(KpiRecordForm kpiRecordForm){
		PageHelper.startPage(kpiRecordForm.getPage(), kpiRecordForm.getRows());
		List<PersonKpiRecord> list = kpiRecordDao.selectKpiGroupRecordByUserId(kpiRecordForm);
		PageModel<PersonKpiRecord> pageModel = new PageModel((Page)list);
		return pageModel;	
	}
	
	/**
	 * 修改kpi员工打分表
	 * @param personKpiRecord
	 */
	public void updateKpiRecord(PersonKpiRecord personKpiRecord, HttpServletRequest request){
		UserInfo user = getUser(request);
		personKpiRecord.setUpdateUserId(user.getUpdateUserId());
		personKpiRecord.setUpdateUserName(user.getUpdateUserName());
		if(!kpiRecordDao.updateKpiRecord(personKpiRecord)){
			throw new RuntimeException();
		}
	}
	/** 
	 * 修改员工打分记录组
	 * @param personKpiGroupRecord
	 */
	public void updateKpiGroupRecord(PersonKpiGroupRecord personKpiGroupRecord, HttpServletRequest request){
		UserInfo user = getUser(request);	
		personKpiGroupRecord.setMarkUserId(user.getUserId());
		personKpiGroupRecord.setMarkUserName(user.getUserName());
		personKpiGroupRecord.setUpdateUserId(user.getUpdateUserId());
		if(!kpiRecordDao.updateKpiGroupRecord(personKpiGroupRecord)){
			throw new RuntimeException();
		}
	}
	/**
	 * 删除员工打分表
	 * @param params
	 */
	public void deleteKpiRecord(String recordId, HttpServletRequest request){
		
		PersonKpiRecord personKpiRecord = new PersonKpiRecord();
		UserInfo user = getUser(request);
		personKpiRecord.setUpdateUserId(user.getUpdateUserId());
		personKpiRecord.setUpdateUserName(user.getUpdateUserName());
		HashMap<String, String> params  = new HashMap<String, String>();
		params.put("recordId", recordId);
		if(!kpiRecordDao.deleteKpiRecord(params)){
			throw new RuntimeException();
			
		}
	}
	/**
	 * 删除员工打分记录组
	 * @param params
	 */
	public void deleteKpiGroupRecord(String groupRecordId, HttpServletRequest request){
		
		PersonKpiGroupRecord personKpiGroupRecord = new PersonKpiGroupRecord();
		UserInfo user = getUser(request);
		personKpiGroupRecord.setUpdateUserId(user.getUpdateUserId());
		HashMap<String, String> params  = new HashMap<String, String>();
		params.put("groupRecordId", groupRecordId);
		if(!kpiRecordDao.deleteKpiGroupRecord(params)){
			throw new RuntimeException("删除员工打分组失败");
		}
		
	}

	
}
