package com.mdoa.admin.model;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 折旧记录
 * @author Administrator
 *
 */
public class DepreRecord {

	private String recordId;
	private String assetsId;
	private BigDecimal workCapacity;
	private String workGrossUnit;
	private BigDecimal depreAmount;
	private Date calTime;
	private String calTimeStr;
	private String aliveFlag;
	private String createUserId;
	private String createUserName;//创建人
	private Date createTime;//创建时间
	private String updateUserId;
	private String updateUserName;//修改人
	private Date updateTime;//修改时间
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
	public BigDecimal getWorkCapacity() {
		return workCapacity;
	}
	public void setWorkCapacity(BigDecimal workCapacity) {
		this.workCapacity = workCapacity;
	}
	public String getWorkGrossUnit() {
		return workGrossUnit;
	}
	public void setWorkGrossUnit(String workGrossUnit) {
		this.workGrossUnit = workGrossUnit;
	}
	public BigDecimal getDepreAmount() {
		return depreAmount;
	}
	public void setDepreAmount(BigDecimal depreAmount) {
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
