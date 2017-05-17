package com.htsoft.oa.dao.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.FormButtonRight;

/**
 * 
 * @author 
 *
 */
public interface FormButtonRightDao extends BaseDao<FormButtonRight>{

	List<FormButtonRight> getByMappingTableTaskName(Long mappingId,
			Long tableId, String taskName, Short buttonType);

	List<FormButtonRight> getByMappingTaskName(Long mappingId, String taskName);
	
}