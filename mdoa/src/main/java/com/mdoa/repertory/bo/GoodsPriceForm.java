package com.mdoa.repertory.bo;

import java.util.Date;
import java.util.List;

import com.mdoa.util.DateUtil;


public class GoodsPriceForm {
	
	private String goodsName;
	private String goodsId;
	private List<String> categories;
	private Date nowTime;
	private String nowTimeStr;
	private Date otherTime;
	private String otherTimeStr;
	private Double currentBalanceAmount;
	private Date monthBalanceTime;
	private String monthBalanceTimeStr;
	private Double weightedAverPrice;
	
	
	public Double getWeightedAverPrice() {
		return weightedAverPrice;
	}
	public void setWeightedAverPrice(Double weightedAverPrice) {
		this.weightedAverPrice = weightedAverPrice;
	}
	public Double getCurrentBalanceAmount() {
		return currentBalanceAmount;
	}
	public void setCurrentBalanceAmount(Double currentBalanceAmount) {
		this.currentBalanceAmount = currentBalanceAmount;
	}
	public Date getMonthBalanceTime() {
		return monthBalanceTime;
	}
	public void setMonthBalanceTime(Date monthBalanceTime) {
		this.monthBalanceTime = monthBalanceTime;
		this.monthBalanceTimeStr = DateUtil.dateToStr(monthBalanceTime,"yyyy-MM-dd");
	}
	public String getMonthBalanceTimeStr() {
		return monthBalanceTimeStr;
	}
	public void setMonthBalanceTimeStr(String monthBalanceTimeStr) {
		this.monthBalanceTimeStr = monthBalanceTimeStr;
		this.monthBalanceTime = DateUtil.strToDate(monthBalanceTimeStr,"yyyy-MM-dd");
	}
	public Date getNowTime() {
		return nowTime;
	}
	public void setNowTime(Date nowTime) {
		this.nowTime = nowTime;
		this.nowTimeStr = DateUtil.dateToStr(nowTime,"yyyy-MM-dd");
	}
	public String getNowTimeStr() {
		return nowTimeStr;
	}
	public void setNowTimeStr(String nowTimeStr) {
		this.nowTimeStr = nowTimeStr;
		this.nowTime = DateUtil.strToDate(nowTimeStr,"yyyy-MM-dd");
	}
	public Date getOtherTime() {
		return otherTime;
	}
	public void setOtherTime(Date otherTime) {
		this.otherTime = otherTime;
		this.otherTimeStr = DateUtil.dateToStr(otherTime,"yyyy-MM-dd");
	}
	public String getOtherTimeStr() {
		return otherTimeStr;
	}
	public void setOtherTimeStr(String otherTimeStr) {
		this.otherTimeStr = otherTimeStr;
		this.otherTime = DateUtil.strToDate(otherTimeStr,"yyyy-MM-dd");
	}
	public String getGoodsName() {
		return goodsName;
	}
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}
	public List<String> getCategories() {
		return categories;
	}
	public void setCategories(List<String> categories) {
		this.categories = categories;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	
}
