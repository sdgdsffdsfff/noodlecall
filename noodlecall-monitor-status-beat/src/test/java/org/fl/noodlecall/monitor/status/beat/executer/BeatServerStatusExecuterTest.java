package org.fl.noodlecall.monitor.status.beat.executer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import org.fl.noodlecall.monitor.status.beat.executer.BeatServerStatusExecuter;

@ContextConfiguration(locations = {
		"classpath:org/fl/noodlecall/monitor/status/beat/executer/noodlecall-monitor-status-executer-beat-server.xml"
})
public class BeatServerStatusExecuterTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	BeatServerStatusExecuter beatServerStatusExecuter;
	
	@Test
	public void testExecute() throws Exception {
		beatServerStatusExecuter.execute();
	}
}
