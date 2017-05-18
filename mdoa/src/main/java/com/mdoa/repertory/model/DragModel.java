package com.mdoa.repertory.model;

import java.util.Date;
import java.util.List;

import com.mdoa.util.DateUtil;

public class DragModel {

	private String sourceId;
	private String sourceParentId;
	private String sourceName;
	private String targetId;
	private String targetUrl;
	private String targetName;
	private String point;
	private String sourceUrl;
	private String sourceUrlo;
	private String updateUserId;
	private String updateUserName;
	private Date updateTime;
	private String updateTimeStr;
	private TreeModel treeModel;
	private String replaceUrl;
	private String queryUrl;
	private String previousUrl;
	private String targetParentId;
	private List<TreeModel> treeList;
	private boolean flag;
	private boolean mflag;
	private boolean sflag;
	private String flagStr;
	private String ppreviousUrl;
	private String rreplaceUrl;
	private String formerSourceUrl;
	
	
	
	public boolean isSflag() {
		return sflag;
	}
	public void setSflag(boolean sflag) {
		this.sflag = sflag;
	}
	public String getSourceParentId() {
		return sourceParentId;
	}
	public void setSourceParentId(String sourceParentId) {
		this.sourceParentId = sourceParentId;
	}
	public String getSourceUrlo() {
		return sourceUrlo;
	}
	public void setSourceUrlo(String sourceUrlo) {
		this.sourceUrlo = sourceUrlo;
	}
	public String getFormerSourceUrl() {
		return formerSourceUrl;
	}
	public void setFormerSourceUrl(String formerSourceUrl) {
		this.formerSourceUrl = formerSourceUrl;
	}
	public String getPpreviousUrl() {
		return ppreviousUrl;
	}
	public void setPpreviousUrl(String ppreviousUrl) {
		this.ppreviousUrl = ppreviousUrl;
	}
	public String getRreplaceUrl() {
		return rreplaceUrl;
	}
	public void setRreplaceUrl(String rreplaceUrl) {
		this.rreplaceUrl = rreplaceUrl;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public String getFlagStr() {
		return flagStr;
	}
	public void setFlagStr(String flagStr) {
		this.flagStr = flagStr;
	}
	public boolean isMflag() {
		return mflag;
	}
	public void setMflag(boolean mflag) {
		this.mflag = mflag;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public List<TreeModel> getTreeList() {
		return treeList;
	}
	public void setTreeList(List<TreeModel> treeList) {
		this.treeList = treeList;
	}
	public String getTargetParentId() {
		return targetParentId;
	}
	public void setTargetParentId(String targetParentId) {
		this.targetParentId = targetParentId;
	}
	public String getPreviousUrl() {
		return previousUrl;
	}
	public void setPreviousUrl(String previousUrl) {
		this.previousUrl = previousUrl;
	}
	public String getQueryUrl() {
		return queryUrl;
	}
	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
	public String getReplaceUrl() {
		return replaceUrl;
	}
	public void setReplaceUrl(String replaceUrl) {
		this.replaceUrl = replaceUrl;
	}
	public TreeModel getTreeModel() {
		return treeModel;
	}
	public void setTreeModel(TreeModel treeModel) {
		this.treeModel = treeModel;
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
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
		this.updateTimeStr=DateUtil.dateToStr(updateTime);
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		this.updateTime = DateUtil.strToDate(updateTimeStr);
	}
	
	
	@Override
	public String toString() {
		return "DragModel [sourceId=" + sourceId + ", sourceName=" + sourceName + ", targetId=" + targetId
				+ ", targetName=" + targetName + ", point=" + point + ", sourceUrl=" + sourceUrl + ", updateUserId="
				+ updateUserId + ", updateUserName=" + updateUserName + ", updateTime=" + updateTime
				+ ", updateTimeStr=" + updateTimeStr + ", treeModel=" + treeModel + ", replaceUrl=" + replaceUrl
				+ ", queryUrl=" + queryUrl + ", previousUrl=" + previousUrl + ", targetParentId=" + targetParentId
				+ ", treeList=" + treeList + ", flag=" + flag + "]";
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	
	
	
}
