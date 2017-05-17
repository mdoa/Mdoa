package com.htsoft.test.arch;

import java.io.IOException;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.oa.dao.arch.ArchFondDao;
import com.htsoft.oa.model.arch.ArchFond;
import com.htsoft.test.BaseTestCase;

public class ArchFondDaoTestCase extends BaseTestCase{
	@Resource
	private ArchFondDao archFondDao;
	
	//@Test
	//@Rollback(false)
	public void add(){		
		ArchFond archFond=new ArchFond();
//		TODO

		archFondDao.save(archFond);
	}
	
	@Test
	public void getAll() throws JsonGenerationException, JsonMappingException, IOException{
		List<ArchFond> list= archFondDao.getAll();
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd HH:mm:ss");
		String jsonString = mapper.toPageJson(list, 3);
		System.err.println(jsonString);
	}	
}
