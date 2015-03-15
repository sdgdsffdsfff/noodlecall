package org.fl.noodlecall.demo.client.press;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import org.fl.noodlecall.core.connect.net.TestNetService;
import org.fl.noodlecall.core.connect.net.TestObject;

public class ServiceProxyFactoryDemoPressTwo {
	
	public static void main(String[] args) throws Exception {
		
		ClassPathXmlApplicationContext applicationContext
				= new ClassPathXmlApplicationContext(
						"classpath:org/fl/noodlecall/demo/server/press/noodlecall-demo-press-client-proxyfactory-two.xml");
		
		TestNetService testNetService = (TestNetService) applicationContext.getBean("testNetServiceProxy");
		
		testNetService.sendObject(TestObject.getInstence());

		applicationContext.destroy();
    }
}
