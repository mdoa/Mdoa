package com.htsoft.oa.service.system;

/*
 *  杭州梦德软件有限公司企业管理平台   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-20010 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.GlobalType;

public interface GlobalTypeService extends BaseService<GlobalType> {
	/**
	 * 取得某种分类下的子结点列表
	 * 
	 * @param parentId
	 * @param catKey
	 * @return
	 */
	public List<GlobalType> getByParentIdCatKey(Long parentId, String catKey);

	/**
	 * 获取某种分类节点下的子节点列表
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
	 * 删除分类，同时删除其下所有子子分类
	 * 
	 * @param parentId
	 */
	public void mulDel(Long parentId);

	/**
	 * 根据当前用户权限产生流程分类树
	 * 
	 * @param curUser
	 * @param catKey
	 * @return
	 */
	public List<GlobalType> getByRightsCatKey(AppUser curUser, String catKey);

	/**
	 * @description 根据当前用户权限产生流程分类树
	 * @param curUser
	 * @param catKey
	 * @return
	 */
	List<GlobalType> getByCatKeyUserId(AppUser curUser, String catKey);

	/**
	 * 根据proTypeId删除下面对应的所有数据信息，包含本身
	 * 
	 * @param proTypeId
	 */
	void delChildrens(Long proTypeId);

	/**
	 * 根据fileType查询第一条数据，返回GlobalType
	 * 
	 * @param fileType
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
	 * 根据nodekey获取字典的分类类型。
	 * 
	 * @param nodeKey
	 *            分类表的nodekey。
	 * @return
	 */
	public GlobalType getByDictNodeKey(String nodeKey);

	/**
	 * 根据catKey、sn获取大于sn值的分类类型。
	 * 
	 * @param nodeKey
	 *            分类表的nodekey。
	 * @return
	 */
	public List<GlobalType> getByCatKeyAndGTsn(String catKey, Integer sn);
}
