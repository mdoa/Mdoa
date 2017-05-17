package com.htsoft.oa.service.flow.impl;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jbpm.api.ExecutionService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.task.ParticipationImpl;
import org.jbpm.pvm.internal.task.TaskImpl;

import com.htsoft.core.Constants;
import com.htsoft.core.jms.MailMessageProducer;
import com.htsoft.core.jms.MobileMessageProducer;
import com.htsoft.core.model.DynaModel;
import com.htsoft.core.model.MailModel;
import com.htsoft.core.service.DynamicService;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.oa.action.flow.FlowRunInfo;
import com.htsoft.oa.model.communicate.SmsMobile;
import com.htsoft.oa.model.flow.NodeNodeUserMapping;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.model.info.ShortMessage;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.communicate.SmsMobileService;
import com.htsoft.oa.service.flow.FlowFormService;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.flow.ProcessRunService;
import com.htsoft.oa.service.flow.ProcessService;
import com.htsoft.oa.service.flow.RunDataService;
import com.htsoft.oa.service.info.ShortMessageService;
import com.htsoft.oa.service.system.AppUserService;


public class ProcessServiceImpl implements ProcessService{
	
	private final Log logger=LogFactory.getLog(ProcessServiceImpl.class);
	
	public ProcessServiceImpl() {}
	
	@Resource
	private ProcessRunService processRunService;
	@Resource
	ProDefinitionService proDefinitionService;
	@Resource
	private JbpmService jbpmService;
	@Resource
	private FlowFormService flowFormService;
	
	@Resource
	private AppUserService appUserService;
	@Resource
	private MailMessageProducer mailMessageProducer;
	
	@Resource
	private MobileMessageProducer mobileMessageProducer;
	
	@Resource
	private SmsMobileService smsMobileService;
	
	@Resource
	private RunDataService runDataService;
	
	@Resource
	private ShortMessageService shortMessageService;
	
