package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;
import com.mdoa.util.DateUtil;

public class PersonTrain extends BaseModel{
	
	private String trainDocId;
	
	private String trainName;
	
    private String trainId;
    
    private String startTimeStr;
    
    private Date startTime;

    private String endTimeStr;//string time
    
    private Date endTime;

    private String trainCompany;

    private String createUserName;
    
    private String createUserId;

    private Date createTime;

    private String updateUserId;
    
    private String updateUserName;

    private Date updateTime;

    private String aliveFlag;

    private String joinFlag;

    private String trainDescribe;
    
    

    public String getTrainDocId() {
		return trainDocId;
	}

	public void setTrainDocId(String trainDocId) {
		this.trainDocId = trainDocId;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTrainId() {
        return trainId;
    }

    public void setTrainId(String trainId) {
        this.trainId = trainId == null ? null : trainId.trim();
    }

	
	public Date getStartTime() {
        return startTime;
        
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.startTimeStr = dateFormat.format(startTime);
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.endTimeStr = dateFormat.format(endTime);
    }

    
    
    public String getStartTimeStr() {
		return startTimeStr;
	}

	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.startTime = dateFormat.parse(startTimeStr);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getEndTimeStr() {
		return endTimeStr;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.endTime = dateFormat.parse(endTimeStr);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getTrainCompany() {
        return trainCompany;
    }

    public void setTrainCompany(String trainCompany) {
        this.trainCompany = trainCompany == null ? null : trainCompany.trim();
    }

    

    public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
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
		this.updateUserId = updateUserId;
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

    public String getJoinFlag() {
        return joinFlag;
    }

    public void setJoinFlag(String joinFlag) {
        this.joinFlag = joinFlag == null ? null : joinFlag.trim();
    }

    public String getTrainDescribe() {
        return trainDescribe;
    }

    public void setTrainDescribe(String trainDescribe) {
        this.trainDescribe = trainDescribe == null ? null : trainDescribe.trim();
    }

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getUpdateUserName() {
		return updateUserName;
	}

	public void setUpdateUserName(String updateUserName) {
		this.updateUserName = updateUserName;
	}
}