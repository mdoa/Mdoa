package com.htsoft.oa.action.flow;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.jbpm.api.ProcessInstance;
import org.jbpm.api.model.Activity;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.history.model.HistoryTaskInstanceImpl;
import org.jbpm.pvm.internal.task.ParticipationImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.google.gson.Gson;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.jbpm.pv.TaskInfo;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.flow.HistoryTaskService;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProcessRunService;
import com.htsoft.oa.service.system.AppUserService;

/**
 * 
 * @author csx
 * 
 */
public class ProcessRunAction extends BaseAction {
	@Resource
	private ProcessRunService processRunService;
	private ProcessRun processRun;
	@Resource
	private JbpmService jbpmService;

	@Resource
	private HistoryTaskService historyTaskService;

	@Resource
	private AppUserService appUserService;

	private Long runId;

	public Long getRunId() {
		return runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public ProcessRun getProcessRun() {
		return processRun;
	}

	public void setProcessRun(ProcessRun processRun) {
		this.processRun = processRun;
	}

	/**
	 * 显示流程历史
	 * 
	 * @return
	 */
	public String history() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<ProcessRun> list = processRunService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

		return SUCCESS;
	}

	// o.runId as runId,vo.subject as subject,vo.creator as
	// creator,vo.createtime as createtime,
	// vo.piId as piId, vo.pdId as pdId,vo.piDbid as piDbId ,vo.processName as
	// processName,vo.entityName as entityName,
	// vo.entityId as entityId,vo.formDefId as formDefId,vo.runStatus as
	// runStatus
	private List<ProcessRun> arrToProcessRun(List<?> processRunList) {
		List<ProcessRun> list = new ArrayList<ProcessRun>();
		for (Object obj : processRunList) {
			if (obj instanceof Object[]) {
				Object[] maps = (Object[]) obj;
				ProcessRun run = new ProcessRun();
				run.setRunId((Long) maps[0]);
				run.setSubject((String) maps[1]);
				run.setCreator((String) maps[2]);
				run.setCreatetime((Date) maps[3]);
				run.setPiId((String) maps[4]);
				run.setPdId((String) maps[5]);
				run.setPiDbid((Long) maps[6]);
				run.setProcessName((String) maps[7]);
				run.setEntityName((String) maps[8]);
				run.setEntityId((String) maps[9]);
				run.setFormDefId((Long) maps[10]);
				run.setRunStatus((Short) maps[11]);
				run.setDefId((Long) maps[12]);
				list.add(run);
			} else {
				list.add((ProcessRun) obj);
			}
		}
		return list;
	}

	/**
	 * 显示我正在参与的尚未结束流程，以方便对流程进行追回等操作
	 * 
	 * @return
	 */
	public String myRunning() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.setFilterName("myRunning");
		filter.addParamValue(ContextUtil.getCurrentUserId());
		filter.addParamValue(ProcessRun.RUN_STATUS_RUNNING);

		List<ProcessRun> processRunList = arrToProcessRun(processRunService
				.getAll(filter));

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:[");
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

		for (ProcessRun run : processRunList) {
			buff.append("{runId:'").append(run.getRunId())
					.append("',subject:'").append(run.getSubject())
					.append("',createtime:'")
					.append(sdf.format(run.getCreatetime())).append("',piId:'")
					.append(run.getPiId()).append("',defId:'")
					.append(run.getProDefinition().getDefId())
					.append("',runStatus:'").append(run.getRunStatus())
					.append("'");
			// 通过runid取得任务
			List<Task> listTask = jbpmService.getTasksByPiId(run.getPiId());
			if (listTask != null) {
				String tasks = "";
				String usernames = "";
				int i = 0;
				for (Task task : listTask) {
					if (i++ > 0) {
						tasks += ",";
						usernames += ",";
					}
					tasks += task.getName();
					if (task.getAssignee() != null
							&& StringUtil.isNumeric(task.getAssignee())) {
						AppUser appUser = appUserService.get(new Long(task
								.getAssignee()));
						usernames += appUser.getFullname();
					} else {// 按角色取
						TaskImpl taskImpl = (TaskImpl) task;
						Iterator<ParticipationImpl> it = taskImpl
								.getParticipations().iterator();
						while (it.hasNext()) {
							ParticipationImpl part = it.next();
							if (part.getUserId() != null) {
								if (StringUtil.isNumeric(part.getUserId())) {
									AppUser appUser = appUserService
											.get(new Long(part.getUserId()));
									usernames += appUser.getFullname();
								}
							} else if (part.getGroupId() != null) {
								if (StringUtil.isNumeric(part.getGroupId())) {
									List<AppUser> users = appUserService
											.getUsersByRoleId(new Long(part
													.getGroupId()));
									for (AppUser user : users) {
										usernames += user.getFullname();
									}
								}
							}
						}
					}
				}
				buff.append(",tasks:'").append(tasks).append("'");
				buff.append(",exeUsers:'").append(usernames).append("'");
			}
			buff.append("},");
		}

		if (processRunList.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		buff.append("}");

		jsonString = buff.toString();

		// setRunResult(processRunList,filter.getPagingBean());

		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		// jsonString=formatRunList(list,filter.getPagingBean().getTotalItems());

		QueryFilter filter = new QueryFilter(getRequest());

		// 加上过滤条件，表示只显示当前用户的申请
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());

		List<ProcessRun> list = processRunService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

		return SUCCESS;
	}

