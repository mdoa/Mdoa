package com.mdoa.personnel.model;


import com.mdoa.personnel.bo.TrainApplyForm;

public class TrainIdAndName {//存放比如部门ID和部门名，有利于批量的插入OBJ表中

	private String departmentId;//培训对象部门Id
	private String departmentName;//培训对象部门名
	private String postId;//培训对象岗位Id
	private String postName;//培训对象岗位名
	private String userId;//培训用户Id
	private String userName;//培训用户名
	private TrainApplyForm trainApplyForm;
	
	public TrainApplyForm getTrainApplyForm() {
		return trainApplyForm;
	}
	public void setTrainApplyForm(TrainApplyForm trainApplyForm) {
		this.trainApplyForm = trainApplyForm;
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
	
}
