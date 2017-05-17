package com.htsoft.oa.dao.system;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.system.SerialNumber;

/**
 * 
 * @author
 * 
 */
public interface SerialNumberDao extends BaseDao<SerialNumber> {

	/**
	 * 检查别名是否重复
	 * @param id
	 * @param alias
	 * @return
	 */
	public Boolean checkAlias(Long id, String alias);

	/**
	 * 根据别名查找流水号
	 * @param alias
	 * @return
	 */
	public SerialNumber getByAlias(String alias);

}