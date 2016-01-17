package com.carson.webapp.projects;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.carson.webapp.AbstractEntity;

@Entity
public class Project extends AbstractEntity {

	public enum Status {
		UNKNOWN, UPDATING, OK, FAILING;
	}

	private String name;

	@Embedded
	private BuildStatus buildStatus;

	private Status status;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "url", column = @Column(name = "SVN_URL") ) })
	private SvnRepository svnRepository;

	@Embedded
	@AttributeOverrides({ @AttributeOverride(name = "url", column = @Column(name = "JENKINS_URL") ) })
	private JenkinsBuildServer jenkinsBuildServer;

	public Project() {
		status = Status.UNKNOWN;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setBuildStatus(BuildStatus buildStatus) {
		this.buildStatus = buildStatus;
	}

	public BuildStatus getBuildStatus() {
		return buildStatus;
	}

	public void setSvnRepository(SvnRepository svnRepository) {
		this.svnRepository = svnRepository;
	}

	public SvnRepository getSvnRepository() {
		return svnRepository;
	}

	public void setJenkinsBuildServer(JenkinsBuildServer jenkinsBuildServer) {
		this.jenkinsBuildServer = jenkinsBuildServer;
	}

	public JenkinsBuildServer getJenkinsBuildServer() {
		return jenkinsBuildServer;
	}

}
