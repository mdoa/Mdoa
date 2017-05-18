package com.mdoa.personnel.model;

import java.util.Date;

import com.mdoa.base.model.BaseModel;

public class PersonPackPhoto extends BaseModel{
    private String photoId;

    private String packId;

    private String photoName;
    
    private String url;
    
    private String urlSmall;    
    
    private Integer currentPage;

    private Date createTime;

    private String createUserId;

    private Date updateTime;

    private String updateUserId;

    private String aliveFlag;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId == null ? null : photoId.trim();
    }

    public String getPackId() {
        return packId;
    }

    public void setPactId(String packId) {
        this.packId = packId == null ? null : packId.trim();
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(String createUserId) {
        this.createUserId = createUserId == null ? null : createUserId.trim();
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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


	public String getPhotoName() {
		return photoName;
	}

	public void setPhotoName(String photoName) {
		this.photoName = photoName;
	}

	public String getUrlSmall() {
		return urlSmall;
	}

	public void setUrlSmall(String urlSmall) {
		this.urlSmall = urlSmall;
	}

	public void setPackId(String packId) {
		this.packId = packId;
	}

	@Override
	public String toString() {
		return "PersonPackPhoto [photoId=" + photoId + ", packId=" + packId + ", photoName=" + photoName + ", url="
				+ url + ", urlSmall=" + urlSmall + ", currentPage=" + currentPage + ", createTime=" + createTime
				+ ", createUserId=" + createUserId + ", updateTime=" + updateTime + ", updateUserId=" + updateUserId
				+ ", aliveFlag=" + aliveFlag + "]";
	}
    
    
}