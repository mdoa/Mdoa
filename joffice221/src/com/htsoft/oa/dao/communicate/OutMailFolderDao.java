package com.htsoft.oa.dao.communicate;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.communicate.OutMailFolder;

/**
 * 
 * @author
 * 
 */
public interface OutMailFolderDao extends BaseDao<OutMailFolder> {
	public List<OutMailFolder> getAllUserFolderByParentId(Long userId,
			Long parentId);

	public List<OutMailFolder> getBySetIdAndParentId(Long setID, Long parentId);

	public List<OutMailFolder> getFolderLikePath(String path);

	public OutMailFolder getByFolderTypeAndSetId(short folderType, Long setId);

	/**
	 * 通过用户获得当前邮箱文件夹
	 * 
	 * @param userId
	 *            用户ID
	 * @return
	 */
	public List<OutMailFolder> findListByUserId(Long userId);

	/**
	 * 通过用户ID和setId获得当前邮箱文件夹
	 * 
	 * @param userId
	 *            用户ID
	 * @param setId
	 *            用户设置邮箱
	 * @return
	 */
	public List<OutMailFolder> findListByUserIdAndSetId(Long userId,
			Long setId);

}