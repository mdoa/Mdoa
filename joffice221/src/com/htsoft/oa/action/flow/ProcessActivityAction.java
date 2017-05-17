package com.htsoft.oa.action.flow;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.velocity.app.VelocityEngine;
import org.dom4j.Element;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.model.Transition;
import org.jbpm.api.task.Task;
import org.jbpm.pvm.internal.model.ActivityImpl;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jbpm.pvm.internal.task.TaskImpl;
import org.springframework.ui.velocity.VelocityEngineUtils;

import com.google.gson.Gson;
import com.htsoft.core.jbpm.jpdl.Node;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.model.DynaModel;
import com.htsoft.core.service.DynamicService;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.FileUtil;
import com.htsoft.core.util.FunctionsUtil;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.FieldRights;
import com.htsoft.oa.model.flow.FormButtonRight;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.model.flow.FormTable;
import com.htsoft.oa.model.flow.FormTemplate;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.model.flow.TaskSignData;
import com.htsoft.oa.model.flow.Transform;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.flow.FieldRightsService;
import com.htsoft.oa.service.flow.FlowFormService;
import com.htsoft.oa.service.flow.FormButtonRightService;
import com.htsoft.oa.service.flow.FormDefMappingService;
import com.htsoft.oa.service.flow.FormDefService;
import com.htsoft.oa.service.flow.FormTemplateService;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.flow.ProUserAssignService;
import com.htsoft.oa.service.flow.ProcessRunService;
import com.htsoft.oa.service.flow.ProcessService;
import com.htsoft.oa.service.flow.RunDataService;
import com.htsoft.oa.service.flow.TaskSignDataService;
import com.htsoft.oa.service.info.ShortMessageService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.util.FilePathUtil;
import com.htsoft.oa.util.FlowUtil;

import flexjson.JSONSerializer;

/**
 * 流程的活动及任务管理
 * 
 * @author csx
 * 
 */
public class ProcessActivityAction extends BaseAction {

	@Resource
	private ProDefinitionService proDefinitionService;
	@Resource
	private ProcessRunService processRunService;

	@Resource
	private JbpmService jbpmService;

	@Resource
	private RunDataService runDataService;
	@Resource
	private ProcessService processService;

	@Resource
	private FormDefMappingService formDefMappingService;

	@Resource
	private FieldRightsService fieldRightsService;

	@Resource
	private FormDefService formDefService;

	@Resource
	private VelocityEngine flowVelocityEngine;

	@Resource
	private TaskSignDataService taskSignDataService;
	@Resource
	private FlowFormService flowFormService;
	@Resource
	private FormTemplateService formTemplateService;

	@Resource
	private AppUserService appUserService;

	@Resource
	private ShortMessageService shortMessageService;

	@Resource
	ProUserAssignService proUserAssignService;
	@Resource
	private FormButtonRightService formButtonRightService;

	private String activityName;

	private Long runId;

	private Long taskId;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	public Long getRunId() {
		return runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}

	/**
	 * 流程的定义ID
	 */
	private Long defId;

	public Long getDefId() {
		return defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}

