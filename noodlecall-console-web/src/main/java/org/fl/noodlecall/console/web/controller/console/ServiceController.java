package org.fl.noodlecall.console.web.controller.console;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.fl.noodle.common.mvc.annotation.NoodleRequestParam;
import org.fl.noodle.common.mvc.annotation.NoodleResponseBody;
import org.fl.noodle.common.mvc.vo.MapVo;
import org.fl.noodle.common.mvc.vo.VoidVo;
import org.fl.noodlecall.console.service.ServiceService;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.ServiceVo;

@Controller
@RequestMapping(value = "console/service")
public class ServiceController {
	
	@Autowired
	private ServiceService serviceService;
	
	@RequestMapping(value = "/querypage")
	@NoodleResponseBody
	public PageVo<ServiceVo> queryPage(@NoodleRequestParam ServiceVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serviceService.queryServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@NoodleResponseBody
	public List<ServiceVo> queryList(@NoodleRequestParam ServiceVo vo) throws Exception {
		return serviceService.queryServiceList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@NoodleResponseBody
	public VoidVo insert(@NoodleRequestParam ServiceVo vo) throws Exception {
		serviceService.insertService(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@NoodleResponseBody
	public VoidVo inserts(@NoodleRequestParam ServiceVo[] vos) throws Exception {
		serviceService.insertsService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@NoodleResponseBody
	public VoidVo update(@NoodleRequestParam ServiceVo vo) throws Exception {
		serviceService.updateService(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@NoodleResponseBody
	public VoidVo updates(@NoodleRequestParam ServiceVo[] vos) throws Exception {
		serviceService.updatesService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@NoodleResponseBody
	public VoidVo delete(@NoodleRequestParam ServiceVo vo) throws Exception {
		serviceService.deleteService(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@NoodleResponseBody
	public VoidVo deletes(@NoodleRequestParam ServiceVo[] vos) throws Exception {
		serviceService.deletesService(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/querygraph")
	@NoodleResponseBody
	public MapVo<String, Map<String, List<?>>> queryGraph(@NoodleRequestParam ServiceVo vo) throws Exception {
		return new MapVo<String, Map<String, List<?>>>(serviceService.queryGraph(vo));
	}
}
