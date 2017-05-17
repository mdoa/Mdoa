package com.htsoft.oa.dao.communicate.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.communicate.OutMailUserSetingDao;
import com.htsoft.oa.model.communicate.OutMailUserSeting;

/**
 * @description 外部邮箱设置管理
 * @class OutMailUserSetingDaoImpl
 * @extend BaseDaoImpl
 */
@SuppressWarnings("unchecked")
public class OutMailUserSetingDaoImpl extends BaseDaoImpl<OutMailUserSeting>
		implements OutMailUserSetingDao {

	public OutMailUserSetingDaoImpl() {
		super(OutMailUserSeting.class);
	}

	/*
	 * 根据当前登陆人，取得外部邮箱设置
	 */
	@Override
	public List<OutMailUserSeting> getByLoginId(Long loginid) {
		String hql = "select a from OutMailUserSeting a where a.appUser.userId ="
				+ loginid;
		List<OutMailUserSeting> loginList = getHibernateTemplate().find(hql);
		return (loginList != null && loginList.size() > 0) ? loginList
				: null;
	}

	@Override
	public List<Object[]> findByUserAll(String userName,PagingBean pb) {
		@SuppressWarnings("rawtypes")
		List params=new ArrayList();
		String hql = "select au,vo from OutMailUserSeting au right join au.appUser vo where vo.delFlag = 0";
		if(StringUtils.isNotEmpty(userName)){
			hql +="and vo.fullname like ?";
			params.add("%"+userName+"%");
		}
		return (List<Object[]>) find(hql,params.toArray(),pb);
	}
	
	@Override
	public OutMailUserSeting getDefault(Long currentUserId) {
		String hql = "select a from OutMailUserSeting a where a.isDefault = 1 and a.appUser.userId ="
				+ currentUserId;
		List<OutMailUserSeting> list = getHibernateTemplate().find(hql);
		return (list != null && list.size() > 0) ? (OutMailUserSeting) list
				.get(0)
				: null;
	}
}