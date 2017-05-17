package com.htsoft.oa.model.flow;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2009 GuangZhou HongTian Software Limited company.
 */
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * 表单验证规则 Base Java Bean, base class for the.oa.model, mapped directly to
 * database table
 * 
 * Avoid changing this file if not necessary, will be overwritten.
 * 
 * 
 */
public class FormRule extends com.htsoft.core.model.BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4764137318638938081L;
	protected Long ruleId;
	protected String name;
	protected String rule;
	protected String tipInfo;
	protected String memo;

	/**
	 * Default Empty Constructor for class FormRule
	 */
	public FormRule() {
		super();
	}

	/**
	 * Default Key Fields Constructor for class FormRule
	 */
	public FormRule(Long in_ruleId) {
		this.setRuleId(in_ruleId);
	}

	/**
	 * * @return Long
	 * 
	 * @hibernate.id column="RULEID" type="java.lang.Long"
	 *               generator-class="native"
	 */
	public Long getRuleId() {
		return this.ruleId;
	}

	/**
	 * Set the ruleId
	 */
	public void setRuleId(Long aValue) {
		this.ruleId = aValue;
	}

	/**
	 * * @return String
	 * 
	 * @hibernate.property column="NAME" type="java.lang.String" length="128"
	 *                     not-null="true" unique="false"
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name
	 * 
	 * @spring.validator type="required"
	 */
	public void setName(String aValue) {
		this.name = aValue;
	}

	/**
	 * * @return String
	 * 
	 * @hibernate.property column="RULE" type="java.lang.String" length="128"
	 *                     not-null="true" unique="false"
	 */
	public String getRule() {
		return this.rule;
	}

	/**
	 * Set the rule
	 * 
	 * @spring.validator type="required"
	 */
	public void setRule(String aValue) {
		this.rule = aValue;
	}

	/**
	 * * @return String
	 * 
	 * @hibernate.property column="TIPINFO" type="java.lang.String" length="128"
	 *                     not-null="true" unique="false"
	 */
	public String getTipInfo() {
		return this.tipInfo;
	}

	/**
	 * Set the tipInfo
	 * 
	 * @spring.validator type="required"
	 */
	public void setTipInfo(String aValue) {
		this.tipInfo = aValue;
	}

	/**
	 * * @return String
	 * 
	 * @hibernate.property column="MEMO" type="java.lang.String" length="256"
	 *                     not-null="false" unique="false"
	 */
	public String getMemo() {
		return this.memo;
	}

	/**
	 * Set the memo
	 */
	public void setMemo(String aValue) {
		this.memo = aValue;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof FormRule)) {
			return false;
		}
		FormRule rhs = (FormRule) object;
		return new EqualsBuilder().append(this.ruleId, rhs.ruleId)
				.append(this.name, rhs.name).append(this.rule, rhs.rule)
				.append(this.tipInfo, rhs.tipInfo).append(this.memo, rhs.memo)
				.isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.ruleId)
				.append(this.name).append(this.rule).append(this.tipInfo)
				.append(this.memo).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("ruleId", this.ruleId)
				.append("name", this.name).append("rule", this.rule)
				.append("tipInfo", this.tipInfo).append("memo", this.memo)
				.toString();
	}

}
