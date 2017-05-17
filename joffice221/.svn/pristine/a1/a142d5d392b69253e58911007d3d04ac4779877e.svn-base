package com.htsoft.oa.service.archive;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.archive.ArchDispatch;
import com.htsoft.oa.model.system.AppUser;

public interface ArchDispatchService extends BaseService<ArchDispatch>{
	public List<ArchDispatch> findByUser(AppUser user,PagingBean pb);
	/**
	 * 根据公文的ID来查找阅读处理的记录数
	 */
	public int countArchDispatch(Long archivesId);
}


