package com.mdoa.repertory.model;

import java.util.Date;

/**
 * 仓库商品仓位
 * @author Administrator
 *
 */
public class RepertoryGoodsPosition {

	private String goodsPositionId;
	private String goodsPositionName;
	private String goodsId;
	private Integer goodsNumber;
	private String repertoryId;
	private String createTime;
	private Date strCreateTime;
	private String createUserId;
	private String createUserName;
	private String updateTime;
	private Date strUpdateTime;
	private String updateUserId;
	private String updateUserName;
	private String aliveFlag;
	public String getGoodsPositionId() {
		return goodsPositionId;
	}
	public void setGoodsPositionId(String goodsPositionId) {
		this.goodsPositionId = goodsPositionId;
	}
	public String getGoodsPositionName() {
		return goodsPositionName;
	}
	public void setGoodsPositionName(String goodsPositionName) {
		this.goodsPositionName = goodsPositionName;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	public Integer getGoodsNumber() {
		return goodsNumber;
	}
	public void setGoodsNumber(Integer goodsNumber) {
		this.goodsNumber = goodsNumber;
	}
	public String getRepertoryId() {
		return repertoryId;
	}
	public void setRepertoryId(String repertoryId) {
		this.repertoryId = repertoryId;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public Date getStrCreateTime() {
		return strCreateTime;
	}
	public void setStrCreateTime(Date strCreateTime) {
		this.strCreateTime = strCreateTime;
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public String getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}
	public Date getStrUpdateTime() {
		return strUpdateTime;
	}
	public void setStrUpdateTime(Date strUpdateTime) {
		this.strUpdateTime = strUpdateTime;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}
	public String getUpdateUserName() {
		return updateUserName;
	}
	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	
	
	
}