	/**
	 * 显示某个流程的任务表单信息,并生成Ext的表单的信息, 可以分别传入两个参数,传入taskId表示在执行某个流程任务需要加载的表单
	 * 若传入defId，则表示为启动流程时需要加载的表单 </br>启动流程
	 * 
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public String get() throws Exception {
		HttpServletRequest request = getRequest();
		String deployId = null;
		ProcessRun processRun = null;
		// 表单变量
		Map<String, Object> formVars = new HashMap<String, Object>();
		// 流程定义的HTML
		String defHtml = null;

		ProDefinition proDefinition = null;
		// 流程对应的表单定义
		FormDef formDef = null;

		String taskName = activityName;
		if (taskId != null) {// 通过任务Id取到当前的流程定义
			request.setAttribute("taskId", taskId);
			deployId = jbpmService.getProcessDefinitionByTaskId(
					taskId.toString()).getDeploymentId();

			// 支持子流程，子流程的表单与父流程共用
			ExecutionImpl pi = (ExecutionImpl) jbpmService
					.getProcessInstanceByTaskId(taskId.toString());
			String piId = pi.getId();
			if (pi.getSuperProcessExecution() != null) {
				piId = pi.getSuperProcessExecution().getId();
			}

			processRun = processRunService.getByPiId(piId);

			if (processRun.getFormDefId() != null) {
				formDef = formDefService.get(processRun.getFormDefId());
			}
			defHtml = processRun.getDefHtml();
			// 取到绑定的实体
			Serializable pkValue = (Serializable) processRun.getEntityId();// jbpmService.getVarByTaskIdVarName(taskId.toString(),
																			// FlowRunInfo.ENTITY_PK);
			String entityName = processRun.getEntityName();// jbpmService.getVarByTaskIdVarName(taskId.toString(),
															// FlowRunInfo.ENTITY_NAME);

			if (entityName != null) {// 实体名不为空
				DynamicService dynamicService = BeanUtil
						.getDynamicServiceBean(entityName);
				DynaModel dyModel = FlowUtil.DynaModelMap.get(entityName);
				if (pkValue != null) {// 主键值不为空
					Object entity = dynamicService.get(new Long(pkValue
							.toString()));

					request.setAttribute("entityJson",
							JsonUtil.mapEntity2Json((Map) entity, entityName));
					request.setAttribute("pkValue", pkValue);
					if (dyModel != null) {
						request.setAttribute("pkName",
								dyModel.getPrimaryFieldName());
					}
				}
			}
		} else {
			request.setAttribute("defId", defId);
			proDefinition = proDefinitionService.get(defId);
			if (activityName == null) {
				taskName = jbpmService.getStartNodeName(proDefinition);
			}
			deployId = proDefinition.getDeployId();
		}

		FormDefMapping fdm = formDefMappingService.getByDeployId(deployId);
		// 检查其是使用Ext表单还是Html表单
		List<FieldRights> rights = new ArrayList<FieldRights>();
		List<FormButtonRight> subButtonRights = new ArrayList<FormButtonRight>();
		if (fdm != null) {// 表示没有绑定表单定义，应该使用缺省的表单
			proDefinition = proDefinitionService.get(fdm.getDefId());
			if (FormDefMapping.USE_TEMPLATE.equals(fdm.getUseTemplate())) {// 使用模板

				formVars.put("activityName", taskName);

				if (taskId != null) {
					Map vars = jbpmService.getVarsByTaskId(taskId.toString());
					formVars.putAll(vars);

					if (processRun != null) {
						Map<String, Object> fVars = runDataService
								.getMapByRunId(processRun.getRunId());
						formVars.putAll(fVars);
					}
				}

				FormTemplate formTemplate = formTemplateService
						.getByMappingIdNodeName(fdm.getMappingId(), taskName);

				if (formTemplate != null
						&& FormTemplate.TEMP_TYPE_URL.equals(formTemplate
								.getTempType())) {// 使用URL模板
					request.setAttribute("formTemplate", formTemplate);
					request.setAttribute("formVars", formVars);
					return "formUrl";
				}
				// ====================使用EXT模板子=============================================================
				// 通过deployId取得其对应Vm的模板的内容

				String formPath = FilePathUtil.formTemplatePath(null, null,
						null);
				// 节点路径
				String nodeVmPath = FilePathUtil.formTemplatePath(
						proDefinition.getName(), fdm.getVersionNo(), taskName);
				// 模板路径
				String tempPath = FilePathUtil
						.formTemplatePath(proDefinition.getName(),
								fdm.getVersionNo(), "Template");
				// 程序绝对路径
				String absPath = FilePathUtil.formTemplatePath();

				if (FileUtil.isExist(absPath + nodeVmPath)) {
					formPath = nodeVmPath;
				} else if (FileUtil.isExist(absPath + tempPath)) {
					formPath = tempPath;
				}
				String formUiJs = VelocityEngineUtils.mergeTemplateIntoString(
						flowVelocityEngine, formPath, "UTF-8", formVars);
				request.setAttribute("formUiJs", formUiJs);

				return "formExt";
			} else {
				if (formDef == null) {// 若在开始运行中没有指定表单，则用后台设置对应的表单
					formDef = fdm.getFormDef();
				}
				if (formDef == null) {
					formDef = formDefService.get(FormDef.DEFAULT_FLOW_FORMID);
				}
				if (StringUtils.isEmpty(defHtml)) {
					defHtml = formDef.getDefHtml();
				}
				rights = fieldRightsService.getByMappingIdAndTaskName(
						fdm.getMappingId(), taskName);
				subButtonRights=formButtonRightService.getByMappingTaskName(fdm.getMappingId(), taskName);
			}
		} else {// 使用缺省的表单执行
			formDef = formDefService.get(FormDef.DEFAULT_FLOW_FORMID);
			if (StringUtils.isEmpty(defHtml)) {
				defHtml = formDef.getDefHtml();
			}
		}
       
		request.setAttribute("formRights", getRights(rights));
		
		request.setAttribute("subButtonRights", getSubButtonRights(subButtonRights));
		// 取得主表及从表
		Iterator<FormTable> it = formDef.getFormTables().iterator();
		List reList = new ArrayList();
		List tables = new ArrayList();
		while (it.hasNext()) {
			FormTable formTable = it.next();
			if (FormTable.MAIN_TABLE.equals(formTable.getIsMain())) {// 主表
				request.setAttribute("mainTable", formTable);
				// 取得其对应的实体名
			} else {// 从表
				DynaModel subModel = FlowUtil.DynaModelMap.get(formTable
						.getEntityName());
				if (subModel != null) {
					reList.add(subModel);
				}
				tables.add(formTable);
				// 设置该实体在主表实体中的变量名，为表格明细赋值
				String subSetVarName = FunctionsUtil
						.makeFirstLetterUpperCase(formTable.getEntityName())
						+ "s";
				request.setAttribute("subSetVarName", subSetVarName);
			}
		}
		request.setAttribute("subModels", reList);
		request.setAttribute("subTables", tables);
		request.setAttribute("formDefId", formDef.getFormDefId());
		request.setAttribute("defHtml", defHtml);
		return "formHtml";
	}

	public String flowForm() {
		return "formHtml";
	}

	/**
	 * 返回该任务对应的父流程的跳转分支
	 * 
	 * @return
	 */
	public String parentTrans() {
		if (taskId != null) {
			List<Transition> trans = jbpmService
					.getTransitionsBySubFlowTaskId(taskId.toString());

			List allTrans = new ArrayList();

			for (Transition tran : trans) {
				if (tran != null && tran.getDestination() != null) {
					allTrans.add(new Transform(tran));
				}
			}

			JSONSerializer serializer = JsonUtil.getJSONSerializer();
			String result = serializer.serialize(allTrans);

			setJsonString("{success:true,data:" + result + "}");
		}
		return SUCCESS;
	}

