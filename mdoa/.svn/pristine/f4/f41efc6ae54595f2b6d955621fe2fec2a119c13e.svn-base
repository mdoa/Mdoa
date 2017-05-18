package com.mdoa.repertory.bo;

import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

/**
 * 物品类别月结报表表单类
 * 
 * @author Administrator
 *
 */
public class TypeMonthBalanceForm extends BaseModel {

	private String goodsTypeId;
	private String goodsTypeIds;
	private String goodsTypeUrl;
	private String goodsTypeUrls;
	private String goodsTypeName;
	private Date monthBalanceTime;
	private String monthBalanceTimeStr;
	private Date startTime;
	private String startTimeStr;
	private Date endTime;
	private String endTimeStr;
	private Double lastBalanceAmount;
	private Double currentOutAmount;
	private Double currentInAmount;
	private Double currentBalanceAmount;

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		this.startTimeStr = DateUtil.dateToStr(startTime, "yy-MM-dd");
	}

	public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
		this.startTime = DateUtil.strToDate(startTimeStr, "yy-MM-dd");
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		this.endTimeStr = DateUtil.dateToStr(endTime, "yy-MM-dd");
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
		this.startTime = DateUtil.strToDate(endTimeStr, "yy-MM-dd");
	}

	public Double getLastBalanceAmount() {
		return lastBalanceAmount;
	}

	public void setLastBalanceAmount(Double lastBalanceAmount) {
		this.lastBalanceAmount = lastBalanceAmount;
	}

	public Double getCurrentOutAmount() {
		return currentOutAmount;
	}

	public void setCurrentOutAmount(Double currentOutAmount) {
		this.currentOutAmount = currentOutAmount;
	}

	public Double getCurrentInAmount() {
		return currentInAmount;
	}

	public void setCurrentInAmount(Double currentInAmount) {
		this.currentInAmount = currentInAmount;
	}

	public Double getCurrentBalanceAmount() {
		return currentBalanceAmount;
	}

	public void setCurrentBalanceAmount(Double currentBalanceAmount) {
		this.currentBalanceAmount = currentBalanceAmount;
	}

	public String getGoodsTypeId() {
		return goodsTypeId;
	}

	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}

	public String getGoodsTypeUrl() {
		return goodsTypeUrl;
	}

	public void setGoodsTypeUrl(String goodsTypeUrl) {
		this.goodsTypeUrl = goodsTypeUrl;
	}

	public String getGoodsTypeName() {
		return goodsTypeName;
	}

	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}

	public Date getMonthBalanceTime() {
		return monthBalanceTime;
	}

	public void setMonthBalanceTime(Date monthBalanceTime) {
		this.monthBalanceTime = monthBalanceTime;
		this.monthBalanceTimeStr = DateUtil.dateToStr(monthBalanceTime, "yy-MM-dd");
	}

	public String getMonthBalanceTimeStr() {
		return monthBalanceTimeStr;
	}

	public void setMonthBalanceTimeStr(String monthBalanceTimeStr) {
		this.monthBalanceTimeStr = monthBalanceTimeStr;
		this.monthBalanceTime = DateUtil.strToDate(monthBalanceTimeStr, "yy-MM-dd");
	}

	public String getGoodsTypeIds() {
		return goodsTypeIds;
	}

	public void setGoodsTypeIds(String goodsTypeIds) {
		String[] ids = goodsTypeIds.split(",");
		goodsTypeIds = "";
		for (int i = 0; i < ids.length; i++) {
			if (i != ids.length - 1) {
				goodsTypeIds += "'" + ids[i] + "',";
			} else {
				goodsTypeIds += "'" + ids[i] + "'";
			}
		}
		this.goodsTypeIds = goodsTypeIds;
	}

	public String getGoodsTypeUrls() {
		return goodsTypeUrls;
	}

	public void setGoodsTypeUrls(String goodsTypeUrls) {
		String[] urls = goodsTypeUrls.split(",");
		goodsTypeUrls = "";
		for (int i = 0; i < urls.length; i++) {
			if (i != urls.length - 1) {
				goodsTypeUrls += "'" + urls[i] + "',";
			} else {
				goodsTypeUrls += "'" + urls[i] + "'";
			}
		}
		this.goodsTypeUrls = goodsTypeUrls;
	}
}
