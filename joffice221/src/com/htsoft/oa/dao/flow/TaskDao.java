package com.htsoft.oa.dao.flow;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import org.jbpm.api.Execution;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.JbpmTask;

public interface TaskDao extends BaseDao<TaskImpl>{
	
	public List<TaskImpl> getTasksByUserId(String userId,PagingBean pb);
	
	/**
	 * 取得所有任务
	 * @param taskName
	 * @param pb
	 * @return
	 */
	public List<TaskImpl> getAllTasks(String taskName,PagingBean pb);
	/**
	 * 取得所有未挂起的任务
	 * @param taskName
	 * @param pb
	 * @return
	 * @author lxy add at 2011-12-20
	 */
	public List<TaskImpl> getAllOpenTasks();
	/**
	 * 通过活动名称及参数Key取得某任务列表
	 * @param activityName
	 * @param varKey
	 * @return
	 */
	public List<JbpmTask> getByActivityNameVarKeyLongVal(String activityName,String varKey,Long value);
	/**
	 * 通过任务ID来查找任务人员组的角色ID
	 * @param taskId
	 * @return
	 */
	public List<Long> getGroupByTask(Long taskId);
	/**
	 * 通过任务的ID来查找子任务的执行人员ID
	 * @param taskId
	 * @return
	 */
	public List<Long> getUserIdByTask(Long taskId);
	
	/**
	 * 取得某个用户候选的任务列表
	 * @param userId
	 * @param pb
	 * @return
	 */
	public List<TaskImpl> getCandidateTasks(String userId,PagingBean pb);
	
	/**
	 * 查找个人归属任务，不包括其角色归属的任务
	 * @param userId
	 * @param pb
	 * @return
	 */
	public List<TaskImpl> getPersonTasks(String userId,PagingBean pb);
	
	/**
	 * 按主键查找execution实体
	 * @param dbid
	 * @return
	 */
	public Execution getExecutionByDbid(Long dbid);
	
	/**
	 * 保存executionimpl
	 * @param executionImpl
	 */
	public void save(ExecutionImpl executionImpl);
	
	/**
	 * 去掉某个execution的子execution及其相关联的记录
	 * @param parentDbid
	 */
	public void removeExeByParentId(Long parentDbid);
	
	
	/**
	 * 取得用户的对应的某个流程定义的任务列表
	 * @param userId
	 * @return
	 */
	public List<TaskImpl> getTasksByUserIdProcessName(String userId,String processName,PagingBean pb);
}
