package com.htsoft.oa.service.system.impl;
/*
 *  杭州梦德软件有限公司
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.UserOrgDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.UserOrg;
import com.htsoft.oa.service.system.UserOrgService;

public class UserOrgServiceImpl extends BaseServiceImpl<UserOrg> implements UserOrgService{
	@SuppressWarnings("unused")
	private UserOrgDao dao;
	
	public UserOrgServiceImpl(UserOrgDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	/**
	 * 取某一用户所有的部门
	 * @param userId
	 * @return
	 */
	public List<UserOrg> getDepOrgsByUserId(Long userId){
		return dao.getDepOrgsByUserId(userId);
	}

	/**
	 * 删除UserOrg对象
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public void delUserOrg(Long orgId) {
		dao.delUserOrg(orgId);
	}
	
	public List<UserOrg> getByUserIdOrgId(Long userId,Long orgId){
		return dao.getByUserIdOrgId(userId,orgId);
	}

	/**
	 * 删除UserOrg对象
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public void delCascade(Long userId, Long orgId) {
		dao.delCascade(userId,orgId);
	}

	/**
	 * 获取用户及部门Id
	 * @param orgId
	 * @return
	 */
	public List<AppUser> getUsersByOrgId(Long orgId) {
		return dao.getUsersByOrgId(orgId);
	}
	
	/**
	 * 修改组织主要负责人
	 * @param uids
	 * @param orgId
	 */
	public void updOrgCharge(String uids, Long orgId){
		dao.updOrgCharge(uids, orgId);
	}
	/**
	 * 删除用户及部门信息
	 * @param userId
	 * @param orgId
	 * @param isPrimary
	 * @return
	 */
	public void delCascade(Long userId,Long orgId,Short isPrimary){
		 dao.delCascade(userId, orgId, isPrimary);
	}
	
	/**
	 * 获取用户及主部门
	 * @param orgId
	 * @return
	 */
	public List<AppUser> getUsersByOrgIdandIsPrimary(Long orgId,Short isPrimary){
		return dao.getUsersByOrgIdandIsPrimary(orgId, isPrimary);
	}
	
	/**
	 * 获取部门
	 * @param orgId
	 * @return
	 */
	public List<Organization> getByUserId(Long userId){
		return dao.getByUserId(userId);
	}
}