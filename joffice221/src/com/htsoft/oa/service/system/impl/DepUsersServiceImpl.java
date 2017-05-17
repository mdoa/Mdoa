package com.htsoft.oa.service.system.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.DepUsersDao;
import com.htsoft.oa.model.system.DepUsers;
import com.htsoft.oa.service.system.DepUsersService;

/**
 * @description 部门员工管理
 * @class DeUsersSerivceImpl
 * @author 宏天软件
 * @updater YHZ
 * @company www.jee-soft.cn
 * @createtime 2011-1-15PM
 * 
 */
public class DepUsersServiceImpl extends BaseServiceImpl<DepUsers> implements
		DepUsersService {
	private DepUsersDao dao;

	public DepUsersServiceImpl(DepUsersDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<DepUsers> findByDepartment(String path, PagingBean pb) {
		return dao.findByDepartment(path, pb);
	}

	@Override
	public List<DepUsers> search(String path, DepUsers depUsers, PagingBean pb) {
		return dao.search(path, depUsers, pb);
	}

	/**
	 * 根据用户id查询对应用户的所有部门信息
	 */
	@Override
	public List<DepUsers> findByUserIdDep(Long userId) {
		return dao.findByUserIdDep(userId);
	}

	/**
	 * 根据用户id查询该用户是否存在主部门
	 */
	@Override
	public Boolean existsDep(Long depUserId, Long userId) {
		return dao.existsDep(depUserId, userId);
	}

	/**
	 * 单纯添加数据操作，同时判断是否添加相同数据，返回：string
	 */
	@Override
	public String add(DepUsers depUsers) {
		return dao.add(depUsers);
	}
}