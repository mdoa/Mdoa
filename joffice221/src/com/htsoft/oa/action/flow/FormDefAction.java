package com.htsoft.oa.action.flow;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.struts2.dispatcher.multipart.MultiPartRequestWrapper;
import org.dom4j.Document;
import org.dom4j.Element;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.htsoft.core.command.QueryFilter;
import com.htsoft.core.engine.FreemarkEngine;
import com.htsoft.core.json.JacksonMapper;
import com.htsoft.core.util.AppUtil;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.Dom4jUtil;
import com.htsoft.core.util.FileUtil;
import com.htsoft.core.util.HtmlParseUtil;
import com.htsoft.core.util.RequestUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.core.web.action.BaseAction;

import com.htsoft.oa.model.flow.FieldRights;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.model.flow.FormField;
import com.htsoft.oa.model.flow.FormTable;
import com.htsoft.oa.model.flow.FormTableItem;
import com.htsoft.oa.model.flow.ProDefinition;
import com.htsoft.oa.service.flow.FieldRightsService;
import com.htsoft.oa.service.flow.FormDefMappingService;
import com.htsoft.oa.service.flow.FormDefService;
import com.htsoft.oa.service.flow.FormFieldService;
import com.htsoft.oa.service.flow.FormTableGenService;
import com.htsoft.oa.service.flow.FormTableService;
import com.htsoft.oa.service.flow.ProDefinitionService;
import com.htsoft.oa.util.FilePathUtil;
import com.htsoft.oa.util.FormUtil;
import com.htsoft.oa.vo.ParseReult;

import freemarker.template.TemplateException;

/**
 * 
 * @author
 * 
 */
public class FormDefAction extends BaseAction {
	@Resource
	private FormDefService formDefService;
	@Resource
	private FormTableGenService formTableGenService;
	@Resource
	private FormTableService formTableService;
	@Resource
	private ProDefinitionService proDefinitionService;
	@Resource
	private FormDefMappingService formDefMappingService;
	@Resource
	private FieldRightsService fieldRightsService;
	@Resource
	private FormFieldService formFieldService;

	@Resource
	private FreemarkEngine freemarkEngine;
	
	private FormDef formDef;

	private FormTable formTable;

	private FormTable formTable1;

	private Long formDefId;
	private Long defId;// 流程定义id

	public Long getFormDefId() {
		return formDefId;
	}

	public void setFormDefId(Long formDefId) {
		this.formDefId = formDefId;
	}

	public FormDef getFormDef() {
		return formDef;
	}

	public void setFormDef(FormDef formDef) {
		this.formDef = formDef;
	}

	public FormTable getFormTable() {
		return formTable;
	}

	public void setFormTable(FormTable formTable) {
		this.formTable = formTable;
	}

	public FormTable getFormTable1() {
		return formTable1;
	}

	public void setFormTable1(FormTable formTable1) {
		this.formTable1 = formTable1;
	}

	public Long getDefId() {
		return defId;
	}

	public void setDefId(Long defId) {
		this.defId = defId;
	}

	/**
	 * 显示列表
	 */
	public String list() {

		QueryFilter filter = new QueryFilter(getRequest());
		List<FormDef> list = formDefService.getAll(filter);

		Type type = new TypeToken<List<FormDef>>() {
		}.getType();
		StringBuffer buff = new StringBuffer("{success:true,'totalCounts':")
				.append(filter.getPagingBean().getTotalItems()).append(
						",result:");

		Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
				.create();
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
				formDefService.remove(new Long(id));
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
		FormDef formDef = formDefService.get(formDefId);

		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		// 将数据转成JSON格式
		StringBuffer sb = new StringBuffer("{success:true,data:");
		sb.append(gson.toJson(formDef));
		sb.append("}");
		setJsonString(sb.toString());

		return SUCCESS;
	}

	/**
	 * 生成实体
	 */

