package com.mdoa.personnel.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.mdoa.base.model.BaseModel;

public class KpiApplyForm extends BaseModel{
	//前台传来的参数，在这里接收
	private String createUserId;//创建人id
	private String createUserName;//创建人名字
	private String strCreateTime;//string创建时间
	private Date createTime;//创建时间
	private String updateUserId;//修改人ID
	private String updateUserName;//修改人名字
	private String strUpdateTime;//string更新时间
	private Date updateTime;//更新时间
	
	private String kpiGroupId;//kpi组id
	private String kpiGroupName;//kpi组的名字
	private String note;//kpi组描述 	
	private String kpiId;//kpi的id
	private String kpiName;//kpi的名字
	private Integer scoreStandard;//标准分数
    private Integer scoreReality;//实际分数
    private String text;//评分标准描述
	
    private String departmentId;//部门id
    private String departmentName;//部门名称
    private String aliveFlag;//有效标志位
    
    private String groupRecordId;//打分记录组id
    private String scoreUser;//组用户分数
    private String markMonth;//打分月份
    private String markUserName;//打分人
    private String markUserId;//打分人id
    
    private String recordId;//打分记录id
    private String userScore;//用户分数
	private String standardScore;//满分
	private String reason;//评分原因
	
	
	
	public String getRecordId() {
		return recordId;
	}
	public void setRecordId(String recordId) {
		this.recordId = recordId;
	}
	public String getUserScore() {
		return userScore;
	}
	public void setUserScore(String userScore) {
		this.userScore = userScore;
	}
	public String getStandardScore() {
		return standardScore;
	}
	public void setStandardScore(String standardScore) {
		this.standardScore = standardScore;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getGroupRecordId() {
		return groupRecordId;
	}
	public void setGroupRecordId(String groupRecordId) {
		this.groupRecordId = groupRecordId;
	}
	public String getScoreUser() {
		return scoreUser;
	}
	public void setScoreUser(String scoreUser) {
		this.scoreUser = scoreUser;
	}
	public String getMarkMonth() {
		return markMonth;
	}
	public void setMarkMonth(String markMonth) {
		this.markMonth = markMonth;
	}
	public String getMarkUserName() {
		return markUserName;
	}
	public void setMarkUserName(String markUserName) {
		this.markUserName = markUserName;
	}
	public String getMarkUserId() {
		return markUserId;
	}
	public void setMarkUserId(String markUserId) {
		this.markUserId = markUserId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
	}
	
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}
	

	public String getKpiGroupName() {
		return kpiGroupName;
	}
	public void setKpiGroupName(String kpiGroupName) {
		this.kpiGroupName = kpiGroupName;
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
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getUpdateUserId() {
		return updateUserId;
	}
	public void setUpdateUserId(String updateUserId) {
		this.updateUserId = updateUserId;
	}

	public String getStrCreateTime() {
		return strCreateTime;
	}
	public void setStrCreateTime(String strCreateTime) {
		this.strCreateTime = strCreateTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.strCreateTime = dateFormat.format(createTime);
	}
	public String getStrUpdateTime() {
		return strUpdateTime;
	}
	public void setStrUpdateTime(String strUpdateTime) {
		this.strUpdateTime = strUpdateTime;
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		this.strUpdateTime = dateFormat.format(updateTime);
	}
	public String getKpiGroupId() {
		return kpiGroupId;
	}
	public void setKpiGroupId(String kpiGroupId) {
		this.kpiGroupId = kpiGroupId;
	}
	public String getNote() {
		return note;
	}
	public void setNote(String note) {
		this.note = note;
	}
	public String getAliveFlag() {
		return aliveFlag;
	}
	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}
	public String getKpiId() {
		return kpiId;
	}
	public void setKpiId(String kpiId) {
		this.kpiId = kpiId;
	}
	public String getKpiName() {
		return kpiName;
	}
	public void setKpiName(String kpiName) {
		this.kpiName = kpiName;
	}
	public Integer getScoreStandard() {
		return scoreStandard;
	}
	public void setScoreStandard(Integer scoreStandard) {
		this.scoreStandard = scoreStandard;
	}
	public Integer getScoreReality() {
		return scoreReality;
	}
	public void setScoreReality(Integer scoreReality) {
		this.scoreReality = scoreReality;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
	
	
	
	
	
}
