package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;

public class PersonAwardPunishType extends BaseModel{
    private String awardPunishTypeId;
    
    private String typeName;

    private String createUserId;
    
    /**
     * string类型的创建时间
     */
    private String strCreateTime;
   
    private Date createTime;

    private String updateUserId;
    /**
     * string类型的更新时间
     */
    private String strUpdateTime;
    
    private Date updateTime;

    private String aliveFlag;

    private String solution;
    

    public String getStrCreateTime() {
		return strCreateTime;
	}

	public void setStrCreateTime(String strCreateTime) {
		this.strCreateTime = strCreateTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			this.createTime = dateFormat.parse(strCreateTime);			
		}catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.strCreateTime = dateFormat.format(createTime);
	}

	public String getStrUpdateTime() {
		return strUpdateTime;
	}

	public void setStrUpdateTime(String strUpdateTime) {
		this.strUpdateTime = strUpdateTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try{
			this.updateTime = dateFormat.parse(strUpdateTime);
		}catch (ParseException e) {
			e.printStackTrace();
		}
		
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.strUpdateTime = dateFormat.format(updateTime);
	}

	

    public String getAwardPunishTypeId() {
		return awardPunishTypeId;
	}

	public void setAwardPunishTypeId(String awardPunishTypeId) {
		this.awardPunishTypeId = awardPunishTypeId;
	}

	public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName == null ? null : typeName.trim();
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


    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution == null ? null : solution.trim();
    }
}