package com.htsoft.oa.model.system;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2009 GuangZhou HongTian Software Limited company.
 */
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * SerialNumber Base Java Bean, base class for the.oa.model, mapped directly to
 * database table
 * 
 * Avoid changing this file if not necessary, will be overwritten.
 * 
 * 
 */
public class SerialNumber extends com.htsoft.core.model.BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// 主键
	protected Long numberId = 0L;
	// 名称
	protected String name;
	// 别名
	protected String alias;
	// 规则
	protected String regulation;
	// 生成方式
	protected Short genType = 1;
	// 流水号长度
	protected Integer noLength;
	// 初始值
	protected Integer initValue;
	// 当前值
	protected Integer curValue;
	// 当前时间
	protected String curDate = "";

	// 步长
	protected Short step = 1;

	/**
	 * 每天生成
	 */
	public static Short genEveryDay = 1;

	/**
	 * 每月生成
	 */
	public static Short genEveryMonth = 2;

	/**
	 * 每年生成
	 */
	public static Short genEveryYear = 3;

	/**
	 * 递增
	 */
	public static Short genIncrease = 4;

	/**
	 * Default Empty Constructor for class SerialNumber
	 */
	public SerialNumber() {
		super();
	}

	/**
	 * Default Key Fields Constructor for class SerialNumber
	 */
	public SerialNumber(Long in_id) {
		this.setNumberId(in_id);
	}

	/**
	 * * @return Long
	 * 
	 * @hibernate.id column="NUMBERID" type="java.lang.Long"
	 *               generator-class="native"
	 */
	public Long getNumberId() {
		return this.numberId;
	}

	/**
	 * Set the id
	 */
	public void setNumberId(Long aValue) {
		this.numberId = aValue;
	}

	/**
	 * * @return String
	 * 
	 * @hibernate.property column="NAME" type="java.lang.String" length="50"
	 *                     not-null="false" unique="false"
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Set the name
	 */
	public void setName(String aValue) {
		this.name = aValue;
	}

	/**
	 * * @return String
	 * 
	 * @hibernate.property column="ALIAS" type="java.lang.String" length="20"
	 *                     not-null="false" unique="false"
	 */
	public String getAlias() {
		return this.alias;
	}

	/**
	 * Set the alias
	 */
	public void setAlias(String aValue) {
		this.alias = aValue;
	}

	/**
	 * * @return String
	 * 
	 * @hibernate.property column="REGULATION" type="java.lang.String"
	 *                     length="100" not-null="false" unique="false"
	 */
	public String getRegulation() {
		return this.regulation;
	}

	/**
	 * Set the regulation
	 */
	public void setRegulation(String aValue) {
		this.regulation = aValue;
	}

	/**
	 * * @return Short
	 * 
	 * @hibernate.property column="GENTYPE" type="java.lang.Short" length="22"
	 *                     not-null="false" unique="false"
	 */
	public Short getGenType() {
		return this.genType;
	}

	/**
	 * Set the genEveryDay
	 */
	public void setGenType(Short aValue) {
		this.genType = aValue;
	}

	/**
	 * * @return Integer
	 * 
	 * @hibernate.property column="NOLENGTH" type="java.lang.Integer"
	 *                     length="22" not-null="false" unique="false"
	 */
	public Integer getNoLength() {
		return this.noLength;
	}

	/**
	 * Set the noLength
	 */
	public void setNoLength(Integer aValue) {
		this.noLength = aValue;
	}

	/**
	 * * @return String
	 * 
	 * @hibernate.property column="CURDATE" type="java.lang.String" length="10"
	 *                     not-null="false" unique="false"
	 */
	public String getCurDate() {
		return this.curDate;
	}

	/**
	 * Set the curDate
	 */
	public void setCurDate(String aValue) {
		this.curDate = aValue;
	}

	/**
	 * * @return Integer
	 * 
	 * @hibernate.property column="INITVALUE" type="java.lang.Integer"
	 *                     length="22" not-null="false" unique="false"
	 */
	public Integer getInitValue() {
		return this.initValue;
	}

	/**
	 * Set the initValue
	 */
	public void setInitValue(Integer aValue) {
		this.initValue = aValue;
	}

	/**
	 * * @return Integer
	 * 
	 * @hibernate.property column="CURVALUE" type="java.lang.Integer"
	 *                     length="22" not-null="false" unique="false"
	 */
	public Integer getCurValue() {
		return this.curValue;
	}

	/**
	 * Set the curValue
	 */
	public void setCurValue(Integer aValue) {
		this.curValue = aValue;
	}

	/**
	 * * @return Long
	 * 
	 * @hibernate.property column="STEP" type="java.lang.Short" length="22"
	 *                     not-null="false" unique="false"
	 */
	public Short getStep() {
		return this.step;
	}

	/**
	 * Set the step
	 */
	public void setStep(Short aValue) {
		this.step = aValue;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof SerialNumber)) {
			return false;
		}
		SerialNumber rhs = (SerialNumber) object;
		return new EqualsBuilder().append(this.numberId, rhs.numberId)
				.append(this.name, rhs.name).append(this.alias, rhs.alias)
				.append(this.regulation, rhs.regulation)
				.append(this.genType, rhs.genType)
				.append(this.noLength, rhs.noLength)
				.append(this.curDate, rhs.curDate)
				.append(this.initValue, rhs.initValue)
				.append(this.curValue, rhs.curValue)
				.append(this.step, rhs.step).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.numberId)
				.append(this.name).append(this.alias).append(this.regulation)
				.append(this.genType).append(this.noLength)
				.append(this.curDate).append(this.initValue)
				.append(this.curValue).append(this.step).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("numberId", this.numberId)
				.append("name", this.name).append("alias", this.alias)
				.append("regulation", this.regulation)
				.append("genType", this.genType)
				.append("noLength", this.noLength)
				.append("curDate", this.curDate)
				.append("curDate", this.initValue)
				.append("curValue", this.curValue).append("step", this.step)
				.toString();
	}

}
