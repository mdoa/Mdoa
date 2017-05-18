package com.mdoa.repertory.model.crosstab;

import java.util.List;

public class DepartmentUseModel {
	public String departmentId = "";
	public String departmentName = "";
	public String useTypeKey = "";
	public String useTypeValue = "";
	public List<TypeUseDataModel> typeUseDataModels;
	
	public DepartmentUseModel(){
		
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

	public String getUseTypeKey() {
		return useTypeKey;
	}

	public void setUseTypeKey(String useTypeKey) {
		this.useTypeKey = useTypeKey;
	}

	public String getUseTypeValue() {
		return useTypeValue;
	}

	public void setUseTypeValue(String useTypeValue) {
		this.useTypeValue = useTypeValue;
	}

	public List<TypeUseDataModel> getTypeUseDataModels() {
		return typeUseDataModels;
	}

	public void setTypeUseDataModels(List<TypeUseDataModel> typeUseDataModels) {
		this.typeUseDataModels = typeUseDataModels;
	}
	
}
