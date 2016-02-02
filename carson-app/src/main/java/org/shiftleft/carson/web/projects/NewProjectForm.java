package org.shiftleft.carson.web.projects;

import org.hibernate.validator.constraints.NotBlank;

public class NewProjectForm {

	@NotBlank
	private String scmUrl;

	private String scmUsername;

	private String scmPassword;

//	@NotBlank
//	private String buildUrl;
//
//	@NotBlank
//	private String name;
//
//	private String buildUsername;
//
//	private String buildPassword;

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

//	public String getBuildUrl() {
//		return buildUrl;
//	}
//
//	public void setBuildUrl(String buildUrl) {
//		this.buildUrl = buildUrl;
//	}
//
//	public String getName() {
//		return name;
//	}
//
//	public void setName(String name) {
//		this.name = name;
//	}
//
//	public void setBuildUsername(String buildUsername) {
//		this.buildUsername = buildUsername;
//	}
//
//	public String getBuildUsername() {
//		return buildUsername;
//	}
//
//	public void setBuildPassword(String buildPassword) {
//		this.buildPassword = buildPassword;
//	}
//
//	public String getBuildPassword() {
//		return buildPassword;
//	}

}
