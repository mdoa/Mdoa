package com.mdoa.framework.bo;

import com.mdoa.base.model.BaseModel;

public class RoleForm extends BaseModel{
	
	private String roleId;
	
	/**
	 * 角色Id列表，以逗号“,”(英文)进行分割，用于批量删除
	 */
	private String roleIds;
	
	private String roleName;
	
	private String createTimeStartStr;
	
	private String createTimeEndStr;
	
	/**
	 * 权限Id列表,以,进行分割
	 */
	private String powerIdsStr;
	
	/**
	 * 
	 */
	private String powers;
	
	public RoleForm(){
		
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getCreateTimeStartStr() {
		return createTimeStartStr;
	}

	public void setCreateTimeStartStr(String createTimeStartStr) {
		this.createTimeStartStr = createTimeStartStr;
	}

	public String getCreateTimeEndStr() {
		return createTimeEndStr;
	}

	public void setCreateTimeEndStr(String createTimeEndStr) {
		this.createTimeEndStr = createTimeEndStr;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getPowerIdsStr() {
		return powerIdsStr;
	}

	public void setPowerIdsStr(String powerIdsStr) {
		this.powerIdsStr = powerIdsStr;
	}

	public String getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(String roleIds) {
		this.roleIds = roleIds;
	}

	public String getPowers() {
		return powers;
	}

	public void setPowers(String powers) {
		this.powers = powers;
	}
}
