package org.fl.noodlecall.core.connect.net.rpc;

import java.io.Serializable;

public class Result implements Serializable {
	
	private static final long serialVersionUID = 2854437428775283832L;
	
	private Object result;
    private Throwable exception;
	
    public Result() {
    	
    }
    
    public Result(Object result) {
    	this.result = result;
    }
    
    public Result(Throwable exception) {
    	this.exception = exception;
    }
	
	public Object getResult() {
		return result;
	}

	public void setResult(Object result) {
		this.result = result;
	}

	public Throwable getException() {
		return exception;
	}
	
	public void setException(Throwable exception) {
		this.exception = exception;
	}

	public boolean hasException() {
    	return exception != null;
    }
    
    public Object recreate() throws Throwable {
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
