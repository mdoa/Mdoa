package com.mdoa.framework.bo;

import com.mdoa.base.model.BaseModel;

public class DepartmentForm extends BaseModel{
	
	/**
	 * 父级部门ID
	 */
	private String superDepartmentId;
	
	/**
	 * 父级部门URL
	 */
	private String superDepartmentUrl;
	
	/**
	 * 父部门名称
	 */
	private String superDepartmentName;
	
	/**
	 * 部门Id
	 */
	private String departmentId;
	
	/**
	 * 部门名称
	 */
	private String departmentName;
	
	/**
	 * 根据创建时间查询
	 */
	private String createTimeStartStr;
	
	/**
	 * 根据创建时间查询
	 */
	private String createTimeEndStr;
	
	/**
	 * 员工姓名
	 */
	private String userName;
	
	/**
	 * 是否需要分页,0不需要1需要，默认为1
	 */
	private String hasPage = "1";
	
	public DepartmentForm(){
		
	}

	public String getSuperDepartmentId() {
		return superDepartmentId;
	}

	public void setSuperDepartmentId(String superDepartmentId) {
		this.superDepartmentId = superDepartmentId;
	}

	public String getSuperDepartmentUrl() {
		return superDepartmentUrl;
	}

	public void setSuperDepartmentUrl(String superDepartmentUrl) {
		this.superDepartmentUrl = superDepartmentUrl;
	}

	public String getSuperDepartmentName() {
		return superDepartmentName;
	}

	public void setSuperDepartmentName(String superDepartmentName) {
		this.superDepartmentName = superDepartmentName;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
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

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}

	public String getHasPage() {
		return hasPage;
	}

	public void setHasPage(String hasPage) {
		this.hasPage = hasPage;
	}
	
}
