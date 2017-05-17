package com.htsoft.oa.service.communicate;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.communicate.OutMailUserSeting;

/**
 * @description 外部邮箱管理
 * @class OutMailUserSetingService
 * 
 */
public interface OutMailUserSetingService extends
		BaseService<OutMailUserSeting> {

	public List<OutMailUserSeting> getByLoginId(Long loginid);

	public List<Object[]> findByUserAll(String userName, PagingBean pb);

	/**
	 * 获取默认邮箱
	 * 
	 * @param currentUserId
	 *            当前用户
	 * @return
	 */
	public OutMailUserSeting getDefault(Long currentUserId);

	/**
	 * 保存默认邮箱
	 * 
	 * @param setId
	 * @param currentUserId
	 * @return
	 */
	public OutMailUserSeting saveDefault(Long setId, Long currentUserId);
}
