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

	private Status status;

	@Embedded
	private SourceControl sourceControl;

	@Embedded
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
