package org.shiftleft.carson.web.projects;

import java.util.List;

public class ConnectionValidationResult {

	private List<String> errors;

	public ConnectionValidationResult(List<String> errors) {
		this.errors = errors;
	}
	
	public List<String> getErrors() {
		return errors;
	}

	public boolean hasErrors() {
		return !errors.isEmpty();
	}

}
