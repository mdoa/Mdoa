package com.htsoft.oa.service.communicate.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */

import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.communicate.OutMailUserSetingDao;
import com.htsoft.oa.model.communicate.OutMailUserSeting;
import com.htsoft.oa.service.communicate.OutMailUserSetingService;

/**
 * @description 外部邮箱设置管理
 * @class OutMailUserSetingServiceImpl
 * 
 */
public class OutMailUserSetingServiceImpl extends
		BaseServiceImpl<OutMailUserSeting> implements OutMailUserSetingService {
	private OutMailUserSetingDao dao;

	public OutMailUserSetingServiceImpl(OutMailUserSetingDao dao) {
		super(dao);
		this.dao = dao;
	}

	public List<OutMailUserSeting> getByLoginId(Long loginid) {
		return dao.getByLoginId(loginid);
	}

	@Override
	public List<Object[]> findByUserAll(String userName, PagingBean pb) {
		return dao.findByUserAll(userName, pb);
	}

	@Override
	public OutMailUserSeting getDefault(Long currentUserId) {
		return dao.getDefault(currentUserId);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.htsoft.oa.service.communicate.OutMailUserSetingService#saveDefault
	 * (java.lang.Long, java.lang.Long)
	 */
	@Override
	public OutMailUserSeting saveDefault(Long setId, Long currentUserId) {
		OutMailUserSeting defaultSeting = dao.getDefault(currentUserId);
		OutMailUserSeting outMailUserSeting = dao.get(setId);
		outMailUserSeting.setIsDefault(OutMailUserSeting.IS_DEFAULT);
		dao.save(outMailUserSeting);
		if (defaultSeting != null) {
			defaultSeting.setIsDefault(OutMailUserSeting.NOT_DEFAULT);
			dao.save(defaultSeting);
		}
		return outMailUserSeting;
	}

}