package com.htsoft.oa.action.hrm;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.hrm.EmpProfile;
import com.htsoft.oa.service.hrm.EmpProfileService;

/**
 * 档案管理
 * 
 * @author
 * 
 */
public class EmpProfileAction extends BaseAction {

	@Resource
	private EmpProfileService empProfileService;
	
	private EmpProfile empProfile;

	private Long profileId;
    
	public Long getProfileId() {
		return profileId;
	}

	public void setProfileId(Long profileId) {
		this.profileId = profileId;
	}

	public EmpProfile getEmpProfile() {
		return empProfile;
	}

	public void setEmpProfile(EmpProfile empProfile) {
		this.empProfile = empProfile;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<EmpProfile> list = empProfileService.getAll(filter);
		jsonString = mapper.toPageJson(list, filter.getPagingBean().getTotalItems());
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
				EmpProfile deletePro = empProfileService.get(new Long(id));
				deletePro.setDelFlag(EmpProfile.DELETE_FLAG_HAD);
				empProfileService.save(deletePro);
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
		EmpProfile empProfile = empProfileService.get(profileId);
		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		jsonString = mapper.toDataJson(empProfile);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		// 验证
		boolean pass = false;
		StringBuffer buff = new StringBuffer("{");
		if (empProfile.getProfileId() == null) {
			if (empProfileService.checkProfileNo (empProfile.getProfileNo())) {
				pass = true;
			} else {
				buff.append("msg:'档案编号已存在,请重新输入.',");
			}
		} else {
			pass = true;
		}
		if (pass) {
			empProfile.setCreator(ContextUtil.getCurrentUser().getFullname());
			empProfile.setCreatetime(new Date());
			empProfile.setDelFlag(EmpProfile.DELETE_FLAG_NOT);
			empProfile.setApprovalStatus(EmpProfile.CHECK_FLAG_NONE);
			empProfileService.save(empProfile);
			buff.append("success:true}");
		} else {
			buff.append("failure:true}");
		}
		setJsonString(buff.toString());
		return SUCCESS;
	}

	/**
	 * 档案审核动作类
	 * 
	 * @return
	 */
	public String check() {
		EmpProfile checkProfile = empProfileService.get(profileId);
		checkProfile.setCheckName(ContextUtil.getCurrentUser().getFullname());
		checkProfile.setChecktime(new Date());
		checkProfile.setApprovalStatus(empProfile.getApprovalStatus());
		checkProfile.setOpprovalOpinion(empProfile.getOpprovalOpinion());
		empProfileService.save(checkProfile);
		return SUCCESS;
	}

	/**
	 * 恢复删除掉的记录
	 * 
	 * @return
	 */
	public String recovery() {
		String[] ids = getRequest().getParameterValues("ids");
		if (ids != null) {
			for (String id : ids) {
				EmpProfile deletePro = empProfileService.get(new Long(id));
				deletePro.setDelFlag(EmpProfile.DELETE_FLAG_NOT);
				empProfileService.save(deletePro);
			}
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 删除图片
	 */
	public String delphoto() {
		if (profileId != null) {
			empProfile = empProfileService.get(profileId);
			empProfile.setPhoto("");
			empProfileService.save(empProfile);
			jsonString = JSON_SUCCESS;
		}
		return SUCCESS;
	}
}
