package org.fl.noodlecall.console.web.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.fl.noodle.common.mvc.annotation.NoodleRequestParam;
import org.fl.noodle.common.mvc.annotation.NoodleResponseBody;
import org.fl.noodle.common.mvc.vo.VoidVo;
import org.fl.noodlecall.console.service.ServerService;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.ServerVo;

@Controller
@RequestMapping(value = "console/server")
public class ServerController {
	
	@Autowired
	private ServerService serverService;
	
	@RequestMapping(value = "/querypage")
	@NoodleResponseBody
	public PageVo<ServerVo> queryPage(@NoodleRequestParam ServerVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serverService.queryServerPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@NoodleResponseBody
	public List<ServerVo> queryList(@NoodleRequestParam ServerVo vo) throws Exception {
		return serverService.queryServerList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@NoodleResponseBody
	public VoidVo insert(@NoodleRequestParam ServerVo vo) throws Exception {
		serverService.insertServer(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@NoodleResponseBody
	public VoidVo inserts(@NoodleRequestParam ServerVo[] vos) throws Exception {
		serverService.insertsServer(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@NoodleResponseBody
	public VoidVo update(@NoodleRequestParam ServerVo vo) throws Exception {
		serverService.updateServer(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@NoodleResponseBody
	public VoidVo updates(@NoodleRequestParam ServerVo[] vos) throws Exception {
		serverService.updatesServer(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@NoodleResponseBody
	public VoidVo delete(@NoodleRequestParam ServerVo vo) throws Exception {
		serverService.deleteServer(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@NoodleResponseBody
	public VoidVo deletes(@NoodleRequestParam ServerVo[] vos) throws Exception {
		serverService.deletesServer(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/queryincludeservicepage")
	@NoodleResponseBody
	public PageVo<ServerVo> queryIncludeServicePage(@NoodleRequestParam ServerVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serverService.queryServerIncludeServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/queryexcludeservicepage")
	@NoodleResponseBody
	public PageVo<ServerVo> queryExcludeServicePage(@NoodleRequestParam ServerVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serverService.queryServerExcludeServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/insertsservice")
	@NoodleResponseBody
	public VoidVo insertsService(@NoodleRequestParam ServerVo[] vos) throws Exception {
		serverService.insertsServerService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletesservice")
	@NoodleResponseBody
	public VoidVo deletesService(@NoodleRequestParam ServerVo[] vos) throws Exception {
		serverService.deletesServerService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/queryincludegrouppage")
	@NoodleResponseBody
	public PageVo<ServerVo> queryIncludeGroupPage(@NoodleRequestParam ServerVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serverService.queryServerIncludeGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/queryexcludegrouppage")
	@NoodleResponseBody
	public PageVo<ServerVo> queryExcludeGroupPage(@NoodleRequestParam ServerVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serverService.queryServerExcludeGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/insertsgroup")
	@NoodleResponseBody
	public VoidVo insertsGroup(@NoodleRequestParam ServerVo[] vos) throws Exception {
		serverService.insertsServerGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletesgroup")
	@NoodleResponseBody
	public VoidVo deletesGroup(@NoodleRequestParam ServerVo[] vos) throws Exception {
		serverService.deletesServerGroup(vos);
		return VoidVo.VOID;
	}
}
