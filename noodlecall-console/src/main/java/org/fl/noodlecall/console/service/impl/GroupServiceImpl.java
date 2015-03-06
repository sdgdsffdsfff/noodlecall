package org.fl.noodlecall.console.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.fl.noodlecall.console.dao.GroupDao;
import org.fl.noodlecall.console.service.GroupService;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.GroupVo;

@Service("groupService")
public class GroupServiceImpl implements GroupService {

	@Autowired
	private GroupDao groupDao;
	
	@Override
	public PageVo<GroupVo> queryGroupPage(GroupVo vo, int page, int rows) throws Exception {
		return groupDao.queryGroupPage(vo, page, rows);
	}

	@Override
	public List<GroupVo> queryGroupList(GroupVo vo) throws Exception {
		return groupDao.queryGroupList(vo);
	}

	@Override
	public void insertGroup(GroupVo vo) throws Exception {
		groupDao.insertGroup(vo);
	}

	@Override
	public void insertsGroup(GroupVo[] vos) throws Exception {
		groupDao.insertsGroup(vos);
	}

	@Override
	public void updateGroup(GroupVo vo) throws Exception {
		groupDao.updateGroup(vo);
	}

	@Override
	public void updatesGroup(GroupVo[] vos) throws Exception {
		groupDao.updatesGroup(vos);
	}

	@Override
	public void deleteGroup(GroupVo vo) throws Exception {
		groupDao.deleteGroup(vo);
	}

	@Override
	public void deletesGroup(GroupVo[] vos) throws Exception {
		for (GroupVo vo : vos) {
			groupDao.deleteGroup(vo);
		}
	}
}
