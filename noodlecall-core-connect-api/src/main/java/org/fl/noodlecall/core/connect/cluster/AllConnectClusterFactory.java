package org.fl.noodlecall.core.connect.cluster;

public class AllConnectClusterFactory extends AbstractConnectClusterFactory {

	@Override
	public ConnectCluster createConnectCluster(Class<?> serviceInterface) {
		return new AllConnectCluster(serviceInterface, connectDistinguish, performanceMonitor);
	}
}
