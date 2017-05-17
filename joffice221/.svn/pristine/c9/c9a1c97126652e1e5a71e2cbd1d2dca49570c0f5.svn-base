package com.htsoft.oa.dao.communicate;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.communicate.OutMail;
import java.util.*;

/**
 * 
 * @author
 * 
 */
public interface OutMailDao extends BaseDao<OutMail> {
	public List<OutMail> findByFolderId(Long folderId);

	public Long CountByFolderId(Long folderId);

	/**
	 * 通过用户ID获得邮箱UID的Map<String,String>
	 * 
	 * @param userId
	 *            用户ID
	 * @return Map<String,String>  Map<UID,"Y">
	 */
	public Map<String, String> getUidByUserId(Long userId);
}