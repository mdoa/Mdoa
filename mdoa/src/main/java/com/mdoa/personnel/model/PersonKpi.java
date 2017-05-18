package com.mdoa.personnel.model;

import java.util.Date;

import com.mdoa.base.model.BaseModel;

public class PersonKpi extends BaseModel{
    private String kpiId;
    
    private String kpiGroupId;

    private String kpiName;

    private Integer scoreStandard;

    private String text;
    
    private String createUserId;

    private Date createTime;

    private String updateUserId;

    private Date updateTime;

    private String aliveFlag;

    public String getKpiId() {
        return kpiId;
    }

    public void setKpiId(String kpiId) {
        this.kpiId = kpiId == null ? null : kpiId.trim();
    }
    
	public String getKpiGroupId() {
		return kpiGroupId;
	}

	public void setKpiGroupId(String kpiGroupId) {
		this.kpiGroupId = kpiGroupId;
	}

	public String getKpiName() {
        return kpiName;
    }

    public void setKpiName(String kpiName) {
        this.kpiName = kpiName == null ? null : kpiName.trim();
    }

    public Integer getScoreStandard() {
        return scoreStandard;
    }

    public void setScoreStandard(Integer scoreStandard) {
        this.scoreStandard = scoreStandard;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text == null ? null : text.trim();
    }
}