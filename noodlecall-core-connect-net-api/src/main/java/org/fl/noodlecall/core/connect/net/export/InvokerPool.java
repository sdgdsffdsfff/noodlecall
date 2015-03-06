package org.fl.noodlecall.core.connect.net.export;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.fl.noodlecall.core.connect.net.rpc.Invoker;

public class InvokerPool {

	private static ConcurrentMap<String, Invoker> invokerMap = new ConcurrentHashMap<String, Invoker>();
	
	public static Invoker getInvoker(String invokerKey) {
		return invokerMap.get(invokerKey);
	}
	
	public static void addInvoker(String invokerKey, Invoker invoker) {
		invokerMap.put(invokerKey, invoker);
	}
}
