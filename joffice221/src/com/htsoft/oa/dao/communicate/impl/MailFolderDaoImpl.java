package com.htsoft.oa.dao.communicate.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.communicate.MailFolderDao;
import com.htsoft.oa.model.communicate.MailFolder;

@SuppressWarnings("unchecked")
public class MailFolderDaoImpl extends BaseDaoImpl<MailFolder> implements
		MailFolderDao {

	public MailFolderDaoImpl() {
		super(MailFolder.class);
	}

	/**
	 * 根据用户ID,取得文件夹
	 */
	@Override
	public List<MailFolder> getUserFolder(Long userId) {
		String hql = "from MailFolder mf where  mf.appUser.userId=?";
		return findByHql(hql, new Object[] { userId });
	}

	/**
	 * * 根据用户ID,或用户ID为空取得文件夹 取得MailFolder第一层文件夹,包括收件箱,发件箱,草稿箱,删除箱
	 */
	@Override
	public List<MailFolder> getMailFolderByParentId(Long parentId) {
		String hql = "from MailFolder mf where  parentId=? and userId is null";
		return findByHql(hql, new Object[] { parentId });
	}

	/**
	 * 取得某path下的所有Folder
	 * 
	 * @param path
	 * @return
	 */
	@Override
	public List<MailFolder> getFolderLikePath(String path) {
		String hql = "from MailFolder mf where mf.path like ?";
		return findByHql(hql, new Object[] { path + '%' });
	}

}