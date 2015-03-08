package org.fl.noodlecall.core.connect.net.netty.client;

import org.apache.commons.pool.BasePoolableObjectFactory;
import org.apache.commons.pool.impl.GenericObjectPool;
import org.fl.noodle.common.net.socket.SocketConnect;

public class NettyNetConnectPool {
	
	protected GenericObjectPool internalPool;
    
    public NettyNetConnectPool(String ip, int port, int connectTimeout, int readTimeout, String encoding, NettyNetConnectPoolConfParam nettyNetConnectPoolConfParam) {
    	this.internalPool = new GenericObjectPool(new NettyNetConnectFactory(ip, port, connectTimeout, readTimeout, encoding), nettyNetConnectPoolConfParam);
    }
    
    public SocketConnect getResource() throws Exception {
    	return (SocketConnect) internalPool.borrowObject();
    }
        
    public void returnResource(Object resource) {
    	try {
			internalPool.returnObject(resource);
		} catch (Exception e) {
		}
    }

    protected void returnBrokenResource(Object resource) {
        try {
            internalPool.invalidateObject(resource);
        } catch (Exception e) {
        }
    }

    public void destroy() {

    	try {
            internalPool.close();
        } catch (Exception e) {
        }
    }
    
    private static class NettyNetConnectFactory extends BasePoolableObjectFactory {
    	
        private String ip;
        private int port;
        private int connectTimeout;
    	private int readTimeout;
        private String encoding;

        public NettyNetConnectFactory(String ip, int port, int connectTimeout, int readTimeout, String encoding) {
            super();
            this.ip = ip;
            this.port = port;
            this.connectTimeout = connectTimeout;
            this.readTimeout = readTimeout;
            this.encoding = encoding;
        }

        public Object makeObject() throws Exception {
        	
            final SocketConnect socketConnect = new SocketConnect(ip, port, connectTimeout, readTimeout, encoding);
            try {
            	socketConnect.connect();
            } catch (Exception e) {
            	
            }
            return socketConnect;
        }

        public void destroyObject(final Object obj) throws Exception {
            if (obj instanceof SocketConnect) {
                final SocketConnect socketConnect = (SocketConnect) obj;
                if (socketConnect.isConnected()) {
                    try {
                    	socketConnect.close();
                    } catch (Exception e) {
                    	
                    }
                }
            }
        }

        public boolean validateObject(final Object obj) {
            if (obj instanceof SocketConnect) {
                final SocketConnect socketConnect = (SocketConnect) obj;
                return socketConnect.isConnected();
            } else {
                return false;
            }
        }
    }
}
