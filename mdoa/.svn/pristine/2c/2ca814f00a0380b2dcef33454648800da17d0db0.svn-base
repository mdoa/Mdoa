package com.mdoa.personnel.dao;

import java.util.List;

import com.mdoa.personnel.bo.AttendanceClassForm;
import com.mdoa.personnel.bo.AttendanceGroupForm;
import com.mdoa.personnel.bo.AttendanceUserInfoForm;
import com.mdoa.personnel.model.AttendanceClass;
import com.mdoa.personnel.model.AttendanceGroup;
import com.mdoa.user.model.UserInfo;

public interface AttendanceDao {
	
	//获取uuid
	String getuuid();
	
	/**
	 * 插入考勤组
	 * @param attendanceGroupForm
	 * @return
	 */
	boolean insertGroup(AttendanceGroupForm attendanceGroupForm);
	
	/**
	 * 查询考勤组
	 * @param attendanceGroupForm
	 * @return
	 */
	List<AttendanceGroup> findGroupByCondition(AttendanceGroupForm attendanceGroupForm);
	
	/**
	 * 更新考勤组
	 * @param attendanceGroupForm
	 * @return
	 */
	boolean updateGroupByCondition(AttendanceGroupForm attendanceGroupForm);
	
	/**
	 * 查询考勤组人数
	 * @param groupId
	 * @return
	 */
	Integer findGroupMemberNum(String groupId);
	
	/**
	 * 删除考勤组
	 * @param attendanceGroupForm
	 * @return
	 */
	boolean deleteGroup(AttendanceGroupForm attendanceGroupForm);
	
	/**
	 * 添加人员进考勤组
	 * @param userInfo
	 */
	void addGroupMemberByAdvancedQuery(AttendanceUserInfoForm userInfo);
	
	/**
	 * 添加人员进考勤组
	 * @param userInfo
	 */
	void addGroupMemberByIds(AttendanceUserInfoForm userInfo);
	
	/**
	 * 从考勤组移除人员
	 * @param userInfo
	 */
	void removeGroupMember(AttendanceUserInfoForm userInfo);
	
	/**
	 * 插入班次
	 * @param attendanceClassForm
	 * @return
	 */
	boolean insertClass(AttendanceClassForm attendanceClassForm);
	
	/**
	 * 查询班次
	 * @param attendanceClassForm
	 * @return
	 */
	List<AttendanceClass> findClassByCondition(AttendanceClassForm attendanceClassForm);
	
	/**
	 * 更新班次
	 * @param attendanceClassForm
	 * @return
	 */
	boolean updateClassByCondition(AttendanceClassForm attendanceClassForm);
	
	/**
	 * 删除班次
	 * @param attendanceClassForm
	 * @return
	 */
	boolean deleteClass(AttendanceClassForm attendanceClassForm);

	
	
}