	@Resource
	private ExecutionService executionService;
	
	
	/**
	 * 启动工作流  传入defId
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public ProcessRun doStartFlow(HttpServletRequest request) throws Exception{
		FlowRunInfo startInfo=getFlowRunInfo(request);
		ProcessRun processRun=null;
		
		String useTemplate=request.getParameter("useTemplate");

		//若在提交参数中指定启动工作流前需要预处理
		int result=invokeHandler(startInfo, "PRE");
		
		if(result==-1 || result>=1){//正常
			DynaModel entity=null;
			if(!"true".equals(useTemplate)){
				//保存业务数据
				entity=flowFormService.doSaveData(startInfo);
				if(entity!=null){
					//把业务数据也放至流程中
					startInfo.getVariables().putAll(entity.getDatas());
				}else{
					return processRun;
				}
			}
			//启动流程
			processRun=jbpmService.doStartProcess(startInfo);
			
			if("true".equals(useTemplate)){
			    startInfo.getVariables().putAll(BeanUtil.getMapFromRequest(request));
			}
			//保存后，把流程中相关的变量及数据全部提交至run_data表中，以方便后续的展示
			runDataService.saveFlowVars(processRun.getRunId(), startInfo.getVariables());
			
			//加上，以方便第三方业务读取流程相关的数据
			startInfo.setProcessRun(processRun);
			
			//发送邮件或短信通知相关人员
			notice(processRun,startInfo);
			
			if(entity!=null){
				//更新runId，通过runId，可以取到该审批业务的所有审批信息 
				try{
					entity.set("runId", processRun.getRunId());
					DynamicService service=BeanUtil.getDynamicServiceBean((String)entity.get(FlowRunInfo.ENTITY_NAME));
					service.save(entity.getDatas());
				}catch(Exception ex){
					ex.printStackTrace();
					logger.debug("error:" + ex.getMessage());
				}
			}
		}
		//若在提交参数中指定启动工作流后需要后处理
		invokeHandler(startInfo,"AFT");
		
		return processRun;
	}
	/**
	 * 完成任务，并且进入下一步
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public ProcessRun doNextFlow(HttpServletRequest request) throws Exception{
		
		String useTemplate=request.getParameter("useTemplate");
		
		FlowRunInfo nextInfo=getFlowRunInfo(request);
		
		ProcessRun processRun=null;
		//执行之前的动作
		int result=invokeHandler(nextInfo, "PRE");
		
		if(result==-1 || result>=1){//正常
			if(!"true".equals(useTemplate)){
				//保存业务数据
				DynaModel entity=flowFormService.doSaveData(nextInfo);
				if(entity!=null){
					//把业务数据也放至流程中
					nextInfo.getVariables().putAll(entity.getDatas());
				}
			}
			
			//保存流程数据
			processRun= jbpmService.doNextStep(nextInfo);
			
			if("true".equals(useTemplate)){
			    nextInfo.getVariables().putAll(BeanUtil.getMapFromRequest(request));
			}
    			//保存后，把流程中相关的变量及数据全部提交至run_data表中，以方便后续的展示
    			runDataService.saveFlowVars(processRun.getRunId(), nextInfo.getVariables());
			
			nextInfo.setProcessRun(processRun);
			//发送邮件或短信通知相关人员
			notice(processRun,nextInfo);
		}
		//执行之后的动作
		invokeHandler(nextInfo,"AFT");
		
		return processRun;
	}
	
	/**
	 * 使用邮件或短信通知相关的人员处理
	 * @param piId
	 */
	private void notice(ProcessRun processRun,FlowRunInfo flowInfo ){
		if(processRun.getPiId()==null) return;
		List<Task> taskList=jbpmService.getTasksByPiId(processRun.getPiId());
		
		for(Task task:taskList){
			TaskImpl taskImpl=(TaskImpl)task;
			if(taskImpl.getAssignee()==null){
				Iterator<ParticipationImpl> partIt= taskImpl.getAllParticipants().iterator();
				while(partIt.hasNext()){
					ParticipationImpl part=partIt.next();
					if(part.getGroupId()!=null && StringUtil.isNumeric(part.getGroupId())){
						//发送邮件
						List<AppUser> appUserList=appUserService.findByRoleId(new Long(part.getGroupId()));
						for(AppUser appUser:appUserList){
							sendMailNotice(processRun.getSubject(),taskImpl,appUser,flowInfo);
						}
					}else if(part.getUserId()!=null && StringUtil.isNumeric(part.getUserId())){
						AppUser appUser=appUserService.get(new Long(part.getUserId()));
						sendMailNotice(processRun.getSubject(),taskImpl,appUser,flowInfo);
					}
				}
			}else if(StringUtil.isNumeric(taskImpl.getAssignee())){				
				AppUser appUser=appUserService.get(new Long(taskImpl.getAssignee()));
				sendMailNotice(processRun.getSubject(),taskImpl,appUser,flowInfo);
			}
		}
		
	}

	/**
	 * 发送邮件及短信通知
	 * @param task
	 * @param appUser
	 */
	private void sendMailNotice(String piSubject,Task task,AppUser appUser,FlowRunInfo flowRunInfo){
		Date curDate=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm");
		String curDateStr=sdf.format(curDate);
		
		String shortContent= curDateStr + "待办事项(" + piSubject + ")提交至审批环节("+ task.getName() + ")，请及时审批。";
		shortMessageService.save(AppUser.SYSTEM_USER, appUser.getUserId().toString(), shortContent, ShortMessage.MSG_TYPE_TASK);
		
		if(flowRunInfo.isSendMail()){
			//发送邮件
			if(appUser.getEmail()!=null){
				if(logger.isDebugEnabled()){
					logger.info("Notice " + appUser.getFullname() + " by mail:" + appUser.getEmail());
				}
				String tempPath="mail/flowMail.vm";
				Map model=new HashMap();
				model.put("curDateStr", curDateStr);
				model.put("appUser", appUser);
				model.put("task", task);
				String subject="来自"+AppUtil.getCompanyName()+"办公系统的待办任务(" + piSubject + "--" + task.getName() + ")提醒";
				
				MailModel mailModel=new MailModel();
				mailModel.setMailTemplate(tempPath);
				mailModel.setTo(appUser.getEmail());
				mailModel.setSubject(subject);
				mailModel.setMailData(model);
				//把邮件加至发送列队中去
				mailMessageProducer.send(mailModel);
			}
		}
		if(flowRunInfo.isSendMsg()){
			//发送手机短信
			if(appUser.getMobile()!=null){
				if(logger.isDebugEnabled()){
					logger.info("Notice " + appUser.getFullname() + " by mobile:" + appUser.getMobile());
				}
				
				if(appUser.getMobile()!=null){
					String content=AppUtil.getCompanyName()+"办公系统于" + curDateStr + "产生了一项待办事项(" + piSubject + "--" + task.getName() + ")，请您在规定时间内完成审批~";
					SmsMobile smsMobile=new SmsMobile();
					smsMobile.setPhoneNumber(appUser.getMobile());
					smsMobile.setSmsContent(content);
					smsMobile.setSendTime(new Date());
					smsMobile.setUserId(-1l);
					smsMobile.setUserName("system user");
					smsMobile.setStatus(SmsMobile.STATUS_NOT_SENDED);
					
					smsMobileService.save(smsMobile);
					//放置发送队列
					mobileMessageProducer.send(smsMobile);
				}
			}
		}
	}
	
