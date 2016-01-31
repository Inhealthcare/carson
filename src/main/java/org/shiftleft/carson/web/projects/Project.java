package org.shiftleft.carson.web.projects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.shiftleft.carson.web.AbstractEntity;

@Entity
public class Project extends AbstractEntity {

	public enum Status {
		UNKNOWN, UPDATING, OK, FAILING;
	}

	private String name;

	private Status status;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "source_control_id")
	private SourceControl sourceControl;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "build_server_id")
	private BuildServer buildServer;

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

	public SourceControl getSourceControl() {
		return sourceControl;
	}

	public void setSourceControl(SourceControl sourceControl) {
		this.sourceControl = sourceControl;
	}

	public BuildServer getBuildServer() {
		return buildServer;
	}

	public void setBuildServer(BuildServer buildServer) {
		this.buildServer = buildServer;
	}

}
