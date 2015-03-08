package org.fl.noodlecall.console.web.controller.console;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.fl.noodle.common.mvc.annotation.NoodleRequestParam;
import org.fl.noodle.common.mvc.annotation.NoodleResponseBody;
import org.fl.noodle.common.mvc.vo.VoidVo;
import org.fl.noodlecall.console.service.ServiceGroupService;
import org.fl.noodle.common.mvc.vo.PageVo;
import org.fl.noodlecall.console.vo.ServiceGroupVo;

@Controller
@RequestMapping(value = "console/service/group")
public class ServiceGroupController {
	
	@Autowired
	private ServiceGroupService serviceGroupService;
	
	@RequestMapping(value = "/querypage")
	@NoodleResponseBody
	public PageVo<ServiceGroupVo> queryPage(@NoodleRequestParam ServiceGroupVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serviceGroupService.queryServiceGroupPage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/querylist")
	@NoodleResponseBody
	public List<ServiceGroupVo> queryList(@NoodleRequestParam ServiceGroupVo vo) throws Exception {
		return serviceGroupService.queryServiceGroupList(vo);
	}
	
	@RequestMapping(value = "/insert")
	@NoodleResponseBody
	public VoidVo insert(@NoodleRequestParam ServiceGroupVo vo) throws Exception {
		serviceGroupService.insertServiceGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/inserts")
	@NoodleResponseBody
	public VoidVo inserts(@NoodleRequestParam ServiceGroupVo[] vos) throws Exception {
		serviceGroupService.insertsServiceGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/update")
	@NoodleResponseBody
	public VoidVo update(@NoodleRequestParam ServiceGroupVo vo) throws Exception {
		serviceGroupService.updateServiceGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/updates")
	@NoodleResponseBody
	public VoidVo updates(@NoodleRequestParam ServiceGroupVo[] vos) throws Exception {
		serviceGroupService.updatesServiceGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/delete")
	@NoodleResponseBody
	public VoidVo delete(@NoodleRequestParam ServiceGroupVo vo) throws Exception {
		serviceGroupService.deleteServiceGroup(vo);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/deletes")
	@NoodleResponseBody
	public VoidVo deletes(@NoodleRequestParam ServiceGroupVo[] vos) throws Exception {
		serviceGroupService.deletesServiceGroup(vos);
		return VoidVo.VOID;
	}
	
	@RequestMapping(value = "/queryincludepage")
	@NoodleResponseBody
	public PageVo<ServiceGroupVo> queryIncludePage(@NoodleRequestParam ServiceGroupVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serviceGroupService.queryServiceGroupIncludePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
	
	@RequestMapping(value = "/queryexcludepage")
	@NoodleResponseBody
	public PageVo<ServiceGroupVo> queryExcludePage(@NoodleRequestParam ServiceGroupVo vo, String page, String rows) throws Exception {
		page = page != null && !page.equals("") ? page : "0";
		rows = rows != null && !rows.equals("") ? rows : "0";
		return serviceGroupService.queryServiceGroupExcludePage(vo, Integer.parseInt(page), Integer.parseInt(rows));
	}
}
