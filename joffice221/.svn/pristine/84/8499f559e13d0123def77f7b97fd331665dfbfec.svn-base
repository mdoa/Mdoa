package com.htsoft.oa.dao.document.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.document.DocFolderDao;
import com.htsoft.oa.model.document.DocFolder;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.model.system.AppUser;

public class DocFolderDaoImpl extends BaseDaoImpl<DocFolder> implements
		DocFolderDao {

	public DocFolderDaoImpl() {
		super(DocFolder.class);
	}

	/**
	 * 取得某用户对应的所有文件夹
	 * 
	 * @param userId
	 * @param parentId
	 * @return
	 */
	public List<DocFolder> getUserFolderByParentId(Long userId, Long parentId) {

		String hql = "from DocFolder df where df.isShared=0 and df.appUser.userId=? and parentId=?";
		return findByHql(hql, new Object[] { userId, parentId });
	}

	@Override
	public List<DocFolder> getByUserId(Long userId) {
		String hql = "from DocFolder df where df.isShared=0 and df.appUser.userId=?";
		return findByHql(hql, new Object[] { userId });
	}

	/**
	 * 取得某path下的所有Folder
	 * 
	 * @param path
	 * @return
	 */
	public List<DocFolder> getFolderLikePath(String path) {
		String hql = "from DocFolder df where df.path like ?";
		return findByHql(hql, new Object[] { path + '%' });
	}

	@Override
	public List<DocFolder> getPublicFolderByParentId(Long parentId) {
		String hql = "from DocFolder df where df.isShared=1 and df.parentId=? ";
		return findByHql(hql, new Object[] { parentId });
	}

	@Override
	public List<DocFolder> getPublicFolder() {
		String hql = "from DocFolder df where df.isShared=1 ";
		return findByHql(hql);
	}

	@Override
	public List<DocFolder> findByParentId(Long parentId) {
		String hql = "from DocFolder df where df.parentId=?";
		return findByHql(hql, new Object[] { parentId });
	}

	@Override
	public List<DocFolder> findByUserAndName(Long userId, String foleName) {
		String hql = "from DocFolder df where df.isShared=0 and df.appUser.userId=? and df.folderName like ?";
		return findByHql(hql, new Object[] { userId, "%" + foleName + "%" });
	}

	@Override
	public List<DocFolder> getOnlineFolderByParentId(Long parentId) {
		String hql = "from DocFolder df where df.isShared=2 and df.parentId=? ";
		return findByHql(hql, new Object[] { parentId });
	}

	public List<DocFolder> getByRight(AppUser appUser, DocFolder docFolder) {
		// TODO Auto-generated method stub
		return null;
	}
}