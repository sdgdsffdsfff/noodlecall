package org.fl.noodlecall.console.util;

public final class ConsoleConstant {
	
	public static final Integer MANUAL_STATUS_YES = 1;
	public static final Integer MANUAL_STATUS_NO = 2;
	
	public static final Integer SYSTEM_STATUS_ONLINE = 1;
	public static final Integer SYSTEM_STATUS_OFFLINE = 2;
	
	public static final String SERVER_TYPE_JETTY = "JETTY";
	public static final String SERVER_TYPE_SERVLET = "SERVLET";
	public static final String SERVER_TYPE_NETTY = "NETTY";
	
	public static final String SERIALIZE_TYPE_JSON = "JSON";
	public static final String SERIALIZE_TYPE_HESSIAN = "HESSIAN";
	
	public static final String CLUSTER_TYPE_FAILOVER = "FAILOVER";
	public static final String CLUSTER_TYPE_ONCE = "ONCE";
	public static final String CLUSTER_TYPE_ALL = "ALL";
	
	public static final String ROUTE_TYPE_RANDOM = "RANDOM";
	public static final String ROUTE_TYPE_WEIGHT = "WEIGHT";
	public static final String ROUTE_TYPE_RESPONSE = "RESPONSE";
	
	public static final Integer IS_DOWNGRADE_YES = 1;
	public static final Integer IS_DOWNGRADE_NO = 2;
	
	public static final Integer DOWNGRADE_TYPE_AVGTIME = 1;
	public static final Integer DOWNGRADE_TYPE_OVERTIME = 2;
	
	public static final Integer RETURN_TYPE_T_EXCEPTION = 1;
	public static final Integer RETURN_TYPE_R_NULL = 2;
}
