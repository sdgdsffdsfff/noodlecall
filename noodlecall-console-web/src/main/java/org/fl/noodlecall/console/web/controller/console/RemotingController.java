package org.fl.noodlecall.console.web.controller.console;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.fl.noodle.common.mvc.annotation.NoodleRequestParam;
import org.fl.noodle.common.mvc.annotation.NoodleResponseBody;
import org.fl.noodlecall.console.remoting.ConsoleRemotingResult;
import org.fl.noodlecall.console.service.ClientService;
import org.fl.noodlecall.console.service.ServerService;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodlecall.console.vo.ClientVo;
import org.fl.noodlecall.console.vo.MethodVo;
import org.fl.noodlecall.console.vo.ServerVo;
import org.fl.noodlecall.console.vo.ServiceVo;

@Controller
@RequestMapping(value = "remoting")
public class RemotingController {
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ServerService serverService;
	
	@RequestMapping(value = "/clientregister")
	@NoodleResponseBody(type="json-java")
	public ConsoleRemotingResult clientRegister(@NoodleRequestParam ClientVo vo) {
		try {
			vo.setClient_Id(clientService.updateOrInsertClient(vo));
		} catch (Exception e) {
			return new ConsoleRemotingResult(e.getMessage());
		}
		return new ConsoleRemotingResult(vo);
	}
	
	@RequestMapping(value = "/clientcancel")
	@NoodleResponseBody(type="json-java")
	public ConsoleRemotingResult clientCancel(@NoodleRequestParam ClientVo vo) {
		vo.setSystem_Status(ConsoleConstant.SYSTEM_STATUS_OFFLINE);
		try {
			clientService.updateClient(vo);
		} catch (Exception e) {
			return new ConsoleRemotingResult(e.getMessage());
		}
		return new ConsoleRemotingResult(vo);
	}
	
	@RequestMapping(value = "/serverregister")
	@NoodleResponseBody(type="json-java")
	public ConsoleRemotingResult serverRegister(
			@NoodleRequestParam ServerVo vo, 
			@NoodleRequestParam(name="input-service") ServiceVo serviceVo, 
			@NoodleRequestParam(name="input-method") MethodVo[] methodVos,
			boolean isUpdate
			) {
		try {
			if (isUpdate) {				
				vo.setServer_Id(serverService.updateOrInsertServer(vo, serviceVo, methodVos));
			} else {
				vo.setServer_Id(serverService.updateOrInsertServer(vo, null, null));				
			}
		} catch (Exception e) {
			return new ConsoleRemotingResult(e.getMessage());
		}
		return new ConsoleRemotingResult(vo);
	}
	
	@RequestMapping(value = "/servercancel")
	@NoodleResponseBody(type="json-java")
	public ConsoleRemotingResult serverCancel(@NoodleRequestParam ServerVo vo) {
		vo.setSystem_Status(ConsoleConstant.SYSTEM_STATUS_OFFLINE);
		try {
			serverService.updateServer(vo);
		} catch (Exception e) {
			return new ConsoleRemotingResult(e.getMessage());
		}
		return new ConsoleRemotingResult(vo);
	}
	
	@RequestMapping(value = "/getclientneedinfo")
	@NoodleResponseBody(type="json-java")
	public ConsoleRemotingResult clientGetServerList(@NoodleRequestParam ClientVo[] vos) {
		try {
			return new ConsoleRemotingResult(clientService.queryClientNeedInfo(vos));
		} catch (Exception e) {
			return new ConsoleRemotingResult(e.getMessage());
		}
	}
	
	@RequestMapping(value = "/clientbeat")
	@NoodleResponseBody(type="json-java")
	public ConsoleRemotingResult clientBeat(@NoodleRequestParam ClientVo[] vos) {
		for (ClientVo vo : vos) {			
			vo.setBeat_Time(new Date());
			try {
				clientService.updateClient(vo);
			} catch (Exception e) {
				return new ConsoleRemotingResult(e.getMessage());
			}
		}
		return new ConsoleRemotingResult();
	}
	
	@RequestMapping(value = "/serverbeat")
	@NoodleResponseBody(type="json-java")
	public ConsoleRemotingResult serverBeat(@NoodleRequestParam ServerVo[] vos) {
		for (ServerVo vo : vos) {			
			vo.setBeat_Time(new Date());
			try {
				serverService.updateServer(vo);
			} catch (Exception e) {
				return new ConsoleRemotingResult(e.getMessage());
			}
		}
		return new ConsoleRemotingResult();
	}
}
