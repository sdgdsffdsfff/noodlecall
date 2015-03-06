package org.fl.noodlecall.core.connect.net.serialize;

import org.fl.noodlecall.core.connect.serialize.ConnectSerialize;
import org.fl.noodlecall.util.tools.ObjectJsonTranslator;

public class JsonNetConnectSerialize implements ConnectSerialize {

	@Override
	public String serializationToString(Object object) throws Exception {
		return ObjectJsonTranslator.toStringWithClassName(object);
	}

	@Override
	public byte[] serializationToByte(Object object) throws Exception {
		return ObjectJsonTranslator.toByteArray(object);
	}

	@Override
	public <T> T deserializationFromString(String string, Class<T> clazz) throws Exception {
		return ObjectJsonTranslator.fromString(string, clazz);
	}

	@Override
	public <T> T deserializationFromByte(byte[] byteArray, Class<T> clazz) throws Exception {
		return ObjectJsonTranslator.fromByteArray(byteArray, clazz);
	}
}
