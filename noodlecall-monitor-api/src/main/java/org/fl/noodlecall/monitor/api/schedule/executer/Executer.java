package org.fl.noodlecall.monitor.api.schedule.executer;

public interface Executer {
	public void execute() throws Exception;
	public long getInitialDelay();
	public long getDelay();
}
