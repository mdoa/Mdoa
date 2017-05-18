package com.mdoa.personnel.bo;

public class ClockMonthBalanceForm {
	
	private String userId;
	private String userName;
	private Integer attendanceDays;//出勤天数
	private Integer restDays;//休息天数
	private Integer lateTimes;//迟到次数
	private Integer earlyTimes;//早退次数
	private Integer missTimes;//缺卡次数
	private Integer absentDays;//旷工天数
	private Integer fieldTimes;//外勤次数
	private Double outHour;//外出时长
	private Double businessLeaveDays;//事假天数
	private Double sickLeaveDays;//病假天数
	private Double averWorkingHours;//本月平均工时
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Integer getAttendanceDays() {
		return attendanceDays;
	}
	public void setAttendanceDays(Integer attendanceDays) {
		this.attendanceDays = attendanceDays;
	}
	public Integer getRestDays() {
		return restDays;
	}
	public void setRestDays(Integer restDays) {
		this.restDays = restDays;
	}
	public Integer getLateTimes() {
		return lateTimes;
	}
	public void setLateTimes(Integer lateTimes) {
		this.lateTimes = lateTimes;
	}
	public Integer getEarlyTimes() {
		return earlyTimes;
	}
	public void setEarlyTimes(Integer earlyTimes) {
		this.earlyTimes = earlyTimes;
	}
	public Integer getMissTimes() {
		return missTimes;
	}
	public void setMissTimes(Integer missTimes) {
		this.missTimes = missTimes;
	}
	public Integer getAbsentDays() {
		return absentDays;
	}
	public void setAbsentDays(Integer absentDays) {
		this.absentDays = absentDays;
	}
	public Integer getFieldTimes() {
		return fieldTimes;
	}
	public void setFieldTimes(Integer fieldTimes) {
		this.fieldTimes = fieldTimes;
	}
	public Double getOutHour() {
		return outHour;
	}
	public void setOutHour(Double outHour) {
		this.outHour = outHour;
	}
	public Double getBusinessLeaveDays() {
		return businessLeaveDays;
	}
	public void setBusinessLeaveDays(Double businessLeaveDays) {
		this.businessLeaveDays = businessLeaveDays;
	}
	public Double getSickLeaveDays() {
		return sickLeaveDays;
	}
	public void setSickLeaveDays(Double sickLeaveDays) {
		this.sickLeaveDays = sickLeaveDays;
	}
	public Double getAverWorkingHours() {
		return averWorkingHours;
	}
	public void setAverWorkingHours(Double averWorkingHours) {
		this.averWorkingHours = averWorkingHours;
	}
}
