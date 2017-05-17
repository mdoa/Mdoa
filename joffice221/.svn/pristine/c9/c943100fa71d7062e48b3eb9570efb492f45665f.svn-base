package com.htsoft.test.system;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.htsoft.test.BaseTestCase;
import com.htsoft.oa.dao.system.SerialNumberDao;
import com.htsoft.oa.model.system.SerialNumber;

public class SerialNumberDaoTestCase extends BaseTestCase {
	@Resource
	private SerialNumberDao serialNumberDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		SerialNumber serialNumber=new SerialNumber();
//		TODO

		serialNumberDao.save(serialNumber);
	}
}