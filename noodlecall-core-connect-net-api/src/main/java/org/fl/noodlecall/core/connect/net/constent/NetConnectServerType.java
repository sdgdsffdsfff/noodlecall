package org.fl.noodlecall.core.connect.net.constent;

public enum NetConnectServerType {
	
	JETTY("JETTY"), SERVLET("SERVLET"), NETTY("NETTY");
	
	private String code;

	private NetConnectServerType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}  
}
