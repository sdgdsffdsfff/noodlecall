package org.fl.noodlecall.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.fl.noodlecall.console.dao.ServiceGroupDao;
import org.fl.noodlecall.console.domain.ServiceGroupMd;
import org.fl.noodlecall.console.persistence.sql.DynamicSqlTemplate;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.ServiceGroupVo;

@Repository("serviceGroupDao")
public class ServiceGroupDaoImpl implements ServiceGroupDao {

	@Autowired
	private DynamicSqlTemplate dynamicSqlTemplate;

	@Override
	public PageVo<ServiceGroupVo> queryServiceGroupPage(ServiceGroupVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("serviceGroup_Id", vo.getServiceGroup_Id() != null ? vo.getServiceGroup_Id() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? (new StringBuilder().append("%").append(vo.getService_Name()).append("%")).toString() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? (new StringBuilder().append("%").append(vo.getGroup_Name()).append("%")).toString() : null);
		return dynamicSqlTemplate.queryPage("service-group-query-page", paramsMap, page, rows, ServiceGroupVo.class);
	}

	@Override
	public List<ServiceGroupVo> queryServiceGroupList(ServiceGroupVo vo) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("serviceGroup_Id", vo.getServiceGroup_Id() != null ? vo.getServiceGroup_Id() : null);
		paramsMap.put("service_Name", vo.getService_Name() != null ? vo.getService_Name() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? vo.getGroup_Name() : null);
		return dynamicSqlTemplate.queryList("service-group-query-list", paramsMap, ServiceGroupVo.class);
	}

	@Override
	public void insertServiceGroup(ServiceGroupVo vo) throws Exception {
		dynamicSqlTemplate.insert(vo, ServiceGroupMd.class);
	}

	@Override
	public void insertsServiceGroup(ServiceGroupVo[] vos) throws Exception {
		for (ServiceGroupVo vo : vos) {
			dynamicSqlTemplate.insert(vo, ServiceGroupMd.class);
		}
	}

	@Override
	public void updateServiceGroup(ServiceGroupVo vo) throws Exception {
		dynamicSqlTemplate.updateNonull(vo, ServiceGroupMd.class);
	}

	@Override
	public void updatesServiceGroup(ServiceGroupVo[] vos) throws Exception {
		for (ServiceGroupVo vo : vos) {
			dynamicSqlTemplate.updateNonull(vo, ServiceGroupMd.class);
		}
	}

	@Override
	public void deleteServiceGroup(ServiceGroupVo vo) throws Exception {
		dynamicSqlTemplate.delete(vo, ServiceGroupMd.class);
	}

	@Override
	public void deletesServiceGroup(ServiceGroupVo[] vos) throws Exception {
		for (ServiceGroupVo vo : vos) {
			dynamicSqlTemplate.delete(vo, ServiceGroupMd.class);
		}
	}

	@Override
	public PageVo<ServiceGroupVo> queryServiceGroupIncludePage(ServiceGroupVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("service_Name", vo.getService_Name() != null ? vo.getService_Name() : null);
		paramsMap.put("group_Id", vo.getGroup_Id() != null ? vo.getGroup_Id() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? (new StringBuilder().append("%").append(vo.getGroup_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		return dynamicSqlTemplate.queryPage("service-group-query-include-page", paramsMap, page, rows, ServiceGroupVo.class);
	}

	@Override
	public PageVo<ServiceGroupVo> queryServiceGroupExcludePage(ServiceGroupVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("service_Name", vo.getService_Name() != null ? vo.getService_Name() : null);
		paramsMap.put("group_Id", vo.getGroup_Id() != null ? vo.getGroup_Id() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? (new StringBuilder().append("%").append(vo.getGroup_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		return dynamicSqlTemplate.queryPage("service-group-query-exclude-page", paramsMap, page, rows, ServiceGroupVo.class);
	}
}
