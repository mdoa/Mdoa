package com.htsoft.oa.service.archive.impl;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import javax.annotation.Resource;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.archive.ArchTemplateDao;
import com.htsoft.oa.model.archive.ArchTemplate;
import com.htsoft.oa.service.archive.ArchTemplateService;
import com.htsoft.oa.service.system.FileAttachService;

public class ArchTemplateServiceImpl extends BaseServiceImpl<ArchTemplate> implements ArchTemplateService{
	private ArchTemplateDao dao;
	
	@Resource
	FileAttachService fileAttachService;
	
	public ArchTemplateServiceImpl(ArchTemplateDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	/**
	 * 删除该模板时，若存在附件，也需要同时删除附件
	 */
	public void remove(Long id) {
		ArchTemplate template=dao.get(id);
		remove(template);
		fileAttachService.removeByPath(template.getTempPath());
		
	}

}