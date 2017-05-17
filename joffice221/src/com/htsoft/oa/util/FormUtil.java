package com.htsoft.oa.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONObject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.select.Elements;

import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.model.flow.FormField;
import com.htsoft.oa.model.flow.FormTable;
import com.htsoft.oa.vo.Pair;
import com.htsoft.oa.vo.ParseReult;

public class FormUtil {
	private static Log logger = LogFactory.getLog(FormUtil.class);

	/**
	 * 控件类型
	 */
	public static Map<String, Short> controlTypeMap = new HashMap<String, Short>();
	static {
		controlTypeMap.put("hidedomain", (short) 0);
		controlTypeMap.put("textfield", (short) 1);
		controlTypeMap.put("textarea", (short) 2);
		controlTypeMap.put("checkbox", (short) 3);
		controlTypeMap.put("radioinput", (short) 4);
		controlTypeMap.put("selectinput", (short) 5);
		controlTypeMap.put("dictionary", (short) 6);

		controlTypeMap.put("userselector", (short) 7);
		controlTypeMap.put("singleuserselector", (short) 8);
		controlTypeMap.put("roleselector", (short) 9);
		controlTypeMap.put("singleroleselector", (short) 10);
		controlTypeMap.put("positionselector", (short) 11);
		controlTypeMap.put("singlepositionselector", (short) 12);
		controlTypeMap.put("depselector", (short) 13);
		controlTypeMap.put("singledepselector", (short) 14);

		controlTypeMap.put("attachement", (short) 15);
		controlTypeMap.put("datepicker", (short) 16);
		controlTypeMap.put("officecontrol", (short) 17);
		controlTypeMap.put("ckeditor", (short) 18);

	}

	// TODO 对输入的html进行解析。
	/**
	 * 对输入的html进行解析。
	 * 
	 * <pre>
	 * 	1.解析出表结构。
	 *  2.生成fremaker的模版。
	 *  
	 *  处理过程：
	 *  1.先处理子表的字段，处理子表字段后将html元素的external属性删除掉。
	 *  2.在处理主表字段。
	 *  3.处理意见模版。
	 *  
	 *  返回值为map：
	 *  键：FormTable:表示为生成的表对象。
	 *  键：template：表示生成的模版。
	 * </pre>
	 * 
	 * @param params
	 *            map对象有 html、tableKey、tableName
	 * @return 处理结果
	 */

	public static ParseReult parseHtmlNoTable(Map<String, Object> params) {
		String defHtml = (String) params.get("defHtml");
		ParseReult result = new ParseReult();
		Document doc = Jsoup.parseBodyFragment(defHtml);
		// 解析子表
		List<FormTable> subTableList = parseSubTableHtml(doc, result);
		// 解析主表。
		FormTable mainTable = parseMainTable(doc, params, result);
		// 设置子表。
		mainTable.setSubTableList(subTableList);
		// 获取主表
		result.setFormTable(mainTable);
		// 暂时不处理 保留 处理意见权限及赋值处理。
		// parseOpinion(doc,result);

		// 暂时不处理 保留 设置fremaker模版,。
		// String template = doc.body().html();
		// template = template.replaceAll("&lt;", "<").replaceAll("&gt;", ">")
		// .replaceAll("&quot;", "'");
		// template = "<#setting number_format=\"#\">\r\n" + template;
		//
		// result.setTemplate(template);
		return result;
	}

