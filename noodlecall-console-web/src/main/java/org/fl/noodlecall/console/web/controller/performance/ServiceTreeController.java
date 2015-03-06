package org.fl.noodlecall.console.web.controller.performance;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import org.fl.noodlecall.console.service.ClientService;
import org.fl.noodlecall.console.service.MethodService;
import org.fl.noodlecall.console.service.ServerService;
import org.fl.noodlecall.console.service.ServiceGroupService;
import org.fl.noodlecall.console.service.ServiceService;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodlecall.console.vo.ClientVo;
import org.fl.noodlecall.console.vo.MethodVo;
import org.fl.noodlecall.console.vo.ServiceGroupVo;
import org.fl.noodlecall.console.vo.ServiceVo;
import org.fl.noodlecall.console.web.mvc.annotation.ResponseBody;
import org.fl.noodlecall.core.connect.expand.monitor.constent.ModuleType;

@Controller
@RequestMapping(value = "monitor/service/tree")
public class ServiceTreeController {
	
	@Autowired
	private ServiceService serviceService;
	
	@Autowired
	private MethodService methodService;
	
	@Autowired
	private ServiceGroupService serviceGroupService;
	
	@Autowired
	private ClientService clientService;
	
	@Autowired
	private ServerService serverService;
	
	@RequestMapping(value = "/querynull")
	@ResponseBody
	public List<TreeVo> queryNull() throws Exception {
		List<TreeVo> treeVoList = new ArrayList<TreeVo>();
		return treeVoList;
	}
	
	@RequestMapping(value = "/queryservicelist")
	@ResponseBody
	public List<TreeVo> queryServiceList(String queryInfo) throws Exception {
		
		List<TreeVo> treeVoList = new ArrayList<TreeVo>();
		
		ServiceVo serviceVo = new ServiceVo();
		serviceVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
		if (queryInfo != null && !queryInfo.equals("")) {
			String[] queryInfos = queryInfo.split("\\.");
			if (queryInfos.length > 0) {
				serviceVo.setService_Name(queryInfos[0]);
			}
		}
		List<ServiceVo> serviceVoList = serviceService.queryServicePage(serviceVo, 0, 0).getData();
		for (ServiceVo serviceVoIt : serviceVoList) {
			TreeVo treeVo = new TreeVo();
			treeVo.setId(serviceVoIt.getService_Name());
			treeVo.setLabel(serviceVoIt.getService_Name());
			treeVo.setPid("SERVICE");
			treeVo.setUrl("monitor/service/tree/querymethodlist");
			treeVoList.add(treeVo);
		}
		
		return treeVoList;
	}
	
	@RequestMapping(value = "/querymethodlist")
	@ResponseBody
	public List<TreeVo> queryMethodList(String queryInfo, String pid) throws Exception {
		
		List<TreeVo> treeVoList = new ArrayList<TreeVo>();
		
		MethodVo methodVo = new MethodVo();
		methodVo.setService_Name(pid);
		methodVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
		if (queryInfo != null && !queryInfo.equals("")) {
			String[] queryInfos = queryInfo.split("\\.");
			if (queryInfos.length > 1) {
				methodVo.setMethod_Name(queryInfos[1]);
			}
		}
		List<MethodVo> methodVoList = methodService.queryMethodPage(methodVo, 0, 0).getData();
		for (MethodVo methodVoIt : methodVoList) {
			TreeVo treeVo = new TreeVo();
			treeVo.setId(methodVoIt.getMethod_Name());
			treeVo.setLabel(methodVoIt.getMethod_Name());
			treeVo.setPid(pid);
			treeVo.setUrl("monitor/service/tree/querygrouplist");
			treeVoList.add(treeVo);
		}
		
		return treeVoList;
	}
	
	@RequestMapping(value = "/querygrouplist")
	@ResponseBody
	public List<TreeVo> queryGroupList(String pid, String ppid) throws Exception {
		
		List<TreeVo> treeVoList = new ArrayList<TreeVo>();
		
		ServiceGroupVo serviceGroupVo = new ServiceGroupVo();
		serviceGroupVo.setService_Name(ppid);
		serviceGroupVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
		List<ServiceGroupVo> serviceGroupVoList = serviceGroupService.queryServiceGroupList(serviceGroupVo);
		for (ServiceGroupVo serviceGroupVoIt : serviceGroupVoList) {
			TreeVo treeVo = new TreeVo();
			treeVo.setId(serviceGroupVoIt.getGroup_Name());
			treeVo.setLabel(serviceGroupVoIt.getGroup_Name());
			treeVo.setPid(pid);
			treeVo.setUrl("monitor/service/tree/queryclientlist");
			treeVoList.add(treeVo);
		}
		
		return treeVoList;
	}
	
	@RequestMapping(value = "/queryclientlist")
	@ResponseBody
	public List<TreeVo> queryClientList(String pid, String pppid) throws Exception {
		
		List<TreeVo> treeVoList = new ArrayList<TreeVo>();
		
		ClientVo clientVo = new ClientVo();
		clientVo.setService_Name(pppid);
		clientVo.setGroup_Name(pid);
		clientVo.setManual_Status(ConsoleConstant.MANUAL_STATUS_YES);
		List<ClientVo> clientVoList = clientService.queryClientList(clientVo);
		for (ClientVo clientVoIt : clientVoList) {
			TreeVo treeVo = new TreeVo();
			treeVo.setId(String.valueOf(clientVoIt.getClient_Id()));
			treeVo.setLabel(clientVoIt.getClient_Name() + "-" + clientVoIt.getIp().substring(clientVoIt.getIp().lastIndexOf(".", clientVoIt.getIp().lastIndexOf(".") - 1) + 1));
			treeVo.setPid(pid);
			treeVo.setOther(ModuleType.CLIENT.getCode());
			treeVo.setUrl("monitor/service/tree/querynull");
			treeVo.setEnableHighlight("true");
			treeVo.setLoad("true");
			treeVoList.add(treeVo);
		}
		
		return treeVoList;
	}
}
