package com.htsoft.test.flow;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.htsoft.test.BaseTestCase;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.oa.dao.flow.ProNodeSetDao;
import com.htsoft.oa.model.flow.ProNodeSet;
import com.htsoft.oa.model.flow.ProUserSet;

public class ProNodeSetDaoTestCase extends BaseTestCase {
	@Resource
	private ProNodeSetDao proNodeSetDao;

	// @Test
	// @Rollback(false)
	// public void add(){
	// ProNodeSet proNodeSet=new ProNodeSet();
	// // TODO
	//
	// proNodeSetDao.save(proNodeSet);
	// }
	@Test
	public void save() {
		String data = "[{\"id\":5,\"defId\":10000,\"deployId\":\"7\",\"nodeName\":\"任务1\",\"userType\":2,\"uids\":\"10000\",\"unames\":\"庄晓晖\",\"compType\":1},{\"id\":101,\"defId\":10000,\"deployId\":\"7\",\"nodeName\":\"任务1\",\"userType\":5,\"uids\":\"10001\",\"unames\":\"财务经理\",\"compType\":1}]";
		JacksonMapper mapper = new JacksonMapper(true);
		ProUserSet[] proUserSets = mapper.toObject(data, ProUserSet[].class);
		System.out.println(proUserSets[0].getId());
	}
}