	private String formatRunList(List<ProcessRun> processRunList,
			Integer totalItems) {
		Gson gson = JsonUtil.getGson();

		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(totalItems).append(",result:[");

		for (ProcessRun run : processRunList) {
			buff.append("{runId:").append(gson.toJson(run.getRunId()))
					.append(",subject:").append(gson.toJson(run.getSubject()))
					.append(",createtime:")
					.append(gson.toJson(run.getCreatetime())).append(",piId:")
					.append(gson.toJson(run.getPiId())).append(",defId:")
					.append(gson.toJson(run.getProDefinition().getDefId()))
					.append(",runStatus:")
					.append(gson.toJson(run.getRunStatus())).append("},");
		}

		if (processRunList.size() > 0) {
			buff.deleteCharAt(buff.length() - 1);
		}
		buff.append("]");
		buff.append("}");
		return buff.toString();
	}

	/**
	 * 浏览我参与的流程
	 * 
	 * @return
	 */
	public String my() {
		QueryFilter filter = new QueryFilter(getRequest());

		// 该filterName配置在app-dao.xml中
		filter.setFilterName("MyAttendProcessRun");
		// 加上过滤条件，表示只显示当前用户的申请
		filter.addParamValue(ContextUtil.getCurrentUserId());

		List<ProcessRun> processRunList = arrToProcessRun(processRunService
				.getAll(filter));
		jsonString = mapper.toPageJson(processRunList, filter.getPagingBean()
				.getTotalItems());
		// jsonString=formatRunList(processRunList,filter.getPagingBean().getTotalItems());
		return SUCCESS;
	}

