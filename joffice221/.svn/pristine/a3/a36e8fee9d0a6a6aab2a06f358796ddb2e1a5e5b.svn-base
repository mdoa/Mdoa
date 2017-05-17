package com.htsoft.oa.dao.system.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.ReportParamDao;
import com.htsoft.oa.model.system.ReportParam;

public class ReportParamDaoImpl extends BaseDaoImpl<ReportParam> implements ReportParamDao{

	public ReportParamDaoImpl() {
		super(ReportParam.class);
	}

	@Override
	public List<ReportParam> findByRepTemp(Long reportId) {
		String hql="from ReportParam vo where vo.reportTemplate.reportId=? order by vo.sn asc";
		Object []objs={reportId};
		return findByHql(hql, objs);
	}

}