package uk.co.inhealthcare.ops.svn;

import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.inhealthcare.ops.vc.ProjectRoot;
import uk.co.inhealthcare.ops.vc.Trunk;
import uk.co.inhealthcare.ops.vc.VersionControlClientException;

public class SvnClient {

	private static final Logger logger = LoggerFactory.getLogger(SvnClient.class);

	@Test
	public void shouldTestMultipleProjects() {

		try {

			String url = System.getProperty("svn.url");
			String username = System.getProperty("svn.username");
			String password = System.getProperty("svn.password");

			SVNLocation location = SVNLocationBuilder.create().url(url).credentials(username, password).build();

			SVNTemplate template = new SVNTemplate(location);

			logger.info("TESTING CONECTION: {}", location);
			template.testConnection();
			logger.info("CONECTION TESTED: {}");

			logger.info("LISTING PROJECT ROOTS");
			ProjectRoot testProject = null;
			Set<ProjectRoot> listProjectRoots = template.getProjectRoots();
			for (ProjectRoot projectRoot : listProjectRoots) {
				logger.info("PROJECT ROOT FOUND: {} {}", projectRoot.getName(), projectRoot.getPath());
				if (testProject(projectRoot)) {
					testProject = projectRoot;
				}
			}
			logger.info("PROJECT ROOTS LISTED");

			logger.info("INTROSPECTING TEST PROJECT");
			if (testProject != null) {
				logger.info("PROJECT LOCATION {}", testProject.getLocation());
				Trunk trunk = testProject.getTrunk();
				logger.info("PROJECT TRUNK PATH {}", trunk.getPath());
				InputStream content = template.getContent(trunk, "/README.md");
				try {
					logger.debug(IOUtils.toString(content));
				} catch (IOException e) {
					e.printStackTrace();
				}
				logger.info("Found {} branches", testProject.getBranches().size());
				logger.info("Found {} tags", testProject.getTags().size());
			}
			logger.info("FINISHED INTROSPECTING TEST PROJECT");

		} catch (VersionControlClientException e) {
			e.printStackTrace();
		}

	}

	private boolean testProject(ProjectRoot projectRoot) {
		return "CARSON_TEST_PROJECT_1".equals(projectRoot.getName());
	}

}
