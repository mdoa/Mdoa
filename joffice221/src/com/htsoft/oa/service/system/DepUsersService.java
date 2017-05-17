package com.htsoft.oa.service.system;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.DepUsers;

/**
 * @description 部门员工管理
 * @class DepUsersService
 * @author 宏天软件
 * @updater YHZ
 * @company www.jee-soft.cn
 * @createtime 2011-1-15PM
 * 
 */
public interface DepUsersService extends BaseService<DepUsers> {

	public List<DepUsers> findByDepartment(String path, PagingBean pb);

	/**
	 * @description 根据条件查询对应的数据
	 * @param path
	 *            路径
	 * @param depUsers
	 *            DepUsers对象
	 * @param pb
	 *            PagingBean pb
	 * @return List<DepUsers>
	 */
	List<DepUsers> search(String path, DepUsers depUsers, PagingBean pb);

	/**
	 * @description 根据userId查询对应用户的所有部门信息
	 * @param userId
	 *            用户id
	 * @return List<DepUsers>
	 */
	List<DepUsers> findByUserIdDep(Long userId);

	/**
	 * @description 根据userId查询该用户是否存在主部门信息,存在：true
	 * @param depUserId
	 *            depUserId
	 * @param userId
	 *            用户id
	 * @return 存在：true
	 */
	Boolean existsDep(Long depUserId, Long userId);

	/**
	 * @description 单纯添加数据操作，同时判断是否添加相同部门的信息
	 * @param depUsers
	 *            DepUsers
	 * @return String
	 */
	String add(DepUsers depUsers);
}
