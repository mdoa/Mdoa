package com.htsoft.oa.service.flow.impl;

/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
 */
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.BeanUtil;
import com.htsoft.oa.dao.flow.FormDefMappingDao;
import com.htsoft.oa.model.flow.FormDefMapping;
import com.htsoft.oa.service.flow.FormDefMappingService;

public class FormDefMappingServiceImpl extends BaseServiceImpl<FormDefMapping>
		implements FormDefMappingService {

	private FormDefMappingDao dao;

	public FormDefMappingServiceImpl(FormDefMappingDao dao) {
		super(dao);
		this.dao = dao;
	}

	/**
	 * 按jbpm流程发布id取得表单映射
	 * 
	 * @param deployId
	 * @return
	 */
	public FormDefMapping getByDeployId(String deployId) {
		return dao.getByDeployId(deployId);
	}

	// 根据defId查询是否已经设置表单数据
	@Override
	public FormDefMapping getByDefId(Long defId) {
		return dao.getByDefId(defId);
	}

	@Override
	public boolean formDefHadMapping(Long formDefId) {
		List<FormDefMapping> mps=dao.getByFormDef(formDefId);
		if(mps.size()>0){
			return true;
		}
		return false;
	}
	
	public void copyNewConfig(String oldDeployId,String newDeployId,Integer versionNo){
		FormDefMapping formDefMap=dao.getByDeployId(oldDeployId);
		if(formDefMap!=null){
			FormDefMapping newDefMapping=new FormDefMapping();
			try{
				BeanUtil.copyNotNullProperties(newDefMapping, formDefMap);
				newDefMapping.setMappingId(null);
				newDefMapping.setDeployId(newDeployId);
				newDefMapping.setVersionNo(versionNo);
				newDefMapping.setFieldRightss(null);
	    		dao.save(newDefMapping);
			}catch(Exception ex){
				logger.error(ex);
			}
		}
	}
}