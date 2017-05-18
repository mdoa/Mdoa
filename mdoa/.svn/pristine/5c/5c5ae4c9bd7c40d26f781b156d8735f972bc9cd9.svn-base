package com.mdoa.personnel.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserInfoArchives {

	private String userId;//用户id
    private String password;//用户密码
    private String userName;//用户名字
    private String userAccount;
	private String phoneNum;//电话号码
    private String accidentPhoneNum;//紧急电话号码
    private Date birthday;//生日
    private String birthdayStr;
    private String idCard;//卡号
    private String sex;//男女
    private String email;//邮箱
    private Date workTime;//工作时间
    private String workTimeStr;
    private String address;//家庭地址
    private String kpiGroupId;//kpi组id
    private String leaderFlag;//是否位领导
    private String createUserId;//创建人id
    private String createUserName;//创建人名字
    private String strCreateTime;//string类型
    private Date createTime;//创建时间
    private String updateUserId;//修改人id
    private String updateUserName;//修改人名字
    private String strUpdateTime;//string类型
    private Date updateTime;//修改时间
    private String aliveFlag;//
    private String departmentId;
    private String departmentName;//部门名称
    private String educationId;//教育id
    private String specialty;
    private String strStarTime;
    private Date starTime;//开始时间
    private String strEndTime;
    private Date endTime;//结束时间
    private String schoolName;//学校名称
    private String trainId;//培训id
    private String trainName;
    private String trainDocId;
    private String trainCompany;//培训公司  0000为本公司
    private String trainDescribe;//培训描述
    private String joinFlag;//是否参加了培训 01参加02逃离03病事假
    private String workId;//工作id
    private String postName;//岗位名称
    private String componyName;//公司名称
    private String workDescribe;//工作描述
    
    
    public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
	public String getTrainDocId() {
		return trainDocId;
	}
	public void setTrainDocId(String trainDocId) {
		this.trainDocId = trainDocId;
	}
	public String getSpecialty() {
		return specialty;
	}
	public void setSpecialty(String specialty) {
		this.specialty = specialty;
	}
	public String getBirthdayStr() {
		return birthdayStr;
	}
	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.birthday = dateFormat.parse(birthdayStr);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getWorkTimeStr() {
		return workTimeStr;
	}
	public void setWorkTimeStr(String workTimeStr) {
		this.workTimeStr = workTimeStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.workTime = dateFormat.parse(workTimeStr);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getUserAccount() {
		return userAccount;
	}
	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
    public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	public Date getStarTime() {
        return starTime;
    }
    public void setStarTime(Date starTime) {
        this.starTime = starTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.strStarTime = dateFormat.format(starTime);
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.strEndTime = dateFormat.format(endTime);
    }

    public String getStrStarTime() {
		return strStarTime;
	}

	public void setStrStarTime(String strStarTime) {
		this.strStarTime = strStarTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			this.starTime = dateFormat.parse(strStarTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getStrEndTime() {
		return strEndTime;
	}

	public void setStrEndTime(String strEndTime) {
		this.strEndTime = strEndTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			this.endTime = dateFormat.parse(strEndTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
    
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getAccidentPhoneNum() {
		return accidentPhoneNum;
	}
	public void setAccidentPhoneNum(String accidentPhoneNum) {
		this.accidentPhoneNum = accidentPhoneNum;
	}
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.birthdayStr = dateFormat.format(birthday);
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.workTimeStr = dateFormat.format(workTime);
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
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
	public String getStrCreateTime() {
		return strCreateTime;
	}
	public void setStrCreateTime(String strCreateTime) {
		this.strCreateTime = strCreateTime;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
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
	public String getStrUpdateTime() {
		return strUpdateTime;
	}
	public void setStrUpdateTime(String strUpdateTime) {
		this.strUpdateTime = strUpdateTime;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	public String getEducationId() {
		return educationId;
	}
	public void setEducationId(String educationId) {
		this.educationId = educationId;
	}
	
	public String getSchoolName() {
		return schoolName;
	}
	public void setSchoolName(String schoolName) {
		this.schoolName = schoolName;
	}
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getTrainCompany() {
		return trainCompany;
	}
	public void setTrainCompany(String trainCompany) {
		this.trainCompany = trainCompany;
	}
	public String getTrainDescribe() {
		return trainDescribe;
	}
	public void setTrainDescribe(String trainDescribe) {
		this.trainDescribe = trainDescribe;
	}
	public String getJoinFlag() {
		return joinFlag;
	}
	public void setJoinFlag(String joinFlag) {
		this.joinFlag = joinFlag;
	}
	public String getWorkId() {
		return workId;
	}
	public void setWorkId(String workId) {
		this.workId = workId;
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
	}
	public String getComponyName() {
		return componyName;
	}
	public void setComponyName(String componyName) {
		this.componyName = componyName;
	}
	public String getWorkDescribe() {
		return workDescribe;
	}
	public void setWorkDescribe(String workDescribe) {
		this.workDescribe = workDescribe;
	}
    
    
    
    
}
