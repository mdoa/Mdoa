package com.htsoft.test.flow;

import java.util.List;

import javax.annotation.Resource;

import org.jbpm.pvm.internal.task.TaskImpl;
import org.junit.Test;

import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.flow.TaskDao;
import com.htsoft.test.BaseTestCase;

public class TaskDaoTestCase extends BaseTestCase{
	@Resource
	private TaskDao taskDao;
	
	//@Test
	public void testPersonTask(){
		String userId= "1";
		
//		List<TaskImpl> list=taskDao.getTasksByUserId(userId);
//		
//		for(TaskImpl task:list){
//			System.out.println("task:" + task.getDbid() + " Name:" + task.getActivityName());
//		}
	}
	
	@Test
	public void testPersonTaskByUserIdProcessName()
	{
		String userId="1";
		String processName="end flow";
		
		PagingBean pb=new PagingBean(0, 15);
		List<TaskImpl> list=taskDao.getTasksByUserIdProcessName(userId, processName, pb);
		for(TaskImpl task:list)
		{
			System.out.println("name:"+task.getName());
		}
	}
	
}
