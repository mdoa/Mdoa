package com.htsoft.oa.service.customer.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.customer.ContractDao;
import com.htsoft.oa.model.customer.Contract;
import com.htsoft.oa.service.customer.ContractService;

public class ContractServiceImpl extends BaseServiceImpl<Contract> implements ContractService{
	private ContractDao dao;
	
	public ContractServiceImpl(ContractDao dao) {
		super(dao);
		this.dao=dao;
	}

}