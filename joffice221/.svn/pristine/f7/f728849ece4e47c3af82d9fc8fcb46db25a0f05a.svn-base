package com.htsoft.oa.action.system;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DataEncryptUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.OutMailUserSeting;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.service.communicate.OutMailUserSetingService;
import com.htsoft.oa.service.system.AppUserService;

public class OutMailSetAction extends BaseAction {
	@Resource
	private OutMailUserSetingService outMailUserSetingService;
	@Resource
	private AppUserService appUserService;
	private AppUser appUser;
	private OutMailUserSeting outMailUserSeting;

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser appUser) {
		this.appUser = appUser;
	}

	public OutMailUserSeting getOutMailUserSeting() {
		return outMailUserSeting;
	}

	public void setOutMailUserSeting(OutMailUserSeting outMailUserSeting) {
		this.outMailUserSeting = outMailUserSeting;
	}

	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		QueryFilter filter = new QueryFilter(getRequest());
		List<OutMailUserSeting> list = outMailUserSetingService.getAll(filter);
		for (OutMailUserSeting outMailUserSeting : list) {
			outMailUserSeting.setMailPass(DataEncryptUtil
					.desDecrypt(outMailUserSeting.getMailPass()));
		}

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
				outMailUserSetingService.remove(new Long(id));
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
		OutMailUserSeting outMailUserSeting = outMailUserSetingService.get(id);

		if (outMailUserSeting == null) {
			outMailUserSeting = new OutMailUserSeting();
			outMailUserSeting.setUserId(ContextUtil.getCurrentUserId());
			outMailUserSeting.setUserName(ContextUtil.getCurrentUser()
					.getUsername());
		}
		jsonString = mapper.toDataJson(outMailUserSeting);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String data = getRequest().getParameter("data");
		if (StringUtils.isNotEmpty(data)) {
			Gson gson = new Gson();
			OutMailUserSeting[] outSet = gson.fromJson(data,
					OutMailUserSeting[].class);
			for (OutMailUserSeting setting : outSet) {
				if (setting.getSetId() == -1L) {
					setting.setSetId(null);
				}
				if (setting.getReuserId() != null
						&& StringUtils.isNotEmpty(setting.getAccountName())
						&& StringUtils.isNotEmpty(setting.getMailAddress())
						&& StringUtils.isNotEmpty(setting.getPopHost())
						&& StringUtils.isNotEmpty(setting.getUserName())
						&& StringUtils.isNotEmpty(setting.getSmtpPort())
						&& StringUtils.isNotEmpty(setting.getSmtpHost())
						&& StringUtils.isNotEmpty(setting.getPopPort())
						&& StringUtils.isNotEmpty(setting.getMailPass())) {
					AppUser appUser = appUserService.get(setting.getReuserId());
					setting.setAppUser(appUser);
					setting.setMailPass(DataEncryptUtil.desEncrypt(setting
							.getMailPass()));
					outMailUserSetingService.save(setting);
				}
			}
		}
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
