package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;

public class PersonPack extends BaseModel{
    private String packId;

    private String strStarTime;//string的时间
    
    private Date startTime;

    private String strEndTime;
    
    private Date endTime;
    
    private String strTryStarTime;
    
    private Date tryStarTime;//试用期的开始时间
    
    private String strTryEndTime;
    
    private Date tryEndTime;//试用期的结束时间

    private String createUserId;

    private Date createTime;

    private String updateUserId;

    private Date updateTime;
    
    private String packFlag;//合同标志位 0初聘 1续签 2返聘

    private String aliveFlag;
    
    

    public String getPackFlag() {
		return packFlag;
	}

	public void setPackFlag(String packFlag) {
		this.packFlag = packFlag;
	}

	public String getStrTryStarTime() {
		return strTryStarTime;
	}

	public void setStrTryStarTime(String strTryStarTime) {
		this.strTryStarTime = strTryStarTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.tryStarTime = dateFormat.parse(strTryStarTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getTryStarTime() {
		return tryStarTime;
	}

	public void setTryStarTime(Date tryStarTime) {
		this.tryStarTime = tryStarTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.strTryStarTime = dateFormat.format(tryStarTime);
	}

	public String getStrTryEndTime() {
		return strTryEndTime;
	}

	public void setStrTryEndTime(String strTryEndTime) {
		this.strTryEndTime = strTryEndTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.tryEndTime = dateFormat.parse(strTryEndTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Date getTryEndTime() {
		return tryEndTime;
	}

	public void setTryEndTime(Date tryEndTime) {
		this.tryEndTime = tryEndTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.strTryEndTime = dateFormat.format(tryEndTime);
	}

	
	
	
	public String getPackId() {
        return packId;
    }

    public void setPackId(String packId) {
        this.packId = packId == null ? null : packId.trim();
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.strStarTime = dateFormat.format(startTime);
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        this.strEndTime = dateFormat.format(endTime);
    }

    public String getStrStarTime() {
		return strStarTime;
	}

	public void setStrStarTime(String strStarTime) {
		this.strStarTime = strStarTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.startTime = dateFormat.parse(strStarTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public String getStrEndTime() {
		return strEndTime;
	}

	public void setStrEndTime(String strEndTime) {
		this.strEndTime = strEndTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		try{
			this.endTime = dateFormat.parse(strEndTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
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
}