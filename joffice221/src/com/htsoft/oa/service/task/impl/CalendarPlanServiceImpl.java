package com.htsoft.oa.service.task.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.Date;
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.task.CalendarPlanDao;
import com.htsoft.oa.model.task.CalendarPlan;
import com.htsoft.oa.service.task.CalendarPlanService;

public class CalendarPlanServiceImpl extends BaseServiceImpl<CalendarPlan> implements CalendarPlanService{
	private CalendarPlanDao dao;
	
	public CalendarPlanServiceImpl(CalendarPlanDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	/**
	 * 今日常务
	 * @param userId
	 * @param pb
	 * @return
	 */
	public List<CalendarPlan> getTodayPlans(Long userId,PagingBean pb){
		return dao.getTodayPlans(userId, pb);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.htsoft.oa.service.task.CalendarPlanService#getByPeriod(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public List<CalendarPlan> getByPeriod(Long userId,Date startTime,Date endTime){
		return dao.getByPeriod(userId, startTime, endTime);
	}

	@Override
	public List showCalendarPlanByUserId(Long userId, PagingBean pb) {
		// TODO Auto-generated method stub
		return dao.showCalendarPlanByUserId(userId, pb);
	}
	
	public List<CalendarPlan> getCalendarPlanByUserId(Long userId, PagingBean pb) {
		// TODO Auto-generated method stub
		return dao.getCalendarPlanByUserId(userId, pb);
	}

}