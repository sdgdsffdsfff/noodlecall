package org.fl.noodlecall.core.connect.net.netty.receiver;

import java.io.IOException;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.fl.noodlecall.core.connect.exception.ConnectNoExistException;
import org.fl.noodlecall.core.connect.exception.ConnectSerializeException;
import org.fl.noodlecall.core.connect.net.export.InvokerPool;
import org.fl.noodlecall.core.connect.net.rpc.Invocation;
import org.fl.noodlecall.core.connect.net.rpc.Invoker;
import org.fl.noodlecall.core.connect.net.rpc.Result;
import org.fl.noodlecall.core.connect.serialize.ConnectSerialize;

public class NettyReceiver {
	
	private final static Logger logger = LoggerFactory.getLogger(NettyReceiver.class);
	
	private ConnectSerialize connectSerialize;
	
	public NettyReceiver(ConnectSerialize connectSerialize) {
		this.connectSerialize = connectSerialize;
	}
	
	public void receive(ChannelHandlerContext ctx, String input) throws IOException {

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
		}
		
		try {
			ctx.getChannel().write(output);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("doReceive -> response.getWriter().print -> output:{} -> Exception:{}", output, e.getMessage());
			}
		}
	}
}
