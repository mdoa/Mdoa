package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

public class Invite extends BaseModel{
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
	private Date createTime;//创建时间
	private String createTimeStr;//String形式的创建时间
	private String createUserId;//创建者的ID
	private String createUserName;//创建者的名字
	private Date updateTime;//更新时间
	private String updateTimeStr;//String形式的更新时间
	private String updateUserId;//更新者的ID
	private String updatyeUserName;//更新者的名字
	private String aliveFlag;//有效标志位
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
	
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		this.createTimeStr = DateUtil.dateToStr(createTime);
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		this.createTime =DateUtil.strToDate(createTimeStr); 
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		this.updateTimeStr = DateUtil.dateToStr(updateTime);
	}
	
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		this.updateTime = DateUtil.strToDate(updateTimeStr); 
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdatyeUserName() {
		return updatyeUserName;
	}
	public void setUpdatyeUserName(String updatyeUserName) {
		this.updatyeUserName = updatyeUserName;
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	
	
}
