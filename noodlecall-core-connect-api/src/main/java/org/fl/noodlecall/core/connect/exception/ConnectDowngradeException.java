package org.fl.noodlecall.core.connect.exception;

public class ConnectDowngradeException extends RuntimeException {
	
	private static final long serialVersionUID = -2706777719333436881L;

	public ConnectDowngradeException() {
		super();
	}
	
	public ConnectDowngradeException(String message) {
		super(message);
	}
}
