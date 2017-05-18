package com.mdoa.personnel.bo;

/**
 * 福利审核信息表单
 * @author Administrator
 *
 */
public class WelfareApplyExamineForm {
	private String welfareStreamId;
	private String welfareId;
	private String examineIdea;//审核意见
	private String examineStatus;//审核状态 1 审核中 2 撤回 3 通过 4 驳回 
	private String nextExamineUserId;//下一个审核人ID
	private String nextExamineUserName;//下一个审核人姓名
	private String examineUserId;
	private String examineUserName;
	private String updateUserId;
	private String updateUserName;
	private String recordUserId;
	private String recordUserName;
	private String welfareRecordId;
	private String finishFlag;
	private Double budgetAmount;
	private int population;
	
	public int getPopulation() {
		return population;
	}
	public void setPopulation(int population) {
		this.population = population;
	}
	public Double getBudgetAmount() {
		return budgetAmount;
	}
	public void setBudgetAmount(Double budgetAmount) {
		this.budgetAmount = budgetAmount;
	}
	public String getWelfareRecordId() {
		return welfareRecordId;
	}
	public void setWelfareRecordId(String welfareRecordId) {
		this.welfareRecordId = welfareRecordId;
	}
	public String getFinishFlag() {
		return finishFlag;
	}
	public void setFinishFlag(String finishFlag) {
		this.finishFlag = finishFlag;
	}
	public String getWelfareStreamId() {
		return welfareStreamId;
	}
	public void setWelfareStreamId(String welfareStreamId) {
		this.welfareStreamId = welfareStreamId;
	}
	public String getWelfareId() {
		return welfareId;
	}
	public void setWelfareId(String welfareId) {
		this.welfareId = welfareId;
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
	public String getNextExamineUserName() {
		return nextExamineUserName;
	}
	public void setNextExamineUserName(String nextExamineUserName) {
		this.nextExamineUserName = nextExamineUserName;
	}
	public String getNextExamineUserId() {
		return nextExamineUserId;
	}
	public void setNextExamineUserId(String nextExamineUserId) {
		this.nextExamineUserId = nextExamineUserId;
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
	
	/**
	 * 将本类型变量转成WelfareApplyForm变量的方法
	 * @return
	 */
	public WelfareApplyForm transToApplyForm(){
		WelfareApplyForm welfareApplyForm=new WelfareApplyForm();
		welfareApplyForm.setWelfareId(this.welfareId);
		welfareApplyForm.setExamineUserId(this.nextExamineUserId);
		welfareApplyForm.setExamineUserName(this.nextExamineUserName);
		return welfareApplyForm;
	}
	public String getRecordUserId() {
		return recordUserId;
	}
	public void setRecordUserId(String recordUserId) {
		this.recordUserId = recordUserId;
	}
	public String getRecordUserName() {
		return recordUserName;
	}
	public void setRecordUserName(String recordUserName) {
		this.recordUserName = recordUserName;
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
	
}
