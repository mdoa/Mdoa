package com.htsoft.oa.dao.system.impl;
/*
 *  杭州梦德软件有限公司
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import org.hibernate.Query;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.UserOrgDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.UserOrg;

@SuppressWarnings("unchecked")
public class UserOrgDaoImpl extends BaseDaoImpl<UserOrg> implements UserOrgDao{

	public UserOrgDaoImpl() {
		super(UserOrg.class);
	}
	/**
	 * 取某一用户所有的部门
	 * @param userId
	 * @return
	 */
	public List<UserOrg> getDepOrgsByUserId(Long userId){
//		String hql="from UserOrg uo where uo.appUser.userId=? and (uo.organization.orgType=? or uo.organization.orgType=?)";
//		return findByHql(hql,new Object[]{userId,Organization.ORG_TYPE_COMPANY,Organization.ORG_TYPE_DEPARTMENT});
		String hql="from UserOrg uo where uo.appUser.userId=? ";
		return findByHql(hql,new Object[]{userId});
	}
	
	/**
	 * 删除UserOrg对象
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public void delUserOrg(Long orgId) {
		String hql = "delete UserOrg uo where uo.organization.orgId=?";
		getSession().createQuery(hql);
	}
	
	public List<UserOrg> getByUserIdOrgId(Long userId,Long orgId){
		String hql="from UserOrg uo where uo.appUser.userId=? and uo.organization.orgId=?";
		return(List<UserOrg>) findByHql(hql,new Object[]{userId,orgId});
	}
	
	/**
	 * 删除UserOrg对象
	 * @param userId
	 * @param orgId
	 * @return
	 */
	public void delCascade(Long userId, Long orgId){
		String hql = "delete UserOrg uo where uo.appUser.userId = "+userId;
		if(orgId!=0){
			hql+=" and uo.organization.orgId="+ orgId;
		}
		getSession().createQuery(hql).executeUpdate();
	}
	
	/**
	 * 获取用户及部门Id
	 * @param orgId
	 * @return
	 */
	public List<AppUser> getUsersByOrgId(Long orgId){
		String hql="select distinct uo.appUser from UserOrg uo where uo.organization.orgId=?";
		return(List)findByHql(hql,new Object[]{orgId});
	}
	
	/**
	 * 修改组织主要负责人
	 * @param uids
	 * @param orgId
	 */
	public void updOrgCharge(String uids, Long orgId){
		String hql = "update UserOrg uo set uo.isCharge=0 " +
				"where uo.organization.orgId="+orgId+" and uo.appUser.userId in ("+uids+") ";
		getSession().createQuery(hql).executeUpdate();
	}
	/**
	 * 获取用户及主部门
	 * @param orgId
	 * @return
	 */
	public List<AppUser> getUsersByOrgIdandIsPrimary(Long orgId,Short isPrimary){
		String hql="select distinct uo.appUser from UserOrg uo where uo.organization.orgId=? and uo.isPrimary=?";
		return(List)findByHql(hql,new Object[]{orgId,isPrimary});
	}
	/**
	 * 删除用户及部门信息
	 * @param userId
	 * @param orgId
	 * @param isPrimary
	 * @return
	 */
	public void delCascade(Long userId,Long orgId,Short isPrimary){
		String hql = "delete from UserOrg uo where uo.appUser.userId = "+userId+" and uo.isPrimary="+isPrimary;
		if(orgId!=0){
			hql+=" and uo.organization.orgId="+ orgId;
		}
		getSession().createQuery(hql).executeUpdate();	
	}
	
	/**
	 * 获取部门
	 * @param orgId
	 * @return
	 */
	public List<Organization> getByUserId(Long userId){
		String hql="select distinct uo.organization from UserOrg uo where uo.appUser.userId=? ";
		return(List)findByHql(hql,new Object[]{userId});
	}
}