	/**
	 * 初始化一个新的流程
	 * @return
	 */
	public ProcessRun getInitNewProcessRun(HttpServletRequest request){
		String defId=request.getParameter("defId");
		ProDefinition proDefinition=proDefinitionService.get(new Long(defId));
		
		return processRunService.getInitNewProcessRun(proDefinition);
	}
	
	/**
	 * 取得流程运行的相关信息
	 */
	protected FlowRunInfo getFlowRunInfo(HttpServletRequest request) {
		FlowRunInfo info=new FlowRunInfo(request);
		//Map<String, ParamField> fieldMap=getConstructFieldMap(request);
		//info.setParamFields(fieldMap);
		return info;
	}

	/**
	 * 取得流程定义
	 * @return
	 */
	protected ProDefinition getProDefinition(HttpServletRequest request){
		ProDefinition proDefinition=null;
		String defId=request.getParameter("defId");
		if(defId!=null){
			 proDefinition=proDefinitionService.get(new Long(defId));
		}else {
			String taskId=request.getParameter("taskId");
			ProcessRun processRun=processRunService.getByTaskId(taskId.toString());
			proDefinition=processRun.getProDefinition();
		}
		return proDefinition;
	}
	
	/**
	 * 流程执行前后触发
	 * @param flowRunInfo
	 * @param preAfterMethodFlag 前后标识，PRE代表前置，AFT代表后置
	 * @return 0 代表失败 1代表成功，-1代表不需要执行
	 */
	public int invokeHandler(FlowRunInfo flowRunInfo,String preAfterMethodFlag) throws Exception{
		String handler=null;
		//前置方法
		if("PRE".equals(preAfterMethodFlag)){
			handler=flowRunInfo.getPreHandler();
		}else{//后置方法
			handler=flowRunInfo.getAfterHandler();
		}
		//没有指定方法
		if(handler==null) return -1;
			
		Integer result=0;
		
		String [] beanMethods=handler.split("[.]");
		if(beanMethods!=null){
			String beanId=beanMethods[0];
			String method=beanMethods[1];
			//触发该Bean下的业务方法
			Object serviceBean=AppUtil.getBean(beanId);
			if(serviceBean!=null){
				Method invokeMethod=serviceBean.getClass().getDeclaredMethod(method, new Class[]{FlowRunInfo.class});
				result= (Integer)invokeMethod.invoke(serviceBean, flowRunInfo);
			}
		}
		//为after 添加流程变量
		if("AFT".equals(preAfterMethodFlag) && flowRunInfo.getFlowVars().size()>0){
			String piId=flowRunInfo.getPiId();
			if(piId!=null){
				executionService.setVariables(piId, flowRunInfo.getFlowVars());
			}
		}
		
		return result;
	}
	
