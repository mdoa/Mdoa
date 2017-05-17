package com.htsoft.oa.service.admin.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.Date;
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.admin.CarApplyDao;
import com.htsoft.oa.model.admin.CarApply;
import com.htsoft.oa.service.admin.CarApplyService;

public class CarApplyServiceImpl extends BaseServiceImpl<CarApply> implements CarApplyService{
	private CarApplyDao dao;
	
	public CarApplyServiceImpl(CarApplyDao dao) {
		super(dao);
		this.dao=dao;
	}

	@Override
	public List<CarApply> findByCarIdAndStartEndTime(Long carId,
			Date startTime, Date endTime) {
		return dao.findByCarIdAndStartEndTime(carId, startTime, endTime);
	}

}