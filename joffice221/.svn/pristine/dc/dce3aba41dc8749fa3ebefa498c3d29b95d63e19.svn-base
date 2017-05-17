package com.htsoft.oa.service.system.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.system.AppFunctionDao;
import com.htsoft.oa.model.system.AppFunction;
import com.htsoft.oa.service.system.AppFunctionService;

public class AppFunctionServiceImpl extends BaseServiceImpl<AppFunction> implements AppFunctionService{
	private AppFunctionDao dao;
	
	public AppFunctionServiceImpl(AppFunctionDao dao) {
		super(dao);
		this.dao=dao;
	}

	/*
	 * (non-Javadoc)
	 * @see com.htsoft.oa.service.system.AppFunctionService#getByKey(java.lang.String)
	 */
	public AppFunction getByKey(String key) {
		return dao.getByKey(key);
	}
}