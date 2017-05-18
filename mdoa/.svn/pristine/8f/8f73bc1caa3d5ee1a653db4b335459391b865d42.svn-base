package com.mdoa.framework.model;

import java.util.Date;
import java.util.List;

public class Role {
    private String roleId;

    private String roleName;

    private String createUserId;

    private String createUserName;

    private Date createTime;

    private String updateUserId;

    private String updateUserName;

    private Date updateTime;

    private String aliveFlag;

    private String remark;
    
    /**
     * 权限名称列表
     */
    private String[] powerIds;
    
    /**
     * 权限名，按照逗号“，”进行分割
     */
    private String powerNamesStr;
    
    /**
     * 权限列表
     * @return
     */
    private List<Power> powers;

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId == null ? null : roleId.trim();
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public String getCreateUserName() {
        return createUserName;
    }

    public void setCreateUserName(String createUserName) {
        this.createUserName = createUserName == null ? null : createUserName.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUpdateUserName() {
        return updateUserName;
    }

    public void setUpdateUserName(String updateUserName) {
        this.updateUserName = updateUserName == null ? null : updateUserName.trim();
    }

    public String getAliveFlag() {
        return aliveFlag;
    }

    public void setAliveFlag(String aliveFlag) {
        this.aliveFlag = aliveFlag == null ? null : aliveFlag.trim();
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }

	public String[] getPowerIds() {
		return powerIds;
	}

	public void setPowerIds(String[] powerIds) {
		this.powerIds = powerIds;
	}

	public String getUpdateUserId() {
		return updateUserId;
	}

	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public List<Power> getPowers() {
		return powers;
	}

	public void setPowers(List<Power> powers) {
		this.powers = powers;
		this.powerNamesStr = "";
		for(Power power : powers){
			this.powerNamesStr = powerNamesStr + "," + power.getPowerName();
		}
		powerNamesStr.substring(1);
	}

	public String getPowerNamesStr() {
		return powerNamesStr;
	}

	public void setPowerNamesStr(String powerNamesStr) {
		this.powerNamesStr = powerNamesStr;
	}
	
}