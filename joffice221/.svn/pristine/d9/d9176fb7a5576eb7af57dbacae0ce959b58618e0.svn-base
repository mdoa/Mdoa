package com.htsoft.oa.action.info;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.info.AppTips;
import com.htsoft.oa.service.info.AppTipsService;

/**
 * 个人便签Action
 * 
 * @author
 * 
 */
public class AppTipsAction extends BaseAction {
	@Resource
	private AppTipsService appTipsService;
	private AppTips appTips;

	private Long tipsId;

	public Long getTipsId() {
		return tipsId;
	}

	public void setTipsId(Long tipsId) {
		this.tipsId = tipsId;
	}

	public AppTips getAppTips() {
		return appTips;
	}

	public void setAppTips(AppTips appTips) {
		this.appTips = appTips;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
				.getCurrentUserId().toString());
		List<AppTips> list = appTipsService.getAll(filter);

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
		if (getRequest().getParameter("ids").equals("all")) {
			QueryFilter filter = new QueryFilter(getRequest());
			filter.addFilter("Q_appUser.userId_L_EQ", ContextUtil
					.getCurrentUserId().toString());
			List<AppTips> list = appTipsService.getAll(filter);
			for (AppTips tips : list) {
				appTipsService.remove(tips);
			}
		} else {
			String[] ids = getRequest().getParameterValues("ids");
			String[] names = getRequest().getParameterValues("names");
			if (ids != null && names != null) {
				for (String name : names) {
					AppTips appTips = appTipsService.findByName(name);
					if (appTips != null) {
						appTipsService.remove(appTips);
					}
				}
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
		AppTips appTips = appTipsService.get(tipsId);

		jsonString = mapper.toDataJson(appTips);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String data = getRequest().getParameter("data");
		if (StringUtils.isNotEmpty(data)) {
			Gson gson = new Gson();
			AppTips[] tips = gson.fromJson(data, AppTips[].class);
			for (AppTips tip : tips) {
				if (tip.getTipsId() == -1) {
					tip.setTipsId(null);
					tip.setCreateTime(new Date());
					// SimpleDateFormat date = new
					// SimpleDateFormat("yyMMddHHmmssSSS");
					// String customerNo = date.format(new Date());
					// tip.setTipsName("tips"+customerNo);
					tip.setDislevel(1);
					tip.setAppUser(ContextUtil.getCurrentUser());
					appTipsService.save(tip);
				} else {
					AppTips orgTip = appTipsService.findByName(tip
							.getTipsName());
					if (orgTip != null) {
						tip.setTipsId(null);
						try {
							BeanUtil.copyNotNullProperties(orgTip, tip);
						} catch (Exception e) {
							e.printStackTrace();
						}
						int curWidth = Integer.parseInt(getRequest()
								.getParameter("curWidth"));
						int curHeight = Integer.parseInt(getRequest()
								.getParameter("curHeight"));
						orgTip.setDiswidth(curWidth);
						orgTip.setDisheight(curHeight);
						orgTip.setDislevel(1);
						orgTip.setAppUser(ContextUtil.getCurrentUser());
						appTipsService.save(orgTip);
					}
				}

			}
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

}
