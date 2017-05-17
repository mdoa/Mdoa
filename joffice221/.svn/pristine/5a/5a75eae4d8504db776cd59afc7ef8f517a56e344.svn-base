package com.htsoft.oa.service.archive.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.Date;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchHastenDao;
import com.htsoft.oa.model.archive.ArchHasten;
import com.htsoft.oa.service.archive.ArchHastenService;

public class ArchHastenServiceImpl extends BaseServiceImpl<ArchHasten> implements ArchHastenService{
	private ArchHastenDao dao;
	
	public ArchHastenServiceImpl(ArchHastenDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public Date getLeastRecordByUser(Long archivesId) {
		return dao.getLeastRecordByUser(archivesId);
	}

}