package org.fl.noodlecall.core.connect.net.register;

import java.util.Map.Entry;

import org.fl.noodle.common.connect.register.ClientModuleRegister;
import org.fl.noodlecall.console.remoting.ConsoleRemotingInvoke;

public class NetClientModuleRegister extends ClientModuleRegister {
	
	private ConsoleRemotingInvoke consoleRemotingInvoke;
	
	public void register(String ip, String clientName, String serviceName, String groupName) throws Exception {
		setModuleId(serviceName, consoleRemotingInvoke.clientRegister(ip, clientName, serviceName, groupName));
	}
	
	public void cancel() throws Exception {
		for (Entry<String, Long> entry : moduleIdMap.entrySet()) {			
			consoleRemotingInvoke.serverCancel(entry.getValue());
		}
	}
	
	public void setConsoleRemotingInvoke(ConsoleRemotingInvoke consoleRemotingInvoke) {
		this.consoleRemotingInvoke = consoleRemotingInvoke;
	}
}
