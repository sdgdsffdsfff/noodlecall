package org.fl.noodlecall.monitor.status.console.executer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import org.fl.noodlecall.monitor.status.console.executer.ConsoleServerStatusExecuter;
@ContextConfiguration(locations = {
		"classpath:org/fl/noodlecall/monitor/status/console/executer/noodlecall-monitor-status-executer-console-server.xml"
})
public class ConsoleServerStatusExecuterTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	ConsoleServerStatusExecuter consoleServerStatusExecuter;
	
	@Test
	public void testExecute() throws Exception {
		consoleServerStatusExecuter.execute();
	}
}
