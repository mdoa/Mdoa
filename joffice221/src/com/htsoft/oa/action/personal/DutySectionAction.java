package com.htsoft.oa.action.personal;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.personal.DutySection;
import com.htsoft.oa.service.personal.DutySectionService;

/**
 * 
 * @author
 * 
 */
public class DutySectionAction extends BaseAction {
	@Resource
	private DutySectionService dutySectionService;
	private DutySection dutySection;

	private Long sectionId;

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public DutySection getDutySection() {
		return dutySection;
	}

	public void setDutySection(DutySection dutySection) {
		this.dutySection = dutySection;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<DutySection> list = dutySectionService.getAll(filter);
		mapper.setDateFormat(DateFormatUtil.TIME_NOSECOND_FORMAT);
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
				dutySectionService.remove(new Long(id));
			}
		}
		jsonString = "{success:true}";
		return SUCCESS;
	}

	public String combo() {
		StringBuffer sb = new StringBuffer();

		List<DutySection> dutySectionList = dutySectionService.getAll();
		sb.append("[");
		for (DutySection dutySection : dutySectionList) {
			sb.append("['").append(dutySection.getSectionId()).append("','")
					.append(dutySection.getSectionName()).append("'],");
		}
		if (dutySectionList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		setJsonString(sb.toString());
		return SUCCESS;

	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		DutySection dutySection = dutySectionService.get(sectionId);
		mapper.setDateFormat(DateFormatUtil.TIME_NOSECOND_FORMAT);
		jsonString = mapper.toDataJson(dutySection);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		// set the time here
		dutySectionService.save(dutySection);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
