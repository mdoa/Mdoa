package com.htsoft.oa.service.system;
/*
 *  杭州梦德软件有限公司 J.Office协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2010 GuangZhou HongTian Software Limited Company.
*/
import java.util.List;
import java.util.Set;

import com.htsoft.core.service.BaseService;
import com.htsoft.oa.model.system.AppUser;
import com.htsoft.oa.model.system.UserPosition;

public interface UserPositionService extends BaseService<UserPosition>{
	/**
	 * 查找某一用户的所有职位
	 * @param userId
	 * @return
	 */
	public List<UserPosition> getByUserPos(Long userId);
	
	/**
	 * 取得某个岗位的所有的人员
	 * 
	 * @param posId
	 * @return
	 */
	public List<Long> getUserIdsByPosId(Long posId);
	
	/**
	 * 删除UserPosition对象
	 * @param userId
	 * @param posId
	 * @return
	 */
	public void delCascade(Long userId, Long posId);
	
	/**
	 * 获取某个用户所有部门拥有某个岗位的员工列表 
	 * @param userId 指定的用户
	 * @param posId  指定的岗位
	 * @return
	 */
	public Set<AppUser> getSameDepUsersByUserIdPosId(Long userId,Long posId);
}


