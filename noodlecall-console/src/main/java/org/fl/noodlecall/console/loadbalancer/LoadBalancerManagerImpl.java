package org.fl.noodlecall.console.loadbalancer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.CopyOnWriteArrayList;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoadBalancerManagerImpl implements LoadBalancerManager {
	
	private final static Logger logger = LoggerFactory.getLogger(LoadBalancerManagerImpl.class);
		
	private CopyOnWriteArrayList<DataSourceModel> dataSourcesList = new CopyOnWriteArrayList<DataSourceModel>();
	private CopyOnWriteArrayList<DataSourceModel> deadSourcesList = new CopyOnWriteArrayList<DataSourceModel>();

	private int totalFailureCount = 3;
	private int totalRiseCount = 2;
	private long interTime = 2000;
	private String detectingSql = "SELECT CURRENT_TIMESTAMP FROM DUAL";
	
	private Map<String, DataSource> initDataSourceMap;

	public void start() {

		for (Map.Entry<String, DataSource> entry : initDataSourceMap.entrySet()) {
			DataSourceModel dataSourceModel = new DataSourceModel();
			dataSourceModel.setDataSource(entry.getValue());
			dataSourceModel.setDataSourceType(entry.getKey());
			dataSourcesList.add(dataSourceModel);
		}
		
		Thread dataSourceBeatAlive = new Thread(new HeartBeatAliveTasksScan());
		dataSourceBeatAlive.setDaemon(true);
		dataSourceBeatAlive.setName("dataSourceHeartBeatAlive");
		dataSourceBeatAlive.start();

		Thread dataSourceDeadHeartBeat = new Thread(new HeartBeatDeadTasksScan());
		dataSourceDeadHeartBeat.setDaemon(true);
		dataSourceDeadHeartBeat.setName("dataSourceDeadHeartBeat");
		dataSourceDeadHeartBeat.start();
		
		if (logger.isInfoEnabled()) {
			logger.info("LoadBalancerManager -> start");								
		}
	}
	
	class HeartBeatAliveTasksScan implements Runnable {

		private final Logger logger = LoggerFactory.getLogger(HeartBeatAliveTasksScan.class);
				
		public void run() {
			
			while (true) {
				
				for (DataSourceModel dataSourceModel : dataSourcesList) {
					boolean status = DataSourceCheckAlive.CheckAliveDataSource(dataSourceModel.getDataSource(), detectingSql);
					AtomicInteger failureCount = dataSourceModel.getFailureCount();
					if (!status) {
						if (failureCount.incrementAndGet() > totalFailureCount) {
							dataSourcesList.remove(dataSourceModel);
							failureCount.set(0);
							deadSourcesList.add(dataSourceModel);
							if (logger.isInfoEnabled()) {
								logger.info("HeartBeatAliveTasksScan -> run -> DataSource dead and remove dataSourcesMap, DataSource: " + dataSourceModel.getDataSourceType());								
							}
						} 
					} else {
						failureCount.set(0);
					}
				}

				try {
					Thread.sleep(interTime);
				} catch (InterruptedException e) {
					if (logger.isErrorEnabled()) {						
						logger.error("HeartBeatAliveTasksScan -> run -> HeartBeatAliveTasksScan InterruptedException, Exception: " + e);
					}
				}
			}
		}
	}

	class HeartBeatDeadTasksScan implements Runnable {

		private final Logger logger = LoggerFactory.getLogger(HeartBeatDeadTasksScan.class);
		
		public void run() {
			
			while (true) {
				
				for (DataSourceModel dataSourceModel : deadSourcesList) {
					boolean status = DataSourceCheckAlive.CheckAliveDataSource(dataSourceModel.getDataSource(), detectingSql);
					AtomicInteger riseCount = dataSourceModel.getRiseCount();
					if (status) {
						if (riseCount.incrementAndGet() >= totalRiseCount) {
							deadSourcesList.remove(dataSourceModel);
							riseCount.set(0);
							dataSourcesList.add(dataSourceModel);
							if (logger.isInfoEnabled()) {
								logger.info("HeartBeatAliveTasksScan -> run -> DataSource alive and remove deadSourcesMap, DataSource: " + dataSourceModel.getDataSourceType());								
							}
						}
					} else {
						riseCount.set(0);
					}
				}
				
				try {
					Thread.sleep(interTime);
				} catch (InterruptedException e) {
					if (logger.isErrorEnabled()) {						
						logger.error("HeartBeatAliveTasksScan -> run -> HeartBeatDeadTasksScan InterruptedException, Exception: " + e);
					}
				}
			}
		}
	}

	public void setTotalFailureCount(int totalFailureCount) {
		this.totalFailureCount = totalFailureCount;
	}

	public void setTotalRiseCount(int totalRiseCount) {
		this.totalRiseCount = totalRiseCount;
	}

	public void setDetectingSql(String detectingSql) {
		this.detectingSql = detectingSql;
	}

	public void setInterTime(long interTime) {
		this.interTime = interTime;
	}

	public void setInitDataSourceMap(Map<String, DataSource> initDataSourceMap) {
		this.initDataSourceMap = initDataSourceMap;
	}
	
	public static class DataSourceCheckAlive {
		
		private final static Logger logger = LoggerFactory.getLogger(DataSourceCheckAlive.class); 

		public static boolean CheckAliveDataSource(DataSource dataSource, String detectingSql){
			
			boolean result = false;
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				conn = dataSource.getConnection();
				if (detectingSql == null || detectingSql.equalsIgnoreCase("")) {
					rs = conn.getMetaData().getTables(null, null, "PROBABLYNOT", new String[] { "TABLE" });
				} else {
					pstmt = conn.prepareStatement(detectingSql);
					pstmt.execute();
				}
				result = true;
			} catch (SQLException e) {
				if (logger.isErrorEnabled()) {
					logger.error("CheckAliveDataSource -> Database connection error, SQLState: " + e.getSQLState() + ", Exception: " + e);
				}
			} finally {
				if (rs != null) {
					try {
						rs.close();
					} catch (SQLException e) {
						if (logger.isErrorEnabled()) {
							logger.error("CheckAliveDataSource -> Database rs close error, SQLState: " + e.getSQLState() + ", Exception: " + e);
						}
					}
				}

				if (pstmt != null) {
					try {
						pstmt.close();
					} catch (SQLException e) {
						if (logger.isErrorEnabled()) {
							logger.error("CheckAliveDataSource -> preparedStatement close error, SQLState: " + e.getSQLState() + ", Exception: " + e);
						}
					}
				}

				if (conn != null) {
					try {
						conn.close();
					} catch (SQLException e) {
						if (logger.isErrorEnabled()) {
							logger.error("CheckAliveDataSource -> connection close error, SQLState: " + e.getSQLState() + ", Exception: " + e);
						}
					}
				}
			}

			return result;
		}
	}

	@Override
	public boolean checkIsAliveDataSource(String dataSourceType) {
		for (DataSourceModel dataSourceModel : dataSourcesList) {
			if (dataSourceModel.getDataSourceType().equals(dataSourceType)) {
				return true;
			}
		}
		return false;
	}
}
