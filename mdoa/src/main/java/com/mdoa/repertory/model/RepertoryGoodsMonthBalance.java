package com.mdoa.repertory.model;

import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

/**
 * 物品月结报表
 * 
 * @author Administrator
 *
 */
public class RepertoryGoodsMonthBalance extends BaseModel {

	private String goodsMonthBalanceId;
	private String goodsId;
	private String goodsName;
	private String goodsSize;// 物品规格
	private Double movingAverPrice;
	private Double weightedAverPrice;
	private Date monthBalanceTime;// 物品月结日期
	private String monthBalanceTimeStr;
	private Date startTime;
	private String startTimeStr;
	private Date endTime;
	private String endTimeStr;
	private Integer lastBalanceNumber;
	private Double lastBalanceAmount;
	private Integer currentBalanceNumber;
	private Double currentBalanceAmount;
	private Integer currentOutNumber;
	private Double currentOutAmount;
	private Integer currentInNumber;
	private Double currentInAmount;
	private Date createTime;
	private String createTimeStr;
	private String createUserId;
	private String createUserName;
	private Date updateTime;
	private String updateTimeStr;
	private String updateUserId;
	private String updateUserName;
	private String aliveFlag;

	public Date getMonthBalanceTime() {
		return monthBalanceTime;
	}

	public void setMonthBalanceTime(Date monthBalanceTime) {
		this.monthBalanceTime = monthBalanceTime;
		this.monthBalanceTimeStr = DateUtil.dateToStr(monthBalanceTime, "yyyy-MM-dd");
	}

	public String getMonthBalanceTimeStr() {
		return monthBalanceTimeStr;
	}

	public void setMonthBalanceTimeStr(String monthBalanceTimeStr) {
		this.monthBalanceTimeStr = monthBalanceTimeStr;
		this.monthBalanceTime = DateUtil.strToDate(monthBalanceTimeStr, "yyyy-MM-dd");
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		this.startTimeStr = DateUtil.dateToStr(startTime, "yyyy-MM-dd");
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
		this.startTime = DateUtil.strToDate(startTimeStr, "yyyy-MM-dd");
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		this.endTimeStr = DateUtil.dateToStr(endTime, "yyyy-MM-dd");
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
		this.endTime = DateUtil.strToDate(endTimeStr, "yyyy-MM-dd");
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		this.createTimeStr = DateUtil.dateToStr(createTime);
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		this.createTime = DateUtil.strToDate(createTimeStr);
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		this.updateTimeStr = DateUtil.dateToStr(updateTime);
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		this.updateTime = DateUtil.strToDate(updateTimeStr);
	}

	public String getGoodsMonthBalanceId() {
		return goodsMonthBalanceId;
	}

	public void setGoodsMonthBalanceId(String goodsMonthBalanceId) {
		this.goodsMonthBalanceId = goodsMonthBalanceId;
	}

	public String getGoodsId() {
		return goodsId;
	}

	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getGoodsSize() {
		return goodsSize;
	}

	public void setGoodsSize(String goodsSize) {
		this.goodsSize = goodsSize;
	}

	public Integer getLastBalanceNumber() {
		return lastBalanceNumber;
	}

	public void setLastBalanceNumber(Integer lastBalanceNumber) {
		this.lastBalanceNumber = lastBalanceNumber;
	}

	public Double getLastBalanceAmount() {
		return lastBalanceAmount;
	}

	public void setLastBalanceAmount(Double lastBalanceAmount) {
		this.lastBalanceAmount = lastBalanceAmount;
	}

	public Integer getCurrentBalanceNumber() {
		return currentBalanceNumber;
	}

	public void setCurrentBalanceNumber(Integer currentBalanceNumber) {
		this.currentBalanceNumber = currentBalanceNumber;
	}

	public Double getCurrentBalanceAmount() {
		return currentBalanceAmount;
	}

	public void setCurrentBalanceAmount(Double currentBalanceAmount) {
		this.currentBalanceAmount = currentBalanceAmount;
	}

	public Integer getCurrentOutNumber() {
		return currentOutNumber;
	}

	public void setCurrentOutNumber(Integer currentOutNumber) {
		this.currentOutNumber = currentOutNumber;
	}

	public Double getCurrentOutAmount() {
		return currentOutAmount;
	}

	public void setCurrentOutAmount(Double currentOutAmount) {
		this.currentOutAmount = currentOutAmount;
	}

	public Integer getCurrentInNumber() {
		return currentInNumber;
	}

	public void setCurrentInNumber(Integer currentInNumber) {
		this.currentInNumber = currentInNumber;
	}

	public Double getCurrentInAmount() {
		return currentInAmount;
	}

	public void setCurrentInAmount(Double currentInAmount) {
		this.currentInAmount = currentInAmount;
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

	@Override
	public String toString() {
		return "RepertoryGoodsMonthBalance [goodsMonthBalanceId=" + goodsMonthBalanceId + ", goodsId=" + goodsId
				+ ", goodsName=" + goodsName + ", goodsSize=" + goodsSize + ", movingAverPrice=" + movingAverPrice
				+ ", weightedAverPrice=" + weightedAverPrice + ", monthBalanceTime=" + monthBalanceTime
				+ ", monthBalanceTimeStr=" + monthBalanceTimeStr + ", startTime=" + startTime + ", startTimeStr="
				+ startTimeStr + ", endTime=" + endTime + ", endTimeStr=" + endTimeStr + ", lastBalanceNumber="
				+ lastBalanceNumber + ", lastBalanceAmount=" + lastBalanceAmount + ", currentBalanceNumber="
				+ currentBalanceNumber + ", currentBalanceAmount=" + currentBalanceAmount + ", currentOutNumber="
				+ currentOutNumber + ", currentOutAmount=" + currentOutAmount + ", currentInNumber=" + currentInNumber
				+ ", currentInAmount=" + currentInAmount + ", createTime=" + createTime + ", createTimeStr="
				+ createTimeStr + ", createUserId=" + createUserId + ", createUserName=" + createUserName
				+ ", updateTime=" + updateTime + ", updateTimeStr=" + updateTimeStr + ", updateUserId=" + updateUserId
				+ ", updateUserName=" + updateUserName + ", aliveFlag=" + aliveFlag + "]";
	}
	
	
}
