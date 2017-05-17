package com.htsoft.oa.service.customer;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.customer.Project;

public interface ProjectService extends BaseService<Project>{

	public boolean checkProjectNo(String projectNo);
	
}


