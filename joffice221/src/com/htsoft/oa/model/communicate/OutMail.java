package com.htsoft.oa.model.communicate;

/*
 *  杭州梦德软件有限公司 JOffice协同办公管理系统   -- http://www.Mendersoft.com
 *  Copyright (C) 2008-2011 GuangZhou HongTian Software Limited company.
 */
import java.util.HashSet;
import java.util.Set;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.annotations.Expose;
import com.htsoft.oa.model.system.FileAttach;

/**
 * OutMail Base Java Bean, base class for the.oa.model, mapped directly to
 * database table
 * 
 * Avoid changing this file if not necessary, will be overwritten.
 * 
 * TODO: add class/table comments 外部邮箱
 */
public class OutMail extends com.htsoft.core.model.BaseModel {

	/**
	 */
	private static final long serialVersionUID = 1L;

	/** 已删除 */
	public static final Short HAVE_DELETE = 1;
	/** 未删除 */
	public static final Short NOT_DELETE = 0;
	/** 已读 */
	public static final Short HAVE_READ = 1;
	/** 未读 */
	public static final Short NOT_READ = 0;
	/** 已回复 */
	public static final Short HAVE_REPLY = 1;
	/** 未回复 */
	public static final Short NOT_REPLY = 0;

	/** 发送类型 */
	public static final String SEND_TYPE = "smtp";

	@Expose
	protected String uid;
	@Expose
	protected String title;
	@Expose
	protected String content;
	@Expose
	protected String senderAddresses;
	@Expose
	protected String senderName;
	@Expose
	protected String receiverAddresses;
	@Expose
	protected String receiverNames;
	@Expose
	protected String cCAddresses;
	@Expose
	protected String cCNames;
	@Expose
	protected String bCCAddresses;
	@Expose
	protected String bCCAnames;
	@Expose
	protected java.util.Date mailDate;
	@Expose
	protected String fileIds;
	// @Expose
	// protected String setId;
	@Expose
	protected String fileNames;
	@Expose
	protected Short readFlag;
	@Expose
	protected Short replyFlag;
	@Expose
	@JsonManagedReference
	protected com.htsoft.oa.model.communicate.OutMailFolder outMailFolder;
	@JsonManagedReference
	protected com.htsoft.oa.model.communicate.OutMailUserSeting outMailUserSeting;
	@Expose
	protected Set<FileAttach> outMailFiles = new HashSet<FileAttach>();
	protected Long userId;
	protected Long mailId;
	protected String img; 

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public Set<FileAttach> getOutMailFiles() {

		return outMailFiles;
	}

	public void setOutMailFiles(Set<FileAttach> outMailFiles) {

		this.outMailFiles = outMailFiles;
	}

	public String getcCAddresses() {
		return cCAddresses;
	}

	public void setcCAddresses(String cCAddresses) {
		this.cCAddresses = cCAddresses;
	}

	public String getcCNames() {
		return cCNames;
	}

	public void setcCNames(String cCNames) {
		this.cCNames = cCNames;
	}

	public String getbCCAddresses() {
		return bCCAddresses;
	}

	public void setbCCAddresses(String bCCAddresses) {
		this.bCCAddresses = bCCAddresses;
	}

	public String getbCCAnames() {
		return bCCAnames;
	}

	public void setbCCAnames(String bCCAnames) {
		this.bCCAnames = bCCAnames;
	}

	/**
	 * Default Empty Constructor for class OutMail
	 */
	public OutMail() {
		super();
	}

	/**
	 * Default Key Fields Constructor for class OutMail
	 */
	public OutMail(Long in_mailId) {
		this.setMailId(in_mailId);
	}

	public com.htsoft.oa.model.communicate.OutMailFolder getOutMailFolder() {
		return outMailFolder;
	}

	public void setOutMailFolder(
			com.htsoft.oa.model.communicate.OutMailFolder in_outMailFolder) {
		this.outMailFolder = in_outMailFolder;
	}

	public com.htsoft.oa.model.communicate.OutMailUserSeting getOutMailUserSeting() {
		return outMailUserSeting;
	}

	public void setOutMailUserSeting(
			com.htsoft.oa.model.communicate.OutMailUserSeting in_outMailUserSeting) {
		this.outMailUserSeting = in_outMailUserSeting;
	}

	/**
	 * Set the userId
	 */

	/**
	 * 文件夹编号 * @return Long
	 */
	public Long getFolderId() {
		return this.getOutMailFolder() == null ? null : this.getOutMailFolder()
				.getFolderId();
	}

	/**
	 * Set the folderId
	 */
	public void setFolderId(Long aValue) {
		if (aValue == null) {
			outMailFolder = null;
		} else if (outMailFolder == null) {
			outMailFolder = new com.htsoft.oa.model.communicate.OutMailFolder(
					aValue);
			outMailFolder.setVersion(new Integer(0));// set a version to cheat
														// hibernate only
		} else {
			outMailFolder.setFolderId(aValue);
		}
	}

