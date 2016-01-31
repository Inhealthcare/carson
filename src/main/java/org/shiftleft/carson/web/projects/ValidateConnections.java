package org.shiftleft.carson.web.projects;

import java.util.ArrayList;
import java.util.List;

import org.shiftleftautomation.build.jenkins.JenkinsClient;
import org.shiftleftautomation.svn.SVNLocation;
import org.shiftleftautomation.svn.SVNLocationBuilder;
import org.shiftleftautomation.svn.SVNTemplate;
import org.shiftleftautomation.vc.VersionControlTemplate;

public class ValidateConnections {

	private Project project;

	public ValidateConnections(Project project) {
		this.project = project;
	}

	public ConnectionValidationResult execute() {

		List<String> errorList = new ArrayList<>();

		try {
			testScm();
		} catch (Exception e) {
			errorList.add("project.test.scm.connection.error");
		}

		try {
//			testBuild();
		} catch (Exception e) {
			errorList.add("project.text.build.connection.error");
		}

		ConnectionValidationResult result = new ConnectionValidationResult(errorList);
		return result;
	}

	private void testBuild() throws Exception {

		JenkinsClient client = new JenkinsClient(project.getBuildServer().getJenkinsBuildServer().getUrl(),
				project.getBuildServer().getJenkinsBuildServer().getUsername(),
				project.getBuildServer().getJenkinsBuildServer().getPassword());
		client.getState();

	}

	private void testScm() throws Exception {

		// what valeus have we used
		SvnRepository repoConnection = project.getSourceControl().getSvnRepository();

		// assuming that we're svn
		SVNLocation location = SVNLocationBuilder.create().url(repoConnection.getUrl())
				.credentials(repoConnection.getUsername(), repoConnection.getPassword()).build();

		// test the location
		VersionControlTemplate versionControlTemplate = new SVNTemplate(location);
		versionControlTemplate.test();

	}

}
