package org.fl.noodlecall.console.vo;

import java.util.Date;

public class ClientVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long client_Id;
	private String client_Name;
	private Integer manual_Status;
	private Integer system_Status;
	private String ip;
	private Date beat_Time;
	
	private String service_Name;
	private String group_Name;
	
	public Long getClient_Id() {
		return client_Id;
	}
	public void setClient_Id(Long client_Id) {
		this.client_Id = client_Id;
	}
	
	public String getClient_Name() {
		return client_Name;
	}
	public void setClient_Name(String client_Name) {
		this.client_Name = client_Name;
	}
	
	public Integer getManual_Status() {
		return manual_Status;
	}
	public void setManual_Status(Integer manual_Status) {
		this.manual_Status = manual_Status;
	}
	
	public Integer getSystem_Status() {
		return system_Status;
	}
	public void setSystem_Status(Integer system_Status) {
		this.system_Status = system_Status;
	}
	
	public String getIp() {
		return this.ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	
	public Date getBeat_Time() {
		return beat_Time;
	}
	public void setBeat_Time(Date beat_Time) {
		this.beat_Time = beat_Time;
	}
	
	public String getService_Name() {
		return service_Name;
	}
	public void setService_Name(String service_Name) {
		this.service_Name = service_Name;
	}
	
	public String getGroup_Name() {
		return group_Name;
	}
	public void setGroup_Name(String group_Name) {
		this.group_Name = group_Name;
	}
	
	public String toString () {
		return new StringBuilder()
			.append("client_Id:").append(client_Id).append(", ")
			.append("client_Name:").append(client_Name).append(", ")
			.append("manual_Status:").append(manual_Status).append(", ")
			.append("system_Status:").append(system_Status).append(", ")
			.append("ip:").append(ip).append(", ")
			.append("service_Name:").append(service_Name).append(", ")
			.append("group_Name:").append(group_Name)
			.toString();
	}
}
