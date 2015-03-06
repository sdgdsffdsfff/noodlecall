package org.fl.noodlecall.core.connect.route;

public class ResponseConnectRouteFactory implements ConnectRouteFactory {

	@Override
	public ConnectRoute createConnectRoute() {
		return new ResponseConnectRoute();
	}
}
