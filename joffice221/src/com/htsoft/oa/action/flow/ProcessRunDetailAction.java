package com.htsoft.oa.action.flow;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessInstance;
import org.jbpm.pvm.internal.model.ExecutionImpl;

import com.htsoft.core.service.DynamicService;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProcessFormService;
import com.htsoft.oa.service.flow.ProcessRunService;

/**
 * 显示运行中的流程信息
 * @author csx
 *
 */
public class ProcessRunDetailAction extends BaseAction{
	@Resource
	private ProcessRunService processRunService;
	@Resource
	private ProcessFormService processFormService;
	@Resource
	private JbpmService jbpmService;
	
	private Long runId;

	public Long getRunId() {
		return runId;
	}

	public void setRunId(Long runId) {
		this.runId = runId;
	}

	private Long taskId;

	public Long getTaskId() {
		return taskId;
	}

	public void setTaskId(Long taskId) {
		this.taskId = taskId;
	}

	@Override
	public String execute() throws Exception {
		ProcessRun processRun=null;
		if(runId==null){
			ExecutionImpl pis=(ExecutionImpl)jbpmService.getProcessInstanceByTaskId(taskId.toString());
			String piId=pis.getId();
			if(pis.getSuperProcessExecution()!=null){
				piId=pis.getSuperProcessExecution().getId();
			}
			processRun=processRunService.getByPiId(piId);
			getRequest().setAttribute("processRun", processRun);
			runId=processRun.getRunId();
		}else{
			processRun=processRunService.get(runId);
		}
		
		//取到绑定的实体
		Serializable pkValue=(String)processRun.getEntityId(); 
		String entityName=processRun.getEntityName();
		
		if(pkValue!=null&& entityName!=null){
			//检查某个字符串是否number类型
			if(StringUtil.isNumeric(pkValue.toString())){
				pkValue=new Long(pkValue.toString());
			}
			if(entityName!=null){//实体名不为空
				DynamicService dynamicService=BeanUtil.getDynamicServiceBean(entityName);
				if(pkValue!=null){//主键值不为空
					Object entity=dynamicService.get(pkValue);
					//输出实体的描述信息
					if(entity!=null){
						getRequest().setAttribute("entity", entity);
						getRequest().setAttribute("entityHtml",BeanUtil.mapEntity2Html((Map<String,Object>)entity, entityName));
					}
				}
			}
		}
		
		List pfList=processFormService.getByRunId(runId);
		
		getRequest().setAttribute("pfList", pfList);
		
		return SUCCESS;
	}
}
