package com.carson.webapp.projects;

import java.util.List;

public class ProjectTestResult {

	private List<String> errors;

	public ProjectTestResult(List<String> errors) {
		this.errors = errors;
	}
	
	public List<String> getErrors() {
		return errors;
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

}
