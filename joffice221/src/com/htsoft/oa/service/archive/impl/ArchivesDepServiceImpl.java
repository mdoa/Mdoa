package com.htsoft.oa.service.archive.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchivesDepDao;
import com.htsoft.oa.model.archive.ArchivesDep;
import com.htsoft.oa.service.archive.ArchivesDepService;

public class ArchivesDepServiceImpl extends BaseServiceImpl<ArchivesDep> implements ArchivesDepService{
	private ArchivesDepDao dao;
	
	public ArchivesDepServiceImpl(ArchivesDepDao dao) {
		super(dao);
		this.dao=dao;
	}

}