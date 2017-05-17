package com.htsoft.oa.dao.archive;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.archive.ArchDispatch;
import com.htsoft.oa.model.system.AppUser;

/**
 * 
 * @author 
 *
 */
public interface ArchDispatchDao extends BaseDao<ArchDispatch>{
	/**
	 * 根据当前用户的角色和用户ID来查找相关的分发人记录
	 */
	public List<ArchDispatch> findByUser(AppUser user,PagingBean pb);
	/**
	 * 根据当前公文ID来查找阅读和处理的记录
	 */
	public List<ArchDispatch> findRecordByArc(Long archivesId);
}