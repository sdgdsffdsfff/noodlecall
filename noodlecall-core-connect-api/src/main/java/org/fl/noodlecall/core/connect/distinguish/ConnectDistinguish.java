package org.fl.noodlecall.core.connect.distinguish;

import java.lang.reflect.Method;

import org.fl.noodlecall.core.connect.manager.ConnectManager;

public interface ConnectDistinguish {
	
	public ConnectManager getConnectManager();
	public String getNodeName(Object[] args);
	public String getRouteName(Object[] args);
	public String getMethodKay(Method method, Object[] args);
	public String getModuleName(Object[] args);
}
