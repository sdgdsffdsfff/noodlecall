package org.fl.noodlecall.monitor.status.console.executer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.fl.noodle.common.net.http.HttpConnect;
import org.fl.noodle.common.net.http.jdk.HttpConnectJdk;
import org.fl.noodlecall.console.service.ServerService;
import org.fl.noodlecall.console.util.ConsoleConstant;
import org.fl.noodlecall.console.vo.ServerVo;
import org.fl.noodlecall.monitor.api.schedule.executer.AbstractExecuter;
import org.fl.noodlecall.util.tools.SocketConnect;

public class ConsoleServerStatusExecuter extends AbstractExecuter {

	private final static Logger logger = LoggerFactory.getLogger(ConsoleServerStatusExecuter.class);
	
	@Autowired
	private ServerService serverService;
	
	private long maxInterval = 10 * 1000;
	private int maxRetry = 3;
	private int connectTimeout = 1000;
	private int readTimeout = 1000;
	
	private String healthName = "health";
	private String healthUrl = "/check/health";
	
	@Override
	public void execute() throws Exception {
		
		ServerVo serverVo = new ServerVo();
		serverVo.setBeat_Time(new Date(((new Date()).getTime() - maxInterval)));
		
		serverVo.setSystem_Status(ConsoleConstant.SYSTEM_STATUS_OFFLINE);
		List<ServerVo> serverVoOnlineList = null;
		try {
			serverVoOnlineList = serverService.queryServerOnlineList(serverVo);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("execute -> serverService.queryServerOnlineLis -> {} -> Exception:{}", serverVo, e.getMessage());
			}
		}
		if (serverVoOnlineList != null) {
			for (ServerVo serverVoOnline : serverVoOnlineList) {
				boolean isHealth = false;
				if (serverVoOnline.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_NETTY)) {
					SocketConnect socketConnect = new SocketConnect(serverVoOnline.getIp(), serverVoOnline.getPort(), connectTimeout, readTimeout);
					for (int i=0; i<maxRetry; i++) {					
						try {						
							socketConnect.send(healthName, serverVoOnline.getService_Name());
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("execute -> socketConnect.send -> {} -> Exception:{}", serverVoOnline, e.getMessage());
							}
							continue;
						}
						isHealth = true;
						break;
					}
				} else if (serverVoOnline.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_JETTY) 
							|| serverVoOnline.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_SERVLET)) {
					String fullUrl = new StringBuilder("http://").append(serverVoOnline.getIp()).append(":").append(serverVoOnline.getPort()).append(healthUrl).toString();
					HttpConnect httpConnect = new HttpConnectJdk(fullUrl, connectTimeout, readTimeout);
					for (int i=0; i<maxRetry; i++) {					
						try {						
							httpConnect.getString(healthName, serverVoOnline.getUrl());
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("execute -> httpConnect.send -> {} -> Exception:{}", serverVoOnline, e.getMessage());
							}
							continue;
						}
						isHealth = true;
						break;
					}
				}
				
				if (isHealth) {
					ServerVo serverVoUpdate = new ServerVo();
					serverVoUpdate.setServer_Id(serverVoOnline.getServer_Id());
					serverVoUpdate.setSystem_Status(ConsoleConstant.SYSTEM_STATUS_ONLINE);
					serverService.updateServer(serverVoUpdate);
					if (logger.isDebugEnabled()) {			
						logger.debug("execute -> serverService.updateServer to online -> {}", serverVoUpdate);
					}
				}
			}
		}
		
		serverVo.setSystem_Status(ConsoleConstant.SYSTEM_STATUS_ONLINE);
		List<ServerVo> serverVoOfflineList = null;
		try {
			serverVoOfflineList = serverService.queryServerOfflineList(serverVo);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("execute -> serverService.queryServerOnlineLis -> {} -> Exception:{}", serverVo, e.getMessage());
			}
		}
		if (serverVoOfflineList != null) {
			for (ServerVo serverVoOffline : serverVoOfflineList) {
				boolean isHealth = false;
				if (serverVoOffline.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_NETTY)) {
					SocketConnect socketConnect = new SocketConnect(serverVoOffline.getIp(), serverVoOffline.getPort(), connectTimeout, readTimeout);
					for (int i=0; i<maxRetry; i++) {					
						try {						
							socketConnect.send(healthName, "hi");
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("execute -> socketConnect.send -> {} -> Exception:{}", serverVoOffline, e.getMessage());
							}
							continue;
						}
						isHealth = true;
						break;
					}
				} else if (serverVoOffline.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_JETTY) 
							|| serverVoOffline.getServer_Type().equals(ConsoleConstant.SERVER_TYPE_SERVLET)) {
					String fullUrl = new StringBuilder("http://").append(serverVoOffline.getIp()).append(":").append(serverVoOffline.getPort()).append(healthUrl).toString();
					HttpConnect httpConnect = new HttpConnectJdk(fullUrl, connectTimeout, readTimeout);
					for (int i=0; i<maxRetry; i++) {					
						try {						
							httpConnect.getString("input", "hi");
						} catch (Exception e) {
							if (logger.isErrorEnabled()) {
								logger.error("execute -> httpConnect.send -> {} -> Exception:{}", serverVoOffline, e.getMessage());
							}
							continue;
						}
						isHealth = true;
						break;
					}
				}
				if (!isHealth) {
					ServerVo serverVoUpdate = new ServerVo();
					serverVoUpdate.setServer_Id(serverVoOffline.getServer_Id());
					serverVoUpdate.setSystem_Status(ConsoleConstant.SYSTEM_STATUS_OFFLINE);
					serverService.updateServer(serverVoUpdate);
					if (logger.isDebugEnabled()) {			
						logger.debug("execute -> serverService.updateServer to offline -> {}", serverVoUpdate);
					}
				}
			}
		}
	}

	public void setMaxInterval(long maxInterval) {
		this.maxInterval = maxInterval;
	}

	public void setMaxRetry(int maxRetry) {
		this.maxRetry = maxRetry;
	}

	public void setConnectTimeout(int connectTimeout) {
		this.connectTimeout = connectTimeout;
	}

	public void setReadTimeout(int readTimeout) {
		this.readTimeout = readTimeout;
	}
	
	public void setHealthName(String healthName) {
		this.healthName = healthName;
	}

	public void setHealthUrl(String healthUrl) {
		this.healthUrl = healthUrl;
	}
}