	public String gen() {
		FormDef formDef = formDefService.get(formDefId);
		Set<FormTable> tables = formDef.getFormTables();
		if (tables.size() > 0) {
			FormTable[] formTables = new FormTable[tables.size()];
			Iterator<FormTable> it = tables.iterator();
			int i=0;
			while (it.hasNext()) {
				formTables[i++]=it.next();
			}
			if(formTableGenService.genBean(formTables)==true)
				setJsonString("{success:true}");
			else
				setJsonString("{failure:true}");
			
			formDef.setIsGen(FormDef.HAS_GEN);
			formDefService.save(formDef);
		}
		
		return SUCCESS;
	}
	/*
	 * 生成全部实体
	 */
	public String genAll() {
		QueryFilter filter = new QueryFilter(getRequest());
		filter.addFilter("Q_formDef.status_SN_EQ", FormDef.HAS_Pub.toString());
		List<FormTable> tables = formTableService.getAll(filter);
		if (tables.size() > 0) {
			FormTable[] formTables = new FormTable[tables.size()];
			Iterator<FormTable> it = tables.iterator();
			int i=0;
			while (it.hasNext()) {
				formTables[i++]=it.next();
			}
			if(formTableGenService.genBean(formTables))
				setJsonString("{success:true}");
			else
				setJsonString("{failure:true}");
			
			
		}
		
		return SUCCESS;
	}

	/**
	 * 添加及保存操作
	 */

	public String save() {

		Map<FormTable, String> datas = new HashMap<FormTable, String>();
		String tablesReq=getRequest().getParameter("objs");
		if(StringUtils.isNotEmpty(tablesReq)){
			Gson gson=new Gson();
			FormTableItem[] tables=gson.fromJson(tablesReq,FormTableItem[].class);
			for(FormTableItem formTableItem:tables){
                FormTable formTable=new FormTable();
                formTable.setTableId(formTableItem.getTableId());
                formTable.setTableKey(formTableItem.getTableKey());
                formTable.setTableName(formTableItem.getTableName());
                formTable.setIsMain(formTableItem.getIsMain());
                datas.put(formTable, formTableItem.getFields());
                /**
                 * 检测是否已经存在相同的表名
                 */
                List<FormTable> formTables=formTableService.findByTableKey(formTableItem.getTableKey());
                if(formTables.size()>2||(formTables.size()==1&&formTableItem.getTableId()==null)){
                	setJsonString("{success:false,msg:'"+formTableItem.getTableKey()+"'}");
                	return SUCCESS;
                }
			}
		}
//		String data1 = getRequest().getParameter("field1");
//		String data2 = getRequest().getParameter("field2");
//		datas.put(formTable, data1);
//		if (StringUtils.isNotEmpty(data2)) {
//			datas.put(formTable1, data2);
//		} else {
//			if (formTable1.getTableId() != null) {
//				formTableService.remove(formTable1.getTableId());
//			}
//		}
		
		formDef = formDefService.saveFormDef(formDef, datas);

		setJsonString("{success:true}");
		return SUCCESS;

	}

	/**
	 * 添加流程表单数据
	 */
	public String add() {
		String msg = "{success:true}";
		// 1.判断该流程中是否已经存在设置的表单数据，需要加上版本号
		ProDefinition pd=proDefinitionService.get(defId);
		//当流程定义更改时，需要重新定义其对应的表单
		FormDefMapping fm = formDefMappingService.getByDeployId(pd.getDeployId());
		FormDef fd = formDefService.get(formDefId);
		if (fm != null && fm.getFormDefId() == formDefId) { // 数据添加重复,该表单信息已经添加了
			msg = "{failure:true,msg:'对不起，该表单信息已经添加，请选择其他表单信息！'}";
		} else if (fm == null) { // 原来没有设置表单，直接添加数据
			// 2.根据defId从pro_definition表中获取newVersion[版本号]，deployId[工作流id]
			ProDefinition pdf = proDefinitionService.get(defId);
			// 3.向form_def_mapping表中添加数据
			FormDefMapping fdm = new FormDefMapping();
			fdm.setDeployId(pdf.getDeployId()); // deployId
			fdm.setDefId(defId); // defId
			fdm.setFormDef(fd); // formDefId
			fdm.setVersionNo(pdf.getNewVersion());// 版本号
			// 4.保存
			formDefMappingService.save(fdm);
		} else { // 原来已经设置表单信息，只更换FormDefMapping表中的formDefId
			/**
			 * 更改表单时，需要把之前表单的映射下的权限 如果存在，则需要删除
			 */
			List<FieldRights> rights=fieldRightsService.getByMappingId(fm.getMappingId());
			if(rights.size()>0){
				for(FieldRights right:rights){
					fieldRightsService.remove(right);
				}
			}
			fm.setFormDef(fd); // 修改
			
			formDefMappingService.save(fm);
		}
		setJsonString(msg);
		return SUCCESS;
	}
	
