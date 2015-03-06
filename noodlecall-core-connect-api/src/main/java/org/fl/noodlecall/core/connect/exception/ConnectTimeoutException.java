package org.fl.noodlecall.core.connect.exception;

public class ConnectTimeoutException extends RuntimeException {
	
	private static final long serialVersionUID = -1774490617476268854L;

	public ConnectTimeoutException() {
		super();
	}
	
	public ConnectTimeoutException(String message) {
		super(message);
	}
}
