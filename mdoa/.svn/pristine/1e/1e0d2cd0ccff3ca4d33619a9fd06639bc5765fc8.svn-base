package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.util.DateUtil;

public class Welfare {
	private String welfareId;// id
	private String welfareName;
	private String welfareCode;
	private String text;// 描述
	private String reason;// 发放原因
	private Date giveTime;// 发放时间 没有时分秒
	private String giveTimeStr;
	private String recordUserId;// 备案人id
	private String recordUserName;
	private String recordTime;// 备案时间
	private String examineStatus;// 申请状态(1申请2撤回3通过4驳回)
	private Date createTime;
	private String createTimeStr;
	private String createUserId;
	private String createUserName;
	private Date updateTime;
	private String updateTimeStr;
	private String updateUserId;
	private String updateUserName;
	private String aliveFlag;
	private Double budgetAmount;
	private int population;
	private Date startCreateTime;
	private String startCreateTimeStr;
	private Date endCreateTime;
	private String endCreateTimeStr;
	
	
	public Date getStartCreateTime() {
		return startCreateTime;
	}
	public void setStartCreateTime(Date startCreateTime) {
		this.startCreateTime = startCreateTime;
		this.startCreateTimeStr = DateUtil.dateToStr(startCreateTime);
	}
	public String getStartCreateTimeStr() {
		return startCreateTimeStr;
	}
	public void setStartCreateTimeStr(String startCreateTimeStr) {
		this.startCreateTimeStr = startCreateTimeStr;
		this.startCreateTime = DateUtil.strToDate(startCreateTimeStr);
	}
	public Date getEndCreateTime() {
		return endCreateTime;
	}
	public void setEndCreateTime(Date endCreateTime) {
		this.endCreateTime = endCreateTime;
		this.endCreateTimeStr = DateUtil.dateToStr(endCreateTime);
	}
	public String getEndCreateTimeStr() {
		return endCreateTimeStr;
	}
	public void setEndCreateTimeStr(String endCreateTimeStr) {
		this.endCreateTimeStr = endCreateTimeStr;
		this.endCreateTime = DateUtil.strToDate(endCreateTimeStr);
	}
	public int getPopulation() {
		return population;
	}

	public void setPopulation(int population) {
		this.population = population;
	}

	public Double getBudgetAmount() {
		return budgetAmount;
	}

	public void setBudgetAmount(Double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}

	public String getWelfareId() {
		return welfareId;
	}

	public void setWelfareId(String welfareId) {
		this.welfareId = welfareId;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public Date getGiveTime() {
		return giveTime;
	}

	public void setGiveTime(Date giveTime) {
		this.giveTime = giveTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		giveTimeStr = sdf.format(giveTime);
	}

	public String getRecordUserId() {
		return recordUserId;
	}

	public void setRecordUserId(String recordUserId) {
		this.recordUserId = recordUserId;
	}

	public String getRecordTime() {
		return recordTime;
	}

	public void setRecordTime(String recordTime) {
		this.recordTime = recordTime;
	}

	public String getExamineStatus() {
		return examineStatus;
	}

	public void setExamineStatus(String examineStatus) {
		this.examineStatus = examineStatus;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		createTimeStr = sdf.format(createTime);
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

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		updateTimeStr = sdf.format(updateTime);
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

	public String getGiveTimeStr() {
		return giveTimeStr;
	}

	public void setGiveTimeStr(String giveTimeStr) {
		this.giveTimeStr = giveTimeStr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			giveTime = sdf.parse(giveTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			createTime = sdf.parse(createTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			updateTime = sdf.parse(updateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getWelfareName() {
		return welfareName;
	}

	public void setWelfareName(String welfareName) {
		this.welfareName = welfareName;
	}

	public String getWelfareCode() {
		return welfareCode;
	}

	public void setWelfareCode(String welfareCode) {
		this.welfareCode = welfareCode;
	}

	public String getRecordUserName() {
		return recordUserName;
	}

	public void setRecordUserName(String recordUserName) {
		this.recordUserName = recordUserName;
	}

}