	public String check(){
		boolean isHas=formDefMappingService.formDefHadMapping(formDefId);
		if(isHas){
			setJsonString("{success:true,msg:'该表单已经同相应的流程关联了！'}");
		}else{
			setJsonString("{success:false}");
		}
		return SUCCESS;
	}
	
	/**
	 * 保存表单VM与XML映射文件
	 * @return
	 */
	public String saveVmXml() {

		String defId = getRequest().getParameter("defId");
		String activityName = getRequest().getParameter("activityName");

		String vmSources = getRequest().getParameter("vmSources");

		ProDefinition proDefinition = proDefinitionService.get(new Long(defId));
		String filePath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/"
				+ proDefinition.getName() + "/" + proDefinition.getNewVersion()
				+ "/" + activityName;

		String vmFilePath = filePath + ".vm";

		FileUtil.writeFile(vmFilePath, vmSources);

		setJsonString("{success:true}");

		return SUCCESS;
	}
	
	/**
	 * 取得表单VM与XML的源代码
	 * @return
	 */
	public String getVmXml() {
		String defId = getRequest().getParameter("defId");
		String activityName = getRequest().getParameter("activityName");

		ProDefinition proDefinition = proDefinitionService.get(new Long(defId));
		String filePath = AppUtil.getAppAbsolutePath() + "/WEB-INF/FlowForm/"
				+ proDefinition.getName() + "/" + proDefinition.getNewVersion()
				+ "/" + activityName;
		String vmFilePath = filePath + ".vm";
		String vmSources = FileUtil.readFile(vmFilePath);

		Gson gson = new Gson();

		setJsonString("{success:true,vmSources:" + gson.toJson(vmSources) + "}");

		return SUCCESS;
	}
	
	/**
	 * 取得表单字段
	 * @return
	 */
	public String getField(){
		StringBuffer sb = new StringBuffer();
		String defId=getRequest().getParameter("defId");
		//String activityName=getRequest().getParameter("activityName");
		
		FormDefMapping formDefMapping = formDefMappingService.getByDefId(new Long(defId));
		if(formDefMapping!=null){
			FormDef formDef = formDefMapping.getFormDef();
			if(formDef!=null){
				Set<FormTable> formTables = formDef.getFormTables();
				Iterator<FormTable> itFormTables=formTables.iterator();
				while(itFormTables.hasNext()){
					FormTable formTable = itFormTables.next();
					if(formTable.getIsMain()==1){
						List<FormField> formFields = formFieldService.getFieldFromTableId(formTable.getTableId());
						for(FormField formField:formFields){
							if(sb.length()>1){
								sb.append(",");
							}
							sb.append("[")
							.append("\"").append(formField.getFieldName()).append("\",")
							.append("\"").append(formField.getFieldLabel()).append("\",")
							.append("\"").append(formField.getFieldType()).append("\"")
							.append("]");
						}
					}
				}
			}
		}
		setJsonString("["+sb.toString()+"]");
		
		return SUCCESS;
	}
	
	// TODO 以下是新版本
		/**
		 * 获取模板选择
		 * 
		 * @return
		 */
		@SuppressWarnings("unchecked")
		public String template() {

			String templatePath = FilePathUtil.getDesignTemplatePath();
			String xml = FileUtil.readFile(templatePath + "designtemps.xml");
			Document document = Dom4jUtil.loadXml(xml);
			Element root = document.getRootElement();
			List<Element> list = root.elements();
			StringBuffer sb = new StringBuffer("[");
			for (Element element : list) {
				String alias = element.attributeValue("alias");
				String name = element.attributeValue("name");
				String templateDesc = element.attributeValue("templateDesc");
				sb.append("['").append(alias).append("','").append(name)
						.append("','").append(templateDesc).append("'],");
			}
			if (list.size() > 0) {
				sb.deleteCharAt(sb.length() - 1);
			}
			sb.append("]");

			setJsonString(sb.toString());
			return SUCCESS;

		}

