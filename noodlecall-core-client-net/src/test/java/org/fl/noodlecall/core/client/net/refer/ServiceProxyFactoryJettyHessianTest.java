package org.fl.noodlecall.core.client.net.refer;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import org.fl.noodlecall.core.connect.net.TestNetService;
import org.fl.noodlecall.core.connect.net.TestObject;

@ContextConfiguration(locations = {
		"classpath:org/fl/noodlecall/core/server/net/export/noodlecall-core-server-net-exporter-jetty-hessian.xml",
		"classpath:org/fl/noodlecall/core/client/net/refer/noodlecall-core-client-net-proxyfactory.xml"
})
public class ServiceProxyFactoryJettyHessianTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	TestNetService testNetService;
	
	@Test
	public void testSayHello() {
		System.out.println(testNetService.sayHello("Hello"));
	}

	@Test
	public void testSendObject() {
		System.out.println(testNetService.sendObject(TestObject.getInstence()));
	}
}
