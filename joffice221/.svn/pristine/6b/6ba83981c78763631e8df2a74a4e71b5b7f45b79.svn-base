package com.htsoft.oa.dao.communicate.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.communicate.OutMailDao;
import com.htsoft.oa.model.communicate.OutMail;

public class OutMailDaoImpl extends BaseDaoImpl<OutMail> implements OutMailDao {

	public OutMailDaoImpl() {
		super(OutMail.class);
	}

	public List<OutMail> findByFolderId(Long folderId) {
		String hql = "from OutMail where folderId = ?";
		return findByHql(hql, new Object[] { folderId });
	}

	@Override
	public Long CountByFolderId(Long folderId) {
		String hql = "select count(*) from OutMail where folderId =" + folderId;
		return (Long) getHibernateTemplate().find(hql).get(0);
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map<String, String> getUidByUserId(Long userId) {
		String hql = "select om.uid from OutMail om where om.userId =" + userId;
		List<String> uidList = getHibernateTemplate().find(hql);
		Map<String, String> uidMap = new HashMap<String, String>();
		for (String uid : uidList) {
			uidMap.put(uid, "Y");
		}
		return uidMap;
	}

}