package com.mdoa.personnel.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;


public class InviteApplyForm extends BaseModel{
	private String inviteStreamId;//招聘申请流ID
	private String examineUserId;//审批人ID
	private String examineUserName;//审批人
	private String examineIdea;//审批人建议
	private String examineStatus;//当前审批状态，1审批中2撤回3通过4驳回
	private String nextExamineUser;//下一个审批人ID
	private String nextExamineUserName;//下一个审批人的名字
	private Date examineTime;//审批时间
	private String examineTimeStr;//String类型的审批时间
	private Date createTime;//创建时间
	private String createTimeStr;//String类型的创建时间
	private Date updateTime;//更新时间
	private String updateTimeStr;//String类型的更新时间
	private String streamType;//流类别  1审批 2备案
	
	private String inviteId;//招聘ID
	private String planName;//招聘计划名
	private String applyUserId;//申请人ID
	private String applyUserName;//申请人
	private Date applyTime;//申请时间
	private String applyTimeStr;//String形式的申请时间
	private String text;//招聘描述
	private String reason;//招聘原因
	private Integer planCount;//计划人数
	private Integer reallyCount;//已招人数
	private String inviteStatus;//当前审批状态，1审批中2撤回3招聘中4驳回5招满
	private String recordUserId;//备案人ID
	private String recordUserName;//备案人
	private Date recordTime;//备案时间
	private String recordTimeStr;//String形式的备案时间
	private String createUserId;//创建者的ID
	private String createUserName;//创建者的名字
	private String updateUserId;//更新者的ID
	private String updateUserName;//更新者的名字
	private String aliveFlag;//有效标志位
	
	public String getNextExamineUserName() {
		return nextExamineUserName;
	}
	public void setNextExamineUserName(String nextExamineUserName) {
		this.nextExamineUserName = nextExamineUserName;
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	public String getExamineTimeStr() {
		return examineTimeStr;
	}
	public void setExamineTimeStr(String examinTimeStr) {
		this.examineTimeStr = examinTimeStr;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.examineTime=simpleDateFormat.parse(examinTimeStr);
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
	public String getApplyTimeStr() {
		return applyTimeStr;
	}
	public void setApplyTimeStr(String applyTimeStr) {
		this.applyTimeStr = applyTimeStr;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.applyTime=simpleDateFormat.parse(applyTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getRecordTimeStr() {
		return recordTimeStr;
	}
	public void setRecordTimeStr(String recordTimeStr) {
		this.recordTimeStr = recordTimeStr;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(recordTimeStr+"****************/////啦啦啦滚滚滚滚滚");
		try {
			this.recordTime=simpleDateFormat.parse(recordTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
	public Integer getReallyCount() {
		return reallyCount;
	}
	public void setReallyCount(Integer reallyCount) {
		this.reallyCount = reallyCount;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.recordTimeStr=simpleDateFormat.format(recordTime);
	}
	public String getInviteId() {
		return inviteId;
	}
	public void setInviteId(String inviteId) {
		this.inviteId = inviteId;
	}
	public String getPlanName() {
		return planName;
	}
	public void setPlanName(String planName) {
		this.planName = planName;
	}
	public String getApplyUserId() {
		return applyUserId;
	}
	public void setApplyUserId(String applyUserId) {
		this.applyUserId = applyUserId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Integer getPlanCount() {
		return planCount;
	}
	public void setPlanCount(Integer planCount) {
		this.planCount = planCount;
	}
	public String getApplyUserName() {
		return applyUserName;
	}
	public void setApplyUserName(String applyUserName) {
		this.applyUserName = applyUserName;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
		SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.applyTimeStr=simpleDateFormat.format(applyTime);
	}
	public String getInviteStatus() {
		return inviteStatus;
	}
	public void setInviteStatus(String inviteStatus) {
		this.inviteStatus = inviteStatus;
	}
	public String getRecordUserId() {
		return recordUserId;
	}
	public void setRecordUserId(String recordUserId) {
		this.recordUserId = recordUserId;
	}
	public String getRecordUserName() {
		return recordUserName;
	}
	public void setRecordUserName(String recordUserName) {
		this.recordUserName = recordUserName;
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
	
	
	
	
}
