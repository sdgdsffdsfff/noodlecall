package org.fl.noodlecall.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.fl.noodlecall.console.dao.ServerDao;
import org.fl.noodlecall.console.domain.ServerMd;
import org.fl.noodlecall.console.persistence.sql.DynamicSqlTemplate;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.ServerVo;

@Repository("serverDao")
public class ServerDaoImpl implements ServerDao {

	@Autowired
	private DynamicSqlTemplate dynamicSqlTemplate;

	@Override
	public PageVo<ServerVo> queryServerPage(ServerVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("server_Id", vo.getServer_Id() != null ? vo.getServer_Id() : null);
		paramsMap.put("server_Name", vo.getServer_Name() != null ? (new StringBuilder().append("%").append(vo.getServer_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? (new StringBuilder().append("%").append(vo.getIp()).append("%")).toString() : null);
		paramsMap.put("port", vo.getPort() != null ? vo.getPort() : null);
		paramsMap.put("url", vo.getUrl() != null ? (new StringBuilder().append("%").append(vo.getUrl()).append("%")).toString() : null);
		paramsMap.put("server_Type", vo.getServer_Type() != null ? vo.getServer_Type() : null);
		paramsMap.put("serialize_Type", vo.getSerialize_Type() != null ? vo.getSerialize_Type() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? (new StringBuilder().append("%").append(vo.getService_Name()).append("%")).toString() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? (new StringBuilder().append("%").append(vo.getGroup_Name()).append("%")).toString() : null);
		return dynamicSqlTemplate.queryPage("server-query-page", paramsMap, page, rows, ServerVo.class);
	}

	@Override
	public List<ServerVo> queryServerList(ServerVo vo) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("server_Id", vo.getServer_Id() != null ? vo.getServer_Id() : null);
		paramsMap.put("server_Name", vo.getServer_Name() != null ? vo.getServer_Name() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? vo.getIp() : null);
		paramsMap.put("port", vo.getPort() != null ? vo.getPort() : null);
		paramsMap.put("url", vo.getUrl() != null ? vo.getUrl() : null);
		paramsMap.put("server_Type", vo.getServer_Type() != null ? vo.getServer_Type() : null);
		paramsMap.put("serialize_Type", vo.getSerialize_Type() != null ? vo.getSerialize_Type() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? vo.getService_Name() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? vo.getGroup_Name() : null);
		return dynamicSqlTemplate.queryList("server-query-list", paramsMap, ServerVo.class);
	}

	@Override
	public void insertServer(ServerVo vo) throws Exception {
		dynamicSqlTemplate.insert(vo, ServerMd.class);
	}

	@Override
	public void insertsServer(ServerVo[] vos) throws Exception {
		for (ServerVo vo : vos) {
			dynamicSqlTemplate.insert(vo, ServerMd.class);
		}
	}

	@Override
	public void updateServer(ServerVo vo) throws Exception {
		dynamicSqlTemplate.updateNonull(vo, ServerMd.class);
	}

	@Override
	public void updatesServer(ServerVo[] vos) throws Exception {
		for (ServerVo vo : vos) {
			dynamicSqlTemplate.updateNonull(vo, ServerMd.class);
		}
	}

	@Override
	public void deleteServer(ServerVo vo) throws Exception {
		dynamicSqlTemplate.delete(vo, ServerMd.class);
	}

	@Override
	public void deletesServer(ServerVo[] vos) throws Exception {
		for (ServerVo vo : vos) {
			dynamicSqlTemplate.delete(vo, ServerMd.class);
		}
	}
	
	@Override
	public PageVo<ServerVo> queryServerIncludeServicePage(ServerVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("server_Id", vo.getServer_Id() != null ? vo.getServer_Id() : null);
		paramsMap.put("server_Name", vo.getServer_Name() != null ? (new StringBuilder().append("%").append(vo.getServer_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? (new StringBuilder().append("%").append(vo.getIp()).append("%")).toString() : null);
		paramsMap.put("port", vo.getPort() != null ? vo.getPort() : null);
		paramsMap.put("url", vo.getUrl() != null ? (new StringBuilder().append("%").append(vo.getUrl()).append("%")).toString() : null);
		paramsMap.put("server_Type", vo.getServer_Type() != null ? vo.getServer_Type() : null);
		paramsMap.put("serialize_Type", vo.getSerialize_Type() != null ? vo.getSerialize_Type() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? vo.getService_Name() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? (new StringBuilder().append("%").append(vo.getGroup_Name()).append("%")).toString() : null);
		return dynamicSqlTemplate.queryPage("server-query-include-service-page", paramsMap, page, rows, ServerVo.class);
	}

	@Override
	public PageVo<ServerVo> queryServerExcludeServicePage(ServerVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("server_Id", vo.getServer_Id() != null ? vo.getServer_Id() : null);
		paramsMap.put("server_Name", vo.getServer_Name() != null ? (new StringBuilder().append("%").append(vo.getServer_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? (new StringBuilder().append("%").append(vo.getIp()).append("%")).toString() : null);
		paramsMap.put("port", vo.getPort() != null ? vo.getPort() : null);
		paramsMap.put("url", vo.getUrl() != null ? (new StringBuilder().append("%").append(vo.getUrl()).append("%")).toString() : null);
		paramsMap.put("server_Type", vo.getServer_Type() != null ? vo.getServer_Type() : null);
		paramsMap.put("serialize_Type", vo.getSerialize_Type() != null ? vo.getSerialize_Type() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? (new StringBuilder().append("%").append(vo.getGroup_Name()).append("%")).toString() : null);
		return dynamicSqlTemplate.queryPage("server-query-exclude-service-page", paramsMap, page, rows, ServerVo.class);
	}
	
	@Override
	public void insertsServerService(ServerVo[] vos) throws Exception {
		for (ServerVo vo : vos) {
			dynamicSqlTemplate.updateInclude(vo, ServerMd.class, new String[]{"service_Name"});
		}
	}
	
	@Override
	public void deletesServerService(ServerVo[] vos) throws Exception {
		for (ServerVo vo : vos) {
			vo.setService_Name(null);
			dynamicSqlTemplate.updateInclude(vo, ServerMd.class, new String[]{"service_Name"});
		}
	}

	@Override
	public PageVo<ServerVo> queryServerIncludeGroupPage(ServerVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("server_Id", vo.getServer_Id() != null ? vo.getServer_Id() : null);
		paramsMap.put("server_Name", vo.getServer_Name() != null ? (new StringBuilder().append("%").append(vo.getServer_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? (new StringBuilder().append("%").append(vo.getIp()).append("%")).toString() : null);
		paramsMap.put("port", vo.getPort() != null ? vo.getPort() : null);
		paramsMap.put("url", vo.getUrl() != null ? (new StringBuilder().append("%").append(vo.getUrl()).append("%")).toString() : null);
		paramsMap.put("server_Type", vo.getServer_Type() != null ? vo.getServer_Type() : null);
		paramsMap.put("serialize_Type", vo.getSerialize_Type() != null ? vo.getSerialize_Type() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? (new StringBuilder().append("%").append(vo.getService_Name()).append("%")).toString() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? vo.getGroup_Name() : null);
		return dynamicSqlTemplate.queryPage("server-query-include-group-page", paramsMap, page, rows, ServerVo.class);
	}

	@Override
	public PageVo<ServerVo> queryServerExcludeGroupPage(ServerVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("server_Id", vo.getServer_Id() != null ? vo.getServer_Id() : null);
		paramsMap.put("server_Name", vo.getServer_Name() != null ? (new StringBuilder().append("%").append(vo.getServer_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("system_Status", vo.getSystem_Status() != null ? vo.getSystem_Status() : null);
		paramsMap.put("ip", vo.getIp() != null ? (new StringBuilder().append("%").append(vo.getIp()).append("%")).toString() : null);
		paramsMap.put("port", vo.getPort() != null ? vo.getPort() : null);
		paramsMap.put("url", vo.getUrl() != null ? (new StringBuilder().append("%").append(vo.getUrl()).append("%")).toString() : null);
		paramsMap.put("server_Type", vo.getServer_Type() != null ? vo.getServer_Type() : null);
		paramsMap.put("serialize_Type", vo.getSerialize_Type() != null ? vo.getSerialize_Type() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? (new StringBuilder().append("%").append(vo.getService_Name()).append("%")).toString() : null);
		return dynamicSqlTemplate.queryPage("server-query-exclude-group-page", paramsMap, page, rows, ServerVo.class);
	}
	
	@Override
	public void insertsServerGroup(ServerVo[] vos) throws Exception {
		for (ServerVo vo : vos) {
			dynamicSqlTemplate.updateInclude(vo, ServerMd.class, new String[]{"group_Name"});
		}
	}
	
	@Override
	public void deletesServerGroup(ServerVo[] vos) throws Exception {
		for (ServerVo vo : vos) {
			vo.setGroup_Name(null);
			dynamicSqlTemplate.updateInclude(vo, ServerMd.class, new String[]{"group_Name"});
		}
	}

	@Override
	public Long updateOrInsertServer(ServerVo vo) throws Exception {
		
		int num = dynamicSqlTemplate.updateNonullNoById(vo, ServerMd.class, new String[]{"ip", "port", "url"});
		
		if (num > 0) {			
			List<ServerVo> serverVoList = queryServerList(vo);
			if (serverVoList.size() > 0) {
				return serverVoList.get(0).getServer_Id();
			}
		} else {
			vo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
			vo.setSystem_Status(ConsoleConstant.SYSTEM_STATUS_OFFLINE);
			ServerMd serverMd = dynamicSqlTemplate.insert(vo, ServerMd.class);
			return serverMd.getServer_Id();
		}
		
		return 0L;
	}

	@Override
	public List<ServerVo> queryServerOnlineList(ServerVo vo) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beat_Time", vo.getBeat_Time());
		paramsMap.put("system_Status", vo.getSystem_Status());
		return dynamicSqlTemplate.queryList("server-query-online-list", paramsMap, ServerVo.class);
	}

	@Override
	public List<ServerVo> queryServerOfflineList(ServerVo vo) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("beat_Time", vo.getBeat_Time());
		paramsMap.put("system_Status", vo.getSystem_Status());
		return dynamicSqlTemplate.queryList("server-query-offline-list", paramsMap, ServerVo.class);
	}
}
