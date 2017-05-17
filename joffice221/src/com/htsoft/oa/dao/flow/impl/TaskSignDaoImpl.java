package com.htsoft.oa.dao.flow.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.TaskSignDao;
import com.htsoft.oa.model.flow.TaskSign;

/**
 * @description 任务会签
 * @class TaskSignDaoImpl
 * @author YHZ
 * @company www.jee-soft.cn
 * @data 2011-1-5PM
 * 
 */
@SuppressWarnings("unchecked")
public class TaskSignDaoImpl extends BaseDaoImpl<TaskSign> implements
		TaskSignDao {

	public TaskSignDaoImpl() {
		super(TaskSign.class);
	}

	/**
	 * @description 根据assignId查询TaskSign对象数据
	 */
	@Override
	public TaskSign getBySetId(Long setId) {
		String hql = "from TaskSign ts where ts.proNodeSet.setId=? ";
		return (TaskSign) findUnique(hql, new Object[] { setId });
	}

}