	public String getForm() {
		HttpServletRequest request = getRequest();
		String runId = request.getParameter("runId");
		String defId = request.getParameter("defId");
		ProcessRun processRun = processRunService.get(new Long(runId));

		if (StringUtils.isEmpty(defId)) {
			defId = processRun.getProDefinition().getDefId().toString();
		}
		ProDefinition proDefinition = proDefinitionService.get(new Long(defId));
		String deployId = proDefinition.getDeployId();
		FormDefMapping fdm = formDefMappingService.getByDeployId(deployId);
		FormDef formDef = null;

		if (processRun != null && processRun.getFormDefId() != null) {
			formDef = formDefService.get(processRun.getFormDefId());
		}

		if (fdm != null) {// 表示没有绑定表单定义，应该使用缺省的表单
			proDefinition = proDefinitionService.get(fdm.getDefId());
			if (FormDefMapping.USE_TEMPLATE.equals(fdm.getUseTemplate())) {// 使用Ext模板
				return "formHtml";
			} else {
				if (formDef == null) {// 使用processrun中指定的formDef
					formDef = fdm.getFormDef();
				}
				if (formDef == null) {
					formDef = formDefService.get(FormDef.DEFAULT_FLOW_FORMID);
				}
			}
		} else {// 使用缺省的表单执行
			formDef = formDefService.get(FormDef.DEFAULT_FLOW_FORMID);
		}
		Serializable pkValue = (Serializable) processRun.getEntityId();// jbpmService.getVarByTaskIdVarName(taskId.toString(),
																		// FlowRunInfo.ENTITY_PK);
		String entityName = processRun.getEntityName();// jbpmService.getVarByTaskIdVarName(taskId.toString(),
														// FlowRunInfo.ENTITY_NAME);

		if (entityName != null) {// 实体名不为空
			DynamicService dynamicService = BeanUtil
					.getDynamicServiceBean(entityName);
			DynaModel dyModel = FlowUtil.DynaModelMap.get(entityName);
			if (pkValue != null) {// 主键值不为空
				Object entity = dynamicService
						.get(new Long(pkValue.toString()));

				request.setAttribute("entityJson",
						JsonUtil.mapEntity2Json((Map) entity, entityName));
				request.setAttribute("pkValue", pkValue);
				if (dyModel != null) {
					request.setAttribute("pkName",
							dyModel.getPrimaryFieldName());
				}
			}
		}
		// 取得主表及从表
		Iterator<FormTable> it = formDef.getFormTables().iterator();
		List reList = new ArrayList();
		List tables = new ArrayList();
		while (it.hasNext()) {
			FormTable formTable = it.next();
			if (FormTable.MAIN_TABLE.equals(formTable.getIsMain())) {// 主表
				request.setAttribute("mainTable", formTable);
				// 取得其对应的实体名
			} else {// 从表
				DynaModel subModel = FlowUtil.DynaModelMap.get(formTable
						.getEntityName());
				if (subModel != null) {
					request.setAttribute("subPkName",
							subModel.getPrimaryFieldName());
					reList.add(subModel);
				}
				// request.setAttribute("subTable", formTable);
				tables.add(formTable);
				// 设置该实体在主表实体中的变量名，为表格明细赋值
				String subSetVarName = FunctionsUtil
						.makeFirstLetterUpperCase(formTable.getEntityName())
						+ "s";
				request.setAttribute("subSetVarName", subSetVarName);
			}
		}
		request.setAttribute("subModels", reList);
		request.setAttribute("subTables", tables);
		request.setAttribute("defId", defId);
		request.setAttribute("runId", runId);
		// request.setAttribute("formDef", formDef);
		request.setAttribute("formDefId", formDef.getFormDefId());
		request.setAttribute("defHtml", processRun.getDefHtml());
		return "formHtml";

	}

