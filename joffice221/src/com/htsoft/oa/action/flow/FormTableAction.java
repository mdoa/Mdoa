package com.htsoft.oa.action.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.model.DynaModel;
import com.htsoft.core.service.DynamicService;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.flow.FormTable;
import com.htsoft.oa.model.flow.ProcessRun;
import com.htsoft.oa.service.flow.FormTableService;
import com.htsoft.oa.service.flow.ProcessRunService;
import com.htsoft.oa.util.FlowUtil;

import flexjson.JSONSerializer;
/**
 * 
 * @author 
 *
 */
public class FormTableAction extends BaseAction{
	@Resource
	private FormTableService formTableService;
	private FormTable formTable;
	@Resource
	private ProcessRunService processRunService;
	
	private Long tableId;

	public Long getTableId() {
		return tableId;
	}

	public void setTableId(Long tableId) {
		this.tableId = tableId;
	}

	public FormTable getFormTable() {
		return formTable;
	}

	public void setFormTable(FormTable formTable) {
		this.formTable = formTable;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<FormTable> list= formTableService.getAll(filter);
		Type type=new TypeToken<List<FormTable>>(){}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
		.append(filter.getPagingBean().getTotalItems()).append(",result:");
		
		
		JSONSerializer serializer=new JSONSerializer();
		buff.append(serializer.serialize(list));
						

		
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
				formTableService.remove(new Long(id));
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
		FormTable formTable=formTableService.get(tableId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(formTable));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(formTable.getTableId()==null){
			formTableService.save(formTable);
		}else{
			FormTable orgFormTable=formTableService.get(formTable.getTableId());
			try{
				BeanUtil.copyNotNullProperties(orgFormTable, formTable);
				formTableService.save(orgFormTable);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	
	/**
	 * 显示详细信息
	 * @return
	 */
	public String getModel(){
		String tableKey = RequestUtil.getString(getRequest(), "tableKey","general");
		String fileName = RequestUtil.getString(getRequest(), "fileName","mainId");
		String value = RequestUtil.getString(getRequest(), "code","");
		tableKey=FormTable.TABLE_PRE_NAME +tableKey;
		DynaModel dyModel = FlowUtil.DynaModelMap.get(tableKey);
		Class clazz=dyModel.getType(fileName);
		DynamicService dynamicService = BeanUtil.getDynamicServiceBean(tableKey);
		QueryFilter filter=new QueryFilter();
		String name=clazz.getName();
		if(name.equals("java.util.Date")){
			filter.addFilter("Q_"+fileName+"_D_EQ", value);
		}else if(name.equals("java.util.Float")){
			filter.addFilter("Q_"+fileName+"_FT_EQ", value);
		}else if(name.equals("java.lang.Double")){
			filter.addFilter("Q_"+fileName+"_BD_EQ", value);
		}else if(name.equals("java.lang.Long")){
			filter.addFilter("Q_"+fileName+"_L_EQ", value);
		}else if(name.equals("java.lang.Integer")){
			filter.addFilter("Q_"+fileName+"_N_EQ", value);
		}else if(name.equals("java.lang.Short")){
			filter.addFilter("Q_"+fileName+"_S_EQ", value);
		}else{
			filter.addFilter("Q_"+fileName+"_S_EQ", value);
		}
		filter.setPagingBean(new PagingBean(0,25));
		String runId = "";
		try{
			List<Object> list=dynamicService.getAll(filter);
			if(list.size()<1){
				StringBuffer sb = new StringBuffer("{success:false,'message':'未找到相关文件，请确认关联编号"+value+"是否正确!'}");
				setJsonString(sb.toString());
				return SUCCESS;
			}
			for(Object entity:list){
				String str=JsonUtil.mapEntity2Json((Map) entity, tableKey);
				JSONObject a = JSONObject.fromObject(str);  
				runId=(a.get("runId")).toString();
				 break;
			}
		}catch(Exception e){
			StringBuffer sb = new StringBuffer("{success:false,'message':'对应实体未找到:"+e.getMessage()+"'}");
			setJsonString(sb.toString());
			return SUCCESS;
		}
		ProcessRun  p=processRunService.get(Long.parseLong(runId));
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:{");
		sb.append("'defId':'"+p.getDefId()+"'");
		sb.append(",'piId':'"+p.getPiId()+"'");
		sb.append(",'runId':'"+p.getRunId()+"'");
		sb.append("}}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
}
