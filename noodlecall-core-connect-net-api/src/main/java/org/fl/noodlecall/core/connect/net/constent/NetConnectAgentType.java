package org.fl.noodlecall.core.connect.net.constent;

public enum NetConnectAgentType {
	
	HTTP("HTTP"), NETTY("NETTY"), HESSIAN("HESSIAN");
	
	private String code;

	private NetConnectAgentType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}  
}
