package org.fl.noodlecall.core.connect.net.rpc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class InvokerImpl implements Invoker {

	private Object service;
	private Method method;
	
	public InvokerImpl(Method method, Object service) {
		this.method = method;
		this.service = service;
	}
	
	@Override
	public Result invoke(Invocation invocation) throws Exception {
		
		try {
			return new Result(method.invoke(service, invocation.getArguments()));
		} catch (InvocationTargetException e) {
            return new Result(e.getTargetException());
        } catch (Exception e) {
            throw new Exception("failed to invoke remote service " + invocation.getServiceName() + " method " + invocation.getMethodName(), e);
        }
	}
}
