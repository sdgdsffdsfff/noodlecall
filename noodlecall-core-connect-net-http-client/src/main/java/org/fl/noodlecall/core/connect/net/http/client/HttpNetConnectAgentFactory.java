package org.fl.noodlecall.core.connect.net.http.client;

import org.fl.noodle.common.connect.agent.AbstractConnectAgentFactory;
import org.fl.noodle.common.connect.agent.ConnectAgent;

public class HttpNetConnectAgentFactory extends AbstractConnectAgentFactory {

	private String inputName = "input";

	@Override
	public ConnectAgent createConnectAgent(long connectId, String ip, int port, String url, int connectTimeout, int readTimeout) {
		return new HttpNetConnectAgent(connectId, ip, port, url, connectTimeout, readTimeout, encoding, invalidLimitNum, inputName, connectDistinguish, performanceMonitor);
	}
	
	public void setInputName(String inputName) {
		this.inputName = inputName;
	}
}
