package com.htsoft.oa.service.admin;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.ConfSummary;

/**
 * @description ConfSummaryService
 * @author YHZ
 * @date 2010-10-8 PM
 * 
 */
public interface ConfSummaryService extends BaseService<ConfSummary> {

	/**
	 * @description 发送
	 * @param cm
	 *            ConfSummary
	 * @param fileIds
	 *            附件ids
	 * @return ConfSummary
	 */
	ConfSummary send(ConfSummary cm, String fileIds);

	/**
	 * @description 保存
	 * @param cm
	 *            ConfSummary
	 * @param fileIds
	 * @return ConfSummary
	 */
	ConfSummary save(ConfSummary cm, String fileIds);
}
