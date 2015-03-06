package org.fl.noodlecall.console.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fl.noodlecall.console.dao.ClientDao;
import org.fl.noodlecall.console.dao.GroupDao;
import org.fl.noodlecall.console.dao.MethodDao;
import org.fl.noodlecall.console.dao.ServerDao;
import org.fl.noodlecall.console.dao.ServiceDao;
import org.fl.noodlecall.console.dao.ServiceGroupDao;
import org.fl.noodlecall.console.remoting.constent.ConsoleRemotingConstant;
import org.fl.noodlecall.console.service.ClientService;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.ClientVo;
import org.fl.noodlecall.console.vo.GroupVo;
import org.fl.noodlecall.console.vo.MethodVo;
import org.fl.noodlecall.console.vo.ServerVo;
import org.fl.noodlecall.console.vo.ServiceGroupVo;
import org.fl.noodlecall.console.vo.ServiceVo;

@Service("clientService")
public class ClientServiceImpl implements ClientService {

	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ServerDao serverDao;
	
	@Autowired
	private MethodDao methodDao;
	
	@Autowired
	private ServiceDao serviceDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private ServiceGroupDao serviceGroupDao;
	
	@Override
	public PageVo<ClientVo> queryClientPage(ClientVo vo, int page, int rows) throws Exception {
		return clientDao.queryClientPage(vo, page, rows);
	}

	@Override
	public List<ClientVo> queryClientList(ClientVo vo) throws Exception {
		return clientDao.queryClientList(vo);
	}

	@Override
	public void insertClient(ClientVo vo) throws Exception {
		clientDao.insertClient(vo);
	}

	@Override
	public void insertsClient(ClientVo[] vos) throws Exception {
		clientDao.insertsClient(vos);
	}

	@Override
	public void updateClient(ClientVo vo) throws Exception {
		clientDao.updateClient(vo);
	}

	@Override
	public void updatesClient(ClientVo[] vos) throws Exception {
		clientDao.updatesClient(vos);
	}

	@Override
	public void deleteClient(ClientVo vo) throws Exception {
		clientDao.deleteClient(vo);
	}

	@Override
	public void deletesClient(ClientVo[] vos) throws Exception {
		for (ClientVo vo : vos) {
			clientDao.deleteClient(vo);
		}
	}

	@Override
	public PageVo<ClientVo> queryClientIncludeServicePage(ClientVo vo, int page, int rows) throws Exception {
		return clientDao.queryClientIncludeServicePage(vo, page, rows);
	}

	@Override
	public PageVo<ClientVo> queryClientExcludeServicePage(ClientVo vo, int page, int rows) throws Exception {
		return clientDao.queryClientExcludeServicePage(vo, page, rows);
	}
	
	@Override
	public void insertsClientService(ClientVo[] vos) throws Exception {
		clientDao.insertsClientService(vos);
	}
	
	@Override
	public void deletesClientService(ClientVo[] vos) throws Exception {
		clientDao.deletesClientService(vos);
	}
	
	@Override
	public PageVo<ClientVo> queryClientIncludeGroupPage(ClientVo vo, int page, int rows) throws Exception {
		return clientDao.queryClientIncludeGroupPage(vo, page, rows);
	}

	@Override
	public PageVo<ClientVo> queryClientExcludeGroupPage(ClientVo vo, int page, int rows) throws Exception {
		return clientDao.queryClientExcludeGroupPage(vo, page, rows);
	}
	
	@Override
	public void insertsClientGroup(ClientVo[] vos) throws Exception {
		clientDao.insertsClientGroup(vos);
	}
	
	@Override
	public void deletesClientGroup(ClientVo[] vos) throws Exception {
		clientDao.deletesClientGroup(vos);
	}

	@Override
	public Long updateOrInsertClient(ClientVo vo) throws Exception {
		return clientDao.updateOrInsertClient(vo);
	}

	@Override
	public Map<String, Map<String, List<?>>> queryClientNeedInfo(ClientVo[] vos) throws Exception {

		Map<String, List<?>> serviceVoMap = new HashMap<String, List<?>>();
		Map<String, List<?>> serverVoMap = new HashMap<String, List<?>>();
		Map<String, List<?>> methodVoMap = new HashMap<String, List<?>>();

		for (ClientVo vo : vos) {
			
			List<ClientVo> clientVoList = clientDao.queryClientList(vo);
			if (clientVoList.size() == 0) {
				continue;
			}
			ClientVo clientVo = clientVoList.get(0);
			if (clientVo.getManual_Status().equals(ConsoleConstant.MANUAL_STATUS_NO)) {
				continue;
			}
			
			ServiceVo serviceVo = new ServiceVo();
			serviceVo.setService_Name(clientVo.getService_Name());
			serviceVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
			List<ServiceVo> serviceVoList = serviceDao.queryServiceList(serviceVo);
			if (serviceVoList.size() == 0) {
				continue;
			}
			
			GroupVo groupVo = new GroupVo();
			groupVo.setGroup_Name(clientVo.getGroup_Name());
			groupVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
			List<GroupVo> groupVoList = groupDao.queryGroupList(groupVo);
			if (groupVoList.size() == 0) {
				continue;
			}
			
			ServiceGroupVo serviceGroupVo = new ServiceGroupVo();
			serviceGroupVo.setService_Name(clientVo.getService_Name());
			serviceGroupVo.setGroup_Name(clientVo.getGroup_Name());
			List<ServiceGroupVo> serviceGroupVoList = serviceGroupDao.queryServiceGroupList(serviceGroupVo);
			if (serviceGroupVoList.size() == 0) {
				continue;
			}
			
			ServerVo serverVo = new ServerVo();
			serverVo.setService_Name(clientVo.getService_Name());
			serverVo.setGroup_Name(clientVo.getGroup_Name());
			serverVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
			serverVo.setSystem_Status(ConsoleConstant.SYSTEM_STATUS_ONLINE);
			List<ServerVo> serverVoList = serverDao.queryServerList(serverVo);
			
			MethodVo methodVo = new MethodVo();
			methodVo.setService_Name(clientVo.getService_Name());
			methodVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
			List<MethodVo> methodVoList = methodDao.queryMethodList(methodVo);
			
			serviceVoMap.put(clientVo.getService_Name(), serviceVoList);
			serverVoMap.put(clientVo.getService_Name(), serverVoList);
			methodVoMap.put(clientVo.getService_Name(), methodVoList);
		}
		
		Map<String, Map<String, List<?>>> map = new HashMap<String, Map<String, List<?>>>();
		map.put(ConsoleRemotingConstant.CLIENT_NEED_INFO_TYPE_SERVICE, serviceVoMap);
		map.put(ConsoleRemotingConstant.CLIENT_NEED_INFO_TYPE_SERVER, serverVoMap);
		map.put(ConsoleRemotingConstant.CLIENT_NEED_INFO_TYPE_METHOD, methodVoMap);
		
		return map;
	}

	@Override
	public void updateClientOnline(ClientVo vo) throws Exception {
		clientDao.updateClientOnline(vo);
	}

	@Override
	public void updateClientOffline(ClientVo vo) throws Exception {
		clientDao.updateClientOffline(vo);
	}
}
