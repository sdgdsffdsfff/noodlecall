package org.fl.noodlecall.core.connect.cluster;

public interface ConnectClusterFactory {

	public ConnectCluster createConnectCluster(Class<?> serviceInterface);
}
