package com.htsoft.oa.service.cxf.impl;

import java.util.List;

import javax.annotation.Resource;
import javax.jws.WebService;

import com.htsoft.oa.model.admin.Regulation;
import com.htsoft.oa.service.admin.RegulationService;
import com.htsoft.oa.service.cxf.RegService;

@WebService
public class RegServiceImpl implements RegService{
	@Resource
	RegulationService regulationService;
	
	@Override
	public  List<Regulation> getAll() {
		return regulationService.getAll();
	}
}
