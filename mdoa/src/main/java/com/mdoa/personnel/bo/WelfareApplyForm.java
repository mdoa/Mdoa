package com.mdoa.personnel.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

/**
 * 福利申请信息表单
 * @author Administrator
 *
 */
public class WelfareApplyForm extends BaseModel{
	
	private String welfareId;
	private String welfareName;
	private String welfareCode;
	private String text;//描述
	private String reason;//发放原因
	private Date giveTime;//发放时间 没有时分秒
	private String giveTimeStr;
	private String examineUserId;//审核人id
	private String examineUserName;//审核人姓名
	private String departmentId;//福利部门id
	private String departmentName;//福利部门名称
	private String postId;//多个福利岗位id的字符串
	private String postName;//福利岗位名称
	private String objFlag;//福利是哪种对象1部门2岗位3退休
	private String createUserId;
	private String createUserName;
	private String updateUserId;
	private String updateUserName;
	private String welfareStreamId;
	private String examineStatus;
	private String streamType;
	private String getUserId;
	private String[] getUserIds;
	private String getUserIdCard;
	private String[] getUserIdCards;
	private String getUserName;
	private String[] getUserNames;
	private WelfareApplyForm welfareApplyForm;
	private String queryFlag;
	private String education;
	private String volk;
    private String password;
    private String userAccount;
    private String phoneNum;
    private String accidentPhoneNum;
    private Date birthday;
    private String birthdayStr;//
    private String idCard;
    private String sex;
    private String email;
    private String workTimeStr;//
    private Date workTime;
    private String address;
    private String kpiGroupId;
    private String leaderFlag;
    private Date createTime;
    private String createTimeStr;//
    private Date updateTime;
    private String updateTimeStr;//
    private String aliveFlag;
    private String departmentUrl;
    private String leadId;
    private String leadName;
    private String roleIds;
    private String roleNames;
    private String workTimeStartStr;//
    private String workTimeEndStr;//
    private String inviteFlag;
    private String retireFlag;
    private String nativePlace;
    private String marriageFlag;
    private String politicalStatus;
    private String idCardUpImg;
    private String idCardDownImg;
    private String photo;
	private String dormFlag;
	private String dorm;
	private String welfareRecordId;
	private String finishFlag;
	private Double budgetAmount;
	private int population;
	private Date startCreateTime;
	private String startCreateTimeStr;
	private Date endCreateTime;
	private String endCreateTimeStr;
	
	
	public Date getStartCreateTime() {
		return startCreateTime;
	}
	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = startCreateTime;
	}
	public String getStartCreateTimeStr() {
		return startCreateTimeStr;
	}
	public void setStartCreateTimeStr(String startCreateTimeStr) {
		this.startCreateTimeStr = startCreateTimeStr;
	}
	public Date getEndCreateTime() {
		return endCreateTime;
	}
	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
	}
	public String getEndCreateTimeStr() {
		return endCreateTimeStr;
	}
	public void setEndCreateTimeStr(String endCreateTimeStr) {
		this.endCreateTimeStr = endCreateTimeStr;
	}
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public Double getBudgetAmount() {
		return budgetAmount;
	}
	public void setBudgetAmount(Double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}
	public String getFinishFlag() {
		return finishFlag;
	}
	public void setFinishFlag(String finishFlag) {
		this.finishFlag = finishFlag;
	}
	public String getWelfareRecordId() {
		return welfareRecordId;
	}
	public void setWelfareRecordId(String welfareRecordId) {
		this.welfareRecordId = welfareRecordId;
	}
	public String getDorm() {
		return dorm;
	}
	public void setDorm(String dorm) {
		this.dorm = dorm;
	}
	public String getDormFlag() {
		return dormFlag;
	}
	public void setDormFlag(String dormFlag) {
		this.dormFlag = dormFlag;
	}
	public WelfareApplyForm getWelfareApplyForm() {
		return welfareApplyForm;
	}
	public void setWelfareApplyForm(WelfareApplyForm welfareApplyForm) {
		this.welfareApplyForm = welfareApplyForm;
	}
	public String getGetUserId() {
		return getUserId;
	}
	public void setGetUserId(String getUserId) {
		this.getUserId = getUserId;
		this.getUserIds = getUserId.split(",");
	}
	public String[] getGetUserIds() {
		return getUserIds;
	}
	public void setGetUserIds(String[] getUserIds) {
		this.getUserIds = getUserIds;
	}
	public String getGetUserIdCard() {
		return getUserIdCard;
	}
	public void setGetUserIdCard(String getUserIdCard) {
		this.getUserIdCard = getUserIdCard;
		this.getUserIdCards = getUserIdCard.split(",");
	}
	public String[] getGetUserIdCards() {
		return getUserIdCards;
	}
	public void setGetUserIdCards(String[] getUserIdCards) {
		this.getUserIdCards = getUserIdCards;
	}
	public String getGetUserName() {
		return getUserName;
	}
	public void setGetUserName(String getUserName) {
		this.getUserName = getUserName;
		this.getUserNames = getUserName.split(",");
	}
	public String[] getGetUserNames() {
		return getUserNames;
	}
	public void setGetUserNames(String[] getUserNames) {
		this.getUserNames = getUserNames;
	}
	public String getWelfareId() {
		return welfareId;
	}
	public void setWelfareId(String welfareId) {
		this.welfareId = welfareId;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Date getGiveTime() {
		return giveTime;
	}
	public void setGiveTime(Date giveTime) {
		this.giveTime = giveTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		this.giveTimeStr=sdf.format(giveTime);
		System.out.println("giveTimeStr="+giveTimeStr);
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
	public String getExamineUserId() {
		return examineUserId;
	}
	public void setExamineUserId(String examineUserId) {
		this.examineUserId = examineUserId;
	}
	public String getExamineUserName() {
		return examineUserName;
	}
	public void setExamineUserName(String examineUserName) {
		this.examineUserName = examineUserName;
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
	public String getObjFlag() {
		return objFlag;
	}

	public void setObjFlag(String objFlag) {
		this.objFlag = objFlag;
		if(objFlag=="1"){
			postId=null;
			postName=null;
		}
		if(objFlag=="2"){
			departmentId=null;
			departmentName=null;
		}
	}
	public void setGiveTimeStr(String giveTimeStr) {
		this.giveTimeStr = giveTimeStr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		if(giveTimeStr!=""){
			try {
				giveTime = sdf.parse(giveTimeStr);
			} catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}
	public String getGiveTimeStr() {
		return giveTimeStr;
	}
	public WelfareObjForm toObjForm(){
		WelfareObjForm objForm = new WelfareObjForm();
		objForm.setWelfareId(welfareId);
		objForm.setObjFlag(objFlag);
		objForm.setWelfareName(welfareName);
		objForm.setWelfareCode(welfareCode);
		return objForm;
	}
	public String getWelfareStreamId() {
		return welfareStreamId;
	}
	public void setWelfareStreamId(String welfareStreamId) {
		this.welfareStreamId = welfareStreamId;
	}
	public String getExamineStatus() {
		return examineStatus;
	}
	public void setExamineStatus(String examineStatus) {
		this.examineStatus = examineStatus;
	}
	public String getStreamType() {
		return streamType;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}
	public String getWelfareName() {
		return welfareName;
	}
	public void setWelfareName(String welfareName) {
		this.welfareName = welfareName;
	}
	public String getWelfareCode() {
		return welfareCode;
	}
	public void setWelfareCode(String welfareCode) {
		this.welfareCode = welfareCode;
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
	public String getQueryFlag() {
		return queryFlag;
	}
	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}
	public String getEducation() {
		return education;
	}
	public void setEducation(String education) {
		this.education = education;
	}
	public String getVolk() {
		return volk;
	}
	public void setVolk(String volk) {
		this.volk = volk;
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
		this.birthdayStr = DateUtil.dateToStr(birthday,"yyyy-MM-dd");
	}
	public String getBirthdayStr() {
		return birthdayStr;
	}
	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
		this.birthday = DateUtil.strToDate(birthdayStr,"yyyy-MM-dd");
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
	public String getWorkTimeStr() {
		return workTimeStr;
	}
	public void setWorkTimeStr(String workTimeStr) {
		this.workTimeStr = workTimeStr;
		this.workTime = DateUtil.strToDate(workTimeStr,"yyyy-MM-dd");
	}
	public Date getWorkTime() {
		return workTime;
	}
	public void setWorkTime(Date workTime) {
		this.workTime = workTime;
		this.workTimeStr = DateUtil.dateToStr(workTime,"yyyy-MM-dd");
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
	public String getDepartmentUrl() {
		return departmentUrl;
	}
	public void setDepartmentUrl(String departmentUrl) {
		this.departmentUrl = departmentUrl;
	}
	public String getLeadId() {
		return leadId;
	}
	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}
	public String getLeadName() {
		return leadName;
	}
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
	public String getRoleIds() {
		return roleIds;
	}
	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}
	public String getRoleNames() {
		return roleNames;
	}
	public void setRoleNames(String roleNames) {
		this.roleNames = roleNames;
	}
	public String getWorkTimeStartStr() {
		return workTimeStartStr;
	}
	public void setWorkTimeStartStr(String workTimeStartStr) {
		this.workTimeStartStr = workTimeStartStr;
	}
	public String getWorkTimeEndStr() {
		return workTimeEndStr;
	}
	public void setWorkTimeEndStr(String workTimeEndStr) {
		this.workTimeEndStr = workTimeEndStr;
	}
	public String getInviteFlag() {
		return inviteFlag;
	}
	public void setInviteFlag(String inviteFlag) {
		this.inviteFlag = inviteFlag;
	}
	public String getRetireFlag() {
		return retireFlag;
	}
	public void setRetireFlag(String retireFlag) {
		this.retireFlag = retireFlag;
	}
	public String getNativePlace() {
		return nativePlace;
	}
	public void setNativePlace(String nativePlace) {
		this.nativePlace = nativePlace;
	}
	public String getMarriageFlag() {
		return marriageFlag;
	}
	public void setMarriageFlag(String marriageFlag) {
		this.marriageFlag = marriageFlag;
	}
	public String getPoliticalStatus() {
		return politicalStatus;
	}
	public void setPoliticalStatus(String politicalStatus) {
		this.politicalStatus = politicalStatus;
	}
	public String getIdCardUpImg() {
		return idCardUpImg;
	}
	public void setIdCardUpImg(String idCardUpImg) {
		this.idCardUpImg = idCardUpImg;
	}
	public String getIdCardDownImg() {
		return idCardDownImg;
	}
	public void setIdCardDownImg(String idCardDownImg) {
		this.idCardDownImg = idCardDownImg;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	
}
