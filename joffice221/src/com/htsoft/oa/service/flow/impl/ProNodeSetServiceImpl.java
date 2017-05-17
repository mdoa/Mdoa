package com.htsoft.oa.service.flow.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.oa.dao.flow.ProNodeSetDao;
import com.htsoft.oa.dao.flow.TaskSignDao;
import com.htsoft.oa.model.flow.ProNodeSet;
import com.htsoft.oa.model.flow.TaskSign;
import com.htsoft.oa.service.flow.ProNodeSetService;

public class ProNodeSetServiceImpl extends BaseServiceImpl<ProNodeSet>
		implements ProNodeSetService {
	private ProNodeSetDao dao;

	@Resource
	private TaskSignDao taskSignDao;

	public ProNodeSetServiceImpl(ProNodeSetDao dao) {
		super(dao);
		this.dao = dao;
	}

	@Override
	public List<ProNodeSet> findProNodeSetByDefIdDeployId(Long defId,
			String deployId) {
		return dao.findProNodeSetByDefIdDeployId(defId, deployId);
	}

	@Override
	public ProNodeSet getByDeployIdNodeName(String deployId, String nodeName) {
		return dao.getByDeployIdNodeName(deployId, nodeName);
	}

	@Override
	public ProNodeSet infoSave(ProNodeSet proNodeSet, TaskSign taskSign) {

		proNodeSet = dao.save(proNodeSet);
		if (proNodeSet.getNodeType().shortValue() == (short) 2) {
			if (taskSign.getSignId() == null) {
				taskSignDao.save(taskSign);
			} else {
				TaskSign orgTaskSign = taskSignDao.get(taskSign
						.getSignId());
				try {
					BeanUtil.copyNotNullProperties(orgTaskSign, taskSign);
					taskSignDao.save(orgTaskSign);
				} catch (Exception ex) {
					logger.error(ex.getMessage());
				}
			}
		}

		return proNodeSet;
	}

}