package com.htsoft.oa.service.flow.impl;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;

import com.google.gson.Gson;
import com.htsoft.core.service.DynamicService;
import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.core.util.StringUtil;
import com.htsoft.oa.dao.flow.FormDefDao;
import com.htsoft.oa.model.flow.FieldRights;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.model.flow.FormField;
import com.htsoft.oa.model.flow.FormTable;
import com.htsoft.oa.service.flow.FormDefService;
import com.htsoft.oa.service.flow.FormFieldService;
import com.htsoft.oa.service.flow.FormTableService;

public class FormDefServiceImpl extends BaseServiceImpl<FormDef> implements
		FormDefService {
	private FormDefDao dao;
	@Resource
	private FormTableService formTableService;
	@Resource
	private FormFieldService formFieldService;

	public FormDefServiceImpl(FormDefDao dao) {
		super(dao);
		this.dao = dao;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * com.htsoft.oa.service.flow.FormDefService#getByDeployId(java.lang.String)
	 */
	public List<FormDef> getByDeployId(String deployId) {
		return dao.getByDeployId(deployId);
	}

	/**
	 * 按流程定义ID及任务名称查找对应的表单定义
	 * 
	 * @param deployId
	 * @param activityName
	 * @return
	 */
	public FormDef getByDeployIdActivityName(String deployId,
			String activityName) {
		return dao.getByDeployIdActivityName(deployId, activityName);
	}

	@Override
	public FormDef saveFormDef(FormDef formDef, Map<FormTable, String> datas) {
		if (formDef.getFormDefId() == null) {
			this.save(formDef);
		} else {
			FormDef orgFormDef = dao.get(formDef.getFormDefId());
			try {
				BeanUtil.copyNotNullProperties(orgFormDef, formDef);
				formDef = dao.save(orgFormDef);
			} catch (Exception ex) {
				logger.error(ex.getMessage());
			}
		}

		Iterator<Map.Entry<FormTable, String>> it = datas.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<FormTable, String> obj = (Map.Entry<FormTable, String>) it
					.next();
			FormTable formTable = obj.getKey();
			String data = obj.getValue();
			if (StringUtils.isNotEmpty(data)) {
				Gson gson = new Gson();
				FormField[] formFields = gson.fromJson(data, FormField[].class);
				Set<FormField> fieldss = new HashSet<FormField>();
				for (FormField field : formFields) {
					if (field.getFieldId() == null) {
						fieldss.add(field);
					} else {
						FormField orgForm = formFieldService.get(field
								.getFieldId());
						Set<FieldRights> rights = orgForm.getFieldRightss();
						try {
							BeanUtil.copyNotNullProperties(orgForm, field);
							orgForm.setFieldRightss(rights);
							fieldss.add(orgForm);
							// field=formFieldService.save(orgForm);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}

				if (formTable.getTableId() == null) {
					formTable.setFormDef(formDef);
					formTable.setFormFields(fieldss);
					formTable = formTableService.save(formTable);
				} else {
					FormTable orgFormTable = formTableService.get(formTable
							.getTableId());
					Set<FormField> fields = orgFormTable.getFormFields();
					try {
						BeanUtil.copyNotNullProperties(orgFormTable, formTable);
						String formTableName=formDef.getFormTitle();
						// 保留fields里有fieldss相同的字段 旧的字段
						// fields.retainAll(fieldss);
						// //未添加的字段 fieldss
						// fieldss.removeAll(fields);
						//
						// fields.addAll(fieldss);
						// //把删除的字段
						fields.removeAll(fieldss);
						for (FormField field : fields) {
							// 对已经删除的字段进行标识一下
							field.setStatus(FormField.STATUS_DEL);
						}
						fields.addAll(fieldss);

						orgFormTable.setFormFields(fields);
						formTable = formTableService.save(orgFormTable);
					} catch (Exception ex) {
						logger.error(ex.getMessage());
					}
				}

			}
		}
		return formDef;
	}

	/**
	 * 保存表单。
	 * 
	 * <pre>
	 * 	1.表单输入新创建的表单。
	 * 		1.保存表单。
	 * 		
	 *  2.表单未发布。
	 *  	1.保存表单。
	 *  	
	 *  3.表单已经发布的情况，表单已经发布，数据库表已经创建。
	 *  	1.保存表单。
	 *  	2.表单是否有其他的表单定义情况。
	 *  		1.相同的表不止对应一个表单的情况，对表做更新处理。
	 *  		2.没有数据的情况，表删除重建。
	 * </pre>
	 * 
	 * @param formdef
	 * @param formTable
	 * @throws Exception
	 */
	@Override
	public FormDef saveForm(FormDef formDef, FormTable formTable, short status)
			throws Exception {
		formDef.setStatus(status);
		if (BeanUtil.isEmpty(formDef.getFormDefId())) {// 新增 表单
			// 保存流程表单定义
			formDef = dao.save(formDef);
			// 发布状态
			if ((status == FormDef.HAS_Pub.shortValue())) {
				this.saveFormTable(formDef, formTable);
			}
		} else {// 编辑保存
			// 当前为发布已经发布。
			if (status == FormDef.HAS_Pub.shortValue()) {
				// 原来的表
				Set<FormTable> origTables = formDef.getFormTables();
				if (BeanUtil.isNotEmpty(origTables)) { // 存在表
					Set<FormTable> formTables = new HashSet<FormTable>();

					FormTable mainTable = formDef.getMainTable();
					List<FormTable> origSubTables = formDef.getSubTables();

					List<FormTable> subTableList = formTable.getSubTableList();
					// 保存主表
					mainTable = this.saveFormTable(mainTable, formTable);
					formTables.add(mainTable);
					// 保存从表
					this.saveSubTable(formTables, formDef, origSubTables,
							subTableList);

					// 保存表单定义
					FormDef orgFormDef = dao.get(formDef.getFormDefId());
					Set<FormTable> tables = orgFormDef.getFormTables();
					tables.retainAll(formTables);
					BeanUtil.copyNotNullProperties(orgFormDef, formDef);
					orgFormDef.setFormTables(tables);
					dao.save(orgFormDef);
				} else {
					// 保存流程表单定义
					formDef = dao.save(formDef);
					this.saveFormTable(formDef, formTable);
				}
			} else {
				formDef.setIsGen(FormDef.NOT_GEN);
				// 保存流程表单定义
				formDef = dao.save(formDef);
			}

		}

		return formDef;
	}

	/**
	 * 保存子表相关信息
	 * 
	 * @param result
	 *            当前表的集合
	 * @param formDef
	 *            当前的流程定义
	 * @param origSubTables
	 *            原来的子表集合
	 * @param curSubTables
	 *            现在的子表集合
	 * @return 当前表的集合
	 * @throws Exception
	 */
	private Set<FormTable> saveSubTable(Set<FormTable> result, FormDef formDef,
			List<FormTable> origSubTables, List<FormTable> curSubTables)
			throws Exception {
		Map<String, FormTable> origMap = new HashMap<String, FormTable>();
		Map<String, FormTable> curMap = new HashMap<String, FormTable>();

		// 原来的表
		putFormTable(origMap, origSubTables);

		// 现在设置的表
		putFormTable(curMap, curSubTables);

		for (FormTable table : curSubTables) {
			String tableKey = StringUtil.toLowerCase(table.getTableKey());
			if (origMap.containsKey(tableKey)) {
				// 更新原来的
				FormTable origTable = origMap.get(tableKey);
				table = this.saveFormTable(origTable, table);
			} else {
				table = this.saveFormTable(formDef, table,
						FormTable.NOT_MAIN_TABLE);
			}
			result.add(table);
		}

		// 标记删除多余的表
		for (FormTable table : origSubTables) {
			String tableKey = StringUtil.toLowerCase(table.getTableKey());
			if (!curMap.containsKey(tableKey)) {
				formTableService.remove(table);
			}
		}
		return result;

	}

	/**
	 * 设置表 的map
	 * 
	 * @param map
	 * @param formTables
	 *            表
	 * @return
	 */
	private Map<String, FormTable> putFormTable(Map<String, FormTable> map,
			List<FormTable> formTables) {
		for (FormTable table : formTables) {
			map.put(StringUtil.toLowerCase(table.getTableKey()), table);
		}
		return map;
	}

	/**
	 * 保存表及字段的数据
	 * 
	 * @param origTable
	 * @param curTable
	 * @return
	 * @throws Exception
	 */
	private FormTable saveFormTable(FormTable origTable, FormTable curTable)
			throws Exception {
		Set<FormField> destFields = this.caculateFields(
				origTable.getFormFields(), curTable.getFormFields());

		FormTable origFormTable = formTableService.get(origTable.getTableId());
		Set<FormField> fields = origFormTable.getFormFields();
		BeanUtil.copyNotNullProperties(origFormTable, curTable);
		// 把字段删除
		fields.removeAll(destFields);
		for (FormField field : fields) {
			// 对已经删除的字段进行标识一下
			field.setStatus(FormField.STATUS_DEL);
		}

		// 增加 回字段
		fields.addAll(destFields);
		origFormTable.setFormFields(fields);

		return formTableService.save(origFormTable);

	}

	/**
	 * 计算字段，这种情况用于在表字段有变化时获取变化的字段，包括添加的字段和删除的字段。
	 * 
	 * <pre>
	 * 需要注意的是：
	 * 	这种情况在表创建之后才会用到。
	 *  更新时：
	 *  数据库中已有的字段，字段名，字段类型不能修改。
	 *  可以添加字段。
	 *  返回值类型：Set&lt;FormField>
	 *  
	 *  获取更新后的字段：
	 *  Set&lt;FormField> result= caculateFields(origFields,curFields);
	 *  
	 * 
	 *  
	 *  更新列时将原来的origFields和curFields放到新列中。
	 * 
	 * </pre>
	 * 
	 * @param origFields
	 *            原来字段
	 * @param curFields
	 *            现在字段
	 * @return
	 * @throws Exception
	 */
	private Set<FormField> caculateFields(Set<FormField> origFields,
			Set<FormField> curFields) throws Exception {
		Map<String, FormField> curMap = new HashMap<String, FormField>();
		Map<String, FormField> orginMap = new HashMap<String, FormField>();

		Set<FormField> fields = new HashSet<FormField>();

		// 现在设置的字段
		this.putFormField(curMap, origFields);
		// 原来的字段
		this.putFormField(orginMap, origFields);

		// 整理出现有的字段
		for (FormField field : curFields) {
			String fieldName = StringUtil.toLowerCase(field.getFieldName());
			if (curMap.containsKey(fieldName)) {
				// 更新原来的
				FormField destField = curMap.get(fieldName);

				FormField orgField = formFieldService.get(destField
						.getFieldId());
				Set<FieldRights> rights = orgField.getFieldRightss();
				BeanUtil.copyNotNullProperties(orgField, field);
				orgField.setFieldRightss(rights);
				fields.add(orgField);
			} else {
				fields.add(field);
			}

		}

		// // 把删除的字段标记为删除，其它设置为0
		for (FormField field : fields) {
			String fieldName = StringUtil.toLowerCase(field.getFieldName());
			if (!orginMap.containsKey(fieldName)) {
				field.setStatus(FormField.STATUS_DEL);
			} else {
				field.setStatus(FormField.STATUS_NOTDEL);
			}
		}

		return fields;
	}

	/**
	 * 设置字段 的map
	 * 
	 * @param map
	 * @param fields
	 *            字段
	 * @return
	 */
	private Map<String, FormField> putFormField(Map<String, FormField> map,
			Set<FormField> fields) {
		for (FormField field : fields) {
			map.put(StringUtil.toLowerCase(field.getFieldName()), field);
		}
		return map;
	}

	/**
	 * 保存主表和相关子表
	 * 
	 * @param formDef
	 * @param formTable
	 */
	private void saveFormTable(FormDef formDef, FormTable formTable) {
		List<FormTable> subTableList = formTable.getSubTableList();
		// 保存主表
		this.saveFormTable(formDef, formTable, FormTable.MAIN_TABLE);
		// 保存子表
		for (FormTable subTable : subTableList) {
			this.saveFormTable(formDef, subTable, FormTable.NOT_MAIN_TABLE);
		}
	}

	/**
	 * 保存表的数据
	 * 
	 * @param formDef
	 *            表定义
	 * @param formTable
	 *            表
	 * @param isMain
	 *            是主表，还是从表
	 * @return
	 */
	private FormTable saveFormTable(FormDef formDef, FormTable formTable,
			short isMain) {
		Set<FormField> formFields = formTable.getFormFields();
		formTable.setFormFields(formFields);
		formTable.setIsMain(isMain);
		// 保存表 及字段
		formTable.setFormDef(formDef);

		return formTableService.save(formTable);
	}

	/**
	 * 检查是否能进行编辑
	 */
	@Override
	public boolean checkCanEditColumnNameTypeByFormDef(FormDef formDef) {
		FormTable formTable = formDef.getMainTable();
		if (BeanUtil.isEmpty(formTable))
			return true;

		// 生成实体
		//if (formDef.getIsGen().shortValue() == FormDef.HAS_GEN.shortValue())
		//	return false;

		try {
			DynamicService dynamicService = BeanUtil
					.getDynamicServiceBean(formTable.getEntityName());

			List<Object> list = dynamicService.getAll();
			if (BeanUtil.isNotEmpty(list)) // 有数据
				return false;
		} catch (Exception e) {
			// e.printStackTrace();
			return true;
		}
		// int formAmount= dao.getFormDefAmount(tableId);
		// if(formAmount>1){
		// return false;
		// }
		return true;
	}

}