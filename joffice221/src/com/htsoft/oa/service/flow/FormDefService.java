package com.htsoft.oa.service.flow;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
*/
import java.util.List;
import java.util.Map;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FormDef;
import com.htsoft.oa.model.flow.FormTable;

public interface FormDefService extends BaseService<FormDef>{
	/**
	 * 取某流程对应的所有任务表单定义
	 * @param deployId
	 * @return
	 */
	public List<FormDef> getByDeployId(String deployId);
	
	/**
	 * 按流程定义ID及任务名称查找对应的表单定义
	 * @param deployId
	 * @param activityName
	 * @return
	 */
	public FormDef getByDeployIdActivityName(String deployId,String activityName);
	
	public FormDef saveFormDef(FormDef formDef,Map<FormTable,String> datas);

	/**
	 * 保存表单
	 * @param formDef 表单定义
	 * @param formTable 表
	 * @param status 是否发布
	 * @return
	 */
	public FormDef saveForm(FormDef formDef, FormTable formTable, short status) throws Exception;

	/**
	 * 检查是否能进行编辑
	 * @param FormDef
	 * @return
	 */
	public boolean checkCanEditColumnNameTypeByFormDef(FormDef formDef);
}


