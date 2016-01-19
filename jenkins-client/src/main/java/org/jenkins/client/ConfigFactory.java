package org.jenkins.client;

import org.jenkins.client.api.Config;

public class ConfigFactory {

	public Config create(JobTemplate template) {
		return new Config();
	}

}
