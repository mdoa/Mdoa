package com.mdoa.personnel.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.util.DateUtil;

public class InviteEngageUserForm {

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
	
	
	private String education;//教育背景
    private String password;//密码
    private String userAccount;//帐号
    private String workTimeStr;//String类型的入职时间
    private Date workTime;//入职时间
    private String kpiGroupId;//kpi组id
    private String leaderFlag;//是否是领导
    private String createUserId;//创建人id
    private String createUserName;//创建人名字
    private Date createTime;//创建时间
    private String createTimeStr;//String类型的创建时间
    private String updateUserId;//修改人id
    private String updateUserName;//修改人名字
    private Date updateTime;//修改时间
    private String updateTimeStr;//String类型的修改时间
    private String departmentName;//部门名字
	
    
    
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	public String getWorkTimeStr() {
		return workTimeStr;
	}
	public void setWorkTimeStr(String workTimeStr) {
		this.workTimeStr = workTimeStr;
		this.workTime=DateUtil.strToDate(workTimeStr);
	}
	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
		this.workTimeStr=DateUtil.dateToStr(workTime);
	}
	public String getKpiGroupId() {
		return kpiGroupId;
	}
	public void setKpiGroupId(String kpiGroupId) {
		this.kpiGroupId = kpiGroupId;
	}
	public String getLeaderFlag() {
		return leaderFlag;
	}
	public void setLeaderFlag(String leaderFlag) {
		this.leaderFlag = leaderFlag;
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
		this.createTimeStr=DateUtil.dateToStr(createTime);
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		this.createTime=DateUtil.strToDate(createTimeStr);
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
		this.updateTimeStr=DateUtil.dateToStr(updateTime);
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		this.updateTime=DateUtil.strToDate(updateTimeStr);
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	//-------------------------------------华丽的分割线------------------------------------------
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
