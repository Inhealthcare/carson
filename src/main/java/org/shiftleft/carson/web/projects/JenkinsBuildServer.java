package org.shiftleft.carson.web.projects;

import javax.persistence.Embeddable;

@Embeddable
public class JenkinsBuildServer {

	private String url;
	private String username;
	private String password;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getUsername() {
		return username;
	}

}
