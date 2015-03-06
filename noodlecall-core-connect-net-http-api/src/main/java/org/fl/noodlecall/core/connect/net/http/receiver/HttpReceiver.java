package org.fl.noodlecall.core.connect.net.http.receiver;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface HttpReceiver {
	
	public void receive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