	/**
	 * 主题 * @return String
	 * 
	 * @hibernate.property column="title" type="java.lang.String" length="512"
	 *                     not-null="false" unique="false"
	 */
	public String getTitle() {
		return this.title;
	}

	/**
	 * Set the title
	 */
	public void setTitle(String aValue) {
		this.title = aValue;
	}

	/**
	 * 内容 * @return String
	 * 
	 * @hibernate.property column="content" type="java.lang.String"
	 *                     length="1024" not-null="false" unique="false"
	 */
	public String getContent() {
		return this.content;
	}

	/**
	 * Set the content
	 */
	public void setContent(String aValue) {
		this.content = aValue;
	}

	/**
	 * 发件人地址 * @return String
	 * 
	 * @hibernate.property column="senderAddresses" type="java.lang.String"
	 *                     length="128" not-null="true" unique="false"
	 */
	public String getSenderAddresses() {
		return this.senderAddresses;
	}

	/**
	 * Set the senderAddresses
	 * 
	 * @spring.validator type="required"
	 */
	public void setSenderAddresses(String aValue) {
		this.senderAddresses = aValue;
	}

	/**
	 * 发件人地址别名 * @return String
	 * 
	 * @hibernate.property column="senderName" type="java.lang.String"
	 *                     length="128" not-null="false" unique="false"
	 */
	public String getSenderName() {
		return this.senderName;
	}

	/**
	 * Set the senderName
	 */
	public void setSenderName(String aValue) {
		this.senderName = aValue;
	}

	/**
	 * 收件人地址 * @return String
	 * 
	 * @hibernate.property column="receiverAddresses" type="java.lang.String"
	 *                     length="512" not-null="true" unique="false"
	 */
	public String getReceiverAddresses() {
		return this.receiverAddresses;
	}

	/**
	 * Set the receiverAddresses
	 * 
	 * @spring.validator type="required"
	 */
	public void setReceiverAddresses(String aValue) {
		this.receiverAddresses = aValue;
	}

	/**
	 * 收件人地址别名 * @return String
	 * 
	 * @hibernate.property column="receiverNames" type="java.lang.String"
	 *                     length="512" not-null="false" unique="false"
	 */
	public String getReceiverNames() {
		return this.receiverNames;
	}

	/**
	 * Set the receiverNames
	 */
	public void setReceiverNames(String aValue) {
		this.receiverNames = aValue;
	}

	/**
	 * 抄送人地址 * @return String
	 * 
	 * @hibernate.property column="cCAddresses" type="java.lang.String"
	 *                     length="512" not-null="false" unique="false"
	 */
	public String getCCAddresses() {
		return this.cCAddresses;
	}

	/**
	 * Set the cCAddresses
	 */
	public void setCCAddresses(String aValue) {
		this.cCAddresses = aValue;
	}

	/**
	 * 抄送人地址别名 * @return String
	 * 
	 * @hibernate.property column="cCNames" type="java.lang.String" length="512"
	 *                     not-null="false" unique="false"
	 */
	public String getCCNames() {
		return this.cCNames;
	}

	/**
	 * Set the cCNames
	 */
	public void setCCNames(String aValue) {
		this.cCNames = aValue;
	}

	/**
	 * 暗送人地址 * @return String
	 * 
	 * @hibernate.property column="bCCAddresses" type="java.lang.String"
	 *                     length="512" not-null="false" unique="false"
	 */
	public String getBCCAddresses() {
		return this.bCCAddresses;
	}

	/**
	 * Set the bCCAddresses
	 */
	public void setBCCAddresses(String aValue) {
		this.bCCAddresses = aValue;
	}

	/**
	 * 暗送人地址别名 * @return String
	 * 
	 * @hibernate.property column="bCCAnames" type="java.lang.String"
	 *                     length="512" not-null="false" unique="false"
	 */
	public String getBCCAnames() {
		return this.bCCAnames;
	}

	/**
	 * Set the bCCAnames
	 */
	public void setBCCAnames(String aValue) {
		this.bCCAnames = aValue;
	}

	/**
	 * * @return java.util.Date
	 * 
	 * @hibernate.property column="mailDate" type="java.util.Date" length="19"
	 *                     not-null="true" unique="false"
	 */
	public java.util.Date getMailDate() {
		return this.mailDate;
	}

	/**
	 * Set the mailDate
	 * 
	 * @spring.validator type="required"
	 */
	public void setMailDate(java.util.Date aValue) {
		this.mailDate = aValue;
	}

