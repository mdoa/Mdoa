package com.htsoft.oa.service.system.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.DepartmentDao;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.service.system.DepartmentService;

public class DepartmentServiceImpl extends BaseServiceImpl<Department> implements
		DepartmentService {

	private DepartmentDao dao;
	public DepartmentServiceImpl(DepartmentDao dao) {
		super(dao);
		this.dao=dao;
	}
	@Override
	public List<Department> findByParentId(Long parentId) {
		return dao.findByParentId(parentId);
	}
	@Override
	public List<Department> findByDepName(String depName) {
		return dao.findByDepName(depName);
	}
	@Override
	public List<Department> findByPath(String path) {
		return dao.findByPath(path);
	}

	/**
	 * 删除某个部门及其下属部门
	 * @param depId
	 */
	public void delCascade(Long depId){
		Department dep=get(depId);
		evict(dep);
		List<Department> listDeps=findByPath(dep.getPath());
		for(Department o:listDeps){
			remove(o);
		}
	}
	
}
