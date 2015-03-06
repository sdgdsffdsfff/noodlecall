package org.fl.noodlecall.console.aop.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.fl.noodlecall.console.datasource.DataSourceSwitch;
import org.fl.noodlecall.console.datasource.DataSourceType;
import org.fl.noodlecall.console.loadbalancer.LoadBalancerManager;

public class FailoverMethodInterceptor implements MethodInterceptor {

	private final static Logger logger = LoggerFactory.getLogger(FailoverMethodInterceptor.class);
	
	private LoadBalancerManager loadBalancerManager;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		
		if (loadBalancerManager.checkIsAliveDataSource(DataSourceType.MASTER)) {
			
			try {
				DataSourceSwitch.setDataSourceType(DataSourceType.MASTER);
				return invocation.proceed();
			} catch (Throwable e) {
				if (logger.isErrorEnabled()) {
					logger.error("invoke -> MASTER invoke -> DataAccessException: " + e);
				}
				throw e;
			}
		} else {
			
			try {
				DataSourceSwitch.setDataSourceType(DataSourceType.SALVE);
				return invocation.proceed();
			} catch (Throwable e) {
				if (logger.isErrorEnabled()) {
					logger.error("invoke -> SALVE invoke -> DataAccessException: " + e);
				}
				throw e;
			}
		}
	}
	
	public void setLoadBalancerManager(LoadBalancerManager loadBalancerManager) {
		this.loadBalancerManager = loadBalancerManager;
	}
}
