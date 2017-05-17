package com.htsoft.oa.service.hrm.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.StandSalaryItemDao;
import com.htsoft.oa.model.hrm.StandSalaryItem;
import com.htsoft.oa.service.hrm.StandSalaryItemService;

public class StandSalaryItemServiceImpl extends BaseServiceImpl<StandSalaryItem> implements StandSalaryItemService{
	private StandSalaryItemDao dao;
	
	public StandSalaryItemServiceImpl(StandSalaryItemDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<StandSalaryItem> getAllByStandardId(Long standardId) {
		return dao.getAllByStandardId(standardId);
	}

}