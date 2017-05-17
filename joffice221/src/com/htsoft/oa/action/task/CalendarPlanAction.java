package com.htsoft.oa.action.task;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.time.DateUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.task.CalendarPlan;
import com.htsoft.oa.model.task.PlanInfo;
import com.htsoft.oa.service.task.CalendarPlanService;

/**
 * 
 * 日程管理
 * 
 * @author csx
 * 
 */
public class CalendarPlanAction extends BaseAction {
	@Resource
	private CalendarPlanService calendarPlanService;
	private CalendarPlan calendarPlan;

	// 格式化日期
	private String[] parsePatterns = new String[] { "MM/dd/yyyy" };

	private List<CalendarPlan> list;
	private Long planId;

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public CalendarPlan getCalendarPlan() {
		return calendarPlan;
	}

	public void setCalendarPlan(CalendarPlan calendarPlan) {
		this.calendarPlan = calendarPlan;
	}

	public List<CalendarPlan> getList() {
		return list;
	}

	public void setList(List<CalendarPlan> list) {
		this.list = list;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());

		// 是否查看自己的任务列表,若Q_assignerId_L_EQ不为空，则代表是查看自己分配的任务
		if (getRequest().getParameter("Q_assignerId_L_EQ") == null) {
			filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
					.toString());
		}
		List<CalendarPlan> list = calendarPlanService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 首页显示列表
	 */
	public String display() {

		QueryFilter filter = new QueryFilter(getRequest());

		// 是否查看自己的任务列表,若Q_assignerId_L_EQ不为空，则代表是查看自己分配的任务
		filter.addFilter("Q_userId_L_EQ", ContextUtil.getCurrentUserId()
				.toString());
		filter.addSorted("planId", "desc");
		List<CalendarPlan> list = calendarPlanService.getAll(filter);
		getRequest().setAttribute("calendarList", list);
		return "display";
	}

	/**
	 * 今日常务
	 * 
	 * @return
	 */
	public String today() {
		PagingBean pb = new PagingBean(start, limit);
		List<CalendarPlan> list = calendarPlanService.getTodayPlans(
				ContextUtil.getCurrentUserId(), pb);
		List<PlanInfo> planList = new ArrayList<PlanInfo>();

		for (CalendarPlan plan : list) {
			planList.add(new PlanInfo(plan));
		}

		jsonString = mapper.toPageJson(planList, pb.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 批量删除
	 * 
	 * @return
	 */
	public String multiDel() {

		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				calendarPlanService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		CalendarPlan calendarPlan = calendarPlanService.get(planId);

		jsonString = mapper.toDataJson(calendarPlan);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String magSave() {
		if (calendarPlan.getPlanId() == null) {
			calendarPlan.setStatus(CalendarPlan.STATUS_UNFINISHED);

			AppUser appUser = ContextUtil.getCurrentUser();

			calendarPlan.setAssignerId(appUser.getUserId());

			calendarPlan.setAssignerName(appUser.getFullname());

			calendarPlanService.save(calendarPlan);
		} else {

			CalendarPlan cp = calendarPlanService.get(calendarPlan.getPlanId());
			try {
				BeanUtil.copyNotNullProperties(cp, calendarPlan);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
			calendarPlanService.save(cp);
		}

		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 返回我的任务
	 * 
	 * @return
	 */
	public String oldmy() {

		HttpServletRequest request = getRequest();
		String datafn = request.getParameter("action");

		Date startDate = null;
		Date endDate = null;

		if ("month".equals(datafn)) {

			String monthday = request.getParameter("monthday");
			try {
				Date reqDate = DateUtils.parseDate(monthday, parsePatterns);
				Calendar cal = Calendar.getInstance();
				cal.setTime(reqDate);

				cal.set(Calendar.DAY_OF_MONTH, 1);
				// 开始日期为本月1号 00时00分00秒
				startDate = DateUtil.setStartDay(cal).getTime();

				cal.add(Calendar.MONTH, 1);
				cal.add(Calendar.DAY_OF_MONTH, -1);

				// 结束日期为本月最后一天的23时59分59秒

				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		} else if ("day".equals(datafn)) {
			// $str = $_POST['day']; 12/12/2008

			String day = request.getParameter("day");
			logger.info("day:" + day);
			try {
				Date reqDay = DateUtils.parseDate(day, parsePatterns);

				Calendar cal = Calendar.getInstance();
				cal.setTime(reqDay);

				// 开始日期为本月1号 00时00分00秒
				startDate = DateUtil.setStartDay(cal).getTime();

				cal.add(Calendar.MONTH, 1);
				cal.add(Calendar.DAY_OF_MONTH, -1);

				// 结束日期为本月最后一天的23时59分59秒
				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}

		} else if ("week".equals(datafn)) {
			// weeknumber 50
			// String weeknumber=request.getParameter("weeknumber");

			String startweek = request.getParameter("startweek");
			String endweek = request.getParameter("endweek");
			try {
				Date reqStartWeek = DateUtils.parseDate(startweek,
						parsePatterns);
				Date reqEndWeek = DateUtils.parseDate(endweek, parsePatterns);
				Calendar cal = Calendar.getInstance();

				cal.setTime(reqStartWeek);

				startDate = DateUtil.setStartDay(cal).getTime();
				cal.setTime(reqEndWeek);

				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
			// logger.info("weeknumber:" + weeknumber + " startweek:" +
			// startweek + " endweek:" + endweek);
		} else if ("period".equals(datafn)) {
			String start = request.getParameter("start");
			String end = request.getParameter("end");
			try {
				Date reqStartDate = DateUtils.parseDate(start, parsePatterns);
				Date reqEndDate = DateUtils.parseDate(end, parsePatterns);

				Calendar cal = Calendar.getInstance();

				cal.setTime(reqStartDate);

				startDate = DateUtil.setStartDay(cal).getTime();

				cal.setTime(reqEndDate);

				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				logger.info(ex.getMessage());
			}
		} else {
			jsonString = "{success:false,errors:'there's enough arguments to generate data'}";
		}

		StringBuffer sb = new StringBuffer();

		// SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		// logger.info("startDate:" + sdf.format(startDate) + " endDate:" +
		// sdf.format(endDate));

		// TODO
		List<CalendarPlan> planList = calendarPlanService.getByPeriod(
				ContextUtil.getCurrentUserId(), startDate, endDate);

		sb.append("{success:true,totalCount:").append(planList.size())
				.append(",records:[");

		// SimpleDateFormat sdf=new SimpleDateFormat("MM/dd/yyyy HH:mm:ss a");

		for (CalendarPlan plan : planList) {

			sb.append("{id:'").append(plan.getPlanId()).append("',");

			String subject = plan.getContent();
			if (subject.length() > 12) {
				subject = subject.substring(1, 12) + "...";
			}
			// 获取结束时间,若为空，则加上50年，表示这种没有结束时间，用于防止前台展示日历任务时出现异常
			Date endTime = plan.getEndTime();
			if (endTime == null) {
				Calendar curCal = Calendar.getInstance();
				curCal.add(Calendar.YEAR, 50);
				endTime = curCal.getTime();
			}

			Date startTime = plan.getStartTime();
			if (start == null) {
				Calendar curCal = Calendar.getInstance();
				startTime = curCal.getTime();
			}

			sb.append("subject:'").append(StringUtil.convertQuot(subject))
					.append("',");
			sb.append("description:'")
					.append(StringUtil.convertQuot(plan.getContent()))
					.append("',");
			sb.append("startdate:'").append(DateUtil.formatEnDate(startTime))
					.append("',");
			sb.append("enddate:'").append(DateUtil.formatEnDate(endTime))
					.append("',");
			sb.append("color:'").append(plan.getColor()).append("',");
			sb.append("parent:'0',");
			sb.append("priority:'").append(plan.getUrgent()).append("'},");
		}

		if (planList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]}");

		jsonString = sb.toString();
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save(CalendarPlan cpModel) {

		String msg = "更新任务失败";

		if (cpModel.getPlanId() == 0) {
			cpModel.setPlanId(null);
			cpModel.setStatus(CalendarPlan.STATUS_UNFINISHED);
			AppUser appUser = ContextUtil.getCurrentUser();
			cpModel.setAssignerId(appUser.getUserId());
			cpModel.setAssignerName(appUser.getFullname());

			cpModel.setFullname(appUser.getFullname());
			cpModel.setStatus(new Short("0"));
			cpModel.setUserId(appUser.getUserId());
			cpModel.setShowStyle(new Short("1"));

			calendarPlanService.save(cpModel);
			msg = "提交任务成功";

		} else {

			CalendarPlan cp = calendarPlanService.get(cpModel.getPlanId());
			try {

				cp.setFeedback("");
				cp.setStatus(cpModel.getStatus());

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}

			calendarPlanService.save(cp);
			msg = "完成任务成功";
		}

		return msg;
	}

	/**
	 * 返回我的任务
	 * 
	 * @return
	 */
	public String my(Object param) {

		Map<?, ?> map = (Map<?, ?>) param;
		String mode = (String) map.get("mode");
		String sDate = (String) map.get("startDate");
		String eDate = (String) map.get("endDate");

		Date startDate = null;
		Date endDate = null;

		if ("month".equals(mode)) {

			try {
				Date reqDate = DateUtils.parseDate(sDate, parsePatterns);
				Calendar cal = Calendar.getInstance();
				cal.setTime(reqDate);
				startDate = DateUtil.setStartDay(cal).getTime();
				reqDate = DateUtils.parseDate(eDate, parsePatterns);
				cal.setTime(reqDate);
				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}

		} else if ("day".equals(mode)) {

			try {
				Date reqDay = DateUtils.parseDate(sDate, parsePatterns);

				Calendar cal = Calendar.getInstance();
				cal.setTime(reqDay);

				// 开始日期为本月1号 00时00分00秒
				startDate = DateUtil.setStartDay(cal).getTime();

				cal.add(Calendar.MONTH, 1);
				cal.add(Calendar.DAY_OF_MONTH, -1);

				// 结束日期为本月最后一天的23时59分59秒
				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}

		} else if ("week".equals(mode)) {

			try {
				Date reqStartWeek = DateUtils.parseDate(sDate, parsePatterns);
				Date reqEndWeek = DateUtils.parseDate(eDate, parsePatterns);
				Calendar cal = Calendar.getInstance();

				cal.setTime(reqStartWeek);

				startDate = DateUtil.setStartDay(cal).getTime();
				cal.setTime(reqEndWeek);

				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}

		} else if ("workweek".equals(mode)) {
			try {
				Date reqStartWeek = DateUtils.parseDate(sDate, parsePatterns);
				Date reqEndWeek = DateUtils.parseDate(eDate, parsePatterns);
				Calendar cal = Calendar.getInstance();

				cal.setTime(reqStartWeek);

				startDate = DateUtil.setStartDay(cal).getTime();
				cal.setTime(reqEndWeek);

				endDate = DateUtil.setEndDay(cal).getTime();

			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		} else {
			jsonString = "{success:false,errors:'there's enough arguments to generate data'}";
		}

		List<CalendarPlan> planList = calendarPlanService.getByPeriod(
				ContextUtil.getCurrentUser().getUserId(), startDate, endDate);

		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (CalendarPlan plan : planList) {

			sb.append("{\"id\":\"").append(plan.getPlanId()).append("\",");

			// 获取结束时间,若为空，则加上50年，表示这种没有结束时间，用于防止前台展示日历任务时出现异常
			Date endTime = plan.getEndTime();
			if (endTime == null) {
				Calendar curCal = Calendar.getInstance();
				curCal.add(Calendar.YEAR, 50);
				endTime = curCal.getTime();
			}

			Date startTime = plan.getStartTime();

			if (start == null) {
				Calendar curCal = Calendar.getInstance();
				startTime = curCal.getTime();
			}

			sb.append("\"taskType\":\"").append(plan.getTaskType())
					.append("\",");
			sb.append("\"startTime\":\"")
					.append(DateUtil.formatEnDate(startTime)).append("\",");
			sb.append("\"endTime\":\"").append(DateUtil.formatEnDate(endTime))
					.append("\",");
			sb.append("\"summary\":\"").append(plan.getSummary()).append("\",");
			sb.append("\"description\":\"")
					.append(StringUtil.convertQuot(plan.getContent()))
					.append("\",");
			sb.append("\"status\":\"").append(plan.getStatus()).append("\",");
			sb.append("\"urgent\":\"").append(plan.getUrgent()).append("\"},");
		}

		if (planList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}

		sb.append("]");
		jsonString = sb.toString();

		return jsonString;
	}

}
