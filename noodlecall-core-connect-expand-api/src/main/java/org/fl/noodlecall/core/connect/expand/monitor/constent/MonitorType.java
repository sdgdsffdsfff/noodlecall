package org.fl.noodlecall.core.connect.expand.monitor.constent;

public enum MonitorType {
	
	SERVICE("SERVICE"), CONNECT("CONNECT");
	
	private String code;

	private MonitorType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}  
}
