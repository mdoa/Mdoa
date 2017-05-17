package com.htsoft.oa.service.hrm.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.hrm.JobDao;
import com.htsoft.oa.model.hrm.Job;
import com.htsoft.oa.service.hrm.JobService;

/**
 * @description 岗位管理
 * @class JObServiceImpl
 * @author YHZ
 * @company www.jee-soft.cn
 * @data 2010-12-23AM
 * 
 */
public class JobServiceImpl extends BaseServiceImpl<Job> implements JobService {
	private JobDao dao;

	public JobServiceImpl(JobDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<Job> findByDep(Long jobId) {
		return dao.findByDep(jobId);
	}

	/**
	 * 根据parentId查询
	 */
	@Override
	public List<Job> findByCondition(Long parentId) {
		return dao.findByCondition(parentId);
	}

	/**
	 * 编辑
	 */
	@Override
	public void edit(Job job) {
		dao.edit(job);
	}

}