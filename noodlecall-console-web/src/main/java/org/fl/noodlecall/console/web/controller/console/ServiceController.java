package org.fl.noodlecall.console.web.controller.console;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.fl.noodlecall.console.service.ServiceService;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.ServiceVo;
import org.fl.noodlecall.console.web.mvc.annotation.RequestParam;
import org.fl.noodlecall.console.web.mvc.annotation.ResponseBody;
import org.fl.noodlecall.console.web.mvc.util.MapVo;
import org.fl.noodlecall.console.web.mvc.util.VoidVo;

@Controller
@RequestMapping(value = "console/service")
public class ServiceController {
	
	@Autowired
	private ServiceService serviceService;
	
	@RequestMapping(value = "/querypage")
	@ResponseBody
	public PageVo<ServiceVo> queryPage(@RequestParam ServiceVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serviceService.queryServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@ResponseBody
	public List<ServiceVo> queryList(@RequestParam ServiceVo vo) throws Exception {
		return serviceService.queryServiceList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@ResponseBody
	public VoidVo insert(@RequestParam ServiceVo vo) throws Exception {
		serviceService.insertService(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@ResponseBody
	public VoidVo inserts(@RequestParam ServiceVo[] vos) throws Exception {
		serviceService.insertsService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public VoidVo update(@RequestParam ServiceVo vo) throws Exception {
		serviceService.updateService(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@ResponseBody
	public VoidVo updates(@RequestParam ServiceVo[] vos) throws Exception {
		serviceService.updatesService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public VoidVo delete(@RequestParam ServiceVo vo) throws Exception {
		serviceService.deleteService(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@ResponseBody
	public VoidVo deletes(@RequestParam ServiceVo[] vos) throws Exception {
		serviceService.deletesService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/querygraph")
	@ResponseBody
	public MapVo<String, Map<String, List<?>>> queryGraph(@RequestParam ServiceVo vo) throws Exception {
		return new MapVo<String, Map<String, List<?>>>(serviceService.queryGraph(vo));
	}
}
