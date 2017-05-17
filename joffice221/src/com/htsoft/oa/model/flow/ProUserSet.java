package com.htsoft.oa.model.flow;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2009 GuangZhou HongTian Software Limited company.
*/
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.htsoft.oa.model.system.Demension;

/**
 * ProUserSet Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * TODO: add class/table comments
 */
public class ProUserSet extends com.htsoft.core.model.BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2109707080425259744L;
	/**
	 * 发起人=1
	 */
	public static final short USER_TYPE_START_USER = 1;
	/**
	 * 用户=2
	 */
	public static final short USER_TYPE_USER = 2;
	/**
	 * 角色=3
	 */
	public static final short USER_TYPE_ROLE = 3;
	/**
	 * 岗位=4
	 */
	public static final short USER_TYPE_JOB = 4;
	/**
	 * 部门或组织负责人=5
	 */
	public static final short USER_TYPE_ORG_DEPPOS = 5;
	/**
	 * 部门负责人=6
	 */
	public static final short USER_TYPE_DEP = 6;


	/**
	 * 上下级=7
	 */
	public static final short USER_TYPE_REJOB = 7;
	/**
	 * 组织上下级=8
	 */
	public static final short USER_TYPE_REORG = 8;
	
	/**
	 * 部门
	 */
	public static final short USER_DEP=9;
	
	
	/**
	 * 运算类型为 or=1
	 */
	public static final short COMP_TYPE_OR = 1;
	/**
	 * 运算类型为 and=2
	 */
	public static final short COMP_TYPE_AND = 2;
	/**
	 * 运算类型为 exclude=3
	 */
	public static final short COMP_TYPE_EXCLUDE = 3;
	
	/**
	 * 只查找本级的部分负责人
	 */
	public static final short STRATEGY_LIMIT_END=0;
	/**
	 * 如果本级负责人为空，继续往上级查找
	 */
	public static final short STRATEGY_LIMIT_UP=1;
	/**
	 * 只查找本级的部分所有人
	 */
	public static final short STRATEGY_END=2;
	/**
	 * 如果本级所有人为空，继续往上级查找
	 */
	public static final short STRATEGY_UP=3;

	
    protected Long id;
	protected String deployId;
	protected String jbpmDefId;
	protected String nodeName;
	protected Short userType;
	protected String uids;
	protected String unames;
	protected Short compType;
	protected Integer sn;
	protected com.htsoft.oa.model.flow.ProDefinition proDefinition;
	protected Demension demension;
	protected Short strategy;
	
	
	public Demension getDemension() {
		return demension;
	}

	public void setDemension(Demension demension) {
		this.demension = demension;
	}
	/**
	 * Default Empty Constructor for class ProUserSet
	 */
	public ProUserSet () {
		super();
	}
	
	/**
	 * Default Key Fields Constructor for class ProUserSet
	 */
	public ProUserSet (
		 Long in_id
        ) {
		this.setId(in_id);
    }

	
	public com.htsoft.oa.model.flow.ProDefinition getProDefinition () {
		return proDefinition;
	}	
	
	public void setProDefinition (com.htsoft.oa.model.flow.ProDefinition in_proDefinition) {
		this.proDefinition = in_proDefinition;
	}
    

	/**
	 * 	 * @return Long
     * @hibernate.id column="id" type="java.lang.Long" generator-class="native"
	 */
	public Long getId() {
		return this.id;
	}
	
	/**
	 * Set the id
	 */	
	public void setId(Long aValue) {
		this.id = aValue;
	}	

	/**
	 * 流程定义ID	 * @return Long
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
	 * 	 * @return String
	 * @hibernate.property column="deployId" type="java.lang.String" length="64" not-null="true" unique="false"
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
	 * jbpm流程定义Id	 * @return String
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
	 * 	 * @return String
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
	 * 1=发起人
            2=user
            3=role
            4=岗位
            5=部门、组织
            6=部门、组织负责人
            7=上下级
            	 * @return Short
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
	 * 1=or
            2=and
            3=exclude	 * @return Short
	 * @hibernate.property column="compType" type="java.lang.Short" length="5" not-null="false" unique="false"
	 */
	public Short getCompType() {
		return this.compType;
	}
	
	/**
	 * Set the compType
	 */	
	public void setCompType(Short aValue) {
		this.compType = aValue;
	}	
	/**
	 * 序号	 * @return Integer
	 * @hibernate.property column="sn" type="java.lang.Integer" length="10" not-null="true" unique="false"
	 */
	public Integer getSn() {
		return this.sn;
	}
	
	/**
	 * Set the sn
	 * @spring.validator type="required"
	 */	
	public void setSn(Integer aValue) {
		this.sn = aValue;
	}	
	
	
	public Short getStrategy() {
		return strategy;
	}

	public void setStrategy(Short strategy) {
		this.strategy = strategy;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof ProUserSet)) {
			return false;
		}
		ProUserSet rhs = (ProUserSet) object;
		return new EqualsBuilder()
				.append(this.id, rhs.id)
						.append(this.deployId, rhs.deployId)
				.append(this.jbpmDefId, rhs.jbpmDefId)
				.append(this.nodeName, rhs.nodeName)
				.append(this.userType, rhs.userType)
				.append(this.uids, rhs.uids)
				.append(this.unames, rhs.unames)
				.append(this.compType, rhs.compType)
				.append(this.strategy, rhs.strategy)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.id) 
						.append(this.deployId) 
				.append(this.jbpmDefId) 
				.append(this.nodeName) 
				.append(this.userType) 
				.append(this.uids) 
				.append(this.unames) 
				.append(this.compType) 
				.append(this.strategy)
				.toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("id", this.id) 
						.append("deployId", this.deployId) 
				.append("jbpmDefId", this.jbpmDefId) 
				.append("nodeName", this.nodeName) 
				.append("userType", this.userType) 
				.append("uids", this.uids) 
				.append("unames", this.unames) 
				.append("compType", this.compType) 
				.append("strategy",this.strategy)
				.toString();
	}



}