	/**
	 * 删除一个尚未启动的流程
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				ProcessRun processRun = processRunService.get(new Long(id));
				if (processRun != null) {
					try {
						//判断流程是否正在运行
						if(processRun.getRunStatus().shortValue()==ProcessRun.RUN_STATUS_RUNNING.shortValue()){
							processRunService.end(processRun);
						}
					} catch (Exception e) {
						e.printStackTrace();
						setJsonString("{success:false,message:'删除数据失败，请联系管理员!'}");
						return SUCCESS;
					}
				}
				processRunService.remove(new Long(id));
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		ProcessRun processRun = processRunService.get(runId);
		jsonString = mapper.toDataJson(processRun);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		processRunService.save(processRun);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	public String instance() {
		QueryFilter filter = new QueryFilter(getRequest());

		List<ProcessRun> list = processRunService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

		return SUCCESS;
	}

	public String tasks() {
		String runId = getRequest().getParameter("runId");
		ProcessRun processRun = processRunService.get(new Long(runId));
		String piId = processRun.getPiId();
		List<Task> tasks = jbpmService.getTasksByPiId(piId);
		List<TaskImpl> list = new ArrayList<TaskImpl>();

		for (Task task : tasks) {
			list.add((TaskImpl) task);
		}
		List<TaskInfo> infos = constructTaskInfos(list, processRun);
		jsonString = mapper.toPageJson(infos, list.size());
		// 以下是原始可运行代码
		// StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		// .append(list.size()).append(
		// ",result:");
		// Gson gson=new
		// GsonBuilder().setDateFormat(Constants.DATE_FORMAT_FULL).create();
		// buff.append(gson.toJson(infos));
		// buff.append("}");
		// jsonString = buff.toString();
		return SUCCESS;
	}

	protected List<TaskInfo> constructTaskInfos(List<TaskImpl> taskImpls,
			ProcessRun processRun) {
		List<TaskInfo> taskInfoList = new ArrayList<TaskInfo>();
		for (TaskImpl taskImpl : taskImpls) {
			TaskInfo taskInfo = new TaskInfo(taskImpl);
			if (taskImpl.getAssignee() != null
					&& !taskImpl.getAssignee().trim().equalsIgnoreCase("null")) {
				try {
					AppUser user = appUserService.get(new Long(taskImpl
							.getAssignee()));
					taskInfo.setAssignee(user.getFullname());
				} catch (Exception ex) {
					logger.error(ex);
				}
			}
			if (taskImpl.getSuperTask() != null) {
				taskImpl = taskImpl.getSuperTask();
			}
			if (processRun != null) {
				taskInfo.setTaskName(processRun.getSubject() + "--"
						+ taskImpl.getActivityName());
				taskInfo.setActivityName(taskImpl.getActivityName());
			}
			// 显示任务，需要加上流程的名称
			taskInfoList.add(taskInfo);
		}
		return taskInfoList;
	}

	public String end() {
		String runId = getRequest().getParameter("runIds");
		String[] ids = runId.split(",");
		for (String id : ids) {
			ProcessRun processRun = processRunService.get(new Long(id));
			if (processRun != null) {
				try {
					processRunService.end(processRun);
				} catch (Exception e) {
					e.printStackTrace();
					setJsonString("{success:false}");
					return SUCCESS;
				}
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	/**
	 * 任务回退，只允许当前用户执行后提交到下一步，并且下一步的任务尚没有处理
	 * 
	 * @return
	 */
	public String rollback() {
		// 前一任务执节点名
		String preTaskName = null;

		ProcessRun processRun = processRunService.get(runId);
		// 取得该实例的所有的任务，并且任务的前一节点执行人为当前人，才允许回滚
		List<Task> tasks = jbpmService.getTasksByPiId(processRun.getPiId());
		String assignee = ContextUtil.getCurrentUserId().toString();
		for (Task task : tasks) {
			List<Transition> trans = jbpmService
					.getInTransForTask(task.getId());
			for (Transition tran : trans) {
				String preType = tran.getSource().getType();
				logger.info("pre node type:" + preType);

				if ("decision".equals(preType) || "fork".equals(preType)) {// 对于前一节点为汇集及分支的情况
					Activity source = tran.getSource();
					List preTrans = source.getIncomingTransitions();
					for (int i = 0; i < preTrans.size(); i++) {
						Transition tr = (Transition) preTrans.get(i);
						String outcome = tr.getName();
						String activityName = tr.getSource().getName();
						List<HistoryTaskInstanceImpl> list = historyTaskService
								.getByPiIdAssigneeOutcome(processRun.getPiId(),
										assignee, activityName, outcome);
						if (list.size() > 0) {
							HistoryTaskInstanceImpl impl = (HistoryTaskInstanceImpl) list
									.get(0);
							preTaskName = impl.getActivityName();
							logger.info("allow back 2:"
									+ impl.getActivityName());
							break;
						}
					}

				} else if ("task".equals(preType)) {// 前一节点为任务节点
					String outcome = tran.getName();
					String activityName = tran.getSource().getName();
					List<HistoryTaskInstanceImpl> list = historyTaskService
							.getByPiIdAssigneeOutcome(processRun.getPiId(),
									assignee, activityName, outcome);
					if (list.size() > 0) {
						HistoryTaskInstanceImpl impl = (HistoryTaskInstanceImpl) list
								.get(0);
						preTaskName = impl.getActivityName();
						logger.info("allow back :" + impl.getActivityName());
						break;
					}
				}

			}
		}
		// 假若前一任务为当前执行用户，则允许回退
		if (preTaskName != null) {
			logger.info("prepared to jump previous task node");
			jbpmService.jumpToPreTask(processRun.getPiId(), assignee, null,
					preTaskName);
			jsonString = "{success:true}";
		} else {
			jsonString = "{success:false}";
		}

		return SUCCESS;
	}

}