	/**
	 * * @return String
	 * 
	 * @hibernate.property column="fileIds" type="java.lang.String" length="128"
	 *                     not-null="false" unique="false"
	 */
	public String getFileIds() {
		return this.fileIds;
	}

	/**
	 * Set the fileIds
	 */
	public void setFileIds(String aValue) {
		this.fileIds = aValue;
	}

	// /**
	// * * @return String
	// * @hibernate.property column="setId" type="java.lang.String" length="128"
	// not-null="false" unique="false"
	// */
	// public String getSetId() {
	// return this.setId;
	// }
	//
	// /**
	// * Set the setId
	// */
	// public void setSetId(String aValue) {
	// this.setId = aValue;
	// }
	/**
	 * * @return String
	 * 
	 * @hibernate.property column="fileNames" type="java.lang.String"
	 *                     length="128" not-null="false" unique="false"
	 */
	public String getFileNames() {
		return this.fileNames;
	}

	/**
	 * Set the fileNames
	 */
	public void setFileNames(String aValue) {
		this.fileNames = aValue;
	}

	/**
	 * 0:未阅 1:已阅 * @return Short
	 * 
	 * @hibernate.property column="readFlag" type="java.lang.Short" length="5"
	 *                     not-null="true" unique="false"
	 */
	public Short getReadFlag() {
		return this.readFlag;
	}

	/**
	 * Set the readFlag
	 * 
	 * @spring.validator type="required"
	 */
	public void setReadFlag(Short aValue) {
		this.readFlag = aValue;
	}

	/**
	 * 0:未回复 1;已回复 * @return Short
	 * 
	 * @hibernate.property column="replyFlag" type="java.lang.Short" length="5"
	 *                     not-null="true" unique="false"
	 */
	public Short getReplyFlag() {
		return this.replyFlag;
	}

	/**
	 * Set the replyFlag
	 * 
	 * @spring.validator type="required"
	 */
	public void setReplyFlag(Short aValue) {
		this.replyFlag = aValue;
	}

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	public boolean equals(Object object) {
		if (!(object instanceof OutMail)) {
			return false;
		}
		OutMail rhs = (OutMail) object;
		return new EqualsBuilder().append(this.mailId, rhs.mailId)
				.append(this.title, rhs.title)
				.append(this.content, rhs.content)
				.append(this.senderAddresses, rhs.senderAddresses)
				.append(this.senderName, rhs.senderName)
				.append(this.receiverAddresses, rhs.receiverAddresses)
				.append(this.receiverNames, rhs.receiverNames)
				.append(this.cCAddresses, rhs.cCAddresses)
				.append(this.cCNames, rhs.cCNames)
				.append(this.bCCAddresses, rhs.bCCAddresses)
				.append(this.bCCAnames, rhs.bCCAnames)
				.append(this.mailDate, rhs.mailDate)
				.append(this.fileIds, rhs.fileIds)
				.append(this.fileNames, rhs.fileNames)
				.append(this.readFlag, rhs.readFlag)
				.append(this.replyFlag, rhs.replyFlag).isEquals();
	}

	/**
	 * @see java.lang.Object#hashCode()
	 */
	public int hashCode() {
		return new HashCodeBuilder(-82280557, -700257973).append(this.mailId)
				.append(this.title).append(this.content)
				.append(this.senderAddresses).append(this.senderName)
				.append(this.receiverAddresses).append(this.receiverNames)
				.append(this.cCAddresses).append(this.cCNames)
				.append(this.bCCAddresses).append(this.bCCAnames)
				.append(this.mailDate).append(this.fileIds)
				.append(this.fileNames).append(this.readFlag)
				.append(this.replyFlag).toHashCode();
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return new ToStringBuilder(this).append("mailId", this.mailId)
				.append("title", this.title).append("content", this.content)
				.append("senderAddresses", this.senderAddresses)
				.append("senderName", this.senderName)
				.append("receiverAddresses", this.receiverAddresses)
				.append("receiverNames", this.receiverNames)
				.append("cCAddresses", this.cCAddresses)
				.append("cCNames", this.cCNames)
				.append("bCCAddresses", this.bCCAddresses)
				.append("bCCAnames", this.bCCAnames)
				.append("mailDate", this.mailDate)
				.append("fileIds", this.fileIds)
				.append("fileNames", this.fileNames)
				.append("readFlag", this.readFlag)
				.append("replyFlag", this.replyFlag).toString();
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * * @return Long
	 * 
	 * @hibernate.id column="mailId" type="java.lang.Long"
	 *               generator-class="native"
	 */
	public Long getMailId() {
		return this.mailId;
	}

	/**
	 * Set the mailId
	 */
	public void setMailId(Long aValue) {
		this.mailId = aValue;
	}
	
	public String getImg() {
		return img;
	}

	public void setImg(String img) {
		this.img = img;
	}


}
