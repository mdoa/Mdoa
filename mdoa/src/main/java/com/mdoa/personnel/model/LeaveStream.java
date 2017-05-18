package com.mdoa.personnel.model;

import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

public class LeaveStream extends BaseModel{
	private String leaveStreamId;
	private String leaveApplyId;
	private String examineUseId;
	private String examineUserName;
	private String examineIdea;
	private String examinestatus;
	private String nextExamineUserName;
	private Date examineTime;
	private String examineTimeStr;
	private Date createTime;
	private String createTimeStr;
	private Date updateTime;
	private String updateTimeStr;
	private String streamType;
	private String aliveflag;
	
	public String getLeaveStreamId() {
		return leaveStreamId;
	}
	public void setLeaveStreamId(String leaveStreamId) {
		this.leaveStreamId = leaveStreamId;
	}
	public String getLeaveApplyId() {
		return leaveApplyId;
	}
	public void setLeaveApplyId(String leaveApplyId) {
		this.leaveApplyId = leaveApplyId;
	}
	public String getExamineUseId() {
		return examineUseId;
	}
	public void setExamineUseId(String examineUseId) {
		this.examineUseId = examineUseId;
	}
	public String getExamineUserName() {
		return examineUserName;
	}
	public void setExamineUserName(String examineUserName) {
		this.examineUserName = examineUserName;
	}
	public String getExamineIdea() {
		return examineIdea;
	}
	public void setExamineIdea(String examineIdea) {
		this.examineIdea = examineIdea;
	}
	public String getExaminestatus() {
		return examinestatus;
	}
	public void setExaminestatus(String examinestatus) {
		this.examinestatus = examinestatus;
	}
	public String getNextExamineUserName() {
		return nextExamineUserName;
	}
	public void setNextExamineUserName(String nextExamineUserName) {
		this.nextExamineUserName = nextExamineUserName;
	}
	public String getStreamType() {
		return streamType;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}
	public String getAliveflag() {
		return aliveflag;
	}
	public void setAliveflag(String aliveflag) {
		this.aliveflag = aliveflag;
	}
	public Date getExamineTime() {
		return examineTime;
	}
	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
		examineTimeStr=DateUtil.dateToStr(examineTime);
	}
	public String getExamineTimeStr() {
		return examineTimeStr;
	}
	public void setExamineTimeStr(String examineTimeStr) {
		this.examineTimeStr = examineTimeStr;
		examineTime=DateUtil.strToDate(examineTimeStr);
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		createTimeStr=DateUtil.dateToStr(createTime);
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		createTime=DateUtil.strToDate(createTimeStr);
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		updateTimeStr=DateUtil.dateToStr(updateTime);
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		updateTime = DateUtil.strToDate(updateTimeStr);
	}
	
}
