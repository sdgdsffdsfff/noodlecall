package org.fl.noodlecall.console.vo;

import java.util.Date;

public class ServerVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long server_Id;
	private String server_Name;
	private Integer manual_Status;
	private Integer system_Status;
	private String ip;
	private Integer port;
	private String url;
	private String server_Type;
	private String serialize_Type;
	private Integer weight;
	private Date beat_Time;
	
	private String service_Name;
	private String group_Name;
	
	public Long getServer_Id() {
		return server_Id;
	}
	public void setServer_Id(Long server_Id) {
		this.server_Id = server_Id;
	}
	
	public String getServer_Name() {
		return server_Name;
	}
	public void setServer_Name(String server_Name) {
		this.server_Name = server_Name;
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

	public Integer getPort() {
		return this.port;
	}
	public void setPort(Integer port) {
		this.port = port;
	}
	
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		if (url != null && !url.startsWith("/")) {
			url = "/" + url;
		}
		this.url = url;
	}

	public String getServer_Type() {
		return server_Type;
	}
	public void setServer_Type(String server_Type) {
		this.server_Type = server_Type;
	}
	
	public String getSerialize_Type() {
		return serialize_Type;
	}
	public void setSerialize_Type(String serialize_Type) {
		this.serialize_Type = serialize_Type;
	}
	
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
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
			.append("server_Id:").append(server_Id).append(", ")
			.append("server_Name:").append(server_Name).append(", ")
			.append("manual_Status:").append(manual_Status).append(", ")
			.append("system_Status:").append(system_Status).append(", ")
			.append("ip:").append(ip).append(", ")
			.append("port:").append(port).append(", ")
			.append("url:").append(url).append(", ")
			.append("server_Type:").append(server_Type).append(", ")
			.append("serialize_Type:").append(serialize_Type).append(", ")
			.append("weight:").append(weight).append(", ")
			.append("service_Name:").append(service_Name).append(", ")
			.append("group_Name:").append(group_Name)
			.toString();
	}
}
