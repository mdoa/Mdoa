package com.htsoft.oa.dao.communicate.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.communicate.SmsMobileDao;
import com.htsoft.oa.model.communicate.SmsMobile;

public class SmsMobileDaoImpl extends BaseDaoImpl<SmsMobile> implements SmsMobileDao{

	public SmsMobileDaoImpl() {
		super(SmsMobile.class);
	}

	@Override
	public List<SmsMobile> getNeedToSend() {
		String hql = "from SmsMobile sm where sm.status = ? order by sm.sendTime desc";
		Object[] params = {SmsMobile.STATUS_NOT_SENDED};
		return findByHql(hql, params);
	}

}