	/**
	 * 显示当前任务的执行情况，决定是否给当前用户执行
	 * 
	 * @return
	 */
	public String check() {

		// 检查该任务是否已经执行完成
		Task task = jbpmService.getTaskById(String.valueOf(taskId));
		// 该任务存在
		if (task != null) {
			String assignId = task.getAssignee();
			Long curUserId = ContextUtil.getCurrentUserId();

			// 该任务目前是由该用户来执行
			if (curUserId.toString().equals(assignId)) {
				jsonString = "{success:true,isValid:true,msg:''}";
			} else if (StringUtils.isNotEmpty(assignId)) {// 已经被其他人员申请执行
				jsonString = "{success:true,isValid:false,msg:'该任务已经被其他成员锁定执行！'}";
			} else {// 锁定该任务
				jbpmService.assignTask(task.getId(), curUserId.toString());
				jsonString = "{success:true,isValid:true,msg:'该任务已经被您锁定执行!'}";
			}
		} else {// 该任务已经完成或删除
			jsonString = "{success:true,isValid:false,msg:'该任务已经完成了'}";
		}

		return SUCCESS;
	}

	/**
	 * 启动工作流
	 */
	public String save() {
		if (logger.isDebugEnabled()) {
			logger.info("start process..............");
		}
		try {
			ProcessRun processRun=processService.doStartFlow(getRequest());
			if(processRun==null){
				setJsonString("{success:false,\"message\":\"请生成表单对应的实体\"}");
			}
		} catch (Exception ex) {
			logger.error("error:" + ex.getMessage());
			ex.printStackTrace();
			setJsonString("{success:false}");
		}
		return SUCCESS;
	}

