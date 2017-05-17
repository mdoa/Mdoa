package com.htsoft.oa.service.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FormField;

public interface FormFieldService extends BaseService<FormField>{
	
	/**
	 * 取某个表的标题字段
	 * @param tableId
	 * @param isFlowTitle
	 * @return
	 */
	public FormField find(Long tableId,Short isFlowTitle);
	
	/**
	 * 取某个表的全部字段
	 * @param tableId
	 * @return
	 */
	public List<FormField> getFieldFromTableId(Long tableId);
}


