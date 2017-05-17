package com.htsoft.oa.dao.admin.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */

import java.util.HashSet;
import java.util.Set;

import javax.annotation.Resource;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.admin.ConfSummaryDao;
import com.htsoft.oa.dao.admin.ConferenceDao;
import com.htsoft.oa.dao.system.FileAttachDao;
import com.htsoft.oa.model.admin.ConfSummary;
import com.htsoft.oa.model.admin.Conference;
import com.htsoft.oa.model.info.ShortMessage;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.info.ShortMessageService;

/**
 * @description confSummaryDaoImpl
 * @author YHZ
 * @date 2010-10-8 PM
 * 
 */
@SuppressWarnings("unchecked")
public class ConfSummaryDaoImpl extends BaseDaoImpl<ConfSummary> implements
		ConfSummaryDao {
	@Resource
	private ConferenceDao confDao;
	@Resource
	private FileAttachDao fileAttachDao;
	@Resource
	private ShortMessageService shortMessageService;

	public ConfSummaryDaoImpl() {
		super(ConfSummary.class);
	}

	/**
	 * @description 发送
	 */
	public ConfSummary send(ConfSummary cm, String fileIds) {
		// 内部邮件
		Conference conf = confDao.get(cm.getConfId().getConfId());
		String ids = conf.getCompere() + "," + conf.getRecorder() + ","
				+ conf.getAttendUsers();
		String msg = "请查看主题为【" + conf.getConfTopic() + "】的会议纪要信息！";
		shortMessageService.save(AppUser.SYSTEM_USER, ids, msg,
				ShortMessage.MSG_TYPE_SYS);
		return save(cm, fileIds);
	}

	/**
	 * @description 保存
	 */
	public ConfSummary save(ConfSummary cm, String fileIds) {
		if (fileIds != null && !fileIds.isEmpty()) {
			Set<FileAttach> set = new HashSet<FileAttach>();
			for (String s : fileIds.split(",")) {
				set.add(fileAttachDao.get(new Long(s)));
			}
			cm.setAttachFiles(set);
		}
		Conference cf = confDao.get(cm.getConfId().getConfId());
		cm.setConfId(cf);
		return super.save(cm);
	}

}