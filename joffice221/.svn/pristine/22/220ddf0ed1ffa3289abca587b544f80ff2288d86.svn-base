package com.htsoft.core.jbpm.pv;
/*
 *  杭州梦德软件有限公司 OA办公自动管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.Date;
import org.jbpm.pvm.internal.task.TaskImpl;

public class TaskInfo {
	/**
	 * 过期任务
	 */
	public final static Short DUE=1;
	/**
	 * 未过期
	 */
	public final static Short UN_DUE=0;
	
	//用于显示任务名称，一般会包括流程名称在前
	private String taskName;
	//活动名称
	private String activityName;
	private String assignee;
	private Date createTime;
	private Date dueDate;
	private String executionId;
	//是否过期  0=未过期,1=过期
	private Short isDue=UN_DUE;
	
	/**
	 * process instance id
	 */
	private String piId;
	
	private Long taskId;
	/**
	 * 流程状态 open 开放中,suspended 已挂起
	 */
	private String state;
	/**
	 * 任务是否可由多人来执行，默认为0,则表示该任务只能由特定的人来执行。
	 */
	private Short isMultipleTask=0;
	
	//候选人员
	private String candidateUsers="";//taskImpl.getParticipations();
	//候选角色
	private String candidateRoles="";
	
	public TaskInfo() {
	}
	
	public TaskInfo(TaskImpl taskImpl){
		this.taskName=taskImpl.getActivityName();
		
		this.activityName=taskImpl.getActivityName();
		this.assignee=taskImpl.getAssignee();
		this.dueDate=taskImpl.getDuedate();
		this.createTime=taskImpl.getCreateTime();
		if(taskImpl.getSuperTask()!=null){
			this.piId=taskImpl.getSuperTask().getProcessInstance().getId();
			this.executionId=taskImpl.getSuperTask().getExecutionId();
		}else{
			this.piId=taskImpl.getProcessInstance().getId();
			this.executionId=taskImpl.getExecutionId();
		}
		
		this.taskId=taskImpl.getDbid();
		
		if(taskImpl.getParticipations().size()>0){//可由其他人来执行
			isMultipleTask=1;
		}
		
		this.state=taskImpl.getState();
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getCandidateUsers() {
		return candidateUsers;
	}

	public void setCandidateUsers(String candidateUsers) {
		this.candidateUsers = candidateUsers;
	}

	public String getCandidateRoles() {
		return candidateRoles;
	}

	public void setCandidateRoles(String candidateRoles) {
		this.candidateRoles = candidateRoles;
	}

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Short getIsMultipleTask() {
		return isMultipleTask;
	}

	public void setIsMultipleTask(Short isMultipleTask) {
		this.isMultipleTask = isMultipleTask;
	}

	public String getPiId() {
		return piId;
	}

	public void setPiId(String piId) {
		this.piId = piId;
	}

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Short getIsDue() {
		return isDue;
	}

	public void setIsDue(Short isDue) {
		this.isDue = isDue;
	}

}
