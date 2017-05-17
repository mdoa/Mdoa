package com.htsoft.oa.dao.flow.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.ProDefRightsDao;
import com.htsoft.oa.model.flow.ProDefRights;

@SuppressWarnings("unchecked")
public class ProDefRightsDaoImpl extends BaseDaoImpl<ProDefRights> implements ProDefRightsDao{

	public ProDefRightsDaoImpl() {
		super(ProDefRights.class);
	}

	@Override
	public ProDefRights findByDefId(Long defId) {
		String hql = "from ProDefRights pd where pd.proDefinition.defId = ?";
		List<ProDefRights> list = findByHql(hql,new Object[]{defId});
		if(list.size()>0){
			return list.get(0);
		}else{
			return new ProDefRights();
		}
	}

	@Override
	public ProDefRights findByTypeId(Long proTypeId) {
		String hql = "from ProDefRights pd where pd.globalType.proTypeId = ?";
		List<ProDefRights> list = findByHql(hql,new Object[]{proTypeId});
		if(list.size()>0){
			return list.get(0);
		}else{
			return new ProDefRights();
		}
	}

}