	/**
	 * 处理主表
	 * 
	 * @param doc
	 * @param params
	 * @param result
	 * @return
	 */
	private static FormTable parseMainTable(Document doc,
			Map<String, Object> params, ParseReult result) {
		String tableKey = (String) params.get("tableKey");
		String tableName = (String) params.get("tableName");
		FormTable formTable = new FormTable();

		formTable.setTableKey(tableKey);
		formTable.setTableName(tableName);
		formTable.setIsMain((short) FormTable.MAIN_TABLE);
		// 获取主表中有external属性的控件集合。
		Elements mainFields = doc.select("[external]");

		if (mainFields.size() == 0) {
			result.addError("主表【" + (tableKey == null ? "" : tableKey) + ","
					+ (tableName == null ? "主表" : tableName) + "】尚未定义任何字段");
			return formTable;
		}

		for (Iterator<Element> it = mainFields.iterator(); it.hasNext();) {
			Element el = it.next();
			// 从html中解析出字段。
			FormField formField = parseExternal(el, result);

			if (formField == null) {
				continue;
			}

			String controlName = "m:" + tableKey + ":"
					+ formField.getFieldName();
			// 字段是否重复
			boolean rtn = formTable.addField(formField);
			if (!rtn) {
				result.addError("表单中主表:【" + tableKey + "】，字段:【"
						+ formField.getFieldName() + "】重复");
				continue;
			}
			// 整理html。
			List<Pair> list = parseChilds(doc, el, controlName, formField);

			for (Iterator<Pair> tmpIt = list.iterator(); tmpIt.hasNext();) {
				Pair pair = tmpIt.next();
				String error = parseMainField(pair.getEl(), pair.getFieldName());
				if (StringUtil.isNotEmpty(error)) {
					result.addError(error);
				}
			}
			// 删除包装。
			removeWrap(el);
		}
		// 检查是否存在流程标题
		if (BeanUtil.isNotEmpty(formTable))
			checkFlowTitle(formTable, result);
		return formTable;
	}

	/**
	 * 检查是否存在流程标题
	 * 
	 * @param formTable
	 *            表
	 * @param result
	 *            返回的结果
	 */
	private static void checkFlowTitle(FormTable formTable, ParseReult result) {
		Set<FormField> formFields = formTable.getFormFields();
		String fieldName = "";
		int i = 0;
		for (FormField formField : formFields) {
			Short isFlowTitle = formField.getIsFlowTitle();
			if (isFlowTitle.shortValue() == 1) {
				i++;
				fieldName += formField.getFieldName() + ",";
			}
		}
		if (i == 0) {
			result.addError("表单中主表:【" + formTable.getTableKey()
					+ "】,未定义流程标题，请设置一个为流程标题！");
		} else if (i > 1) {
			result.addError("表单中主表:【" + formTable.getTableKey()
					+ "】,存在多个流程标题:【" + fieldName + "】，请检查！");
		}
	}

	/**
	 * 移除外围span对象。
	 * 
	 * <pre>
	 * &lt;span style="display:inline-block" name="editable-input"><input type="text" external="" />&lt;/span>
	 * 此方法删除外围name为editable-input的span对象。
	 * </pre>
	 * 
	 * @param parentEl
	 */
	private static void removeWrap(Element parentEl) {
		for (Iterator<Element> it = parentEl.children().iterator(); it
				.hasNext();) {
			Node elClone = it.next();
			parentEl.before(elClone);
		}
		parentEl.remove();
	}

	// TODO 没修过完成
	/**
	 * @param doc
	 * @param parentEl
	 * @param controlName
	 * @param formField
	 * @return
	 */
	private static List<Pair> parseChilds(Document doc, Element parentEl,
			String controlName, FormField formField) {
		List<Pair> list = new ArrayList<Pair>();
		Elements elList = parentEl.select("input,select,textarea");
		for (Iterator<Element> it = elList.iterator(); it.hasNext();) {
			Element el = it.next();
			Pair pair = new Pair(el, formField.getFieldName());
			list.add(pair);
		}
		return list;

	}

