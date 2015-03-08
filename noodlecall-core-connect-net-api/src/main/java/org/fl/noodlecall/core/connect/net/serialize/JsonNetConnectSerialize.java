package org.fl.noodlecall.core.connect.net.serialize;

import org.fl.noodle.common.util.json.JsonTranslator;
import org.fl.noodlecall.core.connect.serialize.ConnectSerialize;

public class JsonNetConnectSerialize implements ConnectSerialize {

	@Override
	public String serializationToString(Object object) throws Exception {
		return JsonTranslator.toStringWithClassName(object);
	}

	@Override
	public byte[] serializationToByte(Object object) throws Exception {
		return JsonTranslator.toByteArray(object);
	}

	@Override
	public <T> T deserializationFromString(String string, Class<T> clazz) throws Exception {
		return JsonTranslator.fromString(string, clazz);
	}

	@Override
	public <T> T deserializationFromByte(byte[] byteArray, Class<T> clazz) throws Exception {
		return JsonTranslator.fromByteArray(byteArray, clazz);
	}
}
