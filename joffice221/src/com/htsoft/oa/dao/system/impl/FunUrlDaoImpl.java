package com.htsoft.oa.dao.system.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.system.FunUrlDao;
import com.htsoft.oa.model.system.FunUrl;

public class FunUrlDaoImpl extends BaseDaoImpl<FunUrl> implements FunUrlDao{

	public FunUrlDaoImpl() {
		super(FunUrl.class);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.htsoft.oa.dao.system.FunUrlDao#getByPathFunId(java.lang.String, java.lang.Long)
	 */
	public FunUrl getByPathFunId(String path,Long funId){
		String hql="from FunUrl fu where fu.urlPath=? and fu.appFunction.functionId=? ";
		return (FunUrl)findUnique(hql, new Object[]{path,funId});
	}

}