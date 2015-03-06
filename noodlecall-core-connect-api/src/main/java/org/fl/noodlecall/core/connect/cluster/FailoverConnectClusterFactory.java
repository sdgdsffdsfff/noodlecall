package org.fl.noodlecall.core.connect.cluster;

public class FailoverConnectClusterFactory extends AbstractConnectClusterFactory {

	@Override
	public ConnectCluster createConnectCluster(Class<?> serviceInterface) {
		return new FailoverConnectCluster(serviceInterface, connectDistinguish, performanceMonitor);
	}
}
