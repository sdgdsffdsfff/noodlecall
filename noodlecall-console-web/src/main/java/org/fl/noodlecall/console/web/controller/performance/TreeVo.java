package org.fl.noodlecall.console.web.controller.performance;

public class TreeVo {

	private String 	id;
	private String	label;
	private String 	url;
	private String 	pid;
	private String 	other;
	private String 	enableHighlight;
	private String 	load;

	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getEnableHighlight() {
		return enableHighlight;
	}

	public void setEnableHighlight(String enableHighlight) {
		this.enableHighlight = enableHighlight;
	}

	public String getLoad() {
		return load;
	}

	public void setLoad(String load) {
		this.load = load;
	}
}
