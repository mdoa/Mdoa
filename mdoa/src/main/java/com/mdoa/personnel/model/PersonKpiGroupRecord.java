package com.mdoa.personnel.model;

import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

public class PersonKpiGroupRecord extends BaseModel{
	private String groupRecordId;
	private String markMonth;//月份
	private String kpiGroupId;
	private String kpiGroupName;//KPI组名字
	private Integer scoreUser;//用户分数
	private Integer scoreStandard;//满分
	private String markUserName;//用户名
	private String markUserId;
	private String createUserId;
	private String createUserName;//创建人
	private Date createTime;//创建时间
	private String createTimeStr;
	private String updateUserId;
	private String updateUserName;//修改人
	private Date updateTime;//修改时间
	private String updateTimeStr;
	private String aliveFlag;
	
	public String getGroupRecordId() {
		return groupRecordId;
	}
	public void setGroupRecordId(String groupRecordId) {
		this.groupRecordId = groupRecordId;
	}
	public String getMarkMonth() {
		return markMonth;
	}
	public void setMarkMonth(String markMonth) {
		this.markMonth = markMonth;
	}
	public String getKpiGroupId() {
		return kpiGroupId;
	}
	public void setKpiGroupId(String kpiGroupId) {
		this.kpiGroupId = kpiGroupId;
	}
	public String getKpiGroupName() {
		return kpiGroupName;
	}
	public void setKpiGroupName(String kpiGroupName) {
		this.kpiGroupName = kpiGroupName;
	}
	
	public Integer getScoreUser() {
		return scoreUser;
	}
	public void setScoreUser(Integer scoreUser) {
		this.scoreUser = scoreUser;
	}
	public Integer getScoreStandard() {
		return scoreStandard;
	}
	public void setScoreStandard(Integer scoreStandard) {
		this.scoreStandard = scoreStandard;
	}
	public String getMarkUserName() {
		return markUserName;
	}
	public void setMarkUserName(String markUserName) {
		this.markUserName = markUserName;
	}
	public String getMarkUserId() {
		return markUserId;
	}
	public void setMarkUserId(String markUserId) {
		this.markUserId = markUserId;
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
		this.createTime = DateUtil.strToDate(createTimeStr);
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
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

   
}
