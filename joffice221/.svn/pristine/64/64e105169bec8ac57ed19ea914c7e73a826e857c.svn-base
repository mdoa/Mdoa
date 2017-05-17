package com.htsoft.oa.model.info;

/*
 *  杭州梦德软件有限公司 OA办公管理系统   --  http://www.Mendersoft.com
 *  2017/1/21
 */

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.google.gson.annotations.Expose;
import com.htsoft.core.model.BaseModel;

public class InMessage extends BaseModel {

	/***/
	private static final long serialVersionUID = 1L;

	/** 标记为已读 */
	public final static Short FLAG_READ = 1;
	/** 标记为未读 */
	public final static Short FLAG_UNREAD = 0;

	@Expose
	private Long receiveId;
	@Expose
	@JsonManagedReference
	private ShortMessage shortMessage;
	private Long userId;
	private String userFullname;
	private Short readFlag;
	private Short delFlag;

	public InMessage() {

	}

	public Long getReceiveId() {
		return receiveId;
	}

	public ShortMessage getShortMessage() {
		return shortMessage;
	}

	public void setShortMessage(ShortMessage shortMessage) {
		this.shortMessage = shortMessage;
	}

	public void setReceiveId(Long receiveId) {
		this.receiveId = receiveId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getUserFullname() {
		return userFullname;
	}

	public void setUserFullname(String userFullname) {
		this.userFullname = userFullname;
	}

	public Short getReadFlag() {
		return readFlag;
	}

	public void setReadFlag(Short readFlag) {
		this.readFlag = readFlag;
	}

	public Short getDelFlag() {
		return delFlag;
	}

	public void setDelFlag(Short delFlag) {
		this.delFlag = delFlag;
	}

}
