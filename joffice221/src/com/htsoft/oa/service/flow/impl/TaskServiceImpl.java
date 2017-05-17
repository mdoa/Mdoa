package com.htsoft.oa.service.flow.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.jbpm.api.Execution;
import org.jbpm.api.ProcessInstance;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.htsoft.core.jbpm.pv.TaskInfo;
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.flow.TaskDao;
import com.htsoft.oa.model.flow.JbpmTask;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.model.info.ShortMessage;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProcessRunService;
import com.htsoft.oa.service.flow.RunDataService;
import com.htsoft.oa.service.flow.TaskService;
import com.htsoft.oa.service.info.ShortMessageService;
import com.htsoft.oa.service.system.AppUserService;

public class TaskServiceImpl extends BaseServiceImpl<TaskImpl> implements TaskService{

	@Resource
	private ProcessRunService processRunService;
	@Resource
	private ShortMessageService shortMessageService;
	@Resource
	private JbpmService jbpmService;
	@Resource
	private RunDataService runDataService;
	private TaskDao dao;
	public TaskServiceImpl(TaskDao dao) {
		super(dao);
		this.dao=dao;
	}
	/**
	 * 默认流程任务期限配置为 1,流程任务挂起
	 */
	private static short DUE_CONFIG_DEFAULT = (short)1;
	/**
	 * 流程任务期限配置为 2,流程任务执行下一步
	 */
	private static short DUE_CONFIG_NEXT = (short)2;
	/**
	 * 流程任务期限配置为 2,流程任务结束
	 */
	private static short DUE_CONFIG_END = (short)3;
	
	private Short dueDateConfig;
	
	public Short getDueDateConfig() {
		return dueDateConfig;
	}
	public void setDueDateConfig(Short dueDateConfig) {
		this.dueDateConfig = dueDateConfig;
	}

	@Resource
	private AppUserService appUserService;
	
	public List<TaskImpl> getTasksByUserId(String userId,PagingBean pb){
		return dao.getTasksByUserId(userId,pb);
	}
	/**
	 * 取得所有任务
	 * @param taskName
	 * @param pb
	 * @return
	 */
	public List<TaskInfo> getAllTaskInfos(String taskName,PagingBean pb){
		List<TaskImpl> list=dao.getAllTasks(taskName, pb);
		List<TaskInfo> taskInfoList=constructTaskInfos(list);;
		return taskInfoList;
	}
	
	
	protected List<TaskInfo> constructTaskInfos(List<TaskImpl> taskImpls){
		List<TaskInfo> taskInfoList=new ArrayList<TaskInfo>();
		Date curDate=new Date();
		for(TaskImpl taskImpl:taskImpls){
			TaskInfo taskInfo=new TaskInfo(taskImpl);
			if(taskImpl.getAssignee()!=null&&!taskImpl.getAssignee().trim().equalsIgnoreCase("null")){
				try{
					AppUser user=appUserService.get(new Long(taskImpl.getAssignee()));
					taskInfo.setAssignee(user.getFullname());
				}catch(Exception ex){
					logger.error(ex);
				}
			}
			if(taskImpl.getDuedate()!=null){
				if(curDate.compareTo(taskImpl.getDuedate())<0){
					taskInfo.setIsDue(TaskInfo.DUE);
				}
			}
			if(taskImpl.getSuperTask()!=null){
				taskImpl=taskImpl.getSuperTask();
			}
			ProcessRun processRun=processRunService.getByPiId(taskInfo.getPiId());
			if(processRun!=null){
				taskInfo.setTaskName(processRun.getSubject() + "--" + taskImpl.getActivityName());
				taskInfo.setActivityName(taskImpl.getActivityName());
			}
			//显示任务，需要加上流程的名称
			taskInfoList.add(taskInfo);
			
		
		}
		return taskInfoList;
	}
	/**
	 * 显示自定义的任务信息
	 */
	public List<TaskInfo> getTaskInfosByUserId(String userId,PagingBean pb){
		List<TaskImpl> list=getTasksByUserId(userId, pb);
//		List<TaskInfo> taskInfoList=new ArrayList<TaskInfo>();
//		for(TaskImpl taskImpl:list){
//			TaskInfo taskInfo=new TaskInfo(taskImpl);
//			if(taskImpl.getAssignee()!=null){
//				AppUser user=appUserService.get(new Long(taskImpl.getAssignee()));
//				taskInfo.setAssignee(user.getFullname());
//				
//			}
//			ProcessRun processRun=processRunService.getByPiId(taskImpl.getExecutionId());
//			if(processRun!=null){
//				taskInfo.setTaskName(processRun.getProDefinition().getName() + "--" + taskImpl.getActivityName());
//				taskInfo.setActivityName(taskImpl.getActivityName());
//			}
//			//显示任务，需要加上流程的名称
//			taskInfoList.add(taskInfo);
//		}
		
		return constructTaskInfos(list);
		
		
	}

