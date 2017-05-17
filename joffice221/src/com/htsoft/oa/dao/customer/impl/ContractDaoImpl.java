package com.htsoft.oa.dao.customer.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.customer.ContractDao;
import com.htsoft.oa.model.customer.Contract;

public class ContractDaoImpl extends BaseDaoImpl<Contract> implements ContractDao{

	public ContractDaoImpl() {
		super(Contract.class);
	}

}