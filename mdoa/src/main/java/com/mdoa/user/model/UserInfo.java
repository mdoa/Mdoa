package com.mdoa.user.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;

import com.mdoa.base.model.BaseModel;
import com.mdoa.dictionary.model.DictionaryModelConstant;
import com.mdoa.util.DateUtil;
import com.mdoa.util.StringUtil;

public class UserInfo extends BaseModel implements Serializable {
	
	private String attendanceGroupId;
	
	private String queryFlag;
	
	private String education;
	
	private String volk;
	
    private String password;

    private String userAccount;
    
    private String phoneNum;

    private String accidentPhoneNum;

    private Date birthday;
    
    private String birthdayStr;

    private String idCard;

    private String sex;

    private String email;

    private String workTimeStr;
    
    private Date workTime;

    private String address;

    private String kpiGroupId;

    private String leaderFlag;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private String createTimeStr;
    
    private String updateUserId;

    private String updateUserName;

    private Date updateTime;

    private String aliveFlag;
    
    private String departmentName;
    
    private String departmentId;
    
    private String departmentUrl;
    
    private String postId;
    
    private String postName;
    
    private String leadId;
    
    private String leadName;
    
    private String roleIds;
    
    private String roleNames;
    
    private String workTimeStartStr;
    
    private String workTimeEndStr;
    
    private String inviteFlag;
    
    private String retireFlag;
    
    private String nativePlace;
    
    private String marriageFlag;
    
    private String politicalStatus;
    
    private String idCardUpImg;
    
    private String idCardDownImg;
    
    private String idCardValid;
    
    private String idCardUpImgBase;
    
    private String idCardDownImgBase;
    
    private String photoBase;
    
    private String idCardIssued;

    private String photo;
    
    private String wageAccount;
    
    private String englishLevel;
    
    private String diseaseHistoryFlag;
    
    private String diseaseHistory;
    
    private String readIdcardFlag;
    
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

	public String getIdCardUpImg() {
		return idCardUpImg;
	}

	public void setIdCardUpImg(String idCardUpImg) {
		this.idCardUpImg = idCardUpImg;
	}

	 private String dorm;
	    
	    private String dormFlag;
  
    /**
     * 用户权限信息
     */
    private Set<String> permissions;
    
    public UserInfo(){
    	
    }
    
