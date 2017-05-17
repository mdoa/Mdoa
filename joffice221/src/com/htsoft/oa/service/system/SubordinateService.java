package com.htsoft.oa.service.system;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;
import java.util.Set;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.Subordinate;

public interface SubordinateService extends BaseService<Subordinate>{
	
	/**
	 * 获取上下级记录
	 * @param userId
	 * @param demId
	 * @param isSuper
	 * @return
	 */
	public List<Subordinate> findByCondition(Long userId, Long demId, Short relative);
	/**
	 * 根据userId和jobUserId及demId查询对应数据的总条数，返回:对应的总数据条数
	 */	
	public AppUser judge(Long userId, Long jobUserId,Long demId);
	/**
	 * 获取上下级记录
	 * @param userId
	 * @param jobUserId
	 * @return
	 */
	public Subordinate getByBothUserId(Long userId,  Long jobUserId);
	//查询某维度下某用户所属组织
	public Organization getUserOrg(String demId,	String userId );
	
	/**
	 * 获取某维度下的某用户某级用户
	 * @param userId
	 * @param jobUserId
	 * @return
	 */
	public List<AppUser> getByLevel(Long userId,Long demId,Integer level);
	
	/**
	 * 取得某个用户的上级
	 */
	public Set<AppUser> getUpUser(Long userId);
}


