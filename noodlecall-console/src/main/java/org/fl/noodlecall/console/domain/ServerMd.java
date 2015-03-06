package org.fl.noodlecall.console.domain;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "CC_SERVER", uniqueConstraints = {@UniqueConstraint(columnNames = {"IP", "PORT", "URL"})})
public class ServerMd implements java.io.Serializable {

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
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SERVER_ID", nullable = false)
	public Long getServer_Id() {
		return server_Id;
	}
	public void setServer_Id(Long server_Id) {
		this.server_Id = server_Id;
	}
	
	@Column(name = "SERVER_NAME", nullable = false, length = 128)
	public String getServer_Name() {
		return server_Name;
	}
	public void setServer_Name(String server_Name) {
		this.server_Name = server_Name;
	}
	
	@Column(name = "MANUAL_STATUS", nullable = false)
	public Integer getManual_Status() {
		return manual_Status;
	}
	public void setManual_Status(Integer manual_Status) {
		this.manual_Status = manual_Status;
	}
	
	@Column(name = "SYSTEM_STATUS", nullable = false)
	public Integer getSystem_Status() {
		return system_Status;
	}
	public void setSystem_Status(Integer system_Status) {
		this.system_Status = system_Status;
	}
	
	@Column(name = "IP", nullable = false, length = 15)
	public String getIp() {
		return this.ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}

	@Column(name = "PORT", nullable = false, length = 8)
	public int getPort() {
		return this.port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	
	@Column(name = "URL", nullable = false, length = 255, columnDefinition = "varchar(255)")
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	
	@Column(name = "SERVER_TYPE", nullable = false, length = 32)
	public String getServer_Type() {
		return server_Type;
	}
	public void setServer_Type(String server_Type) {
		this.server_Type = server_Type;
	}
	
	@Column(name = "SERIALIZE_TYPE", nullable = false, length = 32)
	public String getSerialize_Type() {
		return serialize_Type;
	}
	public void setSerialize_Type(String serialize_Type) {
		this.serialize_Type = serialize_Type;
	}
	
	@Column(name = "WEIGHT", nullable = false, length = 8)
	public Integer getWeight() {
		return weight;
	}
	public void setWeight(Integer weight) {
		this.weight = weight;
	}
	
	@Column(name = "BEAT_TIME", nullable = true)
	public Date getBeat_Time() {
		return beat_Time;
	}
	public void setBeat_Time(Date beat_Time) {
		this.beat_Time = beat_Time;
	}
	
	@Column(name = "SERVICE_NAME", nullable = true, length = 255, columnDefinition = "varchar(255)")
	public String getService_Name() {
		return service_Name;
	}
	public void setService_Name(String service_Name) {
		this.service_Name = service_Name;
	}
	
	@Column(name = "GROUP_NAME", nullable = true, length = 128)
	public String getGroup_Name() {
		return group_Name;
	}
	public void setGroup_Name(String group_Name) {
		this.group_Name = group_Name;
	}
}
