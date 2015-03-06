package org.fl.noodlecall.core.connect.net.netty.client;

import org.fl.noodlecall.core.connect.agent.AbstractConnectAgentFactory;
import org.fl.noodlecall.core.connect.agent.ConnectAgent;

public class NettyNetConnectAgentFactory extends AbstractConnectAgentFactory {

	private NettyNetConnectPoolConfParam nettyNetConnectPoolConfParam = new NettyNetConnectPoolConfParam();
	
	@Override
	public ConnectAgent createConnectAgent(long connectId, String ip, int port, String url, int connectTimeout, int readTimeout) {
		return new NettyNetConnectAgent(connectId, ip, port, null, connectTimeout, readTimeout, encoding, invalidLimitNum, nettyNetConnectPoolConfParam, connectDistinguish, performanceMonitor);
	}

	public void setNettyNetConnectPoolConfParam(NettyNetConnectPoolConfParam nettyNetConnectPoolConfParam) {
		this.nettyNetConnectPoolConfParam = nettyNetConnectPoolConfParam;
	}
}
