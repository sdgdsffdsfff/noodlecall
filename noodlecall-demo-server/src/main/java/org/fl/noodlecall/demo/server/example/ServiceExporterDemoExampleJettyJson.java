package org.fl.noodlecall.demo.server.example;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import sun.misc.Signal;
import sun.misc.SignalHandler;

public class ServiceExporterDemoExampleJettyJson {
	
	public static void main(String[] args) {
		
		final ClassPathXmlApplicationContext applicationContext
			= new ClassPathXmlApplicationContext(
				"classpath:org/fl/noodlecall/demo/server/example/noodlecall-demo-example-server-jetty-json.xml");
		
		Signal.handle(new Signal("INT"), new SignalHandler() {
			@Override
			public void handle(Signal signal) {
				System.out.println("Signal INT And Over");
				applicationContext.destroy();
				System.exit(0);
			}
	    });
	    Signal.handle(new Signal("TERM"), new SignalHandler() {
			@Override
			public void handle(Signal signal) {
				System.out.println("Signal TERM And Over");
				applicationContext.destroy();
				System.exit(0);
			}
	    });
		
		try {
			Thread.sleep(Long.MAX_VALUE);
		} catch (InterruptedException e) {
		}
	}
}
