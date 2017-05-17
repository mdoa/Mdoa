package com.htsoft.test.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.htsoft.oa.dao.flow.FormRuleDao;
import com.htsoft.oa.model.flow.FormRule;
import com.htsoft.test.BaseTestCase;

public class FormRlueDaoTestCase extends BaseTestCase {
	@Resource
	private FormRuleDao formRuleDao;
	
	@Test
	@Rollback(false)
	public void add(){		
		FormRule formRule=new FormRule();
//		TODO

		formRuleDao.save(formRule);
	}
}