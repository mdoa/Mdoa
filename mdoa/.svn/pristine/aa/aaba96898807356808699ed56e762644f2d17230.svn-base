package com.mdoa.personnel.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mdoa.framework.model.Department;
import com.mdoa.framework.model.Post;
import com.mdoa.user.model.UserInfo;

public class Train {
	private String trainId;//员工培训ID
	private String trainName;
	private String trainReason;//培训申请原因
	private Date startTime;//开始时间
	private String startTimeStr;//String类型的开始时间
	private Date endTime;//培训结束时间
	private String endTimeStr;//String类型的培训结束时间
	private String trainNote;//培训记录
	private String trainDetail;//培训描述
	private String recordUserId;//记录人ID
	private Date recordTime;//记录时间
	private String recordTimeStr;//String类型的开始时间
	private String applyState;//申请审批状态(1待审2撤回3通过4驳回5完成)
	private Date createTime;//创建时间
	private String createTimeStr;//String类型的创建时间
	private String createUserId;//创建者Id
	private String createUseName;//创建者名字
	private Date updateTime;//更新时间
	private String updateTimeStr;//String类型的更新时间
	private String updateUserId;//更新者Id
	private String updateUserName;//更新者名字
	private String aliveFlag;//有效标识符
	
	/**
	 * 培训的部门目标的对象列表
	 */
	private List<Department> departmentObjs;
	
	/**
	 * 培训用户目标的对象列表
	 */
	private List<UserInfo> userObjs;
	
	/**
	 * 培训岗位目标的对象列表
	 */
	private List<Post> postObjs;
	
	public String getTrainId() {
		return trainId;
	}
	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}
	public String getTrainReason() {
		return trainReason;
	}
	public void setTrainReason(String trainReason) {
		this.trainReason = trainReason;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.startTimeStr=dateFormat.format(startTime);
	}
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.startTime=dateFormat.parse(startTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public Date getEndTime() {
		return endTime;
	}
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.endTimeStr=dateFormat.format(endTime);
	}
	public String getEndTimeStr() {
		return endTimeStr;
		
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.endTime=dateFormat.parse(endTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getTrainNote() {
		return trainNote;
	}
	public void setTrainNote(String trainNote) {
		this.trainNote = trainNote;
	}
	public String getTrainDetail() {
		return trainDetail;
	}
	public void setTrainDetail(String trainDetail) {
		this.trainDetail = trainDetail;
	}
	public String getRecordUserId() {
		return recordUserId;
	}
	public void setRecordUserId(String recordUserId) {
		this.recordUserId = recordUserId;
	}
	public Date getRecordTime() {
		return recordTime;
	}
	public void setRecordTime(Date recordTime) {
		this.recordTime = recordTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.recordTimeStr=dateFormat.format(recordTime);
	}
	public String getRecordTimeStr() {
		return recordTimeStr;
	}
	public void setRecordTimeStr(String recordTimeStr) {
		this.recordTimeStr = recordTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.recordTime=dateFormat.parse(recordTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getApplyState() {
		return applyState;
	}
	public void setApplyState(String applyState) {
		this.applyState = applyState;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.createTimeStr=dateFormat.format(createTime);
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.createTime=dateFormat.parse(createTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(String createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUseName() {
		return createUseName;
	}
	public void setCreateUseName(String createUseName) {
		this.createUseName = createUseName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		this.updateTimeStr=dateFormat.format(updateTime);
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			this.updateTime=dateFormat.parse(updateTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
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
	public List<Department> getDepartmentObjs() {
		return departmentObjs;
	}
	public void setDepartmentObjs(List<Department> departmentObjs) {
		this.departmentObjs = departmentObjs;
	}
	public List<UserInfo> getUserObjs() {
		return userObjs;
	}
	public void setUserObjs(List<UserInfo> userObjs) {
		this.userObjs = userObjs;
	}
	public List<Post> getPostObjs() {
		return postObjs;
	}
	public void setPostObjs(List<Post> postObjs) {
		this.postObjs = postObjs;
	}
	public String getTrainName() {
		return trainName;
	}
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}
}
