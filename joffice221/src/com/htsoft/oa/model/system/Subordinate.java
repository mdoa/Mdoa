package com.htsoft.oa.model.system;
/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2009 GuangZhou HongTian Software Limited company.
*/
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * SUBORDINATE Base Java Bean, base class for the.oa.model, mapped directly to database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. 
 *
 * 
 */
public class Subordinate extends com.htsoft.core.model.BaseModel {

	/**
	 * 上级标识
	 */
	public static Short SUPER_FLAG_TRUE = 1;
	/**
	 * 下级标识
	 */
	public static Short SUPER_FLAG_FALSE = 0;
	/**
	 * 同级标识
	 */
	public static Short SUPER_FLAG_VIS= 2;
	
	protected Long subordinateId;
		protected Short relative;
		protected AppUser appUser;
		protected Demension demension;
		protected AppUser jobUser;




	/**
	 * Default Empty Constructor for class RelativeUser
	 */
	public Subordinate() {
		super();
	}



	public Long getSubordinateId() {
		return subordinateId;
	}



	public void setSubordinateId(Long subordinateId) {
		this.subordinateId = subordinateId;
	}



	public Short getRelative() {
		return relative;
	}



	public void setRelative(Short relative) {
		this.relative = relative;
	}



	public Demension getDemension() {
		return demension;
	}



	public void setDemension(Demension demension) {
		this.demension = demension;
	}

	public AppUser getAppUser() {
		return appUser;
	}

	public void setAppUser(AppUser in_appUser) {
		this.appUser = in_appUser;
	}

	public Long getDemID() {
		return this.getDemension() == null ? null : this.getDemension()
				.getDemId();
	}


	/**
	 * Set the reJobId
	 */
	public void setDemID(Long aValue) {
		if (aValue == null) {
			demension = null;
		} else if (demension == null) {
			demension = new Demension(aValue);
		} else {
			//
			demension.setDemId(aValue);
		}
	}

	/**
	 * 所属员工 * @return Long
	 */
	public Long getUserId() {
		return this.getAppUser() == null ? null : this.getAppUser().getUserId();
	}

	/**
	 * Set the userId
	 */
	public void setUserId(Long aValue) {
		if (aValue == null) {
			appUser = null;
		} else if (appUser == null) {
			appUser = new AppUser(aValue);
			appUser.setVersion(new Integer(0));// set a version to cheat
			// hibernate only
		} else {
			//
			appUser.setUserId(aValue);
		}
	}

	/**
	 * 所属员工 * @return Long
	 */
	public Long getJobUserId() {
		return this.getJobUser() == null ? null : this.getJobUser().getUserId();
	}

	/**
	 * Set the userId
	 */
	public void setJobUserId(Long aValue) {
		if (aValue == null) {
			jobUser = null;
		} else if (jobUser == null) {
			jobUser = new AppUser(aValue);
			jobUser.setVersion(new Integer(0));// set a version to cheat
			// hibernate only
		} else {
			//
			jobUser.setUserId(aValue);
		}
	}
	public AppUser getJobUser() {
		return jobUser;
	}

	public void setJobUser(AppUser jobUser) {
		this.jobUser = jobUser;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof Subordinate)) {
			return false;
		}
		Subordinate rhs = (Subordinate) object;
		return new EqualsBuilder().append(this.subordinateId,
				rhs.subordinateId).append(this.jobUser, rhs.jobUser).append(
				this.relative, rhs.relative).append(this.appUser, rhs.appUser)
				.append(this.demension, rhs.demension).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(
				this.subordinateId).append(this.jobUser).append(this.jobUser)
				.append(this.appUser).append(this.demension).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("subordinateId",
				this.subordinateId).append("jobUser", this.jobUser).append(
				"relative", this.relative).append("appUser", this.appUser)
				.append("demension", this.demension).toString();
	}
}
