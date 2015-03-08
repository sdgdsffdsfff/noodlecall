package org.fl.noodlecall.monitor.performance.schedule.executer;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.fl.noodle.common.util.json.JsonTranslator;
import org.fl.noodlecall.core.connect.expand.monitor.PerformanceMonitor;
import org.fl.noodlecall.core.connect.expand.monitor.constent.ModuleType;
import org.fl.noodlecall.core.connect.register.ClientModuleRegister;
import org.fl.noodlecall.monitor.api.schedule.executer.AbstractExecuter;
import org.fl.noodlecall.monitor.performance.net.UdpClient;
import org.fl.noodlecall.monitor.performance.note.Note;
import org.fl.noodlecall.monitor.performance.storage.MemoryStorage;
import org.fl.noodlecall.monitor.performance.storage.ThreadLocalStorage;
import org.fl.noodlecall.monitor.performance.vo.InfoVo;
import org.fl.noodlecall.monitor.performance.vo.KeyVo;
import org.fl.noodlecall.monitor.performance.vo.NetVo;

public class PerformanceExecuter extends AbstractExecuter implements PerformanceMonitor {

	private final static Logger logger = LoggerFactory.getLogger(PerformanceExecuter.class);
	
	private ClientModuleRegister clientModuleRegister;

	private UdpClient udpClient;
	
	private long noteTimeout = 180000;
	
	@Override
	public void before(String themeName, String monitorType, String moduleType, long moduleId) {
		
		ThreadPerformanceInfo threadPerformanceInfo = null;
		
		try {
			threadPerformanceInfo = ThreadLocalStorage.get(themeName, monitorType, moduleType, moduleId, ThreadPerformanceInfo.class);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("before -> ThreadLocalStorage.get -> themeName:{}, monitorType:{}, moduleType:{}, moduleId{}", themeName, monitorType, moduleType, moduleId);
			}
		}
		
		if (threadPerformanceInfo != null) {
			threadPerformanceInfo.startTime = System.currentTimeMillis();
		}
	}

	@Override
	public void after(String moduleName, String themeName, String monitorType, String moduleType, long moduleId, long threshold, boolean result) {
		
		long elapseTime = 0;
		
		ThreadPerformanceInfo threadPerformanceInfo = null;
		
		try {
			threadPerformanceInfo = ThreadLocalStorage.get(themeName, monitorType, moduleType, moduleId, ThreadPerformanceInfo.class);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("after -> ThreadLocalStorage.get -> themeName:{}, monitorType:{}, moduleType:{}, moduleId{} -> Exception:{}", themeName, monitorType, moduleType, moduleId, e.getMessage());
			}
		}
		
		if (threadPerformanceInfo != null) {
			elapseTime = System.currentTimeMillis() - threadPerformanceInfo.startTime;
		}
		
		Note note = null;
		
		try {
			note = MemoryStorage.get(themeName, monitorType, moduleType, moduleId, Note.class);
		} catch (Exception e) {
			if (logger.isErrorEnabled()) {
				logger.error("after -> MemoryStorage.get -> themeName:{}, monitorType:{}, moduleType:{}, moduleId{} -> Exception:{}", themeName, monitorType, moduleType, moduleId, e.getMessage());
			}
		}
		
		if (note != null) {
			note.totalCountAdd();
			note.overtimeThresholdSet(threshold);
			if (result) {
				note.successCountAdd();
				if (elapseTime > threshold) {
					note.overtimeCountAdd();
		        }
				note.totalTimeAdd(elapseTime);
			}
			note.setTimestamp((new Date()).getTime());
			note.setModuleName(moduleName);
		}
	}

	@Override
	public void execute() {
		
		InfoVo infoVo = new InfoVo();
		NetVo netVo = new NetVo();
		
		List<KeyVo> keyVoList = MemoryStorage.getKeys();
		for (KeyVo keyVo : keyVoList) {
			
			Note note = null;
			
			try {
				note = MemoryStorage.get(keyVo.getThemeName(), keyVo.getMonitorType(), keyVo.getModuleType(), keyVo.getModuleId(), Note.class);
			} catch (Exception e) {
				if (logger.isErrorEnabled()) {
					logger.error("execute -> MemoryStorage.get -> themeName:{}, monitorType:{}, moduleType:{}, moduleId{} -> Exception:{}", keyVo.getThemeName(), keyVo.getMonitorType(), keyVo.getModuleType(), keyVo.getModuleId(), e.getMessage());
				}
			}
			
			if (note != null && (new Date()).getTime() - note.getTimestamp() < noteTimeout) {
				
				long totalCount = note.totalCountReset();
				long overtimeCount = note.overtimeCountReset();
				long totalTime = note.totalTimeReset();
				long overtimeThreshold = note.overtimeThresholdReset();
				long successCount = note.successCountReset();
				
				infoVo.setTotalCount(totalCount);
				infoVo.setOvertimeCount(overtimeCount);
				
				if (totalCount > 0 && successCount > 0) {
					infoVo.setAverageTime(totalTime / successCount);
				} else {
					infoVo.setAverageTime(0);
				}
				
				infoVo.setThreshold(overtimeThreshold);
				infoVo.setSuccessCount(successCount);
				infoVo.setTimestamp(System.currentTimeMillis());
				
				netVo.setKeyVo(keyVo);
				netVo.setInfoVo(infoVo);
				
				if (clientModuleRegister != null) {					
					keyVo.setSelfModuleType(ModuleType.CLIENT.getCode());
					keyVo.setSelfModuleId(clientModuleRegister.getModuleId(note.getModuleName()));
				}
				
				try {
					udpClient.send(JsonTranslator.toString(netVo));
				} catch (Exception e) {
					if (logger.isErrorEnabled()) {
						logger.error("execute -> udpClient.send -> themeName:{}, monitorType:{}, moduleType:{}, moduleId{} -> Exception:{}", keyVo.getThemeName(), keyVo.getMonitorType(), keyVo.getModuleType(), keyVo.getModuleId(), e.getMessage());
					}
				}
			}
		}
	}
	
	public void setClientModuleRegister(ClientModuleRegister clientModuleRegister) {
		this.clientModuleRegister = clientModuleRegister;
	}

	public void setUdpClient(UdpClient udpClient) {
		this.udpClient = udpClient;
	}

	public void setNoteTimeout(long noteTimeout) {
		this.noteTimeout = noteTimeout;
	}

	public static class ThreadPerformanceInfo {
		public long startTime;
	}
}
