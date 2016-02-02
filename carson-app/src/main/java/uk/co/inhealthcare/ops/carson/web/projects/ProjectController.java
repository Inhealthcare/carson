package uk.co.inhealthcare.ops.carson.web.projects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import uk.co.inhealthcare.ops.build.jenkins.JenkinsClient;
import uk.co.inhealthcare.ops.build.jenkins.JenkinsClientException;
import uk.co.inhealthcare.ops.build.jenkins.api.config.ConfigFactory;
import uk.co.inhealthcare.ops.carson.web.projects.Project.Status;

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

		try {
			createJob(project);
			project.setStatus(Status.OK);
		} catch (Exception e) {
			project.setStatus(Status.FAILING);
			return "redirect:/projects/" + id;
		}

		return "redirect:/projects/" + id;

	}

	private void createJob(Project project) throws JenkinsClientException {

		BuildServer buildServer = project.getBuildServer();

		JenkinsBuildServer jenkins = buildServer.getJenkinsBuildServer();

		JenkinsClient jenkinsClient = new JenkinsClient(jenkins.getUrl(), jenkins.getUsername(), jenkins.getPassword());

		jenkinsClient.createItem(project.getName(), ConfigFactory.createMavenProject());

	}

}
