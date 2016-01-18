package org.jenkins.client;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import generated.HudsonMavenMavenModuleSet;
import generated.HudsonModelJob;
import generated.HudsonModelNode;

public class JenkinsClient {

	private static final Logger logger = LoggerFactory.getLogger(JenkinsClient.class);

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

	public void createItem(CreateItem createItem) {

		HttpHeaders headers = new HttpHeaders();
		headers.set("Content-Type", MediaType.APPLICATION_XML_VALUE);

		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl + "/createItem").queryParam("name",
				createItem.getName());

		JobXmlBuilder xmlBuilder = new JobXmlBuilder(createItem.getConfig());

		HttpEntity<?> entity = new HttpEntity<>(xmlBuilder.toXml(), headers);

		URI uri = builder.build().encode().toUri();

		// build up config
		try {
			logger.debug("Sending to {} the request {}", uri, entity);
			ResponseEntity<String> responseEntity = template.exchange(uri, HttpMethod.POST, entity, String.class);
			logger.debug("Received {}", responseEntity);
		} catch (RestClientException e) {
			e.printStackTrace();
		}

		// post to create endpoint. result?

	}

}
