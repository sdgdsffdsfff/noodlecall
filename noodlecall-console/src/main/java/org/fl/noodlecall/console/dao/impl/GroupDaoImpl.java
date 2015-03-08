package org.fl.noodlecall.console.dao.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import org.fl.noodlecall.console.dao.GroupDao;
import org.fl.noodlecall.console.domain.GroupMd;
import org.fl.noodlecall.console.persistence.sql.DynamicSqlTemplate;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.GroupVo;

@Repository("groupDao")
public class GroupDaoImpl implements GroupDao {

	@Autowired
	private DynamicSqlTemplate dynamicSqlTemplate;

	@Override
	public PageVo<GroupVo> queryGroupPage(GroupVo vo, int page, int rows) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("group_Id", vo.getGroup_Id() != null ? vo.getGroup_Id() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? (new StringBuilder().append("%").append(vo.getGroup_Name()).append("%")).toString() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		return dynamicSqlTemplate.queryPage("group-query-page", paramsMap, page, rows, GroupVo.class);
	}

	@Override
	public List<GroupVo> queryGroupList(GroupVo vo) throws Exception {
		Map<String, Object> paramsMap = new HashMap<String, Object>();
		paramsMap.put("group_Id", vo.getGroup_Id() != null ? vo.getGroup_Id() : null);
		paramsMap.put("group_Name", vo.getGroup_Name() != null ? vo.getGroup_Name() : null);
		paramsMap.put("manual_Status", vo.getManual_Status() != null ? vo.getManual_Status() : null);
		return dynamicSqlTemplate.queryList("group-query-list", paramsMap, GroupVo.class);
	}

	@Override
	public void insertGroup(GroupVo vo) throws Exception {
		dynamicSqlTemplate.insert(vo, GroupMd.class);
	}

	@Override
	public void insertsGroup(GroupVo[] vos) throws Exception {
		for (GroupVo vo : vos) {
			dynamicSqlTemplate.insert(vo, GroupMd.class);
		}
	}

	@Override
	public void updateGroup(GroupVo vo) throws Exception {
		dynamicSqlTemplate.updateNonull(vo, GroupMd.class);
	}

	@Override
	public void updatesGroup(GroupVo[] vos) throws Exception {
		for (GroupVo vo : vos) {
			dynamicSqlTemplate.updateNonull(vo, GroupMd.class);
		}
	}

	@Override
	public void deleteGroup(GroupVo vo) throws Exception {
		dynamicSqlTemplate.delete(vo, GroupMd.class);
	}

	@Override
	public void deletesGroup(GroupVo[] vos) throws Exception {
		for (GroupVo vo : vos) {
			dynamicSqlTemplate.delete(vo, GroupMd.class);
		}
	}
}
