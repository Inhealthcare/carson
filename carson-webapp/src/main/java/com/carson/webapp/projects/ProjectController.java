package com.carson.webapp.projects;

import org.jenkins.client.JenkinsClient;
import org.jenkins.client.JobConfig;
import org.jenkins.client.CreateItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.carson.webapp.projects.Project.Status;

@RequestMapping({ "/projects" })
@Controller
public class ProjectController {

	@Autowired
	private ProjectRepository projectRepository;

	@RequestMapping(method = RequestMethod.GET)
	public String projectsHome(ModelMap map) {

		map.addAttribute("projects", projectRepository.findAllByOrderByNameAsc());

		return "projects";

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public String projectHome(ModelMap map, @PathVariable("id") Long id) {

		map.addAttribute("project", projectRepository.findOne(id));

		return "project";

	}

	@RequestMapping(value = "/{id}", method = RequestMethod.POST, params = "create")
	public String create(ModelMap map, @PathVariable("id") Long id) {

		Project project = projectRepository.findOne(id);
		
		createJob(project);

		project.setStatus(Status.OK);

		return "redirect:/projects/" + id;

	}

	private void createJob(Project project) {

		BuildServer buildServer = project.getBuildServer();

		JenkinsBuildServer jenkins = buildServer.getJenkinsBuildServer();
		
		JenkinsClient jenkinsClient = new JenkinsClient(jenkins.getUrl());
		
		jenkinsClient.createItem(new CreateItem(project.getName(), new JobConfig()));
		
	}

}
