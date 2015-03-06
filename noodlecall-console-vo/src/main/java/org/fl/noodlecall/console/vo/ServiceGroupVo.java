package org.fl.noodlecall.console.vo;

public class ServiceGroupVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long serviceGroup_Id;
	private Long group_Id;
	private String group_Name;
	private Long service_Id;
	private String service_Name;
	
	private Integer manual_Status;
	
	public Long getServiceGroup_Id() {
		return serviceGroup_Id;
	}
	public void setServiceGroup_Id(Long serviceGroup_Id) {
		this.serviceGroup_Id = serviceGroup_Id;
	}
	
	public Long getGroup_Id() {
		return group_Id;
	}
	public void setGroup_Id(Long group_Id) {
		this.group_Id = group_Id;
	}
	
	public String getGroup_Name() {
		return group_Name;
	}
	public void setGroup_Name(String group_Name) {
		this.group_Name = group_Name;
	}
	
	public Long getService_Id() {
		return service_Id;
	}
	public void setService_Id(Long service_Id) {
		this.service_Id = service_Id;
	}
	
	public String getService_Name() {
		return service_Name;
	}
	public void setService_Name(String service_Name) {
		this.service_Name = service_Name;
	}
	
	public Integer getManual_Status() {
		return manual_Status;
	}
	public void setManual_Status(Integer manual_Status) {
		this.manual_Status = manual_Status;
	}
}
