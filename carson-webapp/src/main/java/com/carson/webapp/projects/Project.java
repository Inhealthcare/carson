package com.carson.webapp.projects;

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

}
