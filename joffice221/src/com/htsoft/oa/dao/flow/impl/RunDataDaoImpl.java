package com.htsoft.oa.dao.flow.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/

import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.flow.RunDataDao;
import com.htsoft.oa.model.flow.RunData;

public class RunDataDaoImpl extends BaseDaoImpl<RunData> implements RunDataDao{

	public RunDataDaoImpl() {
		super(RunData.class);
	}
	
	public RunData getByRunIdFieldName(Long runId,String fieldName){
		String hql="from RunData rd where rd.processRun.runId=? and rd.fieldName=?";
		return (RunData)findUnique(hql,new Object[]{runId,fieldName});
	}
	/*
	 * (non-Javadoc)
	 * @see com.htsoft.oa.dao.flow.RunDataDao#getByRunId(java.lang.Long)
	 */
	public List<RunData> getByRunId(Long runId){
	    String hql="from RunData rd where rd.processRun.runId=?";
	    return findByHql(hql, new Object[]{runId});
	}

	@Override
	public void insertSql(String inSql) {
        this.jdbcTemplate.execute(inSql);
 
	}

}