package org.fl.noodlecall.console.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CC_METHOD")
public class MethodMd implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long method_Id;
	private String method_Name;
	private Integer manual_Status;
	private String cluster_Type;
	private String route_Type;
	
	private String service_Name;
	
	private Integer is_Downgrade;
	private Integer downgrade_Type;
	private Integer return_Type;
	private Long avgTime_Limit_Threshold;
	private Long overtime_Threshold;
	private Long overtime_Limit_Threshold;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "METHOD_ID", nullable = false)
	public Long getMethod_Id() {
		return method_Id;
	}
	public void setMethod_Id(Long method_Id) {
		this.method_Id = method_Id;
	}
	
	@Column(name = "METHOD_NAME", nullable = false, length = 2048, columnDefinition = "varchar(2048)")
	public String getMethod_Name() {
		return method_Name;
	}
	public void setMethod_Name(String method_Name) {
		this.method_Name = method_Name;
	}
	
	@Column(name = "MANUAL_STATUS", nullable = false)
	public Integer getManual_Status() {
		return manual_Status;
	}
	public void setManual_Status(Integer manual_Status) {
		this.manual_Status = manual_Status;
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
	
	@Column(name = "SERVICE_NAME", nullable = true, length = 255, columnDefinition = "varchar(255)")
	public String getService_Name() {
		return service_Name;
	}
	public void setService_Name(String service_Name) {
		this.service_Name = service_Name;
	}
	
	@Column(name = "IS_DOWNGRADE", nullable = false)
	public Integer getIs_Downgrade() {
		return is_Downgrade;
	}
	public void setIs_Downgrade(Integer is_Downgrade) {
		this.is_Downgrade = is_Downgrade;
	}
	
	@Column(name = "DOWNGRADE_TYPE", nullable = false)
	public Integer getDowngrade_Type() {
		return downgrade_Type;
	}
	public void setDowngrade_Type(Integer downgrade_Type) {
		this.downgrade_Type = downgrade_Type;
	}
	
	@Column(name = "RETURN_TYPE", nullable = false)
	public Integer getReturn_Type() {
		return return_Type;
	}
	public void setReturn_Type(Integer return_Type) {
		this.return_Type = return_Type;
	}
	
	@Column(name = "AVGTIME_LIMIT_THRESHOLD", nullable = false)
	public Long getAvgTime_Limit_Threshold() {
		return avgTime_Limit_Threshold;
	}
	public void setAvgTime_Limit_Threshold(Long avgTime_Limit_Threshold) {
		this.avgTime_Limit_Threshold = avgTime_Limit_Threshold;
	}
	
	@Column(name = "OVERTIME_THRESHOLD", nullable = false)
	public Long getOvertime_Threshold() {
		return overtime_Threshold;
	}
	public void setOvertime_Threshold(Long overtime_Threshold) {
		this.overtime_Threshold = overtime_Threshold;
	}
	
	@Column(name = "OVERTIME_LIMIT_THRESHOLD", nullable = false)
	public Long getOvertime_Limit_Threshold() {
		return overtime_Limit_Threshold;
	}
	public void setOvertime_Limit_Threshold(Long overtime_Limit_Threshold) {
		this.overtime_Limit_Threshold = overtime_Limit_Threshold;
	}
}
