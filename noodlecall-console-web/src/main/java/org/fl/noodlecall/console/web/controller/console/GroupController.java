package org.fl.noodlecall.console.web.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.fl.noodlecall.console.service.GroupService;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.GroupVo;
import org.fl.noodlecall.console.web.mvc.annotation.RequestParam;
import org.fl.noodlecall.console.web.mvc.annotation.ResponseBody;
import org.fl.noodlecall.console.web.mvc.util.VoidVo;

@Controller
@RequestMapping(value = "console/group")
public class GroupController {
	
	@Autowired
	private GroupService groupService;
	
	@RequestMapping(value = "/querypage")
	@ResponseBody
	public PageVo<GroupVo> queryPage(@RequestParam GroupVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return groupService.queryGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@ResponseBody
	public List<GroupVo> queryList(@RequestParam GroupVo vo) throws Exception {
		return groupService.queryGroupList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@ResponseBody
	public VoidVo insert(@RequestParam GroupVo vo) throws Exception {
		groupService.insertGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@ResponseBody
	public VoidVo inserts(@RequestParam GroupVo[] vos) throws Exception {
		groupService.insertsGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public VoidVo update(@RequestParam GroupVo vo) throws Exception {
		groupService.updateGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@ResponseBody
	public VoidVo updates(@RequestParam GroupVo[] vos) throws Exception {
		groupService.updatesGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public VoidVo delete(@RequestParam GroupVo vo) throws Exception {
		groupService.deleteGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@ResponseBody
	public VoidVo deletes(@RequestParam GroupVo[] vos) throws Exception {
		groupService.deletesGroup(vos);
		return VoidVo.VOID;
	}
}
