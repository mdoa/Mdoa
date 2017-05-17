package com.htsoft.oa.service.system;

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.hrm.Job;

/**
 * @description 岗位管理
 * @class JobService
 * @author YHZ
 * @company www.jee-soft.cn
 * @data 2010-12-23AM
 * 
 */
public interface JobService extends BaseService<Job> {

	/**
	 * @author lyy
	 * @param depId
	 *            根据部门的ID来查找部门下面的职位
	 * @return
	 */
	public List<Job> findByDep(Long depId);

	/**
	 * @description 根据parentId条件查询
	 * @param parentId
	 *            父节点Id
	 * @return List<Job>
	 */
	List<Job> findByCondition(Long parentId);

	/**
	 * @description 修改Job信息[jobName,memo,delFlag]
	 * @param job
	 *            Job对象
	 */
	void edit(Job job);
}
