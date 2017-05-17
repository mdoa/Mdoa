package com.htsoft.oa.dao.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.ProUserSet;

/**
 * 
 * @author 
 *
 */
public interface ProUserSetDao extends BaseDao<ProUserSet>{
	
	/**
	 * 通过定义ID、部署id获得流程参与的人员
	 * @param defId 定义id
	 * @param deployId 部署id
	 * @return 流程参与的人员
	 */
	public List<ProUserSet> findByDefIdDeployId(Long defId, String deployId);

	/**
	 * 通过定义ID、部署id和节点名称获得流程参与的人员
	 * @param defId 定义id
	 * @param deployId 部署id
	 * @param nodeName 节点名称
	 * @return 流程参与的人员
	 */
	public List<ProUserSet> findByDefIdDeployIdNodeName(Long defId,
			String deployId, String nodeName);
	/**
	 * 通过部署id和节点名称获得流程参与的人员
	 * @param deployId 部署id
	 * @param nodeName 节点名称
	 * @return 流程参与的人员
	 */
	public List<ProUserSet> findByDeployIdNodeName(
			String deployId, String nodeName);
}