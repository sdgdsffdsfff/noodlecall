package org.fl.noodlecall.core.server.net.export;

import org.fl.noodlecall.core.connect.net.constent.NetConnectSerializeType;
import org.fl.noodlecall.core.connect.net.constent.NetConnectServerType;

public class ServiceExporterJettyHessian extends ServiceExporter {

	public ServiceExporterJettyHessian() {
		this.localPort = 7170;		
		this.serverType = NetConnectServerType.JETTY;
		this.serializeType = NetConnectSerializeType.HESSIAN;
	}
}
