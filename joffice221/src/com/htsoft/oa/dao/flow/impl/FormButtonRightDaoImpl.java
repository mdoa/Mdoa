package com.htsoft.oa.dao.flow.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.FormButtonRightDao;
import com.htsoft.oa.model.flow.FieldRights;
import com.htsoft.oa.model.flow.FormButtonRight;

@SuppressWarnings("unchecked")
public class FormButtonRightDaoImpl extends BaseDaoImpl<FormButtonRight> implements FormButtonRightDao{

	public FormButtonRightDaoImpl() {
		super(FormButtonRight.class);
	}

	@Override
	public List<FormButtonRight> getByMappingTableTaskName(Long mappingId,Long tableId, String taskName,Short buttonType) {
		String hql="from FormButtonRight vo where vo.tableId=? and vo.formDefMapping.mappingId=? and vo.taskName=? and vo.buttonType=?";
		return findByHql(hql, new Object[]{tableId,mappingId,taskName,buttonType});
	}
	
	@Override
	public List<FormButtonRight> getByMappingTaskName(Long mappingId, String taskName) {
		String hql="from FormButtonRight vo where vo.formDefMapping.mappingId=? and vo.taskName=?";
		return findByHql(hql, new Object[]{mappingId,taskName});
	}
}