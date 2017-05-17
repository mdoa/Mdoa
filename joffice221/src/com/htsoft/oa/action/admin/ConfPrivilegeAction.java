package com.htsoft.oa.action.admin;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.admin.ConfPrivilege;
import com.htsoft.oa.service.admin.ConfPrivilegeService;

/**
 * @description ConfPrivilegeAction
 * @author YHZ
 * @date 2010-10-8 PM
 * 
 */
public class ConfPrivilegeAction extends BaseAction {
	@Resource
	private ConfPrivilegeService confPrivilegeService;
	private ConfPrivilege confPrivilege;

	private Long privilegeId;

	public Long getPrivilegeId() {
		return privilegeId;
	}

	public void setPrivilegeId(Long privilegeId) {
		this.privilegeId = privilegeId;
	}

	public ConfPrivilege getConfPrivilege() {
		return confPrivilege;
	}

	public void setConfPrivilege(ConfPrivilege confPrivilege) {
		this.confPrivilege = confPrivilege;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<ConfPrivilege> list = confPrivilegeService.getAll(filter);
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
				confPrivilegeService.remove(new Long(id));
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
		ConfPrivilege confPrivilege = confPrivilegeService.get(privilegeId);

		JacksonMapper mapper = new JacksonMapper(true);
		jsonString = mapper.toDataJson(confPrivilege);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		confPrivilegeService.save(confPrivilege);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 判断查看权限
	 */
	public String allowView() {
		String confId = getRequest().getParameter("confId");
		Short st = confPrivilegeService.getPrivilege(new Long(confId),
				(short) 1);
		if (st == 1)
			setJsonString("{success:true}");
		else
			setJsonString("{failure:true,msg:'对不起，您没有权限查看该会议内容，请原谅！'}");
		return SUCCESS;
	}

	//判断修改权限
	public String allowUpdater() {
		String confId = getRequest().getParameter("confId");
		Short st = confPrivilegeService.getPrivilege(new Long(confId),
				(short) 2);
		if (st == 2)
			setJsonString("{success:true}");
		else
			setJsonString("{failure:true,msg:'对不起，您没有权限编辑该会议内容，请原谅！'}");
		return SUCCESS;
	}
}
