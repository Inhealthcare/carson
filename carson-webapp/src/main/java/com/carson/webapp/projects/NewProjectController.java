package com.carson.webapp.projects;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/projects/newProject")
public class NewProjectController {

	private static final String NEW_PROJECT_FORM_OBJECT_NAME = "newProjectForm";

	private static final Logger logger = LoggerFactory.getLogger(NewProjectController.class);

	@Autowired
	private ProjectRepository projectRepository;

	@ModelAttribute(NEW_PROJECT_FORM_OBJECT_NAME)
	private NewProjectForm newProjectForm() {
		return new NewProjectForm();
	}

	@RequestMapping(method = RequestMethod.GET)
	public String newProject(ModelMap map) {

		map.addAttribute("projects", projectRepository.findAllByOrderByNameAsc());

		return "newProject";

	}

	@RequestMapping(method = RequestMethod.POST)
	public String createNewProject(ModelMap map, @Valid @ModelAttribute NewProjectForm form, BindingResult binding) {

		logger.debug("Creaing new form " + form);

		// test the initial settings before creating the new project
		Project project = new Project();
		form.update(project);
		ProjectTest test = new ProjectTest(project);
		ProjectTestResult result = test.execute();

		// map results to binding object
		if (result.hasErrors()) {
			BindingResultMapper mapper = new BindingResultMapper();
			mapper.addErrors(result, NEW_PROJECT_FORM_OBJECT_NAME, binding);
		}

		// return to page if we've got errors
		if (binding.hasErrors()) {
			return newProject(map);
		}
		
		project.setBuildStatus(new BuildStatus());
		
		projectRepository.save(project);

		return "redirect:/projects";

	}

}
