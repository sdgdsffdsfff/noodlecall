package org.fl.noodlecall.console.web.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.fl.noodle.common.mvc.annotation.NoodleRequestParam;
import org.fl.noodle.common.mvc.annotation.NoodleResponseBody;
import org.fl.noodle.common.mvc.vo.VoidVo;
import org.fl.noodlecall.console.service.GroupService;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.GroupVo;

@Controller
@RequestMapping(value = "console/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/querypage")
	@NoodleResponseBody
	public PageVo<GroupVo> queryPage(@NoodleRequestParam GroupVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return groupService.queryGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@NoodleResponseBody
	public List<GroupVo> queryList(@NoodleRequestParam GroupVo vo) throws Exception {
		return groupService.queryGroupList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@NoodleResponseBody
	public VoidVo insert(@NoodleRequestParam GroupVo vo) throws Exception {
		groupService.insertGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@NoodleResponseBody
	public VoidVo inserts(@NoodleRequestParam GroupVo[] vos) throws Exception {
		groupService.insertsGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@NoodleResponseBody
	public VoidVo update(@NoodleRequestParam GroupVo vo) throws Exception {
		groupService.updateGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@NoodleResponseBody
	public VoidVo updates(@NoodleRequestParam GroupVo[] vos) throws Exception {
		groupService.updatesGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@NoodleResponseBody
	public VoidVo delete(@NoodleRequestParam GroupVo vo) throws Exception {
		groupService.deleteGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@NoodleResponseBody
	public VoidVo deletes(@NoodleRequestParam GroupVo[] vos) throws Exception {
		groupService.deletesGroup(vos);
		return VoidVo.VOID;
	}
}
