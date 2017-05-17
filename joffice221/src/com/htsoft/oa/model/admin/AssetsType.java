package com.htsoft.oa.model.admin;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */

import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.EqualsBuilder;

/**
 * AssetsType Base Java Bean, base class for the.oa.model, mapped directly to
 * database table
 * 
 * Avoid changing this file if not necessary, will be overwritten. TODO: add
 * class/table comments 资产类型
 */
public class AssetsType extends com.htsoft.core.model.BaseModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	protected Long assetsTypeId;
	protected String typeName;

	/**
	 * Default Empty Constructor for class AssetsType
	 */
	public AssetsType() {
		super();
	}

	/**
	 * Default Key Fields Constructor for class AssetsType
	 */
	public AssetsType(Long assetsTypeId) {
		this.setAssetsTypeId(assetsTypeId);
	}

	/**
	 * * @return Long
	 * 
	 * @hibernate.id column="assetsTypeId" type="java.lang.Long"
	 *               generator-class="native"
	 */
	public Long getAssetsTypeId() {
		return this.assetsTypeId;
	}

	/**
	 * Set the assetsTypeId
	 */
	public void setAssetsTypeId(Long assetsTypeId) {
		this.assetsTypeId = assetsTypeId;
	}

	/**
	 * 分类名称 * @return String
	 * 
	 * @hibernate.property column="typeName" type="java.lang.String"
	 *                     length="128" not-null="true" unique="false"
	 */
	public String getTypeName() {
		return this.typeName;
	}

	/**
	 * Set the typeName
	 * 
	 * @spring.validator type="required"
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof AssetsType)) {
			return false;
		}
		AssetsType rhs = (AssetsType) object;
		return new EqualsBuilder().append(this.assetsTypeId, rhs.assetsTypeId)
				.append(this.typeName, rhs.typeName).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973)
				.append(this.assetsTypeId).append(this.typeName).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this)
				.append("assetsTypeId", this.assetsTypeId)
				.append("typeName", this.typeName).toString();
	}

}
