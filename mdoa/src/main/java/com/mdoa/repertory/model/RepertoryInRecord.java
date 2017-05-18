package com.mdoa.repertory.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * 入库记录
 * @author Administrator
 *
 */
public class RepertoryInRecord {

	private String inRecordId;
	private String goodsId;
	private String repertoryId;
	private String repertoryName;
	private String goodsPositionId;
	private String goodsPositionName;
	private Integer inNumber;
	private Double pretaxAmount;
	private Double pretaxAverPrice;
	private Double taxRate;
	private Double taxAmount;
	private Double taxAverPrice;
	private String operateUserId;
	private String operateUserName;
	private String strInTime;//string入库时间
	private Date inTime;//入库时间
	private String putUserId;
	private String putUserName;
	private String createTime;
	private Date strCreateTime;
	private String createUserId;
	private String createUserName;
	private String updateTime;
	private Date strUpdateTime;
	private String updateUserId;
	private String updateUserName;
	private String aliveFlag;
	private String record;
	private String provider;
	
	
	public String getProvider() {
		return provider;
	}
	public void setProvider(String provider) {
		this.provider = provider;
	}
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public String getInRecordId() {
		return inRecordId;
	}
	public void setInRecordId(String inRecordId) {
		this.inRecordId = inRecordId;
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
	public Integer getInNumber() {
		return inNumber;
	}
	public void setInNumber(Integer inNumber) {
		this.inNumber = inNumber;
	}
	public Double getPretaxAmount() {
		return pretaxAmount;
	}
	public void setPretaxAmount(Double pretaxAmount) {
		this.pretaxAmount = pretaxAmount;
	}
	public Double getPretaxAverPrice() {
		return pretaxAverPrice;
	}
	public void setPretaxAverPrice(Double pretaxAverPrice) {
		this.pretaxAverPrice = pretaxAverPrice;
	}
	public Double getTaxRate() {
		return taxRate;
	}
	public void setTaxRate(Double taxRate) {
		this.taxRate = taxRate;
	}
	public Double getTaxAmount() {
		return taxAmount;
	}
	public void setTaxAmount(Double taxAmount) {
		this.taxAmount = taxAmount;
	}
	public Double getTaxAverPrice() {
		return taxAverPrice;
	}
	public void setTaxAverPrice(Double taxAverPrice) {
		this.taxAverPrice = taxAverPrice;
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
	public String getStrInTime() {
		return strInTime;
	}
	public void setStrInTime(String strInTime) {
		this.strInTime = strInTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			this.inTime = dateFormat.parse(strInTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public Date getInTime() {
		return inTime;
	}
	public void setInTime(Date inTime) {
		this.inTime = inTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.strInTime = dateFormat.format(inTime);
	}
	public String getPutUserId() {
		return putUserId;
	}
	public void setPutUserId(String putUserId) {
		this.putUserId = putUserId;
	}
	public String getPutUserName() {
		return putUserName;
	}
	public void setPutUserName(String putUserName) {
		this.putUserName = putUserName;
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
