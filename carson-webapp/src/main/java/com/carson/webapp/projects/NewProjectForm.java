package com.carson.webapp.projects;

import org.hibernate.validator.constraints.NotBlank;

public class NewProjectForm {

	@NotBlank
	private String scmUrl;

	@NotBlank
	private String scmUsername;

	@NotBlank
	private String scmPassword;

	@NotBlank
	private String buildUrl;

	@NotBlank
	private String name;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "NewProjectForm [scmUrl=" + scmUrl + ", scmUsername=" + scmUsername + ", scmPassword=" + scmPassword
				+ ", buildUrl=" + buildUrl + ", name=" + name + "]";
	}

	public void update(Project project) {

		project.setName(name);

		SvnRepository svn = new SvnRepository();
		svn.setUrl(scmUrl);
		svn.setUsername(scmUsername);
		svn.setPassword(scmPassword);

		project.setSvnRepository(svn);

		JenkinsBuildServer server = new JenkinsBuildServer();
		server.setUrl(buildUrl);
		project.setJenkinsBuildServer(server);

	}

}
