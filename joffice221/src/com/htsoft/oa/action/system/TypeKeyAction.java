package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.TypeKey;
import com.htsoft.oa.service.system.TypeKeyService;

/**
 * 
 * @author
 * 
 */
public class TypeKeyAction extends BaseAction {
	@Resource
	private TypeKeyService typeKeyService;
	private TypeKey typeKey;

	private Long typekeyId;

	public Long getTypekeyId() {
		return typekeyId;
	}

	public void setTypekeyId(Long typekeyId) {
		this.typekeyId = typekeyId;
	}

	public TypeKey getTypeKey() {
		return typeKey;
	}

	public void setTypeKey(TypeKey typeKey) {
		this.typeKey = typeKey;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<TypeKey> list = typeKeyService.getAll(filter);
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
				if (new Long(id).longValue() > 12) {
					typeKeyService.remove(new Long(id));
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
		TypeKey typeKey = typeKeyService.get(typekeyId);

		jsonString = mapper.toDataJson(typeKey);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		typeKeyService.save(typeKey);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 分类Key下拉列表
	 * 
	 * @return
	 */
	public String combo() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addSorted("sn", "ASC");
		List<TypeKey> list = typeKeyService.getAll(filter);
		StringBuffer sb = new StringBuffer("[");
		for (TypeKey typeKey : list) {
			if (sb.length() > 1) {
				sb.append(",");
			}
			sb.append("['").append(typeKey.getTypeKey()).append("','")
					.append(typeKey.getTypeName()).append("(")
					.append(typeKey.getTypeKey()).append(")").append("']");
		}
		sb.append("]");
		setJsonString(sb.toString());
		return SUCCESS;
	}
}
