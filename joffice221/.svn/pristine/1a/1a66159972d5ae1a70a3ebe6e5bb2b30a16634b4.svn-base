package com.htsoft.oa.dao.flow;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.flow.RunData;

/**
 * 
 * @author 
 *
 */
public interface RunDataDao extends BaseDao<RunData>{

	/**
	 * 取得某个运行实例中某个字段的详细信息
	 * @param runId
	 * @param fieldName
	 * @return
	 */
	public RunData getByRunIdFieldName(Long runId,String fieldName);
	
	/**
	 * 取得某个流程对应的参数数据列表
	 * @param runId
	 * @return
	 */
	public List<RunData> getByRunId(Long runId);
	
	/**
	 * 根据sql语句直接查询库表
	 */
	public void insertSql(String inSql);
}