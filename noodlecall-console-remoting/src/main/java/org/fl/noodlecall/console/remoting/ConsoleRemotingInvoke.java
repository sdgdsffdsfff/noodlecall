package org.fl.noodlecall.console.remoting;

import java.util.List;
import java.util.Map;

import org.fl.noodlecall.console.vo.MethodVo;
import org.fl.noodlecall.console.vo.ServiceVo;

public interface ConsoleRemotingInvoke {
	
	public Long clientRegister(
			String ip, 
			String client_Name, 
			String service_Name, 
			String group_Name
			) throws Exception;
	
	public void clientCancel(Long clientId) throws Exception;
	
	public Long serverRegister(
			String ip, 
			Integer port, 
			String url, 
			String serverName, 
			String serverType, 
			String serializeType, 
			Integer weight, 
			String serviceName, 
			String groupName, 
			ServiceVo serviceVo, 
			List<MethodVo> methodVoList,
			boolean isUpdate
			) throws Exception;
	
	public void serverCancel(Long serverId) throws Exception;
	
	public Map<String, Map<String, List<?>>> getClientNeedInfo(List<Long> clientIdList) throws Exception;

	public void clientBeat(List<Long> clientIdList) throws Exception;
	public void serverBeat(List<Long> serverIdList) throws Exception;
}
