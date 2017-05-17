package com.htsoft.oa.service.hrm.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.HireIssueDao;
import com.htsoft.oa.model.hrm.HireIssue;
import com.htsoft.oa.service.hrm.HireIssueService;

public class HireIssueServiceImpl extends BaseServiceImpl<HireIssue> implements HireIssueService{
	private HireIssueDao dao;
	
	public HireIssueServiceImpl(HireIssueDao dao) {
		super(dao);
		this.dao=dao;
	}

}