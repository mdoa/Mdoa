package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainObj {
	private String trainObjId;//培训对象Id
	private String trainId;//培训ID
	private String departmentId;//培训对象部门Id
	private String departmentName;//培训对象部门名
	private String postId;//培训对象岗位Id
	private String postName;//培训对象岗位名
	private String userId;//培训用户Id
	private String userName;//培训用户名
	private String objTypeFlag;//判定培训是哪种对象1部门2岗位3用户
	private Date createTime;//创建时间
	private String createTimeStr;//String类型的创建时间.
	private String createUserId;//创建者的Id
	private Date updateTime;//更新时间
	private String updateTimeStr;//String类型的更新时间
	private String updateUserId;//更新者的Id
	private String aliveFlag;//有效标识符
	
	public String getTrainObjId() {
		return trainObjId;
	}
	public void setTrainObjId(String trainObjId) {
		this.trainObjId = trainObjId;
	}

	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
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
	public String getObjTypeFlag() {
		return objTypeFlag;
	}
	public void setObjTypeFlag(String objTypeFlag) {
		this.objTypeFlag = objTypeFlag;
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
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	

}
