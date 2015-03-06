package org.fl.noodlecall.console.service;

import java.util.List;

import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.MethodVo;

public interface MethodService {
	
	public PageVo<MethodVo> queryMethodPage(MethodVo vo, int page, int rows) throws Exception;

	public List<MethodVo> queryMethodList(MethodVo vo) throws Exception;

	public void insertMethod(MethodVo vo) throws Exception;

	public void insertsMethod(MethodVo[] vos) throws Exception;

	public void updateMethod(MethodVo vo) throws Exception;

	public void updatesMethod(MethodVo[] vos) throws Exception;

	public void deleteMethod(MethodVo vo) throws Exception;

	public void deletesMethod(MethodVo[] vos) throws Exception;

	public PageVo<MethodVo> queryMethodByServicePage(MethodVo vo, int page, int rows) throws Exception;
}
