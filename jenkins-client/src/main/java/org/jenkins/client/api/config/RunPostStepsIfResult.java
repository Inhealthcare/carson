package org.jenkins.client.api.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;

@XmlAccessorType(XmlAccessType.FIELD)
public class RunPostStepsIfResult {

	private String name;
	private int ordinal;
	private String color;
	private boolean completeBuild;

}
