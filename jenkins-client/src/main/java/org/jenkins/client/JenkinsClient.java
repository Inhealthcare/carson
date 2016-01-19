package org.jenkins.client;

import java.io.StringWriter;
import java.net.URI;
import java.util.Collections;

import org.jenkins.client.api.Config;
import org.jenkins.client.api.JenkinsState;
import org.jenkins.client.api.JobState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class JenkinsClient {

	private static final Logger logger = LoggerFactory.getLogger(JenkinsClient.class);

	private static final String CREATE_ITEM_ENDPOINT = "/createItem";
	private static final String JSON_ENDPOINT = "/api/json";
	private static final String JOB_PATH = "/job/";

	private String apiUrl;
	private RestTemplate template = new RestTemplate();
	private ConfigFactory factory = new ConfigFactory();

	public JenkinsClient(String baseUrl) {
		if (baseUrl.endsWith("/")) {
			baseUrl = baseUrl.substring(0, baseUrl.length() - 1);
		}
		this.apiUrl = baseUrl;
	}

	public JenkinsState getState() throws JenkinsClientException {
		try {

			return template.getForEntity(apiUrl + JSON_ENDPOINT, JenkinsState.class, Collections.emptyMap()).getBody();

		} catch (Exception e) {
			logger.debug("Fault {}", e);
			throw new JenkinsClientException(e);
		}
	}

	public JobState getJobState(String jobName) throws JenkinsClientException {
		try {

			return template
					.getForEntity(apiUrl + JOB_PATH + jobName + JSON_ENDPOINT, JobState.class, Collections.emptyMap())
					.getBody();

		} catch (Exception e) {
			logger.debug("Fault {}", e);
			throw new JenkinsClientException(e);
		}
	}

	public void createItem(CreateItem createItem) throws JenkinsClientException {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_XML_VALUE);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl + CREATE_ITEM_ENDPOINT)
					.queryParam("name", createItem.getName());

			Config config = factory.create(createItem.getTemplate());
			
			XmlConfigConverter xmlBuilder = new XmlConfigConverter();
			StringWriter writer = new StringWriter();
			xmlBuilder.write(config, writer);

			HttpEntity<?> entity = new HttpEntity<>(writer.toString(), headers);

			URI uri = builder.build().encode().toUri();

			// build up config
			logger.debug("Sending to {} the request {}", uri, entity);
			ResponseEntity<String> responseEntity = template.exchange(uri, HttpMethod.POST, entity, String.class);
			logger.debug("Received {}", responseEntity);

		} catch (Exception e) {
			logger.debug("Fault {}", e);
			throw new JenkinsClientException(e);
		}

	}

}
