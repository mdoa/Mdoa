package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.ConfAttend;
import com.htsoft.oa.service.admin.ConfAttendService;

/**
 * @description ConfAttendAction
 * @author YHZ
 * @date 2010-10-8 PM
 * 
 */
public class ConfAttendAction extends BaseAction {
	@Resource
	private ConfAttendService confAttendService;
	private ConfAttend confAttend;

	private Long attendId;

	public Long getAttendId() {
		return attendId;
	}

	public void setAttendId(Long attendId) {
		this.attendId = attendId;
	}

	public ConfAttend getConfAttend() {
		return confAttend;
	}

	public void setConfAttend(ConfAttend confAttend) {
		this.confAttend = confAttend;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<ConfAttend> list = confAttendService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

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
				confAttendService.remove(new Long(id));
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
		ConfAttend confAttend = confAttendService.get(attendId);
		jsonString = mapper.toDataJson(confAttend);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		confAttendService.save(confAttend);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