	/**
	 * 
	 * @param taskId
	 * @param curUserId
	 * @return
	 */
	public List<NodeNodeUserMapping> getTaskNodeUserMapping(Long taskId,Long curUserId){
		ProcessDefinition pd=jbpmService.getProcessDefinitionByTaskId(taskId.toString());
		List<Transition> trans=jbpmService.getTransitionsByTaskId(taskId.toString());
		
		//curUserId from taskId
		Long startUserId=(Long)jbpmService.getVarByTaskIdVarName(taskId.toString(),FlowRunInfo.START_USER_ID);
		if(startUserId!=null)curUserId=startUserId;
		
		return getByPdUserId(pd,curUserId,trans);
	}
	/**
	 * 获取某个节点对应的所有跳转分支的人员映射
	 * @param taskId
	 * @param curUserId
	 * @param destName
	 * @return
	 */
	public List<NodeNodeUserMapping> getNodeUserMapping(Long taskId,Long curUserId,String destName){
		ProcessDefinition pd=jbpmService.getProcessDefinitionByTaskId(taskId.toString());
		List<Transition> trans=jbpmService.getNodeOuterTrans(pd, destName);
		return getDestByPdUserId(pd,curUserId,trans);
	}
	

	@Override
	public List<NodeNodeUserMapping> getStartDefNodeUserMapping(Long defId,Long curUserId) {
		return getStartDefNodeUserMapping(defId, curUserId,false);
	}
	
	/**
	 * 获取流程启动的跳转分支路径及其对应的执行人员
	 * @param defId
	 * @param curUserId
	 * @param isNextStart 把开始节点与下一步结节当作一个节点
	 * @return
	 */
	public List<NodeNodeUserMapping> getStartDefNodeUserMapping(Long defId,Long curUserId,boolean isNextStart){
		
		ProcessDefinition pd=jbpmService.getProcessDefinitionByDefId(defId);
		//取得最新版的流程定义了
		List<Transition> trans=jbpmService.getStartOutTransByDeployId(pd.getDeploymentId(),isNextStart);
		
		return getByPdUserId(pd,curUserId,trans);
	}
	
	private List<NodeNodeUserMapping> getDestByPdUserId(ProcessDefinition pd,Long userId,List<Transition> trans){
		List<NodeNodeUserMapping> nodeList=new ArrayList<NodeNodeUserMapping>();
		for(Transition tran:trans){
			if(tran!=null && tran.getDestination()!=null){
				String nodeName=tran.getSource().getName();
				String nodeType=tran.getSource().getType();
				
				Map<String,Set<AppUser>> nodeUserMap=new HashMap<String,Set<AppUser>>();
				NodeNodeUserMapping nodeNodeUserMapping=new NodeNodeUserMapping(nodeName,nodeUserMap);
				
				if("decision".equals(nodeType) || "fork".equals(nodeType) || "join".equals(nodeType)){//若为非任务节点
					
					List<Transition> subTrans=jbpmService.getTransByPdActivityName(pd, nodeName);
					genUserMap(pd,subTrans,nodeUserMap,userId);
					
				}else if("task".equals(nodeType)){//若为任务节点
					
					 Set<AppUser> users=jbpmService.getNodeHandlerUsers(pd,nodeName,userId);
					 nodeUserMap.put(nodeName, users);
					
				}
				
				nodeList.add(nodeNodeUserMapping);
			}
		}
		return nodeList;
	}
	
	private List<NodeNodeUserMapping> getByPdUserId(ProcessDefinition pd,Long userId,List<Transition> trans){
		

		List<NodeNodeUserMapping> nodeList=new ArrayList<NodeNodeUserMapping>();

		for(Transition tran:trans){
			if(tran!=null && tran.getDestination()!=null){
				String nodeName=tran.getDestination().getName();
				String nodeType=tran.getDestination().getType();
				
				Map<String,Set<AppUser>> nodeUserMap=new HashMap<String,Set<AppUser>>();
				NodeNodeUserMapping nodeNodeUserMapping=new NodeNodeUserMapping(nodeName,nodeUserMap);
				
				if("decision".equals(nodeType) || "fork".equals(nodeType) || "join".equals(nodeType)){//若为非任务节点
					List<Transition> subTrans=jbpmService.getTransByPdActivityName(pd, nodeName);
					genUserMap(pd,subTrans,nodeUserMap,userId);
				}else if("task".equals(nodeType)){//若为任务节点
					
					 Set<AppUser> users=jbpmService.getNodeHandlerUsers(pd,nodeName,userId);
					 nodeUserMap.put(nodeName, users);
					
				}
				 nodeList.add(nodeNodeUserMapping);
			}
		}
		return nodeList;
	}
	
