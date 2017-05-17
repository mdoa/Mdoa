package com.htsoft.oa.dao.system;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.Department;

public interface DepartmentDao extends BaseDao<Department> {

	public List<Department> findByParentId(Long parentId);
	public List<Department> findByVo(Department department,PagingBean pb);
	public List<Department> findByDepName(String depName);
	public List<Department> findByPath(String path);
}
