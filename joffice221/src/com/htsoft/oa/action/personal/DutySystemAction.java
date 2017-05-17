package com.htsoft.oa.action.personal;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.personal.DutySystem;
import com.htsoft.oa.model.personal.DutySystemSections;
import com.htsoft.oa.service.personal.DutySystemService;

/**
 * 班制定义管理
 * 
 * @author
 * 
 */
public class DutySystemAction extends BaseAction {
	@Resource
	private DutySystemService dutySystemService;

	private DutySystem dutySystem;

	private Long systemId;

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public DutySystem getDutySystem() {
		return dutySystem;
	}

	public void setDutySystem(DutySystem dutySystem) {
		this.dutySystem = dutySystem;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<DutySystem> list = dutySystemService.getAll(filter);

		jsonString = mapper.toPageJson(list, filter.getPagingBean()
				.getTotalItems());
		return SUCCESS;
	}

	/**
	 * 用于表单的数据准备
	 * 
	 * @return
	 */
	public String setting() {

		if (systemId != null) {
			dutySystem = dutySystemService.get(systemId);
		}
		List<Map<String, String>> list = new ArrayList<Map<String, String>>();
		Map<String, String> map = new HashMap<String, String>();
		if (dutySystem != null) {
			String[] ids = dutySystem.getSystemSetting().split("[|]");
			String[] desc = dutySystem.getSystemDesc().split("[|]");
			// 7 days a week
			if (desc != null && desc.length == 7) {
				for (int i = 0; i < desc.length; i++) {
					map.put("day" + i, desc[i]);
				}
			}
			if (ids != null && ids.length == 7) {
				for (int i = 0; i < ids.length; i++) {
					map.put("dayId" + i, ids[i]);
				}
			}
		} else {
			for (int i = 0; i < 7; i++) {
				map.put("day" + i, "");
				map.put("dayId" + i, "");
			}
		}

		list.add(map);
		jsonString = mapper.toResultJson(list);

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
				dutySystemService.remove(new Long(id));
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
		DutySystem dutySystem = dutySystemService.get(systemId);

		jsonString = mapper.toDataJson(dutySystem);
		return SUCCESS;
	}

	/**
	 * 用于选择班制的下拉列表
	 * 
	 * @return
	 */
	public String combo() {

		StringBuffer sb = new StringBuffer();

		List<DutySystem> dutySystemList = dutySystemService.getAll();
		sb.append("[");
		for (DutySystem dutySystem : dutySystemList) {
			sb.append("['").append(dutySystem.getSystemId()).append("','")
					.append(dutySystem.getSystemName()).append("','").append(dutySystem.getSystemDesc()).append("'],");
		}
		if (dutySystemList.size() > 0) {
			sb.deleteCharAt(sb.length() - 1);
		}
		sb.append("]");
		setJsonString(sb.toString());
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		String data = getRequest().getParameter("data");

		DutySystemSections[] dss = mapper.toObject(data,
				DutySystemSections[].class);

		dutySystem.setSystemSetting(dss[0].dayIdToString());
		dutySystem.setSystemDesc(dss[0].dayToString());

		dutySystemService.save(dutySystem);

		jsonString = JSON_SUCCESS;
		return SUCCESS;
	}
}
