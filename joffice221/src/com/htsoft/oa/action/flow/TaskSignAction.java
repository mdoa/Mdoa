package com.htsoft.oa.action.flow;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.flow.ProNodeSet;
import com.htsoft.oa.model.flow.TaskSign;
import com.htsoft.oa.service.flow.ProNodeSetService;
import com.htsoft.oa.service.flow.TaskSignService;

/**
 * @description 任务会签
 * @class TaskSignAction
 * @author YHZ
 * @company www.jee-soft.cn
 * @data 2011-1-5PM
 * 
 */
public class TaskSignAction extends BaseAction {
	@Resource
	private TaskSignService taskSignService;
	@Resource
	private ProNodeSetService proNodeSetService;

	private TaskSign taskSign;

	private Long signId;
	private Long setId; // 流程节点设置Id

	public Long getSignId() {
		return signId;
	}

	public void setSignId(Long signId) {
		this.signId = signId;
	}

	public TaskSign getTaskSign() {
		return taskSign;
	}

	public void setTaskSign(TaskSign taskSign) {
		this.taskSign = taskSign;
	}

	public Long getSetId() {
		return setId;
	}

	public void setSetId(Long setId) {
		this.setId = setId;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<TaskSign> list = taskSignService.getAll(filter);

		Type type = new TypeToken<List<TaskSign>>() {
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
				taskSignService.remove(new Long(id));
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
		TaskSign taskSign = taskSignService.get(signId);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(taskSign);

		return SUCCESS;
	}

	/*
	 * 根据assignId查询TaskSign对象
	 */
	public String find() {
		TaskSign taskSign = taskSignService.getBySetId(setId);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(taskSign);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (taskSign.getSignId() == null) {
			ProNodeSet proNodeSet = proNodeSetService.get(setId);
			taskSign.setProNodeSet(proNodeSet);
			taskSignService.save(taskSign);
		} else {
			TaskSign orgTaskSign = taskSignService.get(taskSign.getSignId());
			try {
				BeanUtil.copyNotNullProperties(orgTaskSign, taskSign);
				taskSignService.save(orgTaskSign);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;

	}
}
