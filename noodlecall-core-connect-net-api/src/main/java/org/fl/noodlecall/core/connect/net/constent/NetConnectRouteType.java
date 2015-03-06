package org.fl.noodlecall.core.connect.net.constent;

public enum NetConnectRouteType {
	
	RANDOM("RANDOM"), WEIGHT("WEIGHT"), RESPONSE("RESPONSE") ;
	
	private String code;

	private NetConnectRouteType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}  
}
