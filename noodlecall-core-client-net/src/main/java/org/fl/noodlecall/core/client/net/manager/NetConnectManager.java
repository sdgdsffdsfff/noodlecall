package org.fl.noodlecall.core.client.net.manager;

import org.fl.noodle.common.connect.agent.ConnectAgent;
import org.fl.noodle.common.connect.agent.ConnectAgentFactory;
import org.fl.noodle.common.connect.cluster.ConnectClusterFactory;
import org.fl.noodle.common.connect.manager.AbstractConnectManager;
import org.fl.noodle.common.connect.node.ConnectNode;
import org.fl.noodle.common.connect.node.ConnectNodeImpl;
import org.fl.noodle.common.connect.performance.ConnectPerformanceInfo;
import org.fl.noodle.common.connect.register.ClientModuleRegister;
import org.fl.noodle.common.connect.route.ConnectRouteFactory;
import org.fl.noodle.common.connect.serialize.ConnectSerializeFactory;
import org.fl.noodlecall.console.remoting.ConsoleRemotingInvoke;
import org.fl.noodlecall.console.remoting.constent.ConsoleRemotingConstant;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodlecall.console.vo.MethodVo;
import org.fl.noodlecall.console.vo.ServerVo;
import org.fl.noodlecall.console.vo.ServiceVo;
import org.fl.noodlecall.core.connect.net.agent.AbstractNetConnectAgent;
import org.fl.noodlecall.core.connect.net.constent.NetConnectAgentType;
import org.fl.noodlecall.core.connect.net.constent.NetConnectManagerType;
import org.fl.noodlecall.core.connect.net.rpc.Invoker;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

public class NetConnectManager extends AbstractConnectManager implements ApplicationListener<ContextRefreshedEvent> {
	
	private final static Logger logger = LoggerFactory.getLogger(NetConnectManager.class);
			
	private ClientModuleRegister clientModuleRegister;
	
	private ConsoleRemotingInvoke consoleRemotingInvoke;
	
	@Override
	protected void updateConnectAgent() {
		
		Map<String, Map<String, List<?>>> clientNeedInfo = null;
		
		try {
			clientNeedInfo = consoleRemotingInvoke.getClientNeedInfo(clientModuleRegister.getModuleIdSet());
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("updateConnectAgent -> consoleRemotingInvoke.getClientNeedInfo -> Exception:{}", e.getMessage());
			}
			return;
		}
		
		Map<String, List<?>> serverMap = clientNeedInfo.get(ConsoleRemotingConstant.CLIENT_NEED_INFO_TYPE_SERVER);
		if (serverMap == null) {
			if (logger.isErrorEnabled()) {
				logger.error("updateConnectAgent -> server clientNeedInfo.get -> Exception:server return null");
			}
			return;
		}
		
		Map<String, List<?>> serviceMap = clientNeedInfo.get(ConsoleRemotingConstant.CLIENT_NEED_INFO_TYPE_SERVICE);
		if (serviceMap == null) {
			if (logger.isErrorEnabled()) {
				logger.error("updateConnectAgent -> service clientNeedInfo.get -> Exception:service return null");
			}
			return;
		}
		
		Map<String, List<?>> methodMap = clientNeedInfo.get(ConsoleRemotingConstant.CLIENT_NEED_INFO_TYPE_METHOD);
		if (methodMap == null) {
			if (logger.isErrorEnabled()) {
				logger.error("updateConnectAgent -> method clientNeedInfo.get -> Exception:method return null");
			}
			return;
		}
		
		Set<Long> connectIdSet = new HashSet<Long>();
		
