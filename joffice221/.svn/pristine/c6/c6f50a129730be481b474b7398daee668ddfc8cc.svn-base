package com.htsoft.oa.action.arch;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;
import javax.annotation.Resource;

import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.web.action.BaseAction;

import com.htsoft.oa.model.arch.BorrowFileList;
import com.htsoft.oa.service.arch.BorrowFileListService;

import flexjson.transformer.DateTransformer;
import flexjson.JSONSerializer;

/**
 * 
 * @author
 * 
 */
public class BorrowFileListAction extends BaseAction {
	@Resource
	private BorrowFileListService borrowFileListService;
	private BorrowFileList borrowFileList;

	private Long listId;

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public BorrowFileList getBorrowFileList() {
		return borrowFileList;
	}

	public void setBorrowFileList(BorrowFileList borrowFileList) {
		this.borrowFileList = borrowFileList;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<BorrowFileList> list = borrowFileListService.getAll(filter);
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd HH:mm:ss");
		jsonString=mapper.toPageJson(list, filter.getPagingBean().getTotalItems());

		return SUCCESS;
	}

	public String listCheck() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<BorrowFileList> list = borrowFileListService.getAll(filter);
		JacksonMapper mapper=new JacksonMapper(true,"yyyy-MM-dd HH:mm:ss");
		jsonString=mapper.toPageJson(list, filter.getPagingBean().getTotalItems());

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
				borrowFileListService.remove(new Long(id));
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
		BorrowFileList borrowFileList = borrowFileListService.get(listId);

		Gson gson = new Gson();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(borrowFileList));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		borrowFileListService.save(borrowFileList);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
