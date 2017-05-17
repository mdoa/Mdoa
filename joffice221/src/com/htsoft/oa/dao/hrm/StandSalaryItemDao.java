package com.htsoft.oa.dao.hrm;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.hrm.StandSalaryItem;

/**
 * 
 * @author 
 *
 */
public interface StandSalaryItemDao extends BaseDao<StandSalaryItem>{

	public List<StandSalaryItem> getAllByStandardId(Long standardId);
	
}