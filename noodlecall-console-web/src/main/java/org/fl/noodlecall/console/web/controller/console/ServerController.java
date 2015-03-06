package org.fl.noodlecall.console.web.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.fl.noodlecall.console.service.ServerService;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.ServerVo;
import org.fl.noodlecall.console.web.mvc.annotation.RequestParam;
import org.fl.noodlecall.console.web.mvc.annotation.ResponseBody;
import org.fl.noodlecall.console.web.mvc.util.VoidVo;

@Controller
@RequestMapping(value = "console/server")
public class ServerController {
	
	@Autowired
	private ServerService serverService;
	
	@RequestMapping(value = "/querypage")
	@ResponseBody
	public PageVo<ServerVo> queryPage(@RequestParam ServerVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serverService.queryServerPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@ResponseBody
	public List<ServerVo> queryList(@RequestParam ServerVo vo) throws Exception {
		return serverService.queryServerList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@ResponseBody
	public VoidVo insert(@RequestParam ServerVo vo) throws Exception {
		serverService.insertServer(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@ResponseBody
	public VoidVo inserts(@RequestParam ServerVo[] vos) throws Exception {
		serverService.insertsServer(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public VoidVo update(@RequestParam ServerVo vo) throws Exception {
		serverService.updateServer(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@ResponseBody
	public VoidVo updates(@RequestParam ServerVo[] vos) throws Exception {
		serverService.updatesServer(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public VoidVo delete(@RequestParam ServerVo vo) throws Exception {
		serverService.deleteServer(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@ResponseBody
	public VoidVo deletes(@RequestParam ServerVo[] vos) throws Exception {
		serverService.deletesServer(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/queryincludeservicepage")
	@ResponseBody
	public PageVo<ServerVo> queryIncludeServicePage(@RequestParam ServerVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serverService.queryServerIncludeServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/queryexcludeservicepage")
	@ResponseBody
	public PageVo<ServerVo> queryExcludeServicePage(@RequestParam ServerVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serverService.queryServerExcludeServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/insertsservice")
	@ResponseBody
	public VoidVo insertsService(@RequestParam ServerVo[] vos) throws Exception {
		serverService.insertsServerService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletesservice")
	@ResponseBody
	public VoidVo deletesService(@RequestParam ServerVo[] vos) throws Exception {
		serverService.deletesServerService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/queryincludegrouppage")
	@ResponseBody
	public PageVo<ServerVo> queryIncludeGroupPage(@RequestParam ServerVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serverService.queryServerIncludeGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/queryexcludegrouppage")
	@ResponseBody
	public PageVo<ServerVo> queryExcludeGroupPage(@RequestParam ServerVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serverService.queryServerExcludeGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/insertsgroup")
	@ResponseBody
	public VoidVo insertsGroup(@RequestParam ServerVo[] vos) throws Exception {
		serverService.insertsServerGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletesgroup")
	@ResponseBody
	public VoidVo deletesGroup(@RequestParam ServerVo[] vos) throws Exception {
		serverService.deletesServerGroup(vos);
		return VoidVo.VOID;
	}
}
