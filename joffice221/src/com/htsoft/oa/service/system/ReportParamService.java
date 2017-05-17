package com.htsoft.oa.service.system;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.ReportParam;

public interface ReportParamService extends BaseService<ReportParam>{
	/**
	 * 根据ID来查找参数
	 * @param reportId
	 * @return
	 */
	public List<ReportParam> findByRepTemp(Long reportId);
}


