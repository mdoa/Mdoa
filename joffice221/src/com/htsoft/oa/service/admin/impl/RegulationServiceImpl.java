package com.htsoft.oa.service.admin.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.RegulationDao;
import com.htsoft.oa.model.admin.Regulation;
import com.htsoft.oa.service.admin.RegulationService;

public class RegulationServiceImpl extends BaseServiceImpl<Regulation> implements RegulationService{
	@SuppressWarnings("unused")
	private RegulationDao dao;
	
	public RegulationServiceImpl(RegulationDao dao) {
		super(dao);
		this.dao=dao;
	}

}