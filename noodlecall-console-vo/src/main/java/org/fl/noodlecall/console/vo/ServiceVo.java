package org.fl.noodlecall.console.vo;

public class ServiceVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long service_Id;
	private String service_Name;
	private Integer manual_Status;
	private String inteface_Name;
	private String cluster_Type;
	private String route_Type;
	
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
	
	public String getInteface_Name() {
		return inteface_Name;
	}
	public void setInteface_Name(String inteface_Name) {
		this.inteface_Name = inteface_Name;
	}
	
	public String getCluster_Type() {
		return cluster_Type;
	}
	public void setCluster_Type(String cluster_Type) {
		this.cluster_Type = cluster_Type;
	}
	
	public String getRoute_Type() {
		return route_Type;
	}
	public void setRoute_Type(String route_Type) {
		this.route_Type = route_Type;
	}
	
	public String toString () {
		return new StringBuilder()
			.append("service_Id:").append(service_Id).append(", ")
			.append("service_Name:").append(service_Name).append(", ")
			.append("manual_Status:").append(manual_Status).append(", ")
			.append("inteface_Name:").append(inteface_Name).append(", ")
			.append("cluster_Type:").append(cluster_Type).append(", ")
			.append("route_Type:").append(route_Type)
			.toString();
	}
}
