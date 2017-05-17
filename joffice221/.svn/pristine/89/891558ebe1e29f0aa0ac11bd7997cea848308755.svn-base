package com.htsoft.oa.service.archive;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;
import java.util.Set;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.action.flow.FlowRunInfo;
import com.htsoft.oa.model.archive.Archives;
import com.htsoft.oa.model.system.AppRole;

public interface ArchivesService extends BaseService<Archives>{
	/**
	 * 根据用户的ID或角色ID来查找当前用户的分发公文
	 */
	public List<Archives> findByUserOrRole(Long userId,Set<AppRole> roles,PagingBean pb);
	
	/**
	 * 发文启动流程后保存公文信息
	 */
	public Integer startArchFlow(FlowRunInfo flowRunInfo);
	
	/**
	 * 收发文流程启动后把RunId保存至公文信息中
	 */
	public Integer setRunId(FlowRunInfo flowRunInfo);
	 
	/**
	 * 发文流程任务结束后把status写入公文中
	 */
	public Integer saveStatus(FlowRunInfo flowRunInfo) ;
	
	/**
	 * 发文流程结束,归档时操作endFlow
	 */
	public Integer endFlow(FlowRunInfo flowRunInfo);
	
	/**
	 * 收文流程启动前执行以下方法
	 */
	public Integer startRecFlow(FlowRunInfo flowRunInfo);
	/**
	 * 收文流程结束后执行以下方法
	 * @param flowRunInfo
	 * @return
	 */
	public Integer endRecFlow (FlowRunInfo flowRunInfo);
	/**
	 * 
	 * 收文流程分发时记录分发信息
	 * @return
	 */
	public Integer saveDispatch (FlowRunInfo flowRunInfo);
	
	/**
	 * 移动端取得发文流程表单
	 * @return
	 */
	public String mobileArchGet(String runId);
}


