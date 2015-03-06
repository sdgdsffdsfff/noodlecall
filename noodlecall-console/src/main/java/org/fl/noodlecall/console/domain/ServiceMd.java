package org.fl.noodlecall.console.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CC_SERVICE")
public class ServiceMd implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long service_Id;
	private String service_Name;
	private Integer manual_Status;
	private String inteface_Name;
	private String cluster_Type;
	private String route_Type;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "SERVICE_ID", nullable = false)
	public Long getService_Id() {
		return service_Id;
	}
	public void setService_Id(Long service_Id) {
		this.service_Id = service_Id;
	}	
	
	@Column(name = "SERVICE_NAME", nullable = false, length = 255, unique = true, columnDefinition = "varchar(255)")
	public String getService_Name() {
		return service_Name;
	}
	public void setService_Name(String service_Name) {
		this.service_Name = service_Name;
	}
	
	@Column(name = "MANUAL_STATUS", nullable = false)
	public Integer getManual_Status() {
		return manual_Status;
	}
	public void setManual_Status(Integer manual_Status) {
		this.manual_Status = manual_Status;
	}
	
	@Column(name = "INTEFACE_NAME", nullable = false, length = 2048, columnDefinition = "varchar(2048)")
	public String getInteface_Name() {
		return inteface_Name;
	}
	public void setInteface_Name(String inteface_Name) {
		this.inteface_Name = inteface_Name;
	}
	
	@Column(name = "CLUSTER_TYPE", nullable = false, length = 32)
	public String getCluster_Type() {
		return cluster_Type;
	}
	public void setCluster_Type(String cluster_Type) {
		this.cluster_Type = cluster_Type;
	}
	
	@Column(name = "ROUTE_TYPE", nullable = false, length = 32)
	public String getRoute_Type() {
		return route_Type;
	}
	public void setRoute_Type(String route_Type) {
		this.route_Type = route_Type;
	}
}
