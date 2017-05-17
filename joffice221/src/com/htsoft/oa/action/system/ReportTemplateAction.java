package com.htsoft.oa.action.system;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.web.action.BaseAction;
import com.htsoft.oa.model.system.ReportParam;
import com.htsoft.oa.model.system.ReportTemplate;
import com.htsoft.oa.service.system.ReportParamService;
import com.htsoft.oa.service.system.ReportTemplateService;

import flexjson.JSONSerializer;

/**
 * 
 * @author csx
 * 
 */
public class ReportTemplateAction extends BaseAction {
	private String uploadPath = AppUtil.getAppAbsolutePath() + "attachFiles\\";
	@Resource
	private ReportTemplateService reportTemplateService;
	private ReportTemplate reportTemplate;
	@Resource
	private ReportParamService reportParamService;

	private Long reportId;

	public Long getReportId() {
		return reportId;
	}

	public void setReportId(Long reportId) {
		this.reportId = reportId;
	}

	public ReportTemplate getReportTemplate() {
		return reportTemplate;
	}

	public void setReportTemplate(ReportTemplate reportTemplate) {
		this.reportTemplate = reportTemplate;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<ReportTemplate> list = reportTemplateService.getAll(filter);

		Type type = new TypeToken<List<ReportTemplate>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new Gson();
		buff.append(gson.toJson(list, type));
		buff.append("}");

		jsonString = buff.toString();

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
				reportTemplate = reportTemplateService.get(new Long(id));
				List<ReportParam> list = reportParamService
						.findByRepTemp(new Long(id));
				for (ReportParam rp : list) {
					reportParamService.remove(rp);
				}

				java.io.File file = new java.io.File(uploadPath
						+ reportTemplate.getReportLocation());
				java.io.File parent=file.getParentFile();
				deleteFile(parent);

				reportTemplateService.remove(new Long(id));

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
		ReportTemplate reportTemplate = reportTemplateService.get(reportId);

		JacksonMapper mapper = new JacksonMapper(true, "yyyy-MM-dd HH:mm:ss");
		jsonString = mapper.toDataJson(reportTemplate);

		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */
	public String save() {
		// 通过后台直接设置报表上传时间和修改时间
		if (reportTemplate.getReportId() == null) {
			reportTemplate.setCreatetime(new Date());
			reportTemplate.setUpdatetime(new Date());
			reportTemplateService.save(reportTemplate);
		} else {
			
			ReportTemplate old=reportTemplateService.get(reportTemplate.getReportId());
			//删除旧文件ljf
//			if(!old.getReportLocation().toString().trim().equals(reportTemplate.getReportLocation().toString().trim()))
//			{
//				java.io.File file = new java.io.File(uploadPath+ old.getReportLocation());
//				deleteFile(file);
//			}
			
			
			try {
				BeanUtil.copyNotNullProperties(old, reportTemplate);
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			old.setUpdatetime(new Date());
			
			reportTemplateService.save(old);
		}
		
		setJsonString("{success:true}");
		return SUCCESS;
	}
	
	
	/**
	 * 删除旧文件
	 * 黎剑发2010.08.05
	 */
	private void deleteFile(File file){ 
		if(file.exists()){ 
			if(file.isFile()){ 
				file.delete(); 
			}else if(file.isDirectory()){ 
				File files[] = file.listFiles(); 
				for(int i=0;i<files.length;i++){ 
					this.deleteFile(files[i]); 
				}
			}
		}else{ 
			System.out.println("所删除的文件不存在！"+'\n'); 
		} 
	}

	/**
	 * 加载搜索条件框
	 */
	public String load() {
		String strReportId = getRequest().getParameter("reportId");
		if (StringUtils.isNotEmpty(strReportId)) {
			List<ReportParam> list = reportParamService.findByRepTemp(new Long(
					strReportId));
			Gson gson = new Gson();
			StringBuffer sb = new StringBuffer();
			sb.append(gson.toJson(list));
			
			setJsonString("{success:true,data:" + sb.toString() + "}");
		} else {
			setJsonString("{success:false}");
		}
		return SUCCESS;
	}

	/**
	 * 提交数据
	 */
//	public String submit() {
//		Map map = getRequest().getParameterMap();
//		Iterator it = map.entrySet().iterator();
//		StringBuffer sb = new StringBuffer();
//		while (it.hasNext()) {
//			Map.Entry entry = (Map.Entry) it.next();
//			String key = (String) entry.getKey();
//			String[] value = (String[]) entry.getValue();
//			sb.append("&" + key + "=" + value[0]);
//		}
//		setJsonString("{success:true,data:'" + sb.toString() + "'}");
//		return SUCCESS;
//	}
	
	public String submit() {
		java.text.SimpleDateFormat dateformat=new SimpleDateFormat("yyyy-mm-dd");
		
		Map map = getRequest().getParameterMap();
		Iterator it = map.entrySet().iterator();
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			Map.Entry entry = (Map.Entry) it.next();
			String key = (String) entry.getKey();
			String[] value = (String[]) entry.getValue();
			String v=value[0];
			
			
			if(v==null||v.equals("")){
				v="%";
			}else{
				try {
					dateformat.parse(v.trim());
				} catch (ParseException e) {
					v="%"+v.trim()+"%";
				}
			}
			
			
			sb.append("&" + key + "=" + v);
		}
		setJsonString("{success:true,data:'" + sb.toString() + "'}");
		return SUCCESS;
	}

}
