package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;

public class PersonWork extends BaseModel{
	private String idCard;
	
    private String workId;

    private String postName;

    private String componyName;

    private String startTimeStr;//string time
   
    private Date startTime;
    
    private String endTimeStr;
    
    private Date endTime;
    
    private String createUserId;
    
    private String createUserName;
    
    private Date createTime;

    private String updateUserId;
    
    private String updateUserName;

    private Date updateTime;

    private String aliveFlag;

    private String workDescribe;
    
    public PersonWork(){
    	
    }

    public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
	}

	public String getWorkId() {
        return workId;
    }

    public void setWorkId(String workId) {
        this.workId = workId == null ? null : workId.trim();
    }

    public String getPostName() {
        return postName;
    }

    public void setPostName(String postName) {
        this.postName = postName == null ? null : postName.trim();
    }

    public String getComponyName() {
        return componyName;
    }

    public void setComponyName(String componyName) {
        this.componyName = componyName == null ? null : componyName.trim();
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

	public void setendTimeStr(String endtimeStr) {
		this.endTimeStr = endtimeStr;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.endTime = dateFormat.parse(endTimeStr);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public String getWorkDescribe() {
        return workDescribe;
    }

    public void setWorkDescribe(String workDescribe) {
        this.workDescribe = workDescribe == null ? null : workDescribe.trim();
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
    
}