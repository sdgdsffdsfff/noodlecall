package org.fl.noodlecall.core.server.net.export;

import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration(locations = {
		"classpath:org/fl/noodlecall/core/server/net/export/noodlecall-core-server-net-exporter-jetty-hessian.xml"
})
public class ServiceExporterJettyHessianTest extends AbstractJUnit4SpringContextTests {

	@Test
	public void testStart() {
		try {
			Thread.sleep(1);
		} catch (InterruptedException e) {
		}
	}
}
