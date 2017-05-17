package com.htsoft.test.hrm;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.htsoft.test.BaseTestCase;
import com.htsoft.oa.dao.hrm.JobDao;
import com.htsoft.oa.model.hrm.Job;

public class JobDaoTestCase extends BaseTestCase {
	@Resource
	private JobDao jobDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		Job job=new Job();
//		TODO

		jobDao.save(job);
	}
}