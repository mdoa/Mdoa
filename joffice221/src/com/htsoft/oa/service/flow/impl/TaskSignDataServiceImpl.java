package com.htsoft.oa.service.flow.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.Date;
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.oa.dao.flow.TaskSignDataDao;
import com.htsoft.oa.model.flow.TaskSignData;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.flow.TaskSignDataService;

public class TaskSignDataServiceImpl extends BaseServiceImpl<TaskSignData> implements TaskSignDataService{
	@SuppressWarnings("unused")
	private TaskSignDataDao dao;
	
	public TaskSignDataServiceImpl(TaskSignDataDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.htsoft.oa.service.flow.TaskSignDataService#addVote(java.lang.String, java.lang.Short)
	 */
	public void addVote(String taskId,Short isAgree){
		AppUser curUser=ContextUtil.getCurrentUser();
		
		TaskSignData data=new TaskSignData();
		
		data.setTaskId(taskId);
		data.setIsAgree(isAgree);
		data.setVoteId(curUser.getUserId());
		data.setVoteName(curUser.getFullname());
		data.setVoteTime(new Date());
		
		save(data);
	}
	
	/**
	 * 返回某个（父）任务的投票情况
	 * @return
	 */
	public Long getVoteCounts(String taskId,Short isAgree){
		return dao.getVoteCounts(taskId,isAgree);
	}
	
	/**
	 * 取得某任务对应的会签投票情况
	 * @param taskId
	 * @return
	 */
	public List<TaskSignData> getByTaskId(String taskId){
		return dao.getByTaskId(taskId);
	}

}