package org.fl.noodlecall.demo.client.example;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.fl.noodlecall.core.connect.net.TestNetService;

public class ServiceProxyFactoryDemoExample {
	
	public static void main(String[] args) throws Exception {
		
		ClassPathXmlApplicationContext applicationContext
				= new ClassPathXmlApplicationContext(
						"classpath:org/fl/noodlecall/demo/server/example/noodlecall-demo-example-client-proxyfactory.xml");
		
		TestNetService testNetService = (TestNetService) applicationContext.getBean("testNetService");
		
		for (int i=0; i<1000; i++) {			
			System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] " + testNetService.sayHello("noodlecall"));
			Thread.sleep(1000);
		}

		applicationContext.destroy();
    }
}
