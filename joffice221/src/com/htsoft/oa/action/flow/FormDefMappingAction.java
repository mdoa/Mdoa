package com.htsoft.oa.action.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.util.BeanUtil;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;


import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.service.flow.FormDefMappingService;
/**
 * 
 * @author 
 *
 */
public class FormDefMappingAction extends BaseAction{
	@Resource
	private FormDefMappingService formDefMappingService;
	private FormDefMapping formDefMapping;
	
	private Long mappingId;

	public Long getMappingId() {
		return mappingId;
	}

	public void setMappingId(Long mappingId) {
		this.mappingId = mappingId;
	}

	public FormDefMapping getFormDefMapping() {
		return formDefMapping;
	}

	public void setFormDefMapping(FormDefMapping formDefMapping) {
		this.formDefMapping = formDefMapping;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<FormDefMapping> list= formDefMappingService.getAll(filter);
		
		Type type=new TypeToken<List<FormDefMapping>>(){}.getType();
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
				formDefMappingService.remove(new Long(id));
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
		FormDefMapping formDefMapping=formDefMappingService.get(mappingId);
		
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(formDefMapping));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(formDefMapping.getMappingId()==null){
			formDefMappingService.save(formDefMapping);
		}else{
			FormDefMapping orgFormDefMapping=formDefMappingService.get(formDefMapping.getMappingId());
			try{
				BeanUtil.copyNotNullProperties(orgFormDefMapping, formDefMapping);
				formDefMappingService.save(orgFormDefMapping);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
}
