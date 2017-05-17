package com.htsoft.oa.dao.flow.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.FormRuleDao;
import com.htsoft.oa.model.flow.FormRule;

@SuppressWarnings("unchecked")
public class FormRuleDaoImpl extends BaseDaoImpl<FormRule> implements FormRuleDao{

	public FormRuleDaoImpl() {
		super(FormRule.class);
	}

}