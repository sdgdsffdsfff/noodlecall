package org.fl.noodlecall.console.web.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.fl.noodlecall.console.service.ServiceGroupService;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.ServiceGroupVo;
import org.fl.noodlecall.console.web.mvc.annotation.RequestParam;
import org.fl.noodlecall.console.web.mvc.annotation.ResponseBody;
import org.fl.noodlecall.console.web.mvc.util.VoidVo;

@Controller
@RequestMapping(value = "console/service/group")
public class ServiceGroupController {
	
	@Autowired
	private ServiceGroupService serviceGroupService;
	
	@RequestMapping(value = "/querypage")
	@ResponseBody
	public PageVo<ServiceGroupVo> queryPage(@RequestParam ServiceGroupVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serviceGroupService.queryServiceGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@ResponseBody
	public List<ServiceGroupVo> queryList(@RequestParam ServiceGroupVo vo) throws Exception {
		return serviceGroupService.queryServiceGroupList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@ResponseBody
	public VoidVo insert(@RequestParam ServiceGroupVo vo) throws Exception {
		serviceGroupService.insertServiceGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@ResponseBody
	public VoidVo inserts(@RequestParam ServiceGroupVo[] vos) throws Exception {
		serviceGroupService.insertsServiceGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public VoidVo update(@RequestParam ServiceGroupVo vo) throws Exception {
		serviceGroupService.updateServiceGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@ResponseBody
	public VoidVo updates(@RequestParam ServiceGroupVo[] vos) throws Exception {
		serviceGroupService.updatesServiceGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public VoidVo delete(@RequestParam ServiceGroupVo vo) throws Exception {
		serviceGroupService.deleteServiceGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@ResponseBody
	public VoidVo deletes(@RequestParam ServiceGroupVo[] vos) throws Exception {
		serviceGroupService.deletesServiceGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/queryincludepage")
	@ResponseBody
	public PageVo<ServiceGroupVo> queryIncludePage(@RequestParam ServiceGroupVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serviceGroupService.queryServiceGroupIncludePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/queryexcludepage")
	@ResponseBody
	public PageVo<ServiceGroupVo> queryExcludePage(@RequestParam ServiceGroupVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serviceGroupService.queryServiceGroupExcludePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
}
