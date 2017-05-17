package com.htsoft.oa.model.flow;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2009 GuangZhou HongTian Software Limited company.
*/
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * FormButtonRight Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class FormButtonRight extends com.htsoft.core.model.BaseModel {

	//按钮类型为隐藏
    public static final Short HIDE_RIGHT=0;
	//添加按钮类型
	public static final Short ADD_BUTTON=1;
	//删除按钮类型
	public static final Short DEL_BUTTON =2;
	

    protected Long buttonId;
	protected Long tableId;
	protected String tableName;
	protected Short buttonRight;
	protected Short userType;
	protected String uids;
	//0 隐藏，1显示
	protected Short buttonType;
	protected String unames;
	protected String taskName;
	//protected Long mappingId;
	protected com.htsoft.oa.model.flow.FormDefMapping formDefMapping;


	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public Short getButtonType() {
		return buttonType;
	}

	public void setButtonType(Short buttonType) {
		this.buttonType = buttonType;
	}

	/**
	 * Default Empty Constructor for class FormButtonRight
	 */
	public FormButtonRight () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class FormButtonRight
	 */
	public FormButtonRight (
		 Long in_id
        ) {
		this.setButtonId(in_id);
    }

	
	public com.htsoft.oa.model.flow.FormDefMapping getFormDefMapping () {
		return formDefMapping;
	}	
	
	public void setFormDefMapping (com.htsoft.oa.model.flow.FormDefMapping in_formDefMapping) {
		this.formDefMapping = in_formDefMapping;
	}
    

	/**
	 * 	 * @return Long
     * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
	 */
	public Long getButtonId() {
		return this.buttonId;
	}
	
	/**
	 * Set the id
	 */	
	public void setButtonId(Long aValue) {
		this.buttonId = aValue;
	}	

	/**
	 * 	 * @return Long
	 */
	public Long getMappingId() {
		return this.getFormDefMapping()==null?null:this.getFormDefMapping().getMappingId();
	}
	
	/**
	 * Set the mappingId
	 */	
	public void setMappingId(Long aValue) {
	    if (aValue==null) {
	    	formDefMapping = null;
	    } else if (formDefMapping == null) {
	        formDefMapping = new com.htsoft.oa.model.flow.FormDefMapping(aValue);
	        formDefMapping.setVersion(new Integer(0));//set a version to cheat hibernate only
	    } else {
	    	//
			formDefMapping.setMappingId(aValue);
	    }
	}	

	/**
	 * 	 * @return Long
	 * @hibernate.property column="tableId" type="java.lang.Long" length="19" not-null="true" unique="false"
	 */
	public Long getTableId() {
		return this.tableId;
	}
	
	/**
	 * Set the tableId
	 * @spring.validator type="required"
	 */	
	public void setTableId(Long aValue) {
		this.tableId = aValue;
	}	

	/**
	 * 隐藏/显示权限  0 隐藏 1 显示	 * @return Short
	 * @hibernate.property column="right" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getButtonRight() {
		return this.buttonRight;
	}
	
	/**
	 * Set the right
	 * @spring.validator type="required"
	 */	
	public void setButtonRight(Short aValue) {
		this.buttonRight = aValue;
	}	

	/**
	 * 1=发起人;2=user;3=role;4=岗位;5=部门、组织;6=部门、组织负责人;7=上下级	 * @return Short
	 * @hibernate.property column="userType" type="java.lang.Short" length="5" not-null="true" unique="false"
	 */
	public Short getUserType() {
		return this.userType;
	}
	
	/**
	 * Set the userType
	 * @spring.validator type="required"
	 */	
	public void setUserType(Short aValue) {
		this.userType = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="uids" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getUids() {
		return this.uids;
	}
	
	/**
	 * Set the uids
	 */	
	public void setUids(String aValue) {
		this.uids = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="unames" type="java.lang.String" length="65535" not-null="false" unique="false"
	 */
	public String getUnames() {
		return this.unames;
	}
	
	/**
	 * Set the unames
	 */	
	public void setUnames(String aValue) {
		this.unames = aValue;
	}	

	/**
	 * 	 * @return String
	 * @hibernate.property column="taskName" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getTaskName() {
		return this.taskName;
	}
	
	/**
	 * Set the taskName
	 * @spring.validator type="required"
	 */	
	public void setTaskName(String aValue) {
		this.taskName = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof FormButtonRight)) {
			return false;
		}
		FormButtonRight rhs = (FormButtonRight) object;
		return new EqualsBuilder()
				.append(this.buttonId, rhs.buttonId)
				 .append(this.tableId, rhs.tableId)
				  .append(this.tableName, rhs.tableName)
				.append(this.buttonRight, rhs.buttonRight)
				.append(this.userType, rhs.userType)
				.append(this.uids, rhs.uids)
				.append(this.unames, rhs.unames)
				.append(this.taskName, rhs.taskName)
				.append(this.buttonType, rhs.buttonType)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.buttonId) 
						.append(this.tableId) 
						.append(this.tableName) 
				.append(this.buttonRight) 
				.append(this.userType) 
				.append(this.uids) 
				.append(this.unames) 
				.append(this.taskName) 
				.append(this.buttonType)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.buttonId) 
						.append("tableId", this.tableId) 
						.append("tableName", this.tableName)
				.append("buttonRight", this.buttonRight) 
				.append("userType", this.userType) 
				.append("uids", this.uids) 
				.append("unames", this.unames) 
				.append("taskName", this.taskName) 
				.append("buttonType", this.buttonType) 
				.toString();
	}



}
