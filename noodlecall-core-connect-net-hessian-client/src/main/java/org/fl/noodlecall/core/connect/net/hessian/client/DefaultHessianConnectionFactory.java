package org.fl.noodlecall.core.connect.net.hessian.client;

import java.io.IOException;
import java.net.URL;

import com.caucho.hessian.client.HessianConnection;
import com.caucho.hessian.client.HessianConnectionFactory;
import com.caucho.hessian.client.HessianProxyFactory;

public class DefaultHessianConnectionFactory implements HessianConnectionFactory {

	private int connectTimeout;
	private int readTimeout;
	private String encoding = "utf-8";
	
	@Override
	public HessianConnection open(URL url) throws IOException {
		return new DefaultHessianConnection(url, connectTimeout, readTimeout, encoding);
	}

	@Override
	public void setHessianProxyFactory(HessianProxyFactory hessianProxyFactory) {
		this.connectTimeout = (int) hessianProxyFactory.getConnectTimeout();
		this.readTimeout = (int) hessianProxyFactory.getReadTimeout();
	}
}