	/**
	 * 执行下一步
	 * 
	 * @return
	 */
	public String next() {
		if (logger.isDebugEnabled()) {
			logger.info("process jump to next ..............");
		}
		try {
			processService.doNextFlow(getRequest());
		} catch (Exception ex) {
			logger.error("error:" + ex.getMessage());
			ex.printStackTrace();
			setJsonString("{success:false}");
		}
		return SUCCESS;
	}

	/**
	 * 流程是否允许回退
	 * 
	 * @return
	 */
	public String allowBack() {
		if (logger.isDebugEnabled()) {
			logger.info("allown black ");
		}

		boolean isAllown = jbpmService.isAllownBack(getRequest().getParameter(
				"taskId"));

		setJsonString("{success:" + isAllown + "}");
		return SUCCESS;
	}

	/**
	 * 通过DefId取得开始节点的分支
	 * 
	 * @return
	 */
	public String startTrans() {
		ProDefinition proDef = proDefinitionService.get(defId);
		if (proDef != null) {
			// 通过DefId取得开始节点的分支
			List<Transform> allTrans = jbpmService.startTrans(proDef);

			JacksonMapper mapper = new JacksonMapper(true,
					"yyyy-MM-dd HH:mm:ss");
			jsonString = mapper.toDataJson(allTrans);

		}
		return SUCCESS;
	}