	/**
	 * 处理主表字段的权限计算和对控件赋值。
	 * 
	 * @param el
	 * @param fieldName
	 */
	private static String parseMainField(Element el, String fieldName) {
		String controltype = el.attr("controltype");
		// 控件类型
		String type = el.attr("type").toLowerCase();
		// 附件的处理方式
		if ("attachment".equalsIgnoreCase(controltype)) {
			Element parent = getContainer(el, "div_attachment_container");
			parent.attr("right", "${service.getFieldRight('" + fieldName
					+ "',  permission)}");

			el.val("${service.getFieldValue('" + fieldName + "',model)}");
		}
		// office控件的处理
		// 设置office的值和权限。
		else if ("office".equalsIgnoreCase(controltype)) {
			el.attr("value", "${service.getFieldValue('" + fieldName
					+ "',model)}");
			// 设置权限。
			el.attr("right", "${service.getFieldRight('" + fieldName
					+ "',  permission)}");
		}
		// checkbox和radio的处理
		// checkbox和radio的必须为以下格式
		// <label><input type='checkbox' value='JAVA' />JAVA</label>
		// <label><input type='checkbox' value='JQUERY' />JQUERY</label>
		else if ("checkbox".equalsIgnoreCase(type)
				|| "radio".equalsIgnoreCase(type)) {
			String value = el.attr("value");
			// 给checkbox设置是否chk和disabled属性。
			// 用户在模版中取值。
			el.attr("chk", "1").attr("disabled", "disabled");
			Element elParent = el.parent();
			String parentNodeName = elParent.nodeName();
			if (!parentNodeName.equals("label")) {
				return fieldName
						+ "的html代码必须为<label><input type='checkbox|radio' value='是'/>是</label>的形式";
			}
			// 将html赋值给一个变量，在使用service.getRdoChkBox 方法做解析
			// 如果外层元素是label，就把<#assign fieldName><label><input type='checkbox'
			// value='' /></label></#assign>当成一个整体进行处理。
			String tmp = parentNodeName.equals("label") ? elParent.toString()
					: el.toString();

			String str = "<span>&lt;#assign " + fieldName + "Html&gt;" + tmp
					+ " &lt;/#assign&gt;" + "\r\n${service.getRdoChkBox('"
					+ fieldName + "', " + fieldName + "Html,'" + value
					+ "', model, permission)}</span>";
			elParent.before(str);
			elParent.remove();
		}
		// 多行文本
		else if (el.nodeName().equalsIgnoreCase("textarea")) {
			el.append("#value");
			String str = "<span>&lt;#assign " + fieldName + "Html&gt;"
					+ el.toString() + " &lt;/#assign&gt;"
					+ "\r\n${service.getField('" + fieldName + "'," + fieldName
					+ "Html, model, permission)}</span>";
			el.before(str);
			el.remove();
		}
		// 处理文本输入框
		else if (el.nodeName().equalsIgnoreCase("input")) {
			el.attr("value", "#value");
			String str = "&lt;#assign " + fieldName + "Html&gt;"
					+ el.toString() + " &lt;/#assign&gt;"
					+ "\r\n${service.getField('" + fieldName + "'," + fieldName
					+ "Html, model, permission)}";
			// 隐藏的文本框在只读权限下面不返回value
			if ("hidden".equalsIgnoreCase(type)) {
				str = "&lt;#assign " + fieldName + "Html&gt;" + el.toString()
						+ " &lt;/#assign&gt;"
						+ "\r\n${service.getHiddenField('" + fieldName + "',"
						+ fieldName + "Html, model, permission)}";
			}
			el.before(str);
			el.remove();
		}
		// 下拉框
		else if (el.nodeName().equalsIgnoreCase("select")) {
			el.attr("val", "#value");
			String str = "&lt;#assign " + fieldName + "Html&gt;"
					+ el.toString() + " &lt;/#assign&gt;"
					+ "\r\n${service.getField('" + fieldName + "'," + fieldName
					+ "Html, model, permission)}";
			el.before(str);
			el.remove();
		}
		// 处理选择器的a标签
		else if (el.nodeName().equalsIgnoreCase("a")) {
			String str = "&lt;#assign " + fieldName + "Html&gt;"
					+ el.toString() + " &lt;/#assign&gt;"
					+ "\r\n${service.getLink('" + fieldName + "'," + fieldName
					+ "Html, model, permission)}";
			el.before(str);
			el.remove();
		}
		return "";
	}

	/**
	 * 根据指定的名称，查找某个节点的父节点。
	 * 
	 * @param node
	 * @param containerName
	 * @return
	 */
	private static Element getContainer(Element node, String containerName) {
		Element parent = node;
		while ((parent = (Element) parent.parent()) != null) {
			String name = parent.attr("name");
			if (containerName.equals(name)) {
				return parent;
			}
		}
		return node;
	}

