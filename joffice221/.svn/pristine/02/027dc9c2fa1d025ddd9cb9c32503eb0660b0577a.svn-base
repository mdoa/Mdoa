package com.htsoft.oa.dao.system;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.Organization;

/**
 * 
 * @author
 * 
 */
public interface OrganizationDao extends BaseDao<Organization> {
	/**
	 * 取得某个节点下的所有子节点
	 * 
	 * @param parentId
	 * @param demId
	 * @return
	 */
	public List<Organization> getByParent(Long parentId, Long demId);

	/**
	 * 取得维度下所有组织
	 * 
	 * @param demId
	 * @return
	 */
	public List<Organization> getByDemId(Long demId);

	/**
	 * 按路径查找所有节点
	 * 
	 * @param path
	 * @return
	 */
	public List<Organization> getByPath(String path);
	
	/**
	 * 取得某个维度下的最大深度数
	 * @param parentId
	 * @param demId
	 * @return
	 */
	public Long getMaxDepth(Long demId);
	
	/**
	 * 按路径及深度查找所有节点
	 * 
	 * @param path
	 * @return
	 */
	public List<Organization> getDirectByPath(String path,Long depth);

	/**
	 * 通过userId取得该用户所属的所有部门
	 * @param userId
	 * @return
	 */
	public List<Organization> getByUserId(Long userId);

}