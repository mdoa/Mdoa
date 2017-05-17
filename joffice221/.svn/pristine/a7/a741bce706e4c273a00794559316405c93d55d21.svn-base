package com.htsoft.oa.dao.archive;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;
import java.util.Set;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.archive.Archives;
import com.htsoft.oa.model.system.AppRole;

/**
 * 
 * @author 
 *
 */
public interface ArchivesDao extends BaseDao<Archives>{
	/**
	 * 根据用户的ID或角色来查找当前用户的分发公文
	 */
	public List<Archives> findByUserOrRole(Long userId,Set<AppRole> roles,PagingBean pb);
	
	/**
	 * 根据runId获取数据
	 * @param runId
	 * @return
	 */
	public Archives getByRunId(String runId);
}