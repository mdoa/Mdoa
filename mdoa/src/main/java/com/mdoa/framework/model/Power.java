package com.mdoa.framework.model;

public class Power {
	
	private String powerId;
	
	private String powerName;
	
	private String superPowerId;
	
	private String url;
	
	private String aliveFlag;
	
	private String power;
	
	private String powerKey;
	
	
	
	public String getPowerKey() {
		return powerKey;
	}

	public void setPowerKey(String powerKey) {
		this.powerKey = powerKey;
	}

	public Power(){
		
	}

	public String getPowerId() {
		return powerId;
	}

	public void setPowerId(String powerId) {
		this.powerId = powerId;
	}

	public String getPowerName() {
		return powerName;
	}

	public void setPowerName(String powerName) {
		this.powerName = powerName;
	}

	public String getSuperPowerId() {
		return superPowerId;
	}

	public void setSuperPowerId(String superPowerId) {
		this.superPowerId = superPowerId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getAliveFlag() {
		return aliveFlag;
	}

	public void setAliveFlag(String aliveFlag) {
		this.aliveFlag = aliveFlag;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}
}

