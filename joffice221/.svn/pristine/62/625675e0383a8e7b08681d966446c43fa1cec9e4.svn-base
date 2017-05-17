package com.htsoft.oa.service.admin.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.InStockDao;
import com.htsoft.oa.model.admin.InStock;
import com.htsoft.oa.service.admin.InStockService;

public class InStockServiceImpl extends BaseServiceImpl<InStock> implements InStockService{
	private InStockDao dao;
	
	public InStockServiceImpl(InStockDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Integer findInCountByBuyId(Long buyId) {
		return dao.findInCountByBuyId(buyId);
	}

}