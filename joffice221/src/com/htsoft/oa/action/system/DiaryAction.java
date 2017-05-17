package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.Diary;
import com.htsoft.oa.service.system.AppUserService;
import com.htsoft.oa.service.system.DiaryService;

/**
 * @description 我的下属日志搜索展示
 * @class DiaryAction
 * @author csx,YHZ
 * @company www.jee-soft.cn
 * @data 2010-12-23PM
 */
public class DiaryAction extends BaseAction {
	@Resource
	private DiaryService diaryService;
	@Resource
	private AppUserService appUserService;
	private Diary diary;
	private Date from;
	private Date to;

	public Date getFrom() {
		return from;
	}

	public void setFrom(Date from) {
		this.from = from;
	}

	public Date getTo() {
		return to;
	}

	public void setTo(Date to) {
		this.to = to;
	}

	private Long diaryId;

	public Long getDiaryId() {
		return diaryId;
	}

	public void setDiaryId(Long diaryId) {
		this.diaryId = diaryId;
	}

	public Diary getDiary() {
		return diary;
	}

	public void setDiary(Diary diary) {
		this.diary = diary;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		AppUser user = ContextUtil.getCurrentUser();
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", user.getId());
		List<Diary> list = diaryService.getAll(filter);
		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 查找我的下属的工作日志
	 */
	public String subUser() {
		// 查询我的下属
		AppUser user = ContextUtil.getCurrentUser();
		int Counts = 0;
		List<AppUser> list = appUserService.findRelativeUsersByFullname(null,
				user.getUserId());
		List<Diary> diaryList = new ArrayList<Diary>();
		if (list != null) {
			for (AppUser appUser : list) {
				QueryFilter filter = new QueryFilter(getRequest());
				filter.addFilter("Q_appUser.userId_L_EQ",
						(appUser.getUserId()).toString());
				diaryList.addAll(diaryService.getAll(filter));
				Counts += filter.getPagingBean().getTotalItems();
			}
		}
		jsonString = mapper.toPageJson(diaryList, Counts);
		return SUCCESS;
	}

	/*
	 * 查询列表
	 */
	public String search() {

		AppUser user = ContextUtil.getCurrentUser();
		QueryFilter filter = new QueryFilter(getRequest());
		// 按用户查询
		filter.addFilter("Q_appUser.userId_L_EQ", (user.getId()).toString());
		// 按起始时间查询
		if (getRequest().getParameter("from") != "") {
			filter.addFilter("Q_dayTime_D_GE", getRequest()
					.getParameter("from"));
		}
		// 按最终时间查询
		if (getRequest().getParameter("to") != "") {
			filter.addFilter("Q_dayTime_D_LE", getRequest().getParameter("to"));
		}
		// 按内容查询
		filter.addFilter("Q_content_S_LK", diary.getContent());
		// 按类型查询
		if (diary.getDiaryType() != null) {
			filter.addFilter("Q_diaryType_SN_EQ",
					(diary.getDiaryType()).toString());
		}

		List<Diary> list = diaryService.getAll(filter);

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
				diaryService.remove(new Long(id));
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
		Diary diary = diaryService.get(diaryId);
		mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
		jsonString = mapper.toDataJson(diary);
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		AppUser user = ContextUtil.getCurrentUser();
		diary.setAppUser(user);
		diaryService.save(diary);
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	public String check() {
		String strId = getRequest().getParameter("diaryId");
		if (StringUtils.isNotEmpty(strId)) {
			diary = diaryService.get(new Long(strId));
		}
		return "check";
	}

	public String display() {
		AppUser user = ContextUtil.getCurrentUser();
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_appUser.userId_L_EQ", (user.getId()).toString());
		filter.addSorted("diaryId", "desc");
		List<Diary> list = diaryService.getAll(filter);
		getRequest().setAttribute("diaryList", list);
		return "display";
	}

}
