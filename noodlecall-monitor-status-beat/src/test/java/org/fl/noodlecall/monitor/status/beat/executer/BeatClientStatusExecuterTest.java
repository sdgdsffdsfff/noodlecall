package org.fl.noodlecall.monitor.status.beat.executer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import org.fl.noodlecall.monitor.status.beat.executer.BeatClientStatusExecuter;

@ContextConfiguration(locations = {
		"classpath:org/fl/noodlecall/monitor/status/beat/executer/noodlecall-monitor-status-executer-beat-client.xml"
})
public class BeatClientStatusExecuterTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	BeatClientStatusExecuter beatClientStatusExecuter;
	
	@Test
	public void testExecute() throws Exception {
		beatClientStatusExecuter.execute();
	}
}
