package com.htsoft.oa.service.personal.impl;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.Date;
import java.util.List;

import com.htsoft.core.service.impl.BaseServiceImpl;
import com.htsoft.oa.dao.personal.DutyDao;
import com.htsoft.oa.model.personal.Duty;
import com.htsoft.oa.service.personal.DutyService;

public class DutyServiceImpl extends BaseServiceImpl<Duty> implements DutyService{
	private DutyDao dao;
	
	public DutyServiceImpl(DutyDao dao) {
		super(dao);
		this.dao=dao;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.htsoft.oa.service.personal.DutyService#isExistDutyForUser(java.lang.Long, java.util.Date, java.util.Date)
	 */
	public boolean isExistDutyForUser(Long userId, Date startTime, Date endTime) {
		List dutyList=dao.getUserDutyByTime(userId, startTime, endTime);
		if(dutyList.size()>0) return true;
		return false;
	}
	
	/**
	 * 取当前用户的班制
	 * @param userId
	 * @return
	 */
	public Duty getCurUserDuty(Long userId){
		List<Duty> dutyList=dao.getCurUserDuty(userId);
		if(dutyList.size()>0){
			return dutyList.get(0);
		}
		return null;
	}
	
	/**
	 * 根据systemId删除数据
	 * @param sysId
	 */
	public void delBySysId(Long sysId)
	{
		dao.delBySysId(sysId);
	}
	
}