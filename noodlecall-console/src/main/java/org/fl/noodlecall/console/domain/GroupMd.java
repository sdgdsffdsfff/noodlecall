package org.fl.noodlecall.console.domain;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CC_GROUP")
public class GroupMd implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	private Long group_Id;
	private String group_Name;
	private Integer manual_Status;
	
	@Id
	@GeneratedValue(strategy = IDENTITY)
	@Column(name = "GROUP_ID", nullable = false)
	public Long getGroup_Id() {
		return group_Id;
	}
	public void setGroup_Id(Long group_Id) {
		this.group_Id = group_Id;
	}
	
	@Column(name = "GROUP_NAME", nullable = false, length = 128, unique = true)
	public String getGroup_Name() {
		return group_Name;
	}
	public void setGroup_Name(String group_Name) {
		this.group_Name = group_Name;
	}
	
	@Column(name = "MANUAL_STATUS", nullable = false)
	public Integer getManual_Status() {
		return manual_Status;
	}
	public void setManual_Status(Integer manual_Status) {
		this.manual_Status = manual_Status;
	}
}
