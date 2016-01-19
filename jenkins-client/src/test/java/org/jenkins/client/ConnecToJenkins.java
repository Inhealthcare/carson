package org.jenkins.client;

import org.jenkins.client.api.JobState;
import org.jenkins.client.api.JenkinsState;
import org.jenkins.client.api.Mode;
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
		client.createItem(new CreateItem(jobName, new JobTemplate()));
		JobState set = client.getJobState(jobName);
		Assert.assertEquals(jobName, set.getDisplayName());

	}

}
