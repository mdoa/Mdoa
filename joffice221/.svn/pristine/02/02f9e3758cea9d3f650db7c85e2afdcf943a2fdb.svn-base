package com.htsoft.oa.dao.admin.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.htsoft.core.dao.impl.BaseDaoImpl;
import com.htsoft.oa.dao.admin.CarApplyDao;
import com.htsoft.oa.model.admin.CarApply;

@SuppressWarnings("unchecked")
public class CarApplyDaoImpl extends BaseDaoImpl<CarApply> implements CarApplyDao{

	public CarApplyDaoImpl() {
		super(CarApply.class);
	}

	@Override
	public List<CarApply> findByCarIdAndStartEndTime(Long carId,
			Date startTime, Date endTime) {
		ArrayList<Object> params = new ArrayList<Object>();
		StringBuffer hql = new StringBuffer("select vo from CarApply vo ");
		hql.append("where vo.car.carId = ? ");
		params.add(carId);
	    hql.append("and ((vo.startTime < ? and vo.endTime > ?) "); 
	    hql.append("or (vo.startTime < ? and vo.endTime > ?) ");
	    hql.append("or (vo.startTime > ? and vo.endTime <?))");
		params.add(startTime);
		params.add(startTime);
		params.add(endTime);
		params.add(endTime);
		params.add(startTime);
		params.add(endTime);
		return findByHql(hql.toString(), params.toArray());
	}

}