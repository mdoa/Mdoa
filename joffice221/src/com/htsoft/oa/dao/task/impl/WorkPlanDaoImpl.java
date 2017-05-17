package com.htsoft.oa.dao.task.impl;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.core.web.paging.PagingBean;
import com.htsoft.oa.dao.task.WorkPlanDao;
import com.htsoft.oa.model.system.AppRole;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Department;
import com.htsoft.oa.model.task.WorkPlan;

public class WorkPlanDaoImpl extends BaseDaoImpl<WorkPlan> implements
		WorkPlanDao {

	public WorkPlanDaoImpl() {
		super(WorkPlan.class);
	}

	@Override
	public List<WorkPlan> findByDepartment(WorkPlan workPlan, AppUser user,
			PagingBean pb, String sort, String dir) {
		StringBuffer sb = new StringBuffer();
		sb.append("select wp from WorkPlan wp where wp.status=1 and wp.isPersonal=0");
		ArrayList<Object> list = new ArrayList<Object>();
		if (!user.getRights().contains(AppRole.SUPER_RIGHTS)) {
			//sb.append(" and wp.planId in (select pa.workPlan.planId from PlanAttend pa where (userid =?");
			
			sb.append("and wp.planId in (select distinct pa.workPlan.planId from PlanAttend pa where ((pa.appUser.userId=? and pa.isDep=0)");
			
			Department dep = user.getDepartment();
			list.add(user.getUserId());
			if (dep != null) {
				String path = dep.getPath();
				if (StringUtils.isNotEmpty(path)) {
					StringBuffer buff = new StringBuffer(path.replace(".", ","));
					buff.deleteCharAt(buff.length() - 1);
					sb.append(" or (pa.department.depId in (" + buff.toString()
							+ ") and pa.isDep=1)");
				}
			}
			sb.append("))");
		}
		if (workPlan != null) {
			if (StringUtils.isNotEmpty(workPlan.getPlanName())) {
				sb.append(" and wp.planName like ?");
				list.add("%" + workPlan.getPlanName() + "%");
			}
			if (StringUtils.isNotEmpty(workPlan.getPrincipal())) {
				sb.append(" and wp.principal like ?");
				list.add("%" + workPlan.getPrincipal() + "%");
			}
			if (workPlan.getGlobalType() != null
					&& workPlan.getGlobalType().getProTypeId() != null) {
				sb.append(" and wp.globalType.proTypeId = ?");
				list.add(workPlan.getGlobalType().getProTypeId());
			}
		}
		if (StringUtils.isNotEmpty(sort)) {
			sb.append(" order by wp." + sort + " " + dir);
		}
		return findByHql(sb.toString(), list.toArray(), pb);
	}

	@Override
	public List<WorkPlan> sendWorkPlanTime() {
		String hql = "from WorkPlan wp where wp.endTime <=?";
		return findByHql(hql,new Object[]{new Date()});
	}
}