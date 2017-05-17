package com.htsoft.oa.model.personal;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */

import java.text.SimpleDateFormat;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * DutySection Base Java Bean, base class for the.oa.model, mapped directly to
 * database table
 * 
 * Avoid changing this file if not necessary, will be overwritten.
 * 
 * 
 */
public class DutySection extends com.htsoft.core.model.BaseModel {

	/**
	 */
	private static final long serialVersionUID = 1L;
	
	protected Long sectionId;
	protected String sectionName;
	protected java.util.Date startSignin;
	protected java.util.Date dutyStartTime;
	protected java.util.Date endSignin;
	protected java.util.Date earlyOffTime;
	protected java.util.Date dutyEndTime;
	protected java.util.Date signOutTime;


	/**
	 * Default Empty Constructor for class DutySection
	 */
	public DutySection() {
		super();
	}

	/**
	 * Default Key Fields Constructor for class DutySection
	 */
	public DutySection(Long in_sectionId) {
		this.setSectionId(in_sectionId);
	}

	/**
	 * * @return Integer
	 * 
	 * @hibernate.id column="sectionId" type="java.lang.Integer"
	 *               generator-class="native"
	 */
	public Long getSectionId() {
		return this.sectionId;
	}

	/**
	 * Set the sectionId
	 */
	public void setSectionId(Long aValue) {
		this.sectionId = aValue;
	}

	/**
	 * 开始签到 * @return java.util.Date
	 * 
	 * @hibernate.property column="startSignin" type="java.util.Date"
	 *                     length="19" not-null="true" unique="false"
	 */
	public java.util.Date getStartSignin() {
		return this.startSignin;
	}

	/**
	 * Set the startSignin
	 * 
	 * @spring.validator type="required"
	 */
	public void setStartSignin(java.util.Date aValue) {
		this.startSignin = aValue;
	}

	/**
	 * 上班时间 * @return java.util.Date
	 * 
	 * @hibernate.property column="dutyStartTime" type="java.util.Date"
	 *                     length="19" not-null="true" unique="false"
	 */
	public java.util.Date getDutyStartTime() {
		return this.dutyStartTime;
	}

	/**
	 * Set the dutyStartTime
	 * 
	 * @spring.validator type="required"
	 */
	public void setDutyStartTime(java.util.Date aValue) {
		this.dutyStartTime = aValue;
	}

	public String getSectionName() {
		return sectionName;
	}

	public void setSectionName(String sectionName) {
		this.sectionName = sectionName;
	}

	
	/**
	 * 签到结束时间 * @return java.util.Date
	 * 
	 * @hibernate.property column="endSignin" type="java.util.Date" length="19"
	 *                     not-null="true" unique="false"
	 */
	public java.util.Date getEndSignin() {
		return this.endSignin;
	}

	/**
	 * Set the endSignin
	 * 
	 * @spring.validator type="required"
	 */
	public void setEndSignin(java.util.Date aValue) {
		this.endSignin = aValue;
	}

	

	/**
	 * 早退计时 * @return java.util.Date
	 * 
	 * @hibernate.property column="earlyOffTime" type="java.util.Date"
	 *                     length="19" not-null="true" unique="false"
	 */
	public java.util.Date getEarlyOffTime() {
		return this.earlyOffTime;
	}

	/**
	 * Set the earlyOffTime
	 * 
	 * @spring.validator type="required"
	 */
	public void setEarlyOffTime(java.util.Date aValue) {
		this.earlyOffTime = aValue;
	}

	
	/**
	 * 下班时间 * @return java.util.Date
	 * 
	 * @hibernate.property column="dutyEndTime" type="java.util.Date"
	 *                     length="19" not-null="true" unique="false"
	 */
	public java.util.Date getDutyEndTime() {
		return this.dutyEndTime;
	}

	/**
	 * Set the dutyEndTime
	 * 
	 * @spring.validator type="required"
	 */
	public void setDutyEndTime(java.util.Date aValue) {
		this.dutyEndTime = aValue;
	}


	/**
	 * 签退结束 * @return java.util.Date
	 * 
	 * @hibernate.property column="signOutTime" type="java.util.Date"
	 *                     length="19" not-null="true" unique="false"
	 */
	public java.util.Date getSignOutTime() {
		return this.signOutTime;
	}

	/**
	 * Set the signOutTime
	 * 
	 * @spring.validator type="required"
	 */
	public void setSignOutTime(java.util.Date aValue) {
		this.signOutTime = aValue;
	}


	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof DutySection)) {
			return false;
		}
		DutySection rhs = (DutySection) object;
		return new EqualsBuilder().append(this.sectionId, rhs.sectionId)
				.append(this.startSignin, rhs.startSignin)
				.append(this.dutyStartTime, rhs.dutyStartTime)
				.append(this.endSignin, rhs.endSignin)
				.append(this.earlyOffTime, rhs.earlyOffTime)
				.append(this.dutyEndTime, rhs.dutyEndTime)
				.append(this.signOutTime, rhs.signOutTime).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.sectionId).append(this.startSignin)
				.append(this.dutyStartTime).append(this.endSignin)
				.append(this.earlyOffTime).append(this.dutyEndTime)
				.append(this.signOutTime).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("sectionId", this.sectionId)
				.append("startSignin", this.startSignin)
				.append("dutyStartTime", this.dutyStartTime)
				.append("endSignin", this.endSignin)
				.append("earlyOffTime", this.earlyOffTime)
				.append("dutyEndTime", this.dutyEndTime)
				.append("signOutTime", this.signOutTime).toString();
	}

}
