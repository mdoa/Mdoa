package com.htsoft.oa.dao.system.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jfree.util.Log;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.DictionaryDao;
import com.htsoft.oa.model.system.Dictionary;

public class DictionaryDaoImpl extends BaseDaoImpl<Dictionary> implements
		DictionaryDao {

	public DictionaryDaoImpl() {
		super(Dictionary.class);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllItems() {
		final String hql = "select itemName from Dictionary group by itemName";
		return ((List<String>) getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						return query.list();
					}
				}));
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<String> getAllByItemName(final String itemName) {
		final String hql = "select itemValue from Dictionary where itemName=?";
		return (List<String>) getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setString(0, itemName);
						return query.list();
					}
				});
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Dictionary> getByItemName(final String itemName) {
		final String hql = " from Dictionary where itemName=? order by sn";
		return (List<Dictionary>) getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setString(0, itemName);
						return query.list();
					}
				});
	}

	@Override
	public List<Dictionary> getByNodeKey(String nodeKey) {
		String hql = " from Dictionary d where d.globalType.nodeKey = ? order by d.sn asc";
		return findByHql(hql, new Object[] { nodeKey });
	}

	@Override
	public List<Dictionary> getByProTypeId(Long proTypeId) {
		String hql = " from Dictionary d where d.globalType.proTypeId = ? order by d.sn asc";
		return findByHql(hql, new Object[] { proTypeId });
	}

}