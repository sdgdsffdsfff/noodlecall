package org.fl.noodlecall.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.fl.noodlecall.console.dao.ServiceDao;
import org.fl.noodlecall.console.domain.ServiceMd;
import org.fl.noodlecall.console.persistence.sql.DynamicSqlTemplate;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.ServiceVo;

@Repository("serviceDao")
public class ServiceDaoImpl implements ServiceDao {

	@Autowired
	private DynamicSqlTemplate dynamicSqlTemplate;

	@Override
	public PageVo<ServiceVo> queryServicePage(ServiceVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("service_Id", vo.getService_Id() != null ? vo.getService_Id() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? (new StringBuilder().append("%").append(vo.getService_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("inteface_Name", vo.getInteface_Name() != null ? (new StringBuilder().append("%").append(vo.getInteface_Name()).append("%")).toString() : null);
		paramsMap.put("cluster_Type", vo.getCluster_Type() != null ? vo.getCluster_Type() : null);
		paramsMap.put("route_Type", vo.getRoute_Type() != null ? vo.getRoute_Type() : null);
		return dynamicSqlTemplate.queryPage("service-query-page", paramsMap, page, rows, ServiceVo.class);
	}

	@Override
	public List<ServiceVo> queryServiceList(ServiceVo vo) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("service_Id", vo.getService_Id() != null ? vo.getService_Id() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? vo.getService_Name() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		paramsMap.put("inteface_Name", vo.getInteface_Name() != null ? vo.getInteface_Name() : null);
		paramsMap.put("cluster_Type", vo.getCluster_Type() != null ? vo.getCluster_Type() : null);
		paramsMap.put("route_Type", vo.getRoute_Type() != null ? vo.getRoute_Type() : null);
		return dynamicSqlTemplate.queryList("service-query-list", paramsMap, ServiceVo.class);
	}

	@Override
	public void insertService(ServiceVo vo) throws Exception {
		dynamicSqlTemplate.insert(vo, ServiceMd.class);
	}

	@Override
	public void insertsService(ServiceVo[] vos) throws Exception {
		for (ServiceVo vo : vos) {
			dynamicSqlTemplate.insert(vo, ServiceMd.class);
		}
	}

	@Override
	public void updateService(ServiceVo vo) throws Exception {
		dynamicSqlTemplate.updateNonull(vo, ServiceMd.class);
	}

	@Override
	public void updatesService(ServiceVo[] vos) throws Exception {
		for (ServiceVo vo : vos) {
			dynamicSqlTemplate.updateNonull(vo, ServiceMd.class);
		}
	}

	@Override
	public void deleteService(ServiceVo vo) throws Exception {
		dynamicSqlTemplate.delete(vo, ServiceMd.class);
	}

	@Override
	public void deletesService(ServiceVo[] vos) throws Exception {
		for (ServiceVo vo : vos) {
			dynamicSqlTemplate.delete(vo, ServiceMd.class);
		}
	}

	@Override
	public Long updateOrInsertService(ServiceVo vo) throws Exception {
		
		int num = dynamicSqlTemplate.updateNonullNoById(vo, ServiceMd.class, new String[]{"service_Name"});
		
		if (num > 0) {			
			List<ServiceVo> serviceVoList = queryServiceList(vo);
			if (serviceVoList.size() > 0) {
				return serviceVoList.get(0).getService_Id();
			}
		} else {
			vo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
			ServiceMd serviceMd = dynamicSqlTemplate.insert(vo, ServiceMd.class);
			return serviceMd.getService_Id();
		}
		
		return 0L;
	}
}
