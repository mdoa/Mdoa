package com.htsoft.oa.dao.admin.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.admin.RegulationDao;
import com.htsoft.oa.model.admin.Regulation;

@SuppressWarnings("unchecked")
public class RegulationDaoImpl extends BaseDaoImpl<Regulation> implements RegulationDao{

	public RegulationDaoImpl() {
		super(Regulation.class);
	}

}