package org.fl.noodlecall.core.connect.net.http.web.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.fl.noodlecall.core.connect.net.http.receiver.HttpReceiver;
import org.fl.noodlecall.core.connect.net.http.receiver.HttpReceiverPool;

public class ServletNetConnectServer extends HttpServlet {

	private final static Logger logger = LoggerFactory.getLogger(ServletNetConnectServer.class);
	
	private static final long serialVersionUID = -4218770080560431433L;
	
	private String encoding = "utf-8";
	
	private String healthName = "health";
	private String healthUrl = "check/health";

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding(encoding);
		response.setContentType("application/x-www-form-urlencoded;charset=" + encoding);
		response.setStatus(HttpServletResponse.SC_OK);

		if (request.getRequestURI().endsWith(healthUrl)) {
        	
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
        				logger.error("service -> response.getWriter().print -> input:{} -> Exception:{}", input, e.getMessage());
        			}
        		}
            	return;
        	}
        }
		
		HttpReceiver httpReceiver = HttpReceiverPool.getHttpReceiver(request.getRequestURI());
		if (httpReceiver == null) {
			if (logger.isErrorEnabled()) {
				logger.error("service -> HttpReceiverPool.getHttpReceiver -> Exception:receiver return null");
			}
			return;
		}

		httpReceiver.receive(request, response);
	}
	
	public void setHealthUrl(String healthUrl) {
		this.healthUrl = healthUrl;
	}
}
