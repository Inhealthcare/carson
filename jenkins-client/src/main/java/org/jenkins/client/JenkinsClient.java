package org.jenkins.client;

import java.util.Collections;

import org.springframework.web.client.RestTemplate;

import generated.HudsonMavenMavenModuleSet;
import generated.HudsonModelNode;

public class JenkinsClient {

	private static final String JENKINS_MODEL = "/api/xml";
	private String apiUrl;
	private RestTemplate template = new RestTemplate();

	public JenkinsClient(String baseUrl) {
		if (baseUrl.endsWith("/")) {
			baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
		}
		this.apiUrl = baseUrl;
	}

	public HudsonModelNode getHudsonInstance() {

		return template.getForEntity(apiUrl + JENKINS_MODEL, HudsonModelNode.class, Collections.emptyMap()).getBody();

	}

	public HudsonMavenMavenModuleSet getMavenProject(String jobName) {
		return template.getForEntity(apiUrl + "/job/" + jobName + JENKINS_MODEL, HudsonMavenMavenModuleSet.class,
				Collections.emptyMap()).getBody();
	}

}
