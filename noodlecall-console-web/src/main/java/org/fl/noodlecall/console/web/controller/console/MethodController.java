package org.fl.noodlecall.console.web.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.fl.noodle.common.mvc.annotation.NoodleRequestParam;
import org.fl.noodle.common.mvc.annotation.NoodleResponseBody;
import org.fl.noodle.common.mvc.vo.VoidVo;
import org.fl.noodlecall.console.service.MethodService;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.MethodVo;

@Controller
@RequestMapping(value = "console/method")
public class MethodController {
	
	@Autowired
	private MethodService methodService;
	
	@RequestMapping(value = "/querypage")
	@NoodleResponseBody
	public PageVo<MethodVo> queryPage(@NoodleRequestParam MethodVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return methodService.queryMethodPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@NoodleResponseBody
	public List<MethodVo> queryList(@NoodleRequestParam MethodVo vo) throws Exception {
		return methodService.queryMethodList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@NoodleResponseBody
	public VoidVo insert(@NoodleRequestParam MethodVo vo) throws Exception {
		methodService.insertMethod(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@NoodleResponseBody
	public VoidVo inserts(@NoodleRequestParam MethodVo[] vos) throws Exception {
		methodService.insertsMethod(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@NoodleResponseBody
	public VoidVo update(@NoodleRequestParam MethodVo vo) throws Exception {
		methodService.updateMethod(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@NoodleResponseBody
	public VoidVo updates(@NoodleRequestParam MethodVo[] vos) throws Exception {
		methodService.updatesMethod(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@NoodleResponseBody
	public VoidVo delete(@NoodleRequestParam MethodVo vo) throws Exception {
		methodService.deleteMethod(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@NoodleResponseBody
	public VoidVo deletes(@NoodleRequestParam MethodVo[] vos) throws Exception {
		methodService.deletesMethod(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/querybyservicepage")
	@NoodleResponseBody
	public PageVo<MethodVo> queryByServicePage(@NoodleRequestParam MethodVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return methodService.queryMethodByServicePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
}
