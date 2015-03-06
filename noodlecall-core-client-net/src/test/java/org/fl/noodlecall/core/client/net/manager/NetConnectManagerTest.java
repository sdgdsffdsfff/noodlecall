package org.fl.noodlecall.core.client.net.manager;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import org.fl.noodlecall.core.connect.register.ClientModuleRegister;

@ContextConfiguration(locations = {
		"classpath:org/fl/noodlecall/core/client/net/manager/noodlecall-core-client-net-manager.xml"
})
public class NetConnectManagerTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	NetConnectManager netConnectManager;
	
	@Test
	public void testStart() {
		
		ClientModuleRegister clientModuleRegister = new ClientModuleRegister();
		clientModuleRegister.setModuleId("TestNetService", 4L);
		netConnectManager.setClientModuleRegister(clientModuleRegister);
		netConnectManager.runUpdate();
		
		netConnectManager.getConnectNode("TestNetService");
	}
	
	@Test
	public void testDestroy() {
	}
}
