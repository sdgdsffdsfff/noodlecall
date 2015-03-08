package org.fl.noodlecall.console.dao;

import java.util.List;

import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.ServiceVo;

public interface ServiceDao {
	
	public PageVo<ServiceVo> queryServicePage(ServiceVo vo, int page, int rows) throws Exception;

	public List<ServiceVo> queryServiceList(ServiceVo vo) throws Exception;

	public void insertService(ServiceVo vo) throws Exception;

	public void insertsService(ServiceVo[] vos) throws Exception;

	public void updateService(ServiceVo vo) throws Exception;

	public void updatesService(ServiceVo[] vos) throws Exception;

	public void deleteService(ServiceVo vo) throws Exception;

	public void deletesService(ServiceVo[] vos) throws Exception;
	
	public Long updateOrInsertService(ServiceVo vo) throws Exception;
}
