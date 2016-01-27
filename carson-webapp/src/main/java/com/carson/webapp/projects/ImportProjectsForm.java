package com.carson.webapp.projects;

import org.hibernate.validator.constraints.NotBlank;

public class ImportProjectsForm {

	@NotBlank
	private String scmUrl;

	private String scmUsername;

	private String scmPassword;

	@NotBlank
	private String buildUrl;

	private String buildUsername;

	private String buildPassword;

	public String getScmUrl() {
		return scmUrl;
	}

	public void setScmUrl(String scmUrl) {
		this.scmUrl = scmUrl;
	}

	public String getScmUsername() {
		return scmUsername;
	}

	public void setScmUsername(String scmUsername) {
		this.scmUsername = scmUsername;
	}

	public String getScmPassword() {
		return scmPassword;
	}

	public void setScmPassword(String scmPassword) {
		this.scmPassword = scmPassword;
	}

	public String getBuildUrl() {
		return buildUrl;
	}

	public void setBuildUrl(String buildUrl) {
		this.buildUrl = buildUrl;
	}

	public void setBuildUsername(String buildUsername) {
		this.buildUsername = buildUsername;
	}

	public String getBuildUsername() {
		return buildUsername;
	}

	public void setBuildPassword(String buildPassword) {
		this.buildPassword = buildPassword;
	}

	public String getBuildPassword() {
		return buildPassword;
	}

	@Override
	public String toString() {
		return "ImportProjectsForm [scmUrl=" + scmUrl + ", scmUsername=" + scmUsername + ", buildUrl=" + buildUrl
				+ ", buildUsername=" + buildUsername + "]";
	}

}
