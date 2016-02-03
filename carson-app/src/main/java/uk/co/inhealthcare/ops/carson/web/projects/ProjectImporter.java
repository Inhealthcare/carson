package uk.co.inhealthcare.ops.carson.web.projects;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import uk.co.inhealthcare.ops.svn.SVNLocation;
import uk.co.inhealthcare.ops.svn.SVNLocationBuilder;
import uk.co.inhealthcare.ops.svn.SVNTemplate;
import uk.co.inhealthcare.ops.vc.ProjectRoot;
import uk.co.inhealthcare.ops.vc.VersionControlTemplate;

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

		// assuming that we're svn
		SVNLocation location = SVNLocationBuilder.create().url(importSettings.getScmUrl())
				.credentials(importSettings.getScmUsername(), importSettings.getScmPassword()).build();

		// test the location
		VersionControlTemplate versionControlTemplate = new SVNTemplate(location);
		versionControlTemplate.testConnection();

		// convert all the version control project roots to projects
		list.addAll(versionControlTemplate.getProjectRoots().stream().map(r -> createProject(r))
				.collect(Collectors.toList()));

		return this;

	}

	private Project createProject(ProjectRoot root) {

		Project project = new Project();
		project.setName(root.getName());

		BuildServer buildServer = new BuildServer();
		JenkinsBuildServer jenkinsBuildServer = new JenkinsBuildServer();
		// jenkinsBuildServer.setUrl(importSettings.getBuildUrl());
		// jenkinsBuildServer.setUsername(importSettings.getBuildUsername());
		// jenkinsBuildServer.setPassword(importSettings.getBuildPassword());
		buildServer.setJenkinsBuildServer(jenkinsBuildServer);
		project.setBuildServer(buildServer);

		SourceControl sourceControl = new SourceControl();
		SvnRepository svnRepository = new SvnRepository();
		svnRepository.setUrl(root.getLocation());
		svnRepository.setUsername(importSettings.getScmUsername());
		svnRepository.setPassword(importSettings.getScmPassword());
		sourceControl.setSvnRepository(svnRepository);

		sourceControl.setTrunk(root.getTrunk().getName());
		sourceControl.setBranches(root.getBranches().stream().map(b -> b.getName()).collect(Collectors.toSet()));
		sourceControl.setTags(root.getTags().stream().map(b -> b.getName()).collect(Collectors.toSet()));

		project.setSourceControl(sourceControl);

		return project;

	}

}
