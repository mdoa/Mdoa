package com.htsoft.oa.action.htmobile;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.jbpm.api.model.Transition;
import org.jbpm.pvm.internal.model.ExecutionImpl;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import com.htsoft.core.jbpm.pv.TaskInfo;
import com.htsoft.core.model.DynaModel;
import com.htsoft.core.service.DynamicService;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.FileUtil;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.FieldRights;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.model.flow.FormTemplate;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.model.flow.Transform;
import com.htsoft.oa.service.flow.FieldRightsService;
import com.htsoft.oa.service.flow.FormDefMappingService;
import com.htsoft.oa.service.flow.FormDefService;
import com.htsoft.oa.service.flow.FormTemplateService;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.flow.ProcessRunService;
import com.htsoft.oa.service.flow.ProcessService;
import com.htsoft.oa.service.flow.RunDataService;
import com.htsoft.oa.util.FilePathUtil;
import com.htsoft.oa.util.FlowUtil;

import flexjson.JSONSerializer;

public class MyTaskAction extends BaseAction {
	
	@Resource(name="flowTaskService")
	private com.htsoft.oa.service.flow.TaskService flowTaskService;
	
	/**
	 * 取得我的待办任务列表
	 */
	public String my() {
		String subject = getRequest().getParameter("taskName");
		String start = getRequest().getParameter("start");
		String limit = getRequest().getParameter("limit");
		PagingBean pb = null;
		if(StringUtil.isNotEmpty(start)||StringUtil.isNotEmpty(limit)){
			pb = new PagingBean(Integer.parseInt(start),Integer.parseInt(limit));
		}
		List<TaskInfo> tasks=flowTaskService.getMyMobileTaskByUserId(ContextUtil.getCurrentUserId().toString(),subject,pb);
		jsonString = gsonFormat(tasks, 
				flowTaskService.getMyMobileTaskByUserId(ContextUtil.getCurrentUserId().toString(),subject,null).size());
		return SUCCESS;
	}
	
	/**
	 * 取得我的待办任务数
	 */
	public String getNotice() {
		String id = getRequest().getParameter("id");
		List<TaskInfo> tasks=flowTaskService.getMyMobileTaskByUserId(ContextUtil.getCurrentUserId().toString(),"",null);
		jsonString = "{\"success\":true,\"totalCounts\":"+tasks.size()+",\"id\":\""+id+"\"}";
		return SUCCESS;
	}
	
	@Resource
	private JbpmService jbpmService;
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private FormDefService formDefService;
	@Resource
	private ProDefinitionService proDefinitionService;
	@Resource
	private FormDefMappingService formDefMappingService;
	@Resource
	private RunDataService runDataService;
	@Resource
	private FormTemplateService formTemplateService;
	@Resource
	private FieldRightsService fieldRightsService;
	@Resource
	private ProcessService processService;
	
	private String activityName;
	
	public String getActivityName() {
		return activityName;
	}

	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	
	private Long taskId;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
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
	
