package com.htsoft.oa.service.system.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.TypeKeyDao;
import com.htsoft.oa.model.system.TypeKey;
import com.htsoft.oa.service.system.TypeKeyService;

public class TypeKeyServiceImpl extends BaseServiceImpl<TypeKey> implements TypeKeyService{
	private TypeKeyDao dao;
	
	public TypeKeyServiceImpl(TypeKeyDao dao) {
		super(dao);
		this.dao=dao;
	}

}