package com.htsoft.oa.action.htmobile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.oa.model.task.CalendarPlan;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.web.action.BaseAction;

public class MyCalendarPlanAction extends BaseAction{
	@Resource
	private com.htsoft.oa.service.task.CalendarPlanService calendarplanService;
	
	/**
	 * 取得当前登录用户的常务
	 */
	public String list() {
		QueryFilter queryFilter = new QueryFilter(getRequest());
		List<CalendarPlan> calendarPlans = calendarplanService.getCalendarPlanByUserId(ContextUtil.getCurrentUserId(), queryFilter.getPagingBean());
		for(int i = 0; i < calendarPlans.size(); i++) {
			//根据时间的紧急程度初始化显示颜色
			short urgent = calendarPlans.get(i).getUrgent();
			short status = calendarPlans.get(i).getStatus();
			if(urgent==0){
				calendarPlans.get(i).setCss("blue");
			}else if(urgent==1) {
				calendarPlans.get(i).setCss("orange");
			}else {
				calendarPlans.get(i).setCss("red");
			}
			//设置已完成任务的显示颜色
			if(status==1) {
				calendarPlans.get(i).setCss("gray");
			}
			
			if(calendarPlans.get(i).getStartTime()==null) {
				calendarPlans.get(i).setStartTime(new Date());
			}
			if(calendarPlans.get(i).getEndTime()==null) {
				calendarPlans.get(i).setEndTime(new Date());
			}
		}
		jsonString = mapper.toPageJson(calendarPlans, queryFilter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}
	
	/**
	 * 设置日程任务为完成状态
	 */
	public String update() {
		Long planId = RequestUtil.getLong(getRequest(), "planId");
		CalendarPlan calendar = calendarplanService.get(planId);
		calendar.setStatus((short)1);
		calendarplanService.save(calendar);
		jsonString = "{\"success\":true}";
		return SUCCESS;
	}
	
	/**
	 * 获取当天未完成的日程任务个数
	 */
	public String getCount() {
		String id = getRequest().getParameter("id");
		QueryFilter queryFilter = new QueryFilter(getRequest());
		List<CalendarPlan> calendarPlans = calendarplanService.getCalendarPlanByUserId(ContextUtil.getCurrentUserId(), queryFilter.getPagingBean());
		int counts = 0;
		SimpleDateFormat sf  = new SimpleDateFormat("yyyy-MM-dd");
		String current = sf.format(new Date());
		for(CalendarPlan calendarPlan:calendarPlans) {
			if(calendarPlan.getStatus()==1) continue;
			if(calendarPlan.getStartTime()==null) {
				calendarPlan.setStartTime(new Date());
			}
			if(calendarPlan.getEndTime()==null) {
				calendarPlan.setEndTime(new Date());
			}
			if(current.equals(sf.format(calendarPlan.getStartTime())) || current.equals(sf.format(calendarPlan.getEndTime()))){
				counts++;
			}
		}
		jsonString = "{\"success\":true,\"totalCounts\":" + counts + ",\"id\":\""+id+"\"}";
		return SUCCESS;
	}
}
