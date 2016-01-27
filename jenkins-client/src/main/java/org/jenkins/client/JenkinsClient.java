package org.jenkins.client;

import java.io.StringWriter;
import java.net.URI;
import java.util.Arrays;
import java.util.Collections;

import org.jenkins.client.api.config.Config;
import org.jenkins.client.api.config.XmlConfigConverter;
import org.jenkins.client.api.job.JobState;
import org.jenkins.client.api.state.JenkinsState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

public class JenkinsClient {

	private static final Logger logger = LoggerFactory.getLogger(JenkinsClient.class);

	private static final String CREATE_ITEM_ENDPOINT = "/createItem";
	private static final String JSON_ENDPOINT = "/api/json";
	private static final String CONFIG_ENDPOINT = "/config.xml";
	private static final String JOB_PATH = "/job/";

	private String apiUrl;
	private RestTemplate template = new RestTemplate();

	public JenkinsClient(String baseUrl, String username, String password) {
		if (!StringUtils.isEmpty(username)) {
			ClientHttpRequestInterceptor auth = new BasicAuthHttpRequestInterceptor(username, password);
			this.template.setInterceptors(Arrays.asList(auth));
		}
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

	public void createItem(String name, Config config) throws JenkinsClientException {

		try {

			HttpHeaders headers = new HttpHeaders();
			headers.set("Content-Type", MediaType.APPLICATION_XML_VALUE);

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl + CREATE_ITEM_ENDPOINT)
					.queryParam("name", name);

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

	public Config getItemConfig(String name) throws JenkinsClientException {
		try {

			HttpHeaders headers = new HttpHeaders();

			UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(apiUrl + JOB_PATH + name + CONFIG_ENDPOINT);

			XmlConfigConverter xmlBuilder = new XmlConfigConverter();

			HttpEntity<?> entity = new HttpEntity<>(headers);

			URI uri = builder.build().encode().toUri();

			// build up config
			logger.debug("Getting {}", uri, entity);
			ResponseEntity<Config> responseEntity = template.exchange(uri, HttpMethod.GET, entity, Config.class);
			logger.debug("Fetched {}", responseEntity);

			return responseEntity.getBody();

		} catch (Exception e) {
			logger.debug("Fault {}", e);
			throw new JenkinsClientException(e);
		}
	}

}
