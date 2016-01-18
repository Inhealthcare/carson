package org.jenkins.client;

public class CreateItem {

	private String name;
	private JobConfig config;

	public CreateItem(String name, JobConfig config) {
		this.name = name;
		this.config = config;
	}

	public String getName() {
		return name;
	}

	public JobConfig getConfig() {
		return config;
	}

}
