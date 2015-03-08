package org.fl.noodlecall.core.connect.net.hessian.client;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fl.noodle.common.connect.distinguish.ConnectDistinguish;
import org.fl.noodle.common.connect.exception.ConnectRefusedException;
import org.fl.noodle.common.connect.exception.ConnectResetException;
import org.fl.noodle.common.connect.exception.ConnectStopException;
import org.fl.noodle.common.connect.exception.ConnectTimeoutException;
import org.fl.noodle.common.connect.expand.monitor.PerformanceMonitor;
import org.fl.noodlecall.core.connect.net.agent.AbstractNetConnectAgent;
import org.fl.noodlecall.core.connect.net.constent.NetConnectAgentType;
import org.fl.noodlecall.core.connect.net.rpc.Invocation;
import org.fl.noodlecall.core.connect.net.rpc.Invoker;
import org.fl.noodlecall.core.connect.net.rpc.Result;

import com.caucho.hessian.client.HessianProxyFactory;

public class HessianNetConnectAgent extends AbstractNetConnectAgent implements Invoker {

	private final static Logger logger = LoggerFactory.getLogger(HessianNetConnectAgent.class);
	
	private String fullUrl;
	
	private Object serviceObject;
	
	public HessianNetConnectAgent(long connectId, String ip, int port, String url, int connectTimeout, int readTimeout, String encoding, int invalidLimitNum, ConnectDistinguish connectDistinguish, PerformanceMonitor performanceMonitor) {
		super(connectId, ip, port, url, NetConnectAgentType.HESSIAN.getCode(), connectTimeout, readTimeout, encoding, invalidLimitNum, connectDistinguish, performanceMonitor);
		fullUrl = new StringBuilder("http://").append(ip).append(":").append(port).append(url).toString();
	}

	@Override
	protected void connectActual() throws Exception {
		try {
			(new DefaultHessianConnectionFactory()).open(new URL(fullUrl));
			removeHessianProxy();
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("connectActual -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectRefusedException("connection refused for create net jetty connect agent");
		} 
	}

	@Override
	protected void reconnectActual() throws Exception {
		try {
			(new DefaultHessianConnectionFactory()).open(new URL(fullUrl));
			removeHessianProxy();
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("reconnectActual -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectRefusedException("connection refused for create net jetty connect agent");
		} 
	}

	@Override
	protected void closeActual() {
		removeHessianProxy();
	}
	
	@Override
	public Result invoke(Invocation invocation) throws Throwable {
		
		createHessianProxy(invocation.getServiceInterface());
		
		Method method = invocation.getServiceInterface().getMethod(invocation.getMethodName(), invocation.getParameterTypes());
		
		try {
			return new Result(method.invoke(serviceObject, invocation.getArguments()));
		} catch (IllegalAccessException e) {
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> method.invoke -> {} -> Exception:{}", this, e.getMessage());
			}	
			throw e;
		} catch (IllegalArgumentException e) {
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> method.invoke -> {} -> Exception:{}", this, e.getMessage());
			}	
			throw e;
		} catch (InvocationTargetException e) {
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> method.invoke -> {} -> Exception:{}", this, e.getTargetException().getMessage());
			}			
			if (e.getTargetException().getCause() instanceof java.net.ConnectException) { 
				throw new ConnectResetException("connect refused for send by net http connect agent");
			} else if (e.getTargetException().getCause() instanceof java.io.FileNotFoundException) { 
				throw new ConnectResetException("connection refused for send by net http connect agent");
			} else if (e.getTargetException().getCause() instanceof java.net.SocketException) { 
				throw new ConnectResetException("connection reset for send by net http connect agent");
			} else if (e.getTargetException().getCause() instanceof java.net.SocketTimeoutException) { 
				throw new ConnectTimeoutException("connection timeout for send by net http connect agent");
			} else if (e.getTargetException().getCause() instanceof ConnectStopException) { 
				throw new ConnectResetException("connection refused for send by net http connect agent");
			} else {				
				throw e.getTargetException();
			}
		}	
	}
	
	private void createHessianProxy(Class<?> serviceInterface) throws MalformedURLException {
		if (serviceObject == null) {
			doCreateHessianProxy(serviceInterface);
		}
	}
	
	private synchronized void doCreateHessianProxy(Class<?> serviceInterface) throws MalformedURLException {
		if (serviceObject == null) {
			HessianProxyFactory hessianProxyFactory = new HessianProxyFactory();
	        hessianProxyFactory.setConnectionFactory(new DefaultHessianConnectionFactory());
	        hessianProxyFactory.setConnectTimeout(connectTimeout);
	        hessianProxyFactory.setReadTimeout(readTimeout);
	        serviceObject = hessianProxyFactory.create(serviceInterface, fullUrl, Thread.currentThread().getContextClassLoader());
		}
	}
	
	private synchronized void removeHessianProxy() {
		serviceObject = null;
	}
}
