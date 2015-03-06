package org.fl.noodlecall.console.remoting;

import static org.junit.Assert.assertNotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodlecall.console.vo.MethodVo;
import org.fl.noodlecall.console.vo.ServiceVo;

@ContextConfiguration(locations = {
		"classpath:org/fl/noodlecall/console/remoting/noodlecall-console-remoting-invoke-http.xml"
})
public class ConsoleRemotingInvokeHttpTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	ConsoleRemotingInvoke consoleRemotingInvoke;
	
	@Test
	public void testClientRegister() throws Exception {
		
		Long client_Id = consoleRemotingInvoke.clientRegister(
				"127.0.0.1",
				"TestClient", 
				"TestService", 
				"TestGroup"
				);
		
		System.out.println("Register a client, id:" + client_Id);
		
		assertNotNull(client_Id);
	}

	@Test
	public void testClientCancel() throws Exception {
		
		Long client_Id = consoleRemotingInvoke.clientRegister(
				"127.0.0.1",
				"TestClient", 
				"TestService", 
				"TestGroup"
				);
		
		consoleRemotingInvoke.clientCancel(client_Id);
		
		System.out.println("Cancel a client, id:" + client_Id);
	}

	@Test
	public void testServerRegister() throws Exception {
		
		ServiceVo serviceVo = new ServiceVo();
		serviceVo.setService_Name("TestService");
		serviceVo.setInteface_Name("org.fl.noodlecall.core.connect.net.TestNetService");
		serviceVo.setCluster_Type(ConsoleConstant.CLUSTER_TYPE_FAILOVER);
		serviceVo.setRoute_Type(ConsoleConstant.ROUTE_TYPE_RANDOM);
		
		List<MethodVo> methodVoList = new ArrayList<MethodVo>();
		MethodVo methodVo = new MethodVo();
		methodVo.setService_Name("TestService");
		methodVo.setMethod_Name("TestNetService.test()");
		methodVo.setCluster_Type(ConsoleConstant.CLUSTER_TYPE_FAILOVER);
		methodVo.setRoute_Type(ConsoleConstant.ROUTE_TYPE_RANDOM);
		methodVoList.add(methodVo);
		
		Long server_Id = consoleRemotingInvoke.serverRegister(
				"127.0.0.1",
				12345, 
				"test/server", 
				"TestServer",
				ConsoleConstant.SERVER_TYPE_JETTY,
				ConsoleConstant.SERIALIZE_TYPE_JSON, 
				1, 
				"TestService",
				"TestGroup", 
				serviceVo, 
				methodVoList, 
				true);
		
		System.out.println("Register a server, id:" + server_Id);
		
		assertNotNull(server_Id);
	}

	@Test
	public void testServerCancel() throws Exception {
		
		ServiceVo serviceVo = new ServiceVo();
		
		List<MethodVo> methodVoList = new ArrayList<MethodVo>();
		
		Long server_Id = consoleRemotingInvoke.serverRegister(
				"127.0.0.1",
				12345, 
				"test/server", 
				"TestServer",
				ConsoleConstant.SERVER_TYPE_JETTY,
				ConsoleConstant.SERIALIZE_TYPE_JSON, 
				1, 
				"TestService",
				"TestGroup", 
				serviceVo, 
				methodVoList, 
				true
				);
		
		consoleRemotingInvoke.serverCancel(server_Id);
		
		System.out.println("Cancel a server, id:" + server_Id);
	}

	@Test
	public void testGetClientNeedInfo() throws Exception {
		Long client_Id = consoleRemotingInvoke.clientRegister(
				"127.0.0.1",
				"TestClient", 
				"TestService", 
				"TestGroup"
				);
		List<Long> client_Id_List = new ArrayList<Long>();
		client_Id_List.add(client_Id);
		Map<String, Map<String, List<?>>> map = consoleRemotingInvoke.getClientNeedInfo(client_Id_List);
		System.out.println("Client get server list, id:" + client_Id);
		assertNotNull(map);
		assertNotNull(map.get("method").get("TestService"));
		assertNotNull(map.get("server").get("TestService"));
		assertNotNull(map.get("service").get("TestService"));
	}
	
	@Test
	public void testClientBeat() throws Exception {
		Long client_Id = consoleRemotingInvoke.clientRegister(
				"127.0.0.1", 
				"TestClient", 
				"TestService", 
				"TestGroup"
				);
		List<Long> client_Id_List = new ArrayList<Long>();
		client_Id_List.add(client_Id);
		consoleRemotingInvoke.clientBeat(client_Id_List);
		System.out.println("Beat a client, id:" + client_Id);
	}
	
	@Test
	public void testServerBeat() throws Exception {
		ServiceVo serviceVo = new ServiceVo();
		List<MethodVo> methodVoList = new ArrayList<MethodVo>();
		Long server_Id = consoleRemotingInvoke.serverRegister(
				"127.0.0.1", 
				12345, 
				"test/server", 
				"TestServer", 
				ConsoleConstant.SERVER_TYPE_JETTY, 
				ConsoleConstant.SERIALIZE_TYPE_JSON, 
				1, 
				"TestService", 
				"TestGroup", 
				serviceVo, 
				methodVoList, 
				true
				);
		List<Long> server_Id_List = new ArrayList<Long>();
		server_Id_List.add(server_Id);
		consoleRemotingInvoke.serverBeat(server_Id_List);
		System.out.println("Beat a server, id:" + server_Id);
	}
}
