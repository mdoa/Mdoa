package com.htsoft.oa.dao.personal;
/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
*/
import com.htsoft.core.dao.BaseDao;
import com.htsoft.oa.model.personal.DutyRegister;

/**
 * 
 * @author 
 *
 */
public interface DutyRegisterDao extends BaseDao<DutyRegister>{
	/**
	 * 查取当前用户当天上下班登记情况
	 * @param userId
	 * @param inOffFlag
	 * @param sectionId
	 * @return
	 */
	public DutyRegister getTodayUserRegister(Long userId,Short inOffFlag,Long sectionId);
}