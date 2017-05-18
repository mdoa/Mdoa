package com.mdoa.user.dao;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;

import com.mdoa.personnel.model.PersonEducation;
import com.mdoa.personnel.model.PersonTrain;
import com.mdoa.personnel.model.PersonWork;
import com.mdoa.user.model.UserInfo;
import com.mdoa.user.model.UserInfoOther;
import com.mdoa.user.model.UserTransfer;

public interface UserInfoDao {
	/**
	 * 获取唯一主键uuid
	 */
	String getuuid();
	
	/**
	 * 根据前端传入的信息，添加用户基本信息，然后返回用户Id
	 * @param userInfo
	 * @return
	 */
	boolean saveUserInfo(UserInfo userInfo);
	
	/**
	 * 根据前端传输来的用户Id和工作信息，将员工工作记录情况保存在数据库中
	 */
	boolean saveWork(PersonWork personWork);
	
	/**
	 * 根据前端传输来的用户Id和培训信息，将员工培训记录情况保存在数据库中
	 */
	boolean saveTrain(PersonTrain personTrain);
	
	/**
	 * 根据前端传输来的用户Id和教育信息，将员工教育记录情况保存在数据库中
	 */
	boolean saveEdu(PersonEducation personEducation);
	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	boolean updateUserInfo(UserInfo userInfo);
	
	/**
	 * 修改工作信息
	 */
	boolean updateWork(PersonWork personWork);
	
	/**
	 * 修改培训信息
	 */
	boolean updateTrain(PersonTrain personTrain);
	
	/**
	 * 修改教育信息
	 */
	boolean updateEdu(PersonEducation personEducation);
	
	/**
	 * 修改用户信息
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> selectUserInfoById(UserInfo userInfo);
	/**
	 * 
	 * @param userInfo
	 * @return
	 */
	List<UserInfo> selectUserInfo(UserInfo userInfo);
	
	/**
	 * 修改工作信息
	 */
	List<PersonWork> selectWork(PersonWork personWork);
	
	/**
	 * 修改培训信息
	 */
	List<PersonTrain> selectTrain(PersonTrain personTrain);
	
	/**
	 * 修改教育信息
	 */
	List<PersonEducation> selectEdu(PersonEducation personEducation);
	
	/**
	 * 查询所有退休员工的信息
	 * @return
	 */
	List<UserInfo> findRetireUserInfo();
	/**
	 * 重置密码
	 * @param userInfo
	 * @return
	 */
	boolean resetPassword(UserInfo userInfo);
	
	/**
	 * 添加员工调度信息
	 */
	boolean addUserTransferInfo(UserTransfer userTransfer);
	
	/**
	 * 查询员工调动信息
	 */
	List<UserTransfer> selectUserTransfer(UserTransfer userTransfer);
	
	/**
	 * 修改调动记录表
	 * @param userTransfer
	 * @return
	 */
	boolean updateUserTransfer(UserTransfer userTransfer);
	/**
	 * 修改员工信息表中的部门信息
	 * @param userTransfer
	 * @return
	 */
	boolean updateUserTransferInfo(UserTransfer userTransfer);
	
	/**
	 * 删除人员调动信息
	 * @param userTransfer
	 * @return
	 */
	boolean deleteUserTransfer(UserTransfer userTransfer);
	/**
	 * 批量修改用户信息
	 * @param userTransfer
	 * @return
	 */
	boolean updateUserInfos(UserTransfer userTransfer);
	/**
	 * 批量添加调动记录
	 * @param list
	 * @return
	 */
	boolean addUserTransferInsfo(List<UserTransfer> list);

	/**
	 * 添加人员的其他信息
	 */
	boolean addOtherInfo(UserInfoOther userInfoOther);
	
	/**
	 * 修改人员的其他信息
	 */
	boolean updateOtherInfo(UserInfoOther userInfoOther);
	
	/**
	 * 查询人员的其他信息
	 */
	List<UserInfoOther> selectOtherInfo(UserInfoOther userInfoOther);
}