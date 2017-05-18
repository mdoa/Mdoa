package com.mdoa.user.model;

import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;
import com.mdoa.util.StringUtil;

public class UserTransfer extends BaseModel {
	private String idCard;
	private String userTransferInfoId;
	private String oldPostId;
	private String oldPostName;
	private String oldDepartmentId;
	private String oldDepartmentUrl;
	private String oldDepartmentName;
	private String newPostId;
	private String newPostName;
	private String newDepartmentId;
	private String newDepartmentUrl;
	private String newDepartmentName;
	private Date createTime;
	private String createTimeStr;
	private String createUserId;
	private String createUserName;
	private Date updateTime;
	private String updateTimeStr;
	private String updateUserId;
	private String updateUserName;
	private String aliveFlag;
	private String startTimeStr;
	private String endTimeStr;
	private String remark;
	private Date transferTime;
	private String transferTimeStr;
	private String[] userIds;
	private String[] idCards;
	private String[] oldDepartmentUrls;
	private String[] oldPostIds;
	
	public String[] getOldDepartmentUrls() {
		return oldDepartmentUrls;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setOldDepartmentUrls(String[] oldDepartmentUrls) {
		this.oldDepartmentUrls = oldDepartmentUrls;
	}

	public String[] getOldPostIds() {
		return oldPostIds;
	}

	public void setOldPostIds(String[] oldPostIds) {
		this.oldPostIds = oldPostIds;
	}

	public String[] getUserIds() {
		this.userIds = getUserId().split(",");
		return userIds;
	}

	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}
	
	public String[] getIdCards() {
		this.idCards = this.getIdCard().split(",");
		return idCards;
	}

	public void setIdCards(String[] userIds) {
		this.idCards = userIds;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getTransferTime() {
		return transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
		this.transferTimeStr = DateUtil.dateToStr(transferTime, "yyyy-MM-dd");
	}

	public String getTransferTimeStr() {
		return transferTimeStr;
	}

	public void setTransferTimeStr(String transferTimeStr) {
		this.transferTimeStr = transferTimeStr;
		this.transferTime = DateUtil.strToDate(transferTimeStr, "yyyy-MM-dd");
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		this.updateTimeStr = DateUtil.dateToStr(updateTime, "yyyy-MM-dd");
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		this.updateTime = DateUtil.strToDate(updateTimeStr, "yyyy-MM-dd");
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

	public UserTransfer() {

	}

	public String getUserTransferInfoId() {
		return userTransferInfoId;
	}

	public void setUserTransferInfoId(String userTransferInfoId) {
		this.userTransferInfoId = userTransferInfoId;
	}

	public String getOldPostId() {
		return oldPostId;
	}

	public void setOldPostId(String oldPostId) {
		this.oldPostId = oldPostId;
		this.oldPostIds = oldPostId.split(",");
	}

	public String getOldDepartmentId() {
		return oldDepartmentId;
	}

	public void setOldDepartmentId(String oldDepartmentId) {
		this.oldDepartmentId = oldDepartmentId;
	}

	public String getOldDepartmentUrl() {
		return oldDepartmentUrl;
	}

	public void setOldDepartmentUrl(String oldDepartmentUrl) {
		this.oldDepartmentUrl = oldDepartmentUrl;
		this.oldDepartmentUrls = oldDepartmentUrl.split(",");
	}

	public String getNewPostId() {
		return newPostId;
	}

	public void setNewPostId(String newPostId) {
		this.newPostId = newPostId;
	}

	public String getNewDepartmentId() {
		return newDepartmentId;
	}

	public void setNewDepartmentId(String newDepartmentId) {
		this.newDepartmentId = newDepartmentId;
	}

	public String getNewDepartmentUrl() {
		return newDepartmentUrl;
	}

	public void setNewDepartmentUrl(String newDepartmentUrl) {
		this.newDepartmentUrl = newDepartmentUrl;
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

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public String getNewPostName() {
		return newPostName;
	}

	public void setNewPostName(String newPostName) {
		this.newPostName = newPostName;
	}

	public String getNewDepartmentName() {
		return newDepartmentName;
	}

	public void setNewDepartmentName(String newDepartmentName) {
		this.newDepartmentName = newDepartmentName;
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		this.createTimeStr = DateUtil.dateToStr(createTime, "yyyy-MM-dd");
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		this.createTime = DateUtil.strToDate(createTimeStr, "yyyy-MM-dd");
	}

	public String getOldPostName() {
		return oldPostName;
	}

	public void setOldPostName(String oldPostName) {
		this.oldPostName = oldPostName;
	}

	public String getOldDepartmentName() {
		return oldDepartmentName;
	}

	public void setOldDepartmentName(String oldDepartmentName) {
		this.oldDepartmentName = oldDepartmentName;
	}
	
}
