package com.htsoft.oa.service.archive.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchFlowConfDao;
import com.htsoft.oa.model.archive.ArchFlowConf;
import com.htsoft.oa.service.archive.ArchFlowConfService;

public class ArchFlowConfServiceImpl extends BaseServiceImpl<ArchFlowConf> implements ArchFlowConfService{
	private ArchFlowConfDao dao;
	
	public ArchFlowConfServiceImpl(ArchFlowConfDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public ArchFlowConf getByFlowType(Short archType) {
		return dao.getByFlowType(archType);
	}

	@Override
	public Long getDefId(Short archType) {
		ArchFlowConf ac=  getByFlowType(archType);
		if(ac !=null){
			return ac.getDefId();
		}else{
			return null;
		}
		
	}

}