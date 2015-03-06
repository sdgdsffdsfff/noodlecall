package org.fl.noodlecall.console.remoting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.fl.noodlecall.console.vo.ClientVo;
import org.fl.noodlecall.console.vo.MethodVo;
import org.fl.noodlecall.console.vo.ServerVo;
import org.fl.noodlecall.console.vo.ServiceVo;
import org.fl.noodlecall.util.tools.HttpConnect;

public class ConsoleRemotingInvokeHttp implements ConsoleRemotingInvoke {

	private String consoleUrl;
	private int connectTimeout = 10000;
	private int readTimeout = 10000;
	private String encoding = "utf-8";
	
	@Override
	public Long clientRegister(
			String ip, 
			String clientName, 
			String serviceName, 
			String groupName
			) throws Exception {
		
		ClientVo clientVo = new ClientVo();
		clientVo.setIp(ip);
		clientVo.setClient_Name(clientName);
		clientVo.setService_Name(serviceName);
		clientVo.setGroup_Name(groupName);
		
		ConsoleRemotingResult consoleRemotingResult = request("remoting/clientregister", clientVo, ConsoleRemotingResult.class);
		ClientVo clientVoResult = (ClientVo) consoleRemotingResult.recreate();
		
		return clientVoResult.getClient_Id();
	}

	@Override
	public void clientCancel(Long clientId) throws Exception {
		
		ClientVo clientVo = new ClientVo();
		clientVo.setClient_Id(clientId);
		
		request("remoting/clientcancel", clientVo, ConsoleRemotingResult.class).recreate();
	}

	@Override
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
			) throws Exception {
		
		ServerVo serverVo = new ServerVo();
		serverVo.setIp(ip);
		serverVo.setPort(port);
		serverVo.setUrl(url);
		serverVo.setServer_Name(serverName);
		serverVo.setServer_Type(serverType);
		serverVo.setSerialize_Type(serializeType);
		serverVo.setService_Name(serviceName);
		serverVo.setGroup_Name(groupName);
		serverVo.setWeight(weight);
		
		ConsoleRemotingResult consoleRemotingResult = requests(
						"remoting/serverregister", 
						new String[] {"input", "input-service", "input-method", "isUpdate"},
						new Object[] {serverVo, serviceVo, methodVoList, isUpdate}, 
						ConsoleRemotingResult.class
						);
		
		ServerVo serverVoResult = (ServerVo) consoleRemotingResult.recreate();
		
		return serverVoResult.getServer_Id();
	}

	@Override
	public void serverCancel(Long serverId) throws Exception {
		
		ServerVo serverVo = new ServerVo();
		serverVo.setServer_Id(serverId);
		
		request("remoting/servercancel", serverVo, ConsoleRemotingResult.class).recreate();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Map<String, List<?>>> getClientNeedInfo(List<Long> clientIdList) throws Exception {
		
		List<ClientVo> clientVoList = new ArrayList<ClientVo>();
		
		for (Long clientId : clientIdList) {
			ClientVo clientVo = new ClientVo();
			clientVo.setClient_Id(clientId);
			clientVoList.add(clientVo);
		}
		
		return (Map<String, Map<String, List<?>>>) request("remoting/getclientneedinfo", clientVoList, ConsoleRemotingResult.class).recreate();
	}
	

	@Override
	public void clientBeat(List<Long> clientIdList) throws Exception {
		
		List<ClientVo> clientVoList = new ArrayList<ClientVo>();
		
		for (Long clientId : clientIdList) {
			ClientVo clientVo = new ClientVo();
			clientVo.setClient_Id(clientId);
			clientVoList.add(clientVo);
		}
		
		request("remoting/clientbeat", clientVoList, ConsoleRemotingResult.class).recreate();
	}

	@Override
	public void serverBeat(List<Long> serverIdList) throws Exception {
		
		List<ServerVo> serverVoList = new ArrayList<ServerVo>();
		
		for (Long serverId : serverIdList) {
			ServerVo serverVo = new ServerVo();
			serverVo.setServer_Id(serverId);
			serverVoList.add(serverVo);
		}
		
		request("remoting/serverbeat", serverVoList, ConsoleRemotingResult.class).recreate();
	}
	
	private <T> T request(String url, Object object, Class<T> clazz) throws Exception {		
		HttpConnect httpConnect = new HttpConnect(consoleUrl + url, connectTimeout, readTimeout, encoding);
		return httpConnect.send("input", object, clazz);
	}
	
	private <T> T requests(String url, String[] names, Object[] objects, Class<T> clazz) throws Exception {		
		HttpConnect httpConnect = new HttpConnect(consoleUrl + url, connectTimeout, readTimeout, encoding);
		return httpConnect.sends(names, objects, clazz);
	}
	
	public void setConsoleUrl(String consoleUrl) {
		if (!consoleUrl.endsWith("/")) {
			consoleUrl += "/";
		}
		this.consoleUrl = consoleUrl;
	}
	
	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}
}
