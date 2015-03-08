package org.fl.noodlecall.core.client.net.refer;

import java.net.InetAddress;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.ProxyFactory;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.fl.noodle.common.connect.cluster.ConnectCluster;
import org.fl.noodle.common.connect.exception.ConnectInvokeException;
import org.fl.noodle.common.connect.manager.ConnectManager;
import org.fl.noodlecall.core.connect.net.register.NetClientModuleRegister;
import org.fl.noodlecall.core.connect.net.rpc.Invocation;
import org.fl.noodlecall.core.connect.net.rpc.Invoker;
import org.fl.noodlecall.core.connect.net.rpc.Result;
import org.fl.noodlecall.core.connect.net.util.NetServiceTools;

public class ServiceProxyFactory implements FactoryBean<Object>, MethodInterceptor, InitializingBean {

	private final static Logger logger = LoggerFactory.getLogger(ServiceProxyFactory.class);
	
	private Object serviceProxy;
	
	private Class<?> serviceInterface;
	
	private String serviceName;
	private String interfaceName;
	private ConnectManager connectManager;
	
	private NetClientModuleRegister clientModuleRegister;
	
	private String localIp;
	private String clientName;
	private String groupName;
	
	@Override
	public void afterPropertiesSet() throws Exception {
		serviceInterface = Class.forName(interfaceName);
		ProxyFactory ProxyFactory = new ProxyFactory(serviceInterface, this);
		this.serviceProxy = ProxyFactory.getProxy();
		if (serviceName == null) {
			serviceName = serviceInterface.getSimpleName();
		}
		
		localIp = localIp == null ? InetAddress.getLocalHost().getHostAddress() : localIp;
		clientName = clientName == null ? serviceInterface.getSimpleName() + "-Client" : clientName;
		groupName = groupName == null ? "DefaultGroup" : groupName;
		clientModuleRegister.register(localIp, clientName, serviceName, groupName);
	}

	@Override
	public Object getObject() throws Exception {
		return serviceProxy;
	}

	@Override
	public Class<?> getObjectType() {
		return serviceInterface;
	}

	@Override
	public boolean isSingleton() {
		return true;
	}

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		String invokerKey = NetServiceTools.getInvokerKey(serviceName, invocation.getMethod());
		
		ConnectCluster connectCluster = connectManager.getConnectCluster(invokerKey);
		if (connectCluster == null) {
			if (logger.isErrorEnabled()) {
				logger.error("invoke -> connectManager.getConnectCluster return null -> invokerKey: {}", invokerKey);
			}
			throw new ConnectInvokeException("no have this connect cluster");
		}
		
		Invoker invoker = (Invoker) connectCluster.getProxy();
		
		Result result = invoker.invoke(
				new Invocation(
						invokerKey, 
						serviceName, 
						invocation.getMethod().getName(), 
						invocation.getArguments(), 
						serviceInterface, 
						invocation.getMethod().getParameterTypes()
						));
		
		return result.recreate();
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public void setInterfaceName(String interfaceName) {
		this.interfaceName = interfaceName;
	}

	public void setConnectManager(ConnectManager connectManager) {
		this.connectManager = connectManager;
	}
	
	public void setClientModuleRegister(NetClientModuleRegister clientModuleRegister) {
		this.clientModuleRegister = clientModuleRegister;
	}

	public void setLocalIp(String localIp) {
		this.localIp = localIp;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public void setServiceProxy(Object serviceProxy) {
		this.serviceProxy = serviceProxy;
	}

	public void setServiceInterface(Class<?> serviceInterface) {
		this.serviceInterface = serviceInterface;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
}
