package com.htsoft.oa.dao.admin;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.admin.ConfPrivilege;

/**
 * @description ConfPrivilegeDao
 * @author YHZ
 * @date 2010-10-8 PM
 * 
 */
public interface ConfPrivilegeDao extends BaseDao<ConfPrivilege> {

	/**
	 * @description 根据用户id，查询该用户的权限
	 * @param userId
	 *            用户Id
	 * @param confId
	 *            会议编号
	 * @param s 1=查看,2=修改,3=建立纪要
	 * @return 0=没有权限,1=查看,2=修改,3=建立纪要
	 */
	Short getPrivilege(Long userId, Long confId, Short s);

	/**
	 * @description 根据会议编号confId删除会议权限
	 * @param confId
	 *            confId
	 */
	void delete(Long confId);
}