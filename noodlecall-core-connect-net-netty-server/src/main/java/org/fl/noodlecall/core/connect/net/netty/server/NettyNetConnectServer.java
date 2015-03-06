package org.fl.noodlecall.core.connect.net.netty.server;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFactory;
import org.jboss.netty.channel.ChannelHandler;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyNetConnectServer {
	
	private final static Logger logger = LoggerFactory.getLogger(NettyNetConnectServer.class);
	
	private ChannelGroup allChannels = new DefaultChannelGroup("NettyNetConnectServer");
	private ChannelFactory factory;
	
	private ChannelHandler channelHandler;
	
	private int port;
	
	private int workerCount = 8;
	
	public void start() throws Exception {
		
		factory = new NioServerSocketChannelFactory(
				Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool(), workerCount
		);

		ServerBootstrap bootstrap = new ServerBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				ChannelPipeline pipeline = Channels.pipeline();
				pipeline.addLast("encode", new JsonEncoder());  
				pipeline.addLast("decode", new JsonDecoder()); 
				pipeline.addLast("handler", channelHandler); 
				return pipeline;
			}
		});
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);

		Channel channel = bootstrap.bind(new InetSocketAddress(port));
		allChannels.add(channel);
		if (logger.isDebugEnabled()) {
			logger.debug("start a netty server -> Port: {}", port);
		}
	}
	
	public void destroy() throws Exception {
		ChannelGroupFuture future = allChannels.close();
		future.awaitUninterruptibly();
		factory.releaseExternalResources();
		if (logger.isDebugEnabled()) {
			logger.debug("close a netty server -> Port: {}", port);
		}
	}
	
	public void setPort(int port) {
		this.port = port;
	}
	
	public void setChannelHandler(ChannelHandler channelHandler) {
		this.channelHandler = channelHandler;
	}
	
	public void setWorkerCount(int workerCount) {
		this.workerCount = workerCount;
	}
	
	private class JsonEncoder extends OneToOneEncoder {

		@Override
		protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
			
			String jMessage = (String) msg;
			
			byte[] bMessage = jMessage.getBytes("UTF-8");
			
			int bufSendSize = bMessage.length;
			int bufSendSizeApply = ((bMessage.length + 4) / 64 + 1) * 64;
			
			ChannelBuffer channelBuffer = ctx.getChannel().getConfig().getBufferFactory().getBuffer(bufSendSizeApply);
			channelBuffer.writeInt(bufSendSize);
			channelBuffer.writeBytes(bMessage);
			
			return channelBuffer;
		}
	}
	
	private class JsonDecoder extends FrameDecoder {

		@Override
		protected Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer buffer) throws Exception {
			
			if (buffer.readableBytes() < 4) {
				return null;
			}
			
			buffer.markReaderIndex();
			
			int bufRecvSize = buffer.readInt();
			
			if (buffer.readableBytes() < bufRecvSize) {
				buffer.resetReaderIndex();
				return null;
			}
			
			ChannelBuffer channelBuffer = buffer.readBytes(bufRecvSize);
			
			NettyNetConnectServerModel nettyNetConnectServerModel = new NettyNetConnectServerModel();
			nettyNetConnectServerModel.setName(new String(channelBuffer.array(), "UTF-8"));
			
			if (buffer.readableBytes() < 4) {
				buffer.resetReaderIndex();
				return null;
			}
			
			bufRecvSize = buffer.readInt();
			
			if (buffer.readableBytes() < bufRecvSize) {
				buffer.resetReaderIndex();
				return null;
			}
			
			channelBuffer = buffer.readBytes(bufRecvSize);
			
			nettyNetConnectServerModel.setData(new String(channelBuffer.array(), "UTF-8"));
			
			return nettyNetConnectServerModel;
		}
	}
}
