package org.jenkins.client;

import org.junit.Assert;
import org.junit.Test;

import generated.HudsonMavenMavenModuleSet;
import generated.HudsonModelJob;
import generated.HudsonModelNode;
import generated.HudsonModelNodeMode;

public class ConnecToJenkins {

	@Test
	public void shouldConnectToJenkins() {

		JenkinsClient client = new JenkinsClient("http://localhost:8080");
		HudsonModelNode node = client.getHudsonInstance();

		Assert.assertEquals(HudsonModelNodeMode.NORMAL, node.getMode());

	}
	
	@Test
	public void shouldFindAJob() {

		JenkinsClient client = new JenkinsClient("http://localhost:8080/");
		HudsonMavenMavenModuleSet set = client.getMavenProject("my-usual-build");

		Assert.assertEquals("my-usual-build", set.getDisplayName() );

	}

}