	/**
	 * 自由跳转
	 * 
	 * @return
	 */
	public String freeTrans() {

		Gson gson = new Gson();
		StringBuffer sb = new StringBuffer();

		sb.append("[");

		List<Transition> trans = jbpmService.getFreeTransitionsByTaskId(taskId
				.toString());

		for (Transition tran : trans) {
			sb.append("[").append(gson.toJson(tran.getName())).append(",")
					.append(gson.toJson(tran.getDestination().getName()))
					.append(",")
					.append(gson.toJson(tran.getDestination().getType()))
					.append("],");
		}

		if (trans.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		sb.append("]");

		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 显示某一节点下的分支节点情况
	 * 
	 * @return
	 */
	public String outerTrans() {
		ProcessDefinition pd = null;
		if (taskId != null) {// 按流程任务取
			String isParentFlow = getRequest().getParameter("isParentFlow");
			if ("true".equals(isParentFlow)) {
				TaskImpl taskImpl = (TaskImpl) jbpmService.getTaskById(taskId
						.toString());
				pd = jbpmService.getProcessDefinitionByPdId(taskImpl
						.getExecution().getSuperProcessExecution()
						.getProcessDefinitionId());
			} else {
				pd = jbpmService
						.getProcessDefinitionByTaskId(taskId.toString());
			}
		} else {// 按流程定义取
			pd = jbpmService.getProcessDefinitionByDefId(defId);
		}

		String nodeName = getRequest().getParameter("nodeName");
		List<Transition> trans = jbpmService.getNodeOuterTrans(pd, nodeName);

		StringBuffer sb = new StringBuffer();

		// 产生目前跳转出的人员选择
		genOutTrans(pd, trans, sb);

		if (sb.length() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.insert(0, "[");
		sb.append("]");

		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 根据目前的跳转路径列表，获取其目标节点产生的节点任务及执行人员
	 * 
	 * @param pd
	 * @param trans
	 * @param sb
	 */
	private void genOutTrans(ProcessDefinition pd, List<Transition> trans,
			StringBuffer sb) {
		if (trans == null || trans.size() == 0)
			return;
		Gson gson = new Gson();
		for (Transition tran : trans) {
			// 若目标节点不为任务节点，如（为合并，同步，决定），需要递归其下面的节点
			String type = tran.getDestination().getType();
			if ("task".equals(type)) {
				sb.append("[").append(gson.toJson(tran.getName())).append(",")
						.append(gson.toJson(tran.getDestination().getName()))
						.append(",").append(gson.toJson(type)).append(",")
						.append(getUsers(pd, tran.getDestination().getName()))
						.append("],");
			} else if ("fork".equals(type) || "join".equals(type)
					|| "decision".equals(type)) {
				List<Transition> subTrans = jbpmService.getNodeOuterTrans(pd,
						tran.getDestination().getName());
				genOutTrans(pd, subTrans, sb);
			}
		}
	}

	private String getUsers(ProcessDefinition pd, String activityName) {

		Long startUserId = null;
		if (taskId != null) {
			startUserId = jbpmService.getFlowStartUserId(taskId.toString());
		} else {
			startUserId = ContextUtil.getCurrentUserId();
		}
		// 取得当前流程用户
		Set<AppUser> users = jbpmService.getNodeHandlerUsers(pd, activityName,
				startUserId);

		StringBuffer uIds = new StringBuffer();
		StringBuffer uNames = new StringBuffer();
		Iterator<AppUser> it = users.iterator();
		int i = 0;
		while (it.hasNext()) {
			AppUser user = it.next();
			if (i > 0) {
				uIds.append(",");
				uNames.append(",");
			}
			uIds.append(user.getUserId());
			uNames.append(user.getFullname());
			i++;
		}
		return "\"" + uIds.toString() + "\",\"" + uNames.toString() + "\"";
	}

	/**
	 * 取得某个任务的处理用户
	 * 
	 * @return
	 */
	public String users() {
		ProcessDefinition pd = null;
		Set<AppUser> users = null;

		if (taskId != null) {// 按流程任务取
			String isParentFlow =getRequest().getParameter("isParentFlow");
			String activityType =getRequest().getParameter("activityType");
			
			if ("true".equals(isParentFlow)) {
				TaskImpl taskImpl = (TaskImpl) jbpmService.getTaskById(taskId
						.toString());
				pd = jbpmService.getProcessDefinitionByPdId(taskImpl
						.getExecution().getSuperProcessExecution()
						.getProcessDefinitionId());
				
			} else {
				pd = jbpmService
						.getProcessDefinitionByTaskId(taskId.toString());
			}
			
			if(activityType!=null && activityType.length()>0){
				String definitionXml=jbpmService.getDefinitionXmlByDpId(pd.getDeploymentId());
				List<Element> elementList=jbpmService.getNodeByXmlWithType(definitionXml,"sub-process");
				for(Element element:elementList){
					if(element.attribute("name").getValue().toString().equals(activityName) && element.attribute("sub-process-key")!=null){
						String subKey=element.attribute("sub-process-key").getValue().toString();
						pd=jbpmService.getProcessDefinitionByKey(subKey);
						List<Node> nodeList=jbpmService.getJumpNodesByDeployId(pd.getDeploymentId());
						if(nodeList.size()>0)
							activityName=nodeList.get(0).getName();
						break;
					}
				}
			}			
			Long startUserId = jbpmService
					.getFlowStartUserId(taskId.toString());
			users = jbpmService.getNodeHandlerUsers(pd, activityName,
					startUserId);
		} else {// 按流程定义取
			pd = jbpmService.getProcessDefinitionByDefId(defId);
			users = jbpmService.getNodeHandlerUsers(pd, activityName,
					ContextUtil.getCurrentUserId());
		}

		StringBuffer uIds = new StringBuffer();
		StringBuffer uNames = new StringBuffer();
		Iterator<AppUser> it = users.iterator();
		int i = 0;
		while (it.hasNext()) {
			AppUser user = it.next();
			if (i > 0) {
				uIds.append(",");
				uNames.append(",");
			}
			uIds.append(user.getUserId());
			uNames.append(user.getFullname());
			i++;
		}

		jsonString = "[[\"\",\"" + activityName + "\",\"\",\""
				+ uIds.toString() + "\",\"" + uNames.toString() + "\"]]";

		return SUCCESS;
	}

	/**
	 * 取得当前任务所有出口，并且退回是否允许回退及当前任务是否为会签任务的情况
	 * 
	 * @return
	 */
	public String trans() {
		// 是否允许该任务回退
		// boolean isAllown=jbpmService.isAllownBack(taskId.toString());
		String preTaskName = jbpmService.getPreTask(taskId.toString());
		if (preTaskName == null) {
			preTaskName = "";
		}

		// 是否为会签任务
		boolean isSignTask = jbpmService.isSignTask(taskId.toString());

		// 取得该任务对应的所有
		List allTrans = new ArrayList();

		List<Transition> trans = jbpmService.getTransitionsByTaskId(taskId
				.toString());

		for (Transition tran : trans) {
			if (tran != null && tran.getDestination() != null) {
				allTrans.add(new Transform(tran));
			}
		}

		JSONSerializer serializer = JsonUtil.getJSONSerializer();
		String result = serializer.serialize(allTrans);

		setJsonString("{success:true,preTaskName:'" + preTaskName
				+ "',isSignTask:" + isSignTask + ",data:" + result + "}");
		return SUCCESS;
	}

	/**
	 * 显示会签情况列表
	 * 
	 * @return
	 */
	public String signList() {
		Task parentTask = jbpmService.getParentTask(taskId.toString());
		// 取得已经投票的会签情况
		List signDataList = taskSignDataService.getByTaskId(parentTask.getId());
		// 取得尚未投票的人员
		List<String> unHandleUserList = jbpmService
				.getAssigneeByTaskId(parentTask.getId());
		for (String userId : unHandleUserList) {
			TaskSignData data = new TaskSignData();
			AppUser user = appUserService.get(new Long(userId));
			data.setVoteName(user.getFullname());
			// 在前台界面同时也显示尚未投票的人员
			signDataList.add(data);
		}
		getRequest().setAttribute("signDataList", signDataList);
		return "signList";
	}

	/**
	 * 取得流程定义
	 * 
	 * @return
	 */
	protected ProDefinition getProDefinition() {
		ProDefinition proDefinition = null;
		if (runId != null) {
			ProcessRun processRun = processRunService.get(runId);
			proDefinition = processRun.getProDefinition();
		} else if (defId != null) {
			proDefinition = proDefinitionService.get(defId);
		} else {// if(piId!=null){
			ProcessRun processRun = processRunService.getByTaskId(taskId
					.toString());
			proDefinition = processRun.getProDefinition();
		}
		return proDefinition;
	}

	/**
	 * 将所对应的权限转化成字符串
	 * 
	 * @param rights
	 * @return
	 */
	protected String getRights(List<FieldRights> rights) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (FieldRights right : rights) {
			sb.append("'").append(right.getFormField().getFieldName())
					.append("':'").append(right.getReadWrite()).append("',");
		}
		if (rights.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("}");
		return sb.toString();
	}
	/**
	 * 将所对应的子表权限转化成json字符串
	 * {子表key-按钮类型:权限控制id}这样检查时有就发后端请求验证
	 * @param rights
	 * @return
	 */
	protected String getSubButtonRights(List<FormButtonRight> rights) {
		StringBuffer sb = new StringBuffer();
		sb.append("{");
		for (FormButtonRight right : rights) {
			sb.append("'").append(right.getTableName()+"-"+right.getButtonType())
					.append("':'").append(right.getButtonId()).append("',");
		}
		if (rights.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 删除子表列表的明细项
	 */
	@SuppressWarnings("unchecked")
	public String delItems() {
		String strIds = getRequest().getParameter("ids");
		String tableId = getRequest().getParameter("tableId");
		flowFormService.deleteItems(strIds, new Long(tableId));
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	
	public Integer handleComment(FlowRunInfo flowRunInfo){
		
		ProcessRun processRun = flowRunInfo.getProcessRun();
		if(flowRunInfo.isBack()){
			Map<String, Object> map=flowRunInfo.getVariables();
		}
		return 1;
	}

}
