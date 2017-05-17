package com.htsoft.test.flow;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.htsoft.test.BaseTestCase;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.oa.dao.flow.ProcessRunDao;
import com.htsoft.oa.model.flow.ProcessRun;

public class ProcessRunDaoTestCase extends BaseTestCase {
	@Resource
	private ProcessRunDao processRunDao;
	
//	@Test
//	@Rollback(false)
//	public void add(){		
//		ProcessRun processRun=new ProcessRun();
////		TODO
//
//		processRunDao.save(processRun);
//	}
	
	@Test
	public void find(){		
		List<ProcessRun> list = processRunDao.getAll();
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		
		String	jsonString = mapper.toPageJson(list, 1);
		
		System.err.println(jsonString);
	}
}