		/**
		 * 表单设计
		 * 
		 * <pre>
		 * 处理3个逻辑 
		 * 1.新增 
		 * 2.编辑 
		 * 3.下一步 返回（包括新增和编辑）
		 * </pre>
		 * 
		 * @return
		 */
		public String formDesign() {

			long formDefId = RequestUtil.getLong(getRequest(), "formDefId");
			String formTitle = RequestUtil.getString(getRequest(), "formTitle");
			String formDesp = RequestUtil.getString(getRequest(), "formDesp");
			String defHtml = RequestUtil.getString(getRequest(), "defHtml");
			String tempalias = RequestUtil.getString(getRequest(), "tempalias");
			String tableKey = RequestUtil.getString(getRequest(), "tableKey");
			String tableName = RequestUtil.getString(getRequest(), "tableName");

			int isBack = RequestUtil.getInt(getRequest(), "isBack");
			// 是否发布
			boolean status = false;
			boolean canEditColumnNameAndType = true;// 能否编辑
			if (formDefId == 0L) {
				if (StringUtil.isNotEmpty(tempalias)) {
					String templatePath = FilePathUtil.getDesignTemplatePath();
					defHtml = FileUtil.readFile(templatePath + tempalias + ".html");
				}
				formDef = new FormDef();
				formDef.setFormTitle(formTitle);
				formDef.setFormDesp(formDesp);
				formDef.setDefHtml(defHtml);
			} else {
				formDef = formDefService.get(formDefId);
				if (BeanUtil.isNotEmpty(formDef)
						&& BeanUtil.isNotEmpty(formDef.getFormTables())) {
					FormTable formTable = formDef.getMainTable();
					canEditColumnNameAndType = formDefService
							.checkCanEditColumnNameTypeByFormDef(formDef);
					if (isBack == 1) {// 返回
						formTable.setTableKey(tableKey);
						formTable.setTableName(tableName);
					}
					getRequest().setAttribute("formTable", formTable);
					status = true;
				}
				if (isBack == 1) {// 返回
					formDef.setFormTitle(formTitle);
					formDef.setFormDesp(formDesp);
					formDef.setDefHtml(defHtml);
				}
			}

			getRequest().setAttribute("formDef", formDef);
			getRequest().setAttribute("status", status);
			getRequest().setAttribute("canEditColumnNameAndType",
					canEditColumnNameAndType);
			return "formDesign";

		}

