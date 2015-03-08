package org.fl.noodlecall.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fl.noodlecall.console.dao.MethodDao;
import org.fl.noodlecall.console.service.MethodService;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.MethodVo;

@Service("methodService")
public class MethodServiceImpl implements MethodService {

	@Autowired
	private MethodDao methodDao;
	
	@Override
	public PageVo<MethodVo> queryMethodPage(MethodVo vo, int page, int rows) throws Exception {
		return methodDao.queryMethodPage(vo, page, rows);
	}

	@Override
	public List<MethodVo> queryMethodList(MethodVo vo) throws Exception {
		return methodDao.queryMethodList(vo);
	}

	@Override
	public void insertMethod(MethodVo vo) throws Exception {
		methodDao.insertMethod(vo);
	}

	@Override
	public void insertsMethod(MethodVo[] vos) throws Exception {
		methodDao.insertsMethod(vos);
	}

	@Override
	public void updateMethod(MethodVo vo) throws Exception {
		methodDao.updateMethod(vo);
	}

	@Override
	public void updatesMethod(MethodVo[] vos) throws Exception {
		methodDao.updatesMethod(vos);
	}

	@Override
	public void deleteMethod(MethodVo vo) throws Exception {
		methodDao.deleteMethod(vo);
	}

	@Override
	public void deletesMethod(MethodVo[] vos) throws Exception {
		for (MethodVo vo : vos) {
			methodDao.deleteMethod(vo);
		}
	}

	@Override
	public PageVo<MethodVo> queryMethodByServicePage(MethodVo vo, int page, int rows) throws Exception {
		return methodDao.queryMethodByServicePage(vo, page, rows);
	}
}
