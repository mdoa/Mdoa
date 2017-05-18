package com.mdoa.personnel.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

public class AttendanceGroupForm extends BaseModel{
	
	private String groupId;
	private String groupName;
	private String newGroupName;
	private String defaultOnTime;//默认上班时间
	private String newOnTime;
	private String defaultOffTime;//00:00:00默认下班时间
	private String newOffTime;
	private String createUserId;
	private String createUserName;
	private Date createTime;
	private String createTimeStr;
	private String updateUserId;
	private String updateUserName;
	private Date updateTime;
	private String updateTimeStr;
	private String aliveFlag;
		
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	public String getGroupName() {
		return groupName;
	}
	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	public String getDefaultOnTime() {
		return defaultOnTime;
	}
	public void setDefaultOnTime(String defaultOnTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			sdf.parse(defaultOnTime);
		} catch (ParseException e) {
			e.printStackTrace();
			defaultOnTime = null;
		}finally{	
			this.defaultOnTime = defaultOnTime;
		}
	}
	public String getNewOnTime() {
		return newOnTime;
	}
	public void setNewOnTime(String newOnTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			sdf.parse(newOnTime);
		} catch (ParseException e) {
			e.printStackTrace();
			newOnTime = null;
		}finally{	
			this.newOnTime = newOnTime;
		}
	}
	public String getDefaultOffTime() {
		return defaultOffTime;
	}
	public void setDefaultOffTime(String defaultOffTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			sdf.parse(defaultOffTime);
		} catch (ParseException e) {
			e.printStackTrace();
			defaultOffTime = null;
		}finally{	
			this.defaultOffTime = defaultOffTime;
		}
	}
	public String getNewOffTime() {
		return newOffTime;
	}
	public void setNewOffTime(String newOffTime) {
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
		try {
			sdf.parse(newOffTime);
		} catch (ParseException e) {
			e.printStackTrace();
			newOffTime = null;
		}finally{	
			this.newOffTime = newOffTime;
		}
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
	public String getNewGroupName() {
		return newGroupName;
	}
	public void setNewGroupName(String newGroupName) {
		this.newGroupName = newGroupName;
	}
}
