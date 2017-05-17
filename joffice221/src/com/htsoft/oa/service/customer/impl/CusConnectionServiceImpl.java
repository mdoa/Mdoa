package com.htsoft.oa.service.customer.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.customer.CusConnectionDao;
import com.htsoft.oa.model.customer.CusConnection;
import com.htsoft.oa.service.customer.CusConnectionService;

public class CusConnectionServiceImpl extends BaseServiceImpl<CusConnection> implements CusConnectionService{
	private CusConnectionDao dao;
	
	public CusConnectionServiceImpl(CusConnectionDao dao) {
		super(dao);
		this.dao=dao;
	}

}