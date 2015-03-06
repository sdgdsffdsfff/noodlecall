package org.fl.noodlecall.core.server.net.export;

import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.InitializingBean;

import org.fl.noodlecall.core.connect.net.constent.NetConnectServerType;
import org.fl.noodlecall.core.connect.net.constent.NetConnectClusterType;
import org.fl.noodlecall.core.connect.net.constent.NetConnectRouteType;
import org.fl.noodlecall.core.connect.net.constent.NetConnectSerializeType;
import org.fl.noodlecall.core.connect.net.export.InvokerPool;
import org.fl.noodlecall.core.connect.net.hessian.server.HessianHttpReceiver;
import org.fl.noodlecall.core.connect.net.http.receiver.HttpReceiverPool;
import org.fl.noodlecall.core.connect.net.http.server.DefaultHttpReceiver;
import org.fl.noodlecall.core.connect.net.netty.receiver.NettyReceiver;
import org.fl.noodlecall.core.connect.net.netty.receiver.NettyReceiverPool;
import org.fl.noodlecall.core.connect.net.register.NetServerModuleRegister;
import org.fl.noodlecall.core.connect.net.rpc.InvokerImpl;
import org.fl.noodlecall.core.connect.net.serialize.JsonNetConnectSerialize;
import org.fl.noodlecall.core.connect.net.util.NetServiceTools;

public class ServiceExporter implements InitializingBean {

	private String serviceName;
	private String interfaceName;
	private Object service;
	
	private NetServerModuleRegister serverModuleRegister;
	
	private String localIp;
	protected int localPort = 7170;
	private String localUrl;
	private String serverName;
	private String groupName;
	private int weight = 1;
	
	protected NetConnectServerType serverType = NetConnectServerType.JETTY;
	protected NetConnectSerializeType serializeType = NetConnectSerializeType.JSON;
	private NetConnectClusterType clusterType = NetConnectClusterType.FAILOVER;
	private NetConnectRouteType routeType = NetConnectRouteType.RANDOM;
	
	private Map<String, String> clusterTypeMap = new HashMap<String, String>();
	private Map<String, String> routeTypeMap = new HashMap<String, String>();
	
	private boolean isUpdate = true;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		
		Class<?> serviceInterface = Class.forName(interfaceName);
		if (serviceName == null) {
			serviceName = serviceInterface.getSimpleName();
		}
		for (Method method : serviceInterface.getMethods()) {
			String invokerKey = NetServiceTools.getInvokerKey(serviceName, method);
			if (InvokerPool.getInvoker(invokerKey) != null) {
				throw new Exception("there are duplicate method name definition, method name: " + invokerKey);
			}
			InvokerPool.addInvoker(invokerKey, new InvokerImpl(method, service));
		}
		
		localUrl = localUrl == null ? "/noodlecall/" + serializeType.getCode().toLowerCase() +  "/" + serviceName : localUrl;
		
		if (serverType == NetConnectServerType.JETTY) {
			if (serializeType == NetConnectSerializeType.JSON) {
				HttpReceiverPool.addHttpReceiver(localUrl, new DefaultHttpReceiver(new JsonNetConnectSerialize()));
			} else if (serializeType == NetConnectSerializeType.HESSIAN) {
				HttpReceiverPool.addHttpReceiver(localUrl, new HessianHttpReceiver(service, serviceInterface));			
			} else {
				throw new Exception("this serialization type not supported by this server, server type: " + serverType + ", serialize type:  " + serializeType);
			}
		} else if (serverType == NetConnectServerType.SERVLET) {
			if (serializeType == NetConnectSerializeType.JSON) {
				HttpReceiverPool.addHttpReceiver(localUrl, new DefaultHttpReceiver(new JsonNetConnectSerialize()));
			} else if (serializeType == NetConnectSerializeType.HESSIAN) {
				HttpReceiverPool.addHttpReceiver(localUrl, new HessianHttpReceiver(service, serviceInterface));			
			} else {
				throw new Exception("this serialization type not supported by this server, server type: " + serverType + ", serialize type:  " + serializeType);
			}
		} else if (serverType == NetConnectServerType.NETTY) {			
			if (serializeType == NetConnectSerializeType.JSON) {
				NettyReceiverPool.addNettyReceiver(serviceName, new NettyReceiver(new JsonNetConnectSerialize()));
			} else if (serializeType == NetConnectSerializeType.HESSIAN) {
				throw new Exception("this serialization type not supported by this server, server type: " + serverType + ", serialize type:  " + serializeType);
			} else {
				throw new Exception("this serialization type not supported by this server, server type: " + serverType + ", serialize type:  " + serializeType);
			}
		} else {
			throw new Exception("this server type not supported, server type: " + serverType);
		}
		
		localIp = localIp == null ? InetAddress.getLocalHost().getHostAddress() : localIp;
		serverName = serverName == null ? serviceInterface.getSimpleName() + "-Server" : serverName;
		groupName = groupName == null ? "DefaultGroup" : groupName;
		serverModuleRegister.register(
				localIp, 
				localPort, 
				localUrl,
				serverName, 
				serverType.getCode(), 
				serializeType.getCode(),
				serviceName, 
				groupName, 
				clusterType.getCode(),
				routeType.getCode(), 
				weight, 
				serviceInterface, 
				clusterTypeMap,
				routeTypeMap,
				isUpdate
				);
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public void setService(Object service) {
		this.service = service;
	}
	
	public void setServerModuleRegister(NetServerModuleRegister serverModuleRegister) {
		this.serverModuleRegister = serverModuleRegister;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}
	
	public void setLocalPort(int localPort) {
		this.localPort = localPort;
	}

	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}

	public void setServerName(String serverName) {
		this.serverName = serverName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public void setServerType(String serverType) throws Exception {
		try {			
			this.serverType = NetConnectServerType.valueOf(serverType);
		} catch (Exception e) {
			throw new Exception("this server type not supported, server type: " + serverType);
		}
	}

	public void setSerializeType(String serializeType) throws Exception {
		try {			
			this.serializeType = NetConnectSerializeType.valueOf(serializeType);
		} catch (Exception e) {
			throw new Exception("this serialize type not supported, serialize type: " + serializeType);
		}
	}

	public void setClusterType(String clusterType) throws Exception {
		try {			
			this.clusterType = NetConnectClusterType.valueOf(clusterType);
		} catch (Exception e) {
			throw new Exception("this cluster type not supported, cluster type: " + clusterType);
		}
	}

	public void setRouteType(String routeType) throws Exception {
		try {			
			this.routeType = NetConnectRouteType.valueOf(routeType);
		} catch (Exception e) {
			throw new Exception("this route type not supported, route type: " + routeType);
		}
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	public void setClusterTypeMap(Map<String, String> clusterTypeMap) {
		this.clusterTypeMap = clusterTypeMap;
	}

	public void setRouteTypeMap(Map<String, String> routeTypeMap) {
		this.routeTypeMap = routeTypeMap;
	}

	public void setUpdate(boolean isUpdate) {
		this.isUpdate = isUpdate;
	}
}
