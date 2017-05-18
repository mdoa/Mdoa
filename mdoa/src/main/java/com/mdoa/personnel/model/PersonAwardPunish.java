package com.mdoa.personnel.model;

import java.util.Date;

import com.mdoa.base.model.BaseModel;

public class PersonAwardPunish extends BaseModel{
    private String awardPunishId;

    private String awardPunishReason;
    
    private String solution;

    private String awardPunishTypeId;

    private String performUserId;

    private String performUserName;

    private String performState;

    private String createUserId;

    private Date creatTime;

    private String updateUserId;

    private Date updateTime;

    private String aliveFlag;
    
    

    public String getAwardPunishReason() {
		return awardPunishReason;
	}

	public void setAwardPunishReason(String awardPunishReason) {
		this.awardPunishReason = awardPunishReason;
	}

	public String getSolution() {
		return solution;
	}

	public void setSolution(String solution) {
		this.solution = solution;
	}

	public String getAwardPunishId() {
        return awardPunishId;
    }

    public void setAwardPunishId(String awardPunishId) {
        this.awardPunishId = awardPunishId == null ? null : awardPunishId.trim();
    }


    public String getAwardPunishTypeId() {
        return awardPunishTypeId;
    }

    public void setAwardPunishTypeId(String awardPunishTypeId) {
        this.awardPunishTypeId = awardPunishTypeId == null ? null : awardPunishTypeId.trim();
    }

    public String getPerformUserId() {
        return performUserId;
    }

    public void setPerformUserId(String performUserId) {
        this.performUserId = performUserId == null ? null : performUserId.trim();
    }

    public String getPerformUserName() {
        return performUserName;
    }

    public void setPerformUserName(String performUserName) {
        this.performUserName = performUserName == null ? null : performUserName.trim();
    }

    public String getPerformState() {
        return performState;
    }

    public void setPerformState(String performState) {
        this.performState = performState == null ? null : performState.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreatTime() {
        return creatTime;
    }

    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    public String getUpdateUserId() {
        return updateUserId;
    }

    public void setUpdateUserId(String updateUserId) {
        this.updateUserId = updateUserId == null ? null : updateUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }
}