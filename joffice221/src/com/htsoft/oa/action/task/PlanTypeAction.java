package com.htsoft.oa.action.task;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.task.PlanType;
import com.htsoft.oa.model.task.WorkPlan;
import com.htsoft.oa.service.task.PlanTypeService;
import com.htsoft.oa.service.task.WorkPlanService;

/**
 * 
 * @author csx
 * 
 */
public class PlanTypeAction extends BaseAction {
	@Resource
	private PlanTypeService planTypeService;
	private PlanType planType;
	@Resource
	private WorkPlanService workPlanService;

	private Long typeId;

	public Long getTypeId() {
		return typeId;
	}

	public void setTypeId(Long typeId) {
		this.typeId = typeId;
	}

	public PlanType getPlanType() {
		return planType;
	}

	public void setPlanType(PlanType planType) {
		this.planType = planType;
	}

	public String combo() {

		StringBuffer sb = new StringBuffer();

		List<PlanType> planTypeList = planTypeService.getAll();
		sb.append("[");
		for (PlanType planType : planTypeList) {
			sb.append("['").append(planType.getTypeId()).append("','")
					.append(planType.getTypeName()).append("'],");
		}
		if (planTypeList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<PlanType> list = planTypeService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());

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
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_planType.typeId_L_EQ", id);
				List<WorkPlan> list = workPlanService.getAll(filter);
				if (list.size() > 0) {
					jsonString = "{success:false,message:'类型下还有计划，请移走该类型的计划任务后，再删除类型！'}";
					return SUCCESS;
				}
				planTypeService.remove(new Long(id));
			}
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		PlanType planType = planTypeService.get(typeId);

		jsonString = mapper.toDataJson(planType);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		planTypeService.save(planType);
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}
}
