package org.fl.noodlecall.core.connect.net;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestNetServiceImpl implements TestNetService {

	@Override
	public String sayHello(String name) {
        System.out.println("[" + new SimpleDateFormat("HH:mm:ss").format(new Date()) + "] Hello " + name + ", request from consumer: ");
        return "Hello " + name + ", response form provider: ";
	}

	@Override
	public TestObject sendObject(TestObject testObject) {
		return testObject;
	}
}
