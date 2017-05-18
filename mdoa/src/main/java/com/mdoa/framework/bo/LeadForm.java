package com.mdoa.framework.bo;


import java.util.List;

import com.mdoa.base.model.BaseModel;
import com.mdoa.user.model.UserInfo;

public class LeadForm extends BaseModel{
	private String createUserId;
	
	private String updateUserId;
	
	private String createUserName;
	
	private String updateUserName;
	
	/**
	 * 以“,”逗号分割的 下级员工Id
	 */
	private String lowerIds;
	
	/**
	 * 以“,”逗号分割的 下级员工Name
	 */
	private String lowerNames;
	
	/**
	 * 下级员工对象，数组形式
	 */
	private List<UserInfo> lowers;
	
	/**
	 * 用户的上级Id
	 */
	private String leadId;
	
	/**
	 * 领导姓名
	 */
	private String leadName;
	
	public LeadForm(){
		
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getLowerIds() {
		return lowerIds;
	}

	public void setLowerIds(String lowerIds) {
		this.lowerIds = lowerIds;
	}

	public String getLeaderId() {
		return leadId;
	}

	public void setLeadId(String leadId) {
		this.leadId = leadId;
	}

	public String getLeadId() {
		return leadId;
	}

	public String getLeadName() {
		return leadName;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	public String getLowerNames() {
		return lowerNames;
	}

	public void setLowerNames(String lowerNames) {
		this.lowerNames = lowerNames;
	}

	public List<UserInfo> getLowers() {
		return lowers;
	}

	public void setLowers(List<UserInfo> lowers) {
		this.lowers = lowers;
	}
	
}
