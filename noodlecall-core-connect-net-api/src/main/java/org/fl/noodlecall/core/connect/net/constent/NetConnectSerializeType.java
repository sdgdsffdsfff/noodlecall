package org.fl.noodlecall.core.connect.net.constent;

public enum NetConnectSerializeType {
	
	JSON("JSON"), HESSIAN("HESSIAN");
	
	private String code;

	private NetConnectSerializeType(String code) {
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}  
}
