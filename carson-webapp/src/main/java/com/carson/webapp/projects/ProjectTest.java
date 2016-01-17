package com.carson.webapp.projects;

import java.net.MalformedURLException;
import java.net.URL;
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

		ProjectTestResult result = new ProjectTestResult(errorList);
		return result;
	}

	private void testBuild() throws Exception {

		URL url = new URL(project.getJenkinsBuildServer().getUrl());
		url.openConnection();

	}

	private void testScm() throws Exception {

		URL url = new URL(project.getSvnRepository().getUrl());
		url.openConnection();

	}

}
