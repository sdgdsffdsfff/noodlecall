package org.fl.noodlecall.core.connect.net.serialize;

import org.fl.noodle.common.connect.serialize.ConnectSerialize;
import org.fl.noodle.common.connect.serialize.ConnectSerializeFactory;

public class JsonNetConnectSerializeFactory implements ConnectSerializeFactory {

	@Override
	public ConnectSerialize createConnectSerialize() {
		return new JsonNetConnectSerialize();
	}
}
