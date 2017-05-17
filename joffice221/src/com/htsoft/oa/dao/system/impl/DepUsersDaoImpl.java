package com.htsoft.oa.dao.system.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */

import java.util.ArrayList;
import java.util.List;

import com.htsoft.core.Constants;
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.system.DepUsersDao;
import com.htsoft.oa.model.system.DepUsers;

/**
 * @description DepUsers
 * @class DepUsersDaoImpl
 * @author 宏天软件
 * @updater YHZ
 * @company www.jee-soft.cn
 * @createTime 2011-1-13AM
 * 
 */
@SuppressWarnings("unchecked")
public class DepUsersDaoImpl extends BaseDaoImpl<DepUsers> implements
		DepUsersDao {

	public DepUsersDaoImpl() {
		super(DepUsers.class);
	}

	/**
	 * 根据部门PATH属性查找
	 */
	@Override
	public List<DepUsers> findByDepartment(String path, PagingBean pb) {
		List list = new ArrayList();
		String hql = new String();
		if ("0.".equals(path)) {
			hql = "select DISTINCT vo3 from Department vo1,DepUsers vo3,AppUser vo2 where 1=1"
					+ " and vo3.appUser=vo2"
					+ " and vo3.department=vo1"
					+ " and vo2.delFlag = ? order by  vo3.sn,vo1.depId";
			list.add(Constants.FLAG_UNDELETED);
		} else {
			hql = "select DISTINCT vo3 from Department vo1,AppUser vo2,DepUsers vo3 where 1=1"
					+ " and vo3.appUser=vo2"
					+ " and vo3.department=vo1"
					+ " and vo1.path like ? and vo2.delFlag = ? order by  vo3.sn,vo1.depId";
			list.add(path + "%");
			list.add(Constants.FLAG_UNDELETED);
		}
		return findByHql(hql, list.toArray(), pb);
	}

	@Override
	public List<DepUsers> search(String path, DepUsers depUsers, PagingBean pb) {
		List<Object> list = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer();
		if ("0.".equals(path)) {
			hql
					.append("select DISTINCT vo3 from Department vo1,DepUsers vo3,AppUser vo2 where 1=1"
							+ " and vo3.appUser=vo2 and vo3.department=vo1 and vo2.delFlag = ? ");
			list.add(Constants.FLAG_UNDELETED);
		} else {
			hql
					.append("select DISTINCT vo3 from Department vo1,AppUser vo2,DepUsers vo3 where 1=1"
							+ " and vo3.appUser=vo2 and vo3.department=vo1 and vo1.path like ? and vo2.delFlag = ? ");
			list.add(path + "%");
			list.add(Constants.FLAG_UNDELETED);
		}
		if (depUsers != null && depUsers.getAppUser() != null) {
			// 用户账号
			String username = depUsers.getAppUser().getUsername();
			if (username != null && !username.equals("")) {
				hql.append("and vo2.username like ? ");
				list.add(username + "%");
			}
			// 用户名称
			String fullname = depUsers.getAppUser().getFullname();
			if (fullname != null && !fullname.equals("")) {
				hql.append("and vo2.fullname like ? ");
				list.add(fullname + "%");
			}
			// 主部门
			Short isMain = depUsers.getIsMain();
			if (isMain != null && !isMain.equals("")) {
				hql.append("and vo3.isMain >= ? ");
				list.add(isMain);
			}
		}
		hql.append("order by  vo3.sn,vo1.depId");
		logger.debug("自定义DepUserDaoImpl:" + hql.toString());
		return findByHql(hql.toString(), list.toArray(), pb);
	}

	/**
	 * 根据userId查询该用户的所有部门信息
	 */
	@Override
	public List<DepUsers> findByUserIdDep(Long userId) {
		
		String hql = "select d from DepUsers d where d.appUser.userId = ? order by d.sn asc";
		
		return findByHql(hql,new Object[]{userId});
	}

	/**
	 * 根据userId查询该用户是否存在主部门,存在:true
	 */
	@Override
	public Boolean existsDep(Long depUserId, Long userId) {
		String hql = "select d from DepUsers d where d.appUser.userId = ? and d.isMain = 1 and d.depUserId not in(?) ";
		Object[] paramList = { userId, depUserId };
		List<DepUsers> list = findByHql(hql, paramList);
		return list != null && list.size() > 0 ? true : false;
	}

	/**
	 * 只执行添加操作，同时判断是否添加相同数据
	 */
	@Override
	public String add(DepUsers depUsers) {
		String msg = "{success:true,msg:'操作数据成功！'}";
		String hql = "select d from DepUsers d where d.appUser.userId = ? and d.department.depId = ? ";
		Object[] paramList = { depUsers.getAppUser().getUserId(),
				depUsers.getDepartment().getDepId() };
		List<DepUsers> list = findByHql(hql, paramList);
		if (list != null && list.size() > 0) // 数据重复
			msg = "{failure:true,msg:'对不起，该用户["
					+ depUsers.getAppUser().getUsername() + "]已经加入该部门["
					+ depUsers.getDepartment().getDepName() + "]！'}";
		else
			save(depUsers);
		return msg;
	}
	
	
}