    public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}

	public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum == null ? null : phoneNum.trim();
    }

    public String getAccidentPhoneNum() {
        return accidentPhoneNum;
    }

    public void setAccidentPhoneNum(String accidentPhoneNum) {
        this.accidentPhoneNum = accidentPhoneNum == null ? null : accidentPhoneNum.trim();
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
        this.birthdayStr = DateUtil.dateToStr(birthday, "yyyy-MM-dd");
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard == null ? null : idCard.trim();
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex == null ? null : sex.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public Date getWorkTime() {
        return workTime;
    }

    public void setWorkTime(Date workTime) {
        this.workTime = workTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.workTimeStr = dateFormat.format(workTime);    
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

	public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address == null ? null : address.trim();
    }

    public String getKpiGroupId() {
        return kpiGroupId;
    }

    public void setKpiGroupId(String kpiGroupId) {
        this.kpiGroupId = kpiGroupId == null ? null : kpiGroupId.trim();
    }

    public String getLeaderFlag() {
        return leaderFlag;
    }

    public void setLeaderFlag(String leaderFlag) {
        this.leaderFlag = leaderFlag == null ? null : leaderFlag.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.createTimeStr = dateFormat.format(createTime);
    }

    public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		 SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		 try {
			this.createTime = dateFormat.parse(createTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
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
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

	public Set<String> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<String> permissions) {
		this.permissions = permissions;
	}

	public String getBirthdayStr() {
		return birthdayStr;
	}

	public void setBirthdayStr(String birthdayStr) {
		this.birthdayStr = birthdayStr;
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

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	
	public String getDepartmentUrl() {
		return departmentUrl;
	}

	public void setDepartmentUrl(String departmentUrl) {
		this.departmentUrl = departmentUrl;
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

	public String getQueryFlag() {
		return queryFlag;
	}

	public void setQueryFlag(String queryFlag) {
		this.queryFlag = queryFlag;
	}
	
	public String getWageAccount() {
		return wageAccount;
	}

	public void setWageAccount(String wageAccount) {
		this.wageAccount = wageAccount;
	}

	public String getEnglishLevel() {
		return englishLevel;
	}

	public void setEnglishLevel(String englishLevel) {
		this.englishLevel = englishLevel;
	}
	
	public String getDiseaseHistoryFlag() {
		return diseaseHistoryFlag;
	}

	public void setDiseaseHistoryFlag(String diseaseHistoryFlag) {
		this.diseaseHistoryFlag = diseaseHistoryFlag;
	}

	public String getDiseaseHistory() {
		return diseaseHistory;
	}

	public void setDiseaseHistory(String diseaseHistory) {
		this.diseaseHistory = diseaseHistory;
	}

	public String getIdCardValid() {
		return idCardValid;
	}

	public void setIdCardValid(String idCardValid) {
		this.idCardValid = idCardValid;
	}

	public String getIdCardIssued() {
		return idCardIssued;
	}

	public void setIdCardIssued(String idCardIssued) {
		this.idCardIssued = idCardIssued;
	}

	public String getIdCardUpImgBase() {
		return idCardUpImgBase;
	}

	public void setIdCardUpImgBase(String idCardUpImgBase) {
		this.idCardUpImgBase = idCardUpImgBase;
	}

	public String getIdCardDownImgBase() {
		return idCardDownImgBase;
	}

	public void setIdCardDownImgBase(String idCardDownImgBase) {
		this.idCardDownImgBase = idCardDownImgBase;
	}

	public String getPhotoBase() {
		return photoBase;
	}

	public void setPhotoBase(String photoBase) {
		this.photoBase = photoBase;
	}

	public String getReadIdcardFlag() {
		return readIdcardFlag;
	}

	public void setReadIdcardFlag(String readIdcardFlag) {
		this.readIdcardFlag = readIdcardFlag;
	}

	/**
	 * 转换所有的数据对象
	 */
	public void parseData(){
		this.parseDormFlag();
		this.parseEducation();
		this.parseInviteFlag();
		this.parseMarriageFlag();
		this.parsePoliticalStatus();
		this.parseRetireFlag();
		this.parseSex();
		this.parseVolk();
	}
	
	/**
	 * 转换sex
	 * @param sex
	 * @return
	 */
	public void parseSex(){
		if(StringUtil.isEmpty(sex)){
			sex="";
		}else if(sex.equals("1")){
			sex="男";
		}else{
			sex="女";
		}
	}
	
	/**
	 * 转换学历
	 * @param education
	 * @return
	 */
	public void parseEducation(){
		if(StringUtil.isEmpty(education)){
			education="";
		}else{
			education = DictionaryModelConstant.dictionaryMap.get("education").get(education);
		}
	}
	
	/**
	 * 转换inviteFlag
	 * @param inviteFlag
	 * @return
	 */
	public void parseInviteFlag(){
		if(StringUtil.isEmpty(inviteFlag))
			return ;
		if (StringUtil.isEmpty(inviteFlag)) {
			inviteFlag = "";
		} else if (inviteFlag.equals("1")) {
			inviteFlag = "在职";
		} else if (inviteFlag.equals("2")) {
			inviteFlag = "离职无手续";
		} else if (inviteFlag.equals("3")) {
			inviteFlag = "离职有手续";
		} else if (inviteFlag.equals("4")) {
			inviteFlag = "试用期";
		} else if (inviteFlag.equals("5")) {
			inviteFlag = "工伤";
		} else if (inviteFlag.equals("0")) {
			inviteFlag = "待聘用";
		}
	}
	
	/**
	 * 转换退休状态
	 * @param retireFlag
	 * @return
	 */
	public void parseRetireFlag(){
		if(StringUtil.isEmpty(retireFlag))
			return ;
		if(this.retireFlag.equals("1"))
			this.retireFlag = "在职";
		else if(this.retireFlag.equals("2"))
			this.retireFlag = "退休";
		else if(this.retireFlag.equals("3"))
			this.retireFlag = "返聘";
	}
	
	/**
	 * 转换民族状态
	 */
	public void parseVolk(){
		if(StringUtil.isEmpty(volk)){
			volk="";
		}else{
			volk = DictionaryModelConstant.dictionaryMap.get("volk").get(volk);
		}
	}
	/**
	 * 转换是否住宿舍状态
	 */
	public void parseDormFlag(){
		if(StringUtil.isEmpty(dormFlag))
			return ;
		if(dormFlag.equals("0"))
			dormFlag = "是";
		if(dormFlag.equals("1"))
			dormFlag = "否";
	}
	/**
	 * 转换政治面貌
	 */
	public void parsePoliticalStatus(){
		if(StringUtil.isEmpty(politicalStatus)){
			politicalStatus="";
		}else{
			politicalStatus = DictionaryModelConstant.dictionaryMap.get("political_status").get(politicalStatus);
		}
	}
	
	/**
	 * 转换婚姻状态
	 */
	public void parseMarriageFlag(){
		if(StringUtil.isEmpty(marriageFlag)){
			marriageFlag = "";
		}else{
			marriageFlag = DictionaryModelConstant.dictionaryMap.get("marriage_flag").get(marriageFlag);
		}
	}

	public String getAttendanceGroupId() {
		return attendanceGroupId;
	}

	public void setAttendanceGroupId(String attendanceGroupId) {
		this.attendanceGroupId = attendanceGroupId;
	}
}