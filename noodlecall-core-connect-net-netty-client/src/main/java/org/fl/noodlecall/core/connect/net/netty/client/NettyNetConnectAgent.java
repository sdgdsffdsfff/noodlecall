package org.fl.noodlecall.core.connect.net.netty.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fl.noodle.common.connect.distinguish.ConnectDistinguish;
import org.fl.noodle.common.connect.exception.ConnectRefusedException;
import org.fl.noodle.common.connect.exception.ConnectResetException;
import org.fl.noodle.common.connect.exception.ConnectSerializeException;
import org.fl.noodle.common.connect.exception.ConnectStopException;
import org.fl.noodle.common.connect.exception.ConnectTimeoutException;
import org.fl.noodle.common.connect.expand.monitor.PerformanceMonitor;
import org.fl.noodle.common.net.socket.SocketConnect;
import org.fl.noodlecall.core.connect.net.agent.AbstractNetConnectAgent;
import org.fl.noodlecall.core.connect.net.constent.NetConnectAgentType;
import org.fl.noodlecall.core.connect.net.rpc.Invocation;
import org.fl.noodlecall.core.connect.net.rpc.Invoker;
import org.fl.noodlecall.core.connect.net.rpc.Result;

public class NettyNetConnectAgent extends AbstractNetConnectAgent implements Invoker {

	private final static Logger logger = LoggerFactory.getLogger(NettyNetConnectAgent.class);

	private NettyNetConnectPool nettyNetConnectPool;
	
	private NettyNetConnectPoolConfParam nettyNetConnectPoolConfParam;
	
	public NettyNetConnectAgent(long connectId, String ip, int port, String url, int connectTimeout, int readTimeout, String encoding, int invalidLimitNum, NettyNetConnectPoolConfParam nettyNetConnectPoolConfParam, ConnectDistinguish connectDistinguish, PerformanceMonitor performanceMonitor) {
		super(connectId, ip, port, url, NetConnectAgentType.NETTY.getCode(), connectTimeout, readTimeout, encoding, invalidLimitNum, connectDistinguish, performanceMonitor);
		this.nettyNetConnectPoolConfParam = nettyNetConnectPoolConfParam;
	}

	@Override
	public void connectActual() throws Exception {

		nettyNetConnectPool = new NettyNetConnectPool(ip, port, connectTimeout, readTimeout, encoding, nettyNetConnectPoolConfParam);
		
		try {
			SocketConnect socketConnect = nettyNetConnectPool.getResource();
			nettyNetConnectPool.returnResource(socketConnect);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("connectActual -> {} -> Exception:{}", this, e.getMessage());
			}
			nettyNetConnectPool.destroy();
			throw new ConnectRefusedException("connection refused for create net netty connect agent");
		} 
	}

	@Override
	public void reconnectActual() throws Exception {

		try {
			SocketConnect socketConnect = nettyNetConnectPool.getResource();
			nettyNetConnectPool.returnResource(socketConnect);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("reconnectActual -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectRefusedException("connection refused for create net netty connect agent");
		} 
	}

	@Override
	public void closeActual() {
		nettyNetConnectPool.destroy();
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
		
		SocketConnect socketConnect = getConnect();
		
		String deserializationString = null;
		
		try {
			deserializationString = socketConnect.send(invocation.getServiceName(), serializationString);
		} catch (java.net.SocketException e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> nettyNetConnect.send -> {} -> Exception:{}", this, e.getMessage());
			}
			nettyNetConnectPool.returnBrokenResource(socketConnect);
			throw new ConnectResetException("connection reset for send by net netty connect agent");
		} catch (java.net.SocketTimeoutException e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> nettyNetConnect.send -> {} -> Exception:{}", this, e.getMessage());
			}
			nettyNetConnectPool.returnBrokenResource(socketConnect);
			throw new ConnectTimeoutException("connection timeout for send by net netty connect agent");
		} catch (ConnectStopException e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> nettyNetConnect.send -> {} -> Exception:{}", this, e.getMessage());
			}
			nettyNetConnectPool.returnBrokenResource(socketConnect);
			throw new ConnectResetException("connection refused for send by net netty connect agent");
		} catch (Exception e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> nettyNetConnect.send -> {} -> Exception:{}", this, e.getMessage());
			}
			nettyNetConnectPool.returnResource(socketConnect);
			throw e;
		}
		
		nettyNetConnectPool.returnResource(socketConnect);
		
		try {
			return connectSerialize.deserializationFromString(deserializationString, Result.class);
		} catch (Exception e) { 
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> connectSerialize.deserializationFromString -> {} -> {} -> Exception:{}", this, deserializationString, e.getMessage());
			}
			throw new ConnectSerializeException("object deserialization from string fail");
		}
	}
	
	private SocketConnect getConnect() throws Exception {
		try {
			SocketConnect socketConnect = nettyNetConnectPool.getResource();
			return socketConnect;
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> nettyNetConnectPool.getResource -> {} -> Exception:{}", this, e.getMessage());
			}
			throw new ConnectResetException("connection refused for send by net netty connect agent");
		}
	}
}
