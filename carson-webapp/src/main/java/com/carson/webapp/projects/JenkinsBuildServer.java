package com.carson.webapp.projects;

import javax.persistence.Embeddable;

@Embeddable
public class JenkinsBuildServer {

	private String url;

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return url;
	}

}
