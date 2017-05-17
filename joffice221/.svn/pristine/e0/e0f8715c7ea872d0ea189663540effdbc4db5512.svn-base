package com.htsoft.oa.service.flow.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.flow.FormTableDao;
import com.htsoft.oa.model.flow.FormField;
import com.htsoft.oa.model.flow.FormTable;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.flow.FormTableService;

public class FormTableServiceImpl extends BaseServiceImpl<FormTable> implements
		FormTableService {
	private FormTableDao dao;

	public FormTableServiceImpl(FormTableDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<FormTable> getListFromPro(String typeId, String tableName,
			AppUser curUser, PagingBean pb) {

		return this.dao.getListFromPro(typeId, tableName, curUser, pb);
	}

	/**
	 * 返回所有表定义及其表的字段
	 * 
	 * @return
	 */
	public List<FormTable> getAllAndFields() {
		return dao.getAllAndFields();
	}

	@Override
	public List<FormTable> findByTableKey(String tableKey) {
		return dao.findByTableKey(tableKey);
	}

	/**
	 * 验证表名，字段合法性
	 * 
	 * @return
	 */
	public String validate(FormTable formTable) {

		Pattern pattern = Pattern
				.compile("^(?!_)(?!.*?_$)[^0-9][0-9A-Za-z_]+$");
		String tableName = formTable.getEntityName();
		Matcher matcher = pattern.matcher(tableName);
		if (!matcher.matches()) {
			return tableName;
		}

		Iterator<FormField> fieldIterator = formTable.getFormFields()
				.iterator();
		while (fieldIterator.hasNext()) {
			FormField formField = fieldIterator.next();
			String fieldName = formField.getFieldName();
			matcher = pattern.matcher(fieldName);
			if (!matcher.matches()) {
				return tableName + " 的 " + fieldName;
			}
		}

		return "";
	}

	/**
	 * 通过表单定义ID获得表
	 * 
	 * @param formDefId
	 * @return
	 */
	@Override
	public List<FormTable> findByFormDefId(Long formDefId) {
		return dao.findByFormDefId(formDefId);
	}

}