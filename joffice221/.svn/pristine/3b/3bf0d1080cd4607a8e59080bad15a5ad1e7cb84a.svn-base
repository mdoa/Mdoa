package com.htsoft.oa.dao.task;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.task.WorkPlan;

/**
 * 
 * @author 
 *
 */
public interface WorkPlanDao extends BaseDao<WorkPlan>{
	
	/**
	 * 查找部门分配的计划
	 */
	public List<WorkPlan> findByDepartment(WorkPlan workPlan,AppUser user,PagingBean pb,String sort,String dir);
	
	/**
	 *计划到期提醒
	 */
	public List<WorkPlan> sendWorkPlanTime();
}