package com.htsoft.oa.service.system.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.OrganizationDao;
import com.htsoft.oa.dao.system.UserOrgDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Organization;
import com.htsoft.oa.service.system.OrganizationService;

public class OrganizationServiceImpl extends BaseServiceImpl<Organization> implements OrganizationService{
	@SuppressWarnings("unused")
	private OrganizationDao dao;
	@Resource
	private UserOrgDao userOrgDao;
	
	public OrganizationServiceImpl(OrganizationDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	/**
	 * 取得某个节点下的所有子节点
	 * @param parentId
	 * @param demId
	 * @return
	 */
	public List<Organization> getByParent(Long parentId,Long demId){
		return dao.getByParent(parentId, demId);
	}
	
	
	@Override
	public List<Organization> getByDemId(Long demId) {
		return dao.getByDemId(demId);
	}

	/**
	 * 按路径查找所有节点
	 * @param path
	 * @return
	 */
	public List<Organization> getByPath(String path){
		return dao.getByPath(path);
	}
	
	/**
	 * 删除某个组织及其下属组织
	 * @param posId
	 */
	public Boolean delCascade(Long orgId){
		Organization org=get(orgId);
		
		List<Organization> listOrgs=getByPath(org.getPath());
		for(Organization o:listOrgs){
			List<AppUser> UserList=userOrgDao.getUsersByOrgId(o.getOrgId());
			if(UserList.size()>0)
				return false;
		}
		for(Organization o:listOrgs){
			remove(o);
		}
		return true;
	}
	
	/**
	 * 取得某个维度下的最大深度数
	 * @param parentId
	 * @param demId
	 * @return
	 */
	public Long getMaxDepth(Long demId){
		return dao.getMaxDepth(demId);
	}

	/**
	 * 通过userId取得该用户所属的所有部门
	 * @param userId
	 * @return
	 */
	public List<Organization> getByUserId(Long userId) {
		// TODO Auto-generated method stub
		return dao.getByUserId(userId);
	}

	@Override
	public List<Organization> getDirectByPathAndDepth(String path, Long depth) {
		return dao.getDirectByPath(path, depth);
	}
	
	

}