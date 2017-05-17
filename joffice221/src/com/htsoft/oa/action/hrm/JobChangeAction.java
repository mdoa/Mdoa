package com.htsoft.oa.action.hrm;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.hrm.EmpProfile;
import com.htsoft.oa.model.hrm.JobChange;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.hrm.EmpProfileService;
import com.htsoft.oa.service.hrm.JobChangeService;

/**
 * 登记职位调动
 * 
 * @author
 * 
 */
public class JobChangeAction extends BaseAction {
	@Resource
	private JobChangeService jobChangeService;
	private JobChange jobChange;
	@Resource
	private EmpProfileService empProfileService;
	private Long changeId;

	public Long getChangeId() {
		return changeId;
	}

	public void setChangeId(Long changeId) {
		this.changeId = changeId;
	}

	public JobChange getJobChange() {
		return jobChange;
	}

	public void setJobChange(JobChange jobChange) {
		this.jobChange = jobChange;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<JobChange> list = jobChangeService.getAll(filter);
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
				jobChangeService.remove(new Long(id));
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
		JobChange jobChange = jobChangeService.get(changeId);
		jsonString = mapper.toDataJson(jobChange);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		jobChange.setRegName(ContextUtil.getCurrentUser().getFullname());
		jobChange.setRegTime(new Date());
		jobChangeService.save(jobChange);
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 读取更改的信息
	 * 
	 * @return
	 */
	public String load() {
		String strId = getRequest().getParameter("changeId");
		if (StringUtils.isNotEmpty(strId)) {
			jobChange = jobChangeService.get(new Long(strId));
		}
		return "load";
	}

	/**
	 * 审核职位更改的信息
	 * 
	 * @return
	 */
	public String check() {
		AppUser appUser = ContextUtil.getCurrentUser();
		Short status = jobChange.getStatus();
		String changeOpinion = jobChange.getCheckOpinion();
		Long changeId = jobChange.getChangeId();
		if (changeId != null) {
			jobChange = jobChangeService.get(changeId);
			jobChange.setStatus(status);
			jobChange.setCheckOpinion(changeOpinion);
			jobChange.setCheckName(appUser.getFullname());
			jobChange.setCheckTime(new Date());
			jobChangeService.save(jobChange);
			if (status == 1) {
				Long profileId = jobChange.getProfileId();
				if (profileId != null) {
					EmpProfile empProfile = empProfileService.get(profileId);
					empProfile.setJobId(jobChange.getNewJobId());
					empProfile.setPosition(jobChange.getNewJobName());
					empProfile.setDepId(jobChange.getNewDepId());
					empProfile.setDepName(jobChange.getNewDepName());
					empProfile.setStandardId(jobChange.getNewStandardId());
					empProfile.setStandardMiNo(jobChange.getNewStandardNo());
					empProfile.setStandardName(jobChange.getNewStandardName());
					empProfile.setStandardMoney(jobChange.getNewTotalMoney());
					empProfileService.merge(empProfile);
				}
			}
			jsonString = JSON_SUCCESS;
		} else {
			jsonString = JSON_ERROR;
		}
		return SUCCESS;
	}

}
