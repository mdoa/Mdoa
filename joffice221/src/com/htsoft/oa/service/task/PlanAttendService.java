package com.htsoft.oa.service.task;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.task.PlanAttend;

public interface PlanAttendService extends BaseService<PlanAttend>{
	public boolean deletePlanAttend(Long planId,Short isDep,Short isPrimary);
}


