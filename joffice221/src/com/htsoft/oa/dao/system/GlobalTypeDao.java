package com.htsoft.oa.dao.system;

/*
 *  杭州梦德软件有限公司企业管理平台   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-20010 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.GlobalType;

/**
 * 
 * @author
 * 
 */
public interface GlobalTypeDao extends BaseDao<GlobalType> {
	/**
	 * 取得某种分类下的子结点列表
	 * 
	 * @param parentId
	 * @param catKey
	 * @return
	 */
	public List<GlobalType> getByParentIdCatKey(Long parentId, String catKey);

	/**
	 * 取得某种分类下某个用户的子结点列表
	 * 
	 * @param parentId
	 * @param catKey
	 * @return
	 */
	List<GlobalType> getByParentIdCatKeyAndNodeKey(Long parentId, String catKey);

	/**
	 * 取得某种分类下某个用户的子结点列表
	 * 
	 * @param parentId
	 * @param catKey
	 * @return
	 */
	public List<GlobalType> getByParentIdCatKeyUserId(Long parentId,
			String catKey, Long userId);

	/**
	 * 取得该分类下的数目
	 * 
	 * @param parentId
	 * @return
	 */
	public Integer getCountsByParentId(Long parentId);

	/**
	 * 取得该分类下的所有子分类
	 * 
	 * @param parentId
	 * @return
	 */
	public List<GlobalType> getByParentId(Long parentId);

	/**
	 * 
	 * @param path
	 * @return
	 */
	public List<GlobalType> getByPath(String path);

	public GlobalType findByTypeName(String typeName);

	/**
	 * 根据当前用户权限产生流程分类树
	 * 
	 * @param curUser
	 * @param catKey
	 * @return
	 */
	public List<GlobalType> getByRightsCatKey(AppUser curUser, String catKey);

	/**
	 * 根据proTypeId删除，下面所有的子节点信息
	 * 
	 * @param proTypeId
	 */
	void delChildrens(Long proTypeId);

	/**
	 * 根据fileType查询对应的一个GlobalType对象
	 * 
	 * @param fileType
	 *            fileType
	 * @return GlobalType
	 */
	GlobalType findByFileType(String fileType);

	/**
	 * 取得某种分类下的结点列表
	 * 
	 * @param parentId
	 * @return
	 */
	public List<GlobalType> getByCatKey(String catKey);

	/**
	 * 根据分类的nodekey获取数据字典项的分类。
	 * 
	 * @param nodeKey
	 *            nodeKey
	 * @return
	 */
	public GlobalType getByDictNodeKey(String nodeKey);

	public List<GlobalType> getByCatKeyAndGTsn(String catKey, Integer sn);
}