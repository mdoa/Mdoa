package com.htsoft.oa.service.archive.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.archive.ArchDispatchDao;
import com.htsoft.oa.model.archive.ArchDispatch;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.archive.ArchDispatchService;

public class ArchDispatchServiceImpl extends BaseServiceImpl<ArchDispatch> implements ArchDispatchService{
	private ArchDispatchDao dao;
	
	public ArchDispatchServiceImpl(ArchDispatchDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<ArchDispatch> findByUser(AppUser user, PagingBean pb) {
		return dao.findByUser(user, pb);
	}

	@Override
	public int countArchDispatch(Long archivesId) {
		return dao.findRecordByArc(archivesId).size();
	}

}