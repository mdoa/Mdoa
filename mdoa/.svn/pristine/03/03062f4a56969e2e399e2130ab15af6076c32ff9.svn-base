package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class WelfareStream {
	
	private String welfareStreamId;//福利申请流id
	private String welfareId;//所申请的福利id
	private String examineUserId;//审核人id
	private String examineUserName;//审核人姓名
	private String examineIdea;//审批意见
	private String examineStatus;//当前审批状态 1审批中2撤回3通过4驳回
	private String nextExamineUserName;//下一级审批人姓名
	private Date examineTime;//审批时间
	private String examineTimeStr;
	private Date createTime;
	private String createTimeStr;
	private String createUserId;
	private String createUserName;
	private Date updateTime;
	private String updateTimeStr;
	private String updateUserId;
	private String updateUserName;
	private String streamType;//流类别1审批 2备案
	private String aliveFlag;
	
	public String getWelfareId() {
		return welfareId;
	}
	public void setWelfareId(String welfareId) {
		this.welfareId = welfareId;
	}
	public String getNextExamineUserName() {
		return nextExamineUserName;
	}
	public void setNextExamineUserName(String nextExamineUserName) {
		this.nextExamineUserName = nextExamineUserName;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public String getWelfareStreamId() {
		return welfareStreamId;
	}
	public void setWelfareStreamId(String welfereStreamId) {
		this.welfareStreamId = welfereStreamId;
	}
	public String getWalfareId() {
		return welfareId;
	}
	public void setWalfareId(String welfareId) {
		this.welfareId = welfareId;
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
	public String getExamineIdea() {
		return examineIdea;
	}
	public void setExamineIdea(String examineIdea) {
		this.examineIdea = examineIdea;
	}
	public String getExamineStatus() {
		return examineStatus;
	}
	public void setExamineStatus(String examineStatus) {
		this.examineStatus = examineStatus;
	}
	public String getnextExamineUserName() {
		return nextExamineUserName;
	}
	public void setnextExamineUserName(String nextExamineUserName) {
		this.nextExamineUserName = nextExamineUserName;
	}
	public String getStreamType() {
		return streamType;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	public Date getExamineTime() {
		return examineTime;
	}
	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		examineTimeStr=sdf.format(examineTime);
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		createTimeStr=sdf.format(createTime);
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		updateTimeStr=sdf.format(updateTime);
	}
	public String getExamineTimeStr() {
		return examineTimeStr;
	}
	public void setExamineTimeStr(String examineTimeStr) {
		this.examineTimeStr = examineTimeStr;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			examineTime=sdf.parse(examineTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			createTime=sdf.parse(createTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			updateTime=sdf.parse(updateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
}
