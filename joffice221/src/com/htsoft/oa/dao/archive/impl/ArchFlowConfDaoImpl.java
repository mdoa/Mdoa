package com.htsoft.oa.dao.archive.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.archive.ArchFlowConfDao;
import com.htsoft.oa.model.archive.ArchFlowConf;

public class ArchFlowConfDaoImpl extends BaseDaoImpl<ArchFlowConf> implements ArchFlowConfDao{

	public ArchFlowConfDaoImpl() {
		super(ArchFlowConf.class);
	}

	@Override
	public ArchFlowConf getByFlowType(Short archType) {
		String hql="from ArchFlowConf vo where vo.archType=?";
		Object[] objs={archType};
		List<ArchFlowConf> list=findByHql(hql, objs);
		if(list.size()==1){
			return list.get(0);
		}else{
		    return null;
		}
	}

}