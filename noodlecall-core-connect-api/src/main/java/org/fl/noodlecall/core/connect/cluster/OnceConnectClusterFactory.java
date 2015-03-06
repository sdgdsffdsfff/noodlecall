package org.fl.noodlecall.core.connect.cluster;

public class OnceConnectClusterFactory extends AbstractConnectClusterFactory {

	@Override
	public ConnectCluster createConnectCluster(Class<?> serviceInterface) {
		return new OnceConnectCluster(serviceInterface, connectDistinguish, performanceMonitor);
	}
}
