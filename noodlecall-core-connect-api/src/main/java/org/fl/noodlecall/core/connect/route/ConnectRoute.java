package org.fl.noodlecall.core.connect.route;

import java.util.List;

import org.fl.noodlecall.core.connect.agent.ConnectAgent;

public interface ConnectRoute {
	
	public ConnectAgent selectConnect(List<ConnectAgent> connectAgentList, List<ConnectAgent> connectAgentListSelected, String methodKey);
}
