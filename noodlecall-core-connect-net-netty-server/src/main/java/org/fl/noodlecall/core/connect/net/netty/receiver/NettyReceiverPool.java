package org.fl.noodlecall.core.connect.net.netty.receiver;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class NettyReceiverPool {

	private static ConcurrentMap<String, NettyReceiver> nettyReceiverMap = new ConcurrentHashMap<String, NettyReceiver>();
	
	public static NettyReceiver getNettyReceiver(String name) {
		return nettyReceiverMap.get(name);
	}
	
	public static void addNettyReceiver(String name, NettyReceiver nettyReceiver) {
		nettyReceiverMap.put(name, nettyReceiver);
	}
}
