package com.htsoft.oa.service.flow.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.flow.ProUserSetDao;
import com.htsoft.oa.model.flow.ProUserSet;
import com.htsoft.oa.service.flow.ProUserSetService;

public class ProUserSetServiceImpl extends BaseServiceImpl<ProUserSet> implements ProUserSetService{
	private ProUserSetDao dao;
	
	public ProUserSetServiceImpl(ProUserSetDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<ProUserSet> findByDefIdDeployId(Long defId, String deployId) {
		return dao.findByDefIdDeployId(defId, deployId);
	}

	@Override
	public List<ProUserSet> findByDefIdDeployIdNodeName(Long defId,
			String deployId, String nodeName) {
		return dao.findByDefIdDeployIdNodeName(defId, deployId, nodeName);
	}

	@Override
	public List<ProUserSet> findByDeployIdNodeName(String deployId,
			String nodeName) {
		return dao.findByDeployIdNodeName(deployId, nodeName);
	}
	
	

}