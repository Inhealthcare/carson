package uk.co.inhealthcare.build.jenkins.client;

import org.junit.Assert;
import org.junit.Test;

import uk.co.inhealthcare.ops.build.jenkins.JenkinsClient;
import uk.co.inhealthcare.ops.build.jenkins.JenkinsClientException;
import uk.co.inhealthcare.ops.build.jenkins.api.config.Config;
import uk.co.inhealthcare.ops.build.jenkins.api.config.ConfigFactory;
import uk.co.inhealthcare.ops.build.jenkins.api.job.JobState;
import uk.co.inhealthcare.ops.build.jenkins.api.state.JenkinsState;
import uk.co.inhealthcare.ops.build.jenkins.api.state.Mode;

public class ConnecToJenkins {

	@Test
	public void shouldConnectToJenkins() throws JenkinsClientException {

		JenkinsClient client = new JenkinsClient("http://localhost:8080", null, null);
		JenkinsState node = client.getState();

		Assert.assertEquals(Mode.NORMAL, node.getMode());

	}

	@Test
	public void shouldFindAJob() throws JenkinsClientException {

		String jobName = "my-usual-build";

		JenkinsClient client = new JenkinsClient("http://localhost:8080/", null, null);
		JobState set = client.getJobState(jobName);
		Assert.assertEquals(jobName, set.getDisplayName());

	}

	@Test
	public void shouldCreateAJobAndFindIt() throws JenkinsClientException {

		String jobName = "my-new-build";

		JenkinsClient client = new JenkinsClient("http://localhost:8080/", null, null);
		client.createItem(jobName, ConfigFactory.createMavenProject());
		JobState set = client.getJobState(jobName);
		Assert.assertEquals(jobName, set.getDisplayName());

	}

	@Test
	public void shouldFetchJobConfig() throws JenkinsClientException {

		String jobName = "test-maven";

		JenkinsClient client = new JenkinsClient("http://localhost:8080/", null, null);
		Config config = client.getItemConfig(jobName);
		Assert.assertEquals("maven-plugin@2.7.1", config.getPlugin());
		Assert.assertEquals("jenkins.mvn.DefaultSettingsProvider", config.getSettings().getClazz());
		Assert.assertEquals("jenkins.mvn.DefaultGlobalSettingsProvider", config.getGlobalSettings().getClazz());
		Assert.assertEquals("https://github.com/teggr/modern-java-development.git",
				config.getScm().getLocations().stream().findFirst().get().getSvnLocation().getRemote());

	}

}
