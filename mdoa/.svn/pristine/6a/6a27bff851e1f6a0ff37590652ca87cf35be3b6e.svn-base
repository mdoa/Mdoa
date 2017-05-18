package com.mdoa.personnel.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;

public class PackForm extends BaseModel{
	
	private String packId;//合同id
    private String strStarTime;//string的时间  
    private Date startTime;//开始时间
    private String strEndTime;//string 结束时间    
    private Date endTime;//结束时间
    private String createUserId;//创建人ID
    private Date createTime;//创建时间
    private String updateUserId;//修改人ID
    private Date updateTime;//修改时间
    private String aliveFlag;//有效符
    private String strTryStarTime;
    private Date tryStarTime;//试用期的开始时间
    private String strTryEndTime;
    private Date tryEndTime;//试用期的结束时间
    private String packFlag;//合同标志位 0初聘 1续签 2返聘
    
    private String photoId;//合同图片ID
    private String photoName;//图片名字
    private String url;//大图路径
    private String urlSmall;//小图路径       
    private Integer currentPage;//当前页码
    
    private String departmentName;//部门名称
    private String postName;//岗位名称
    private String userAccount;//用户账户
    private String phoneNum;//电话号码
    private String accidentPhoneNum;//紧急联系电话
    private String sex;//性别
    private String address;//地址
    private String idCard;//身份证号
    private String inviteFlag;//0未聘用，1在职,2离职无手续3离职有手续4试用期
    
    
    public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}

	public String getPackFlag() {
		return packFlag;
	}

	public void setPackFlag(String packFlag) {
		this.packFlag = packFlag;
	}

	public String getInviteFlag() {
		return inviteFlag;
	}

	public void setInviteFlag(String inviteFlag) {
		this.inviteFlag = inviteFlag;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getStrTryStarTime() {
		return strTryStarTime;
	}

	public void setStrTryStarTime(String strTryStarTime) {
		this.strTryStarTime = strTryStarTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.tryStarTime = dateFormat.parse(strTryStarTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getTryStarTime() {
		return tryStarTime;
	}

	public void setTryStarTime(Date tryStarTime) {
		this.tryStarTime = tryStarTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.strTryStarTime = dateFormat.format(tryStarTime);
	}

	public String getStrTryEndTime() {
		return strTryEndTime;
	}

	public void setStrTryEndTime(String strTryEndTime) {
		this.strTryEndTime = strTryEndTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.tryEndTime = dateFormat.parse(strTryEndTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getTryEndTime() {
		return tryEndTime;
	}

	public void setTryEndTime(Date tryEndTime) {
		this.tryEndTime = tryEndTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.strTryEndTime = dateFormat.format(tryEndTime);
	}
    
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.strStarTime = dateFormat.format(startTime);
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.strEndTime = dateFormat.format(endTime);
    }

    public String getStrStarTime() {
		return strStarTime;
	}

	public void setStrStarTime(String strStarTime) {
		this.strStarTime = strStarTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.startTime = dateFormat.parse(strStarTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getStrEndTime() {
		return strEndTime;
	}

	public void setStrEndTime(String strEndTime) {
		this.strEndTime = strEndTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.endTime = dateFormat.parse(strEndTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getPackId() {
		return packId;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrlSmall() {
		return urlSmall;
	}

	public void setUrlSmall(String urlSmall) {
		this.urlSmall = urlSmall;
	}

	public Integer getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
    
	
}
