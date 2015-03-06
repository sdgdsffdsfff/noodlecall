package org.fl.noodlecall.core.connect.route;

public class RandomConnectRouteFactory implements ConnectRouteFactory {

	@Override
	public ConnectRoute createConnectRoute() {
		return new RandomConnectRoute();
	}
}
