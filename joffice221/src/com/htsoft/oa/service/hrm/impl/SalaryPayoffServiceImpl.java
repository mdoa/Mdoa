package com.htsoft.oa.service.hrm.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.SalaryPayoffDao;
import com.htsoft.oa.model.hrm.SalaryPayoff;
import com.htsoft.oa.service.hrm.SalaryPayoffService;

public class SalaryPayoffServiceImpl extends BaseServiceImpl<SalaryPayoff> implements SalaryPayoffService{
	private SalaryPayoffDao dao;
	
	public SalaryPayoffServiceImpl(SalaryPayoffDao dao) {
		super(dao);
		this.dao=dao;
	}

}