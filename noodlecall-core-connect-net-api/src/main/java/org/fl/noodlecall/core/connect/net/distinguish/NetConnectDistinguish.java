package org.fl.noodlecall.core.connect.net.distinguish;

import java.lang.reflect.Method;

import org.fl.noodle.common.connect.distinguish.ConnectDistinguish;
import org.fl.noodle.common.connect.manager.ConnectManager;
import org.fl.noodle.common.connect.manager.ConnectManagerPool;
import org.fl.noodlecall.core.connect.net.constent.NetConnectManagerType;
import org.fl.noodlecall.core.connect.net.rpc.Invocation;

public class NetConnectDistinguish implements ConnectDistinguish {

	@Override
	public ConnectManager getConnectManager() {
		return ConnectManagerPool.getConnectManager(NetConnectManagerType.NET.getCode());
	}

	@Override
	public String getNodeName(Object[] args) {
		return ((Invocation)args[0]).getServiceName();
	}

	@Override
	public String getRouteName(Object[] args) {
		return ((Invocation)args[0]).getInvokerKey();
	}

	@Override
	public String getMethodKay(Method method, Object[] args) {
		return ((Invocation)args[0]).getInvokerKey();
	}

	@Override
	public String getModuleName(Object[] args) {
		return ((Invocation)args[0]).getServiceName();
	}
}
