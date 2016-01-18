package com.carson.webapp.projects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class BuildServer {

	@Embedded
	private BuildStatus buildStatus = new BuildStatus();

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "url", column = @Column(name = "JENKINS_URL") ) })
	private JenkinsBuildServer jenkinsBuildServer;

	public JenkinsBuildServer getJenkinsBuildServer() {
		return jenkinsBuildServer;
	}

	public void setJenkinsBuildServer(JenkinsBuildServer jenkinsBuildServer) {
		this.jenkinsBuildServer = jenkinsBuildServer;
	}

	public BuildStatus getBuildStatus() {
		return buildStatus;
	}

	public void setBuildStatus(BuildStatus buildStatus) {
		this.buildStatus = buildStatus;
	}

}
