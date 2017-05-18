package com.mdoa.personnel.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mdoa.base.service.BaseService;
import com.mdoa.personnel.bo.AttendanceClassForm;
import com.mdoa.personnel.bo.AttendanceGroupForm;
import com.mdoa.personnel.bo.AttendanceUserInfoForm;
import com.mdoa.personnel.dao.AttendanceDao;
import com.mdoa.personnel.model.AttendanceClass;
import com.mdoa.personnel.model.AttendanceGroup;
import com.mdoa.user.model.UserInfo;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
public class AttendanceService extends BaseService{

	@Autowired
	private AttendanceDao attendanceDao;
	
	/**
	 * 添加考勤组
	 * @param attendanceGroupForm
	 * @param request
	 */
	public void insertGroup(AttendanceGroupForm attendanceGroupForm, HttpServletRequest request) {
		UserInfo userInfo = getUser(request);
		attendanceGroupForm.setCreateUserId(userInfo.getUserId());
		attendanceGroupForm.setCreateUserName(userInfo.getUserName());
		if(!attendanceDao.insertGroup(attendanceGroupForm))
			throw new RuntimeException("插入考勤组失败");
	}
	
	/**
	 * 查询考勤组
	 * @param attendanceGroupForm
	 * @param request
	 * @return 
	 */
	public PageModel<AttendanceGroup> findGroupByCondition(AttendanceGroupForm attendanceGroupForm, HttpServletRequest request) {
		if(!StringUtil.isEmpty(attendanceGroupForm.getGroupName())){
			attendanceGroupForm.setGroupName(StringUtil.toLikeAll(attendanceGroupForm.getGroupName()));
		}
		PageHelper.startPage(attendanceGroupForm.getPage(),attendanceGroupForm.getRows());
		List<AttendanceGroup> list = attendanceDao.findGroupByCondition(attendanceGroupForm);
		PageModel<AttendanceGroup> pageInfo = new PageModel<>((Page<AttendanceGroup>)list);
		return pageInfo;
	}
	
	/**
	 * 更新考勤组
	 * @param attendanceGroupForm
	 * @param request
	 */
	public void updateGroupByCondition(AttendanceGroupForm attendanceGroupForm, HttpServletRequest request) {
		UserInfo userInfo = getUser(request);
		attendanceGroupForm.setUpdateUserId(userInfo.getUserId());
		attendanceGroupForm.setUpdateUserName(userInfo.getUserName());
		if(!attendanceDao.updateGroupByCondition(attendanceGroupForm))
			throw new RuntimeException("更新考勤组失败");
	}
	
	/**
	 * 删除考勤组
	 * @param attendanceGroupForm
	 * @param request
	 */
	public void deleteGroup(AttendanceGroupForm attendanceGroupForm, HttpServletRequest request) {
		UserInfo userInfo = getUser(request);
		attendanceGroupForm.setUpdateUserId(userInfo.getUserId());
		attendanceGroupForm.setUpdateUserName(userInfo.getUserName());
		if(attendanceDao.findGroupMemberNum(attendanceGroupForm.getGroupId()) != 0)
			throw new RuntimeException("考勤组人员不为零，无法删除");
		AttendanceClassForm attendanceClassForm = new AttendanceClassForm();
		//删除考勤组下班次
		attendanceDao.deleteClass(attendanceClassForm);
		if(!attendanceDao.deleteGroup(attendanceGroupForm))
			throw new RuntimeException("删除考勤组失败");
	}
	
	/**
	 * 通过高级查询条件方式添加人员进考勤组
	 * @param userInfo
	 * @param request
	 */
	public void addGroupMemberByAdvacedQuery(AttendanceUserInfoForm userInfo, HttpServletRequest request) {
		
		UserInfo info = getUser(request);
		userInfo.setUpdateUserId(info.getUserId());
		userInfo.setUpdateUserName(info.getUserName());
		
		userInfo.setDepartmentName(StringUtil.toLikeRight(userInfo.getDepartmentName()));
		userInfo.setUserName(StringUtil.toLikeRight(userInfo.getUserName()));
		userInfo.setPostName(StringUtil.toLikeRight(userInfo.getPostName()));
		userInfo.setDepartmentUrl(StringUtil.toLikeRight(userInfo.getDepartmentUrl()));
		userInfo.setAddress(StringUtil.toLikeAll(userInfo.getAddress()));
		userInfo.setNativePlace(StringUtil.toLikeAll(userInfo.getNativePlace()));
		
		attendanceDao.addGroupMemberByAdvancedQuery(userInfo);
	}
	
