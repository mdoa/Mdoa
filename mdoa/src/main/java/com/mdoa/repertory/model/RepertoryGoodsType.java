package com.mdoa.repertory.model;

import java.util.Date;
import java.util.List;

import com.mdoa.util.DateUtil;

public class RepertoryGoodsType {

	private String goodsTypeId;
	private String goodsTypeName;
	private String goodsTypeUrl;
	private String parentTypeId;
	private Date createTime;
	private String createTimeStr;
	private String createUserId;
	private String createUserName;
	private Date updateTime;
	private String updateTimeStr;
	private String updateUserId;
	private String updateUserName;
	private String aliveFlag;
	private String state;
	private String goodsId;
	
	private String sourceId;
	private String sourceParentId;
	private String sourceName;
	private String targetId;
	private String targetUrl;
	private String targetName;
	private String point;
	private String sourceUrl;
	private String sourceUrlo;
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
	private String action;
	
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getGoodsId() {
		return goodsId;
	}
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getGoodsTypeId() {
		return goodsTypeId;
	}
	public void setGoodsTypeId(String goodsTypeId) {
		this.goodsTypeId = goodsTypeId;
	}
	public String getGoodsTypeName() {
		return goodsTypeName;
	}
	public void setGoodsTypeName(String goodsTypeName) {
		this.goodsTypeName = goodsTypeName;
	}
	public String getGoodsTypeUrl() {
		return goodsTypeUrl;
	}
	public void setGoodsTypeUrl(String goodsTypeUrl) {
		this.goodsTypeUrl = goodsTypeUrl;
	}
	public String getParentTypeId() {
		return parentTypeId;
	}
	public void setParentTypeId(String parentTypeId) {
		this.parentTypeId = parentTypeId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
		this.createTimeStr = DateUtil.dateToStr(createTime);
	}
	public String getCreateTimeStr() {
		return createTimeStr;
	}
	public void setCreateTimeStr(String createTimeStr) {
		this.createTimeStr = createTimeStr;
		this.createTime = DateUtil.strToDate(createTimeStr);
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
		this.updateTimeStr = DateUtil.dateToStr(updateTime);
	}
	public String getUpdateTimeStr() {
		return updateTimeStr;
	}
	public void setUpdateTimeStr(String updateTimeStr) {
		this.updateTimeStr = updateTimeStr;
		this.updateTime = DateUtil.strToDate(updateTimeStr);
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
	public String toString() {
		return "RepertoryGoodsType [goodsTypeId=" + goodsTypeId + ", goodsTypeName=" + goodsTypeName + ", goodsTypeUrl="
				+ goodsTypeUrl + ", parentTypeId=" + parentTypeId + ", createTime=" + createTime + ", createTimeStr="
				+ createTimeStr + ", createUserId=" + createUserId + ", createUserName=" + createUserName
				+ ", updateTime=" + updateTime + ", updateTimeStr=" + updateTimeStr + ", updateUserId=" + updateUserId
				+ ", updateUserName=" + updateUserName + ", aliveFlag=" + aliveFlag + "]";
	}
	public String getSourceId() {
		return sourceId;
	}
	public void setSourceId(String sourceId) {
		this.sourceId = sourceId;
	}
	public String getSourceParentId() {
		return sourceParentId;
	}
	public void setSourceParentId(String sourceParentId) {
		this.sourceParentId = sourceParentId;
	}
	public String getSourceName() {
		return sourceName;
	}
	public void setSourceName(String sourceName) {
		this.sourceName = sourceName;
	}
	public String getTargetId() {
		return targetId;
	}
	public void setTargetId(String targetId) {
		this.targetId = targetId;
	}
	public String getTargetUrl() {
		return targetUrl;
	}
	public void setTargetUrl(String targetUrl) {
		this.targetUrl = targetUrl;
	}
	public String getTargetName() {
		return targetName;
	}
	public void setTargetName(String targetName) {
		this.targetName = targetName;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getSourceUrl() {
		return sourceUrl;
	}
	public void setSourceUrl(String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}
	public String getSourceUrlo() {
		return sourceUrlo;
	}
	public void setSourceUrlo(String sourceUrlo) {
		this.sourceUrlo = sourceUrlo;
	}
	public TreeModel getTreeModel() {
		return treeModel;
	}
	public void setTreeModel(TreeModel treeModel) {
		this.treeModel = treeModel;
	}
	public String getReplaceUrl() {
		return replaceUrl;
	}
	public void setReplaceUrl(String replaceUrl) {
		this.replaceUrl = replaceUrl;
	}
	public String getQueryUrl() {
		return queryUrl;
	}
	public void setQueryUrl(String queryUrl) {
		this.queryUrl = queryUrl;
	}
	public String getPreviousUrl() {
		return previousUrl;
	}
	public void setPreviousUrl(String previousUrl) {
		this.previousUrl = previousUrl;
	}
	public String getTargetParentId() {
		return targetParentId;
	}
	public void setTargetParentId(String targetParentId) {
		this.targetParentId = targetParentId;
	}
	public List<TreeModel> getTreeList() {
		return treeList;
	}
	public void setTreeList(List<TreeModel> treeList) {
		this.treeList = treeList;
	}
	public boolean isFlag() {
		return flag;
	}
	public void setFlag(boolean flag) {
		this.flag = flag;
	}
	public boolean isMflag() {
		return mflag;
	}
	public void setMflag(boolean mflag) {
		this.mflag = mflag;
	}
	public boolean isSflag() {
		return sflag;
	}
	public void setSflag(boolean sflag) {
		this.sflag = sflag;
	}
	public String getFlagStr() {
		return flagStr;
	}
	public void setFlagStr(String flagStr) {
		this.flagStr = flagStr;
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
	public String getFormerSourceUrl() {
		return formerSourceUrl;
	}
	public void setFormerSourceUrl(String formerSourceUrl) {
		this.formerSourceUrl = formerSourceUrl;
	}
	
	

}
