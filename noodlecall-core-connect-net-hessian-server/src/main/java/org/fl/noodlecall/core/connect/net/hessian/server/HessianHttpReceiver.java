package org.fl.noodlecall.core.connect.net.hessian.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.fl.noodlecall.core.connect.net.http.receiver.HttpReceiver;
import com.caucho.hessian.server.HessianSkeleton;

public class HessianHttpReceiver implements HttpReceiver {

	private final static Logger logger = LoggerFactory.getLogger(HessianHttpReceiver.class);
	
	private HessianSkeleton hessianSkeleton;
	
	public HessianHttpReceiver(Object service, Class<?> serviceInterface) {
		hessianSkeleton = new HessianSkeleton(service, serviceInterface);
	}
	
	@Override
	public void receive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		try {
			hessianSkeleton.invoke(request.getInputStream(), response.getOutputStream());
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("receive -> hessianSkeleton.invoke -> Exception:{}", e.getMessage());
			}
		}
	}
}
