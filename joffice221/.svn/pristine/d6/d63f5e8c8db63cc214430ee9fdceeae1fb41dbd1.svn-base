package com.htsoft.oa.dao.communicate.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.communicate.OutMailFolderDao;
import com.htsoft.oa.model.communicate.OutMailFolder;

public class OutMailFolderDaoImpl extends BaseDaoImpl<OutMailFolder> implements
		OutMailFolderDao {

	public OutMailFolderDaoImpl() {
		super(OutMailFolder.class);
	}

	/**
	 * 根据用户ID,或文件夹ParentID,或用户ID为空取得文件夹 取得MailFolder第一层文件夹,包括收件箱,发件箱,草稿箱,删除箱
	 */
	@Override
	public List<OutMailFolder> getAllUserFolderByParentId(Long userId,
			Long parentId) {
		String hql = "from OutMailFolder mf where mf.appUser.userId=? and parentId=? or userId is null";
		return findByHql(hql, new Object[] { userId, parentId });

	}

	/**
	 * 根据用户ID,或文件夹ParentID取得文件夹
	 */
	@Override
	public List<OutMailFolder> getBySetIdAndParentId(Long setId, Long parentId) {
		String hql = "from OutMailFolder mf where mf.outMailUserSeting.setId=? and parentId=? order by folderType";
		return findByHql(hql, new Object[] { setId, parentId });
	}

	/**
	 * 取得某path下的所有Folder
	 * 
	 * @param path
	 * @return
	 */
	@Override
	public List<OutMailFolder> getFolderLikePath(String path) {
		String hql = "from OutMailFolder mf where mf.path like ?";
		return findByHql(hql, new Object[] { path + '%' });
	}

	@Override
	public OutMailFolder getByFolderTypeAndSetId(short folderType, Long setId) {
		String hql = "from OutMailFolder mf where mf.outMailUserSeting.setId=? and folderType=?";
		return findByHql(hql, new Object[] { setId, folderType }).get(0);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.htsoft.oa.dao.communicate.OutMailFolderDao#findListByUserId(java.
	 * lang.Long)
	 */
	@Override
	public List<OutMailFolder> findListByUserId(Long userId) {
		String hql = "from OutMailFolder mf where mf.appUser.userId=? ";
		return findByHql(hql, new Object[] { userId });
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.htsoft.oa.dao.communicate.OutMailFolderDao#findListByUserIdAndSetId
	 * (java.lang.Long, java.lang.Long)
	 */
	@Override
	public List<OutMailFolder> findListByUserIdAndSetId(Long userId, Long setId) {
		String hql = "from OutMailFolder mf where mf.appUser.userId=? and mf.outMailUserSeting.setId=?  order by folderType  ";
		return findByHql(hql, new Object[] { userId, setId });
	}

}