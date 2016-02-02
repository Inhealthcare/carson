package uk.co.inhealthcare.ops.carson.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import uk.co.inhealthcare.ops.carson.web.projects.NewProjectForm;
import uk.co.inhealthcare.ops.carson.web.projects.Project;
import uk.co.inhealthcare.ops.carson.web.projects.ProjectRepository;

@Profile("dev")
@Component
public class DataLoader implements CommandLineRunner {

	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public void run(String... arg0) throws Exception {
		
		Project project = new Project();
		
		NewProjectForm form = new NewProjectForm();
//		form.setName("my-maven-project");
//		form.setBuildUrl("http://localhost:8080/");
		form.setScmUrl("https://github.com/teggr/modern-java-development.git");
		form.setScmUsername("teggr");
		
//		form.update(project);
		
//		projectRepository.save(project);
		
	}
}
