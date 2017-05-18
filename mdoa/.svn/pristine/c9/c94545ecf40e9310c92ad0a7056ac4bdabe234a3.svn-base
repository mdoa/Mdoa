package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.util.DateUtil;

public class WelfareRecord {
	
	private String welfareRecordId;//福利发放记录id
	private String welfareId;//所属福利id
	private String objFlag;
	private String getUserId;//获得用户id
	private String getUserIdCard;//获得福利用户身份证
	private String getUserName;//用户姓名
	private Date giveTime;//发放时间
	private String giveTimeStr;
	private Date getTime;//领取时间
	private String getTimeStr;
	private String getFlag;//是否已领取0 没有 1 领取了
	private Date createTime;
	private String createTimeStr;
	private String createUserId;
	private String createUserName;
	private Date updateTime;
	private String updateTimeStr;
	private String updateUserId;
	private String updateUserName;
	private String aliveFlag;
	
	public String getObjFlag() {
		return objFlag;
	}
	public void setObjFlag(String objFlag) {
		this.objFlag = objFlag;
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
	public String getWelfareRecordId() {
		return welfareRecordId;
	}
	public void setWelfareRecordId(String welfareRecordId) {
		this.welfareRecordId = welfareRecordId;
	}
	public String getWelfareId() {
		return welfareId;
	}
	public void setWelfareId(String welfareId) {
		this.welfareId = welfareId;
	}
	public String getGetUserId() {
		return getUserId;
	}
	public void setGetUserId(String getUserId) {
		this.getUserId = getUserId;
	}
	public String getGetUserIdCard() {
		return getUserIdCard;
	}
	public void setGetUserIdCard(String getUserIdCard) {
		this.getUserIdCard = getUserIdCard;
	}
	public String getGetUserName() {
		return getUserName;
	}
	public void setGetUserName(String getUserName) {
		this.getUserName = getUserName;
	}
	public String getGetFlag() {
		return getFlag;
	}
	public void setGetFlag(String getFlag) {
		this.getFlag = getFlag;
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	public Date getGiveTime() {
		return giveTime;
	}
	public String getGiveTimeStr() {
		return giveTimeStr;
	}
	public Date getGetTime() {
		return getTime;
	}
	public String getGetTimeStr() {
		return getTimeStr;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setGiveTime(Date giveTime) {
		this.giveTime = giveTime;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		giveTimeStr=sdf.format(getClass());
	}
	public void setGetTime(Date getTime) {
		this.getTime = getTime;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		getTimeStr=sdf.format(getTime);
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		createTimeStr=sdf.format(createTime);
	}
	public void setGiveTimeStr(String giveTimeStr) {
		this.giveTimeStr = giveTimeStr;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			giveTime=sdf.parse(giveTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public void setGetTimeStr(String getTimeStr) {
		this.getTimeStr = getTimeStr;
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			getTime=sdf.parse(getTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

}