	private void genUserMap(ProcessDefinition pd,List<Transition> trans, Map<String,Set<AppUser>> nodeUserMap,Long curUserId){
		for(Transition tran:trans){
			if(tran!=null && tran.getDestination()!=null){
				String nodeName=tran.getDestination().getName();
				String nodeType=tran.getDestination().getType();
				
				if("decision".equals(nodeType) || "fork".equals(nodeType) || "join".equals(nodeType)){//若为非任务节点
					List<Transition> subTrans=jbpmService.getTransByPdActivityName(pd, nodeName);
					genUserMap(pd,subTrans,nodeUserMap,curUserId);
				}else if("task".equals(nodeType)){
					 Set<AppUser> users=jbpmService.getNodeHandlerUsers(pd,nodeName,curUserId);
					 nodeUserMap.put(nodeName, users);
				}
				
			}
		}
	}
	
	/**
	 * 移动端流程审批完成任务，并且进入下一步
	 * @param request
	 * @return
	 * @throws Exception 
	 */
	public ProcessRun mobileDoNextFlow(HttpServletRequest request) throws Exception{
		
		String useTemplate=request.getParameter("useTemplate");
		
		FlowRunInfo nextInfo= new FlowRunInfo(request);
		
		// 取得下一步执行人
		ProcessDefinition pd = null;
		Set<AppUser> users = null;
		
		String taskId = request.getParameter("taskId");
		String defId = request.getParameter("defId");
		String destName = request.getParameter("destName");
		if (taskId != null) {// 按流程任务取
			String isParentFlow = request.getParameter("isParentFlow");
			if ("true".equals(isParentFlow)) {
				TaskImpl taskImpl = (TaskImpl) jbpmService.getTaskById(taskId.toString());
				pd = jbpmService.getProcessDefinitionByPdId(taskImpl
						.getExecution().getSuperProcessExecution()
						.getProcessDefinitionId());
			} else {
				pd = jbpmService.getProcessDefinitionByTaskId(taskId.toString());
			}
			Long startUserId = jbpmService.getFlowStartUserId(taskId.toString());
			users = jbpmService.getNodeHandlerUsers(pd, destName, startUserId);
		} else {// 按流程定义取
			pd = jbpmService.getProcessDefinitionByDefId(new Long(defId));
			users = jbpmService.getNodeHandlerUsers(pd, destName, ContextUtil.getCurrentUserId());
		}

		StringBuffer uIds = new StringBuffer();
		Iterator<AppUser> it = users.iterator();
		int i = 0;
		while (it.hasNext()) {
			AppUser user = it.next();
			if (i > 0) {
				uIds.append(",");
			}
			uIds.append(user.getUserId());
			i++;
		}
		nextInfo.getVariables().put(Constants.FLOW_ASSIGN_ID, uIds);
		// 取得下一步执行人
		
		ProcessRun processRun=null;
		//执行之前的动作
		int result=invokeHandler(nextInfo, "PRE");
		
		if(result==-1 || result>=1){//正常
			if(!"true".equals(useTemplate)){
				//保存业务数据
				DynaModel entity=flowFormService.doSaveData(nextInfo);
				if(entity!=null){
					//把业务数据也放至流程中
					nextInfo.getVariables().putAll(entity.getDatas());
				}
			}
			
			//保存流程数据
			processRun= jbpmService.doNextStep(nextInfo);
			
			if("true".equals(useTemplate)){
			    nextInfo.getVariables().putAll(BeanUtil.getMapFromRequest(request));
			}
    			//保存后，把流程中相关的变量及数据全部提交至run_data表中，以方便后续的展示
    			runDataService.saveFlowVars(processRun.getRunId(), nextInfo.getVariables());
			
			nextInfo.setProcessRun(processRun);
			//发送邮件或短信通知相关人员
			notice(processRun,nextInfo);
		}
		//执行之后的动作
		invokeHandler(nextInfo,"AFT");
		
		return processRun;
	}
	
}
