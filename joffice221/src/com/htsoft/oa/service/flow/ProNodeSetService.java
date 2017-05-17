package com.htsoft.oa.service.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.ProNodeSet;
import com.htsoft.oa.model.flow.TaskSign;

public interface ProNodeSetService extends BaseService<ProNodeSet>{
	/**
	 * 通过流程定义id和部署id，查找节点的设置
	 * @param defId
	 * @param deployId
	 * @return
	 */
	public List<ProNodeSet> findProNodeSetByDefIdDeployId(Long defId,
			String deployId);
	/**
	 *  通过流程定义id和节点名称，查找节点的设置
	 * @param deployId 程定义id
	 * @param nodeName 节点名称
	 * @return
	 */
	public ProNodeSet getByDeployIdNodeName(String deployId, String nodeName);
	
	/**
	 * 保存节点及会签相关信息
	 * @param proNodeSet
	 * @param taskSign
	 * @return
	 */
	public ProNodeSet infoSave(ProNodeSet proNodeSet, TaskSign taskSign);
	
}


