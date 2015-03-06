package org.fl.noodlecall.core.connect.route;

public class WeightConnectRouteFactory implements ConnectRouteFactory {

	@Override
	public ConnectRoute createConnectRoute() {
		return new WeightConnectRoute();
	}
}
