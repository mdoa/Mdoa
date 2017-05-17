package com.htsoft.oa.service.communicate;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.communicate.OutMail;
import com.htsoft.oa.model.communicate.OutMailUserSeting;
import com.htsoft.oa.model.system.AppUser;

public interface OutMailService extends BaseService<OutMail> {
	public List<OutMail> findByFolderId(Long folderId);

	public Long CountByFolderId(Long folderId);

	/**
	 * 收邮件并进行保存
	 * 
	 * @param userSetingList
	 *            当前邮箱设置
	 * @param appUser 
	 */
	public void fetch(List<OutMailUserSeting> userSetingList, AppUser appUser);

}
