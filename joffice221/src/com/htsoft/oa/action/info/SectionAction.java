package com.htsoft.oa.action.info;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.core.util.DateFormatUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.info.Section;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.SysConfig;
import com.htsoft.oa.service.info.SectionService;
import com.htsoft.oa.service.system.SysConfigService;

/**
 * 栏目管理
 * 
 * @author
 * 
 */
public class SectionAction extends BaseAction {
	@Resource
	private SectionService sectionService;
	@Resource
	private SysConfigService sysConfigService;
	private Section section;

	private Long sectionId;

	public Long getSectionId() {
		return sectionId;
	}

	public void setSectionId(Long sectionId) {
		this.sectionId = sectionId;
	}

	public Section getSection() {
		return section;
	}

	public void setSection(Section section) {
		this.section = section;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<Section> list = sectionService.getAll(filter);

		StringBuffer sb = new StringBuffer("{success:true,totalCounts:")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");
		sb.append(mapper.toJson(list));
		SysConfig sectionColumn = sysConfigService.findByKey("sectionColumn");
		sb.append(",columnType:");
		if (sectionColumn != null) {
			sb.append(sectionColumn.getDataValue());
		} else {
			sb.append("2");// 默认为两列
		}
		sb.append("}");

		jsonString = sb.toString();

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
				sectionService.remove(new Long(id));
			}
		}

		jsonString = "{success:true}";

		return SUCCESS;
	}

	/**
	 * 显示详细信息
	 * 
	 * @return
	 */
	public String get() {
		Section section = sectionService.get(sectionId);

		jsonString = mapper.toDataJson(section);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {

		if (section.getSectionId() == null) {
			AppUser curUser = ContextUtil.getCurrentUser();
			section.setCreatetime(new Date());
			section.setUserId(curUser.getUserId());
			section.setUsername(curUser.getFullname());

			section.setColNumber(Section.COLUMN_ONE);// 默认在第一列出现
			section.setRowNumber(sectionService.getLastColumn() + 1); // 默认在最后一行出现
			sectionService.save(section);

			mapper.setDateFormat(DateFormatUtil.DATE_FORMAT);
			
			jsonString = mapper.toDataJson(section);
		} else {
			Section orgSection = sectionService.get(section.getSectionId());
			try {
				BeanUtil.copyNotNullProperties(orgSection, section);
				sectionService.save(orgSection);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
			jsonString = JSON_SUCCESS;
		}

		return SUCCESS;

	}

	/**
	 * 禁用栏目
	 * 
	 * @return
	 */
	public String disable() {
		String sectionId = getRequest().getParameter("sectionId");
		if (StringUtils.isNotEmpty(sectionId)) {
			section = sectionService.get(new Long(sectionId));
		}
		if (section != null) {
			section.setStatus(Section.STATUS_DISABLE);
			sectionService.save(section);
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 激活栏目
	 * 
	 * @return
	 */
	public String enable() {
		String secIds = getRequest().getParameter("secIds");
		if (StringUtils.isNotEmpty(secIds)) {
			String[] ids = secIds.split(",");
			for (String sectionId : ids) {
				section = sectionService.get(new Long(sectionId));
				if (section != null) {
					section.setStatus(Section.STATUS_ENABLE);
					sectionService.save(section);
				}
			}
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 保存视图
	 * 
	 * @return
	 */
	public String position() {
		String items = getRequest().getParameter("sections");

		JacksonMapper mapper = new JacksonMapper(true);
		Section[] sections = mapper.toObject(items, Section[].class);

		for (Section sec : sections) {
			Section orgSection = sectionService.get(sec.getSectionId());
			orgSection.setColNumber(sec.getColNumber());
			orgSection.setRowNumber(sec.getRowNumber());
			orgSection.setStatus(Section.STATUS_ENABLE);
			sectionService.save(orgSection);
		}
		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 系统栏目列数配置
	 * 
	 * @return
	 */
	public String column() {
		String columnType = getRequest().getParameter("columnType");
		SysConfig sectionColumn = sysConfigService.findByKey("sectionColumn");
		if (sectionColumn == null) {
			sectionColumn = new SysConfig();
			sectionColumn.setConfigDesc("栏目列数配置");
			sectionColumn.setConfigKey("sectionColumn");
			sectionColumn.setConfigName("栏目列数");
			sectionColumn.setDataType(SysConfig.SYS_DATA_TYPE_INTEGER);
			sectionColumn.setDataValue(columnType);
			sectionColumn.setTypeKey("sectionColumn");
			sectionColumn.setTypeName("栏目列数配置");
			sysConfigService.save(sectionColumn);
		} else {
			sectionColumn.setDataValue(columnType);
			sysConfigService.save(sectionColumn);
		}

		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}

	/**
	 * 获得当前配置
	 * 
	 * @return
	 */
	public String getSysConfig() {
		SysConfig sectionColumn = sysConfigService.findByKey("sectionColumn");
		if (sectionColumn == null) {
			sectionColumn = new SysConfig();
			sectionColumn.setConfigDesc("栏目列数配置");
			sectionColumn.setConfigKey("sectionColumn");
			sectionColumn.setConfigName("栏目列数");
			sectionColumn.setDataType(SysConfig.SYS_DATA_TYPE_INTEGER);
			sectionColumn.setDataValue("2");
			sectionColumn.setTypeKey("sectionColumn");
			sectionColumn.setTypeName("栏目列数配置");
			sysConfigService.save(sectionColumn);
		}
		jsonString = mapper.toJson(sectionColumn);
		return SUCCESS;
	}
}
