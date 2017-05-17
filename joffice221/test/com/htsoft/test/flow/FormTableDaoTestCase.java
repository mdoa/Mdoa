package com.htsoft.test.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.htsoft.test.BaseTestCase;
import com.htsoft.oa.dao.flow.FormTableDao;
import com.htsoft.oa.model.flow.FormTable;

public class FormTableDaoTestCase extends BaseTestCase {
	@Resource
	private FormTableDao formTableDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		FormTable formTable=new FormTable();
//		TODO

		formTableDao.save(formTable);
	}
}