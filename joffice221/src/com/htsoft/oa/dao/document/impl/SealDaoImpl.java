package com.htsoft.oa.dao.document.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.document.SealDao;
import com.htsoft.oa.model.document.Seal;

@SuppressWarnings("unchecked")
public class SealDaoImpl extends BaseDaoImpl<Seal> implements SealDao{

	public SealDaoImpl() {
		super(Seal.class);
	}

}