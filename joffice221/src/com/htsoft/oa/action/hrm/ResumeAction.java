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
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.hrm.Resume;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.FileAttach;
import com.htsoft.oa.service.hrm.ResumeService;
import com.htsoft.oa.service.system.FileAttachService;

/**
 * 简历信息管理
 * 
 * @author
 * 
 */
public class ResumeAction extends BaseAction {
	@Resource
	private ResumeService resumeService;
	private Resume resume;
	@Resource
	private FileAttachService fileAttachService;

	private Long resumeId;

	public Long getResumeId() {
		return resumeId;
	}

	public void setResumeId(Long resumeId) {
		this.resumeId = resumeId;
	}

	public Resume getResume() {
		return resume;
	}

	public void setResume(Resume resume) {
		this.resume = resume;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<Resume> list = resumeService.getAll(filter);
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
				resumeService.remove(new Long(id));
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
		Resume resume = resumeService.get(resumeId);

		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		jsonString = mapper.toDataJson(resume);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String fileIds = getRequest().getParameter("fileIds");
		if (resume.getResumeId() == null) {
			AppUser appUser = ContextUtil.getCurrentUser();
			resume.setRegistor(appUser.getFullname());
			resume.setRegTime(new Date());
		}
		if (StringUtils.isNotEmpty(fileIds)) {
			resume.getResumeFiles().clear();
			String[] ids = fileIds.split(",");
			for (int i = 0; i < ids.length; i++) {
				FileAttach fileAttach = fileAttachService.get(new Long(ids[i]));
				resume.getResumeFiles().add(fileAttach);
			}
		}
		resumeService.save(resume);
		setJsonString("{success:true}");
		return SUCCESS;
	}

	/**
	 * 删除个人相片
	 */
	public String delphoto() {
		String strResumeId = getRequest().getParameter("resumeId");
		if (StringUtils.isNotEmpty(strResumeId)) {
			resume = resumeService.get(new Long(strResumeId));
			resume.setPhoto("");
			resumeService.save(resume);
			setJsonString("{success:true}");
		}
		return SUCCESS;
	}
}
