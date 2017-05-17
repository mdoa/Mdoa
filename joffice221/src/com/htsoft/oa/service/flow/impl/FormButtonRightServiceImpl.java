package com.htsoft.oa.service.flow.impl;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.core.util.ContextUtil;
import com.htsoft.oa.dao.flow.FormButtonRightDao;
import com.htsoft.oa.model.flow.FormButtonRight;
import com.htsoft.oa.model.flow.ProUserSet;
import com.htsoft.oa.service.flow.FormButtonRightService;
import com.htsoft.oa.service.flow.JbpmService;

public class FormButtonRightServiceImpl extends BaseServiceImpl<FormButtonRight> implements FormButtonRightService{
	@SuppressWarnings("unused")
	private FormButtonRightDao dao;
	@Resource
	private JbpmService jbpmService;
	
	public FormButtonRightServiceImpl(FormButtonRightDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	@Override
	public List<FormButtonRight> getByMappingTableTaskName(Long mappingId,Long tableId, String taskName,Short buttonType) {
		return dao.getByMappingTableTaskName(mappingId, tableId, taskName,buttonType);
	}
	
	@Override
	public List<FormButtonRight> getByMappingTaskName(Long mappingId, String taskName) {
		return dao.getByMappingTaskName(mappingId, taskName);
	}
	@Override
	public Boolean checkByRightWidthCurrUser(Long rightId){
		FormButtonRight right=dao.get(rightId);
		if (right==null) return false;
		Short userType=right.getUserType();
		if(0==userType.shortValue()){
			if(FormButtonRight.HIDE_RIGHT.equals(right.getButtonRight())) 
				return false;
			else 
				return true;
		}
		ProUserSet proUserSet=new ProUserSet();
		proUserSet.setUids(right.getUids());
		Long currentUId=ContextUtil.getCurrentUserId();
		Set<String> uids=jbpmService.getUsersByUserType(userType,currentUId,proUserSet);
		if(uids!=null && uids.contains(currentUId.toString())){
			if(FormButtonRight.HIDE_RIGHT.equals(right.getButtonRight())) 
				return false;
			else 
				return true;
		}
		return false;
	}

}