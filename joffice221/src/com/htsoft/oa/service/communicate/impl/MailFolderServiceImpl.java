package com.htsoft.oa.service.communicate.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.communicate.MailFolderDao;
import com.htsoft.oa.model.communicate.MailFolder;
import com.htsoft.oa.service.communicate.MailFolderService;

public class MailFolderServiceImpl extends BaseServiceImpl<MailFolder> implements MailFolderService{
	private MailFolderDao dao;
	
	public MailFolderServiceImpl(MailFolderDao dao) {
		super(dao);
		this.dao=dao;
	}

	
	@Override
	public List<MailFolder> getUserFolder(Long userId) {
		return dao.getUserFolder(userId);
	}
	

	@Override
	public List<MailFolder> getMailFolderByParentId(Long parentId) {
		return dao.getMailFolderByParentId(parentId);
	}


	@Override
	public List<MailFolder> getFolderLikePath(String path) {
		return dao.getFolderLikePath(path);
	}

	

}