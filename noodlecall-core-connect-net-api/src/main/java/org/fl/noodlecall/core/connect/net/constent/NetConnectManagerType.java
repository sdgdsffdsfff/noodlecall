package org.fl.noodlecall.core.connect.net.constent;

public enum NetConnectManagerType {
	
	NET("NET");
	
	private String code;

	private NetConnectManagerType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}  
}
