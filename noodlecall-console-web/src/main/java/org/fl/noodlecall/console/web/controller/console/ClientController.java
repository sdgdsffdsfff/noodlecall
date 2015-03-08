package org.fl.noodlecall.console.web.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.fl.noodle.common.mvc.annotation.NoodleRequestParam;
import org.fl.noodle.common.mvc.annotation.NoodleResponseBody;
import org.fl.noodle.common.mvc.vo.VoidVo;
import org.fl.noodlecall.console.service.ClientService;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.ClientVo;

@Controller
@RequestMapping(value = "console/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value = "/querypage")
	@NoodleResponseBody
	public PageVo<ClientVo> queryPage(@NoodleRequestParam ClientVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return clientService.queryClientPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@NoodleResponseBody
	public List<ClientVo> queryList(@NoodleRequestParam ClientVo vo) throws Exception {
		return clientService.queryClientList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@NoodleResponseBody
	public VoidVo insert(@NoodleRequestParam ClientVo vo) throws Exception {
		clientService.insertClient(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@NoodleResponseBody
	public VoidVo inserts(@NoodleRequestParam ClientVo[] vos) throws Exception {
		clientService.insertsClient(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@NoodleResponseBody
	public VoidVo update(@NoodleRequestParam ClientVo vo) throws Exception {
		clientService.updateClient(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@NoodleResponseBody
	public VoidVo updates(@NoodleRequestParam ClientVo[] vos) throws Exception {
		clientService.updatesClient(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@NoodleResponseBody
	public VoidVo delete(@NoodleRequestParam ClientVo vo) throws Exception {
		clientService.deleteClient(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@NoodleResponseBody
	public VoidVo deletes(@NoodleRequestParam ClientVo[] vos) throws Exception {
		clientService.deletesClient(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/queryincludeservicepage")
	@NoodleResponseBody
	public PageVo<ClientVo> queryIncludeServicePage(@NoodleRequestParam ClientVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return clientService.queryClientIncludeServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/queryexcludeservicepage")
	@NoodleResponseBody
	public PageVo<ClientVo> queryExcludeServicePage(@NoodleRequestParam ClientVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return clientService.queryClientExcludeServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/insertsservice")
	@NoodleResponseBody
	public VoidVo insertsService(@NoodleRequestParam ClientVo[] vos) throws Exception {
		clientService.insertsClientService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletesservice")
	@NoodleResponseBody
	public VoidVo deletesService(@NoodleRequestParam ClientVo[] vos) throws Exception {
		clientService.deletesClientService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/queryincludegrouppage")
	@NoodleResponseBody
	public PageVo<ClientVo> queryIncludeGroupPage(@NoodleRequestParam ClientVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return clientService.queryClientIncludeGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/queryexcludegrouppage")
	@NoodleResponseBody
	public PageVo<ClientVo> queryExcludeGroupPage(@NoodleRequestParam ClientVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return clientService.queryClientExcludeGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/insertsgroup")
	@NoodleResponseBody
	public VoidVo insertsGroup(@NoodleRequestParam ClientVo[] vos) throws Exception {
		clientService.insertsClientGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletesgroup")
	@NoodleResponseBody
	public VoidVo deletesGroup(@NoodleRequestParam ClientVo[] vos) throws Exception {
		clientService.deletesClientGroup(vos);
		return VoidVo.VOID;
	}
}