	/**
	 * 生成字段。
	 * 
	 * <pre>
	 * 对字段的external属性进行解析。
	 * external 为一个json对象。
	 * 存储字段类型，备注，值来源，时间格式，选项，条件等信息。
	 * </pre>
	 * 
	 * @param el
	 *            元素
	 * @param result
	 * @return
	 */
	private static FormField parseExternal(Element el, ParseReult result) {
		FormField formField = new FormField();
		String external = el.attr("external").replace("&#39;", "\"");
		// 移除external属性。
		el.removeAttr("external");
		JSONObject jsonObject = null;
		try {
			jsonObject = JSONObject.fromObject(external);
		} catch (Exception ex) {
			result.addError(external + "错误的JSON格式!");
			return null;
		}
		// 获取字段名
		String fieldName = jsonObject.getString("name");
		// 字段类型
		JSONObject dbType = (JSONObject) jsonObject.get("dbType");
		// 注释
		String fieldLabel = jsonObject.getString("comment");
		if (StringUtil.isEmpty(fieldName)) {
			result.addError(external + "没有定义字段名");
			return null;
		}
		if (dbType == null) {
			result.addError(dbType + ",没有定义字段类型。");
			return null;
		}
		formField.setFieldName(fieldName);
		formField.setFieldLabel((fieldLabel == null) ? fieldName : fieldLabel);

		// 处理字段类型。
		handFieldType(dbType, formField, result);

		// 验证规则
		// String validRule=(String)jsonObject.get("validRule");
		// formField.setValidRule(validRule==null?"":validRule);
		// dictType,字典类型
		// handDictType(jsonObject, formField);
		// // 值来源处理
		// handValueFrom(jsonObject, formField);
		// 处理选项。
		handOption(jsonObject, formField);
		// // 处理条件
		// handCondition(jsonObject, formField);
		// 获取控件类型。
		handControlType(el, jsonObject, formField);
		// 处理意见
		// handOptions(jsonObject, formField);

		return formField;
	}

	/**
	 * 处理选项 是否必填，列表
	 * 
	 * @param jsonObject
	 * @param formField
	 */
	private static void handOption(JSONObject jsonObject, FormField formField) {
		// 是否必填
		Object isRequired = (Object) jsonObject.get("isRequired");
		if (isRequired == null) {
			formField.setIsRequired((short) 0);
		} else {
			formField.setIsRequired(Short.parseShort(isRequired.toString()));
		}
		// 列表
		Object isList = (Object) jsonObject.get("isList");
		if (isList == null) {
			formField.setIsList((short) 0);
		} else {
			formField.setIsList(Short.parseShort(isList.toString()));
		}
		// 查询条件。
		Object isQuery = (Object) jsonObject.get("isQuery");
		if (isQuery == null) {
			formField.setIsQuery((short) 0);
		} else {
			formField.setIsQuery(Short.parseShort(isQuery.toString()));
		}
		// 作为流程标题
		Object isFlowTitle = (Object) jsonObject.get("isFlowTitle");
		if (isFlowTitle == null) {
			formField.setIsFlowTitle((short) 0);
		} else {
			formField.setIsFlowTitle(Short.parseShort(isFlowTitle.toString()));
		}

		// 作为主键
		Object isPrimary = (Object) jsonObject.get("isPrimary");
		if (isPrimary == null) {
			formField.setIsPrimary((short) 0);
		} else {
			formField.setIsPrimary(Short.parseShort(isPrimary.toString()));
		}
	}

	/**
	 * 获取控件类型
	 * 
	 * @param el
	 * @param jsonObject
	 * @return 控件类型值
	 */
	private static void handControlType(Element el, JSONObject jsonObject,
			FormField formField) {
		Short controlType = controlTypeMap.get("textfield");
		String clsName = el.attr("class").toLowerCase();

		if (controlTypeMap.containsKey(clsName)) {
			controlType = controlTypeMap.get(clsName);
		}
		if (clsName.equals("userselector") || clsName.equals("roleselector")
				|| clsName.equals("positionselector")
				|| clsName.equals("depselector")) {
			// 是否单选
			if (jsonObject.containsKey("isSingle")) {
				if (jsonObject.getInt("isSingle") == 1)
					controlType = (short) (controlType + 1);
			}
		}
		// 设置字段控件类型
		formField.setControlType(controlType);
	}

