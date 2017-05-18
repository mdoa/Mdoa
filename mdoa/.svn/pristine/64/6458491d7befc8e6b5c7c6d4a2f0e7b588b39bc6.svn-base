package com.mdoa.personnel.dao;

import java.util.HashMap;
import java.util.List;

import com.mdoa.personnel.bo.KpiApplyForm;
import com.mdoa.personnel.bo.KpiRecordForm;
import com.mdoa.personnel.model.PersonKpiGroupRecord;
import com.mdoa.personnel.model.PersonKpiRecord;

//员工kpi打分记录表
public interface PersonKpiRecordDao{
	
	/**
	 * 根据时间去查询员工打分表
	 * @param personKpiGroupRecord
	 * @return
	 */
	List<KpiApplyForm> selectKpiRecordByTime(KpiApplyForm kpiApplyForm);
	/**
	 * 新建员工打分记录表
	 * @param personKpiRecord
	 * @return
	 */
	boolean insertKpiRecord(PersonKpiRecord personKpiRecord);
	/**
	 * 创建员工打分记录组
	 * @param personKpiGroupRecord
	 * @return
	 */
	boolean insertKpiRecordGroup(PersonKpiGroupRecord personKpiGroupRecord);
	/**
	 * 查询组记录下的分数记录
	 * @param kpiRecordForm
	 * @return
	 */
	List<PersonKpiRecord> selectKpiRecordByGroupId(KpiRecordForm kpiRecordForm); 
	/**
	 * 根据userId去查询kpi打分记录
	 * @param kpiRecordForm
	 * @return
	 */
	List<PersonKpiRecord> selectKpiGroupRecordByUserId(KpiRecordForm kpiRecordForm);
	/**
	 * 修改kpi员工打分表
	 * @param personKpiRecord
	 * @return
	 */
	boolean updateKpiRecord(PersonKpiRecord personKpiRecord);
	/**
	 * 修改员工打分记录组
	 * @param personKpiGroupRecord
	 * @return
	 */
	boolean updateKpiGroupRecord(PersonKpiGroupRecord personKpiGroupRecord);
	/**
	 * 删除员工打分表
	 * @param params
	 * @return
	 */
	boolean deleteKpiRecord(HashMap<String, String> params);
	/**
	 * 删除员工打分记录组
	 * @param params
	 * @return
	 */
	boolean deleteKpiGroupRecord(HashMap<String, String> params);
}
