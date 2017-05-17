package com.htsoft.oa.dao.flow.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.ProNodeSetDao;
import com.htsoft.oa.model.flow.ProNodeSet;

@SuppressWarnings("unchecked")
public class ProNodeSetDaoImpl extends BaseDaoImpl<ProNodeSet> implements
		ProNodeSetDao {

	public ProNodeSetDaoImpl() {
		super(ProNodeSet.class);
	}

	@Override
	public List<ProNodeSet> findProNodeSetByDefIdDeployId(Long defId,
			String deployId) {
		StringBuffer hql = new StringBuffer("from ProNodeSet pns where pns.proDefinition.defId=")
				.append(defId).append(" and pns.deployId=").append(deployId) ;
		return findByHql(hql.toString());
	}

	@Override
	public ProNodeSet getByDeployIdNodeName(String deployId, String nodeName) {
		String hql="from ProNodeSet pns where pns.deployId=? and pns.nodeName=?";
		return (ProNodeSet)findUnique(hql, new Object[]{deployId,nodeName});
	}
}