	/**
	 * 
	 * 处理字段类型。
	 * 
	 * @param dbType
	 *            数据格式 类似 {type:'varchar',length:20,}
	 * @param formField
	 *            字段
	 * @param result
	 *            处理结果
	 */
	private static void handFieldType(JSONObject dbType, FormField formField,
			ParseReult result) {
		if (!dbType.containsKey("type")) {
			result.addError("字段:" + formField.getFieldName() + ","
					+ formField.getFieldLabel() + ",没有设置数据类型!");
			return;
		}
		// type
		String type = dbType.getString("type");
		if (StringUtil.isEmpty(type)) {
			result.addError("字段:" + formField.getFieldName() + ","
					+ formField.getFieldLabel() + ",没有设置数据类型!");
			return;
		}
		if (!isValidType(type)) {
			result.addError("字段:" + formField.getFieldName() + ","
					+ formField.getFieldLabel() + ",数据类型设置错误:" + type);
			return;
		}
		// 设置数据库的类型，长度
		formField.setFieldSize(0);
		formField.setDecimalLen(0);
		formField.setFieldType(type);
		formField.setShowFormat(null);
		if ("varchar".equals(type)) {
			if (!dbType.containsKey("length")) {
				result.addError("字段:" + formField.getFieldName() + ","
						+ formField.getFieldLabel() + ",数据类型(“文本”)长度未设置。");
				return;
			}
			// length
			int length = dbType.getInt("length");
			formField.setFieldSize(length);

		} else if ("number".equals(type)) {// 设置数字
			if (!dbType.containsKey("intLen")) {
				result.addError("字段:" + formField.getFieldName() + ","
						+ formField.getFieldLabel() + ",数据类型(“数字”)数据长度未设置。");
				return;
			}
			int intLen = dbType.getInt("intLen");
			int decimalLen = 0;
			if (dbType.containsKey("decimalLen")) {
				decimalLen = dbType.getInt("decimalLen");
			}

			formField.setFieldSize(intLen);
			formField.setDecimalLen(decimalLen);
		} else if ("date".equals(type)) {// 设置日期
			// 设置日期格式
			setDateFormat(dbType, formField);
		}

	}

	/**
	 * 校验字段类型是否有效。
	 * 
	 * @param type
	 *            varchar||number||date||clob
	 * @return
	 */
	private static boolean isValidType(String type) {
		if (type.equals("varchar") || type.equals("number")
				|| type.equals("date") || type.equals("clob")) {
			return true;
		}
		return false;
	}

	/**
	 * 时间格式的处理。
	 * 
	 * @param dbType
	 * @param formField
	 */
	private static void setDateFormat(JSONObject dbType, FormField formField) {
		JSONObject jsonObj = JSONObject.fromObject("{}");
		// 格式
		String format = (String) dbType.get("format");
		if (format == null) {
			jsonObj.element("format", "yyyy-MM-dd");
		} else {
			jsonObj.element("format", format);
		}
		// 显示当前时间
		Object displayDate = dbType.get("displayDate");
		if (displayDate == null) {
			jsonObj.element("displayDate", 0);
		} else {
			jsonObj.element("displayDate",
					Integer.parseInt(displayDate.toString()));
		}
		formField.setShowFormat(format);
	}

