package com.carson.webapp.projects;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tmatesoft.svn.core.SVNDirEntry;
import org.tmatesoft.svn.core.SVNNodeKind;
import org.tmatesoft.svn.core.SVNURL;
import org.tmatesoft.svn.core.auth.ISVNAuthenticationManager;
import org.tmatesoft.svn.core.io.SVNRepository;
import org.tmatesoft.svn.core.io.SVNRepositoryFactory;
import org.tmatesoft.svn.core.wc.SVNWCUtil;

public class ProjectImporter {

	private static final Logger logger = LoggerFactory.getLogger(ProjectImporter.class);

	private ImportProjectsForm importSettings;
	private List<Project> list = new ArrayList<>();

	public ProjectImporter(ImportProjectsForm importSettings) {
		this.importSettings = importSettings;
	}

	public List<Project> getList() {
		return list;
	}

	public ProjectImporter importProjects() throws Exception {

		logger.info("IMPORTING {}", importSettings);

		SVNRepository repository = null;
		try {
			String url = importSettings.getScmUrl();
			repository = SVNRepositoryFactory.create(SVNURL.parseURIEncoded(url));
			ISVNAuthenticationManager authenticationManager = SVNWCUtil.createDefaultAuthenticationManager(
					importSettings.getScmUsername(), importSettings.getScmPassword());
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

			builsdProjects(repository);

			long latestRevision = repository.getLatestRevision();
			System.out.println("Repository latest revision: " + latestRevision);

		} catch (Exception e) {
			throw e;
		}

		return this;

	}

	private void builsdProjects(SVNRepository repository) throws Exception {

		String path = "";

		// get all the potential project roots
		Collection entries = repository.getDir(path, -1, null, (Collection) null);

		Iterator iterator = entries.iterator();
		while (iterator.hasNext()) {

			// potential project root
			SVNDirEntry entry = (SVNDirEntry) iterator.next();

			System.out.println("/" + (path.equals(path) ? path : path + "/") + entry.getName() + " ( author: '"
					+ entry.getAuthor() + "'; revision: " + entry.getRevision() + "; date: " + entry.getDate() + ")");

			String entryPath = entryPath(path, entry);

			try {

				// this is a potential project, validate the root
				validateProjectStructure(repository, entryPath);

				// create the project if valid
				createProject(entry.getName(), entry.getURL());

			} catch (Exception e) {

				logger.warn("Did not process {} because {}", entryPath, e.getMessage());

			}

		}

	}

	private void validateProjectStructure(SVNRepository repository, String path) throws Exception {

		List<String> projectFolders = new ArrayList<>(Arrays.asList("trunk", "branches", "tags"));

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

	private static String entryPath(String path, SVNDirEntry entry) {
		return (path.equals("")) ? entry.getName() : path + "/" + entry.getName();
	}

	private void createProject(String name, SVNURL svnurl) {
		Project project = new Project();
		project.setName(name);

		BuildServer buildServer = new BuildServer();
		JenkinsBuildServer jenkinsBuildServer = new JenkinsBuildServer();
		jenkinsBuildServer.setUrl(importSettings.getBuildUrl());
		jenkinsBuildServer.setUsername(importSettings.getBuildUsername());
		jenkinsBuildServer.setPassword(importSettings.getBuildPassword());
		buildServer.setJenkinsBuildServer(jenkinsBuildServer);
		project.setBuildServer(buildServer);

		SourceControl sourceControl = new SourceControl();
		SvnRepository svnRepository = new SvnRepository();
		svnRepository.setUrl(svnurl.toDecodedString());
		svnRepository.setUsername(importSettings.getScmUsername());
		svnRepository.setPassword(importSettings.getScmPassword());
		sourceControl.setSvnRepository(svnRepository);
		project.setSourceControl(sourceControl);
		list.add(project);
	}

}
