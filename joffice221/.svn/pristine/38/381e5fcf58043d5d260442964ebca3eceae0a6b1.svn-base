package com.htsoft.oa.action.personal;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.personal.HolidayRecord;
import com.htsoft.oa.service.personal.HolidayRecordService;

/**
 * 假期设置管理
 * 
 * @author
 * 
 */
public class HolidayRecordAction extends BaseAction {
	@Resource
	private HolidayRecordService holidayRecordService;
	private HolidayRecord holidayRecord;

	private Long recordId;

	public Long getRecordId() {
		return recordId;
	}

	public void setRecordId(Long recordId) {
		this.recordId = recordId;
	}

	public HolidayRecord getHolidayRecord() {
		return holidayRecord;
	}

	public void setHolidayRecord(HolidayRecord holidayRecord) {
		this.holidayRecord = holidayRecord;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<HolidayRecord> list = holidayRecordService.getAll(filter);
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
				holidayRecordService.remove(new Long(id));
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
		HolidayRecord holidayRecord = holidayRecordService.get(recordId);
		jsonString = mapper.toDataJson(holidayRecord);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		if (holidayRecord.getIsAll() == null) {
			holidayRecord.setIsAll(HolidayRecord.IS_PERSONAL_HOLIDAY);
		} else {
			holidayRecord.setIsAll(HolidayRecord.IS_ALL_HOLIDAY);
		}
		holidayRecordService.save(holidayRecord);

		setJsonString("{success:true}");
		return SUCCESS;
	}
}
