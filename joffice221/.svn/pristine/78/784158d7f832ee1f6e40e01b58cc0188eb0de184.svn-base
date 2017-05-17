package com.htsoft.oa.dao.task;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.Date;
import java.util.List;

import com.htsoft.core.dao.BaseDao;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.task.CalendarPlan;

/**
 * 
 * @author 
 *
 */
public interface CalendarPlanDao extends BaseDao<CalendarPlan>{
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
	 * 取得当前登录用户的常务
	 * @param userId
	 * @param pb
	 * @return
	 */
	public List<CalendarPlan> getCalendarPlanByUserId(Long userId,PagingBean pb);
	/**
	 * 取得当前登录用户的日程列表
	 * @param userId
	 * @param pb
	 * @return
	 */
	public List showCalendarPlanByUserId(Long userId,PagingBean pb);
}