		for (Entry<String, List<?>> serverEntry : serverMap.entrySet()) {
			
			ConnectNode serviceConnectNode = connectNodeMap.get(serverEntry.getKey());
			if (serviceConnectNode == null) {
				serviceConnectNode = new ConnectNodeImpl(serverEntry.getKey());
				connectNodeMap.put(serverEntry.getKey(), serviceConnectNode);
			}
			
			List<ConnectAgent> connectAgentList = new ArrayList<ConnectAgent>();
			
			for (Object serverVoObject : serverEntry.getValue()) {
				
				ServerVo serverVo = (ServerVo)serverVoObject;
				
				connectIdSet.add(serverVo.getServer_Id());
				
				NetConnectAgentType connectAgentType = null;
				
				if (serverVo.getSerialize_Type().equals(ConsoleConstant.SERIALIZE_TYPE_HESSIAN)) {
					if (serverVo.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_JETTY)
							|| serverVo.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_SERVLET)) {		
						connectAgentType = NetConnectAgentType.HESSIAN;
					} else {
						if (logger.isErrorEnabled()) {
							logger.error("updateConnectAgent -> connectAgentFactoryMap.get -> {} -> Exception:no have this connect agent type", serverVo);
						}
						continue;
					}
				} else {
					if (serverVo.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_NETTY)) {
						connectAgentType = NetConnectAgentType.NETTY;
					} else if (serverVo.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_JETTY)
								|| serverVo.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_SERVLET)) {		
						connectAgentType = NetConnectAgentType.HTTP;
					} else {
						if (logger.isErrorEnabled()) {
							logger.error("updateConnectAgent -> connectAgentFactoryMap.get -> {} -> Exception:no have this connect agent type", serverVo);
						}
						continue;
					}
				}
				
				ConnectAgentFactory connectAgentFactory = connectAgentFactoryMap.get(connectAgentType.getCode());
				if (connectAgentFactory == null) {
					if (logger.isErrorEnabled()) {
						logger.error("updateConnectAgent -> connectAgentFactoryMap.get -> {}, connectAgentType:{} -> Exception:no have this connect agent factory", serverVo, connectAgentType);
					}
					continue;
				}
				
				ConnectSerializeFactory connectSerializeFactory = null;
				if (connectAgentType != NetConnectAgentType.HESSIAN) {
					connectSerializeFactory = connectSerializeFactoryMap.get(serverVo.getSerialize_Type());
					if (connectSerializeFactory == null) {
						if (logger.isErrorEnabled()) {
							logger.error("updateConnectAgent -> connectSerializeFactoryMap.get -> {}, connectAgentType:{} -> Exception:no have this connect serialize factory", serverVo, connectAgentType);
						}
						continue;
					}
				}
				
				ConnectAgent connectAgent = connectAgentMap.get(serverVo.getServer_Id());
				
				if (connectAgent == null) {
					
					connectAgent = connectAgentFactory.createConnectAgent(serverVo.getServer_Id(), serverVo.getIp(), serverVo.getPort(), serverVo.getUrl());
					
					if (connectAgentType != NetConnectAgentType.HESSIAN) {
						((AbstractNetConnectAgent)connectAgent).setConnectSerialize(connectSerializeFactory.createConnectSerialize());
					}
					
					connectAgent.setWeight(serverVo.getWeight() != null && serverVo.getWeight() >= 0 ? serverVo.getWeight() : 1);
					
					try {
						connectAgent.connect();
					} catch (Exception e) {
						if (logger.isErrorEnabled()) {
							logger.error("updateConnectAgent -> add a connect agent and connectAgent.connect -> {} -> Exception:{}", serverVo, e.getMessage());
						}
						continue;
					}
					
					connectAgentMap.put(serverVo.getServer_Id(), connectAgent);
					connectAgentList.add(connectAgent);
					
					if (logger.isDebugEnabled()) {
						logger.debug("updateConnectAgent -> add a connect agent -> {}", serverVo);
					}
					
				} else {
					
					connectAgent.setWeight(serverVo.getWeight() != null && serverVo.getWeight() >= 0 ? serverVo.getWeight() : 1);
					
					if (connectAgent.isSameConnect(serverVo.getIp(), serverVo.getPort(), serverVo.getUrl(), connectAgentType.getCode())) {
						if (!connectAgent.isHealthyConnect()) {
							try {
								connectAgent.reconnect();
							} catch (Exception e) {
								if (logger.isErrorEnabled()) {
									logger.error("updateConnectAgent -> update a connect agent again and connectAgent.reconnect -> {} -> Exception:{}", serverVo, e.getMessage());
								}
								continue;
							}
							if (logger.isDebugEnabled()) {
								logger.debug("updateConnectAgent -> update a connect agent again and connectAgent.reconnect -> {}", serverVo);
							}
						}
						connectAgentList.add(connectAgent);
						
						if (logger.isDebugEnabled()) {
							logger.debug("updateConnectAgent -> update a connect agent again -> {}", serverVo);
						}
						
					} else {
						
						connectAgent.close();
						connectAgentMap.remove(connectAgent.getConnectId());
						
						connectAgent = connectAgentFactory.createConnectAgent(serverVo.getServer_Id(), serverVo.getIp(), serverVo.getPort(), serverVo.getUrl());
						
						if (connectAgentType != NetConnectAgentType.HESSIAN) {
							((AbstractNetConnectAgent)connectAgent).setConnectSerialize(connectSerializeFactory.createConnectSerialize());
						}
						
						try {
							connectAgent.connect();
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("updateConnectAgent -> re add a connect agent and connectAgent.connect -> {} -> Exception:{}", serverVo, e.getMessage());
							}
							continue;
						}
						
						connectAgentMap.put(serverVo.getServer_Id(), connectAgent);
						connectAgentList.add(connectAgent);
						
						if (logger.isDebugEnabled()) {
							logger.debug("updateConnectAgent -> re add connect agent -> {}", serverVo);
						}
					}
				}
			}
			
			serviceConnectNode.updateConnectAgentList(connectAgentList);
		}
		
		for (Entry<String, ConnectNode> connectNodeEntry : connectNodeMap.entrySet()) {
			if (!serverMap.containsKey(connectNodeEntry.getKey())) {
				connectNodeMap.remove(connectNodeEntry.getKey());
				if (logger.isDebugEnabled()) {
					logger.debug("updateConnectAgent -> connect node is invalid and remove -> {}", connectNodeEntry.getValue());
				}
			}
		}
		
		for (Entry<Long, ConnectAgent> connectAgentEntry : connectAgentMap.entrySet()) {
			if (!connectIdSet.contains(connectAgentEntry.getKey())) {
				connectAgentEntry.getValue().close();
				connectAgentMap.remove(connectAgentEntry.getKey());
				if (logger.isDebugEnabled()) {
					logger.debug("updateConnectAgent -> connect agent is invalid and remove -> {}", connectAgentEntry.getValue());
				}
			}
		}
		
		for (Entry<String, List<?>> serviceEntry : serviceMap.entrySet()) {
			for (Object serviceVoObject : serviceEntry.getValue()) {
				
				ServiceVo serviceVo = (ServiceVo)serviceVoObject;
				
				ConnectClusterFactory connectClusterFactory = connectClusterFactoryMap.get(serviceVo.getCluster_Type());
				if (connectClusterFactory == null) {
					if (logger.isErrorEnabled()) {
						logger.error("updateConnectAgent -> service connectClusterFactoryMap.get -> {} -> Exception:no have this connect cluster factory", serviceVo);
					}
					continue;
				}
				connectClusterMap.put(serviceVo.getService_Name(), connectClusterFactory.createConnectCluster(Invoker.class)); 
				
				ConnectRouteFactory connectRouteFactory = connectRouteFactoryMap.get(serviceVo.getRoute_Type());
				if (connectRouteFactory == null) {
					if (logger.isErrorEnabled()) {
						logger.error("updateConnectAgent -> service connectRouteFactoryMap.get -> {} -> Exception:no have this connect route factory", serviceVo);
					}
					continue;
				}
				connectRouteMap.put(serviceVo.getService_Name(), connectRouteFactory.createConnectRoute());
			}
		}
		
		for (Entry<String, List<?>> methodEntry : methodMap.entrySet()) {
			for (Object methodVoObject : methodEntry.getValue()) {
				
				MethodVo methodVo = (MethodVo)methodVoObject;
				
				ConnectClusterFactory connectClusterFactory = connectClusterFactoryMap.get(methodVo.getCluster_Type());
				if (connectClusterFactory == null) {
					if (logger.isErrorEnabled()) {
						logger.error("updateConnectAgent -> method connectClusterFactoryMap.get -> {} -> Exception:no have this connect cluster factory", methodVo);
					}
					continue;
				}
				connectClusterMap.put(methodVo.getMethod_Name(), connectClusterFactory.createConnectCluster(Invoker.class)); 
				
				ConnectRouteFactory connectRouteFactory = connectRouteFactoryMap.get(methodVo.getRoute_Type());
				if (connectRouteFactory == null) {
					if (logger.isErrorEnabled()) {
						logger.error("updateConnectAgent -> method connectRouteFactoryMap.get -> {} -> Exception:no have this connect route factory", methodVo);
					}
					continue;
				}
				connectRouteMap.put(methodVo.getMethod_Name(), connectRouteFactory.createConnectRoute());
				
				ConnectPerformanceInfo connectPerformanceInfo = new ConnectPerformanceInfo();
				connectPerformanceInfo.setIsDowngrade(ConnectPerformanceInfo.IsDowngrade.valueOf(methodVo.getIs_Downgrade()));
				connectPerformanceInfo.setDowngradeType(ConnectPerformanceInfo.DowngradeType.valueOf(methodVo.getDowngrade_Type()));
				connectPerformanceInfo.setReturnType(ConnectPerformanceInfo.ReturnType.valueOf(methodVo.getReturn_Type()));
				connectPerformanceInfo.setAvgTimeLimitThreshold(methodVo.getAvgTime_Limit_Threshold());
				connectPerformanceInfo.setOvertimeThreshold(methodVo.getOvertime_Threshold());
				connectPerformanceInfo.setOvertimeLimitThreshold(methodVo.getOvertime_Limit_Threshold());
				connectPerformanceInfoMap.put(methodVo.getMethod_Name(), connectPerformanceInfo);
			}
		}		
	}

	@Override
	protected void destroyConnectAgent() {
		
		connectNodeMap.clear();
		
		for (Entry<Long, ConnectAgent> connectAgentEntry : connectAgentMap.entrySet()) {
			connectAgentEntry.getValue().close();
		}
		connectAgentMap.clear();
		
		connectClusterMap.clear();
		
		connectRouteMap.clear();
		
		connectPerformanceInfoMap.clear();
	}

	@Override
	protected String getManagerName() {
		return NetConnectManagerType.NET.getCode();
	}

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		if (event.getApplicationContext().getParent() == null) {
			updateConnectAgent();
		}
	}

	public void setClientModuleRegister(ClientModuleRegister clientModuleRegister) {
		this.clientModuleRegister = clientModuleRegister;
	}

	public void setConsoleRemotingInvoke(ConsoleRemotingInvoke consoleRemotingInvoke) {
		this.consoleRemotingInvoke = consoleRemotingInvoke;
	}
}
