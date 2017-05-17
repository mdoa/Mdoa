package com.htsoft.oa.service.flow.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.flow.ProcessModuleDao;
import com.htsoft.oa.model.flow.ProcessModule;
import com.htsoft.oa.service.flow.ProcessModuleService;

public class ProcessModuleServiceImpl extends BaseServiceImpl<ProcessModule> implements ProcessModuleService{
	@SuppressWarnings("unused")
	private ProcessModuleDao dao;
	
	public ProcessModuleServiceImpl(ProcessModuleDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public ProcessModule getByKey(String string) {
		return dao.getByKey(string);
	}

}