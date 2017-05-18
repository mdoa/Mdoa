package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainDoc {
	private String trainDocId;//培训人员信息ID
	private String trainName;//培训名
	private String trainId;//培训ID
	private String userId;//用户ID
	private String userName;//用户名
	private Date startTime;//开始时间 :年-月-日
	private String startTimeStr;//String类型的开始时间 :年-月-日
	private Date endTime;//结束时间:年-月-日
	private String endTimeStr;//String类型的结束时间:年-月-日
	private String trainDescribe;//培训描述
	private String trainCompany;//培训公司，0000为本公司
	private String createUserId;//创建人
	private Date createTime;//创建时间:年-月-日   时:分:秒
	private String createTimeStr;//String类型的创建时间:年-月-日   时:分:秒
	private String updateUserId;//修改人
	private Date updateTime;//修改时间:年-月-日   时:分:秒
	private String updateTimeStr;//String类型的修改时间:年-月-日   时:分:秒
	private String aliveFlag;//有效标识符
	private String joinFlag;//是否参加了培训（01参加02逃离03病事假）
	public String getTrainDocId() {
		return trainDocId;
	}
	public void setTrainDocId(String trainDocId) {
		this.trainDocId = trainDocId;
	}
	
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.startTimeStr=dateFormat.format(startTime);
	}
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.startTime=dateFormat.parse(startTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.endTimeStr=dateFormat.format(endTime);
	}
	public String getEndTimeStr() {
		return endTimeStr;
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.endTime= dateFormat.parse(endTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getTrainDescribe() {
		return trainDescribe;
	}
	public void setTrainDescribe(String trainDescribe) {
		this.trainDescribe = trainDescribe;
	}
	public String getTrainCompany() {
		return trainCompany;
	}
	public void setTrainCompany(String trainCompany) {
		this.trainCompany = trainCompany;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createTimeStr=dateFormat.format(createTime);
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.createTime=dateFormat.parse(createTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.updateTimeStr=dateFormat.format(updateTime);
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.updateTime=dateFormat.parse(updateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	public String getJoinFlag() {
		return joinFlag;
	}
	public void setJoinFlag(String joinFlag) {
		this.joinFlag = joinFlag;
	}
	

}
