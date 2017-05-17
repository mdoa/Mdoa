package com.htsoft.oa.dao.admin.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.admin.ConfPrivilegeDao;
import com.htsoft.oa.model.admin.ConfPrivilege;

/**
 * @description ConfPrivilegeDaoImpl
 * @author YHZ
 * @date 2010-10-8 PM
 * 
 */
@SuppressWarnings("unchecked")
public class ConfPrivilegeDaoImpl extends BaseDaoImpl<ConfPrivilege> implements
		ConfPrivilegeDao {

	public ConfPrivilegeDaoImpl() {
		super(ConfPrivilege.class);
	}

	/**
	 * @description 根据用户Id查询在confId的权限
	 * @param userId
	 * @return 0=没有权限1=查看 2=修改 3=建立纪要
	 */
	public Short getPrivilege(Long userId, Long confId, Short s) {
		Short st = 0;
		String hql = "select p from ConfPrivilege p where p.userId =" + userId
				+ " and p.confId = " + confId + " and p.rights=" + s;
		List<ConfPrivilege> list = findByHql(hql);
		if (list != null && list.size() > 0) {
			ConfPrivilege cp = list.get(0);
			st = cp.getRights();
		}
		return st;
	}

	/**
	 * @description 根据会议编号confId删除会议权限
	 * @param confId
	 *            confId
	 */
	public void delete(final Long confId) {
		final String hql = "delete ConfPrivilege c where c.confId = ?";
		Object count = getHibernateTemplate().execute(new HibernateCallback() {
			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session.createQuery(hql);
				query.setLong(0, confId);
				return query.executeUpdate();
			}
		});
		System.out.println("删除数据条数：" + count);
	}
}