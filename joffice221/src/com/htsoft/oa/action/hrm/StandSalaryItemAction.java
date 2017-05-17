package com.htsoft.oa.action.hrm;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.hrm.StandSalaryItem;
import com.htsoft.oa.service.hrm.StandSalaryItemService;

/**
 * 薪酬标准项目管理
 * 
 * @author
 * 
 */
public class StandSalaryItemAction extends BaseAction {
	@Resource
	private StandSalaryItemService standSalaryItemService;
	private StandSalaryItem standSalaryItem;

	private Long itemId;

	private Long standardId;

	public Long getStandardId() {
		return standardId;
	}

	public void setStandardId(Long standardId) {
		this.standardId = standardId;
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long itemId) {
		this.itemId = itemId;
	}

	public StandSalaryItem getStandSalaryItem() {
		return standSalaryItem;
	}

	public void setStandSalaryItem(StandSalaryItem standSalaryItem) {
		this.standSalaryItem = standSalaryItem;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		// QueryFilter filter = new QueryFilter(getRequest());
		if (standardId != null) {
			List<StandSalaryItem> list = standSalaryItemService
					.getAllByStandardId(standardId);
			jsonString = mapper.toPageJson(list, list.size());
		}
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
				standSalaryItemService.remove(new Long(id));
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
		StandSalaryItem standSalaryItem = standSalaryItemService.get(itemId);
		jsonString = mapper.toDataJson(standSalaryItem);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		standSalaryItemService.save(standSalaryItem);
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}
}
