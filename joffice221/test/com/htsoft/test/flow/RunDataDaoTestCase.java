package com.htsoft.test.flow;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.annotation.Rollback;

import com.htsoft.test.BaseTestCase;
import com.htsoft.oa.dao.flow.RunDataDao;
import com.htsoft.oa.model.flow.ProcessForm;
import com.htsoft.oa.model.flow.RunData;
import com.htsoft.oa.service.flow.ProcessFormService;
import com.htsoft.core.util.AppUtil;

public class RunDataDaoTestCase extends BaseTestCase {
	@Resource
	private RunDataDao formDataDao;
	
	@Resource
	private ProcessFormService processFormService;
	private ProcessForm processForm;
	
	@Test
	@Rollback(false)
	public void add(){		
		RunData formData=new RunData();
//		TODO

		formDataDao.save(formData);
		
		
		
		
	}
}