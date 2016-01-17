package com.carson.webapp.projects;

import java.util.ArrayList;
import java.util.List;

public class ProjectTest {

	private Project project;

	public ProjectTest(Project project) {
		this.project = project;
	}

	public ProjectTestResult execute() {
		
		List<String> errorList = new ArrayList<>();

		try {
			testScm();			
		} catch (Exception e) {
			errorList.add("project.test.scm.connection.error");
		}
		
		try {
			testBuild();
		} catch (Exception e) {
			errorList.add("project.text.build.connection.error");
		}
		
		ProjectTestResult result = new ProjectTestResult(errorList );
		return result;
	}

	private void testBuild() {
		
	}

	private void testScm() {
		
	}

}
