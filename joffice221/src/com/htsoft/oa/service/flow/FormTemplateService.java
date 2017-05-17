package com.htsoft.oa.service.flow;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.model.flow.FormTemplate;

public interface FormTemplateService extends BaseService<FormTemplate>{
	/**
	 * 按映射取到所有的流程表单定义
	 * @param mappingId
	 * @return
	 */
	public List<FormTemplate> getByMappingId(Long mappingId);
	
	/**
	 * 为某个流程定义映射添加缺省的流程表单定义
	 * @param nodeNames
	 * @param fdm
	 */
	public void batchAddDefault(List<String>nodeNames,FormDefMapping fdm);
	
	/**
	 * 取得映射
	 * @param mappingId
	 * @param nodeName
	 * @return
	 */
	public FormTemplate getByMappingIdNodeName(Long mappingId,String nodeName);

	/**
	 * 保存映射formTemplates
	 * @param formTemplates
	 */
	public void saveFormTemplates(FormTemplate[] formTemplates);
}
