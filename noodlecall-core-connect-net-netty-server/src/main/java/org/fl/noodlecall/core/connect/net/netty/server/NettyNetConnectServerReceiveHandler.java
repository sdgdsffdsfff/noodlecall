package org.fl.noodlecall.core.connect.net.netty.server;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.fl.noodlecall.core.connect.net.netty.receiver.NettyReceiver;
import org.fl.noodlecall.core.connect.net.netty.receiver.NettyReceiverPool;

public class NettyNetConnectServerReceiveHandler extends SimpleChannelHandler {
	
	private final static Logger logger = LoggerFactory.getLogger(NettyNetConnectServerReceiveHandler.class);
	
	private String healthName = "health";
	
	@Override
	public void messageReceived(final ChannelHandlerContext ctx, final MessageEvent me) throws Exception {
		
		NettyNetConnectServerModel nettyNetConnectServerModel = (NettyNetConnectServerModel) me.getMessage();
		
		if (nettyNetConnectServerModel.getName().equals(healthName)) {
			
			try {
				if (NettyReceiverPool.getNettyReceiver(nettyNetConnectServerModel.getData()) != null) {					
					ctx.getChannel().write("true");
				} else {
					ctx.getChannel().write("false");
				}
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("messageReceived -> response.getWriter().print -> input:{} -> Exception:{}", nettyNetConnectServerModel.getData(), e.getMessage());
				}
			}
			return;
		}
		
		NettyReceiver nettyReceiver = NettyReceiverPool.getNettyReceiver(nettyNetConnectServerModel.getName());
        if (nettyReceiver == null) {
			logger.error("messageReceived -> NettyReceiverPool.getNettyReceiver -> input:{} -> Exception:receiver return null", nettyNetConnectServerModel.getData());
        }
        
        nettyReceiver.receive(ctx, nettyNetConnectServerModel.getData());
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent ee) throws Exception {
		ee.getChannel().close();
	}

	public void setHealthName(String healthName) {
		this.healthName = healthName;
	}
}
