package org.shiftleft.carson.web;

public class SvnRepository extends AbstractEntity {

	private String host;

	private SvnHttpEndpoint httpEndpoint;

	private String username;

	private String password;

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public SvnHttpEndpoint getHttpEndpoint() {
		return httpEndpoint;
	}

	public void setHttpEndpoint(SvnHttpEndpoint httpEndpoint) {
		this.httpEndpoint = httpEndpoint;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