	@Override
	public Set<Long> getHastenByActivityNameVarKeyLongVal(String activityName,
			String varKey, Long value) {
		List<JbpmTask> jtasks=dao.getByActivityNameVarKeyLongVal(activityName, varKey, value);
		Set<Long> userIds=new HashSet<Long>();
		for(JbpmTask jtask:jtasks){
			if(jtask.getAssignee()==null){
				List<Long> userlist=dao.getUserIdByTask(jtask.getTaskId());
				userIds.addAll(userlist);
				List<Long> groupList=dao.getGroupByTask(jtask.getTaskId());
				for(Long l:groupList){
					List<AppUser> uList=appUserService.findByRoleId(l);
					List<Long> idList=new ArrayList<Long>();
					for(AppUser appUser:uList){
						idList.add(appUser.getUserId());
					}
					userIds.addAll(idList);
				}
			}else{
				userIds.add(new Long(jtask.getAssignee()));
			}
		}
		return userIds;
	}
	@Override
	public List<TaskImpl> getCandidateTasks(String userId, PagingBean pb) {
		return dao.getCandidateTasks(userId, pb);
	}
	
	@Override
	public List<TaskImpl> getPersonTasks(String userId, PagingBean pb) {
		return dao.getPersonTasks(userId, pb);
	}
	
	/**
	 * 按主键查找execution实体
	 * @param dbid
	 * @return
	 */
	public Execution getExecutionByDbid(Long dbid){
		return dao.getExecutionByDbid(dbid);
	}
	
	/**
	 * 保存executionimpl
	 * @param executionImpl
	 */
	public void save(ExecutionImpl executionImpl){
		dao.save(executionImpl);
	}
	
	/**
	 * 去掉某个execution的子execution及其相关联的记录
	 * @param parentDbid
	 */
	public void removeExeByParentId(Long parentDbid){
		dao.removeExeByParentId(parentDbid);
	}
	
	/**
	 * 处理期限流程任务
	 */
	public void dueDate(){
		//取到所有(开放中的)任务
		List<TaskImpl> list = dao.getAllOpenTasks();
		Date now = new Date();
		Calendar calNow = Calendar.getInstance();
		calNow.setTime(now);
		calNow.set(Calendar.SECOND, 0);
		calNow.set(Calendar.MILLISECOND, 0); 
		Calendar calDueTime = Calendar.getInstance();
		for(TaskImpl task : list){
			Date dueDate = task.getDuedate();
			calDueTime.setTime(dueDate);
			calDueTime.set(Calendar.SECOND, 0);
			calDueTime.set(Calendar.MILLISECOND, 0);
			if(calNow.after(calDueTime)){//假如期限已到的任务,则执行流程
				//默认挂起任务,管理员有权限恢复,恢复时注意修改期限时间
				if(dueDateConfig==null || dueDateConfig.shortValue()==DUE_CONFIG_DEFAULT){
					task.suspend();
					this.save(task);
				}else if(dueDateConfig.shortValue()==DUE_CONFIG_NEXT){//结束任务
					ProcessRun processRun = processRunService.getByTaskId(task.getId());
					String piId=processRun.getPiId();
					if(processRun!=null){
						try{
							ProcessInstance pi= jbpmService.getProcessInstance(piId);
							if(pi!=null){
								jbpmService.endProcessInstance(piId);
							}
							processRun.setRunStatus(ProcessRun.RUN_STATUS_FINISHED);
							processRunService.save(processRun);
						}catch(Exception e){
							e.printStackTrace();
						}
					}
				}else if(dueDateConfig.shortValue()==DUE_CONFIG_END){//完成任务
					ProcessRun processRun = processRunService.getByTaskId(task.getId());
					Map variables = runDataService.getMapByRunId(processRun.getRunId());
					jbpmService.completeTask(task.getId(), null, null, variables);//不传入流程指向,按默认跳转
				}
			}else{
				calDueTime.add(Calendar.MINUTE, -29);
				calDueTime.add(Calendar.SECOND, -59);
				if(calNow.after(calDueTime)){//假如期限在30分钟以内,则短消息提醒
					SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
					Set<AppUser> appUserList = jbpmService.getNodeHandlerUsers(task.getId(), task.getActivityName());
					String dueDateStr=sdf.format(dueDate);
					ProcessRun processRun = processRunService.getByTaskId(task.getId());
					String shortContent= "待办事项(" + processRun.getSubject() + ")于"+ dueDateStr + "到期，请及时审批。";
					for(AppUser user : appUserList){
						shortMessageService.save(AppUser.SYSTEM_USER, user.getUserId().toString(), shortContent, ShortMessage.MSG_TYPE_TASK);
					}
				}
			}
		}
	}
	
	/**
	 * 显示自定义的任务信息
	 */
	public List getMyMobileTaskByUserId(String userId,String processName,PagingBean pb){
		List<TaskImpl> list=dao.getTasksByUserIdProcessName(userId, processName, pb);
		return pb!=null?constructTaskInfos(list):list;
	}
	
}
