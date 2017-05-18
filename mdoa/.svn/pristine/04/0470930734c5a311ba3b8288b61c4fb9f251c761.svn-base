package com.mdoa.personnel.bo;

import com.mdoa.base.model.BaseModel;

public class GoOutExamineForm extends BaseModel{
	
	private String goOutStreamId;
	private String goOutApplyId;
	private String examineIdea;
	private String examineStatus;
	private String examineUserId;
	private String examineUserName;
	private String nextExamineUserId;
	private String nextExamineUserName;
	private String streamType;
	private String recordIdea;
	
	
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
	public String getGoOutStreamId() {
		return goOutStreamId;
	}
	public void setGoOutStreamId(String goOutStreamId) {
		this.goOutStreamId = goOutStreamId;
	}
	public String getGoOutApplyId() {
		return goOutApplyId;
	}
	public void setGoOutApplyId(String goOutApplyId) {
		this.goOutApplyId = goOutApplyId;
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
	public String getNextExamineUserId() {
		return nextExamineUserId;
	}
	public void setNextExamineUserId(String nextExamineUserId) {
		this.nextExamineUserId = nextExamineUserId;
	}
	public String getNextExamineUserName() {
		return nextExamineUserName;
	}
	public void setNextExamineUserName(String nextExamineUserName) {
		this.nextExamineUserName = nextExamineUserName;
	}
	public String getStreamType() {
		return streamType;
	}
	public void setStreamType(String streamType) {
		this.streamType = streamType;
	}
	public String getRecordIdea() {
		return recordIdea;
	}
	public void setRecordIdea(String recordIdea) {
		this.recordIdea = recordIdea;
	}
	public GoOutApplyForm toApplyForm() {
		GoOutApplyForm goOutApplyForm = new GoOutApplyForm();
		goOutApplyForm.setGoOutApplyId(goOutApplyId);
		goOutApplyForm.setExamineUserId(nextExamineUserId);
		goOutApplyForm.setExamineUserName(nextExamineUserName);
		return goOutApplyForm;
	}
}
