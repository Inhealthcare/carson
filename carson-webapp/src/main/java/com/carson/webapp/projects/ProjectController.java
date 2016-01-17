package com.carson.webapp.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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

}
