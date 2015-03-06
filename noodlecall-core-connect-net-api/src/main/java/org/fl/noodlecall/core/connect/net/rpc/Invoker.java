package org.fl.noodlecall.core.connect.net.rpc;

public interface Invoker {
	
    Result invoke(Invocation invocation) throws Throwable;
}
