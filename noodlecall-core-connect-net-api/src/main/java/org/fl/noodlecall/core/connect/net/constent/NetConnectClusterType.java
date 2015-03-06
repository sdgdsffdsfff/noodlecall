package org.fl.noodlecall.core.connect.net.constent;

public enum NetConnectClusterType {
	
	FAILOVER("FAILOVER"), ONCE("ONCE"), ALL("ALL");
	
	private String code;

	private NetConnectClusterType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}  
}
