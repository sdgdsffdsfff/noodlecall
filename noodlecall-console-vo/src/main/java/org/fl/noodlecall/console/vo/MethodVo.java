package org.fl.noodlecall.console.vo;

public class MethodVo implements java.io.Serializable {

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
	
	public Long getMethod_Id() {
		return method_Id;
	}
	public void setMethod_Id(Long method_Id) {
		this.method_Id = method_Id;
	}
	
	public String getMethod_Name() {
		return method_Name;
	}
	public void setMethod_Name(String method_Name) {
		this.method_Name = method_Name;
	}
	
	public Integer getManual_Status() {
		return manual_Status;
	}
	public void setManual_Status(Integer manual_Status) {
		this.manual_Status = manual_Status;
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
	
	public String getService_Name() {
		return service_Name;
	}
	public void setService_Name(String service_Name) {
		this.service_Name = service_Name;
	}
	
	public Integer getIs_Downgrade() {
		return is_Downgrade;
	}
	public void setIs_Downgrade(Integer is_Downgrade) {
		this.is_Downgrade = is_Downgrade;
	}
	
	public Integer getDowngrade_Type() {
		return downgrade_Type;
	}
	public void setDowngrade_Type(Integer downgrade_Type) {
		this.downgrade_Type = downgrade_Type;
	}
	
	public Integer getReturn_Type() {
		return return_Type;
	}
	public void setReturn_Type(Integer return_Type) {
		this.return_Type = return_Type;
	}
	
	public Long getAvgTime_Limit_Threshold() {
		return avgTime_Limit_Threshold;
	}
	public void setAvgTime_Limit_Threshold(Long avgTime_Limit_Threshold) {
		this.avgTime_Limit_Threshold = avgTime_Limit_Threshold;
	}
	
	public Long getOvertime_Threshold() {
		return overtime_Threshold;
	}
	public void setOvertime_Threshold(Long overtime_Threshold) {
		this.overtime_Threshold = overtime_Threshold;
	}
	
	public Long getOvertime_Limit_Threshold() {
		return overtime_Limit_Threshold;
	}
	public void setOvertime_Limit_Threshold(Long overtime_Limit_Threshold) {
		this.overtime_Limit_Threshold = overtime_Limit_Threshold;
	}
	
	public String toString () {
		return new StringBuilder()
			.append("method_Id:").append(method_Id).append(", ")
			.append("method_Name:").append(method_Name).append(", ")
			.append("manual_Status:").append(manual_Status).append(", ")
			.append("cluster_Type:").append(cluster_Type).append(", ")
			.append("route_Type:").append(route_Type)
			.toString();
	}
}
