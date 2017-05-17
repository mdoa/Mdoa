package com.htsoft.oa.model.flow;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2009 GuangZhou HongTian Software Limited company.
*/
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonManagedReference;

/**
 * ProNodeSet Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProNodeSet extends com.htsoft.core.model.BaseModel {
	/**
	 * 普通任务节点
	 */
	public final static Short NODE_NORMAL=1;
	/**
	 * 并行会签任务节点
	 */
	public final static Short NODE_SIGN_PARALLEL=2;
	
	/**
	 * 串行会签任务节点
	 */
	public final static Short NODE_SIGN_SERIAL=3;
	/**
	 * 分发任务节点
	 */
	public final static Short NODE_SIGN_ISSUE=4;
	
	/**
	 * 允许回退
	 */
	public final static Short ALLOW_BACK=1;
	/**
	 * 不允许回退
	 */
	public final static Short NOT_ALLOW_BACK=0;
	
    protected Long setId;
	protected String deployId;
	protected String jbpmDefId;
	protected String nodeName;
	protected Short nodeType;
	protected String joinNodeName;
	protected Short isAllowBack;
	@JsonManagedReference
	protected com.htsoft.oa.model.flow.ProDefinition proDefinition;


	/**
	 * Default Empty Constructor for class ProNodeSet
	 */
	public ProNodeSet () {
		nodeType=NODE_NORMAL;
		isAllowBack=NOT_ALLOW_BACK;
	}
	
	/**
	 * Default Key Fields Constructor for class ProNodeSet
	 */
	public ProNodeSet (
		 Long in_setId
        ) {
		this.setSetId(in_setId);
    }

	
	public com.htsoft.oa.model.flow.ProDefinition getProDefinition () {
		return proDefinition;
	}	
	
	public void setProDefinition (com.htsoft.oa.model.flow.ProDefinition in_proDefinition) {
		this.proDefinition = in_proDefinition;
	}
    

	/**
	 * 	 * @return Long
     * @hibernate.id column="setId" type="java.lang.Long" generator-class="native"
	 */
	public Long getSetId() {
		return this.setId;
	}
	
	/**
	 * Set the setId
	 */	
	public void setSetId(Long aValue) {
		this.setId = aValue;
	}	

	/**
	 * 定义ID	 * @return Long
	 */
	public Long getDefId() {
		return this.getProDefinition()==null?null:this.getProDefinition().getDefId();
	}
	
	/**
	 * Set the defId
	 */	
	public void setDefId(Long aValue) {
	    if (aValue==null) {
	    	proDefinition = null;
	    } else if (proDefinition == null) {
	        proDefinition = new com.htsoft.oa.model.flow.ProDefinition(aValue);
	        proDefinition.setVersion(new Integer(0));//set a version to cheat hibernate only
	    } else {
	    	//
			proDefinition.setDefId(aValue);
	    }
	}	

	/**
	 * 发布ID	 * @return String
	 * @hibernate.property column="deployId" type="java.lang.String" length="128" not-null="true" unique="false"
	 */
	public String getDeployId() {
		return this.deployId;
	}
	
	/**
	 * Set the deployId
	 * @spring.validator type="required"
	 */	
	public void setDeployId(String aValue) {
		this.deployId = aValue;
	}	

	/**
	 * Jbpm定义ID	 * @return String
	 * @hibernate.property column="jbpmDefId" type="java.lang.String" length="64" not-null="true" unique="false"
	 */
	public String getJbpmDefId() {
		return this.jbpmDefId;
	}
	
	/**
	 * Set the jbpmDefId
	 * @spring.validator type="required"
	 */	
	public void setJbpmDefId(String aValue) {
		this.jbpmDefId = aValue;
	}	

	/**
	 * 节点名称	 * @return String
	 * @hibernate.property column="nodeName" type="java.lang.String" length="256" not-null="true" unique="false"
	 */
	public String getNodeName() {
		return this.nodeName;
	}
	
	/**
	 * Set the nodeName
	 * @spring.validator type="required"
	 */	
	public void setNodeName(String aValue) {
		this.nodeName = aValue;
	}	

	/**
	 * 节点类型
            1=普通任务节点
            2=会签任务节点
            3=分发任务节点	 * @return Short
	 * @hibernate.property column="nodeType" type="java.lang.Short" length="5" not-null="false" unique="false"
	 */
	public Short getNodeType() {
		return this.nodeType;
	}
	
	/**
	 * Set the nodeType
	 */	
	public void setNodeType(Short aValue) {
		this.nodeType = aValue;
	}	

	/**
	 * 合并任务节点	 * @return String
	 * @hibernate.property column="joinNodeName" type="java.lang.String" length="256" not-null="false" unique="false"
	 */
	public String getJoinNodeName() {
		return this.joinNodeName;
	}
	
	/**
	 * Set the joinNodeName
	 */	
	public void setJoinNodeName(String aValue) {
		this.joinNodeName = aValue;
	}	

	/**
	 * 是否允许回退	 * @return Short
	 * @hibernate.property column="isAllowBack" type="java.lang.Short" length="5" not-null="false" unique="false"
	 */
	public Short getIsAllowBack() {
		return this.isAllowBack;
	}
	
	/**
	 * Set the isAllowBack
	 */	
	public void setIsAllowBack(Short aValue) {
		this.isAllowBack = aValue;
	}	

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProNodeSet)) {
			return false;
		}
		ProNodeSet rhs = (ProNodeSet) object;
		return new EqualsBuilder()
				.append(this.setId, rhs.setId)
						.append(this.deployId, rhs.deployId)
				.append(this.jbpmDefId, rhs.jbpmDefId)
				.append(this.nodeName, rhs.nodeName)
				.append(this.nodeType, rhs.nodeType)
				.append(this.joinNodeName, rhs.joinNodeName)
				.append(this.isAllowBack, rhs.isAllowBack)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.setId) 
						.append(this.deployId) 
				.append(this.jbpmDefId) 
				.append(this.nodeName) 
				.append(this.nodeType) 
				.append(this.joinNodeName) 
				.append(this.isAllowBack) 
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("setId", this.setId) 
						.append("deployId", this.deployId) 
				.append("jbpmDefId", this.jbpmDefId) 
				.append("nodeName", this.nodeName) 
				.append("nodeType", this.nodeType) 
				.append("joinNodeName", this.joinNodeName) 
				.append("isAllowBack", this.isAllowBack) 
				.toString();
	}



}
