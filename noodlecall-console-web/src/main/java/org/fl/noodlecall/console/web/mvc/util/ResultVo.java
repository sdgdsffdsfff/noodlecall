package org.fl.noodlecall.console.web.mvc.util;

public class ResultVo {
	
	private String result;
	private String errorMessage;
	
	public ResultVo() {
		
	}
	
	public ResultVo(String result) {
		this.result = result;
	}
	
	public ResultVo(String result, String errorMessage) {
		this.result = result;
		this.errorMessage = errorMessage;
	}
	
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
}
