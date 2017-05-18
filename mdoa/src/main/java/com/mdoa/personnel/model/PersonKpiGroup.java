package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;
import com.mdoa.util.StringUtil;

public class PersonKpiGroup extends BaseModel{
    private String kpiGroupId;

    private String kpiGroupName;
    
    private String note;

    private Date createTime;
    
    private String createTimeStr;

    private String createUserName;

    private String createUserId;

    private String updateUserId;
    
    private String updateUserName;

    private Date updateTime;
    
    private String updateTimeStr;

    private String aliveFlag;
    
    
    
    public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}

	public String getKpiGroupName() {
		return kpiGroupName;
	}

	public void setKpiGroupName(String kpiGroupName) {
		this.kpiGroupName = kpiGroupName;
	}

	public String getCreateTimeStr() {
		return createTimeStr;
	}

	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.createTime = dateFormat.parse(createTimeStr);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getUpdateTimeStr() {
		return updateTimeStr;
	}

	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.updateTime = dateFormat.parse(updateTimeStr);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}


	public String getKpiGroupId() {
        return kpiGroupId;
    }

    public void setKpiGroupId(String kpiGroupId) {
        this.kpiGroupId = kpiGroupId == null ? null : kpiGroupId.trim();
    }


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.createTimeStr = dateFormat.format(createTime);
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.updateTimeStr = dateFormat.format(updateTime);
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }
}