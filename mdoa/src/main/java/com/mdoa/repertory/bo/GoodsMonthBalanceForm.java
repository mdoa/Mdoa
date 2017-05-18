package com.mdoa.repertory.bo;

import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

public class GoodsMonthBalanceForm extends BaseModel{
	
	private String goodsMonthBalanceId;
	private String goodsId;
	private String goodsName;
	private String goodsSize;// 物品规格
	private Date monthBalanceTime;// 物品月结日期
	private String monthBalanceTimeStr;
	private String lastBalanceTime;
	private String startTime;
	private String endTime;
	private Integer inTotalNumber;
	private Integer outTotalNumber;
	private Double inTotalAmount;
	private Double outTotalAmount;
	private Integer lastTotalBalanceNumber;//上期余量汇总
	private Integer currentTotalBalanceNumber;//本期余量汇总
	private Double lastTotalBalanceAmount;//上期余额汇总
	private Double currentTotalBalanceAmount;//本期余额汇总
	
	
	public Integer getInTotalNumber() {
		return inTotalNumber;
	}

	public void setInTotalNumber(Integer inTotalNumber) {
		this.inTotalNumber = inTotalNumber;
	}

	public Integer getOutTotalNumber() {
		return outTotalNumber;
	}

	public void setOutTotalNumber(Integer outTotalNumber) {
		this.outTotalNumber = outTotalNumber;
	}

	public Double getInTotalAmount() {
		return inTotalAmount;
	}

	public void setInTotalAmount(Double inTotalAmount) {
		this.inTotalAmount = inTotalAmount;
	}

	public Double getOutTotalAmount() {
		return outTotalAmount;
	}

	public void setOutTotalAmount(Double outTotalAmount) {
		this.outTotalAmount = outTotalAmount;
	}

	public Integer getLastTotalBalanceNumber() {
		return lastTotalBalanceNumber;
	}

	public void setLastTotalBalanceNumber(Integer lastTotalBalanceNumber) {
		this.lastTotalBalanceNumber = lastTotalBalanceNumber;
	}

	public Integer getCurrentTotalBalanceNumber() {
		return currentTotalBalanceNumber;
	}

	public void setCurrentTotalBalanceNumber(Integer currentTotalBalanceNumber) {
		this.currentTotalBalanceNumber = currentTotalBalanceNumber;
	}

	public Double getLastTotalBalanceAmount() {
		return lastTotalBalanceAmount;
	}

	public void setLastTotalBalanceAmount(Double lastTotalBalanceAmount) {
		this.lastTotalBalanceAmount = lastTotalBalanceAmount;
	}

	public Double getCurrentTotalBalanceAmount() {
		return currentTotalBalanceAmount;
	}

	public void setCurrentTotalBalanceAmount(Double currentTotalBalanceAmount) {
		this.currentTotalBalanceAmount = currentTotalBalanceAmount;
	}
	
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

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getLastBalanceTime() {
		return lastBalanceTime;
	}

	public void setLastBalanceTime(String lastBalanceTime) {
		this.lastBalanceTime = lastBalanceTime;
	}
	
}
