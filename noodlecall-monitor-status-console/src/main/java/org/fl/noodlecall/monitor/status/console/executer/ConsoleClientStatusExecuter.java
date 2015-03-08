package org.fl.noodlecall.monitor.status.console.executer;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.fl.noodle.common.monitor.executer.AbstractExecuter;
import org.fl.noodlecall.console.service.ClientService;
import org.fl.noodlecall.console.vo.ClientVo;

public class ConsoleClientStatusExecuter extends AbstractExecuter {

	private final static Logger logger = LoggerFactory.getLogger(ConsoleClientStatusExecuter.class);
	
	@Autowired
	private ClientService clientService;
	
	private long maxInterval = 10 * 1000;
	
	@Override
	public void execute() throws Exception {
		ClientVo clientVo = new ClientVo();
		clientVo.setBeat_Time(new Date(((new Date()).getTime() - maxInterval)));
		
		try {
			clientService.updateClientOnline(clientVo);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("execute -> clientService.updateClientOnline -> {} -> Exception:{}", clientVo, e.getMessage());
			}
		}
		
		try {
			clientService.updateClientOffline(clientVo);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("execute -> clientService.updateClientOffline -> {} -> Exception:{}", clientVo, e.getMessage());
			}
		}
	}

	public void setMaxInterval(long maxInterval) {
		this.maxInterval = maxInterval;
	}
}
