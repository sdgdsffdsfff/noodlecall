package org.fl.noodlecall.core.connect.net.hessian.client;

import org.fl.noodle.common.connect.agent.AbstractConnectAgentFactory;
import org.fl.noodle.common.connect.agent.ConnectAgent;

public class HessianNetConnectAgentFactory extends AbstractConnectAgentFactory {

	@Override
	public ConnectAgent createConnectAgent(long connectId, String ip, int port, String url, int connectTimeout, int readTimeout) {
		return new HessianNetConnectAgent(connectId, ip, port, url, connectTimeout, readTimeout, encoding, invalidLimitNum, connectDistinguish, performanceMonitor);
	}
}
