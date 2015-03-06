package org.fl.noodlecall.console.loadbalancer;

public interface LoadBalancerManager {
	boolean checkIsAliveDataSource(String dataSourceType);
}
