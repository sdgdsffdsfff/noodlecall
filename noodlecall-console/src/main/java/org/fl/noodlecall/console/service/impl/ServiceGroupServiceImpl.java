package org.fl.noodlecall.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fl.noodlecall.console.dao.ServiceGroupDao;
import org.fl.noodlecall.console.service.ServiceGroupService;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.ServiceGroupVo;

@Service("serviceGroupService")
public class ServiceGroupServiceImpl implements ServiceGroupService {

	@Autowired
	private ServiceGroupDao serviceGroupDao;
	
	@Override
	public PageVo<ServiceGroupVo> queryServiceGroupPage(ServiceGroupVo vo, int page, int rows) throws Exception {
		return serviceGroupDao.queryServiceGroupPage(vo, page, rows);
	}

	@Override
	public List<ServiceGroupVo> queryServiceGroupList(ServiceGroupVo vo) throws Exception {
		return serviceGroupDao.queryServiceGroupList(vo);
	}

	@Override
	public void insertServiceGroup(ServiceGroupVo vo) throws Exception {
		serviceGroupDao.insertServiceGroup(vo);
	}

	@Override
	public void insertsServiceGroup(ServiceGroupVo[] vos) throws Exception {
		serviceGroupDao.insertsServiceGroup(vos);
	}

	@Override
	public void updateServiceGroup(ServiceGroupVo vo) throws Exception {
		serviceGroupDao.updateServiceGroup(vo);
	}

	@Override
	public void updatesServiceGroup(ServiceGroupVo[] vos) throws Exception {
		serviceGroupDao.updatesServiceGroup(vos);
	}

	@Override
	public void deleteServiceGroup(ServiceGroupVo vo) throws Exception {
		serviceGroupDao.deleteServiceGroup(vo);
	}

	@Override
	public void deletesServiceGroup(ServiceGroupVo[] vos) throws Exception {
		for (ServiceGroupVo vo : vos) {
			serviceGroupDao.deleteServiceGroup(vo);
		}
	}

	@Override
	public PageVo<ServiceGroupVo> queryServiceGroupIncludePage(ServiceGroupVo vo, int page, int rows) throws Exception {
		return serviceGroupDao.queryServiceGroupIncludePage(vo, page, rows);
	}

	@Override
	public PageVo<ServiceGroupVo> queryServiceGroupExcludePage(ServiceGroupVo vo, int page, int rows) throws Exception {
		return serviceGroupDao.queryServiceGroupExcludePage(vo, page, rows);
	}
}
