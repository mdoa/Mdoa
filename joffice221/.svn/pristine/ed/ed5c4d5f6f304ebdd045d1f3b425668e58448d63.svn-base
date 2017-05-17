package com.htsoft.oa.dao.flow.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.TaskSignDataDao;
import com.htsoft.oa.model.flow.TaskSignData;

@SuppressWarnings("unchecked")
public class TaskSignDataDaoImpl extends BaseDaoImpl<TaskSignData> implements TaskSignDataDao{

	public TaskSignDataDaoImpl() {
		super(TaskSignData.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.htsoft.oa.dao.flow.TaskSignDataDao#getVoteCounts(java.lang.String, java.lang.Short)
	 */
	public Long getVoteCounts(String taskId,Short isAgree){
		String hql="select count(dataId) from TaskSignData tsd where tsd.taskId=? and tsd.isAgree=?";
		Object count=findUnique(hql, new Object[]{taskId,isAgree});
		return new Long(count.toString());
	}
	
	/**
	 * 取得某任务对应的会签投票情况
	 * @param taskId
	 * @return
	 */
	public List<TaskSignData> getByTaskId(String taskId){
		String hql="from TaskSignData tsd where tsd.taskId=?";
		return findByHql(hql,new Object[]{taskId});
	}

}