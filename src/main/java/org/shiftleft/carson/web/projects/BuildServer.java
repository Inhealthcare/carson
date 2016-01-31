package org.shiftleft.carson.web.projects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import org.shiftleft.carson.web.AbstractEntity;

@Entity
public class BuildServer extends AbstractEntity {
	
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