	// TODO 解析子表。
	/**
	 * 解析子表。
	 * 
	 * <pre>
	 * 1.解析&lt;div type='subtable' tablename='' comment=''>&lt;/div>
	 * 2.解析 formtype属性的元素。
	 * 	edit：页内编辑。
	 *  form：弹出窗口编辑。
	 * 3.解析formtype内的有external属性的元素。
	 * 	1.获取字段formField。
	 *  2.将formtype中的元素进行解析html元素。
	 *  	&lt;span name="editable-input" style="display:inline-block;" class="personpicker" 
	 *  	external="{name:&#39;username&#39;,comment:&#39;用户姓名&#39;,dbType:{type:&#39;varchar&#39;,length:&#39;50&#39;},isRequired:1,isList:1,isQuery:1,isFlowVar:1,valueFrom:{value:&#39;0&#39;,content:&#39;无&#39;},buttoncontent:&#39;选择用户&#39;,singleselect:1}">
	 *  	&lt;input type="text" />&lt;a href="javascript:;" class="link user">选择用户&lt;/a>&lt;/span>
	 *  	
	 *  	解析为：
	 *  	&lt;div>
	 *  	&lt;input name="s:表名:表字段ID" type="hidden" class="hidden" value="">
	 *  	&lt;input type="text" name='s:表名:表字段' />&lt;a href="javascript:;" name='s:表名:表字段'  class="link user">选择用户&lt;/a>
	 *  	&lt;/div>
	 *  3.将formtype复制一份作为循环列表。
	 *  	&lt;#if model.sub.子表名 != null&gt; &lt;#list model.sub.子表名.dataList as table&gt;
	 *  	&lt;tr formtype='newrow'>
	 *  	&lt;/tr>
	 *  	&lt;/#list> &lt;/#if&gt;
	 *  4.对新行的元素复制。
	 * </pre>
	 * 
	 * @param doc
	 * @return
	 */
	private static List<FormTable> parseSubTableHtml(Document doc,
			ParseReult result) {
		List<FormTable> subList = new ArrayList<FormTable>();
		Elements list = doc.select("div[type=subtable]");

		for (Iterator<Element> it = list.iterator(); it.hasNext();) {
			Element subTable = it.next();
			// 如果子表设置了external属性，则先删除external属性。
			if (subTable.hasAttr("external")) {
				subTable.removeAttr("external");
				subTable.removeClass("subtable");
			}

			FormTable table = new FormTable();
			// 设置子表。
			String tableKey = subTable.attr("tablekey").toLowerCase();
			if (StringUtil.isEmpty(tableKey)) {
				result.addError("有子表对象没有设置表名。");
				continue;
			}
			String tableName = subTable.attr("tablename");
			if (StringUtil.isEmpty(tableName)) {
				result.addError("有子表描述未设置。");
				continue;
			}
			table.setTableKey(tableKey);
			table.setTableName(tableName);
			table.setIsMain((short) 0);
			subList.add(table);

			// 设置子表权限。
			subTable.attr("right", "${service.getSubTablePermission('"
					+ tableKey + "', permission)}");
			// 查询编辑行
			Elements rows = subTable.select("[formtype=edit],[formtype=form]");
			if (rows.size() == 0) {
				logger.debug("no formtype row defined");
				result.addError("子表【" + tableKey + "】没有定义属性【formtype】。");
				continue;
			}
			// 原行
			Element row = rows.get(0);

			// 取得行编辑模式。
			// edit:页内编辑模式
			// form:弹窗编辑模式
			String mode = row.attr("formtype");

			// 行内编辑模式
			if (FormDef.EDIT_INLINE.equals(mode)) {
				parseSubTableEditField(doc, row, table, result);
			}

			// 新行
			Element newRow = row.clone().attr("formtype", "newrow");

			row.after(newRow);

			newRow.before("&lt;#if model.sub." + tableKey
					+ " != null&gt; &lt;#list model.sub." + tableKey
					+ ".dataList as table&gt;");
			newRow.after("&lt;/#list> &lt;/#if&gt;");

			if (FormDef.EDIT_INLINE.equals(mode)) {
				parseSubTableEditField(newRow);
			}
			// 弹出窗口
			else if (FormDef.EDIT_FORM.equals(mode)) {
				Elements windowRows = subTable.select("[formtype=window]");
				if (windowRows.size() != 1) {
					logger.debug("window mode hasn't window defined");
					result.addError("在弹出窗口模式下，子表【" + tableKey
							+ "】没有设置window属性的对象。");
					continue;
				}
				Element window = windowRows.get(0);
				parseSubTableFormField(doc, row, newRow, window, table, result);
			}
		}
		return subList;
	}

	/**
	 * 解析行内模式。
	 * 
	 * <pre>
	 * 1.解析字段。
	 * 2.将控件解析符合自定义表单的模式。
	 * 3.删除&lt;span name="editable-input>&lt;/span>
	 * </pre>
	 * 
	 * @param doc
	 * @param newRow
	 * @param table
	 * @param result
	 */
	private static void parseSubTableEditField(Document doc, Element newRow,
			FormTable table, ParseReult result) {
		Elements fields = newRow.select("[external]");
		String tableKey = table.getTableKey();
		String tableName = table.getTableName();
		if (fields.size() == 0) {
			result.addError("子表【" + tableKey + ","
					+ (tableName.equals("") ? "子表" : tableName) + "】尚未定义任何字段");
			return;
		}
		for (Iterator<Element> it = fields.iterator(); it.hasNext();) {
			Element el = it.next();
			// 解析字段。
			FormField formField = parseExternal(el, result);

			boolean rtn = table.addField(formField);

			String fieldName = formField.getFieldName();

			if (!rtn) {
				result.addError("表单中子表:【" + tableKey + "】，字段:【" + fieldName
						+ "】重复!");
				continue;
			}

			String controlName = "s:" + tableKey + ":" + fieldName;
			parseChilds(doc, el, controlName, formField);
			// 删除父节点
			removeWrap(el);
		}
	}

