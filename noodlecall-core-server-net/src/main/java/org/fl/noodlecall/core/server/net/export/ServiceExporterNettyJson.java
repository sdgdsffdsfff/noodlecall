package org.fl.noodlecall.core.server.net.export;

import org.fl.noodlecall.core.connect.net.constent.NetConnectSerializeType;
import org.fl.noodlecall.core.connect.net.constent.NetConnectServerType;

public class ServiceExporterNettyJson extends ServiceExporter {

	public ServiceExporterNettyJson() {
		this.localPort = 7370;		
		this.serverType = NetConnectServerType.NETTY;
		this.serializeType = NetConnectSerializeType.JSON;
	}
}
