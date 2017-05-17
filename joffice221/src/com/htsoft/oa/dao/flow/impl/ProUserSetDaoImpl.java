package com.htsoft.oa.dao.flow.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.ProUserSetDao;
import com.htsoft.oa.model.flow.ProUserSet;

@SuppressWarnings("unchecked")
public class ProUserSetDaoImpl extends BaseDaoImpl<ProUserSet> implements
		ProUserSetDao {

	public ProUserSetDaoImpl() {
		super(ProUserSet.class);
	}

	@Override
	public List<ProUserSet> findByDefIdDeployId(Long defId, String deployId) {
		StringBuffer hql = new StringBuffer(
				"from ProUserSet pus where pus.proDefinition.defId=")
				.append(defId).append(" and pus.deployId=").append(deployId);
		return findByHql(hql.toString());
	}

	@Override
	public List<ProUserSet> findByDefIdDeployIdNodeName(Long defId,
			String deployId, String nodeName) {
		StringBuffer hql = new StringBuffer(
				"from ProUserSet pus where pus.proDefinition.defId=")
				.append(defId).append(" and pus.deployId=").append(deployId)
				.append(" and pus.nodeName ='").append(nodeName).append("' order by sn ");
		return findByHql(hql.toString());
	}

	@Override
	public List<ProUserSet> findByDeployIdNodeName(String deployId,
			String nodeName) {
		StringBuffer hql = new StringBuffer(
				"from ProUserSet pus where pus.deployId=").append(deployId)
				.append(" and pus.nodeName ='").append(nodeName).append("' order by sn ");
		return findByHql(hql.toString());
	}
	
	

}