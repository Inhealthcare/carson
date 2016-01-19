package org.jenkins.client;

import org.jenkins.client.api.config.Config;
import org.jenkins.client.api.config.ConfigFactory;
import org.jenkins.client.api.job.JobState;
import org.jenkins.client.api.state.JenkinsState;
import org.jenkins.client.api.state.Mode;
import org.junit.Assert;
import org.junit.Test;

public class ConnecToJenkins {

	@Test
	public void shouldConnectToJenkins() throws JenkinsClientException {

		JenkinsClient client = new JenkinsClient("http://localhost:8080");
		JenkinsState node = client.getState();

		Assert.assertEquals(Mode.NORMAL, node.getMode());

	}

	@Test
	public void shouldFindAJob() throws JenkinsClientException {

		String jobName = "my-usual-build";

		JenkinsClient client = new JenkinsClient("http://localhost:8080/");
		JobState set = client.getJobState(jobName);
		Assert.assertEquals(jobName, set.getDisplayName());

	}

	@Test
	public void shouldCreateAJobAndFindIt() throws JenkinsClientException {

		String jobName = "my-new-build";

		JenkinsClient client = new JenkinsClient("http://localhost:8080/");
		client.createItem(jobName, ConfigFactory.createMavenProject());
		JobState set = client.getJobState(jobName);
		Assert.assertEquals(jobName, set.getDisplayName());

	}

	@Test
	public void shouldFetchJobConfig() throws JenkinsClientException {

		String jobName = "test-maven";

		JenkinsClient client = new JenkinsClient("http://localhost:8080/");
		Config config = client.getItemConfig(jobName);
		Assert.assertEquals("maven-plugin@2.7.1", config.getPlugin());
		Assert.assertEquals("jenkins.mvn.DefaultSettingsProvider", config.getSettings().getClazz());
		Assert.assertEquals("jenkins.mvn.DefaultGlobalSettingsProvider", config.getGlobalSettings().getClazz());
		Assert.assertEquals("https://github.com/teggr/modern-java-development.git",
				config.getScm().getLocations().stream().findFirst().get().getSvnLocation().getRemote());

	}

}
