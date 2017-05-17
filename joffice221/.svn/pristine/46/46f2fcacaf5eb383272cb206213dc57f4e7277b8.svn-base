package com.htsoft.oa.action.hrm;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.hrm.SalaryItem;
import com.htsoft.oa.service.hrm.SalaryItemService;

/**
 * 
 * @author
 * 
 */
public class SalaryItemAction extends BaseAction {
	@Resource
	private SalaryItemService salaryItemService;
	private SalaryItem salaryItem;

	private Long salaryItemId;

	public Long getSalaryItemId() {
		return salaryItemId;
	}

	public void setSalaryItemId(Long salaryItemId) {
		this.salaryItemId = salaryItemId;
	}

	public SalaryItem getSalaryItem() {
		return salaryItem;
	}

	public void setSalaryItem(SalaryItem salaryItem) {
		this.salaryItem = salaryItem;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		// PagingBean pb = getInitPagingBean();
		String ids = getRequest().getParameter("exclude");
		if (StringUtils.isNotEmpty(ids)) {
			ids = ids.substring(0, ids.length() - 1);// 删除掉最后一个",";
		}

		QueryFilter filter = new QueryFilter(getRequest());
		// List<SalaryItem> list= salaryItemService.getAllExcludeId(ids,pb);
		List<SalaryItem> list = salaryItemService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 
	 * @return
	 */
	public String search() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<SalaryItem> list = salaryItemService.getAll(filter);
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
				salaryItemService.remove(new Long(id));
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
		SalaryItem salaryItem = salaryItemService.get(salaryItemId);
		jsonString = mapper.toDataJson(salaryItem);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		salaryItemService.save(salaryItem);
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}
}
