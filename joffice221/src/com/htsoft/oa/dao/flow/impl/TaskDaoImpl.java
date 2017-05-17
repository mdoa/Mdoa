package com.htsoft.oa.dao.flow.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.Execution;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.jdbc.core.RowMapper;
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.flow.TaskDao;
import com.htsoft.oa.model.flow.JbpmTask;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.model.system.AppUser;

public class TaskDaoImpl extends BaseDaoImpl<TaskImpl> implements TaskDao{

	public TaskDaoImpl() {
		super(TaskImpl.class);
	}
	
	/**
	 * 查找个人归属任务，不包括其角色归属的任务
	 * @param userId
	 * @param pb
	 * @return
	 */
	public List<TaskImpl> getPersonTasks(String userId,PagingBean pb){
		
		StringBuffer hqlSb=new StringBuffer();
		hqlSb.append("select task from org.jbpm.pvm.internal.task.TaskImpl task  where task.assignee=? and task.state='open'");
		hqlSb.append(" order by task.createTime desc");
		
		return findByHql(hqlSb.toString(),new Object[]{userId}, pb);
	}
	
	/**
	 * 取得所有任务
	 * @param taskName
	 * @param pb
	 * @return
	 */
	public List<TaskImpl> getAllTasks(String taskName,PagingBean pb){
		List params=new ArrayList();
		String hql="from org.jbpm.pvm.internal.task.TaskImpl task where 1=1";
		if(StringUtils.isNotEmpty(taskName)){
			hql+=" and task.name like ?";
			params.add("%"+taskName+"%");
		}
		hql+=" order by task.createTime desc";
		return findByHql(hql,params.toArray(),pb);
	}
	
	/**
	 * 取得所有未挂起的任务
	 * @param taskName
	 * @param pb
	 * @return
	 * @author lxy add at 2011-12-20
	 */
	public List<TaskImpl> getAllOpenTasks(){
		String hql="from org.jbpm.pvm.internal.task.TaskImpl task where task.state='open' and task.duedate <> null";
		return findByHql(hql);
	}
	
	/**
	 * 取得某个用户候选的任务列表
	 * @param userId
	 * @param pb
	 * @return
	 */
	public List<TaskImpl> getCandidateTasks(String userId,PagingBean pb){
		AppUser user=(AppUser)getHibernateTemplate().load(AppUser.class, new Long(userId));
		Iterator<AppRole> rolesIt=user.getRoles().iterator();
		StringBuffer groupIds=new StringBuffer();
		int i=0;
		while(rolesIt.hasNext()){
			if(i++>0)groupIds.append(",");
			groupIds.append("'"+rolesIt.next().getRoleId().toString()+"'");
		}
		StringBuffer hqlSb=new StringBuffer();
		hqlSb.append("select distinct task from org.jbpm.pvm.internal.task.TaskImpl task left join task.participations pt ");
		hqlSb.append(" where task.assignee is null and task.state='open' and pt.type = 'candidate' and ( pt.userId=? ");
		
		if(user.getRoles().size()>0){
			hqlSb.append(" or pt.groupId in ("+groupIds.toString()+")");
		}
		hqlSb.append(")");
		hqlSb.append(" order by task.createTime desc");
		
		return findByHql(hqlSb.toString(), new Object[]{userId,userId},pb);
	}
	
