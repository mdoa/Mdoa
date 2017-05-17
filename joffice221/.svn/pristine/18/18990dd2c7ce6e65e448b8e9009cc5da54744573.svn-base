package com.htsoft.oa.service.system;
/*
 *  杭州梦德软件有限公司
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.UserOrg;

public interface UserOrgService extends BaseService<UserOrg>{
	/**
	 * 取某一用户所有的部门
	 * @param userId
	 * @return
	 */
	public List<UserOrg> getDepOrgsByUserId(Long userId);
	
	/**
	 * 删除UserOrg对象
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public void delUserOrg(Long orgId);
	/**
	 * 
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public List<UserOrg> getByUserIdOrgId(Long userId,Long orgId);
	
	/**
	 * 删除UserOrg对象
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public void delCascade(Long userId, Long orgId);
	
	/**
	 * 获取用户及部门Id
	 * @param orgId
	 * @return
	 */
	public List<AppUser> getUsersByOrgId(Long orgId);
	
	/**
	 * 修改组织主要负责人
	 * @param uids
	 * @param orgId
	 */
	public void updOrgCharge(String uids, Long orgId);
	/**
	 * 删除用户及部门信息
	 * @param userId
	 * @param orgId
	 * @param isPrimary
	 * @return
	 */
	public void delCascade(Long userId,Long orgId,Short isPrimary);
	
	/**
	 * 获取用户及主部门
	 * @param orgId
	 * @return
	 */
	public List<AppUser> getUsersByOrgIdandIsPrimary(Long orgId,Short isPrimary);
	/**
	 * 获取部门
	 * @param orgId
	 * @return
	 */
	public List<Organization> getByUserId(Long userId);
}


