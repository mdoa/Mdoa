package com.htsoft.oa.action.task;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.model.task.PlanAttend;
import com.htsoft.oa.model.task.WorkPlan;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.DepartmentService;
import com.htsoft.oa.service.system.FileAttachService;
import com.htsoft.oa.service.task.PlanAttendService;
import com.htsoft.oa.service.task.WorkPlanService;

/**
 * 工作计划
 * 
 * @author csx
 * 
 */
public class WorkPlanAction extends BaseAction {
	@Resource
	private WorkPlanService workPlanService;
	private WorkPlan workPlan;
	@Resource
	private FileAttachService fileAttachService;
	@Resource
	private DepartmentService departmentService;
	@Resource
	private PlanAttendService planAttendService;
	@Resource
	private AppUserService appUserService;

	private Long planId;

	static short ISDEPARTMENT = 1;// 部门
	static short NOTDEPARTMENT = 0;// 不是部门
	static short ISPRIMARY = 1; // 是负责人
	static short NOTPRIMARY = 0;// 不是负责人

	public Long getPlanId() {
		return planId;
	}

	public void setPlanId(Long planId) {
		this.planId = planId;
	}

	public WorkPlan getWorkPlan() {
		return workPlan;
	}

	public void setWorkPlan(WorkPlan workPlan) {
		this.workPlan = workPlan;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		Long userId = ContextUtil.getCurrentUserId();
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
		List<WorkPlan> list = workPlanService.getAll(filter);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 个人计划列表
	 */
	public String personal() {
		QueryFilter filter = new QueryFilter(getRequest());
		Long userId = ContextUtil.getCurrentUserId();
		filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
		filter.addFilter("Q_isPersonal_SN_EQ", "1");
		filter.addFilter("Q_status_SN_EQ", "1");
		List<WorkPlan> list = workPlanService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 部门计划列表
	 * 
	 * @return
	 */
	public String department() {
		PagingBean pb = getInitPagingBean();
		AppUser user = ContextUtil.getCurrentUser();
		// 获取排序的列表信息
		String sort = getRequest().getParameter("sort");
		String dir = getRequest().getParameter("dir");
		List<WorkPlan> list = workPlanService.findByDepartment(workPlan, user,
				pb, sort, dir);

		jsonString = mapper.toPageJson(list, pb.getTotalItems());
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
				workPlanService.remove(new Long(id));
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
		WorkPlan workPlan = workPlanService.get(planId);

		jsonString = mapper.toDataJson(workPlan);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String issueScopeIds = getRequest().getParameter("issueScopeIds");
		String participantsIds = getRequest().getParameter("participantsIds");
		String principalIds = getRequest().getParameter("principalIds");
		String fileIds = getRequest().getParameter("planFileIds");
		short isPersonal = workPlan.getIsPersonal();
		workPlan.getPlanFiles().clear();
		if (StringUtils.isNotEmpty(fileIds)) {
			String[] fIds = fileIds.split(",");
			for (int i = 0; i < fIds.length; i++) {
				FileAttach fileAttach = fileAttachService
						.get(new Long(fIds[i]));
				workPlan.getPlanFiles().add(fileAttach);
			}
		}
		workPlan.setPlanFiles(workPlan.getPlanFiles());
		AppUser appUser = ContextUtil.getCurrentUser();
		workPlan.setAppUser(appUser);
		if (isPersonal == 1) {
			workPlan.setPrincipal(appUser.getFullname());
		}
		workPlanService.save(workPlan);
		if (isPersonal != 1) {
			if (StringUtils.isNotEmpty(issueScopeIds)) {// 发布范围
				boolean b = planAttendService.deletePlanAttend(
						workPlan.getPlanId(), ISDEPARTMENT, NOTPRIMARY);
				if (b) {
					String[] strIssueScopeId = issueScopeIds.split(",");
					for (int i = 0; i < strIssueScopeId.length; i++) {
						if (StringUtils.isNotEmpty(strIssueScopeId[i])) {
							Long depId = new Long(strIssueScopeId[i]);
							PlanAttend pa = new PlanAttend();
							pa.setDepartment(departmentService.get(depId));
							pa.setWorkPlan(workPlan);
							pa.setIsDep(ISDEPARTMENT);
							pa.setIsPrimary(NOTPRIMARY);
							planAttendService.save(pa);
						}
					}
				}
			}
			if (StringUtils.isNotEmpty(participantsIds)) {// 参与人
				boolean b = planAttendService.deletePlanAttend(
						workPlan.getPlanId(), NOTDEPARTMENT, NOTPRIMARY);
				if (b) {
					String[] strParticipantsId = participantsIds.split(",");
					for (int i = 0; i < strParticipantsId.length; i++) {
						if (StringUtils.isNotEmpty(strParticipantsId[i])) {
							Long userId = new Long(strParticipantsId[i]);
							PlanAttend pa = new PlanAttend();
							pa.setAppUser(appUserService.get(userId));
							pa.setIsDep(NOTDEPARTMENT);
							pa.setWorkPlan(workPlan);
							pa.setIsPrimary(NOTPRIMARY);
							planAttendService.save(pa);
						}
					}
				}
			}
			if (StringUtils.isNotEmpty(principalIds)) {// 负责人
				boolean b = planAttendService.deletePlanAttend(
						workPlan.getPlanId(), NOTDEPARTMENT, ISPRIMARY);
				if (b) {
					String[] strPrincipalId = principalIds.split(",");
					for (int i = 0; i < strPrincipalId.length; i++) {
						if (StringUtils.isNotEmpty(strPrincipalId[i])) {
							Long userId = new Long(strPrincipalId[i]);
							PlanAttend pa = new PlanAttend();
							pa.setAppUser(appUserService.get(userId));
							pa.setIsDep(NOTDEPARTMENT);
							pa.setWorkPlan(workPlan);
							pa.setIsPrimary(ISPRIMARY);
							planAttendService.save(pa);
						}
					}
				}
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 显示工作计划
	 */
	public String show() {
		String strPlanId = getRequest().getParameter("planId");
		if (StringUtils.isNotEmpty(strPlanId)) {
			Long planId = new Long(strPlanId);
			workPlan = workPlanService.get(planId);
		}
		return "show";
	}

	/**
	 * 首页显示个人计划列表
	 */

	public String display() {
		QueryFilter filter = new QueryFilter(getRequest());
		Long userId = ContextUtil.getCurrentUserId();
		filter.addFilter("Q_appUser.userId_L_EQ", userId.toString());
		filter.addFilter("Q_isPersonal_SN_EQ", "1");
		filter.addFilter("Q_status_SN_EQ", "1");
		filter.addSorted("planId", "desc");
		List<WorkPlan> list = workPlanService.getAll(filter);
		getRequest().setAttribute("planList", list);
		return "display";
	}

	/**
	 * 首页显示的部门计划列表
	 * 
	 * @return
	 */
	public String displayDep() {
		PagingBean pb = new PagingBean(0, 8);
		AppUser user = ContextUtil.getCurrentUser();
		// 获取排序的列表信息
		String sort = getRequest().getParameter("sort");
		String dir = getRequest().getParameter("dir");
		List<WorkPlan> list = workPlanService.findByDepartment(workPlan, user,
				pb, sort, dir);
		getRequest().setAttribute("planList", list);
		return "displayDep";
	}
}
