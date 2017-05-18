package com.mdoa.personnel.dao;

import java.util.HashMap;
import java.util.List;
import com.mdoa.personnel.bo.KpiApplyForm;
import com.mdoa.personnel.model.PersonKpi;
import com.mdoa.personnel.model.PersonKpiGroup;
import com.mdoa.user.model.UserInfo;;

public interface PersonKpiGroupDao{
   
	String getUUID();
	/**
	 * 给员工分配kpi组
	 * @param userInfo
	 * @return
	 */
	boolean updateUserKpiGroup(UserInfo userInfo);
	/**
	 * 查询kpi组的信息
	 * @param personKpiGroup
	 * @return
	 */
	List<PersonKpiGroup> selectKpiGroupByTime(PersonKpiGroup personKpiGroup);
	/**
	 * 查看打分详细情况的时候显示的信息
	 * @param kpiGroupId
	 * @return
	 */
	List<KpiApplyForm> selectKpiRecordByGroup(String kpiGroupId);
	/**
	 * 新建打分记录的时候回显的kpi标准和kpi组的信息
	 * @param map
	 * @return
	 */
	List<KpiApplyForm> selectKpiGroupByUserId(HashMap<String , String> map);
	/**
	 * 新建kpi组
	 * @param kpiApplyForm
	 * @return
	 */
	boolean addKpiGroup(KpiApplyForm kpiApplyForm);
	/**
	 * 修改kpi组的信息
	 * @param personKpiGroup
	 * @return
	 */
	boolean updateKpiGroup(PersonKpiGroup personKpiGroup);
	/**
	 * 删除kpi组
	 * @param kpiApplyForm
	 * @return
	 */
	boolean deleteKpiGroup(KpiApplyForm kpiApplyForm);
	/**
	 * 查询kpi的信息
	 * @param personKpi
	 * @return
	 */
	List<KpiApplyForm> selectKpiByTime(KpiApplyForm applyForm);
	/**
	 * 删除一个kpi标准
	 * @param kpiId
	 * @return
	 */
	boolean deleteKpi(String kpiId);
	/**
	 * 增加kpi标准
	 * @param personKpi
	 * @return
	 */
	boolean insertKpi(PersonKpi personKpi);
	/**
	 * 修改kpi标准的信息
	 * @param personKpi
	 * @return
	 */
	boolean updateKpi(PersonKpi personKpi);
}