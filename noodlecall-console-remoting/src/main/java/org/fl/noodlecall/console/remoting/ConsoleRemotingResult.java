package org.fl.noodlecall.console.remoting;

import java.io.Serializable;

public class ConsoleRemotingResult implements Serializable {
	
	private static final long serialVersionUID = 350599287838518230L;
	
	private Object result;
    private Exception exception;
    private String errorMessage;
	
    public ConsoleRemotingResult() {
    	
    }
    
    public ConsoleRemotingResult(Object result) {
    	this.result = result;
    }
    
    public ConsoleRemotingResult(String errorMessage) {
    	this.exception = new Exception(errorMessage);
    	this.errorMessage = errorMessage;
    }
	
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Exception getException() {
		return exception;
	}
	
	public void setException(Exception exception) {
		this.exception = exception;
	}

	public boolean hasException() {
    	return exception != null;
    }
    
    public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public Object recreate() throws Exception {
    	if (exception != null) {
            throw exception;
        }
        return result;
    }
    
    public String toString () {
    	return new StringBuilder()
					.append("result:").append(result).append(", ")
					.append("exception:").append(exception)
					.toString();
    }
}
