package com.htsoft.oa.service.system;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */

import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.RelativeJob;

/**
 * @description 相对岗位管理
 * @author 宏天软件
 * @company www.jee-soft.cn
 * @data 2010-12-13PM
 * 
 */
public interface RelativeJobService extends BaseService<RelativeJob> {

	/**
	 * @description 根据parentId查询子节点信息
	 * @param parentId
	 *            父节点id
	 * @return List<RelativeJob>
	 */
	List<RelativeJob> findByParentId(Long parentId);
	
}
