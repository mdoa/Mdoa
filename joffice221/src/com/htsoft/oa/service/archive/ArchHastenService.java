package com.htsoft.oa.service.archive;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.Date;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.archive.ArchHasten;

public interface ArchHastenService extends BaseService<ArchHasten>{
	/**
	 * 获取最后一次发送催办信息的时间
	 * @param archivesId
	 * @param userId
	 * @return
	 */
	public Date getLeastRecordByUser(Long archivesId);
}


