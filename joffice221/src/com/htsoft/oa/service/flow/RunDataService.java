package com.htsoft.oa.service.flow;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/


import java.util.Map;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.RunData;

public interface RunDataService extends BaseService<RunData>{
	
	/**
	 * 取得某个运行实例中某个字段的详细信息
	 * @param runId
	 * @param fieldName
	 * @return
	 */
	public RunData getByRunIdFieldName(Long runId,String fieldName);
	
	/**
	 * 保存流程实例对应的变量
	 * @param runId
	 * @param vars
	 */
	public void saveFlowVars(Long runId,Map<String,Object> vars);
	/**
	 * 取得某个流程对应的所有参数Map
	 * @param runId
	 * @return
	 */
	public Map<String,Object> getMapByRunId(Long runId);
	
	
	/**
	 * 根据sql语句直接查询库表
	 */
	public void insertSql(String inSql);
}


