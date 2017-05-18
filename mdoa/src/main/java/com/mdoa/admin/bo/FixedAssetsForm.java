package com.mdoa.admin.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;

public class FixedAssetsForm extends BaseModel{

	
	private String assetsName;
	private String recordId;
	private String assetsId;
	private Double workCapacity;
	private String workGrossUnit;
	private Double depreAmount;
	private Date calTime;
	private String calTimeStr;
	private String aliveFlag;
	private String createUserId;
	private String createUserName;//创建人
	private Date createTime;//创建时间
	private String updateUserId;
	private String updateUserName;//修改人
	private Date updateTime;//修改时间
	private String beginTime;
	private String endTime;
	private String deperTypeName;//折旧类型名称
	
	
	public String getDeperTypeName() {
		return deperTypeName;
	}
	public void setDeperTypeName(String deperTypeName) {
		this.deperTypeName = deperTypeName;
	}
	public String getBeginTime() {
		return beginTime;
	}
	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getAssetsName() {
		return assetsName;
	}
	public void setAssetsName(String assetsName) {
		this.assetsName = assetsName;
	}
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getAssetsId() {
		return assetsId;
	}
	public void setAssetsId(String assetsId) {
		this.assetsId = assetsId;
	}
	public Double getWorkCapacity() {
		return workCapacity;
	}
	public void setWorkCapacity(Double workCapacity) {
		this.workCapacity = workCapacity;
	}
	public String getWorkGrossUnit() {
		return workGrossUnit;
	}
	public void setWorkGrossUnit(String workGrossUnit) {
		this.workGrossUnit = workGrossUnit;
	}
	public Double getDepreAmount() {
		return depreAmount;
	}
	public void setDepreAmount(Double depreAmount) {
		this.depreAmount = depreAmount;
	}
	public Date getCalTime() {
		return calTime;
	}
	public void setCalTime(Date calTime) {
		this.calTime = calTime;
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.calTimeStr = data.format(calTime);
	}
	public String getCalTimeStr() {
		return calTimeStr;
	}
	public void setCalTimeStr(String calTimeStr) {
		this.calTimeStr = calTimeStr;
		SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.calTime = data.parse(calTimeStr);
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
	}
}
