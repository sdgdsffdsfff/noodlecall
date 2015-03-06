package org.fl.noodlecall.console.dao;

import java.util.List;
import java.util.Map;

import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.ClientVo;

public interface ClientDao {
	
	public PageVo<ClientVo> queryClientPage(ClientVo vo, int page, int rows) throws Exception;

	public List<ClientVo> queryClientList(ClientVo vo) throws Exception;

	public void insertClient(ClientVo vo) throws Exception;

	public void insertsClient(ClientVo[] vos) throws Exception;

	public void updateClient(ClientVo vo) throws Exception;

	public void updatesClient(ClientVo[] vos) throws Exception;

	public void deleteClient(ClientVo vo) throws Exception;

	public void deletesClient(ClientVo[] vos) throws Exception;
	
	public PageVo<ClientVo> queryClientIncludeServicePage(ClientVo vo, int page, int rows) throws Exception;
	
	public PageVo<ClientVo> queryClientExcludeServicePage(ClientVo vo, int page, int rows) throws Exception;
	
	public void insertsClientService(ClientVo[] vos) throws Exception;
	
	public void deletesClientService(ClientVo[] vos) throws Exception;
	
	public PageVo<ClientVo> queryClientIncludeGroupPage(ClientVo vo, int page, int rows) throws Exception;
	
	public PageVo<ClientVo> queryClientExcludeGroupPage(ClientVo vo, int page, int rows) throws Exception;

	public void insertsClientGroup(ClientVo[] vos) throws Exception;
	
	public void deletesClientGroup(ClientVo[] vos) throws Exception;	
	
	public Long updateOrInsertClient(ClientVo vo) throws Exception;
	
	public Map<String, Map<String, List<?>>> updateAndQueryClientNeedInfo(ClientVo vo) throws Exception;
	
	public void updateClientOnline(ClientVo vo) throws Exception;

	public void updateClientOffline(ClientVo vo) throws Exception;
}
