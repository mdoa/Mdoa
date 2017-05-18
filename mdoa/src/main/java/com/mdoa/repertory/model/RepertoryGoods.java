package com.mdoa.repertory.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;
/**
 * 仓库商品
 * @author Administrator
 *
 */
public class RepertoryGoods extends BaseModel{

	private String goodsId;
	private String goodsTypeId;
	private String goodsName;
	private Integer totalNumber;
	private String goodsTypeUrl;
	private String goodsSize;
	private String unit;
	private Integer warnNumber;
	private Double latestUnitPrice;
	private Double movingAverPrice;
	private Double weightedAverPrice;
	private String record;
	private Date createTime;
	private String createTimeStr;
	private String createUserId;
	private String createUserName;
	private Date updateTime;
	private String updateTimeStr;
	private String updateUserId;
	private String updateUserName;
	private String aliveFlag;
	private Integer inNumber;
	
	
	
	public String getRecord() {
		return record;
	}
	public void setRecord(String record) {
		this.record = record;
	}
	public Integer getInNumber() {
		return inNumber;
	}
	public void setInNumber(Integer inNumber) {
		this.inNumber = inNumber;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public Integer getTotalNumber() {
		return totalNumber;
	}
	public void setTotalNumber(Integer totalNumber) {
		this.totalNumber = totalNumber;
	}
	
	public String getGoodsTypeUrl() {
		return goodsTypeUrl;
	}
	public void setGoodsTypeUrl(String goodsTypeUrl) {
		this.goodsTypeUrl = goodsTypeUrl;
	}
	public String getGoodsSize() {
		return goodsSize;
	}
	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public Integer getWarnNumber() {
		return warnNumber;
	}
	public void setWarnNumber(Integer warnNumber) {
		this.warnNumber = warnNumber;
	}
	public Double getLatestUnitPrice() {
		return latestUnitPrice;
	}
	public void setLatestUnitPrice(Double latestUnitPrice) {
		this.latestUnitPrice = latestUnitPrice;
	}
	public Double getMovingAverPrice() {
		return movingAverPrice;
	}
	public void setMovingAverPrice(Double movingAverPrice) {
		this.movingAverPrice = movingAverPrice;
	}
	public Double getWeightedAverPrice() {
		return weightedAverPrice;
	}
	public void setWeightedAverPrice(Double weightedAverPrice) {
		this.weightedAverPrice = weightedAverPrice;
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
	
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createTimeStr = dateFormat.format(createTime);
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.createTime = dateFormat.parse(createTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.updateTimeStr = dateFormat.format(updateTime);
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.updateTime = dateFormat.parse(updateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	
}
