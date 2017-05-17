package com.htsoft.oa.service.personal;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.Date;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.personal.Duty;

public interface DutyService extends BaseService<Duty>{
	/**
	 * 检查当前这个时间段是否横跨于现有的该用户班制时间
	 * @param userId
	 * @param startTime
	 * @param endTime
	 * @return
	 */
	public boolean isExistDutyForUser(Long userId,Date startTime,Date endTime);
	
	/**
	 * 取当前用户的班制
	 * @param userId
	 * @return
	 */
	public Duty getCurUserDuty(Long userId);
	
	/**
	 * 根据systemId删除数据
	 * @param sysId
	 */
	public void delBySysId(Long sysId);
}


