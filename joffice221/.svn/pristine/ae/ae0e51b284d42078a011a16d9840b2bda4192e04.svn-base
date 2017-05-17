package com.htsoft.oa.service.flow.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.flow.FormFieldDao;
import com.htsoft.oa.model.flow.FormField;
import com.htsoft.oa.service.flow.FormFieldService;

public class FormFieldServiceImpl extends BaseServiceImpl<FormField> implements FormFieldService{
	@SuppressWarnings("unused")
	private FormFieldDao dao;
	
	public FormFieldServiceImpl(FormFieldDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	/**
	 * 取某个表的标题字段
	 * @param tableId
	 * @param isFlowTitle
	 * @return
	 */
	public FormField find(Long tableId,Short isFlowTitle){
		return dao.find(tableId, isFlowTitle);
	}

	/**
	 * 取某个表的全部字段
	 * @param tableId
	 * @return
	 */
	public List<FormField> getFieldFromTableId(Long tableId) {
		return dao.getFieldFromTableId(tableId);
	}

}