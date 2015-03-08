package org.fl.noodlecall.console.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fl.noodlecall.console.dao.ClientDao;
import org.fl.noodlecall.console.dao.GroupDao;
import org.fl.noodlecall.console.dao.ServerDao;
import org.fl.noodlecall.console.dao.ServiceDao;
import org.fl.noodlecall.console.dao.ServiceGroupDao;
import org.fl.noodlecall.console.service.ServiceService;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.ClientVo;
import org.fl.noodlecall.console.vo.GroupVo;
import org.fl.noodlecall.console.vo.ServerVo;
import org.fl.noodlecall.console.vo.ServiceGroupVo;
import org.fl.noodlecall.console.vo.ServiceVo;

@Service("serviceService")
public class ServiceServiceImpl implements ServiceService {

	@Autowired
	private ServiceDao serviceDao;
	
	@Autowired
	private ClientDao clientDao;
	
	@Autowired
	private ServerDao serverDao;
	
	@Autowired
	private ServiceGroupDao serviceGroupDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Override
	public PageVo<ServiceVo> queryServicePage(ServiceVo vo, int page, int rows) throws Exception {
		return serviceDao.queryServicePage(vo, page, rows);
	}

	@Override
	public List<ServiceVo> queryServiceList(ServiceVo vo) throws Exception {
		return serviceDao.queryServiceList(vo);
	}

	@Override
	public void insertService(ServiceVo vo) throws Exception {
		serviceDao.insertService(vo);
	}

	@Override
	public void insertsService(ServiceVo[] vos) throws Exception {
		serviceDao.insertsService(vos);
	}

	@Override
	public void updateService(ServiceVo vo) throws Exception {
		serviceDao.updateService(vo);
	}

	@Override
	public void updatesService(ServiceVo[] vos) throws Exception {
		serviceDao.updatesService(vos);
	}

	@Override
	public void deleteService(ServiceVo vo) throws Exception {
		serviceDao.deleteService(vo);
	}

	@Override
	public void deletesService(ServiceVo[] vos) throws Exception {
		for (ServiceVo vo : vos) {
			serviceDao.deleteService(vo);
		}
	}

	@Override
	public Map<String, Map<String, List<?>>> queryGraph(ServiceVo vo) throws Exception {
		
		Map<String, Map<String, List<?>>> map = new HashMap<String, Map<String, List<?>>>();
		
		ServiceGroupVo serviceGroupVo = new ServiceGroupVo();
		serviceGroupVo.setService_Name(vo.getService_Name());
		List<ServiceGroupVo> serviceGroupVoList = serviceGroupDao.queryServiceGroupList(serviceGroupVo);
		
		for (ServiceGroupVo serviceGroupVoIt : serviceGroupVoList) {
			
			GroupVo groupVo = new GroupVo();
			groupVo.setGroup_Id(serviceGroupVoIt.getGroup_Id());
			groupVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
			List<GroupVo> groupVoList = groupDao.queryGroupList(groupVo);
			if (groupVoList.size() == 0) {
				continue;
			}
			
			Map<String, List<?>> groupClientMap = new HashMap<String, List<?>>();
			
			ClientVo clientVo = new ClientVo();
			clientVo.setService_Name(vo.getService_Name());
			clientVo.setGroup_Name(serviceGroupVoIt.getGroup_Name());
			clientVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
			List<ClientVo> clientVoList = clientDao.queryClientList(clientVo);
			groupClientMap.put("client", clientVoList);
			
			ServerVo serverVo = new ServerVo();
			serverVo.setService_Name(vo.getService_Name());
			serverVo.setGroup_Name(serviceGroupVoIt.getGroup_Name());
			serverVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
			List<ServerVo> serverVoList = serverDao.queryServerList(serverVo);
			groupClientMap.put("server", serverVoList);

			map.put(serviceGroupVoIt.getGroup_Name(), groupClientMap);	
		}
		
		return map;
	}
}
