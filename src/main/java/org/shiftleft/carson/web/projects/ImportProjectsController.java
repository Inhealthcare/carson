package org.shiftleft.carson.web.projects;

import java.util.List;

import javax.validation.Valid;

import org.shiftleftautomation.svn.SVNLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/projects/import")
public class ImportProjectsController {

	private static final String IMPORT_PROJECTS_FORM_OBJECT_NAME = "importProjectsForm";

	private static final Logger logger = LoggerFactory.getLogger(ImportProjectsController.class);

	@Autowired
	private ProjectRepository projectRepository;

	@Autowired(required = false)
	private SVNLocation svnLocation;

	@ModelAttribute(IMPORT_PROJECTS_FORM_OBJECT_NAME)
	private ImportProjectsForm importProjectsForm() {
		ImportProjectsForm form = new ImportProjectsForm();
		if (svnLocation != null) {
			form.setScmUrl(svnLocation.getUrl());
			form.setScmUsername(svnLocation.getUsername());
			form.setScmPassword(svnLocation.getPassword());
		}
		return form;
	}

	@RequestMapping(method = RequestMethod.GET)
	public String importProjects(ModelMap map) {
		return "importProjects";
	}

	@RequestMapping(method = RequestMethod.POST)
	public String createNewProject(ModelMap map, @Valid @ModelAttribute ImportProjectsForm form,
			BindingResult binding) {

		logger.debug("Importing projects " + form);

		// return to page if we've got errors
		if (binding.hasErrors()) {
			return importProjects(map);
		}

		// test the initial settings before creating the new project
		Project project = new Project();

		SvnRepository svn = new SvnRepository();
		svn.setUrl(form.getScmUrl());
		svn.setUsername(form.getScmUsername());
		svn.setPassword(form.getScmPassword());

		SourceControl sc = new SourceControl();
		sc.setSvnRepository(svn);
		project.setSourceControl(sc);

		JenkinsBuildServer server = new JenkinsBuildServer();
//		server.setUrl(form.getBuildUrl());
		BuildServer bs = new BuildServer();
		bs.setJenkinsBuildServer(server);
		project.setBuildServer(bs);

		ValidateConnections test = new ValidateConnections(project);
		ConnectionValidationResult result = test.execute();

		// map results to binding object
		if (result.hasErrors()) {
			BindingResultMapper mapper = new BindingResultMapper();
			mapper.addErrors(result, IMPORT_PROJECTS_FORM_OBJECT_NAME, binding);
		}

		// now need to create multiple projects from the output of the svn
		// search for project results
		try {
			List<Project> projects = new ProjectImporter(form).importProjects().getList();
			for (Project projectForCreations : projects) {
				projectRepository.save(projectForCreations);
			}
		} catch (Exception e) {
			binding.addError(new ObjectError(IMPORT_PROJECTS_FORM_OBJECT_NAME, e.getMessage()));
			return importProjects(map);
		}

		return "redirect:/projects";

	}

}
