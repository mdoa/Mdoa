package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;


public class InviteStream extends BaseModel{
	private String inviteStreamId;//招聘申请流ID
	private String inviteId;//招聘ID
	private String examineUserId;//审批人ID
	private String examineUserName;//审批人
	private String examineIdea;//审批人建议
	private String examineStatus;//审批状态
	private String nextExamineUser;//下一个审批人
	private Date examineTime;//审批时间
	private String examineTimeStr;//String类型的审批时间
	private Date createTime;//创建时间
	private String createTimeStr;//String类型的创建时间
	private Date updateTime;//更新时间
	private String updateTimeStr;//String类型的更新时间
	private String streamType;//流类别  1审批 2备案
	private String aliveFlag;//有效标识
	
	public String getExamineTimeStr() {
		return examineTimeStr;
	}
	public void setExamineTimeStr(String examineTimeStr) {
		this.examineTimeStr = examineTimeStr;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.examineTime=simpleDateFormat.parse(examineTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.createTime=simpleDateFormat.parse(createTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.updateTime=simpleDateFormat.parse(updateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getInviteStreamId() {
		return inviteStreamId;
	}
	public void setInviteStreamId(String inviteStreamId) {
		this.inviteStreamId = inviteStreamId;
	}
	public String getInviteId() {
		return inviteId;
	}
	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
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
	public String getNextExamineUser() {
		return nextExamineUser;
	}
	public void setNextExamineUser(String nextExamineUser) {
		this.nextExamineUser = nextExamineUser;
	}
	public Date getExamineTime() {
		return examineTime;
	}
	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.examineTimeStr=simpleDateFormat.format(examineTime);
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createTimeStr=simpleDateFormat.format(createTime);
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.updateTimeStr=simpleDateFormat.format(updateTime);
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
	
	
}
