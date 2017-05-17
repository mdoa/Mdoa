package com.htsoft.oa.action.arch;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;
import javax.annotation.Resource;

import java.io.File;
import java.lang.reflect.Type;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.BeanUtil;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;

import com.htsoft.oa.model.arch.RollFileList;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.arch.RollFileListService;
import com.htsoft.oa.service.system.FileAttachService;

import flexjson.transformer.DateTransformer;
import flexjson.JSONSerializer;

/**
 * 
 * @author
 * 
 */
public class RollFileListAction extends BaseAction {
	
	@Resource
	private RollFileListService rollFileListService;
	private RollFileList rollFileList;
	@Resource
	private FileAttachService fileAttachService;

	private Long listId;

	public Long getListId() {
		return listId;
	}

	public void setListId(Long listId) {
		this.listId = listId;
	}

	public RollFileList getRollFileList() {
		return rollFileList;
	}

	public void setRollFileList(RollFileList rollFileList) {
		this.rollFileList = rollFileList;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<RollFileList> list = rollFileListService.getAll(filter);

		Type type = new TypeToken<List<RollFileList>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		// Gson gson=new Gson();
		// buff.append(gson.toJson(list, type));
		JSONSerializer serializer = new JSONSerializer();
		serializer
				.exclude(new String[] { "rollFile", "fileAttach.createTime" })
				.transform(new DateTransformer("yyyy-MM-dd"),
						new String[] { "fileAttach.createTime", "createTime" });
		buff.append(serializer.serialize(list));

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

		String[] listIds = getRequest().getParameterValues("listIds");
		if (listIds != null && listIds.length > 0) {
			for (String id : listIds) {
				if (id != null && !id.equals("")) {
					rollFileList = rollFileListService.get(new Long(id));
					FileAttach fileAttach = rollFileList.getFileAttach();
					
					rollFileListService.remove(rollFileList);
					fileAttachService.removeByPath(fileAttach.getFilePath());
				}
			}
		}
		String[] fileIds = getRequest().getParameterValues("fileIds");
		if (fileIds != null && fileIds.length > 0) {
			for (String id : fileIds) {
				if (id != null && !id.equals("")) {
					FileAttach fileAttach = fileAttachService.get(new Long(id));
					
					fileAttachService.removeByPath(fileAttach.getFilePath());
				}
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
		RollFileList rollFileList = rollFileListService.get(listId);

		// Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		// sb.append(gson.toJson(rollFileList));
		JSONSerializer serializer = new JSONSerializer();
		serializer.exclude(new String[] { "rollFile" }).transform(
				new DateTransformer("yyyy-MM-dd"),
				new String[] { "fileAttach.createtime" });
		sb.append(serializer.serialize(rollFileList));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (rollFileList.getListId() == null) {
			rollFileListService.save(rollFileList);
		} else {
			RollFileList orgRollFileList = rollFileListService.get(rollFileList
					.getListId());
			try {
				BeanUtil.copyNotNullProperties(orgRollFileList, rollFileList);
				rollFileListService.save(orgRollFileList);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;

	}


}
