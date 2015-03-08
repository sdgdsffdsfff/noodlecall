package org.fl.noodlecall.console.dao;

import java.util.List;

import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.ServiceGroupVo;

public interface ServiceGroupDao {
	
	public PageVo<ServiceGroupVo> queryServiceGroupPage(ServiceGroupVo vo, int page, int rows) throws Exception;

	public List<ServiceGroupVo> queryServiceGroupList(ServiceGroupVo vo) throws Exception;

	public void insertServiceGroup(ServiceGroupVo vo) throws Exception;

	public void insertsServiceGroup(ServiceGroupVo[] vos) throws Exception;

	public void updateServiceGroup(ServiceGroupVo vo) throws Exception;

	public void updatesServiceGroup(ServiceGroupVo[] vos) throws Exception;

	public void deleteServiceGroup(ServiceGroupVo vo) throws Exception;

	public void deletesServiceGroup(ServiceGroupVo[] vos) throws Exception;

	public PageVo<ServiceGroupVo> queryServiceGroupIncludePage(ServiceGroupVo vo, int page, int rows) throws Exception;

	public PageVo<ServiceGroupVo> queryServiceGroupExcludePage(ServiceGroupVo vo, int page, int rows) throws Exception;
}
