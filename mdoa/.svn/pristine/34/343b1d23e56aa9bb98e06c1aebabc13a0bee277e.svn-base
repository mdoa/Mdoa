package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.util.DateUtil;

public class AttendanceToday {

	private String todayId;
	private String userId;
	private Date date;// 时间
	private String dateStr;
	private String onDutyTime;// 上班时间
	private String offDutyTime;// 00:00:00 下班时间
	private String clockInTime;// 上班打卡时间
	private String clockOutTime;// 00:00:00 下班打卡时间
	private String leaveFlag;// 请假状态 0正常 1病假 2事假 3外出 4休息 5放假
	private String beLateFlag;// 是否迟到
	private String leaveEarlyFlag;// 是否早退

	public String getOffDutyTime() {
		return offDutyTime;
	}

	public void setOffDutyTime(String offDutyTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			sdf.parse(offDutyTime);
		} catch (ParseException e) {
			e.printStackTrace();
			offDutyTime = null;
		} finally {
			this.offDutyTime = offDutyTime;
		}
	}

	public String getOnDutyTime() {
		return onDutyTime;
	}

	public void setOnDutyTime(String onDutyTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			sdf.parse(onDutyTime);
		} catch (ParseException e) {
			e.printStackTrace();
			onDutyTime = null;
		} finally {
			this.onDutyTime = onDutyTime;
		}
	}

	public String getTodayId() {
		return todayId;
	}

	public void setTodayId(String todayId) {
		this.todayId = todayId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
		this.dateStr = DateUtil.dateToStr(date, "yyyy-MM-dd");
	}

	public String getDateStr() {
		return dateStr;
	}

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
		this.date = DateUtil.strToDate(dateStr, "yyyy-MM-dd");
	}

	public String getClockInTime() {
		return clockInTime;
	}

	public void setRealOnTime(String clockInTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			sdf.parse(clockInTime);
		} catch (ParseException e) {
			e.printStackTrace();
			clockInTime = null;
		} finally {
			this.clockInTime = clockInTime;
		}
	}

	public String getClockOutTime() {
		return clockOutTime;
	}

	public void setRealOffTime(String clockOutTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			sdf.parse(clockOutTime);
		} catch (ParseException e) {
			e.printStackTrace();
			clockOutTime = null;
		} finally {
			this.clockOutTime = clockOutTime;
		}
	}

	public String getBeLateFlag() {
		return beLateFlag;
	}

	public void setBeLateFlag(String beLateFlag) {
		this.beLateFlag = beLateFlag;
	}

	public String getLeaveEarlyFlag() {
		return leaveEarlyFlag;
	}

	public void setLeaveEarlyFlag(String leaveEarlyFlag) {
		this.leaveEarlyFlag = leaveEarlyFlag;
	}

	public String getLeaveFlag() {
		return leaveFlag;
	}

	public void setLeaveFlag(String leaveFlag) {
		this.leaveFlag = leaveFlag;
	}

}
