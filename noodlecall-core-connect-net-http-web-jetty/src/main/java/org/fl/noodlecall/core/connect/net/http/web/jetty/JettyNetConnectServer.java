package org.fl.noodlecall.core.connect.net.http.web.jetty;

import org.eclipse.jetty.server.Connector;
import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.nio.SelectChannelConnector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JettyNetConnectServer {
	
	private final static Logger logger = LoggerFactory.getLogger(JettyNetConnectServer.class);
	
	private Server server;
	
	private int port;
	
	private Handler servletHandler;

	public void start() throws Exception {
		
		server = new Server();
		SelectChannelConnector conn = new SelectChannelConnector();
		conn.setPort(port);
		server.setConnectors(new Connector[]{conn});
		server.setHandler(servletHandler);
		server.start();
		if (logger.isDebugEnabled()) {
			logger.debug("Start a JettyNetConnectServer -> port:{}", port);
		}
	}

	public void destroy() throws Exception {
		
		server.stop();
		//server.join();
		if (logger.isDebugEnabled()) {
			logger.debug("Close a JettyNetConnectServer -> port:{}", port);
		}
	}

	public void setPort(int port) {
		this.port = port;
	}

	public void setServletHandler(Handler servletHandler) {
		this.servletHandler = servletHandler;
	}
	
}
