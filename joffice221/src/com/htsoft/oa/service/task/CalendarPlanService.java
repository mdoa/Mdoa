package com.htsoft.oa.service.task;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.Date;
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.task.CalendarPlan;

public interface CalendarPlanService extends BaseService<CalendarPlan>{
	/**
	 * 今日常务
	 * @param userId
	 * @param pb
	 * @return
	 */
	public List<CalendarPlan> getTodayPlans(Long userId,PagingBean pb);
	
	/**
	 * 取得某用户某时间内的所有任务
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public List<CalendarPlan> getByPeriod(Long userId,Date startTime,Date endTime);
	/**
	 * 取得当前登录用户日程管理列表
	 * @param userId
	 * @param pb
	 * @return
	 */
	public List showCalendarPlanByUserId(Long userId, PagingBean pb);
	/**
	 * 取得当前登录用户常务
	 * @param userId
	 * @param pb
	 * @return
	 */
	public List<CalendarPlan> getCalendarPlanByUserId(Long userId, PagingBean pb);
}


