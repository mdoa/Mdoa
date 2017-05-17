package com.htsoft.oa.dao.communicate;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */

import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.OutMailUserSeting;

/**
 * @description 外部邮箱管理
 * @class OutMailUserSetingDao
 * @extend BaseDao
 * 
 */
public interface OutMailUserSetingDao extends BaseDao<OutMailUserSeting> {

	/**
	 * 根据当前登陆人，取得外部邮箱设置
	 */
	public List<OutMailUserSeting> getByLoginId(Long loginId);
	
	
	/**
	 *根据用户名查询邮箱设置 
	 */
	public List<Object[]> findByUserAll(String userName,PagingBean pb);


	OutMailUserSeting getDefault(Long currentUserId);
}