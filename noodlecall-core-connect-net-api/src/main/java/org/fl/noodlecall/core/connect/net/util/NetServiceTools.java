package org.fl.noodlecall.core.connect.net.util;

import java.lang.reflect.Method;

public final class NetServiceTools {
	
	public static String getInvokerKey(String serviceName, Method method) {
		
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append(serviceName);
		stringBuilder.append(".");
		stringBuilder.append(method.getName());
		
		stringBuilder.append("(");
		
		Class<?>[] parameters = method.getParameterTypes();
		
		int i = 0;
		for (Class<?> parameter : parameters) {
			stringBuilder.append(parameter.getSimpleName());
			if (++i < parameters.length) {
				stringBuilder.append(",");
			}
		}
		stringBuilder.append(")");
		
		return stringBuilder.toString();
	}
}
