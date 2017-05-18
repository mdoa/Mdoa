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
import com.mdoa.base.model.BaseModel;
import com.mdoa.base.service.BaseService;
import com.mdoa.personnel.bo.KpiApplyForm;
import com.mdoa.personnel.dao.PersonKpiGroupDao;
import com.mdoa.personnel.model.PersonKpi;
import com.mdoa.personnel.model.PersonKpiGroup;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
@Transactional
public class PersonKpiGroupService extends BaseService{

	@Autowired
	public PersonKpiGroupDao kpiGroupDao;
	/**
	 * 给员工分配kpi组 
	 * @param userInfo
	 */
	public void updateUserKpiGroup(UserInfo userInfo, HttpServletRequest request){
		UserInfo user = getUser(request);
		userInfo.setUpdateUserId(user.getUpdateUserId());
		userInfo.setUpdateUserName(user.getUpdateUserName());
		if(!kpiGroupDao.updateUserKpiGroup(userInfo)){
			throw new RuntimeException("员工分配kpi组失败");
		}
	}
	/**
	 * 新建打分记录的时候回显的kpi标准和kpi组的信息
	 * @param userId
	 * @return
	 */
	public List<KpiApplyForm> selectKpiGroupByUserId(String userId,String kpiGroupId){
		HashMap<String, String> params  = new HashMap<String, String>();
		params.put("userId", userId);
		params.put("kpiGroupId", kpiGroupId);
		List<KpiApplyForm> list = kpiGroupDao.selectKpiGroupByUserId(params);
		return list;
	}
	/**
	 * 根据时间去查询kpi组信息
	 * @param model
	 * @param personKpiGroup
	 * @return
	 */
	public PageModel<PersonKpiGroup> selectKpiGroupByTime(PersonKpiGroup personKpiGroup){
		if (!StringUtil.isEmpty(personKpiGroup.getKpiGroupName())) {
			personKpiGroup.setKpiGroupName(personKpiGroup.getKpiGroupName()+"%");
		}
		if (!StringUtil.isEmpty(personKpiGroup.getCreateUserName())) {
			personKpiGroup.setCreateUserName(personKpiGroup.getCreateUserName()+"%");
		}
		PageHelper.startPage(personKpiGroup.getPage(), personKpiGroup.getRows());
		List<PersonKpiGroup> list = kpiGroupDao.selectKpiGroupByTime(personKpiGroup);
		PageModel<PersonKpiGroup> pageInfo = new PageModel((Page)list);
		return pageInfo;
	}
	
	/**
	 * 查看打分详细情况的时候显示的信息
	 * @param kpiApplyForm
	 * @return
	 */
	public PageModel<KpiApplyForm> selectKpiRecordByGroup(KpiApplyForm kpiApplyForm){
		PageHelper.startPage(kpiApplyForm.getPage(), kpiApplyForm.getRows());
		List<KpiApplyForm> list = kpiGroupDao.selectKpiRecordByGroup(kpiApplyForm.getKpiGroupId());
		PageModel<KpiApplyForm> pageInfo = new PageModel((Page)list);
		return pageInfo;
	}
	
	/**
	 * 保存kpi组的信息
	 * @param personKpiGroup
	 */
	public void addKpiGroup(KpiApplyForm kpiApplyForm, HttpServletRequest request){
		UserInfo user = getUser(request);
		kpiApplyForm.setCreateUserId(user.getCreateUserId());
		if(!kpiGroupDao.addKpiGroup(kpiApplyForm)){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 根据kpi组的ID去删除kpi组
	 * @param kpiGroupId
	 */
	public void deleteKpiGroup(KpiApplyForm kpiApplyForm, HttpServletRequest request){
		
		UserInfo user = getUser(request);
		kpiApplyForm.setUpdateUserId(user.getUpdateUserId());
		if(!kpiGroupDao.deleteKpiGroup(kpiApplyForm)){
			throw new RuntimeException();
		}
		
	}
	/**
	 * 修改kpiGroup
	 * @param personKpiGroup
	 */
	public void updateKpiGroup(PersonKpiGroup personKpiGroup, HttpServletRequest request){
		UserInfo user = getUser(request);
		personKpiGroup.setUpdateUserId(user.getUpdateUserId());
		personKpiGroup.setUpdateUserName(user.getUpdateUserName());
		if(!kpiGroupDao.updateKpiGroup(personKpiGroup)){
			throw new RuntimeException("修改失败");
		}
		
	}
	
	
//========================================================================
	/**
	 * 分页查询kpi标准信息
	 * @param model
	 * @param personKpi
	 * @return
	 */
	public PageModel<KpiApplyForm> selectKpiByTime(KpiApplyForm applyForm, HttpServletRequest request){
		if(!StringUtil.isEmpty(applyForm.getKpiName())){
			applyForm.setKpiName("%"+applyForm.getKpiName()+"%");
		}
		PageHelper.startPage(applyForm.getPage(), applyForm.getRows());
		List<KpiApplyForm> list = kpiGroupDao.selectKpiByTime(applyForm);
		PageModel<KpiApplyForm> pageModel = new PageModel((Page)list);
		return pageModel;
	}
	/**
	 *  先得到kpi组的id，然后保存kpi标准信息
	 * @param personKpi
	 */
	public void insertKpi(PersonKpi personKpi, HttpServletRequest request){
		UserInfo user = getUser(request);
		personKpi.setCreateUserId(user.getCreateUserId());
		if(!kpiGroupDao.insertKpi(personKpi)){
			throw new RuntimeException("保存kpi标准信息失败");
		}
	}
	
	/**
	 * 修改kpi标准信息
	 * @param personKpi
	 */
	public void updateKpi(PersonKpi personKpi, HttpServletRequest request){
		UserInfo user = getUser(request);
		personKpi.setUpdateUserId(user.getUpdateUserId());
		if(!kpiGroupDao.updateKpi(personKpi)){
			throw new RuntimeException();
		}
	}
	
	/**
	 * 根据kpi标准id去删除
	 * @param kpiId
	 */
	public void deleteKpi(String kpiId, HttpServletRequest request){
		UserInfo user = getUser(request);
		PersonKpi personKpi = new PersonKpi();
		personKpi.setUpdateUserId(user.getUpdateUserId());
		if(!kpiGroupDao.deleteKpi(kpiId)){
			throw new RuntimeException();
		}
	}
}
