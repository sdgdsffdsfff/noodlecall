package org.fl.noodlecall.console.web.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.fl.noodlecall.console.service.MethodService;
import org.fl.noodlecall.console.util.vo.PageVo;
import org.fl.noodlecall.console.vo.MethodVo;
import org.fl.noodlecall.console.web.mvc.annotation.RequestParam;
import org.fl.noodlecall.console.web.mvc.annotation.ResponseBody;
import org.fl.noodlecall.console.web.mvc.util.VoidVo;

@Controller
@RequestMapping(value = "console/method")
public class MethodController {
	
	@Autowired
	private MethodService methodService;
	
	@RequestMapping(value = "/querypage")
	@ResponseBody
	public PageVo<MethodVo> queryPage(@RequestParam MethodVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return methodService.queryMethodPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@ResponseBody
	public List<MethodVo> queryList(@RequestParam MethodVo vo) throws Exception {
		return methodService.queryMethodList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@ResponseBody
	public VoidVo insert(@RequestParam MethodVo vo) throws Exception {
		methodService.insertMethod(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@ResponseBody
	public VoidVo inserts(@RequestParam MethodVo[] vos) throws Exception {
		methodService.insertsMethod(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@ResponseBody
	public VoidVo update(@RequestParam MethodVo vo) throws Exception {
		methodService.updateMethod(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@ResponseBody
	public VoidVo updates(@RequestParam MethodVo[] vos) throws Exception {
		methodService.updatesMethod(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@ResponseBody
	public VoidVo delete(@RequestParam MethodVo vo) throws Exception {
		methodService.deleteMethod(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@ResponseBody
	public VoidVo deletes(@RequestParam MethodVo[] vos) throws Exception {
		methodService.deletesMethod(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/querybyservicepage")
	@ResponseBody
	public PageVo<MethodVo> queryByServicePage(@RequestParam MethodVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return methodService.queryMethodByServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
}
