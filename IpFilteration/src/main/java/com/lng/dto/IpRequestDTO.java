package com.lng.dto;

public class IpRequestDTO {

	private String ip,newIp,description,updatedBy;
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getNewIp() {
		return newIp;
	}
	public void setNewIp(String newIp) {
		this.newIp = newIp;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUpdatedby() {
		return updatedBy;
	}
	public void setUpdatedby(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Override
	public String toString() {
		return "IpRequestDTO [ip=" + ip + ", newIp=" + newIp + ", description=" + description + ", updatedBy="
				+ updatedBy + "]";
	}
	public IpRequestDTO() {
		
	}
}
