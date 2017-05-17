package com.htsoft.oa.action.flow;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;


import com.htsoft.oa.model.flow.ProcessForm;
import com.htsoft.oa.service.flow.ProcessFormService;
/**
 * 
 * @author csx
 *
 */
public class ProcessFormAction extends BaseAction{
	@Resource
	private ProcessFormService processFormService;
	private ProcessForm processForm;
	
	private Long formId;

	public Long getFormId() {
		return formId;
	}

	public void setFormId(Long formId) {
		this.formId = formId;
	}

	public ProcessForm getProcessForm() {
		return processForm;
	}

	public void setProcessForm(ProcessForm processForm) {
		this.processForm = processForm;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<ProcessForm> list= processFormService.getAll(filter);
		
		Type type=new TypeToken<List<ProcessForm>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		Gson gson=new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");
		
		jsonString=buff.toString();
		
		return SUCCESS;
	}
	/**
	 * 批量删除
	 * @return
	 */
	public String multiDel(){
		
		String[]ids=getRequest().getParameterValues("ids");
		if(ids!=null){
			for(String id:ids){
				processFormService.remove(new Long(id));
			}
		}
		
		jsonString="{success:true}";
		
		return SUCCESS;
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String get(){
		ProcessForm processForm=processFormService.get(formId);
		
		Gson gson=new Gson();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(processForm));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		processFormService.save(processForm);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
