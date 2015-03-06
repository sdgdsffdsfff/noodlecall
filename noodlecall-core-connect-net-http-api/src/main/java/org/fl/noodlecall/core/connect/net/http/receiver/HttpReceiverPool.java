package org.fl.noodlecall.core.connect.net.http.receiver;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

public class HttpReceiverPool {
	
	private static ConcurrentMap<String, HttpReceiver> httpReceiverMap = new ConcurrentHashMap<String, HttpReceiver>();
	
	public static HttpReceiver getHttpReceiver(String url) {
		return httpReceiverMap.get(url);
	}
	
	public static void addHttpReceiver(String url, HttpReceiver httpReceiver) {
		httpReceiverMap.put(url, httpReceiver);
	}
}