	/**
	 * 通过多id字符串方式添加人员进考勤组
	 * @param userInfo
	 * @param request
	 */
	public void addGroupMemberByIds(AttendanceUserInfoForm userInfo, HttpServletRequest request) {
		UserInfo info = getUser(request);
		userInfo.setUpdateUserId(info.getUserId());
		userInfo.setUpdateUserName(info.getUserName());
		
		attendanceDao.addGroupMemberByIds(userInfo);
	}
	
	/**
	 * ids方式移除考勤组人员
	 * @param userInfo
	 * @param request
	 */
	public void removeGroupMember(AttendanceUserInfoForm userInfo, HttpServletRequest request) {
		UserInfo info = getUser(request);
		userInfo.setUpdateUserId(info.getUserId());
		userInfo.setUpdateUserName(info.getUserName());
		
		attendanceDao.removeGroupMember(userInfo);
	}
	
	/**
	 * 添加班次
	 * @param attendanceClassForm
	 * @param request
	 */
	public void insertClass(AttendanceClassForm attendanceClassForm, HttpServletRequest request) {
		UserInfo userInfo = getUser(request);
		attendanceClassForm.setCreateUserId(userInfo.getUserId());
		attendanceClassForm.setCreateUserName(userInfo.getUserName());
		if(StringUtil.isEmpty(attendanceClassForm.getGroupId()))
			throw new RuntimeException("班次所属考勤组id为空");
		AttendanceGroupForm attendanceGroupForm = new AttendanceGroupForm();
		attendanceGroupForm.setGroupId(attendanceClassForm.getGroupId());
		List<AttendanceGroup> list = attendanceDao.findGroupByCondition(attendanceGroupForm);
		if(list == null || list.size() == 0)
			throw new RuntimeException("考勤组不存在");
		AttendanceGroup attendanceGroup = list.get(0);
		if(StringUtil.isEmpty(attendanceClassForm.getOnDutyTime())){
			attendanceClassForm.setOnDutyTime(attendanceGroup.getDefaultOnTime());
		}
		if(StringUtil.isEmpty(attendanceClassForm.getOffDutyTime())){
			attendanceClassForm.setOffDutyTime(attendanceGroup.getDefaultOffTime());
		}
		if(!attendanceDao.insertClass(attendanceClassForm))
			throw new RuntimeException("插入班次失败");
	}
	
	/**
	 * 查询班次
	 * @param attendanceClassForm
	 * @param request
	 * @return 
	 */
	public PageModel<AttendanceClass> findClassByCondition(AttendanceClassForm attendanceClassForm, HttpServletRequest request) {
		PageHelper.startPage(attendanceClassForm.getPage(),attendanceClassForm.getRows());
		List<AttendanceClass> list = attendanceDao.findClassByCondition(attendanceClassForm);
		PageModel<AttendanceClass> pageInfo = new PageModel<>((Page<AttendanceClass>)list);
		return pageInfo;
	}
	
	/**
	 * 更新班次
	 * @param attendanceClassForm
	 * @param request
	 */
	public void updateClassByCondition(AttendanceClassForm attendanceClassForm, HttpServletRequest request) {
		UserInfo userInfo = getUser(request);
		attendanceClassForm.setUpdateUserId(userInfo.getUserId());
		attendanceClassForm.setUpdateUserName(userInfo.getUserName());
		if(!attendanceDao.updateClassByCondition(attendanceClassForm))
			throw new RuntimeException("更新班次失败");
	}
	
	/**
	 * 删除班次
	 * @param attendanceClassForm
	 * @param request
	 */
	public void deleteClass(AttendanceClassForm attendanceClassForm, HttpServletRequest request) {
		UserInfo userInfo = getUser(request);
		attendanceClassForm.setUpdateUserId(userInfo.getUserId());
		attendanceClassForm.setUpdateUserName(userInfo.getUserName());
		if(!attendanceDao.deleteClass(attendanceClassForm))
			throw new RuntimeException("删除考勤组失败");
	}
	
	
}