	public String get() throws Exception{

		String deployId = null;
		ProcessRun processRun = null;
		
		// 返回json
		StringBuffer sb = new StringBuffer("");
		// 表单变量
		Map<String, Object> formVars = new HashMap<String, Object>();
		// 流程定义的HTML
		String defHtml = null;		
		// 表单json
		String formjson = "";

		ProDefinition proDefinition = null;
		// 流程对应的表单定义
		FormDef formDef = null;
		
		// 表单字段的值
		JSONObject entityJsonObj = null;
		
		String taskName = activityName;
		if (taskId != null) {// 通过任务Id取到当前的流程定义
			sb.append("\"taskId\":\"").append(taskId).append("\",");
			deployId = jbpmService.getProcessDefinitionByTaskId(taskId.toString()).getDeploymentId();

			// 支持子流程，子流程的表单与父流程共用
			ExecutionImpl pi = (ExecutionImpl) jbpmService.getProcessInstanceByTaskId(taskId.toString());
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
			Serializable pkValue = (Serializable) processRun.getEntityId();

			String entityName = processRun.getEntityName();

			if (entityName != null) {// 实体名不为空
				DynamicService dynamicService = BeanUtil.getDynamicServiceBean(entityName);
				DynaModel dyModel = FlowUtil.DynaModelMap.get(entityName);
				if (pkValue != null) {// 主键值不为空
					Object entity = null;
					try{
						entity = dynamicService.get(new Long(pkValue.toString()));
					}catch(Exception e){
						jsonString = "{\"success\":false,\"msg\":\"请在系统中生成该表单实体\"}";
						return SUCCESS;
					}
					Map<String,Object> entityMap = (Map<String,Object>)entity;
					String entityJson = JsonUtil.mapEntity2Json(entityMap, entityName);
					entityJsonObj = JSONObject.fromObject(entityJson);
					sb.append("\"pkValue\":\"").append(pkValue).append("\",");
					if (dyModel != null) {
						sb.append("\"pkValue\":\"").append(dyModel.getPrimaryFieldName()).append("\",");
					}
				}
			}
		} else {
			sb.append("\"defId\":\"").append(defId).append("\",");
			proDefinition = proDefinitionService.get(defId);
			if (activityName == null) {
				taskName = jbpmService.getStartNodeName(proDefinition);
			}
			deployId = proDefinition.getDeployId();
		}

		FormDefMapping fdm = formDefMappingService.getByDeployId(deployId);
		// 检查其是使用Ext表单还是Html表单
		List<FieldRights> rights = new ArrayList<FieldRights>();
		if (fdm != null) {// 表示没有绑定表单定义，应该使用缺省的表单
			proDefinition = proDefinitionService.get(fdm.getDefId());
			if (FormDefMapping.USE_TEMPLATE.equals(fdm.getUseTemplate())) {// 使用模板
//				sb.append("\"extForm\":\"").append("true").append("\",");
				formVars.put("activityName", taskName);

				if (taskId != null) {
					Map<String,Object> vars = jbpmService.getVarsByTaskId(taskId.toString());
					formVars.putAll(vars);
					
					if (processRun != null) {
						Map<String, Object> fVars = runDataService.getMapByRunId(processRun.getRunId());
						formVars.putAll(fVars);
					}
				}

				FormTemplate formTemplate = formTemplateService.getByMappingIdNodeName(fdm.getMappingId(), taskName);
				
				// 使用URL模板
				if (formTemplate != null
						&& FormTemplate.TEMP_TYPE_URL.equals(formTemplate.getTempType())) {
					sb.append("\"formTemplate\":\"").append(formTemplate).append("\",");
					sb.append("\"formVars\":\"").append(formVars).append("\",");
				}
				// ====================使用EXT模板子=============================================================
				// 通过deployId取得其对应Vm的模板的内容

				String formPath = FilePathUtil.formTemplatePath(null, null, null);
				// 节点路径
				String nodeVmPath = FilePathUtil.formTemplatePath(proDefinition.getName(), fdm.getVersionNo(), taskName);
				// 模板路径
				String tempPath = FilePathUtil.formTemplatePath(proDefinition.getName(),fdm.getVersionNo(), "Template");
				// 程序绝对路径
				String absPath = FilePathUtil.formTemplatePath();

				if (FileUtil.isExist(absPath + nodeVmPath)) {
					formPath = nodeVmPath;
				} else if (FileUtil.isExist(absPath + tempPath)) {
					formPath = tempPath;
				}
				
				try {
					// 取得模板物理路径
					String p = this.getClass().getResource("/").toString().replace("classes", "FlowForm").replace("file:/", "");
					File f = new File(p+formPath);
					InputStream is = new FileInputStream(f);
					BufferedReader in = new BufferedReader(new InputStreamReader(is));
					String line = "";
					// 读取表单中的url，读取数据
					while ((line = in.readLine()) != null){
						if(line.indexOf("url")!=-1){
							if(line.indexOf("main")!=-1){
								String method = line.substring(line.indexOf("method=")+7,line.length()-2);
								try {
									formjson = this.executeGet(method, formVars.get("runId").toString());
								} catch (Exception e) {
									e.printStackTrace();
								}
							}
						}
					}
					is.close();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
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
				rights = fieldRightsService.getByMappingIdAndTaskName(fdm.getMappingId(), taskName);
				sb.append("\"formRights\":\"").append(getRights(rights)).append("\",");
				formjson = parseHtmlInBody(defHtml,entityJsonObj);
			}
		} else {// 使用缺省的表单执行
			formDef = formDefService.get(FormDef.DEFAULT_FLOW_FORMID);
			if (StringUtils.isEmpty(defHtml)) {
				defHtml = formDef.getDefHtml();
			}
			formjson = parseHtmlInBody(defHtml,entityJsonObj);
		}

		sb.append(formjson).append(",");

		sb.append("\"formDefId\":\"").append(formDef!=null?formDef.getFormDefId():"").append("\",");
		
		// 是否允许该任务回退
		String preTaskName = jbpmService.getPreTask(taskId.toString());
		if (preTaskName == null) {
			preTaskName = "";
		}
		sb.append("\"preTaskName\":\"").append(preTaskName).append("\",");
		// 是否为会签任务
		boolean isSignTask = jbpmService.isSignTask(taskId.toString());
		sb.append("\"isSignTask\":").append(isSignTask).append(",");
		
		// 取得该任务对应的所有
		List<Transform> allTrans = new ArrayList<Transform>();
		List<Transition> trans = jbpmService.getTransitionsByTaskId(taskId.toString());
		for (Transition tran : trans) {
			if (tran != null && tran.getDestination() != null) {
				allTrans.add(new Transform(tran));
			}
		}
		JSONSerializer serializer = JsonUtil.getJSONSerializer();
		String result = serializer.serialize(allTrans);
		sb.append("\"trans\":").append(result).append("");
		
		jsonString = "{\"success\":true,"+sb.toString()+"}";
		
		return SUCCESS;
	}
	
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
	
	public String parseHtmlInBody(String html, JSONObject jsonObj) throws Exception
	{  
		StringBuffer mainsb = new StringBuffer("\"mainform\":[");
		StringBuffer subsb = new StringBuffer("\"subform\":[");
		html = html.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "").replaceAll("&nbsp;", " ");
		if(StringUtils.isNotEmpty(html))
		{
			Document doc = Jsoup.parse(html);
			Element body = doc.body();
			
			for(Element elm:body.getAllElements())
			{
				String tag = elm.nodeName();
				if(tag.equals("table")){
					Attributes attrs = elm.attributes();
					if(attrs.hasKey("isdetail")&&attrs.get("isdetail").equals("true")){
						String tableKey = attrs.get("txtname");
						JSONArray subTableArr = jsonObj.getJSONArray("WF_"+tableKey+"s");
						subsb.append("{\"tableName\":\"").append(tableKey).append("\",\"data\":[");
						for(int idx=0;idx<subTableArr.size();idx++){
							JSONObject subRowJson = JSONObject.fromObject(subTableArr.getString(idx));
							subsb.append("{\"row\":[");
							for(Element subelm:elm.getAllElements())
							{
								String subtag = subelm.nodeName();
								if(subtag.equals("input")||subtag.equals("textarea")||subtag.equals("select")){
									subsb.append(parseHtmlInComp(subelm, subRowJson)).append(",");
								}
							}
							if(subsb.lastIndexOf(",")==subsb.length()-1){
								subsb.deleteCharAt(subsb.length() - 1);
							}
							subsb.append("]},");
						}
						if(subsb.lastIndexOf(",")==subsb.length()-1){
							subsb.deleteCharAt(subsb.length() - 1);
						}
						subsb.append("]},");
					}
				}
				else if(tag.equals("input")||tag.equals("textarea")||tag.equals("select")){
					
					Attributes attrs = elm.attributes();
					
					if(attrs.hasKey("name")){
						String name = attrs.get("name");
						if(!jsonObj.containsKey(name)){
							continue;
						}
					}
					
					mainsb.append(parseHtmlInComp(elm, jsonObj)).append(",");
					
				}
			}

			if(mainsb.lastIndexOf(",")==mainsb.length()-1){
				mainsb.deleteCharAt(mainsb.length() - 1).append("]");
			}else{
				mainsb.append("]");
			}
			
			if(subsb.lastIndexOf(",")==subsb.length()-1){
				subsb.deleteCharAt(subsb.length() - 1).append("]");
			}else{
				subsb.append("]");
			}
		}
		return mainsb.toString()+","+subsb.toString();
	}
	
