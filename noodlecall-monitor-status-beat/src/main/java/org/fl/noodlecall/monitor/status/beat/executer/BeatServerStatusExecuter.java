package org.fl.noodlecall.monitor.status.beat.executer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fl.noodle.common.connect.register.ServerModuleRegister;
import org.fl.noodle.common.monitor.executer.AbstractExecuter;
import org.fl.noodlecall.console.remoting.ConsoleRemotingInvoke;

public class BeatServerStatusExecuter extends AbstractExecuter {

	private final static Logger logger = LoggerFactory.getLogger(BeatServerStatusExecuter.class);
	
	private ServerModuleRegister serverModuleRegister;
	
	private ConsoleRemotingInvoke consoleRemotingInvoke;
	
	@Override
	public void execute() throws Exception {
		
		try {
			consoleRemotingInvoke.serverBeat(serverModuleRegister.getModuleIdSet());
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("execute -> consoleRemotingInvoke.serverBeat -> {} -> Exception:{}", serverModuleRegister, e.getMessage());
			}
		}
		
		if (logger.isDebugEnabled()) {			
			logger.debug("execute -> consoleRemotingInvoke.serverBeat -> {}", serverModuleRegister);
		}
	}
	
	public void setServerModuleRegister(ServerModuleRegister serverModuleRegister) {
		this.serverModuleRegister = serverModuleRegister;
	}
	
	public void setConsoleRemotingInvoke(ConsoleRemotingInvoke consoleRemotingInvoke) {
		this.consoleRemotingInvoke = consoleRemotingInvoke;
	}
}
