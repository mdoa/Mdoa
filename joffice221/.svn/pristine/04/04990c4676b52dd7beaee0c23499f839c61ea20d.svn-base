package com.htsoft.oa.dao.system.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.FormFieldDao;
import com.htsoft.oa.dao.system.OrganizationDao;
import com.htsoft.oa.dao.system.SubordinateDao;
import com.htsoft.oa.dao.system.UserOrgDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.model.system.RelativeUser;
import com.htsoft.oa.model.system.Subordinate;
import com.htsoft.oa.model.system.UserOrg;

@SuppressWarnings("unchecked")
public class SubordinateDaoImpl extends BaseDaoImpl<Subordinate> implements SubordinateDao{

	public SubordinateDaoImpl() {
		super(Subordinate.class);
	}
	
	/**
	 * 获取上下级记录
	 * @param userId
	 * @param demId
	 * @param isSuper
	 * @return
	 */
	public List<Subordinate> findByCondition(Long userId, Long demId, Short relative) {
		String hql = "select s from Subordinate s where s.appUser.userId = ? and s.demension.demId = ? and s.relative = ? ";
		Object[] paramList = {userId, demId, relative};
		return findByHql(hql, paramList);
	}
	
	
	/**
	 * 根据userId和jobUserId及demId查询对应数据的总条数，返回:对应的总数据条数
	 */	
	public AppUser judge(Long userId, Long jobUserId,Long demId) {
		String hql = "select r from Subordinate r where r.appUser.userId = ? and r.jobUser.userId = ? and r.demension.demId = ?";
		Object[] paramList = { userId, jobUserId,demId};
		List<Subordinate> list = findByHql(hql, paramList);
		return (list != null && list.size() > 0) ? list.get(0).getJobUser()
				: null;
	}
	
	/**
	 * 获取上下级记录
	 * @param userId
	 * @param jobUserId
	 * @return
	 */
	public Subordinate getByBothUserId(Long userId,  Long jobUserId) {
		String hql = "select s from Subordinate s where s.appUser.userId = ? and s.jobUser.userId = ? ";
		Object[] paramList = {userId, jobUserId};
		List<Subordinate> list=findByHql(hql, paramList);
		return (list != null && list.size() > 0) ? list.get(0)
				: null;
	}	
	
	/**
	 * 取得某个用户的上级
	 */
	public Set<AppUser> getUpUser(Long userId){
		//分两种情况 
		//1:自己添加上级 
		String hql1 = "select  ru.jobUser from Subordinate ru where ru.appUser.userId =? and ru.relative =?";
		List list1 = findByHql(hql1, new Object[]{userId,Subordinate.SUPER_FLAG_TRUE});
		
		//2:别人把自己加为下级
		String hql2 = "select  ru.appUser from Subordinate ru where  ru.jobUser.userId = ? and ru.relative = ?";
		List list2 = findByHql(hql2, new Object[]{userId,Subordinate.SUPER_FLAG_FALSE});
		
		Set<AppUser> result = new HashSet<AppUser>();
		result.addAll(list1);
		result.addAll(list2);
		
		return result;
	}
}