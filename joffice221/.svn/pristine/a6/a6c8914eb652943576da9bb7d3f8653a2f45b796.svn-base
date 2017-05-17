package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.ReportParam;
import com.htsoft.oa.service.system.ReportParamService;

/**
 * 
 * @author
 * 
 */
public class ReportParamAction extends BaseAction {
	@Resource
	private ReportParamService reportParamService;
	private ReportParam reportParam;

	private Long paramId;

	public Long getParamId() {
		return paramId;
	}

	public void setParamId(Long paramId) {
		this.paramId = paramId;
	}

	public ReportParam getReportParam() {
		return reportParam;
	}

	public void setReportParam(ReportParam reportParam) {
		this.reportParam = reportParam;
	}

	/**
	 * 显示列表
	 */
	public String list() {
		String strReportId = getRequest().getParameter("reportId");
		if (StringUtils.isNotEmpty(strReportId)) {
			List<ReportParam> list = reportParamService.findByRepTemp(new Long(
					strReportId));

			JacksonMapper mapper = new JacksonMapper(true,
					"yyyy-MM-dd HH:mm:ss");
			jsonString = mapper.toPageJson(list, list.size());
		}

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
				reportParamService.remove(new Long(id));
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
	public String get(){
		ReportParam reportParam=reportParamService.get(paramId);
		
		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(reportParam);
		
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		reportParamService.save(reportParam);
		setJsonString("{success:true}");
		return SUCCESS;
	}
}
