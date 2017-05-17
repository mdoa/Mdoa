package com.htsoft.oa.action.htmobile;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.communicate.PhoneGroup;
import com.htsoft.oa.service.communicate.PhoneGroupService;

/*
 * author xianggang
 * 公司  hotent
 */
public class AddressGroupAction extends BaseAction {

	@Resource
	private PhoneGroupService phoneGroupService;

	private PhoneGroup phoneGroup;

	public PhoneGroup getPhoneGroup() {
		return phoneGroup;
	}

	public void setPhoneGroup(PhoneGroup phoneGroup) {
		this.phoneGroup = phoneGroup;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		String isPublic = getRequest().getParameter("isPublic");
		List<PhoneGroup> list = new ArrayList<PhoneGroup>();
		if (StringUtils.isNotEmpty(isPublic) && "true".equals(isPublic)) {
			list = phoneGroupService.getPublicAll();
		} else {
			list = phoneGroupService.getAll(ContextUtil.getCurrentUserId());
		}
		jsonString = mapper.toDataJson(list);
		return SUCCESS;
	}
}
