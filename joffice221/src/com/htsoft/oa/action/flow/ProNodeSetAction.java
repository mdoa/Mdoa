package com.htsoft.oa.action.flow;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.model.flow.ProNodeSet;
import com.htsoft.oa.model.flow.TaskSign;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.service.flow.ProNodeSetService;

/**
 * 
 * @author
 * 
 */
public class ProNodeSetAction extends BaseAction {
	@Resource
	private ProNodeSetService proNodeSetService;
	@Resource
	private ProDefinitionService proDefinitionService;

	private ProNodeSet proNodeSet;

	private TaskSign taskSign;

	private Long setId;

	public Long getSetId() {
		return setId;
	}

	public void setSetId(Long setId) {
		this.setId = setId;
	}

	public ProNodeSet getProNodeSet() {
		return proNodeSet;
	}

	public void setProNodeSet(ProNodeSet proNodeSet) {
		this.proNodeSet = proNodeSet;
	}

	public TaskSign getTaskSign() {
		return taskSign;
	}

	public void setTaskSign(TaskSign taskSign) {
		this.taskSign = taskSign;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<ProNodeSet> list = proNodeSetService.getAll(filter);

		Type type = new TypeToken<List<ProNodeSet>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		jsonString = buff.toString();

		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				proNodeSetService.remove(new Long(id));
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
		ProNodeSet proNodeSet = proNodeSetService.get(setId);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(proNodeSet));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (proNodeSet.getSetId() == null) {
			proNodeSetService.save(proNodeSet);
		} else {
			ProNodeSet orgProNodeSet = proNodeSetService.get(proNodeSet
					.getSetId());
			try {
				BeanUtil.copyNotNullProperties(orgProNodeSet, proNodeSet);
				proNodeSetService.save(orgProNodeSet);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;

	}
	/**
	 * 保存节点及会签相关信息
	 * @return
	 */
	public String infoSave() {
		if (proNodeSet.getSetId() == null) {
			proNodeSetService.infoSave(proNodeSet, taskSign);
		} else {
			ProNodeSet orgProNodeSet = proNodeSetService.get(proNodeSet
					.getSetId());
			try {
				BeanUtil.copyNotNullProperties(orgProNodeSet, proNodeSet);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
			proNodeSetService.infoSave(orgProNodeSet, taskSign);
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String nodeSetList() {
		String defId = getRequest().getParameter("defId");
		// 获取当前的流程定义
		ProDefinition proDefinition = proDefinitionService.get(new Long(defId));
		if(proDefinition.getParentId()!=null){
			defId = proDefinition.getParentId().toString();
		}
		// 获取当前节点信息
		List<ProNodeSet> list = proNodeSetService
				.findProNodeSetByDefIdDeployId(new Long(defId),
						proDefinition.getDeployId());
		// 输出json
		JacksonMapper mapper = new JacksonMapper(true);
		this.setJsonString(mapper.toJson(list));

		return SUCCESS;
	}
}
