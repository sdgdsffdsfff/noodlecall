package org.fl.noodlecall.console.service;

import java.util.List;

import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.GroupVo;

public interface GroupService {
	
	public PageVo<GroupVo> queryGroupPage(GroupVo vo, int page, int rows) throws Exception;

	public List<GroupVo> queryGroupList(GroupVo vo) throws Exception;

	public void insertGroup(GroupVo vo) throws Exception;

	public void insertsGroup(GroupVo[] vos) throws Exception;

	public void updateGroup(GroupVo vo) throws Exception;

	public void updatesGroup(GroupVo[] vos) throws Exception;

	public void deleteGroup(GroupVo vo) throws Exception;

	public void deletesGroup(GroupVo[] vos) throws Exception;
}