	/**
	 * 取得用户的对应的任务列表
	 * @param userId
	 * @return
	 */
	public List<TaskImpl> getTasksByUserId(String userId,PagingBean pb){
		AppUser user=(AppUser)getHibernateTemplate().load(AppUser.class, new Long(userId));
		Iterator<AppRole> rolesIt=user.getRoles().iterator();
		StringBuffer groupIds=new StringBuffer();
		int i=0;
		while(rolesIt.hasNext()){
			if(i++>0)groupIds.append(",");
			groupIds.append("'"+rolesIt.next().getRoleId().toString()+"'");
		}
		StringBuffer hqlSb=new StringBuffer();
		hqlSb.append("select distinct task from org.jbpm.pvm.internal.task.TaskImpl task left join task.participations pt where task.assignee=? and task.state='open'");
		hqlSb.append(" or ( task.assignee is null and pt.type = 'candidate' and  ( pt.userId = ? ");
		
		if(user.getRoles().size()>0){
			hqlSb.append(" or pt.groupId in ("+groupIds.toString()+")");
		}
		hqlSb.append("))");
		hqlSb.append(" order by task.createTime desc");

		return findByHql(hqlSb.toString(), new Object[]{userId,userId},pb);
		
	}
	
	
	/**
	 * 取得用户的对应的某个流程定义的任务列表
	 * @param userId
	 * @return
	 */
	public List<TaskImpl> getTasksByUserIdProcessName(String userId,String subject,PagingBean pb){
		List<Object> params=new ArrayList<Object>();
		AppUser user=(AppUser)getHibernateTemplate().load(AppUser.class, new Long(userId));
		Iterator<AppRole> rolesIt=user.getRoles().iterator();
		StringBuffer groupIds=new StringBuffer();
		int i=0;
		while(rolesIt.hasNext()){
			if(i++>0)groupIds.append(",");
			groupIds.append("'"+rolesIt.next().getRoleId().toString()+"'");
		}
		StringBuffer hqlSb=new StringBuffer();
		hqlSb.append("select distinct task from org.jbpm.pvm.internal.task.TaskImpl task left join task.participations pt where task.assignee=? and task.state='open'");

		params.add(userId);
		if(StringUtils.isNotEmpty(subject)){
			hqlSb.append(" and (task.processRun.subject like ? or task.name like ?)");
			params.add("%" + subject + "%");
			params.add("%" + subject + "%");
		}
		hqlSb.append(" or ( task.assignee is null and pt.type = 'candidate' and  ( pt.userId = ? ");
		params.add(userId);
		if(user.getRoles().size()>0){
			hqlSb.append(" or pt.groupId in ("+groupIds.toString()+")");
		}
		hqlSb.append("))");
		
		hqlSb.append(" order by task.createTime desc");
		if(pb==null){
			return findByHql(hqlSb.toString(),params.toArray());
		}else{
			return findByHql(hqlSb.toString(),params.toArray(),pb);
		}
		
	}
	
	/**
	 * 通过活动名称及参数Key取得某任务列表
	 * @param activityName
	 * @param varKey
	 * @return
	 */
	public List<JbpmTask> getByActivityNameVarKeyLongVal(String activityName,String varKey,Long value){
		String sql="select task.DBID_ taskId, task.ASSIGNEE_ assignee from jbpm4_task task join jbpm4_variable var on task.EXECUTION_=var.EXECUTION_ where  task.ACTIVITY_NAME_=? and var.KEY_=? and var.LONG_VALUE_=?";//task.ASSIGNEE_ is not null and
		Collection<JbpmTask> jbpmtask =(Collection) this.jdbcTemplate.query(sql,new Object[]{activityName,varKey,value},
				new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						JbpmTask task=new JbpmTask();
						Long taskId=rs.getLong("taskId");
						String assignee=rs.getString("assignee");
						task.setAssignee(assignee);
						task.setTaskId(taskId);
						return task;
					}
				}
		);
		return new ArrayList(jbpmtask);
	}
	@Override
	public List<Long> getGroupByTask(Long taskId) {
		String sql="select pa.GROUPID_ groupId from jbpm4_participation pa  where pa.TYPE_ = 'candidate'and pa.TASK_=?";
		Collection<String> groupIds =(Collection) this.jdbcTemplate.query(sql,new Object[]{taskId},
				new RowMapper() {
					@Override
					public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
						String groupId=rs.getString("groupId");
						return groupId;
					}
				}
		);
		return new ArrayList(groupIds);
	}
	@Override
	public List<Long> getUserIdByTask(Long taskId) {
		String hql="from org.jbpm.pvm.internal.task.TaskImpl task where task.superTask.id=? and task.state='open'";
		Object[] objs={taskId};
		List<TaskImpl> taskList=findByHql(hql, objs);
		List<Long> list=new ArrayList<Long>();
		for(TaskImpl task:taskList){
			list.add(new Long(task.getAssignee()));
		}
		return list;
	}
	
	/**
	 * 去掉某个execution的子execution及其相关联的记录
	 * @param parentDbid
	 */
	public void removeExeByParentId(Long parentDbid){
		
		//String delVarHql="delete from Variable var where var.execution.dbid=? ";
		String delExeHql="delete from ExecutionImpl exi where exi.parent.dbid=? and exi.state=?";
		
		//update(delVarHql,parentDbid);
		update(delExeHql,parentDbid,Execution.STATE_ACTIVE_CONCURRENT);

	}
	/**
	 * 按主键查找execution实体
	 * @param dbid
	 * @return
	 */
	public Execution getExecutionByDbid(Long dbid){
		String hql="from ExecutionImpl exi where exi.dbid=?";
		return(Execution)findUnique(hql,new Object[]{dbid});
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.htsoft.oa.dao.flow.TaskDao#save(org.jbpm.pvm.internal.model.ExecutionImpl)
	 */
	public void save(ExecutionImpl executionImpl){
		 getHibernateTemplate().save(executionImpl);
	}
	
}
