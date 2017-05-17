package com.htsoft.oa.service.flow;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.ProUserAssign;

public interface ProUserAssignService extends BaseService<ProUserAssign>{
	public List<ProUserAssign> getByDeployId(String deployId);
	
	/**
	 * 取得某流程某个任务的授权信息
	 * @param deployId
	 * @param activityName
	 * @return
	 */
	public ProUserAssign getByDeployIdActivityName(String deployId,String activityName);
	/**
	 * 把旧版本的流程的人员配置复制至新版本上去
	 * @param oldDeplyId
	 * @param newDeployId
	 */
	public void copyNewConfig(String oldDeplyId,String newDeployId);
}


