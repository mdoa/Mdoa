package com.htsoft.oa.dao.archive.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.Date;
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.archive.ArchHastenDao;
import com.htsoft.oa.model.archive.ArchHasten;

public class ArchHastenDaoImpl extends BaseDaoImpl<ArchHasten> implements ArchHastenDao{

	public ArchHastenDaoImpl() {
		super(ArchHasten.class);
	}

	@Override
	public Date getLeastRecordByUser(Long archivesId) {
		String hql="from ArchHasten vo where vo.archives.archivesId=? order by vo.createtime desc";
		List<ArchHasten> list=findByHql(hql,new Object[]{archivesId});
		if(list.size()>0){
			return list.get(0).getCreatetime();
		}else{
			return null;
		}
	}

}