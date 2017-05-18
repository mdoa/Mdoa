package com.mdoa.personnel.dao;

public interface ClockDao {
	
	/**
	 * 对前一天打卡记录处理迟到早退标志位
	 */
	void dealToday();
	
	/**
	 * 对前一天打卡记录处理leave_flag标志位
	 */
	void dealTodayAgain();
	
	/**
	 * 从today表转存记录进record表
	 */
	void saveRecordFromToday();
	
	/**
	 * 删除前一天记录
	 */
	void truncateToday();
	
	/**
	 * 插入新的today预备记录
	 */
	void insertPreToday();
	
	


}
