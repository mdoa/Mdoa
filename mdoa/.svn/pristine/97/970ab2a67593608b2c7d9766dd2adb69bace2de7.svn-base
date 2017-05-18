package com.mdoa.personnel.service;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.mdoa.base.service.BaseService;
import com.mdoa.personnel.dao.AttendanceDao;
import com.mdoa.personnel.dao.ClockDao;
import com.mdoa.personnel.model.AttendanceGroup;

@Service
@Transactional
public class ClockService extends BaseService{
	
	@Autowired
	private ClockDao clockDao;
	@Autowired
	private AttendanceDao attendanceDao;
	
	/**
	 * 定时任务 处理前一天打卡记录 并插入新的预备记录
	 */
	public void autoDeal() {
		//对前一天打卡记录处理迟到早退标志位
		clockDao.dealToday();
		//对前一天打卡记录处理leave_flag标志位
		clockDao.dealTodayAgain();
		//将前一天打卡记录转存入记录表
		clockDao.saveRecordFromToday();
		//将前一天记录删除
		clockDao.truncateToday();
		//当考勤组没有班次时按默认时间插入班次
		
		//插入新的预备记录
		clockDao.insertPreToday();
	}
	
	public void test(){
		System.out.println("2222222222222222222222222222222");
	}
	
	
}
