package com.htsoft.oa.dao.system.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.oa.dao.system.SerialNumberDao;
import com.htsoft.oa.model.system.SerialNumber;

@SuppressWarnings("unchecked")
public class SerialNumberDaoImpl extends BaseDaoImpl<SerialNumber> implements
		SerialNumberDao {

	public SerialNumberDaoImpl() {
		super(SerialNumber.class);
	}

	@Override
	public Boolean checkAlias(Long numberId, String alias) {
		String hql = "from SerialNumber where alias = '" + alias + "'";
		if (BeanUtil.isNotEmpty(numberId)) {
			hql += " and  numberId != " + numberId + "";
		}
		return findByHql(hql).size() > 0;
	}

	@Override
	public SerialNumber getByAlias(String alias) {
		final String hql = "from SerialNumber  where alias = ?";
		Object[] params = { alias };
		return (SerialNumber) findUnique(hql, params);
	}

}