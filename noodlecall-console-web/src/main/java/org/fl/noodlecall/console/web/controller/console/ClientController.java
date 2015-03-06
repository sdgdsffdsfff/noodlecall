package org.fl.noodlecall.console.web.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.fl.noodlecall.console.service.ClientService;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.ClientVo;
import org.fl.noodlecall.console.web.mvc.annotation.RequestParam;
import org.fl.noodlecall.console.web.mvc.annotation.ResponseBody;
import org.fl.noodlecall.console.web.mvc.util.VoidVo;

@Controller
@RequestMapping(value = "console/client")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	
	@RequestMapping(value = "/querypage")
	@ResponseBody
	public PageVo<ClientVo> queryPage(@RequestParam ClientVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return clientService.queryClientPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@ResponseBody
	public List<ClientVo> queryList(@RequestParam ClientVo vo) throws Exception {
		return clientService.queryClientList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@ResponseBody
	public VoidVo insert(@RequestParam ClientVo vo) throws Exception {
		clientService.insertClient(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@ResponseBody
	public VoidVo inserts(@RequestParam ClientVo[] vos) throws Exception {
		clientService.insertsClient(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public VoidVo update(@RequestParam ClientVo vo) throws Exception {
		clientService.updateClient(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@ResponseBody
	public VoidVo updates(@RequestParam ClientVo[] vos) throws Exception {
		clientService.updatesClient(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public VoidVo delete(@RequestParam ClientVo vo) throws Exception {
		clientService.deleteClient(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@ResponseBody
	public VoidVo deletes(@RequestParam ClientVo[] vos) throws Exception {
		clientService.deletesClient(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/queryincludeservicepage")
	@ResponseBody
	public PageVo<ClientVo> queryIncludeServicePage(@RequestParam ClientVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return clientService.queryClientIncludeServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/queryexcludeservicepage")
	@ResponseBody
	public PageVo<ClientVo> queryExcludeServicePage(@RequestParam ClientVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return clientService.queryClientExcludeServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/insertsservice")
	@ResponseBody
	public VoidVo insertsService(@RequestParam ClientVo[] vos) throws Exception {
		clientService.insertsClientService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletesservice")
	@ResponseBody
	public VoidVo deletesService(@RequestParam ClientVo[] vos) throws Exception {
		clientService.deletesClientService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/queryincludegrouppage")
	@ResponseBody
	public PageVo<ClientVo> queryIncludeGroupPage(@RequestParam ClientVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return clientService.queryClientIncludeGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/queryexcludegrouppage")
	@ResponseBody
	public PageVo<ClientVo> queryExcludeGroupPage(@RequestParam ClientVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return clientService.queryClientExcludeGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/insertsgroup")
	@ResponseBody
	public VoidVo insertsGroup(@RequestParam ClientVo[] vos) throws Exception {
		clientService.insertsClientGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletesgroup")
	@ResponseBody
	public VoidVo deletesGroup(@RequestParam ClientVo[] vos) throws Exception {
		clientService.deletesClientGroup(vos);
		return VoidVo.VOID;
	}
}
