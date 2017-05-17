package com.htsoft.oa.service.archive.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.DocHistoryDao;
import com.htsoft.oa.model.archive.DocHistory;
import com.htsoft.oa.service.archive.DocHistoryService;

public class DocHistoryServiceImpl extends BaseServiceImpl<DocHistory> implements DocHistoryService{
	private DocHistoryDao dao;
	
	public DocHistoryServiceImpl(DocHistoryDao dao) {
		super(dao);
		this.dao=dao;
	}

}