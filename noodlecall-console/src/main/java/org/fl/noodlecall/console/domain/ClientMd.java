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
@Table(name = "CC_CLIENT", uniqueConstraints = {@UniqueConstraint(columnNames = {"CLIENT_NAME", "IP", "SERVICE_NAME"})})
public class ClientMd implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long client_Id;
	private String client_Name;
	private Integer manual_Status;
	private Integer system_Status;
	private String ip;
	private Date beat_Time;
	
	private String service_Name;
	private String group_Name;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "CLIENT_ID", nullable = false)
	public Long getClient_Id() {
		return client_Id;
	}
	public void setClient_Id(Long client_Id) {
		this.client_Id = client_Id;
	}
	
	@Column(name = "CLIENT_NAME", nullable = false, length = 128)
	public String getClient_Name() {
		return client_Name;
	}
	public void setClient_Name(String client_Name) {
		this.client_Name = client_Name;
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
