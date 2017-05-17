package com.htsoft.test.hrm;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.htsoft.test.BaseTestCase;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.oa.dao.hrm.EmpProfileDao;
import com.htsoft.oa.model.hrm.EmpProfile;
import com.htsoft.oa.model.info.Section;

public class EmpProfileDaoTestCase extends BaseTestCase {
	@Resource
	private EmpProfileDao empProfileDao;

	// @Test
	// @Rollback(false)
	public void add() {
		EmpProfile empProfile = new EmpProfile();
		// TODO

		empProfileDao.save(empProfile);
	}

	@Test
	public void getAll() throws JsonGenerationException, JsonMappingException,
			IOException {
		List<EmpProfile> list = empProfileDao.getAll();
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		String jsonString = mapper.toPageJson(list, 3);
		System.err.println("EmpProfile:" + jsonString);
	}
}