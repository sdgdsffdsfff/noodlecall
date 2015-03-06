package org.fl.noodlecall.core.server.net.export;

import org.fl.noodlecall.core.connect.net.constent.NetConnectSerializeType;
import org.fl.noodlecall.core.connect.net.constent.NetConnectServerType;

public class ServiceExporterServletHessian extends ServiceExporter {

	public ServiceExporterServletHessian() {
		this.localPort = 7270;		
		this.serverType = NetConnectServerType.SERVLET;
		this.serializeType = NetConnectSerializeType.HESSIAN;
	}
}
