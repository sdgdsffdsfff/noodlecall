package org.fl.noodlecall.console.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class MultipleRoutingDataSource extends AbstractRoutingDataSource {
	
	protected Object determineCurrentLookupKey() {
		return DataSourceSwitch.getDataSourceType();
	}
}
