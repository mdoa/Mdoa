package com.htsoft.oa.action.hrm;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.hrm.HireIssue;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.hrm.HireIssueService;

/**
 * 招聘信息
 * 
 * @author
 * 
 */
public class HireIssueAction extends BaseAction {
	@Resource
	private HireIssueService hireIssueService;
	private HireIssue hireIssue;

	private Long hireId;

	public Long getHireId() {
		return hireId;
	}

	public void setHireId(Long hireId) {
		this.hireId = hireId;
	}

	public HireIssue getHireIssue() {
		return hireIssue;
	}

	public void setHireIssue(HireIssue hireIssue) {
		this.hireIssue = hireIssue;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<HireIssue> list = hireIssueService.getAll(filter);
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
				hireIssueService.remove(new Long(id));
			}
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		HireIssue hireIssue = hireIssueService.get(hireId);
		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		jsonString = mapper.toDataJson(hireIssue);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		AppUser user = ContextUtil.getCurrentUser();
		if (hireIssue.getHireId() == null) {// 根据主键来判断是否修改
			hireIssue.setRegFullname(user.getFullname());
			hireIssue.setRegDate(new Date());
		} else {
			hireIssue.setModifyFullname(user.getFullname());
			hireIssue.setModifyDate(new Date());
		}
		hireIssue.setStatus(HireIssue.NOTYETPASS_CHECK);
		hireIssueService.save(hireIssue);
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 获取显示对象
	 * 
	 */
	public String load() {
		String strHireId = getRequest().getParameter("hireId");
		if (StringUtils.isNotEmpty(strHireId)) {
			hireIssue = hireIssueService.get(new Long(strHireId));
		}
		return "load";
	}

	/**
	 * 审核招聘信息
	 * 
	 */
	public String check() {
		String status = getRequest().getParameter("status");
		String strHireId = getRequest().getParameter("hireId");
		String checkOpinion = getRequest().getParameter("checkOpinion");
		if (StringUtils.isNotEmpty(strHireId)) {
			AppUser appUser = ContextUtil.getCurrentUser();
			hireIssue = hireIssueService.get(new Long(strHireId));
			hireIssue.setCheckFullname(appUser.getFullname());
			hireIssue.setCheckDate(new Date());
			hireIssue.setCheckOpinion(checkOpinion);
			if (StringUtils.isNotEmpty(status)) {
				hireIssue.setStatus(Short.valueOf(status));
				hireIssueService.save(hireIssue);
				jsonString = JSON_SUCCESS;
			} else {
				jsonString = JSON_ERROR;
			}
		} else {
			jsonString = JSON_ERROR;
		}
		return SUCCESS;
	}
}
