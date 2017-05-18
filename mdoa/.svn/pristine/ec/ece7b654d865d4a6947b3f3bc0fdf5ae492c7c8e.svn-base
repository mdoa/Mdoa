package com.mdoa.personnel.dao;

import java.util.List;

import com.mdoa.personnel.model.Dimission;
import com.mdoa.user.model.UserInfo;

public interface DimissionDao {
	
	/**
	 * 获取员工的离职记录列表信息
	 * @param userInfo
	 * @return
	 */
	List<Dimission> getDimissionList(Dimission dimission);
	
	/**
	 * 添加员工的离职记录
	 * @param dimission
	 * @return
	 */
	boolean addDimissionRecord(Dimission dimission);
	
	/**
	 * 删除离职记录
	 */
	boolean deleteDimissionRecord(String userId);
	
	/**
	 * 验证是否有在职的该身份证号员工
	 */
	Integer checkOnJobUser(String userId);
}
