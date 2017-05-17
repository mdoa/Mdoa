package com.htsoft.oa.service.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
 */
import java.util.Date;
import java.util.List;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.admin.CarApply;

public interface CarApplyService extends BaseService<CarApply> {

	/**
	 * 根据车辆编号[carId],申请的开始时间[startTime],申请的结束时间[endTime]查询满足添加的数据
	 * 
	 * @param carId
	 *            车辆编号
	 * @param startTime
	 *            开始时间
	 * @param endTime
	 *            结束时间
	 * @return List<CarApply>
	 */
	List<CarApply> findByCarIdAndStartEndTime(Long carId, Date startTime,
			Date endTime);
}
