package org.fl.noodlecall.monitor.performance.schedule;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {
		"classpath:org/fl/noodlecall/monitor/performance/schedule/noodlecall-monitor-performance-schedule.xml"
})

public class ExecuterScheduleTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void test() {
	}
}
