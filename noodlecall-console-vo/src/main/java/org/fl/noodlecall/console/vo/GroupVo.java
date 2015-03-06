package org.fl.noodlecall.console.vo;

public class GroupVo implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long group_Id;
	private String group_Name;
	private Integer manual_Status;
	
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
	
	public Integer getManual_Status() {
		return manual_Status;
	}
	public void setManual_Status(Integer manual_Status) {
		this.manual_Status = manual_Status;
	}
}
