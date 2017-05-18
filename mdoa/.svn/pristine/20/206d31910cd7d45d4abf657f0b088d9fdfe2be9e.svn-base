package com.mdoa.framework.dao;

import java.util.List;

import com.mdoa.framework.model.FrameworkTree;
import com.mdoa.framework.model.Power;

public interface PowerDao {
	
	/**
	 * 通过父级
	 * @param superPowerId
	 * @return 子级权限列表
	 */
	public List<FrameworkTree> getPowerBySuper(String superPowerId);
	
	/**
	 * 通过UserId获取该用户所具有的所有权限
	 * @param userId 用户Id
	 * @return 权限列表
	 */
	public List<Power> getPowerByUser(String userId);
	/**
	 * 添加权限
	 * @param power
	 * @return
	 */
	public boolean addUserPower(Power power);
	/**
	 * 查询权限表
	 * @return
	 */
	public List<Power> queryPower(Power power);
}
