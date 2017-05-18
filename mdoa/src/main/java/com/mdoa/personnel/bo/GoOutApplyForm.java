package com.mdoa.personnel.bo;

import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

public class GoOutApplyForm extends BaseModel{
	
	private String goOutApplyId;
	private String goOutStreamId;
	private String goOutUserId;// 外出人id
	private String goOutUserName;
	private String reason;
	private Date startTime;
	private String startTimeStr;
	private Date endTime;
	private String endTimeStr;
	private String examineUserId;
	private String examineUserName;
	private String streamType;
	private String backFlag;
	private String examineStatus;
	
	
	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		startTimeStr = DateUtil.dateToStr(startTime);
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
		startTime = DateUtil.strToDate(startTimeStr);
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		endTimeStr = DateUtil.dateToStr(endTime);
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
		endTime = DateUtil.strToDate(endTimeStr);
	}

	public String getGoOutApplyId() {
		return goOutApplyId;
	}

	public void setGoOutApplyId(String goOutApplyId) {
		this.goOutApplyId = goOutApplyId;
	}

	public String getGoOutUserId() {
		return goOutUserId;
	}

	public void setGoOutUserId(String goOutUserId) {
		this.goOutUserId = goOutUserId;
	}

	public String getGoOutUserName() {
		return goOutUserName;
	}

	public void setGoOutUserName(String goOutUserName) {
		this.goOutUserName = goOutUserName;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getExamineUserId() {
		return examineUserId;
	}

	public void setExamineUserId(String examineUserId) {
		this.examineUserId = examineUserId;
	}

	public String getExamineUserName() {
		return examineUserName;
	}

	public void setExamineUserName(String examineUserName) {
		this.examineUserName = examineUserName;
	}

	public String getStreamType() {
		return streamType;
	}

	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}

	public String getBackFlag() {
		return backFlag;
	}

	public void setBackFlag(String backFlag) {
		this.backFlag = backFlag;
	}

	public String getExamineStatus() {
		return examineStatus;
	}

	public void setExamineStatus(String examineStatus) {
		this.examineStatus = examineStatus;
	}

	public String getGoOutStreamId() {
		return goOutStreamId;
	}

	public void setGoOutStreamId(String goOutStreamId) {
		this.goOutStreamId = goOutStreamId;
	}
	
	
}
