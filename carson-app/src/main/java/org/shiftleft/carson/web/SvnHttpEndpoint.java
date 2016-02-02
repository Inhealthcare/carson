package org.shiftleft.carson.web;

import javax.persistence.Embeddable;

@Embeddable
public class SvnHttpEndpoint {

	private String httpScheme;

	private int httpPort;

	private String httpPath;

	public String getHttpScheme() {
		return httpScheme;
	}

	public void setHttpScheme(String httpScheme) {
		this.httpScheme = httpScheme;
	}

	public int getHttpPort() {
		return httpPort;
	}

	public void setHttpPort(int httpPort) {
		this.httpPort = httpPort;
	}

	public String getHttpPath() {
		return httpPath;
	}

	public void setHttpPath(String httpPath) {
		this.httpPath = httpPath;
	}

}
