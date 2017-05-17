package com.htsoft.oa.action.archive;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.lang.reflect.Type;
import java.util.List;
import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.JsonUtil;
import com.htsoft.core.web.action.BaseAction;


import com.htsoft.oa.model.archive.ArchTemplate;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.archive.ArchTemplateService;
import com.htsoft.oa.service.system.FileAttachService;

import flexjson.JSONSerializer;
/**
 * 
 * @author 
 *
 */
public class ArchTemplateAction extends BaseAction{
	@Resource
	private ArchTemplateService archTemplateService;
	
	@Resource
	private FileAttachService fileAttachService;
	
	private ArchTemplate archTemplate;
	
	private Long templateId;

	public Long getTemplateId() {
		return templateId;
	}

	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}

	public ArchTemplate getArchTemplate() {
		return archTemplate;
	}

	public void setArchTemplate(ArchTemplate archTemplate) {
		this.archTemplate = archTemplate;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		QueryFilter filter=new QueryFilter(getRequest());
		List<ArchTemplate> list= archTemplateService.getAll(filter);
		

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
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
				archTemplateService.remove(new Long(id));
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
		ArchTemplate archTemplate=archTemplateService.get(templateId);
		
		JSONSerializer jsonSerializer=JsonUtil.getJSONSerializer();
		//将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(jsonSerializer.serialize(archTemplate));
		sb.append("}");
		setJsonString(sb.toString());
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		Long fileId = archTemplate.getFileId();
		
		if(StringUtils.isNotEmpty(String.valueOf(fileId))){
			FileAttach file = fileAttachService.get(fileId);
			archTemplate.setFileAttach(file);
		}
		archTemplateService.save(archTemplate);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
