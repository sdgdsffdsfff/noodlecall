package org.fl.noodlecall.core.connect.net.http.web.jetty;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.fl.noodlecall.core.connect.net.http.receiver.HttpReceiver;
import org.fl.noodlecall.core.connect.net.http.receiver.HttpReceiverPool;

public class JettyNetConnectServerReceiveHandler extends AbstractHandler {
	
	private final static Logger logger = LoggerFactory.getLogger(JettyNetConnectServerReceiveHandler.class);
	
	private String encoding = "utf-8";
	
	private String healthName = "health";
	private String healthUrl = "check/health";
	
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		request.setCharacterEncoding(encoding);
		response.setContentType("application/x-www-form-urlencoded;charset=" + encoding);  
        response.setStatus(HttpServletResponse.SC_OK);  
        baseRequest.setHandled(true);  
        
        if (target.endsWith(healthUrl)) {
        	
        	String input = request.getParameter(healthName);
        	if (input != null) {
        		try {
            		if (HttpReceiverPool.getHttpReceiver(input) != null) {
                		response.getWriter().print("true");
            		} else {
                		response.getWriter().print("false");        			
            		}
        		} catch (Exception e) {
        			if (logger.isErrorEnabled()) {
        				logger.error("handle -> response.getWriter().print -> input:{} -> Exception:{}", input, e.getMessage());
        			}
        		}
            	return;
        	}
        }
        
        HttpReceiver httpReceiver = HttpReceiverPool.getHttpReceiver(target);
        if (httpReceiver == null) {
        	if (logger.isErrorEnabled()) {
				logger.error("handle -> HttpReceiverPool.getHttpReceiver -> Exception:receiver return null");
			}
			return;
        }
        
        httpReceiver.receive(request, response);
	}
	
	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public void setHealthUrl(String healthUrl) {
		this.healthUrl = healthUrl;
	}
}
