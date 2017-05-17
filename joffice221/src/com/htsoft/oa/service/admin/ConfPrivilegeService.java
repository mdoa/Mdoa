package com.htsoft.oa.service.admin;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.ConfPrivilege;

/**
 * @description ConfPrivilegeService
 * @author YHZ
 * @date 2010-10-8 PM
 * 
 */
public interface ConfPrivilegeService extends BaseService<ConfPrivilege> {

	/**
	 * @description 获取该数据的权限
	 * @param confId
	 *            confId
	 * @param s
	 *            1=查看,2=修改,3=建立纪要
	 * @return 0.没有权限,1.查看，2.修改，3.创建
	 */
	Short getPrivilege(Long confId, Short s);
}
