package com.mdoa.framework.dao;

import java.util.HashMap;
import java.util.List;

import com.mdoa.framework.bo.LeadForm;
import com.mdoa.framework.model.Lead;
import com.mdoa.user.model.UserInfo;

public interface LeadDao {
	/**
	 * 获取员工上级
	 * @param userId 用户的Id
	 * @return 上级信息
	 */
	UserInfo getLeader(LeadForm form);
	
	/**
	 * 获取员工的下级 
	 * @param userId 用户的Id
	 * @return 下级列表
	 */
	List<UserInfo> getLower(LeadForm form);
	
	/**
	 * 为员工添加上级
	 * @param form userId，leaderId
	 * @return 操作是否成功
	 */
	boolean addLeader(LeadForm form);
	
	/**
	 * 为员工添加下级
	 * @param form userId lowerIds
	 * @return 操作是否成功
	 */
	boolean addLower(LeadForm leadForm);
	
	/**
	 * 删除员工上级
	 * @param form userId leaderId
	 * @return  操作是否成功
	 */
	boolean deleteLeader(LeadForm form);
	
	/**
	 * 删除员工下级
	 * @param form userId lowers
	 * @return 操作是否成功
	 */
	boolean deleteLower(LeadForm form);
	
	/**
	 * 根据上下级员工名查询员工上下级关系
	 * @param form userName leadName 
	 */
	List<Lead> getLeadLowerList(LeadForm form);
	
	/**
	 * 修改员工的上级，根据员工的Id进行选择
	 */
	boolean updateUserLead(LeadForm form);
}
