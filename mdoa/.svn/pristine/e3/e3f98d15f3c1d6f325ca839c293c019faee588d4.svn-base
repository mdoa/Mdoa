package com.mdoa.repertory.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 出库记录
 * @author Administrator
 *
 */
public class RepertoryOutRecord {

	private String outRecordId;
	private String goodsId;
	private String repertoryId;
	private String repertoryName;
	private String goodsPositionId;
	private String goodsPositionName;
	private Integer outNumber;
	private Double pretaxAmount;
	private Double taxAmount;
	private String operateUserId;
	private String operateUserName;
	private String strOutTime;//string出库时间
	private Date outTime;//出库时间
	private String getUserId;
	private String getUserName;
	private String getDepartmentId;
	private String getDepartmentName;
	private String record;
	private String useType;
	
	private String createTime;
	private Date strCreateTime;
	private String createUserId;
	private String createUserName;
	private String updateTime;
	private Date strUpdateTime;
	private String updateUserId;
	private String updateUserName;
	private String aliveFlag;
	
	
	
	public String getUseType() {
		return useType;
	}
	public void setUseType(String useType) {
		this.useType = useType;
	}
	public String getGetDepartmentId() {
		return getDepartmentId;
	}
	public void setGetDepartmentId(String getDepartmentId) {
		this.getDepartmentId = getDepartmentId;
	}
	public String getGetDepartmentName() {
		return getDepartmentName;
	}
	public void setGetDepartmentName(String getDepartmentName) {
		this.getDepartmentName = getDepartmentName;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getOutRecordId() {
		return outRecordId;
	}
	public void setOutRecordId(String outRecordId) {
		this.outRecordId = outRecordId;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getRepertoryId() {
		return repertoryId;
	}
	public void setRepertoryId(String repertoryId) {
		this.repertoryId = repertoryId;
	}
	public String getRepertoryName() {
		return repertoryName;
	}
	public void setRepertoryName(String repertoryName) {
		this.repertoryName = repertoryName;
	}
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
	public Integer getOutNumber() {
		return outNumber;
	}
	public void setOutNumber(Integer outNumber) {
		this.outNumber = outNumber;
	}
	public Double getPretaxAmount() {
		return pretaxAmount;
	}
	public void setPretaxAmount(Double pretaxAmount) {
		this.pretaxAmount = pretaxAmount;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public String getOperateUserId() {
		return operateUserId;
	}
	public void setOperateUserId(String operateUserId) {
		this.operateUserId = operateUserId;
	}
	public String getOperateUserName() {
		return operateUserName;
	}
	public void setOperateUserName(String operateUserName) {
		this.operateUserName = operateUserName;
	}
	public String getStrOutTime() {
		return strOutTime;
	}
	public void setStrOutTime(String strOutTime) {
		this.strOutTime = strOutTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			this.outTime = dateFormat.parse(strOutTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public Date getOutTime() {
		return outTime;
	}
	public void setOutTime(Date outTime) {
		this.outTime = outTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.strOutTime = dateFormat.format(outTime);
	}
	public String getGetUserId() {
		return getUserId;
	}
	public void setGetUserId(String getUserId) {
		this.getUserId = getUserId;
	}
	public String getGetUserName() {
		return getUserName;
	}
	public void setGetUserName(String getUserName) {
		this.getUserName = getUserName;
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