		/**
		 * 对表单的html进行验证，验证html是否合法。
		 * 
		 * @return
		 */
		public String validDesign() {

			JacksonMapper mapper = new JacksonMapper();
			Map<String, Object> map = new HashMap<String, Object>();

			long formDefId = RequestUtil.getLong(getRequest(), "formDefId");
			String defHtml = RequestUtil.getString(getRequest(), "defHtml");
			String tableKey = RequestUtil.getString(getRequest(), "tableKey");
			String tableName = RequestUtil.getString(getRequest(), "tableName");

			Set<FormTable> formTables = new HashSet<FormTable>();
			if (formDefId != 0L) {
				formDef = formDefService.get(formDefId);
				formTables = formDef.getFormTables();
			}
			// 输入了主表名。
			// 验证主表名称。
			String strValid = this.checkTableKey(tableKey, formTables);

			if (StringUtil.isNotEmpty(strValid)) {
				map.put("valid", false);
				map.put("errorMsg", strValid);
				jsonString = mapper.toJson(map);
				return SUCCESS;
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("defHtml", defHtml);
			params.put("tableKey", tableKey);
			params.put("tableName", tableName);
			// 对输入的html进行解析。 返回结果
			ParseReult result = FormUtil.parseHtmlNoTable(params);

			FormTable formTable = result.getFormTable();
			// 验证子表。
			strValid = validSubTable(formTable, formTables);

			if (StringUtil.isNotEmpty(strValid)) {
				map.put("valid", false);
				map.put("errorMsg", strValid);
				jsonString = mapper.toJson(map);
				return SUCCESS;
			}
			// 验证表单是否有错。
			boolean rtn = result.hasErrors();
			if (rtn) {
				map.put("valid", false);
				map.put("errorMsg", result.getError());
			} else {
				map.put("valid", true);
				map.put("table", result.getFormTable());
			}
			jsonString = mapper.toJson(map);
			return SUCCESS;

		}

		/**
		 * 检查主表是否重复
		 * 
		 * @param tableKey
		 *            表名
		 * @param formTables
		 *            当前流程定义的表
		 * @return
		 */
		private String checkTableKey(String tableKey, Set<FormTable> formTables) {
			String strValid = "";
			if (StringUtil.isNotEmpty(tableKey)) {
				if (BeanUtil.isNotEmpty(formTables)) {
					for (FormTable formTable : formTables) {
						if (formTable.getIsMain().shortValue() == FormTable.MAIN_TABLE
								.shortValue()) {
							// 输入的表名和原来的表名不一致的情况。
							if (!tableKey.equalsIgnoreCase(formTable.getTableKey())) {
								// 检测是否已经存在相同的表名
								strValid = this.checkTableKey(tableKey, true);
							}
							break;
						}
					}
				} else {
					// 检测是否已经存在相同的表名
					strValid = this.checkTableKey(tableKey, true);
				}
			}
			return strValid;
		}

		/**
		 * 验证表是否存在
		 * 
		 * @param tableKey
		 *            表名
		 * @param flag
		 *            是主表还是子表
		 * @return 输出结果
		 */
		private String checkTableKey(String tableKey, Boolean flag) {
			String result = "";
			// 检测是否已经存在相同的表名
			List<FormTable> formTables = formTableService.findByTableKey(tableKey);
			if (BeanUtil.isNotEmpty(formTables)) {
				result = (flag ? "主" : "子") + "表:[" + tableKey + "],在系统中已经存在!<br/>";
			}
			return result;
		}

		/**
		 * 验证子表
		 * 
		 * @param table
		 * @param formTables
		 * @return
		 */
		private String validSubTable(FormTable table, Set<FormTable> formTables) {
			List<FormTable> subTableList = table.getSubTableList();
			String str = "";
			if (BeanUtil.isEmpty(subTableList))
				return str;
			for (FormTable subTable : subTableList) {
				String tableKey = subTable.getTableKey().toLowerCase();

				// 表已经生成的情况。
				if (BeanUtil.isNotEmpty(formTables)) {
					Map<String, FormTable> mapSubTable = new HashMap<String, FormTable>();
					for (FormTable formTable : formTables) {
						if (formTable.getIsMain().shortValue() == FormTable.NOT_MAIN_TABLE
								.shortValue()) {
							mapSubTable.put(formTable.getTableKey(), formTable);
						}
					}
					// 原子表中不存在该表，表示该表为新添加的表。
					if (!mapSubTable.containsKey(tableKey)) {
						str = this.checkTableKey(tableKey, false);
					}
				} else {// 还没有生成表的情况。
					str = this.checkTableKey(tableKey, false);
				}
			}
			return str;
		}

		/**
		 * 保存表单数据 //TODO 处理1。保存表单和发布表单
		 * 
		 * @return
		 */
		public String saveForm() {
			JacksonMapper mapper = new JacksonMapper();
			Map<String, Object> resultMap = new HashMap<String, Object>();

			long formDefId = RequestUtil.getLong(getRequest(), "formDefId");
			String formTitle = RequestUtil.getString(getRequest(), "formTitle");
			String formDesp = RequestUtil.getString(getRequest(), "formDesp");
			String defHtml = getRequest().getParameter("defHtml");
			String tableKey = RequestUtil.getString(getRequest(), "tableKey");
			String tableName = RequestUtil.getString(getRequest(), "tableName");
			// 是否发布
			short status = RequestUtil.getShort(getRequest(), "status",
					FormDef.NOT_Pub.shortValue());

			Set<FormTable> formTables = new HashSet<FormTable>();

			// 取得当前的表单
			if (formDefId == 0L) {
				formDef = new FormDef();
			} else {
				formDef = formDefService.get(formDefId);
				formTables = formDef.getFormTables();
			}
			// 验证主表名称。
			String strValid = this.checkTableKey(tableKey, formTables);
			if (StringUtil.isNotEmpty(strValid)) {
				resultMap.put("valid", false);
				resultMap.put("msg", strValid);
				jsonString = mapper.toJson(resultMap);
				return SUCCESS;
			}

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("defHtml", defHtml);
			params.put("tableKey", tableKey);
			params.put("tableName", tableName);
			// 对输入的html进行解析。 返回结果
			ParseReult result = FormUtil.parseHtmlNoTable(params);

			formDef.setFormTitle(formTitle);
			formDef.setFormDesp(formDesp);
			formDef.setDefHtml(defHtml);
			try {
				// 通过解析后的到的表对象。
				FormTable formTable = result.getFormTable();
				formDefService.saveForm(formDef, formTable, status);
			} catch (Exception ex) {
				if (logger.isDebugEnabled()) {
					logger.debug(ex);
				}
				resultMap.put("valid", false);
				resultMap.put("msg", result.getError());
			}

			resultMap.put("valid", true);
			resultMap.put("msg",
					(status == FormDef.HAS_Pub.shortValue()) ? "表单发布成功!"
							: "保存表单成功!");
			jsonString = mapper.toJson(resultMap);
			return SUCCESS;
		}

		/**
		 * 下一步--设计表
		 * 
		 * @return
		 */
		public String designTable() {
			HttpServletRequest request = getRequest();

			long formDefId = RequestUtil.getLong(getRequest(), "formDefId");
			String formTitle = RequestUtil.getString(getRequest(), "formTitle");
			String formDesp = RequestUtil.getString(getRequest(), "formDesp");
			String defHtml = RequestUtil.getString(getRequest(), "content");// 这个注意点
			String tableKey = RequestUtil.getString(getRequest(), "tableKey");
			String tableName = RequestUtil.getString(getRequest(), "tableName");
			boolean canEditTableName = true;

			if (formDefId != 0) {
				formDef = formDefService.get(formDefId);
				canEditTableName = formDefService.checkCanEditColumnNameTypeByFormDef(formDef);
			} else {
				formDef = new FormDef();
			}
			formDef.setFormTitle(formTitle);
			formDef.setFormDesp(formDesp);
			formDef.setDefHtml(defHtml);

			Map<String, Object> params = new HashMap<String, Object>();
			params.put("defHtml", defHtml);
			ParseReult result = FormUtil.parseHtmlNoTable(params);

			request.setAttribute("formDef", formDef);
			request.setAttribute("result", result);
			request.setAttribute("canEditTableName", canEditTableName);
			request.setAttribute("tableKey", tableKey);
			request.setAttribute("tableName", tableName);
			return "designTable";
		}

		/**
		 * 导入表单数据
		 * 
		 * @return
		 */
		public String importForm() {
			FileInputStream fis = null;
			try {
				MultiPartRequestWrapper mpRequest = (MultiPartRequestWrapper) this
						.getRequest();
				File[] files = mpRequest.getFiles("importInput"); // 文件现在还在临时目录中
				fis = new FileInputStream(files[0]);
				jsonString = FileUtil.inputStream2String(fis, "utf-8");
			} catch (Exception e) {
				jsonString = "false";
			} finally {
				if (null != fis) {
					try {
						fis.close();
						fis = null;
					} catch (Exception e) {
					}
				}
			}
			return SUCCESS;
		}

		/**
		 * 导出表单
		 * 
		 * @return
		 * @throws Exception
		 */
		public String exportForm() {
			HttpServletResponse response = getResponse();
			String formTitle = RequestUtil.getString(getRequest(), "formTitle");
			String defHtml = RequestUtil.getString(getRequest(), "defHtml");

			String title = formTitle + ".html";
			response.setContentType("application/octet-stream;charset="
					+ StringUtil.getStrutsEncoding());
			response.addHeader("Content-Disposition", "attachment;filename="
					+ StringUtil.encodingString(title, "GBK", "ISO-8859-1"));
			PrintWriter writer = null;
			try {
				writer = response.getWriter();
				writer.write(defHtml);
			} catch (Exception e) {
				System.out.println("导出错误");
			} finally {
				if (null != writer) {
					try {
						writer.flush();
						writer.close();
						writer = null;
					} catch (Exception e) {
					}
				}
			}
			return SUCCESS;
		}

		/**
		 * 预览
		 * 
		 * @return
		 */
		public String preview() {
			String defHtml = getRequest().getParameter("defHtml");

			// 解析html转化为ext
			String formHtml = "";
			try {

				HtmlParseUtil parse = new HtmlParseUtil();
				Map map = new HashMap();
				map.put("ft", parse.parseHtml(defHtml));
				formHtml = freemarkEngine.mergeTemplateIntoString("formTable.ftl",
						map);
				formHtml = formHtml.replaceAll("\r", "").replaceAll("\n", "")
						.replaceAll("\t", "");
				logger.debug(formHtml);

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (TemplateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			getRequest().setAttribute("defHtml", formHtml);
			return "preview";

		}
}
