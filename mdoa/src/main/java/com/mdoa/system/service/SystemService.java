package com.mdoa.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.mdoa.system.dao.SystemDao;
import com.mdoa.system.model.SystemLog;
import com.mdoa.util.PageModel;
import com.mdoa.util.StringUtil;

@Service
public class SystemService{

	@Autowired
	private SystemDao systemDao;

	/**
	 * 添加日志
	 * @param systemLog
	 */
	public void addSystemLog(SystemLog systemLog) {
		if (!systemDao.addSystemLog(systemLog)) 
			throw new RuntimeException("添加日志失败");
	}

	/**
	 * 查询日志
	 * @param systemLog
	 * @return
	 */
	public PageModel<SystemLog> querySystemLog(SystemLog systemLog) {
		if (!StringUtil.isEmpty(systemLog.getOperaUserName())) 
			systemLog.setOperaUserName(systemLog.getOperaUserName()+"%");
		if (!StringUtil.isEmpty(systemLog.getOperaPanel())) 
			systemLog.setOperaPanel(systemLog.getOperaPanel()+"%");
		if (!StringUtil.isEmpty(systemLog.getOperaTab())) 
			systemLog.setOperaTab(systemLog.getOperaTab()+"%");
		PageHelper.startPage(systemLog.getPage(), systemLog.getRows());
		List<SystemLog> list = systemDao.querySystemLog(systemLog);
		PageModel<SystemLog> pageInfo = new PageModel<>((Page<SystemLog>) list);
		return pageInfo;
	}
	/**
	 * 删除日志
	 * @param systemLog
	 */
	public void deleteSystemLog(SystemLog systemLog) {
		if (!systemDao.deleteSystemLog(systemLog)) 
			throw new RuntimeException("删除日志失败");
	}
	
	
}
