package org.fl.noodlecall.core.connect.manager;

import org.fl.noodlecall.core.connect.agent.ConnectAgent;
import org.fl.noodlecall.core.connect.cluster.ConnectCluster;
import org.fl.noodlecall.core.connect.node.ConnectNode;
import org.fl.noodlecall.core.connect.performance.ConnectPerformanceInfo;
import org.fl.noodlecall.core.connect.route.ConnectRoute;

public interface ConnectManager {
	
	public void runUpdate();
	public void runUpdateNow();
	
	public ConnectNode getConnectNode(String nodeName);
	public ConnectAgent getConnectAgent(long connectId);
	public ConnectCluster getConnectCluster(String clusterName);
	public ConnectRoute getConnectRoute(String routeName);
	public ConnectPerformanceInfo getConnectPerformanceInfo(String methodKey);
}
