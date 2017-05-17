package com.htsoft.oa.action.archive;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.archive.DocHistory;
import com.htsoft.oa.service.archive.DocHistoryService;

/**
 * 
 * 公文历史版本
 * 
 * @author
 * 
 */
public class DocHistoryAction extends BaseAction {
	@Resource
	private DocHistoryService docHistoryService;
	private DocHistory docHistory;

	private Long historyId;

	public Long getHistoryId() {
		return historyId;
	}

	public void setHistoryId(Long historyId) {
		this.historyId = historyId;
	}

	public DocHistory getDocHistory() {
		return docHistory;
	}

	public void setDocHistory(DocHistory docHistory) {
		this.docHistory = docHistory;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<DocHistory> list = docHistoryService.getAll(filter);

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
				docHistoryService.remove(new Long(id));
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
		DocHistory docHistory = docHistoryService.get(historyId);

		jsonString = mapper.toDataJson(docHistory);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		docHistoryService.save(docHistory);
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}
}
