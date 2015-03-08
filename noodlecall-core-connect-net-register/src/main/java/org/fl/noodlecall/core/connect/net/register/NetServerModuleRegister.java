package org.fl.noodlecall.core.connect.net.register;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.fl.noodle.common.connect.register.ServerModuleRegister;
import org.fl.noodlecall.console.remoting.ConsoleRemotingInvoke;
import org.fl.noodlecall.console.vo.MethodVo;
import org.fl.noodlecall.console.vo.ServiceVo;
import org.fl.noodlecall.core.connect.net.util.NetServiceTools;

public class NetServerModuleRegister extends ServerModuleRegister {
	
	private ConsoleRemotingInvoke consoleRemotingInvoke;
	
	public void register(
			String ip, 
			Integer port, 
			String url,
			String serverName, 
			String serverType, 
			String serializeType,
			String serviceName, 
			String groupName, 
			String clusterType,
			String routeType, 
			Integer weight, 
			Class<?> clazz,
			Map<String, String> clusterTypeMap,
			Map<String, String> routeTypeMap,
			boolean isUpdate
			) throws Exception {
		
		ServiceVo serviceVo = new ServiceVo();
		serviceVo.setService_Name(serviceName);
		serviceVo.setCluster_Type(clusterType);
		serviceVo.setRoute_Type(routeType);
		serviceVo.setInteface_Name(clazz.getName());
		
		List<MethodVo> methodVoList = new ArrayList<MethodVo>();
		for (Method method : clazz.getDeclaredMethods()) {
			MethodVo methodVo = new MethodVo();
			methodVo.setMethod_Name(NetServiceTools.getInvokerKey(serviceName, method));
			methodVo.setCluster_Type(clusterTypeMap.get(method.getName()) != null ? clusterTypeMap.get(method.getName()) : clusterType);
			methodVo.setRoute_Type(routeTypeMap.get(method.getName()) != null ? routeTypeMap.get(method.getName()) : routeType);
			methodVo.setService_Name(serviceName);
			methodVoList.add(methodVo);
		}
		
		setModuleId(serviceName, 
				consoleRemotingInvoke.serverRegister(
						ip, 
						port, 
						url, 
						serverName, 
						serverType, 
						serializeType, 
						weight, 
						serviceName, 
						groupName, 
						serviceVo, 
						methodVoList,
						isUpdate
						));
	}

	public void cancel() throws Exception {
		for (Entry<String, Long> entry : moduleIdMap.entrySet()) {			
			consoleRemotingInvoke.serverCancel(entry.getValue());
		}
	}

	public void setConsoleRemotingInvoke(ConsoleRemotingInvoke consoleRemotingInvoke) {
		this.consoleRemotingInvoke = consoleRemotingInvoke;
	}
}
