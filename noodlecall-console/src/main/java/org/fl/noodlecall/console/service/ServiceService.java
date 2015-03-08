package org.fl.noodlecall.console.service;

import java.util.List;
import java.util.Map;

import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.ServiceVo;

public interface ServiceService {
	
	public PageVo<ServiceVo> queryServicePage(ServiceVo vo, int page, int rows) throws Exception;

	public List<ServiceVo> queryServiceList(ServiceVo vo) throws Exception;

	public void insertService(ServiceVo vo) throws Exception;

	public void insertsService(ServiceVo[] vos) throws Exception;

	public void updateService(ServiceVo vo) throws Exception;

	public void updatesService(ServiceVo[] vos) throws Exception;

	public void deleteService(ServiceVo vo) throws Exception;

	public void deletesService(ServiceVo[] vos) throws Exception;
	
	public Map<String, Map<String, List<?>>> queryGraph(ServiceVo vo) throws Exception;	
}
