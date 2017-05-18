package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;

public class InviteRecord extends BaseModel{

	private String inviteRecordId;//招聘记录ID
	private String[] inviteRecordIds;
	private String name;//名字
	private String sex;//性别 1男 2女
	private Integer age;//年龄
	private String marriedFlag;//是否已婚 0 未婚 1已婚
	private String phoneNum;//联系电话
	private String idCard;//身份证号
	private String educationInfo;//教育信息
	private String workInfo;//工作信息
	private String trainInfo;//培训信息
	private String address;//联系地址
	private String nativePlace;//籍贯
	private String educationBackground;//教育背景 1 初中 2 高中 3专科 4本科 5硕士 6博士
	private Date birthday;//生日
	private String birthdayStr;//String类型的生日
	private String aliveFlag;//是否有效
	private Integer grade;//招聘考核分数
	private String postId;//岗位ID
	private String postName;//岗位名字
	private String accidentPhoneNum;//紧急联系电话
	private String volk;//1.汉族 2.其他
	private String email;//邮箱
	
	
	
	public String getAccidentPhoneNum() {
		return accidentPhoneNum;
	}
	public void setAccidentPhoneNum(String accidentPhoneNum) {
		this.accidentPhoneNum = accidentPhoneNum;
	}
	public String getVolk() {
		return volk;
	}
	public void setVolk(String volk) {
		this.volk = volk;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String[] getInviteRecordIds() {
		return inviteRecordIds;
	}
	public void setInviteRecordIds(String[] inviteRecordIds) {
		this.inviteRecordIds = inviteRecordIds;
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
	public String getInviteRecordId() {
		return inviteRecordId;
	}
	public void setInviteRecordId(String inviteRecordId) {
		this.inviteRecordId = inviteRecordId;
		this.inviteRecordIds=inviteRecordId.split(",");
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMarriedFlag() {
		return marriedFlag;
	}
	public void setMarriedFlag(String marriedFlag) {
		this.marriedFlag = marriedFlag;
	}
	public String getIdCard() {
		return idCard;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getEducationInfo() {
		return educationInfo;
	}
	public void setEducationInfo(String educationInfo) {
		this.educationInfo = educationInfo;
	}
	public String getWorkInfo() {
		return workInfo;
	}
	public void setWorkInfo(String workInfo) {
		this.workInfo = workInfo;
	}
	public String getTrainInfo() {
		return trainInfo;
	}
	public void setTrainInfo(String trainInfo) {
		this.trainInfo = trainInfo;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getEducationBackground() {
		return educationBackground;
	}
	public void setEducationBackground(String educationBackground) {
		this.educationBackground = educationBackground;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.birthdayStr=dateFormat.format(birthday);
	}
	public String getBirthdayStr() {
		return birthdayStr;
	}
	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.birthday=dateFormat.parse(birthdayStr);
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
	public Integer getGrade() {
		return grade;
	}
	public void setGrade(Integer grade) {
		this.grade = grade;
	}

}
