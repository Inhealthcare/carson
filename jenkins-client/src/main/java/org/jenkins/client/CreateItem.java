package org.jenkins.client;

public class CreateItem {

	private String name;
	private JobTemplate template;

	public CreateItem(String name, JobTemplate template) {
		this.name = name;
		this.template = template;
	}

	public String getName() {
		return name;
	}

	public JobTemplate getTemplate() {
		return template;
	}

}