	/**
	 * 处理行内编辑情况。
	 * 
	 * @param newRow
	 */
	private static void parseSubTableEditField(Element newRow) {
		Elements fields = newRow.select("[name^=s:]");
		for (Iterator<Element> it = fields.iterator(); it.hasNext();) {
			Element el = it.next();
			handSubFieldValuePermission(el);
		}
	}

	/**
	 * 处理子表字段赋值和授权。
	 * 
	 * @param el
	 */
	private static void handSubFieldValuePermission(Element el) {
		String nodeName = el.nodeName();
		String name = el.attr("name");
		String type = el.attr("type").toLowerCase();
		// 获取字段名
		String fieldName = name.replaceAll("^.*:", "").toLowerCase();
		// 文本框
		if ("textarea".equals(nodeName)) {
			el.append("${table." + fieldName + "}");
		}
		// 复选框和radio
		else if ("checkbox".equals(type) || "radio".equals(type)) {
			el.attr("chk", "1");
			String value = el.attr("value");
			el.before("${service.getRdoChkBox('" + fieldName + "', '"
					+ el.toString() + "','" + value + "', table)}");
			el.remove();
		}
		// input表单和下拉框
		else if ("select".equals(nodeName) || "input".equals(nodeName)) {
			el.attr("value", "${table." + fieldName + "}");
		}
	}

	/**
	 * 解析弹出窗口。
	 * 
	 * @param doc
	 *            文档
	 * @param newRow
	 *            新行
	 * @param window
	 *            window窗口对象
	 * @param table
	 *            自定义表
	 */
	private static void parseSubTableFormField(Document doc, Element row,
			Element newRow, Element window, FormTable table, ParseReult result) {
		String tableKey = table.getTableKey();
		String tableName = table.getTableKey();

		Elements rowFields = row.select("[fieldname]");
		if (rowFields.size() == 0) {
			result.addError("表:" + tableKey + "," + tableName
					+ ",弹窗编辑模式，显示行没有定义任何字段");
			return;
		}
		// 原行
		for (Iterator<Element> it = rowFields.iterator(); it.hasNext();) {
			Element el = it.next();
			String fieldname = el.attr("fieldname").toLowerCase();
			// 修改fieldname。
			el.attr("fieldname", "s:" + tableKey + ":" + fieldname);
		}
		// 新行
		Elements newRowfields = newRow.select("[fieldname]");
		for (Iterator<Element> it = newRowfields.iterator(); it.hasNext();) {
			Element el = it.next();
			String fieldname = el.attr("fieldname").toLowerCase();
			// 修改fieldname。
			el.attr("fieldname", "s:" + tableKey + ":" + fieldname);
			// <td fieldname="name">${table.name}</td>
			// 示例添加 ${table.name}
			el.append("${table." + fieldname + "}");
		}
		// 对form表单进行遍历，在newrow添加隐藏域。
		Elements windowFields = window.select("[external]");

		if (rowFields.size() == 0) {
			result.addError("表:" + tableKey + "," + tableName
					+ ",弹窗编辑模式，窗口没有定义任何字段");
			return;
		}
		for (Iterator<Element> it = windowFields.iterator(); it.hasNext();) {
			Element el = it.next();

			// 解析字段。
			FormField formField = parseExternal(el, result);
			if (formField == null) {
				continue;
			}
			boolean rtn = table.addField(formField);

			String fieldName = formField.getFieldName();

			if (!rtn) {
				result.addError("表单中子表表:【" + tableKey + "】，字段:【" + fieldName
						+ "】重复!");
				continue;
			}

			// 获取字段名
			// 在newrow中添加隐藏字段<input type="hidden" name="s:表名:字段名"
			// value="${table.字段名}"/>

			String name = "s:" + tableKey + ":" + fieldName;
			Element appendTag = doc.createElement("input")
					.attr("type", "hidden").attr("name", name)
					.attr("value", "${table." + fieldName + "}");
			newRow.children().last().after(appendTag);

			parseChilds(doc, el, name, formField);
			// 删除span
			removeWrap(el);
		}

	}
}
