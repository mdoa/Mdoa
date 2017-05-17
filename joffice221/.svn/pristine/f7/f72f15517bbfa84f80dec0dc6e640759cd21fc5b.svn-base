package com.htsoft.oa.action.info;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.info.SuggestBox;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.service.info.SuggestBoxService;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.SysConfigService;

/**
 * 意见箱
 * 
 * @author
 * 
 */
public class SuggestBoxAction extends BaseAction {
	@Resource
	private SuggestBoxService suggestBoxService;
	@Resource
	private SysConfigService sysConfigService;
	@Resource
	private AppUserService appUserService;
	private SuggestBox suggestBox;

	private Long boxId;

	public Long getBoxId() {
		return boxId;
	}

	public void setBoxId(Long boxId) {
		this.boxId = boxId;
	}

	public SuggestBox getSuggestBox() {
		return suggestBox;
	}

	public void setSuggestBox(SuggestBox suggestBox) {
		this.suggestBox = suggestBox;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<SuggestBox> list = suggestBoxService.getAll(filter);

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
				suggestBoxService.remove(new Long(id));
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
		SuggestBox suggestBox = suggestBoxService.get(boxId);

		jsonString = mapper.toDataJson(suggestBox);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		// 意见箱创建日期
		suggestBox.setCreatetime(new Date());
		// 意见发表人IP
		suggestBox.setSenderIp(getRequest().getRemoteAddr());
		// 取得意见箱配置的接收人ID
		SysConfig suggestId = sysConfigService.findByKey("suggestId");
		AppUser suggestManager = appUserService.get(new Long(suggestId
				.getDataValue()));

		if (suggestManager != null) {
			suggestBox.setRecFullname(suggestManager.getFullname());
			suggestBox.setRecUid(suggestManager.getUserId());

			// 发送邮件(是系统中用户时用该用户发送,系统外时用系统用户发送)

		}

		suggestBoxService.save(suggestBox);
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 意见回复
	 * 
	 * @return
	 */
	public String reply() {
		SuggestBox orgSuggest = suggestBoxService.get(suggestBox.getBoxId());
		AppUser curUser = appUserService.get(new Long(sysConfigService
				.findByKey("suggestId").getDataValue()));
		orgSuggest.setReplyId(curUser.getUserId());
		orgSuggest.setIsOpen(suggestBox.getIsOpen());
		orgSuggest.setReplyFullname(curUser.getFullname());
		orgSuggest.setReplyTime(new Date());
		orgSuggest.setStatus(SuggestBox.STATUS_AUDIT);
		orgSuggest.setReplyContent(suggestBox.getReplyContent());
		suggestBoxService.save(orgSuggest);
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 密码访问
	 */
	public String match() {
		SuggestBox orgSuggest = suggestBoxService.get(suggestBox.getBoxId());
		if (orgSuggest.getQueryPwd().equals(suggestBox.getQueryPwd())) {
			jsonString = JSON_SUCCESS;
		} else {
			jsonString = JSON_FAILURE;
		}
		return SUCCESS;
	}
}
