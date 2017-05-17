package com.htsoft.oa.service.personal;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   -- http://www.Mendersoft.com
 *  2017/1/21
*/
import java.util.Date;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.personal.DutyRegister;
import com.htsoft.oa.model.system.AppUser;

public interface DutyRegisterService extends BaseService<DutyRegister>{
	/**
	 * 签到,签退
	 * @param sectionId 班次
	 * @param signInOff 1=signIn 2=signOff
	 * @param curUser 用户
	 * @param 登记时间
	 */
	public void signInOff(Long sectionId,Short signInOff,AppUser curUser,Date registerDate);
	
	/**
	 * 查取当前用户当天上下班登记情况
	 * @param userId
	 * @param inOffFlag
	 * @param sectionId
	 * @return
	 */
	public DutyRegister getTodayUserRegister(Long userId,Short inOffFlag,Long sectionId);
}


