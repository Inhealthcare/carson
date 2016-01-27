package com.carson.webapp.projects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.jenkins.client.JenkinsClient;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNException;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

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
			testBuild();
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

		SvnRepository repoConnection = project.getSourceControl().getSvnRepository();

		SVNRepository repository = null;
		try {
			String url = repoConnection.getUrl();
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
			ISVNAuthenticationManager authenticationManager = SVNWCUtil
					.createDefaultAuthenticationManager(repoConnection.getUsername(), repoConnection.getPassword());
			repository.setAuthenticationManager(authenticationManager);

			System.out.println("Repository Root: " + repository.getRepositoryRoot(true));
			System.out.println("Repository UUID: " + repository.getRepositoryUUID(true));

			SVNNodeKind nodeKind = repository.checkPath("", -1);
			if (nodeKind == SVNNodeKind.NONE) {
				System.err.println("There is no entry at '" + url + "'.");
				System.exit(1);
			} else if (nodeKind == SVNNodeKind.FILE) {
				System.err.println("The entry at '" + url + "' is a file while a directory was expected.");
				System.exit(1);
			}

			validateProjectStructure(repository);

			long latestRevision = repository.getLatestRevision();
			System.out.println("Repository latest revision: " + latestRevision);

		} catch (Exception e) {
			throw e;
		}

	}

	private void validateProjectStructure(SVNRepository repository) throws Exception {

		List<String> projectFolders = new ArrayList<>(Arrays.asList("trunk", "branches", "tags"));

		String path = "";
		Collection entries = repository.getDir(path, -1, null, (Collection) null);
		Iterator iterator = entries.iterator();
		while (iterator.hasNext()) {
			SVNDirEntry entry = (SVNDirEntry) iterator.next();
			System.out.println("/" + (path.equals(path) ? path : path + "/") + entry.getName() + " ( author: '"
					+ entry.getAuthor() + "'; revision: " + entry.getRevision() + "; date: " + entry.getDate() + ")");
			if (!projectFolders.contains(entry.getName())) {
				throw new Exception("Project structure was incorrect");
			} else {
				projectFolders.remove(entry.getName());
			}
		}

		if (!projectFolders.isEmpty()) {
			throw new Exception("Project structure was incorrect");
		}

	}

	public static void listEntries(SVNRepository repository, String path) throws SVNException {
		Collection entries = repository.getDir(path, -1, null, (Collection) null);
		Iterator iterator = entries.iterator();
		while (iterator.hasNext()) {
			SVNDirEntry entry = (SVNDirEntry) iterator.next();
			System.out.println("/" + (path.equals("") ? "" : path + "/") + entry.getName() + " ( author: '"
					+ entry.getAuthor() + "'; revision: " + entry.getRevision() + "; date: " + entry.getDate() + ")");
			if (entry.getKind() == SVNNodeKind.DIR) {
				listEntries(repository, (path.equals("")) ? entry.getName() : path + "/" + entry.getName());
			}
		}
	}

}
