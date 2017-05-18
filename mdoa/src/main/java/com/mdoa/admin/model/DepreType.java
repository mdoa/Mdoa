package com.mdoa.admin.model;

import java.util.Date;

import com.mdoa.base.model.BaseModel;

/**
 * 折旧类型
 * @author Administrator
 *
 */
public class DepreType extends BaseModel{

	private String depreTypeId;
	private String depreTypeName;
	private Integer deprePeriod;
	private String typeDesc;
	private Integer calMethod;
	private String aliveFlag;
	private String createUserId;
	private String createUserName;//创建人
	private Date createTime;//创建时间
	private String updateUserId;
	private String updateUserName;//修改人
	private Date updateTime;//修改时间
	public String getDepreTypeId() {
		return depreTypeId;
	}
	public void setDepreTypeId(String depreTypeId) {
		this.depreTypeId = depreTypeId;
	}
	public String getDepreTypeName() {
		return depreTypeName;
	}
	public void setDepreTypeName(String depreTypeName) {
		this.depreTypeName = depreTypeName;
	}
	public Integer getDeprePeriod() {
		return deprePeriod;
	}
	public void setDeprePeriod(Integer deprePeriod) {
		this.deprePeriod = deprePeriod;
	}
	public String getTypeDesc() {
		return typeDesc;
	}
	public void setTypeDesc(String typeDesc) {
		this.typeDesc = typeDesc;
	}
	public Integer getCalMethod() {
		return calMethod;
	}
	public void setCalMethod(Integer calMethod) {
		this.calMethod = calMethod;
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
