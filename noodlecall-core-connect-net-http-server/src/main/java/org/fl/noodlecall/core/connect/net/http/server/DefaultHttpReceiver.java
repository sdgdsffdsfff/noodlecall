package org.fl.noodlecall.core.connect.net.http.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.fl.noodlecall.core.connect.exception.ConnectNoExistException;
import org.fl.noodlecall.core.connect.exception.ConnectSerializeException;
import org.fl.noodlecall.core.connect.net.export.InvokerPool;
import org.fl.noodlecall.core.connect.net.http.receiver.HttpReceiver;
import org.fl.noodlecall.core.connect.net.rpc.Invocation;
import org.fl.noodlecall.core.connect.net.rpc.Invoker;
import org.fl.noodlecall.core.connect.net.rpc.Result;
import org.fl.noodlecall.core.connect.serialize.ConnectSerialize;

public class DefaultHttpReceiver implements HttpReceiver {

	private final static Logger logger = LoggerFactory.getLogger(DefaultHttpReceiver.class);
	
	private ConnectSerialize connectSerialize;
	
	public DefaultHttpReceiver(ConnectSerialize connectSerialize) {
		this.connectSerialize = connectSerialize;
	}
	
	@Override
	public void receive(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String input = request.getParameter("input");
		
		Result result = null;
		
		if (input != null) {

			Invocation invocation = null;
			try {
				invocation = connectSerialize.deserializationFromString(input, Invocation.class);
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("doReceive -> connectSerialize.deserializationFromString -> input:{} -> Exception:{}", input, e.getMessage());
				}
				result = new Result(new ConnectSerializeException("input deserialization from string fail"));
			}

			if (invocation != null) {
				Invoker invoker = InvokerPool.getInvoker(invocation.getInvokerKey());
				if (invoker != null) {
					try {
						result = invoker.invoke(invocation);
					} catch (Throwable e) {
						if (logger.isErrorEnabled()) {
							logger.error("DoReceive -> invoker.invoke -> {} -> Exception:{} ", invocation, e.getMessage());
						}
						result = new Result(e);
					}
				} else {
					if (logger.isErrorEnabled()) {
						logger.error("doReceive -> InvokerPool.getInvoker -> {} -> Exception:invoker no exist", invocation);
					}
					result = new Result(new ConnectNoExistException("invoker no exist"));
				}
			}
		} else {
			if (logger.isErrorEnabled()) {
				logger.error("doReceive -> request.getParameter -> Exception:input is null");
			}
			result = new Result(new NullPointerException("input is null"));
		}
		
		String output = null;		
		try {
			output = connectSerialize.serializationToString(result);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("doReceive -> connectSerialize.serializationToString -> {} -> Exception:{}", result, e.getMessage());
			}
			result = new Result(new ConnectSerializeException("result serialization to string fail"));
		}
		
		try {
			response.getWriter().print(output);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("doReceive -> response.getWriter().print -> output:{} -> Exception:{}", output, e.getMessage());
			}
		}
	}
}
