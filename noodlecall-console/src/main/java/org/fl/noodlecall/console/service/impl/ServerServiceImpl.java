package org.fl.noodlecall.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.fl.noodlecall.console.dao.GroupDao;
import org.fl.noodlecall.console.dao.MethodDao;
import org.fl.noodlecall.console.dao.ServerDao;
import org.fl.noodlecall.console.dao.ServiceDao;
import org.fl.noodlecall.console.dao.ServiceGroupDao;
import org.fl.noodlecall.console.service.ServerService;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodlecall.console.vo.GroupVo;
import org.fl.noodlecall.console.vo.MethodVo;
import org.fl.noodlecall.console.vo.ServerVo;
import org.fl.noodlecall.console.vo.ServiceGroupVo;
import org.fl.noodlecall.console.vo.ServiceVo;

@Service("serverService")
public class ServerServiceImpl implements ServerService {

	@Autowired
	private ServerDao serverDao;
	
	@Autowired
	private ServiceDao serviceDao;
	
	@Autowired
	private MethodDao methodDao;
	
	@Autowired
	private GroupDao groupDao;
	
	@Autowired
	private ServiceGroupDao serviceGroupDao;
	
	@Override
	public PageVo<ServerVo> queryServerPage(ServerVo vo, int page, int rows) throws Exception {
		return serverDao.queryServerPage(vo, page, rows);
	}

	@Override
	public List<ServerVo> queryServerList(ServerVo vo) throws Exception {
		return serverDao.queryServerList(vo);
	}

	@Override
	public void insertServer(ServerVo vo) throws Exception {
		serverDao.insertServer(vo);
	}

	@Override
	public void insertsServer(ServerVo[] vos) throws Exception {
		serverDao.insertsServer(vos);
	}

	@Override
	public void updateServer(ServerVo vo) throws Exception {
		serverDao.updateServer(vo);
	}

	@Override
	public void updatesServer(ServerVo[] vos) throws Exception {
		serverDao.updatesServer(vos);
	}

	@Override
	public void deleteServer(ServerVo vo) throws Exception {
		serverDao.deleteServer(vo);
	}

	@Override
	public void deletesServer(ServerVo[] vos) throws Exception {
		for (ServerVo vo : vos) {
			serverDao.deleteServer(vo);
		}
	}
	
	@Override
	public PageVo<ServerVo> queryServerIncludeServicePage(ServerVo vo, int page, int rows) throws Exception {
		return serverDao.queryServerIncludeServicePage(vo, page, rows);
	}

	@Override
	public PageVo<ServerVo> queryServerExcludeServicePage(ServerVo vo, int page, int rows) throws Exception {
		return serverDao.queryServerExcludeServicePage(vo, page, rows);
	}
	
	@Override
	public void insertsServerService(ServerVo[] vos) throws Exception {
		serverDao.insertsServerService(vos);
	}
	
	@Override
	public void deletesServerService(ServerVo[] vos) throws Exception {
		serverDao.deletesServerService(vos);
	}
	
	@Override
	public PageVo<ServerVo> queryServerIncludeGroupPage(ServerVo vo, int page, int rows) throws Exception {
		return serverDao.queryServerIncludeGroupPage(vo, page, rows);
	}

	@Override
	public PageVo<ServerVo> queryServerExcludeGroupPage(ServerVo vo, int page, int rows) throws Exception {
		return serverDao.queryServerExcludeGroupPage(vo, page, rows);
	}
	
	@Override
	public void insertsServerGroup(ServerVo[] vos) throws Exception {
		serverDao.insertsServerGroup(vos);
	}
	
	@Override
	public void deletesServerGroup(ServerVo[] vos) throws Exception {
		serverDao.deletesServerGroup(vos);
	}

	@Override
	public Long updateOrInsertServer(ServerVo vo, ServiceVo serviceVo, MethodVo[] methodVos) throws Exception {
		
		if (serviceVo != null && serviceVo.getService_Name() != null) {
			GroupVo groupVo = new GroupVo();
			groupVo.setGroup_Name("DefaultGroup");
			List<GroupVo> groupVoList = groupDao.queryGroupList(groupVo);
			if (groupVoList.size() == 0) {
				groupVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
				groupDao.insertGroup(groupVo);
			}
			
			serviceDao.updateOrInsertService(serviceVo);
			
			ServiceGroupVo serviceGroupVo = new ServiceGroupVo();
			serviceGroupVo.setGroup_Name("DefaultGroup");
			serviceGroupVo.setService_Name(serviceVo.getService_Name());
			List<ServiceGroupVo> serviceGroupVoList = serviceGroupDao.queryServiceGroupList(serviceGroupVo);
			if (serviceGroupVoList.size() == 0) {
				serviceGroupDao.insertServiceGroup(serviceGroupVo);
			}
		}
		
		if (methodVos != null && methodVos.length > 0) {			
			methodDao.updateOrInsertsMethod(methodVos);
		}
		
		return serverDao.updateOrInsertServer(vo);
	}

	@Override
	public List<ServerVo> queryServerOnlineList(ServerVo vo) throws Exception {
		return serverDao.queryServerOnlineList(vo);
	}

	@Override
	public List<ServerVo> queryServerOfflineList(ServerVo vo) throws Exception {
		return serverDao.queryServerOfflineList(vo);
	}
}
