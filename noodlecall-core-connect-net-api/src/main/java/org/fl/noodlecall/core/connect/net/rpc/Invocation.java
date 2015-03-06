package org.fl.noodlecall.core.connect.net.rpc;

import java.io.Serializable;

public class Invocation implements Serializable {

	private static final long serialVersionUID = -2080900084811043071L;
	
	private String invokerKey;
	private String serviceName;
	private String methodName;
    private Object[] arguments;
    
    private transient Class<?> serviceInterface;
    private transient Class<?>[] parameterTypes;
    
    public Invocation() {
    }
    
    public Invocation(
    		String invokerKey, 
    		String serviceName, 
    		String methodName, 
    		Object[] arguments, 
    		Class<?> serviceInterface, 
    		Class<?>[] parameterTypes
    		) {
    	this.invokerKey = invokerKey;
    	this.serviceName = serviceName;
    	this.methodName = methodName;
    	this.arguments = arguments;
    	this.serviceInterface = serviceInterface;
    	this.parameterTypes = parameterTypes;
    }

	public String getInvokerKey() {
		return invokerKey;
	}
	public void setInvokerKey(String invokerKey) {
		this.invokerKey = invokerKey;
	}
    
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getMethodName() {
		return methodName;
	}
	public void setMethodName(String methodName) {
		this.methodName = methodName;
	}
	
	public Object[] getArguments() {
		return arguments;
	}
	public void setArguments(Object[] arguments) {
		this.arguments = arguments;
	}
	
	public Class<?> getServiceInterface() {
		return serviceInterface;
	}

	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public Class<?>[] getParameterTypes() {
		return parameterTypes;
	}

	public void setParameterTypes(Class<?>[] parameterTypes) {
		this.parameterTypes = parameterTypes;
	}

	public String toString() {
		return new StringBuilder()
					.append("invokerKey:").append(invokerKey).append(", ")
					.append("serviceName:").append(serviceName).append(", ")
					.append("methodName:").append(methodName)
					.toString();
	}
}