	public StringBuffer parseHtmlInComp(Element elm, JSONObject jsonObj) throws Exception {
		StringBuffer sb = new StringBuffer();
		String tag = elm.nodeName();
		Attributes attrs = elm.attributes();
		
		int idx = 0;
		if(!attrs.hasKey("xtype")&&!attrs.hasKey("type")&&tag.equals("textarea")){
			if(idx==0){sb.append("{");}
			idx++;
			sb.append("\"xtype\":\"").append("fckeditor").append("\",");
		}
		
		if(tag.equals("select")){
			if(idx==0){sb.append("{");}
			idx++;
			sb.append("\"xtype\":\"").append("textfield").append("\",");
		}
		
		for(Attribute attr:attrs){
			String key = attr.getKey();
			if(key.equals("name")){
				if(idx==0){sb.append("{");}
				idx++;
				sb.append("\"key\":\"").append(attr.getValue()).append("\",");
				String value = jsonObj.getString(attr.getValue())
						.replaceAll("\r", "").replaceAll("\n", "").replaceAll("\t", "").replaceAll("&nbsp;", " ");
				sb.append("\"value\":\"").append(value).append("\",");
			}
			
			if((attrs.hasKey("xtype")&&key.equals("xtype"))
					||(!attrs.hasKey("xtype")&&key.equals("type"))){
				if(idx==0){sb.append("{");}
				idx++;
				sb.append("\"xtype\":\"").append(attr.getValue()).append("\",");
			}

			if(key.equals("txtlabel")){
				if(idx==0){sb.append("{");}
				idx++;
				sb.append("\"label\":\"").append(attr.getValue()).append("\",");
			}
		}
		
		if(sb.lastIndexOf(",")==sb.length()-1){sb.deleteCharAt(sb.length() - 1);}
		sb.append("},");
		if(sb.length()>0){
			sb.deleteCharAt(sb.length() - 1);
		}
		
		return sb;
	}
	
	/**
	 * 执行下一步
	 * 
	 * @return
	 */
	public String doNext() {
		if (logger.isDebugEnabled()) {
			logger.info("process jump to next ..............");
		}
		try {
			processService.mobileDoNextFlow(getRequest());
		} catch (Exception ex) {
			logger.error("error:" + ex.getMessage());
			ex.printStackTrace();
			setJsonString("{success:false}");
		}
		return SUCCESS;
	}
	
	/**
	 * 动态调用表单模板方法取表单数据
	 * @param handler
	 * @param runId
	 * @return
	 * @throws Exception
	 */
	public String executeGet(String handler, String runId) throws Exception{
		String json = "";
		String [] beanMethods = handler.split("[.]");
		if(beanMethods!=null){
			String beanId=beanMethods[0];
			String method=beanMethods[1];
			//触发该Bean下的业务方法
			Object serviceBean=AppUtil.getBean(beanId);
			if(serviceBean!=null){
				Class[] args1 = new Class[1];
				args1[0] = String.class;
				Method invokeMethod=serviceBean.getClass().getDeclaredMethod(method, args1);
				json = (String)invokeMethod.invoke(serviceBean, runId);
			}
		}
		return json;
	}
	
}
