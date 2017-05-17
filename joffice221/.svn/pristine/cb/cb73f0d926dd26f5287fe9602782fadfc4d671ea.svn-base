package com.htsoft.oa.service.system.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.ReportParamDao;
import com.htsoft.oa.model.system.ReportParam;
import com.htsoft.oa.service.system.ReportParamService;

public class ReportParamServiceImpl extends BaseServiceImpl<ReportParam> implements ReportParamService{
	private ReportParamDao dao;
	
	public ReportParamServiceImpl(ReportParamDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<ReportParam> findByRepTemp(Long reportId) {
		return dao.findByRepTemp(reportId);
	}

}