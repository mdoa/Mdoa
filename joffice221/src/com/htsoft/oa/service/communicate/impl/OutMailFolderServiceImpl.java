package com.htsoft.oa.service.communicate.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.communicate.OutMailFolderDao;
import com.htsoft.oa.model.communicate.OutMailFolder;
import com.htsoft.oa.service.communicate.OutMailFolderService;

public class OutMailFolderServiceImpl extends BaseServiceImpl<OutMailFolder>
		implements OutMailFolderService {
	private OutMailFolderDao dao;

	public OutMailFolderServiceImpl(OutMailFolderDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<OutMailFolder> getAllUserFolderByParentId(Long userId,
			Long parentId) {
		return dao.getAllUserFolderByParentId(userId, parentId);
	}

	@Override
	public List<OutMailFolder> getBySetIdAndParentId(Long setId, Long parentId) {
		return dao.getBySetIdAndParentId(setId, parentId);
	}

	@Override
	public List<OutMailFolder> getFolderLikePath(String path) {
		return dao.getFolderLikePath(path);
	}

	@Override
	public OutMailFolder getByFolderTypeAndSetId(short folderType, Long setId) {
		return dao.getByFolderTypeAndSetId(folderType, setId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.htsoft.oa.service.communicate.OutMailFolderService#findListByUserId
	 * (java.lang.Long)
	 */
	@Override
	public List<OutMailFolder> findListByUserId(Long userId) {
		return dao.findListByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.htsoft.oa.service.communicate.OutMailFolderService#findListByUserIdAndSetId(java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<OutMailFolder> findListByUserIdAndSetId(Long userId,
			Long setId) {
		// TODO Auto-generated method stub
		return dao.findListByUserIdAndSetId(userId, setId);
	}
	
	
}