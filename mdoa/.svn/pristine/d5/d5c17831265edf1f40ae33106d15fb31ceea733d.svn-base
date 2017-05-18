package com.mdoa.personnel.bo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import com.mdoa.base.model.BaseModel;
import com.mdoa.personnel.model.TrainObj;
import com.mdoa.util.StringUtil;

public class TrainApplyForm extends BaseModel{
	private String trainId;//培训ID
	private String trainName;//培训名
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
	private String createUserName;//创建者名字
	private Date updateTime;//更新时间
	private String updateTimeStr;//String类型的更新时间
	private String updateUserId;//更新者Id
	private String updateUserName;//更新者名字
	
	private String trainStreamId;//培训流ID
	private String examineUserId;//审批人ID
	private String examineUserName;//审批人名称
	private String examineIdea;//审批意见
	private String examineStatus;//当前审批状态，1审批中2撤回3通过4驳回
	private String nextExamineUser;//下一级审批人ID
	private String nextExamineUserName;//以一级审批人的名字
	private Date examineTime;//审批时间
	private String examineTimeStr;//String类型的审批时间 
	private String streamType;//流类别  1审批 2备案
	private String aliveFlag;//有效标志位
	//培训对象 
	private String trainObjId;//培训对象Id
	private String departmentId;//培训对象部门Id
	private String[] departmentIds;//
	private String departmentName;//培训对象部门名
	private String[] departmentNames; 
	private String postId;//培训对象岗位Id
	private String[] postIds;
	private String postName;//培训对象岗位名
	private String[] postNames;
	private String[] userIds;
	private String[] userNames;
	private String objTypeFlag;//判定培训是哪种对象1部门2岗位3用户
	private List<TrainObj> objList;//判定培训是哪种对象1部门2岗位3用户
	//培训人员信息
	private String trainDocId;//培训人员信息ID
	private String trainDescribe;//培训描述
	private String trainCompany;//培训公司，0000为本公司
	private String joinFlag;//是否参加了培训(01参加02逃离03病事假)
	//用户部门
	private String userDepartmentId;//用户部门表ID
	private String departmentUrl;//所属部门的URL
	//用户岗位
	private String userPostId;//用户岗位关联ID
	
	
	
	
	public String getNextExamineUserName() {
		return nextExamineUserName;
	}
	public void setNextExamineUserName(String nextExamineUserName) {
		this.nextExamineUserName = nextExamineUserName;
	}
	public String[] getDepartmentIds() {
		return departmentIds;
	}
	public void setDepartmentIds(String[] departmentIds) {
		this.departmentIds = departmentIds;
	}
	public String[] getDepartmentNames() {
		return departmentNames;
	}
	public void setDepartmentNames(String[] departmentNames) {
		this.departmentNames = departmentNames;
	}
	public String[] getPostIds() {
		return postIds;
	}
	public void setPostIds(String[] postIds) {
		this.postIds = postIds;
	}
	public String[] getPostNames() {
		return postNames;
	}
	public void setPostNames(String[] postNames) {
		this.postNames = postNames;
	}
	public String[] getUserIds() {
		return userIds;
	}
	public void setUserIds(String[] userIds) {
		this.userIds = userIds;
	}
	public String[] getUserNames() {
		return userNames;
	}
	public void setUserNames(String[] userNames) {
		this.userNames = userNames;
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
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.startTimeStr=dateFormat.format(startTime);
	}
	public String getStartTimeStr() {
		return startTimeStr;
	}
	public void setStartTimeStr(String startTimeStr) {
		this.startTimeStr = startTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
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
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.endTimeStr=dateFormat.format(endTime);
	}
	public String getEndTimeStr() {
		return endTimeStr;
		
	}
	public void setEndTimeStr(String endTimeStr) {
		this.endTimeStr = endTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
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
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.recordTimeStr=dateFormat.format(recordTime);
	}
	public String getRecordTimeStr() {
		return recordTimeStr;
	}
	public void setRecordTimeStr(String recordTimeStr) {
		this.recordTimeStr = recordTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
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
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.createTimeStr=dateFormat.format(createTime);
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
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
	public String getCreateUserName() {
		return createUserName;
	}
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.updateTimeStr=dateFormat.format(updateTime);
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
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
	
	
	public String getTrainStreamId() {
		return trainStreamId;
	}
	public void setTrainStreamId(String trainStreamId) {
		this.trainStreamId = trainStreamId;
	}
	public String getExamineUserId() {
		return examineUserId;
	}
	public void setExamineUserId(String examineUserId) {
		this.examineUserId = examineUserId;
	}
	public String getExamineUserName() {
		return examineUserName;
	}
	public void setExamineUserName(String examineUserName) {
		this.examineUserName = examineUserName;
	}
	public String getExamineIdea() {
		return examineIdea;
	}
	public void setExamineIdea(String examineIdea) {
		this.examineIdea = examineIdea;
	}
	public String getExamineStatus() {
		return examineStatus;
	}
	public void setExamineStatus(String examineStatus) {
		this.examineStatus = examineStatus;
	}
	public String getNextExamineUser() {
		return nextExamineUser;
	}
	public void setNextExamineUser(String nextExamineUser) {
		this.nextExamineUser = nextExamineUser;
	}
	public Date getExamineTime() {
		return examineTime;
	}
	public void setExamineTime(Date examineTime) {
		this.examineTime = examineTime;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		this.examineTimeStr=dateFormat.format(examineTime);
	}
	public String getExamineTimeStr() {
		return examineTimeStr;
	}
	public void setExamineTimeStr(String examineTimeStr) {
		this.examineTimeStr = examineTimeStr;
		SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd");
		try {
			this.examineTime=dateFormat.parse(examineTimeStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	}
	public String getStreamType() {
		return streamType;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}
	public String getTrainObjId() {
		return trainObjId;
	}
	public void setTrainObjId(String trainObjId) {
		this.trainObjId = trainObjId;
	}
	public String getDepartmentId() {
		return departmentId;
	}
	public void setDepartmentId(String departmentId) {
		this.departmentId = departmentId;
		this.departmentIds = StringUtil.splitString(departmentId);
		for(String deptId : departmentIds){
			deptId = StringUtil.getIdFromUrl(deptId);
		}
	}
	public String getDepartmentName() {
		return departmentName;
	}
	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
		this.departmentNames = StringUtil.splitString(departmentName);
	}
	public String getPostId() {
		return postId;
	}
	public void setPostId(String postId) {
		this.postId = postId;
		this.postIds=postId.split(",");
	}
	public String getPostName() {
		return postName;
	}
	public void setPostName(String postName) {
		this.postName = postName;
		this.postNames = postName.split(",");
	}
	public String getObjTypeFlag() {
		return objTypeFlag;
	}
	public void setObjTypeFlag(String objTypeFlag) {
		this.objTypeFlag = objTypeFlag;
	}
	public String getTrainDocId() {
		return trainDocId;
	}
	public void setTrainDocId(String trainDocId) {
		this.trainDocId = trainDocId;
	}
	public String getTrainDescribe() {
		return trainDescribe;
	}
	public void setTrainDescribe(String trainDescribe) {
		this.trainDescribe = trainDescribe;
	}
	public String getTrainCompany() {
		return trainCompany;
	}
	public void setTrainCompany(String trainCompany) {
		this.trainCompany = trainCompany;
	}
	public String getJoinFlag() {
		return joinFlag;
	}
	public void setJoinFlag(String joinFlag) {
		this.joinFlag = joinFlag;
	}
	public String getUserDepartmentId() {
		return userDepartmentId;
	}
	public void setUserDepartmentId(String userDepartmentId) {
		this.userDepartmentId = userDepartmentId;
	}
	public String getDepartmentUrl() {
		return departmentUrl;
	}
	public void setDepartmentUrl(String departmentUrl) {
		this.departmentUrl = departmentUrl;
	}
	public String getUserPostId() {
		return userPostId;
	}
	public void setUserPostId(String userPostId) {
		this.userPostId = userPostId;
	}
	public List<TrainObj> getObjList() {
		return objList;
	}
	public void setObjList(List<TrainObj> objList) {
		this.objList = objList;
	}
}
