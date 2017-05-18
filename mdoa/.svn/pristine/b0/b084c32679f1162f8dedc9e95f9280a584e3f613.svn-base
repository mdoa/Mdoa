package com.mdoa.system.model;

import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

public class SystemLog extends BaseModel{
	private String logId;
	private String[] logIds;
	private Date operaTime;
	private String operaTimeStr;
	private String operaUserId;
	private String operaUserName;
	private String operaPanel;
	private String operaTab;
	private Date createTime;
	private String createTimeStr;
	private String operaTimeStartStr;
	private String operaTimeEndStr;
	private String updateUserId;
	private String updateUserName;
	private Date updateTime;
	private String updateTimeStr;
	
	
	public String[] getLogIds() {
		return logIds;
	}
	public void setLogIds(String[] logIds) {
		this.logIds = logIds;
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
	public String getOperaTimeStartStr() {
		return operaTimeStartStr;
	}
	public void setOperaTimeStartStr(String operaTimeStartStr) {
		this.operaTimeStartStr = operaTimeStartStr;
	}
	public String getOperaTimeEndStr() {
		return operaTimeEndStr;
	}
	public void setOperaTimeEndStr(String operaTimeEndStr) {
		this.operaTimeEndStr = operaTimeEndStr;
	}
	public Date getOperaTime() {
		return operaTime;
	}
	public void setOperaTime(Date operaTime) {
		this.operaTime = operaTime;
		this.operaTimeStr = DateUtil.dateToStr(operaTime);
	}
	public String getOperaTimeStr() {
		return operaTimeStr;
	}
	public void setOperaTimeStr(String operaTimeStr) {
		this.operaTimeStr = operaTimeStr;
		this.operaTime = DateUtil.strToDate(operaTimeStr);
	}
	public String getLogId() {
		return logId;
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
	public void setLogId(String logId) {
		this.logId = logId;
		this.logIds = logId.split(",");
	}
	public String getOperaUserId() {
		return operaUserId;
	}
	public void setOperaUserId(String operaUserId) {
		this.operaUserId = operaUserId;
	}
	public String getOperaUserName() {
		return operaUserName;
	}
	public void setOperaUserName(String operaUserName) {
		this.operaUserName = operaUserName;
	}
	public String getOperaPanel() {
		return operaPanel;
	}
	public void setOperaPanel(String operaPanel) {
		this.operaPanel = operaPanel;
	}
	public String getOperaTab() {
		return operaTab;
	}
	public void setOperaTab(String operaTab) {
		this.operaTab = operaTab;
	}
	
	
	
}
