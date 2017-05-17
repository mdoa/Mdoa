package com.htsoft.oa.service.admin.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.oa.dao.admin.ConfPrivilegeDao;
import com.htsoft.oa.model.admin.ConfPrivilege;
import com.htsoft.oa.service.admin.ConfPrivilegeService;

/**
 * @description ConfPrivilegeServiceImpl
 * @author YHZ
 * @date 2010-10-8 PM
 * 
 */
public class ConfPrivilegeServiceImpl extends BaseServiceImpl<ConfPrivilege>
		implements ConfPrivilegeService {
	private ConfPrivilegeDao dao;

	public ConfPrivilegeServiceImpl(ConfPrivilegeDao dao) {
		super(dao);
		this.dao = dao;
	}

	/**
	 * @description 获取该数据的权限
	 * @param confId
	 *            confId
	 * @param s
	 *            1=查看,2=修改,3=建立纪要
	 * @return 0.没有权限,1.查看，2.修改，3.创建
	 */
	@Override
	public Short getPrivilege(Long confId, Short s) {
		return dao.getPrivilege(ContextUtil.getCurrentUserId(), confId, s);
	}

}