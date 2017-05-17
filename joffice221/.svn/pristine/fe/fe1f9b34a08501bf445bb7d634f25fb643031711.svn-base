package com.htsoft.oa.dao.archive.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.archive.ArchivesDocDao;
import com.htsoft.oa.model.archive.ArchivesDoc;

public class ArchivesDocDaoImpl extends BaseDaoImpl<ArchivesDoc> implements ArchivesDocDao{

	public ArchivesDocDaoImpl() {
		super(ArchivesDoc.class);
	}

	@Override
	public List<ArchivesDoc> findByAid(Long archivesId) {
		String hql="from ArchivesDoc vo where vo.archives.archivesId=?";
		Object [] objs={archivesId};
		return findByHql(hql, objs);
	}

}