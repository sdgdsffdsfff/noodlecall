package org.fl.noodlecall.core.connect.net.agent;

import org.fl.noodle.common.connect.agent.AbstractConnectAgent;
import org.fl.noodle.common.connect.distinguish.ConnectDistinguish;
import org.fl.noodle.common.connect.expand.monitor.PerformanceMonitor;
import org.fl.noodle.common.connect.serialize.ConnectSerialize;
import org.fl.noodlecall.core.connect.net.rpc.Invoker;

public abstract class AbstractNetConnectAgent extends AbstractConnectAgent {

	protected ConnectSerialize connectSerialize;
	
	public AbstractNetConnectAgent(long connectId, String ip, int port, String url, String type, int connectTimeout, int readTimeout, String encoding, int invalidLimitNum, ConnectDistinguish connectDistinguish, PerformanceMonitor performanceMonitor) {
		super(connectId, ip, port, url, type, connectTimeout, readTimeout, encoding, invalidLimitNum, connectDistinguish, performanceMonitor);
	}

	@Override
	protected Class<?> getServiceInterfaces() {
		return Invoker.class;
	}
	
	public void setConnectSerialize(ConnectSerialize connectSerialize) {
		this.connectSerialize = connectSerialize;
	}
}
