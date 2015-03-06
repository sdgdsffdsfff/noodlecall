package org.fl.noodlecall.console.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CC_SERVICE_GROUP")
public class ServiceGroupMd implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long serviceGroup_Id;
	private String service_Name;
	private String group_Name;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SERVICEGROUP_ID", nullable = false)
	public Long getServiceGroup_Id() {
		return serviceGroup_Id;
	}
	public void setServiceGroup_Id(Long serviceGroup_Id) {
		this.serviceGroup_Id = serviceGroup_Id;
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
