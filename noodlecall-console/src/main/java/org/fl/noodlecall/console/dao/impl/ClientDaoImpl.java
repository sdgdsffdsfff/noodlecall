package org.fl.noodlecall.console.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.fl.noodlecall.console.dao.ClientDao;
import org.fl.noodlecall.console.domain.ClientMd;
import org.fl.noodlecall.console.persistence.sql.DynamicSqlTemplate;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.ClientVo;
import org.fl.noodlecall.console.vo.ServerVo;

@Repository("clientDao")
public class ClientDaoImpl implements ClientDao {

	@Autowired
	private DynamicSqlTemplate dynamicSqlTemplate;

	@Override
	public PageVo<ClientVo> queryClientPage(ClientVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("client_Id", vo.getClient_Id() != null ? vo.getClient_Id() : null);
		paramsMap.put("client_Name", vo.getClient_Name() != null ? (new StringBuilder().append("%").append(vo.getClient_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? (new StringBuilder().append("%").append(vo.getIp()).append("%")).toString() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? (new StringBuilder().append("%").append(vo.getService_Name()).append("%")).toString() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? (new StringBuilder().append("%").append(vo.getGroup_Name()).append("%")).toString() : null);
		return dynamicSqlTemplate.queryPage("client-query-page", paramsMap, page, rows, ClientVo.class);
	}

	@Override
	public List<ClientVo> queryClientList(ClientVo vo) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("client_Id", vo.getClient_Id() != null ? vo.getClient_Id() : null);
		paramsMap.put("client_Name", vo.getClient_Name() != null ? vo.getClient_Name() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? vo.getIp() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? vo.getService_Name() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? vo.getGroup_Name() : null);
		return dynamicSqlTemplate.queryList("client-query-list", paramsMap, ClientVo.class);
	}

	@Override
	public void insertClient(ClientVo vo) throws Exception {
		dynamicSqlTemplate.insert(vo, ClientMd.class);
	}

	@Override
	public void insertsClient(ClientVo[] vos) throws Exception {
		for (ClientVo vo : vos) {
			dynamicSqlTemplate.insert(vo, ClientMd.class);
		}
	}

	@Override
	public void updateClient(ClientVo vo) throws Exception {
		dynamicSqlTemplate.updateNonull(vo, ClientMd.class);
	}

	@Override
	public void updatesClient(ClientVo[] vos) throws Exception {
		for (ClientVo vo : vos) {
			dynamicSqlTemplate.updateNonull(vo, ClientMd.class);
		}
	}

	@Override
	public void deleteClient(ClientVo vo) throws Exception {
		dynamicSqlTemplate.delete(vo, ClientMd.class);
	}

	@Override
	public void deletesClient(ClientVo[] vos) throws Exception {
		for (ClientVo vo : vos) {
			dynamicSqlTemplate.delete(vo, ClientMd.class);
		}
	}

	@Override
	public PageVo<ClientVo> queryClientIncludeServicePage(ClientVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("client_Id", vo.getClient_Id() != null ? vo.getClient_Id() : null);
		paramsMap.put("client_Name", vo.getClient_Name() != null ? (new StringBuilder().append("%").append(vo.getClient_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? (new StringBuilder().append("%").append(vo.getIp()).append("%")).toString() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? vo.getService_Name() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? (new StringBuilder().append("%").append(vo.getGroup_Name()).append("%")).toString() : null);
		return dynamicSqlTemplate.queryPage("client-query-include-service-page", paramsMap, page, rows, ClientVo.class);
	}

	@Override
	public PageVo<ClientVo> queryClientExcludeServicePage(ClientVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("client_Id", vo.getClient_Id() != null ? vo.getClient_Id() : null);
		paramsMap.put("client_Name", vo.getClient_Name() != null ? (new StringBuilder().append("%").append(vo.getClient_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? (new StringBuilder().append("%").append(vo.getIp()).append("%")).toString() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? (new StringBuilder().append("%").append(vo.getGroup_Name()).append("%")).toString() : null);
		return dynamicSqlTemplate.queryPage("client-query-exclude-service-page", paramsMap, page, rows, ClientVo.class);
	}
	
	@Override
	public void insertsClientService(ClientVo[] vos) throws Exception {
		for (ClientVo vo : vos) {
			dynamicSqlTemplate.updateInclude(vo, ClientMd.class, new String[]{"service_Name"});
		}
	}
	
	@Override
	public void deletesClientService(ClientVo[] vos) throws Exception {
		for (ClientVo vo : vos) {
			vo.setService_Name(null);
			dynamicSqlTemplate.updateInclude(vo, ClientMd.class, new String[]{"service_Name"});
		}
	}

	@Override
	public PageVo<ClientVo> queryClientIncludeGroupPage(ClientVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("client_Id", vo.getClient_Id() != null ? vo.getClient_Id() : null);
		paramsMap.put("client_Name", vo.getClient_Name() != null ? (new StringBuilder().append("%").append(vo.getClient_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? (new StringBuilder().append("%").append(vo.getIp()).append("%")).toString() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? (new StringBuilder().append("%").append(vo.getService_Name()).append("%")).toString() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? vo.getGroup_Name() : null);
		return dynamicSqlTemplate.queryPage("client-query-include-group-page", paramsMap, page, rows, ClientVo.class);
	}

	@Override
	public PageVo<ClientVo> queryClientExcludeGroupPage(ClientVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("client_Id", vo.getClient_Id() != null ? vo.getClient_Id() : null);
		paramsMap.put("client_Name", vo.getClient_Name() != null ? (new StringBuilder().append("%").append(vo.getClient_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? (new StringBuilder().append("%").append(vo.getIp()).append("%")).toString() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? (new StringBuilder().append("%").append(vo.getService_Name()).append("%")).toString() : null);
		return dynamicSqlTemplate.queryPage("client-query-exclude-group-page", paramsMap, page, rows, ClientVo.class);
	}
	
	@Override
	public void insertsClientGroup(ClientVo[] vos) throws Exception {
		for (ClientVo vo : vos) {
			dynamicSqlTemplate.updateInclude(vo, ClientMd.class, new String[]{"group_Name"});
		}
	}
	
	@Override
	public void deletesClientGroup(ClientVo[] vos) throws Exception {
		for (ClientVo vo : vos) {
			vo.setGroup_Name(null);
			dynamicSqlTemplate.updateInclude(vo, ClientMd.class, new String[]{"group_Name"});
		}
	}

	@Override
	public Long updateOrInsertClient(ClientVo vo) throws Exception {
		
		int num = dynamicSqlTemplate.updateNonullNoById(vo, ClientMd.class, new String[]{"client_Name", "ip", "service_Name"});
		
		if (num > 0) {			
			List<ClientVo> clientVoList = queryClientList(vo);
			if (clientVoList.size() > 0) {
				return clientVoList.get(0).getClient_Id();
			}
		} else {
			vo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
			vo.setSystem_Status(ConsoleConstant.SYSTEM_STATUS_OFFLINE);
			ClientMd clientMd = dynamicSqlTemplate.insert(vo, ClientMd.class);
			return clientMd.getClient_Id();
		}
		
		return 0L;
	}

	@Override
	public Map<String, Map<String, List<?>>> updateAndQueryClientNeedInfo(ClientVo vo) throws Exception {
		
		ServerVo serverVo = new ServerVo();
		serverVo.setServer_Id(1L);
		
		List<ServerVo> list = new ArrayList<ServerVo>();
		list.add(serverVo);
		
		Map<String, List<?>> serviceMap = new HashMap<String, List<?>>();
		serviceMap.put("testService", list);
		
		Map<String, Map<String, List<?>>> map = new HashMap<String, Map<String, List<?>>>();
		map.put("server", serviceMap);
		
		return map;
	}

	@Override
	public void updateClientOnline(ClientVo vo) throws Exception {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("system_Status", ConsoleConstant.SYSTEM_STATUS_ONLINE);
		paramsMap.put("beat_Time", vo.getBeat_Time());
		dynamicSqlTemplate.updateSql("client-update-online", paramsMap);
	}

	@Override
	public void updateClientOffline(ClientVo vo) throws Exception {
		
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("system_Status", ConsoleConstant.SYSTEM_STATUS_OFFLINE);
		paramsMap.put("beat_Time", vo.getBeat_Time());
		dynamicSqlTemplate.updateSql("client-update-offline", paramsMap);
	}
}
