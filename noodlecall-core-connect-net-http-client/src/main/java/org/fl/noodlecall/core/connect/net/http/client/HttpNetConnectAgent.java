package org.fl.noodlecall.core.connect.net.http.client;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fl.noodle.common.net.http.HttpConnect;
import org.fl.noodle.common.net.http.jdk.HttpConnectJdk;
import org.fl.noodlecall.core.connect.distinguish.ConnectDistinguish;
import org.fl.noodlecall.core.connect.exception.ConnectRefusedException;
import org.fl.noodlecall.core.connect.exception.ConnectResetException;
import org.fl.noodlecall.core.connect.exception.ConnectSerializeException;
import org.fl.noodlecall.core.connect.exception.ConnectStopException;
import org.fl.noodlecall.core.connect.exception.ConnectTimeoutException;
import org.fl.noodlecall.core.connect.expand.monitor.PerformanceMonitor;
import org.fl.noodlecall.core.connect.net.agent.AbstractNetConnectAgent;
import org.fl.noodlecall.core.connect.net.constent.NetConnectAgentType;
import org.fl.noodlecall.core.connect.net.rpc.Invocation;
import org.fl.noodlecall.core.connect.net.rpc.Invoker;
import org.fl.noodlecall.core.connect.net.rpc.Result;

public class HttpNetConnectAgent extends AbstractNetConnectAgent implements Invoker {

	private final static Logger logger = LoggerFactory.getLogger(HttpNetConnectAgent.class);
	
	private HttpConnect httpConnect;
	
	private String inputName;
	
	public HttpNetConnectAgent(long connectId, String ip, int port, String url, int connectTimeout, int readTimeout, String encoding, int invalidLimitNum, String inputName, ConnectDistinguish connectDistinguish, PerformanceMonitor performanceMonitor) {
		super(connectId, ip, port, url, NetConnectAgentType.HTTP.getCode(), connectTimeout, readTimeout, encoding, invalidLimitNum, connectDistinguish, performanceMonitor);
		this.inputName = inputName;
	}

	@Override
	public void connectActual() throws Exception {
		
		String fullUrl = new StringBuilder("http://").append(ip).append(":").append(port).append(url).toString();
		httpConnect = new HttpConnectJdk(fullUrl, connectTimeout, readTimeout, encoding);
		
		try {
			httpConnect.connect();
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("connectActual -> httpConnect.connect -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectRefusedException("connection refused for create net jetty connect agent");
		} 
	}

	@Override
	public void reconnectActual() throws Exception {
		
		try {
			httpConnect.connect();
		} catch (IOException e) {
			if (logger.isErrorEnabled()) {
				logger.error("reconnectActual -> httpConnect.connect -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectRefusedException("connection refused for create net jetty connect agent");
		} 
	}

	@Override
	public void closeActual() {
		httpConnect.close();
	}
	
	@Override
	public Result invoke(Invocation invocation) throws Exception {
		
		String serializationString = null;
		try {
			serializationString = connectSerialize.serializationToString(invocation);
		} catch (Exception e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> connectSerialize.serializationToString -> {} -> {} -> Exception:{}", this, invocation, e.getMessage());
			}
			throw new ConnectSerializeException("object serialization to string fail");
		}
		
		String deserializationString = null;
		try {
			deserializationString = httpConnect.postString(inputName, serializationString);
		} catch (java.net.ConnectException e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> httpConnect.send -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectResetException("connect refused for send by net http connect agent");
		} catch (java.io.FileNotFoundException e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> httpConnect.send -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectResetException("connection refused for send by net http connect agent");
		} catch (java.net.SocketException e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> httpConnect.send -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectResetException("connection reset for send by net http connect agent");
		} catch (java.net.SocketTimeoutException e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> httpConnect.send -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectTimeoutException("connection timeout for send by net http connect agent");
		} catch (ConnectStopException e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> httpConnect.send -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectResetException("connection refused for send by net http connect agent");
		} catch (Exception e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> httpConnect.send -> {} -> Exception:{}", this, e.getMessage());
			}
			throw e;
		}
		
		try {
			return connectSerialize.deserializationFromString(deserializationString, Result.class);
		} catch (Exception e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> connectSerialize.deserializationFromString -> {} -> {} -> Exception:{}", this, deserializationString, e.getMessage());
			}
			throw new ConnectSerializeException("object deserialization from string fail");
		}
	}
}
