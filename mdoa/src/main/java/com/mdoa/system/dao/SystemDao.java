package com.mdoa.system.dao;

import java.util.List;

import com.mdoa.system.model.SystemLog;

public interface SystemDao {

	
	/**
	 * 添加日志
	 * @param systemLog
	 * @return
	 */
	boolean addSystemLog(SystemLog systemLog);
	/**
	 * 查询日志
	 * @param systemLog
	 * @return
	 */
	List<SystemLog> querySystemLog(SystemLog systemLog);
	/**
	 * 删除日志
	 * @param systemLog
	 * @return
	 */
	boolean deleteSystemLog(SystemLog systemLog);

	
	
}
