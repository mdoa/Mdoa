package com.mdoa.util;

public class ExcelModel {
	private String propertyName;//属性名
	private String tableHeader;//中文表头
	private Integer columnWidth;//列宽
	private String dateFormat;//时间格式字符串
	private String propertyType;//属性类型
	
	public String getTableHeader() {
		return tableHeader;
	}
	public void setTableHeader(String tableHeader) {
		this.tableHeader = tableHeader;
	}
	public String getDateFormat() {
		return dateFormat;
	}
	public void setDateFormat(String dateFormat) {
		if(StringUtil.isEmpty(dateFormat)){
			this.dateFormat="";
		}else {
			this.dateFormat=dateFormat.replace("&nbsp;", "");
		}
	}
	public String getPropertyType() {
		if(propertyType == null){
			return "";
		}
		return propertyType;
	}
	public void setPropertyType(String propertyType) {
		this.propertyType = propertyType;
	}
	public Integer getColumnWidth() {
		return columnWidth;
	}
	public void setColumnWidth(Integer columnWidth) {
		this.columnWidth = columnWidth;
	}
	public String getPropertyName() {
		return propertyName;
	}
	public void setPropertyName(String propertyName) {
		this.propertyName = propertyName;
	}
	
	public void parseDateFormat(){
		if(StringUtil.isEmpty(dateFormat)){
			dateFormat="";
		}else {
			dateFormat=dateFormat.replace("&nbsp;", "");
		}
	}
}
