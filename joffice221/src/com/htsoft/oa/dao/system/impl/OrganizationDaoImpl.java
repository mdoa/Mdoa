package com.htsoft.oa.dao.system.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.OrganizationDao;
import com.htsoft.oa.model.system.Organization;

@SuppressWarnings("unchecked")
public class OrganizationDaoImpl extends BaseDaoImpl<Organization> implements
		OrganizationDao {

	public OrganizationDaoImpl() {
		super(Organization.class);
	}

	/**
	 * 取得某个节点下的所有子节点
	 * 
	 * @param parentId
	 * @param demId
	 * @return
	 */
	public List<Organization> getByParent(Long parentId, Long demId) {
		ArrayList params = new ArrayList();
		String hql = "from Organization p where p.orgSupId=? ";
		params.add(parentId);
		if (demId != 0 && demId != null) {
			hql += " and p.demId=? ";
			params.add(demId);
		}

		hql += " order by sn desc, createtime asc";

		return findByHql(hql, params.toArray());
	}

	/**
	 * 取得维度下所有组织
	 * 
	 * @param demId
	 * @return
	 */
	@Override
	public List<Organization> getByDemId(Long demId) {
		List<Object> params = new ArrayList<Object>();
		String hql = "from Organization p where 1=1 ";

		if (demId != 0 && demId != null) {
			hql += " and p.demId=? ";
			params.add(demId);
		}
		hql += " order by sn desc, createtime asc";

		return findByHql(hql, params.toArray());
	}

	/**
	 * 按路径查找所有节点
	 * 
	 * @param path
	 * @return
	 */
	public List<Organization> getByPath(String path) {
		String hql = "from Organization p where p.path like ? order by sn desc, createtime asc";
		return findByHql(hql, new Object[] { path + "%" });
	}
	/**
	 * 取得某个维度下的最大深度数
	 * @param parentId
	 * @param demId
	 * @return
	 */
	public Long getMaxDepth(Long demId){
		ArrayList params=new ArrayList();
		String hql="from Organization p where p.demId=? order by p.depth desc ";
		params.add(demId);		
		 List<Organization> list=findByHql(hql, params.toArray());
		 if(list.size()>0)
			 return list.get(0).getDepth();
		return 0L;
	}
	
	/**
	 * 按路径及深度查找所有节点
	 * 
	 * @param path
	 * @return
	 */
	public List<Organization> getDirectByPath(String path,Long depth) {
		String hql = "from Organization p where p.path like ? and p.depth=? order by sn desc, createtime asc";
		return findByHql(hql, new Object[] { path + "%" ,depth});
	}

	/**
	 * 通过userId取得该用户所属的所有部门
	 * @param userId
	 * @return
	 */
	public List<Organization> getByUserId(Long userId) {
		// TODO Auto-generated method stub
		final String sql = "select  *  from organization o  where o.org_id in ( select uo.org_id from  user_org uo where uo.userid = "
				+ userId + ")";

		return  (List<Organization>) getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException { 
						SQLQuery query = session.createSQLQuery(sql).addEntity(Organization.class);
						List<Organization> orgs = query.list();
						return orgs;
					}
				});
	}
}