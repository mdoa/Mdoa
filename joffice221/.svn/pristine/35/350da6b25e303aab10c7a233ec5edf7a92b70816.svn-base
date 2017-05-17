package com.htsoft.oa.action.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.htsoft.core.jbpm.jpdl.Node;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;


import com.htsoft.oa.model.flow.FieldRights;
import com.htsoft.oa.model.flow.FormButtonRight;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.model.flow.FormField;
import com.htsoft.oa.model.flow.FormTable;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.service.flow.FormButtonRightService;
import com.htsoft.oa.service.flow.FormDefMappingService;
import com.htsoft.oa.service.flow.JbpmService;
import com.htsoft.oa.service.flow.ProDefinitionService;
/**
 * 
 * @author 
 *
 */
public class FormButtonRightAction extends BaseAction{
	@Resource
	private FormButtonRightService formButtonRightService;
	@Resource
	private ProDefinitionService proDefinitionService;
	@Resource
	private FormDefMappingService formDefMappingService;
	@Resource
	private JbpmService jbpmService;
	private FormButtonRight formButtonRight;
	
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public FormButtonRight getFormButtonRight() {
		return formButtonRight;
	}

	public void setFormButtonRight(FormButtonRight formButtonRight) {
		this.formButtonRight = formButtonRight;
	}

	/**
	 * 显示列表
	 */
	public String list(){
		
		QueryFilter filter=new QueryFilter(getRequest());
		List<FormButtonRight> list= formButtonRightService.getAll(filter);
		

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
				formButtonRightService.remove(new Long(id));
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
		FormButtonRight formButtonRight=formButtonRightService.get(id);
		
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(formButtonRight);
		
		return SUCCESS;
	}
	/**
	 * 添加及保存操作
	 */
	public String save(){
		if(formButtonRight.getButtonId()==null){
			formButtonRightService.save(formButtonRight);
		}else{
			FormButtonRight orgFormButtonRight=formButtonRightService.get(formButtonRight.getButtonId());
			try{
				BeanUtil.copyNotNullProperties(orgFormButtonRight, formButtonRight);
				formButtonRightService.save(orgFormButtonRight);
			}catch(Exception ex){
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
		
	}
	
	/**
	 * 取流程节点权限
	 */
	public String nodes() {
		Long defId = RequestUtil.getLong(getRequest(), "defId");
		ProDefinition proDefinition = proDefinitionService.get(defId);
		String deployId =  proDefinition.getDeployId();
		FormDefMapping formDefMapping = formDefMappingService.getByDeployId(deployId);
		
		if(BeanUtil.isEmpty(formDefMapping)){
			jsonString = JSON_ERROR;
			return SUCCESS;
		}
		FormDef formDef = formDefMapping.getFormDef();
		if (BeanUtil.isEmpty(formDef)) {
			jsonString = JSON_ERROR;
			return SUCCESS;
		}
		Long mappingId = formDefMapping.getMappingId();
		//流程的节点
		List<Node> nodes = jbpmService.getFormNodesByDeployId(deployId);
		List<FormButtonRight> formButtonRights = new ArrayList<FormButtonRight>();
		List<FormTable> subTables = new ArrayList<FormTable>();
		//获取所有的表
		Set<FormTable> tables = formDef.getFormTables();
		
		Iterator<FormTable> it = tables.iterator();	
		while (it.hasNext()) {
			FormTable  formTable = it.next();
			//子表判断处理
			if(formTable.NOT_MAIN_TABLE==formTable.getIsMain().shortValue()){
				subTables.add(formTable);
			}
		}
		
		StringBuffer buff = new StringBuffer("{success:true,result:[");
		Gson gson = new Gson();
		for (int i = 0; i < nodes.size(); i++) {
			String nodeName = nodes.get(i).getName();
			buff.append(generateForNodes(subTables, mappingId, nodeName,FormButtonRight.ADD_BUTTON ));
			buff.append(generateForNodes(subTables, mappingId, nodeName,FormButtonRight.DEL_BUTTON ));
		}
		if (!subTables.isEmpty()) {
			buff.deleteCharAt(buff.length() - 1);
		}

		buff.append("]}");
		jsonString = buff.toString();
		return SUCCESS;
	
	}
	
	private String generateForNodes(List<FormTable> subTables,Long mappingId,String nodeName, Short buttonType){
		StringBuffer buff=new StringBuffer();
		for (int k = 0; k < subTables.size(); k++) {
			FormTable subTable =  subTables.get(k);
			List<FormButtonRight> formButtonRightList = formButtonRightService.getByMappingTableTaskName(mappingId,subTable.getTableId(), nodeName,buttonType);	
			FormButtonRight right = new FormButtonRight();
			if (formButtonRightList.size() > 0)
				right = formButtonRightList.get(0);
			
			buff.append("{taskName:'").append(nodeName)
					.append("',mappingId:'" + mappingId)
					.append("',buttonId:'")					
					.append(right.getButtonId() == null ? "" : right.getButtonId())
					.append("',buttonRight:'")
					.append(right.getButtonRight() == null ? 1 : right.getButtonRight())
					.append("',userType:'")
					.append(right.getUserType() == null ? 0 : right.getUserType())
					.append("',uids:'")
					.append(right.getUids() == null ? "" : right.getUids())
					.append("',unames:'")
					.append(right.getUnames() == null ? "" : right.getUnames())
					.append("',buttonType:'")
					.append(buttonType)					
					.append("'")
					.append(",tableId:'")
					.append(subTable.getTableId())
					.append("',tableShowName:'")
					.append(subTable.getTableName().replace("\"",""))
					.append("',tableName:'")
					.append(subTable.getTableKey())
					.append("'")
					.append("},");
		}
		return buff.toString();
	}
	
	
	/**
	 * 批量保存
	 */
	public String multSave() {
		String data = getRequest().getParameter("data");
		String mappingId = getRequest().getParameter("mappingId");
		Gson gson = new Gson();
		
		FormButtonRight[] formButtonRights = gson.fromJson(data, FormButtonRight[].class);
		for (FormButtonRight formButtonRight : formButtonRights) {
			if (formButtonRight.getButtonId() == -1) {
				formButtonRight.setButtonId(null);
			}
			formButtonRight.setMappingId(Long.parseLong(mappingId));
			formButtonRightService.save(formButtonRight);
		}
		
		jsonString = "{success:true}";
		return SUCCESS;
	}

	public String checkRight(){
		String rightId = getRequest().getParameter("rightId");
		Boolean b = formButtonRightService.checkByRightWidthCurrUser(Long.parseLong(rightId));
		if(b)
			setJsonString("{success:true}");
		else
			setJsonString("{success:false}");
		return SUCCESS;
	}
}
